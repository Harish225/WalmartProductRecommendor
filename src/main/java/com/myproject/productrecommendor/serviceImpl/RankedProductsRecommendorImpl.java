package com.myproject.productrecommendor.serviceImpl;

import java.util.List;
import com.myproject.productrecommendor.model.Product;
import com.myproject.productrecommendor.service.ProductRecommendationsService;
import com.myproject.productrecommendor.service.ProductReviewsService;
import com.myproject.productrecommendor.service.ProductSearchService;

/**
 * @author NKHarish
 * This class is an implementation for RankedProductsRecommendor Interface.
 * This class consists implementation for Business Service Methods which calls Search,Recommendations and Reviews services.
 *
 */
public class RankedProductsRecommendorImpl implements RankedProductsRecommendor{

	/* 
	 * This method calls the invokeSearchAPI method which invokes Search API and process search results.`
	 * @param searchString - java.lang.String
	 * @Return productItemID - Integer 
	 */
	public int getSearchAPIResults(String searchString) {
		int productItemID;
		ProductSearchService productSearchService=new ProductSearchService();
		productItemID=productSearchService.invokeSearchAPI(searchString);
		return productItemID;
	}
	/* 
	 * This method calls the invokeProductRecommendationsAPI method which invokes Recommendations API and returns Product Recommendations.
	 * @Param productItemID - Integer
	 * @Return Product Recommendation List - List<Product> 
	 */
	public List<Product> getProductRecommendationsAPIResults(int productItemId) {
		List<Product> productRecommendationsList;
		ProductRecommendationsService productRecommendationsService=new ProductRecommendationsService();
		productRecommendationsList=productRecommendationsService.invokeProductRecommendationsAPI(productItemId);
		return productRecommendationsList;
	}

	/* 
	 * This method calls the invokeReviewsAPI method which invokes Reviews API and returns rank order Product Recommendations as String.
	 * @Param Product Recommendations List - List<Product>
	 * @Return Ranked Product Recommendation List - java.lang.String 
	 */
	public String getReviewsAPIResults(List<Product> productRecommendationsList) {
		String finalOutput;
		ProductReviewsService productReviewService=new ProductReviewsService();
		finalOutput=productReviewService.invokeReviewsAPI(productRecommendationsList);
		return finalOutput;
		
	}

}
