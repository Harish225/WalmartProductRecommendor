package com.myproject.productrecommendor.tests;

import static org.junit.Assert.*;
import org.apache.http.HttpResponse;
import org.junit.Test;
import com.myproject.productrecommendor.constants.Constants;
import com.myproject.productrecommendor.exception.CustomException;
import com.myproject.productrecommendor.service.ProductSearchService;
import com.myproject.productrecommendor.utilities.APIDataAccess;

/**
 * @author NKHarish
 *	This class consists of various JUNIT tests.
 */
public class TestSearchService {
	/**
	 * This method tests if Search API returns OK response for valid URL.  
	 */
	@Test
	public void searchAPIConnectionTest(){
		HttpResponse response=null;
		APIDataAccess apiDataAccess = new APIDataAccess();
		Constants.QUERY_PARAM_MAP.put("searchString", "test");
		try {
			response=apiDataAccess.processURI("search");
			if(response!=null){
				int urlResponseCode=response.getStatusLine().getStatusCode();
				assertEquals(Constants.OK_RESPONSE_CODE,urlResponseCode);
			}
		} catch (CustomException ex) {
			System.out.println(ex.getMessage());				
		}
	}
	/**
	 * This method tests if Search API returns BAD_REQUEST response for valid URL.  
	 */
	@Test
	public void searchAPIConnectionTest1(){
		HttpResponse response=null;
		APIDataAccess apiDataAccess = new APIDataAccess();
		Constants.QUERY_PARAM_MAP.put("searchString", "");
		try {
			response=apiDataAccess.processURI("search");
			if(response!=null){
				int urlResponseCode=response.getStatusLine().getStatusCode();
				assertEquals(Constants.BAD_REQUEST_CODE,urlResponseCode);
			}
		} catch (CustomException ex) {
			System.out.println(ex.getMessage());				
		}
	}
	/**
	 * This method tests if Search API returns zero search results for invalid search keyword.  
	 */
	@Test
	public void productSearchServiceTest(){
		String badSearchKeyword="fbkasjdhfk";
		int productItemID;
		ProductSearchService productSearchService=new ProductSearchService();
		productItemID=productSearchService.invokeSearchAPI(badSearchKeyword);
		assertEquals(0, productItemID);
	}
}
