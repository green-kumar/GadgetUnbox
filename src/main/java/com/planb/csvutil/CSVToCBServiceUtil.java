package com.planb.csvutil;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.planb.dao.mobile.Mobile;
import com.planb.dao.mobile.mobileSubFeature.AdditionalFeatures;
import com.planb.dao.review.ReviewDetails;
import com.planb.inmemoery.service.ProductEnum;
import com.planb.metadata.ProductMetaDataRDBService;
import com.planb.product.util.EUtil;
import com.planb.search.dao.SearchProductDao;
import com.planb.search.service.SearchService;
import com.planb.service.ProductService;
import com.planb.service.ReviewService;

@Component
public class CSVToCBServiceUtil {
	@Autowired
	EUtil productUtil;

	@Autowired
	ProductService productService;

	@Autowired
	ReviewService reviewService;
	
	@Autowired
	SearchService SS;
	
	@Autowired
	ProductMetaDataRDBService PMDS;

	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		CSVToCBServiceUtil cSVToCBServiceUtil = context.getBean("CSVToCBServiceUtil", CSVToCBServiceUtil.class);
		cSVToCBServiceUtil.addSearchDaoFromMainProducts();

		// cSVToCBServiceUtil.afterPropertiesSet();
		// cSVToCBServiceUtil.addReviwesToEachProduct();
		//cSVToCBServiceUtil.productSanityTest();

	}
	
	
	private void addSearchDaoFromMainProducts() throws Exception{
		List<Object> OL  =  productService.getAllProduct(ProductEnum.MOBILE.toString());
		List<Mobile> ML =OL.stream().map(o -> (Mobile)o).collect(Collectors.toList());
		
		for(Mobile M : ML){
			try{
			int count = SS.getSearchProductSeqCount();
			SearchProductDao SPD=new SearchProductDao(M.getName(), ProductEnum.MOBILE, "SP_"+count, M.getProductId(), false); 
			SPD.setBrand(M.getBrand());
			SPD.setBestBuyPrice(M.getBestBuyPrice());
			SPD.setRating(PMDS.get(M.getProductId()).getAvgRating());
			productService.addProduct(SPD, ProductEnum.SEARCH.toString(), "SP_"+count);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		System.out.println("done");
		
	}

	private void productSanityTest() {
		List<Object> objList = productService.getAllProduct("mobile");

		for (Object obj : objList) {
			Mobile mobile = (Mobile) obj;
			System.out.println(mobile.getProductId());
			List<String> reviewIds = mobile.getUserReviewIds();
			for (String review : reviewIds) {
				ReviewDetails reviewDetails = (ReviewDetails) productService.getProduct(review, "review");
				System.out.println(reviewDetails.toString());
			}
			System.out.println("*********************************************************************");

		}
	}

	public void addReviwesToEachProduct() throws Exception {
		char c;
		/*
		 * for(int i=97;i<123;i++){ c=(char)i; String name=c+"@"+c+".com";
		 * UserDetail ud=new UserDetail(name,name,"123");
		 * productService.addProduct(ud, "user",name );
		 * 
		 * }
		 */

		System.out.println("Hello world");
		ReviewUtil rUtil = new ReviewUtil();
		List<Object> mobileList = productService.getAllProduct("mobile");
		List<ReviewUtilPojo> CsvReviewList = rUtil.readFromCSV(
				"C:/Users/grkuma/Dropbox/planb/review-aggregator-electronics/src/main/java/com/planb/csvutil/ReviewUtil.csv");
		Map<String, String> mobileNameWithProductIdMap = new HashMap<String, String>();
		for (Object obj : mobileList) {
			Mobile m = (Mobile) obj;
			mobileNameWithProductIdMap.put(m.getName(), m.getProductId());

		}

		int i = 97;
		String productId = null;
		int j = 0;

		for (ReviewUtilPojo reviewUtilPojo : CsvReviewList) {
			try {
				if (i > 122)
					i = 97;
				c = (char) i;

				String name = c + "@" + c + ".com";
				ReviewDetails rd = new ReviewDetails();
				rd.setByUserId(name);
				rd.setCategory("mobile");
				rd.setCreatedOn(reviewUtilPojo.getReviewDate());
				if (mobileNameWithProductIdMap.get(reviewUtilPojo.getReviewId()) == null) {
					productId = mobileNameWithProductIdMap.get(mobileNameWithProductIdMap.keySet().toArray()[j % 78]);
				}

				rd.setForProductId(productId);
				rd.setReviewHeadline(reviewUtilPojo.getReviewHeadline());
				rd.setReviewPros(reviewUtilPojo.getReviewContent());
				rd.setReviewCons(reviewUtilPojo.getReviewContent());
				Double rating;

				try {
					rating = Double.parseDouble(reviewUtilPojo.getRating().substring(0, 1));
				} catch (NumberFormatException e) {
					e.printStackTrace();
					rating = 2.5;

				}
				rd.setRating(rating);
				rd.setLike(i);
				reviewService.submitReview(rd);
				i++;
				j++;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public void afterPropertiesSet() throws Exception {
		boolean flag = true;
		int i = 0;
		MobileUtil mobileUtil = new MobileUtil();
		List<MobileUtilPojo> mobilesPojoList = mobileUtil.readFromCSV(
				"C:/Users/grkuma/Dropbox/planb/review-aggregator-electronics/src/main/java/com/planb/csvutil/MobileUtil.csv");
		Mobile mobile;
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "planb", "api_key", "359772246374455",
				"api_secret", "FZ2xj9siN-q9nt4AcK_5YJw-Js8"));
		for (MobileUtilPojo mup : mobilesPojoList) {
			try {
				if (i++ > 100)
					flag = false;
				System.out.println(i);
				mobile = new Mobile();
				mobile.setTopHeadlineFeatures(Arrays.asList(mup.getTopHeadlineFeatures().split("~")));
				mobile.setName(mup.getName());
				mobile.setBrand(mup.getBrand());
				mobile.setPhoneType(flag == true ? "smartphone" : "fature");
				mobile.setBestBuyPrice(Double.parseDouble(mup.getBestBuyPrice().replace(",", "")));
				mobile.setColors(Arrays.asList(mup.getColors().split("~")));
				mobile.setProcessor(mup.getProcessor());
				mobile.setReleasedOS(mup.getReleasedOS());
				String addFtr[] = mup.getAdditionalFeatures().split("%");
				List<AdditionalFeatures> afl = new ArrayList<AdditionalFeatures>();
				for (String str : addFtr) {
					AdditionalFeatures af = new AdditionalFeatures();
					af.setName(str.split(":")[0]);
					af.setValue(str.split(":")[1]);
					afl.add(af);
				}
				mobile.setAdditionalFeatures(afl);
				mobile.setRAM(mup.getRam());
				mobile.getOverView().setReleaseDate(mup.getOverViewReleaseDate());
				mobile.getOverView().setMarketStaus("Released");
				mobile.getOverView().setMarketStaus(mup.getOverViewMarketStaus());
				mobile.getOverView().setPhoneWeight(mup.getOverViewPhoneWeight());
				mobile.getOverView().setReleaseOS(mup.getReleasedOS());
				if (mup.getCameraFrontcameraInMegaPixalWithFeatures() != null) {
					mobile.getCamera().setFrontcameraInMegaPixalWithFeatures(
							Arrays.asList(mup.getCameraFrontcameraInMegaPixalWithFeatures().split("~")));
				}

				if (mup.getCameraRearCameraInMegaPixalWithFeatures() != null) {
					mobile.getCamera().setRearCameraInMegaPixalWithFeatures(
							Arrays.asList(mup.getCameraRearCameraInMegaPixalWithFeatures().split("~")));
				}

				mobile.getDisplay().setDisplayTechnology(mup.getDisplayDisplayTechnology());
				mobile.getDisplay().setScreenMaterial(mup.getDisplayScreenMaterial());
				mobile.getDisplay().setScreenResolution(mup.getDisplayScreenResolution());
				mobile.getDisplay().setScreenSize(mup.getDisplayScreenSize());

				if (mup.getDisplayDtherDisplayFeatures() != null) {
					mobile.getDisplay()
							.setOtherDisplayFeatures(Arrays.asList(mup.getDisplayDtherDisplayFeatures().split("~")));
				}

				mobile.getBattery().setTalkTime(mup.getBatteryTalkTime());
				mobile.getBattery().setBatteryCapacity(mup.getBatteryBatteryCapacity());
				mobile.getBattery().setBatteryTechnology(mup.getBatteryBatteryTechnology());

				mobile.getStorage().setExpendableStorageFeatures(Arrays.asList(mup.getExpendableStorageFeatures()));
				mobile.getStorage().setInternalStorage(mup.getExpendableStorageFeatures());

				mobile.getConnectivityAndFeatures().setWireLessConnectivityFeatures(
						Arrays.asList(mup.getConnectivityAndFeaturesWireLessConnectivityFeatures()));
				mobile.getConnectivityAndFeatures().setOthersConnectivityFeatures(
						Arrays.asList(mup.getConnectivityAndFeaturesothersConnectivityFeatures().split("~")));
				mobile.getConnectivityAndFeatures()
						.setNetworkFeatures(Arrays.asList(mup.getConnectivityAndFeaturesNetworkFeatures()));

				// *****************uploading image
				// cloudinary********************
				File folder = new File("C:/Users/grkuma/Desktop/CrawledImage/" + mup.getName());
				List<String> restimageurls = new ArrayList<String>();
				HashMap<File, Integer> fileNameWithsize = new HashMap<File, Integer>();
				for (File file : folder.listFiles()) {
					if (file.getAbsolutePath().endsWith("jpeg") || file.getAbsolutePath().endsWith("jpg")) {
						fileNameWithsize.put(file, IOUtils.toByteArray(new FileInputStream(file)).length);
					}
				}
				int maxFileSize = Collections.max(fileNameWithsize.values());
				File filePathWithMaxSixe = null;
				for (Map.Entry<File, Integer> mp : fileNameWithsize.entrySet()) {
					if (mp.getValue().equals(maxFileSize)) {
						filePathWithMaxSixe = mp.getKey();
						Map<String, String> cloudinaryMap = cloudinary.uploader().upload(filePathWithMaxSixe, null);
						mobile.setMainImageUrl(cloudinaryMap.get("url"));

					} else {
						Map<String, String> cloudinaryMap = cloudinary.uploader().upload(mp.getKey(), null);
						restimageurls.add(cloudinaryMap.get("url"));
					}
				}
				mobile.setRestImageUrl(restimageurls);
				productUtil.postProcessForm(mobile, "mobile", new ModelMap());

			} catch (Exception e) {
				System.out.println("*********************************");
				System.out.println(e);
				e.printStackTrace();
			}
		}
		List<Object> list = productService.getAllProduct("mobile");
		for (Object obj : list) {
			System.out.println((Mobile) obj);
		}

	}

	/*
	 * @Override public void afterPropertiesSet() throws Exception { // TODO
	 * Auto-generated method stub
	 * 
	 * }
	 */

}
