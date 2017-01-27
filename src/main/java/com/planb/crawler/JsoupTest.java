package com.planb.crawler;

import java.beans.PropertyDescriptor;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.planb.csvutil.MobileUtil;
import com.planb.csvutil.MobileUtilPojo;
import com.planb.csvutil.ReviewUtilPojo;


public class JsoupTest {

	private static String parentUrl="http://www.91mobiles.com/";
	
	public static void main(String[] args) throws Exception {
		
		/*Response response = Jsoup.connect("http://www.91mobiles.com").execute();*/

		List<MobileUtilPojo> mobileList=new ArrayList<MobileUtilPojo>();
		List<ReviewUtilPojo> reviewList=new ArrayList<ReviewUtilPojo>();
		List<String> failedUrl=new ArrayList<String>();
		
		ExecutorService exe=Executors.newFixedThreadPool(5);
		
		Document doc = Jsoup.connect("http://www.91mobiles.com/sitemap.php").userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
			    .followRedirects(false).get();
		Element link = doc.select("div.box_container").get(3);
		Elements mobiles=link.getElementsByTag("a");
		//#start {for 100 items }
	for(Element e:mobiles){	
		
		
	Runnable run=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String url = null;
		
		try{
			MobileUtilPojo mobileUtilPojo=new MobileUtilPojo();
		 url=e.attr("href");
		String productName=e.text();
		Document specificationDoc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
			    .followRedirects(false).get();
		
		         //start crawling image
		            Elements img = specificationDoc.getElementsByTag("img");
		            for (Element el : img) {
		                //for each element get the srs url
		                String src = el.absUrl("src");
		                System.out.println("Image Found!");
		                System.out.println("src attribute is : "+src);
		                try{
		                getImages(src,productName);
		                }catch(Exception e1){
		                	
		                }
		 
		            }

		
		
		     //end crawling images
		
		
	      	//#start   crawling reviews
				Element review=specificationDoc.select("div.new_launchtxt").first().nextElementSibling();
		
				Document reviewDoc = Jsoup.connect(parentUrl+"/"+review.attr("data-href-url")).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
						.followRedirects(false).get();
				String oveallRating="";
				try{
		         oveallRating=reviewDoc.select("span.ovrRthd").first().nextElementSibling().text();
				}catch(Exception e1){
					System.out.println(e.toString());
				}
		        
		        Elements reviews=reviewDoc.select("div.review_section");
		        ReviewUtilPojo reviewUtilPojo; 
		       /* for(Element reviewItem:reviews){
		        	try{
		        	String rating=reviewItem.getElementsByClass("rating-stars_rw").first().attr("title");
		        	String reviewHeadline=reviewItem.getElementsByClass("rw-heading").first().text();
		        	String reviewContent=reviewItem.getElementsByClass("rw-area").first().text();
		        	String reviewDate=reviewItem.select("span.user_info").first().text();
		        	reviewUtilPojo=new ReviewUtilPojo(productName, rating, reviewHeadline, reviewContent, reviewDate);
		        	reviewList.add(reviewUtilPojo);
		        	}catch(Exception rev){
		        		System.out.println(rev.getMessage());
		        	}
		        	
		        }*/
		    //#end   crawling reviews
		
		 /*String lowestPrice= specificationDoc.select("span.price_color_black > span").first().nextElementSibling().text();   
	     //*******************setting price********************
		 mobileUtilPojo.setBestBuyPrice(lowestPrice);
		 
		 Elements topHeadlilneFeatures  =    specificationDoc.select("li.li_class");
		  Map<String,String> headlineFeatureMap=new HashMap<String,String>();
		  for(Element topHeadlilneFeature:topHeadlilneFeatures){
			  String header=topHeadlilneFeature.getElementsByClass("expand").first().text().split(" ")[0];
			  Elements labels=topHeadlilneFeature.getElementsByTag("label");
			  StringBuffer sb=new StringBuffer();
			  for(Element label:labels){
				  sb.append(label.text()).append("|");
			  }
			  headlineFeatureMap.put(header, sb.toString().substring(0, sb.toString().length()-1));
		  }
		  StringBuffer headlineFeature=new StringBuffer();
		  for(Map.Entry<String, String> mp:headlineFeatureMap.entrySet()){
			  headlineFeature.append(mp.getKey()).append(":").append(mp.getValue()).append("~");
		  }
		  //************setting topHeadline feature****************
		  mobileUtilPojo.setTopHeadlineFeatures(headlineFeature.toString().substring(0,headlineFeature.toString().length()-1));
		  
		 // Element techSpecification=specificationDoc.select("div.specs_table_wrap").first();
		 // Elements subHeader=techSpecification.select("div.sub_heading");
		  Elements sub_heading=specificationDoc.select("div.sub_heading");
		  PropertyDescriptor pd[]=PropertyUtils.getPropertyDescriptors(mobileUtilPojo);
          System.out.println(pd.toString());
		  for(Element subheader:sub_heading){
			  String header=subheader.getElementsByTag("p").first().text();
			  System.out.println("*********"+header+"**************");
			  Element element=subheader.nextElementSibling();
			  Elements specifications=element.select("th.scnd");
			  switch(header.toLowerCase()){
			  
			  case "general":
				 
				  for(Element spec:specifications){
					  String key=spec.text();
					  String value=spec.nextElementSibling().nextElementSibling().text();
					  if(key.equalsIgnoreCase("Launch Date")){
						  mobileUtilPojo.setOverViewReleaseDate(value);
						  
					  }else if(key.equalsIgnoreCase("Brand")){
						  mobileUtilPojo.setBrand(value);
					  }else if(key.equalsIgnoreCase("Model")){
						  mobileUtilPojo.setName(mobileUtilPojo.getBrand()+" "+value);
					  }else if(key.equalsIgnoreCase("Operating System")){
						  mobileUtilPojo.setReleasedOS(value);
					  }else if(key.equalsIgnoreCase("SIM Slot(s)")){
						  mobileUtilPojo.setConnectivityAndFeaturesNetworkFeatures(value);
					  }else if(key.equalsIgnoreCase("Network")){
						  mobileUtilPojo.setConnectivityAndFeaturesWireLessConnectivityFeatures(value);  
					  }else {
						  String temp="";
						  if(mobileUtilPojo.getConnectivityAndFeaturesothersConnectivityFeatures()!=null){
							  temp=mobileUtilPojo.getConnectivityAndFeaturesothersConnectivityFeatures();
						  mobileUtilPojo.setConnectivityAndFeaturesothersConnectivityFeatures(temp+"~"+key+":"+value);
						  }else{
							  mobileUtilPojo.setConnectivityAndFeaturesothersConnectivityFeatures(key+":"+value); 
						  }
					  }
					  
					  
				  }
				 
				  break;
			  case "design":
				  for(Element spec:specifications){
					  String key=spec.text();
					  String value=spec.nextElementSibling().nextElementSibling().text();
					  if(key.equalsIgnoreCase("Dimensions")){
						  
						 mobileUtilPojo.setOverViewDimension(value);
						  
					  }else if(key.equalsIgnoreCase("Weight")){
						  mobileUtilPojo.setOverViewPhoneWeight(value);
					  }else if(key.equalsIgnoreCase("Colours")){
						 Elements colorElements=spec.nextElementSibling().nextElementSibling().getElementsByTag("a");
						 StringBuffer colourString=new StringBuffer();
						 for(Element color:colorElements){
							 colourString.append(color.attr("title")).append("~");
						 }
						  mobileUtilPojo.setColors(colourString.toString().substring(0,colourString.toString().length()-1));
					  
					  }
				  }
				  
				  break;
			  case "":	 
				  
				  
				  
				  break;
			  case "display":	
				  for(Element spec:specifications){
					  String key=spec.text();
					  String value=spec.nextElementSibling().nextElementSibling().text();
					  if(key.equalsIgnoreCase("Screen Size")){
						mobileUtilPojo.setDisplayScreenSize(value);  
						  
					  }else if(key.equalsIgnoreCase("Screen Resolution")){
						  mobileUtilPojo.setDisplayScreenResolution(value);
					  }else if(key.equalsIgnoreCase("Display Type")){
						  mobileUtilPojo.setDisplayScreenMaterial(value);
					  
					  }else{
						  String temp;
						  if(mobileUtilPojo.getDisplayDtherDisplayFeatures()!=null){
							  temp=mobileUtilPojo.getConnectivityAndFeaturesothersConnectivityFeatures();
						  mobileUtilPojo.setDisplayDtherDisplayFeatures(temp+"~"+key+":"+value);
						  }else{
							  mobileUtilPojo.setDisplayDtherDisplayFeatures(key+":"+value); 
						  }
					  }
				  }
				  
				  break;
			  case "performance":	 
				  for(Element spec:specifications){
					  String key=spec.text();
					  String value=spec.nextElementSibling().nextElementSibling().text();
					  if(key.equalsIgnoreCase("Chipset")){
						  
						  String temp;
						  if(mobileUtilPojo.getAccessories()!=null){
							  temp=mobileUtilPojo.getAccessories();
						  mobileUtilPojo.setAccessories(temp+"~"+key+":"+value);
						  }else{
							  mobileUtilPojo.setAccessories(key+":"+value); 
						  }
						  
					  }else if(key.equalsIgnoreCase("Processor")){
						  mobileUtilPojo.setProcessor(value);
					 
					  }else if(key.equalsIgnoreCase("Architecture")){
						  String temp;
						  if(mobileUtilPojo.getAccessories()!=null){
							  temp=mobileUtilPojo.getAccessories();
						  mobileUtilPojo.setAccessories(temp+"~"+key+":"+value);
						  }else{
							  mobileUtilPojo.setAccessories(key+":"+value); 
						  }
					  
					  }else if(key.equalsIgnoreCase("Graphics")){
						  String temp;
						  if(mobileUtilPojo.getAccessories()!=null){
							  temp=mobileUtilPojo.getAccessories();
						  mobileUtilPojo.setAccessories(temp+"~"+key+":"+value);
						  }else{
							  mobileUtilPojo.setAccessories(key+":"+value); 
						  }
					  
					  }else if(key.equalsIgnoreCase("RAM")){
						  mobileUtilPojo.setRam(value);
					  
					  }
				  }
				  break;
			  case "storage":
				  for(Element spec:specifications){
					  String key=spec.text();
					  String value=spec.nextElementSibling().nextElementSibling().text();
					  if(key.equalsIgnoreCase("Internal Memory")){
						  
						  mobileUtilPojo.setInternalStorage(value);
						  
					  }else if(key.equalsIgnoreCase("Expandable Memory")){
						  mobileUtilPojo.setExpendableStorageFeatures(value);
					  }else if(key.equalsIgnoreCase("USB OTG Support")){
						  String temp;
						  if(mobileUtilPojo.getAccessories()!=null){
							  temp=mobileUtilPojo.getAccessories();
						  mobileUtilPojo.setAccessories(temp+"~"+key+":"+value);
						  }else{
							  mobileUtilPojo.setAccessories(key+":"+value); 
						  }
					  
					  }
				  }
				  
				  break;
			  case "camera":
				 Elements cameraTypes= subheader.nextElementSibling().select("div.sub_head");
				 for(Element camera:cameraTypes){
					 String cameraType=camera.getElementsByTag("P").first().text();
					     if(cameraType.equalsIgnoreCase("main camera")){
					    	 Element element1= camera.nextElementSibling();
					    	 Elements specifications1=element1.select("th.scnd");
							  for(Element spec:specifications1){
								  String key=spec.text();
								  String value=spec.nextElementSibling().nextElementSibling().text();
								 String temp;
								if(mobileUtilPojo.getCameraRearCameraInMegaPixalWithFeatures()!=null){
									temp=mobileUtilPojo.getCameraRearCameraInMegaPixalWithFeatures();
									mobileUtilPojo.setCameraRearCameraInMegaPixalWithFeatures(temp+"~"+key+":"+value);
								}else{
									mobileUtilPojo.setCameraRearCameraInMegaPixalWithFeatures(key+":"+value);
								}
								 
							  }
					    	 
					    	 
					     }else{
					    	 Element element1= camera.nextElementSibling();
					    	 Elements specifications1=element1.select("th.scnd");
							  for(Element spec:specifications1){
								  String key=spec.text();
								  String value=spec.nextElementSibling().nextElementSibling().text();
								  String temp;
									if(mobileUtilPojo.getCameraFrontcameraInMegaPixalWithFeatures()!=null){
										temp=mobileUtilPojo.getCameraFrontcameraInMegaPixalWithFeatures();
										mobileUtilPojo.setCameraFrontcameraInMegaPixalWithFeatures(temp+"~"+key+":"+value);
									}else{
										mobileUtilPojo.setCameraFrontcameraInMegaPixalWithFeatures(key+":"+value);
									}
							  }
					     }
					 
					 
					 
					 
					 
				 }
				 
				  break;
			  case "battery":	
				  for(Element spec:specifications){
					  String key=spec.text();
					  String value=spec.nextElementSibling().nextElementSibling().text();
					  if(key.equalsIgnoreCase("Capacity")){
						  
						 mobileUtilPojo.setBatteryBatteryCapacity(value);;
						  
					  }else if(key.equalsIgnoreCase("Type")){
						  mobileUtilPojo.setBatteryBatteryTechnology(value);
					  }else if(key.equalsIgnoreCase("Talktime")){
						  mobileUtilPojo.setBatteryTalkTime(key+":"+value);
					  
					  }else if(key.equalsIgnoreCase("Standby Time")){
						  String temp;
						  if(mobileUtilPojo.getBatteryTalkTime()!=null){
							  temp=mobileUtilPojo.getBatteryTalkTime();
							  mobileUtilPojo.setBatteryTalkTime(temp+key+":"+value);
						  }else{
							  mobileUtilPojo.setBatteryTalkTime(key+":"+value);  
						  }
					  
					  }else{
						  
					  }
				  }
				  break;
			  case "multimedia":
				  for(Element spec:specifications){
					  String key=spec.text();
					  String value=spec.nextElementSibling().nextElementSibling().text();
					  if(key.equalsIgnoreCase("FM Radio")){
						  
						  System.out.println(key+":"+value);
						  
					  }else if(key.equalsIgnoreCase("Loudspeaker")){
						  
					  }else if(key.equalsIgnoreCase("Audio Jack")){
						  
					  
					  }
				  }
				  break;
			 default:	
				 
				 
				 
				 
			  break;
			  
			  
			  }
			  
			  
			  
			  
			  
			  
			  
		  }
		  
      //Fetching additional feature
		  Map<String,String> additioinalFeatures=new HashMap<String,String>();
		  String temp=specificationDoc.select("div.overview_specs_green_box").first().text();
		  String tepArry[]=temp.split(" ");
		  Elements addFeature=specificationDoc.select("div.overview_specs_green_box").first().getElementsByTag("b");
		  for(Element addftr:addFeature){
			  String key=addftr.text();
			  String value=addftr.parent().nextElementSibling().nextElementSibling().text();
			  additioinalFeatures.put(key, value);
		  }
		  
		  System.out.println(additioinalFeatures.toString());
		  
		  StringBuffer additioinalFeaturesSt=new StringBuffer();
		  for(Map.Entry<String, String> mp:additioinalFeatures.entrySet()){
			  additioinalFeaturesSt.append(mp.getKey()).append(":").append(mp.getValue()).append("%");
		  }
		  //************setting Additional feature****************
		  mobileUtilPojo.setAdditionalFeatures(additioinalFeaturesSt.toString().substring(0,additioinalFeaturesSt.toString().length()-1));
		  
		  mobileList.add(mobileUtilPojo);*/
		  
      		  
	}catch(Exception continueException){
		System.out.println(continueException.getMessage());
		failedUrl.add(url);
	}
		  
	}	
	//#end {for 100 items }
	};
	
	
	exe.submit(run);
	System.out.println("submitted to executer");
	}	
	
	exe.shutdown();
	try {
		System.out.println("Before termination");
		exe.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		System.out.println("After termination");
	} catch (InterruptedException e) {
		System.out.println(e);
	}
	
	//MobileUtil mUtil=new MobileUtil();
	//mUtil.writeToCSV("C:/Users/green/Dropbox/planb/review-aggregator-electronics/src/main/java/com/planb/csvutil/MobileUtil.csv", mobileList);
	//ReviewUtil rUtil=new ReviewUtil();
	//rUtil.writeToCSV("C:/Users/green/Dropbox/planb/review-aggregator-electronics/src/main/java/com/planb/csvutil/ReviewUtil.csv", reviewList);
	/*File file=new File("C:/Users/green/Dropbox/planb/review-aggregator-electronics/src/main/java/com/planb/csvutil/failedURL.txt");
	  if(!file.exists())
		  file.createNewFile();
	OutputStream os=new FileOutputStream(file);
	IOUtils.writeLines(failedUrl, null, os);
	*/
	
	}

	private static void getImages(String src,String productName) throws Exception {
		
		String folderPath="C:/Users/grkuma/Desktop/CrawledImage/"+productName;
		new File(folderPath).mkdir();
		        //Exctract the name of the image from the src attribute
		        int indexname = src.lastIndexOf("/");
		 
		        if (indexname == src.length()) {
		            src = src.substring(1, indexname);
		        }
		 
		        indexname = src.lastIndexOf("/");
		        String name = src.substring(indexname, src.length());
		 
		        System.out.println(name);
		 
		        //Open a URL Stream
		        URL url = new URL(src);
		        InputStream in = url.openStream();
		        byte[] imageBytes=IOUtils.toByteArray(in);
		        if(imageBytes.length < 4*1024)
		        	return;
		        
		        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		        OutputStream out = new BufferedOutputStream(new FileOutputStream( folderPath+ name));
		 /*
		        for (int b; (b = in.read()) != -1;) {
		            out.write(b);
		        }*/
		        out.write(imageBytes);
		        out.close();
		        in.close();
		 

		
		
		
		
	}

}
