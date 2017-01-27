package com.planb.controller.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.planb.dao.review.ReviewDetails;
import com.planb.dao.user.UpdatedProfile;
import com.planb.dao.user.UserDetail;
import com.planb.dao.user.UserFavProduct;
import com.planb.dao.user.UserLoginRegsForm;
import com.planb.inmemoery.service.ProductEnum;
import com.planb.mail.MailUtil;
import com.planb.product.util.EUtil;
import com.planb.service.ProductService;
import com.planb.service.ReviewService;

@Controller
public class UserController {

	@Autowired
	ProductService productService;

	@Autowired
	MailUtil mailUtil;

	@Autowired
	EUtil eUtil;

	@Autowired
	ApplicationContext appContext;

	@Autowired
	ReviewService reviewService;

	@RequestMapping(value = "/accountLogin", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody UserLoginRegsForm userLogin(@RequestBody UserLoginRegsForm userLoginRegsForm,
			HttpServletRequest request, HttpServletResponse response) {
		// blank,digit check,email regex, all should be checked at the client
		// side js
		if (userLoginRegsForm != null) {
			String str = (String) request.getSession().getAttribute("userName");
			String email = userLoginRegsForm.getEmail();
			String password = userLoginRegsForm.getPassword();
			List<String> masterUserId_emailList = productService.getAllMasterKeyId("user");
			if (masterUserId_emailList != null && masterUserId_emailList.contains(email)) {
				UserDetail userDetail = (UserDetail) productService.getProduct(email, "user");
				if (userDetail.getPassword().equals(password)) {
					if (!userDetail.isVerified()) {
						// Add a link in UI for resend verification link
						// for testing purpose
						userLoginRegsForm.setErrorMsg("success");
						String userName = userDetail.getName();
						String userEmail = userDetail.getEmail();
						request.getSession().setAttribute("userName", userName);
						request.getSession().setAttribute("userEmail", userEmail);
						// userLoginRegsForm.setErrorMsg("email not
						// verified!!!");
					} else {
						userLoginRegsForm.setErrorMsg("success");
						// encript password and accessKey
						request.getSession().setAttribute("userName", userDetail.getName());
						request.getSession().setAttribute("userEmail", userDetail.getEmail());
					}

				} else {
					userLoginRegsForm.setErrorMsg("email or password is wrong!!!");
				}

			} else {
				userLoginRegsForm.setErrorMsg("Opps!!!user not registerd");

			}

		}

		return userLoginRegsForm;
	}

	@RequestMapping(value = "/accountRegister", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody UserLoginRegsForm userRegistration(@RequestBody UserLoginRegsForm userLoginRegsForm,
			HttpServletRequest request, HttpServletResponse response) {
		// blank,digit check,email regex,password retype check all should be
		// checked at the client
		// side js
		String email = userLoginRegsForm.getEmail();
		List<String> masterUserId_emailList = productService.getAllMasterKeyId("user");
		if (masterUserId_emailList != null && masterUserId_emailList.contains(email)) {
			userLoginRegsForm.setErrorMsg("email already taken!!!");
		} else {
			String uuid = UUID.randomUUID().toString();
			// sent a mail for activate account at given mail
			// mailUtil.sendMail(email, uuid);
			UserDetail userDetail = (UserDetail) appContext.getBean("userDetail");
			userDetail.setAccessKey(uuid);
			userDetail.setEmail(email);
			userDetail.setName(userLoginRegsForm.getName());
			userDetail.setPassword(userLoginRegsForm.getPassword());
			productService.addProduct(userDetail, "user", email);
			userLoginRegsForm.setErrorMsg("success");
		}

		return userLoginRegsForm;

	}

	@RequestMapping(value = "/activateAccount", method = RequestMethod.GET)
	public String activateAccount(@RequestParam("email") String email, @RequestParam("accesskey") String accesskey,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		if (!StringUtils.isEmpty(email) && !StringUtils.isEmpty(accesskey)) {

			Object obj = productService.getProduct(email, "user");
			if (obj != null) {
				UserDetail userDetail = (UserDetail) obj;
				if (userDetail.getAccessKey().equals(accesskey)) {
					userDetail.setVerified(true);
					productService.addProduct(userDetail, "user", email);
					// encript password and accessKey
					request.getSession().setAttribute("userName", userDetail.getName());
					request.getSession().setAttribute("userEmail", userDetail.getEmail());
					return "redirect:frontMainView";

				} else {
					return "redirect:frontMainView";
				}
			} else {

				// userDetail not found in couchbaseDB==>send massage to user
				// for reRegistrtion
				request.getSession().setAttribute("activationMsg", "someting went wrong.Please Re-Register!!!");
				return "redirect:frontMainView";
			}
		} else {
			request.getSession().setAttribute("activationMsg", "someting went wrong.Please Re-Register!!!");
			return "redirect:frontMainView";
		}
	}

	@RequestMapping(value = "/resendActivationLink", method = RequestMethod.GET)
	public @ResponseBody String resendActivationLink(@RequestParam("resendEmail") String resendEmail,
			HttpServletRequest request, HttpServletResponse response) {

		Object obj = productService.getProduct(resendEmail, "user");
		if (obj != null) {
			UserDetail userDetail = (UserDetail) obj;
			String accessKey = userDetail.getAccessKey();
			// send activation email
			// mailUtil.sendMail(email, accessKey);
			return "success";
		} else {
			return "error";
		}

	}

	@RequestMapping(value = "/recoverPassword", method = RequestMethod.GET)
	public @ResponseBody String recoverPassword(@RequestParam("recoverPasswordEmail") String recoverPassword,
			HttpServletRequest request, HttpServletResponse response) {

		Object obj = productService.getProduct(recoverPassword, "user");
		if (obj != null) {
			UserDetail userDetail = (UserDetail) obj;
			String passowd = userDetail.getPassword();
			// send email with password
			// mailUtil.sendMail(email, accessKey);
			return "success";
		} else {
			return "error";
		}

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public @ResponseBody String frontMainView(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("userName");
		request.getSession().removeAttribute("userEmail");
		request.getSession().invalidate();
		return "success";
	}

	@RequestMapping(value = "/favProduct/{category}/{flag}/{productId}", method = RequestMethod.GET)
	public @ResponseBody String favProduct(@PathVariable("category") String category, @PathVariable("flag") int flag,
			@PathVariable("productId") String productId, HttpServletRequest request, HttpServletResponse response) {
		String userEmail = (String) request.getSession().getAttribute("userEmail");
		String responseMessage = "";

		if (userEmail != null) {
			UserDetail userDetail = (UserDetail) productService.getProduct(userEmail, ProductEnum.USER.getValue());
			List<UserFavProduct> favProducts = userDetail.getFavProducts();
			if (favProducts == null) {
				favProducts = new ArrayList<UserFavProduct>();
				userDetail.setFavProducts(favProducts);
			}
			Map<String,UserFavProduct> productIdUserFavProductMap=favProducts.stream().collect(Collectors.toMap(UserFavProduct::getProductId,Function.identity()));
			if (flag == 1) {
				
				
				if (productIdUserFavProductMap.containsKey(productId)) {
					responseMessage = productId + " already present in your fav list";
				} else {
					UserFavProduct userFavProduct=eUtil.populateUserFavMetaData(productId, category);
					favProducts.add(userFavProduct);
					//add user in product fav list
					eUtil.addUserinProductFavList(productId, category,userEmail);
					
					responseMessage = productId + " added in your fav list";
				}
			} else {
				if (productIdUserFavProductMap.containsKey(productId)) {
					UserFavProduct temp=new UserFavProduct();
					temp.setProductId(productId);
					favProducts.remove(temp);
					responseMessage = productId + " removed from your fav list";
					//remove user from product fav list
					eUtil.removeUserFromProductFavList(productId, category,userEmail);
					
				} else {
					responseMessage = productId + " not present in your fav list";
				}
			}
			productService.addProduct(userDetail, ProductEnum.USER.getValue(), userEmail);
		}
		return responseMessage;

	}

	/*
	 * *************************************************************************
	 * *************************** ***************UserProfile Details
	 * API************************
	 * 
	 */

	@RequestMapping(value = "/myprofile", method = RequestMethod.GET)
	public String viewProfile(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		String userEmail = (String) request.getSession().getAttribute("userEmail");
		if (userEmail != null) {
			UserDetail userDetail = (UserDetail) productService.getProduct(userEmail, "user");
			map.put("userDetail", userDetail);
		}
		return "myprofile";
	}

	@RequestMapping(value = "/myfavs", method = RequestMethod.GET)
	public String viewFavs(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		String isFavAvailable = "true";
		String userEmail = (String) request.getSession().getAttribute("userEmail");
		if (userEmail != null) {
			UserDetail userDetail = (UserDetail) productService.getProduct(userEmail, "user");
			List<UserFavProduct> userFavProductList = userDetail.getFavProducts();
			if (!CollectionUtils.isEmpty(userFavProductList)) {
				map.put("userFavProductList", userFavProductList);
			} else {
				isFavAvailable = "false";
			}
		} else {
			isFavAvailable = "false";
		}
		map.put("isFavAvailable", isFavAvailable);
		return "myfavs";
	}

	@RequestMapping(value = "/myreviews", method = RequestMethod.GET)
	public String viewReviews(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		String userEmail = (String) request.getSession().getAttribute("userEmail");
		reviewService.reviewPopulator(map, userEmail);
		return "myreviews";
	}

	@RequestMapping(value = "/myratings", method = RequestMethod.GET)
	public String viewRatings(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		String userEmail = (String) request.getSession().getAttribute("userEmail");
		reviewService.reviewPopulator(map, userEmail);
		map.put("ratingTitleMap", eUtil.getRatingTitleMap());
		return "myratings";
	}

	@RequestMapping(value = "/updateRating/{reviewId}/{productId}/{updatedRating}", method = RequestMethod.GET)
	public @ResponseBody void updateRatings(@PathVariable("reviewId") String reviewId,
			@PathVariable("productId") String producId, @PathVariable("updatedRating") double updatedRating,
			HttpServletRequest request, HttpServletResponse response) {
		String userEmail = (String) request.getSession().getAttribute("userEmail");
		if (userEmail != null) {
			reviewService.updateRating(reviewId, producId, updatedRating, userEmail);
		}
	}

	@RequestMapping(value = "/changePassword/{oldPassword}/{newPassword}", method = RequestMethod.GET)
	public @ResponseBody String changePassword(@PathVariable("oldPassword") String oldPassword,
			@PathVariable("newPassword") String newPassword, HttpServletRequest request, HttpServletResponse response) {
		String userEmail = (String) request.getSession().getAttribute("userEmail");
		if (userEmail != null) {
			UserDetail userDetail = (UserDetail) productService.getProduct(userEmail, "user");
			if (userDetail.getPassword() != null && userDetail.getPassword().equals(oldPassword)) {
				userDetail.setPassword(newPassword);
				productService.addProduct(userDetail, "user", userEmail);
				return "success";
			} else {
				return "incorrect email or password";
			}
		} else {
			return "Session expired!!!Please login.";
		}
	}

	@RequestMapping(value = "/accountUpdate", method = RequestMethod.POST, headers = "accept=application/json")
	public @ResponseBody String accountUpdate(@RequestBody UpdatedProfile updatedProfile, HttpServletRequest request,
			HttpServletResponse response) {
		String msg = "";
		String userEmail = (String) request.getSession().getAttribute("userEmail");
		if (userEmail != null) {
			UserDetail userDetail = (UserDetail) productService.getProduct(userEmail, "user");
			if (!userDetail.getEmail().equals(updatedProfile.getUpdatedEmail())) {
				userDetail.setEmail(updatedProfile.getUpdatedEmail());
				userDetail.setVerified(false);
				userEmail = updatedProfile.getUpdatedEmail();
				request.getSession().setAttribute("userEmail", userEmail);
				// send verificatioin link to updated email
				msg = "A Mail have been sent to your registered email.Please click on link inside mail to activate your account.";
			} else {
				msg = "your profile have been updated successfully.";
			}
			userDetail.setName(updatedProfile.getUpdatedName());

			userDetail.setDOB(updatedProfile.getUpdatedDOB());
			// userDetail.setGender(GenderEnum.getGenderEnum(updatedProfile.getUpdatedGender()));
			productService.addProduct(userDetail, "user", userEmail);

		} else {
			return "Session expired!!!Please login.";
		}

		return msg;
	}

	@RequestMapping(value = "/updateReview", method = RequestMethod.POST, headers = "accept=application/json")
	public @ResponseBody String updateReview(@RequestBody ReviewDetails updatedReviewDetails,
			HttpServletRequest request, HttpServletResponse response) {
		String userEmail = (String) request.getSession().getAttribute("userEmail");
		if (!StringUtils.isEmpty(userEmail)) {
			String reviewId = updatedReviewDetails.getReviewId();
			if (!StringUtils.isEmpty(reviewId)) {
				ReviewDetails reviewDetails = (ReviewDetails) productService.getProduct(reviewId, "review");
				if (reviewDetails != null) {
					reviewDetails.setReviewPros(updatedReviewDetails.getReviewPros());
					reviewDetails.setReviewCons(updatedReviewDetails.getReviewCons());
					reviewDetails.setReviewHeadline(updatedReviewDetails.getReviewHeadline());
					reviewDetails.setRating(updatedReviewDetails.getRating());
					reviewDetails.setModifiedOn(eUtil.getCurrentDateAndTime());
					productService.addProduct(reviewDetails, ProductEnum.REVIEW.getValue(), reviewId);
				}
				return "Review updated successfully.";
			}
			return "Something went wrong,Please try again!!!";
		} else {
			return "Session expired!!!Please login.";
		}
	}

}
