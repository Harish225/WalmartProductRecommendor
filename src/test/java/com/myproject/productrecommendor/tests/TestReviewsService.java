package com.myproject.productrecommendor.tests;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.apache.http.HttpResponse;
import org.junit.Test;
import com.myproject.productrecommendor.constants.Constants;
import com.myproject.productrecommendor.exception.CustomException;
import com.myproject.productrecommendor.model.Product;
import com.myproject.productrecommendor.service.ProductRecommendationsService;
import com.myproject.productrecommendor.service.ProductReviewsService;
import com.myproject.productrecommendor.utilities.APIDataAccess;

/**
 * @author NKHarish
 *	This class consists of various JUNIT tests.
 */
public class TestReviewsService {
	/**
	 * This method tests if Reviews API returns OK response for valid URL.  
	 */
	@Test
	public void reviewsAPIConnectionTest(){
		HttpResponse response=null;
		int validProductID=42608121;
		APIDataAccess apiDataAccess = new APIDataAccess();
		Constants.QUERY_PARAM_MAP.put("productItemId",String.valueOf(validProductID));
		try {
			response=apiDataAccess.processURI("reviews");
			if(response!=null){
				int urlResponseCode=response.getStatusLine().getStatusCode();
				assertEquals(Constants.OK_RESPONSE_CODE,urlResponseCode);
			}
		} catch (CustomException ex) {
			System.out.println(ex.getMessage());				
		}
	}
	/**
	 * This method tests if Reviews API returns BAD_REQUEST response for invalid URL.  
	 */
	@Test
	public void reviewsAPIConnectionTest1(){
		HttpResponse response=null;
		String invalidProductID="askfh";
		APIDataAccess apiDataAccess = new APIDataAccess();
		Constants.QUERY_PARAM_MAP.put("productItemId",invalidProductID);
		try {
			response=apiDataAccess.processURI("reviews");
			if(response!=null){
				int urlResponseCode=response.getStatusLine().getStatusCode();
				assertEquals(Constants.BAD_REQUEST_CODE,urlResponseCode);
			}
		} catch (CustomException ex) {
			System.out.println(ex.getMessage());				
		}
	}
	/**
	 * This method tests if ProductReviewsService works fine and returns String for valid productItemID. 
	 */
	@Test
	public void productReviewsServiceTest(){
		int productItemID=42608121;
		String finalOutput;
		List<Product> productRecommendationsList;
		ProductRecommendationsService productRecommendationsService=new ProductRecommendationsService();
		productRecommendationsList=productRecommendationsService.invokeProductRecommendationsAPI(productItemID);
		ProductReviewsService productReviewsService=new ProductReviewsService();
		finalOutput=productReviewsService.invokeReviewsAPI(productRecommendationsList.subList(0, Constants.MAX_ITEMS));
		assertEquals(finalOutput.getClass(),String.class);
	}
}
