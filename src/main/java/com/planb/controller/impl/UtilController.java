package com.planb.controller.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.planb.dao.mobile.Mobile;
import com.planb.dao.review.ReviewDetails;
import com.planb.inmemoery.service.ElectInMemoryFactory;
import com.planb.inmemoery.service.ElectInMemoryService;
import com.planb.inmemoery.service.ProductEnum;
import com.planb.metadata.CbdToRdbToInMem;
import com.planb.metadata.ProductMetaData;
import com.planb.metadata.ReviewMetaDataRDBService;
import com.planb.product.util.EUtil;
import com.planb.search.dao.SearchProductDao;
import com.planb.search.service.SearchService;
import com.planb.service.ProductService;
import com.planb.service.ReviewService;

@Controller
public class UtilController {

	@Autowired
	CbdToRdbToInMem cbdToRdbToInMem;

	@Autowired
	ProductService productService;

	@Autowired
	ElectInMemoryFactory electInMemoryFactory;

	@Autowired
	EUtil productUtil;

	@Autowired
	ReviewMetaDataRDBService reviewMetaDataRDBService;

	@Autowired
	ReviewService reviewService;

	@Autowired
	SearchService searchService;

	@Value("${front.ui.listing.order}")
	String frontUiListingOrder;

	@Value("${trending.ui.listing.order}")
	String trendingUiListingOrder;

	@Value("${base.url}")
	String baseUrl;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String frontMainView(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		LinkedHashMap<String, ArrayList<ProductMetaData>> frontMetaDataMap = cbdToRdbToInMem.getFrontMetaDataMap();
		model.put("frontMetaDataMap", frontMetaDataMap);
		model.put("frontUiListingOrder", frontUiListingOrder);
		model.put("frontUIHeaderUrlLookup", productUtil.getFrontUIHeaderUrlLookup());
		//replace with actual url without adding www.
		model.put("url", "localhost");
	      response.setHeader("Cache-Control", "no-cache, no-store,must-revalidate"); // HTTP 1.1.
		 response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		//response.setHeader("max-age", "1209600");
		return "frontMainView";
	}

	@RequestMapping(value = "/trending-gadgets", method = RequestMethod.GET)
	public String trendingProducts(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		LinkedHashMap<String, ArrayList<ProductMetaData>> trendingMap = cbdToRdbToInMem.getTrendingMap();
		modelMap.put("trendingMap", trendingMap);
		modelMap.put("trendingUiListingOrder", trendingUiListingOrder);
		return "trending";

	}

