package com.myproject.productrecommendor.serviceImpl;

import java.util.List;
import com.myproject.productrecommendor.model.Product;
/**
 * @author NKHarish
 * This Interface list the mandatory methods which needs to implemented to get the Ranked Product Recommendations List.
 */
public interface RankedProductsRecommendor {

	/**
	 * @param searchString java.lang.String
	 * @return ProductItemID Integer
	 */
	public int getSearchAPIResults(String searchString);
	/**
	 * @param searchString java.lang.String
	 * @return ProductItemID Integer
	 */
	public List<Product> getProductRecommendationsAPIResults(int productItemId);
	/**
	 * @param productRecommendationsList List<Product>
	 * @return rank order Product Recommendations  java.lang.String
	 */
	public String getReviewsAPIResults(List<Product> productRecommendationsList);
	
}
