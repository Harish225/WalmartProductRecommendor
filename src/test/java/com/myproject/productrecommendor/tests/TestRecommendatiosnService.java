package com.myproject.productrecommendor.tests;

import static org.junit.Assert.*;
import java.util.List;
import org.apache.http.HttpResponse;
import org.junit.Test;
import com.myproject.productrecommendor.constants.Constants;
import com.myproject.productrecommendor.exception.CustomException;
import com.myproject.productrecommendor.model.Product;
import com.myproject.productrecommendor.service.ProductRecommendationsService;
import com.myproject.productrecommendor.utilities.APIDataAccess;

/**
 * @author NKHarish
 *	This class consists of various JUNIT tests.
 */
public class TestRecommendatiosnService {
	
	/**
	 * This method tests if Recommendations API returns OK response for valid URL.  
	 */
	@Test
	public void recommendationsAPIConnectionTest(){
		HttpResponse response=null;
		int validProductID=42608121;
		APIDataAccess apiDataAccess = new APIDataAccess();
		Constants.QUERY_PARAM_MAP.put("productItemId",String.valueOf(validProductID));
		try {
			response=apiDataAccess.processURI("nbp");
			if(response!=null){
				int urlResponseCode=response.getStatusLine().getStatusCode();
				assertEquals(Constants.OK_RESPONSE_CODE,urlResponseCode);
			}
		} catch (CustomException ex) {
			System.out.println(ex.getMessage());				
		}
	}
	/**
	 * This method tests if Recommendations API returns BAD_REQUEST response for invalid URL.  
	 */
	@Test
	public void recommendationsAPIConnectionTest1(){
		HttpResponse response=null;
		String invalidProductID="askfh";
		APIDataAccess apiDataAccess = new APIDataAccess();
		Constants.QUERY_PARAM_MAP.put("productItemId",invalidProductID);
		try {
			response=apiDataAccess.processURI("nbp");
			if(response!=null){
				int urlResponseCode=response.getStatusLine().getStatusCode();
				assertEquals(Constants.BAD_REQUEST_CODE,urlResponseCode);
			}
		} catch (CustomException ex) {
			System.out.println(ex.getMessage());				
		}
	}
	/**
	 * This method tests if ProductRecommendationsService works fine and returns list for valid productItemID. 
	 */
	@Test
	public void productRecommendationsServiceTest(){
		int productItemID=42608121;
		List<Product> productRecommendationsList;
		ProductRecommendationsService productRecommendationsService=new ProductRecommendationsService();
		productRecommendationsList=productRecommendationsService.invokeProductRecommendationsAPI(productItemID);
		assertNotEquals(0,productRecommendationsList.size());
	}
}