	@RequestMapping(value = "/latest/{category}", method = RequestMethod.GET)
	public String latestProduct(@PathVariable("category") String category, HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) {

		try {
			if (category != null || !"".equalsIgnoreCase(category)) {

				if (category.equalsIgnoreCase("smartphones"))
					category = "mobiles";
				ElectInMemoryService electInMemoryService = electInMemoryFactory.getElectInMemoryService(category);
				List<ProductMetaData> latestProductList = electInMemoryService.fetchLatestProductList();
				modelMap.put("latestProductList", latestProductList);
				modelMap.put("categoryHeader", "Latest " + category);
				modelMap.put("category", category);
				return "latestProduct";
			} else {
				return "404NotFound";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:" + baseUrl;
		}

	}

	@RequestMapping(value = "/latest/{category}/loadMore/{startFrom}", method = RequestMethod.GET)
	public String LoadMoreLatestProduct(@PathVariable("category") String category,
			@PathVariable("startFrom") int startFrom, HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap) {
		if (category != null || !"".equalsIgnoreCase(category)) {

			if (category.equalsIgnoreCase("smartphone"))
				category = "mobile";

			ElectInMemoryService electInMemoryService = electInMemoryFactory.getElectInMemoryService(category);
			List<ProductMetaData> latestProductList = electInMemoryService.fetchLoadMoreProductList(startFrom);
			/**
			 * Below code is written for fixing the size of latestProductList in multiple of 4 so
			 * that UI should not be distorted  
			 */
			int productCount = latestProductList.size();
			if (productCount < 16) {
				int reminder = productCount % 4;
				if (reminder != 0) {
					int j = 0;
					for (int i = reminder; i < 4; i++) {
						latestProductList.add(latestProductList.get(j++));
					}
				}

			}
			modelMap.put("latestProductList", latestProductList);
			modelMap.put("category", category);
			return "loadMoreLatestProduct";
		} else {
			return "redirect:" + baseUrl;
		}

	}

	@RequestMapping(value = "/submitReview", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody String submitReview(@RequestBody ReviewDetails reviewDetails, HttpServletRequest request,
			HttpServletResponse response) {
		String userEmail = (String) request.getSession().getAttribute("userEmail");
		reviewDetails.setByUserId(userEmail);
		// async method
		reviewService.submitReview(reviewDetails);
		return "success";
	}

	@RequestMapping(value = "/autosuggest/{query}/{category}", method = RequestMethod.GET)
	public @ResponseBody List<SearchProductDao> fetchAutoSuggestedProduct(@PathVariable("query") String query,
			@PathVariable("category") String category) {
		return searchService.getAutoSuggestedProducts(query, category, 10);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String fetchSearchedProduct(@RequestParam(value = "category", required = true) String category,
			@RequestParam(value = "q", required = true) String query,
			@RequestParam(value = "pageCount", required = false) String startFromcount, ModelMap model) {

		if (startFromcount == null)
			startFromcount = "1";
		List<SearchProductDao> searchResultList = null;

		int startFrom = Integer.parseInt(startFromcount);
		List<SearchProductDao> SRD = searchService.getAutoSuggestedProducts(query, category, -1);
		if (SRD != null) {
			int maxListSize = SRD.size();
			model.put("totalSerRes", maxListSize);
			int fromIndex = (startFrom - 1) * 10;
			int toIndex = fromIndex + 10;
			if (fromIndex < maxListSize && toIndex <= maxListSize) {
				searchResultList = new ArrayList<SearchProductDao>(SRD.subList(fromIndex, toIndex));
			} else if (fromIndex < maxListSize && toIndex > maxListSize) {
				searchResultList = new ArrayList<SearchProductDao>(SRD.subList(fromIndex, maxListSize));
			}
			model.put("brand", new ArrayList<String>(
					searchResultList.stream().map(spd -> spd.getBrand()).collect(Collectors.toSet())));
			model.put("caterory", new ArrayList<String>(
					searchResultList.stream().map(spd -> spd.getCategory().toString()).collect(Collectors.toSet())));

		}
		model.put("query", query);
		model.put("searchCategory", category);
		model.put("searchResultList", searchResultList);
		model.put("searchPageCount", startFrom);

		return "searchResult";
	}

	@RequestMapping(value = "/search/filter", method = RequestMethod.GET)
	public String fetchFilteredSearchedProduct(@RequestParam(value = "category", required = true) String category,
			@RequestParam(value = "brand", required = true) String brand,
			@RequestParam(value = "q", required = true) String query,
			@RequestParam(value = "pageCount", required = false) String startFromcount, ModelMap model) {

		if (startFromcount == null)
			startFromcount = "1";
		List<SearchProductDao> searchResultList = null;

		int startFrom = Integer.parseInt(startFromcount);
		List<SearchProductDao> SRD = searchService.getAutoSuggestedProducts(query, category, -1);

		if (!StringUtils.isEmpty(brand) && SRD != null) {
			Set<String> BrandSet = new HashSet<String>(Arrays.asList(brand.split(",")));
			SRD = SRD.stream().filter(spd -> BrandSet.contains(spd.getBrand())).collect(Collectors.toList());
		}

		if (!StringUtils.isEmpty(category) && SRD != null) {
			Set<String> categorySet = new HashSet<String>(Arrays.asList(category.split(",")));
			SRD = SRD.stream().filter(spd -> categorySet.contains(spd.getCategory().toString()))
					.collect(Collectors.toList());
		}

		if (SRD != null) {
			int maxListSize = SRD.size();
			model.put("totalSerRes", maxListSize);
			int fromIndex = (startFrom - 1) * 10;
			int toIndex = fromIndex + 10;
			if (fromIndex < maxListSize && toIndex <= maxListSize) {
				searchResultList = new ArrayList<SearchProductDao>(SRD.subList(fromIndex, toIndex));
			} else if (fromIndex < maxListSize && toIndex > maxListSize) {
				searchResultList = new ArrayList<SearchProductDao>(SRD.subList(fromIndex, maxListSize));
			}
		}
		model.put("query", query);
		model.put("searchPageCount", startFrom);
		model.put("searchResultList", searchResultList);

		return "filteredSearchResult";

	}

	@RequestMapping(value = "search/details/{productId}", method = RequestMethod.GET)
	public String productDetailsView(@PathVariable("productId") String productId,
			@RequestParam("avgRating") String avgRating, HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		Mobile mobileDetail = (Mobile) productService.getProduct(productId, "mobile");
		// fetch only top rated review

		List<String> reviewIds = reviewMetaDataRDBService.getTopRatedReviewId(productId);
		List<String> nonUniqReviewIds = reviewIds.stream().map(str -> str.substring(0, str.length() - 10))
				.collect(Collectors.toList());

		if (nonUniqReviewIds != null && !nonUniqReviewIds.isEmpty()) {
			model.put("allReview", productService.multiGet(nonUniqReviewIds, ProductEnum.REVIEW.getValue()));
		}
		model.put("productDetail", mobileDetail);
		model.put("avgRating", avgRating);
		return "searchDetailsView";
	}

	/*
	 * render only json string
	 * 
	 * fetch latest review id form rdb order by created date latest,by using ids
	 * will fetch latest review pojo and will return these pojo after converting
	 * in json(will done by spring wn message converter)
	 */
	@RequestMapping(value = "/JSON/latest/{productId}/review/{startFrom}", method = RequestMethod.GET)
	public @ResponseBody List<ReviewDetails> LoadMoreLatestReviewJSON(@PathVariable("productId") String productId,
			@PathVariable("startFrom") int startFrom) {

		List<String> reviewIds = reviewMetaDataRDBService.getReviewIdForPagination(productId, startFrom);
		// tempory removal of suffix from reviewId
		List<String> nonUniqReviewIds = reviewIds.stream().map(str -> str.substring(0, str.length() - 10))
				.collect(Collectors.toList());

		List<Object> objectDetailList = productService.multiGet(nonUniqReviewIds, ProductEnum.REVIEW.getValue());
		List<ReviewDetails> reviewDetailsList = objectDetailList.stream().map(obj -> (ReviewDetails) obj)
				.collect(Collectors.toList());
		return reviewDetailsList;

	}

	/*
	 * 
	 * fetch latest review id form rdb order by created date latest,by using ids
	 * will fetch latest review pojo and will return these pojo after converting
	 * in json(will done by spring wn message converter)
	 */
	@RequestMapping(value = "/latest/{productId}/review/{startFrom}", method = RequestMethod.GET)
	public String LoadMoreLatestReview(@PathVariable("productId") String productId,
			@PathVariable("startFrom") int startFrom, HttpServletRequest request, HttpServletResponse response,
			ModelMap map) {

		List<String> reviewIds = reviewMetaDataRDBService.getReviewIdForPagination(productId, startFrom);
		/*// tempory removal of suffix from reviewId
		List<String> nonUniqReviewIds = reviewIds.stream().map(str -> str.substring(0, str.length() - 10))
				.collect(Collectors.toList());*/

		List<Object> objectDetailList = productService.multiGet(reviewIds, ProductEnum.REVIEW.getValue());
		if(objectDetailList!= null){
		List<ReviewDetails> reviewDetailsList = objectDetailList.stream().map(obj -> (ReviewDetails) obj)
				.collect(Collectors.toList());
		map.put("reviewDetailsList", reviewDetailsList);
		}
		return "loadMoreLatestReview";

	}

}
