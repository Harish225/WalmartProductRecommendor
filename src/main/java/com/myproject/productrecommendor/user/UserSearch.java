package com.myproject.productrecommendor.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.myproject.productrecommendor.constants.Constants;
import com.myproject.productrecommendor.model.Product;
import com.myproject.productrecommendor.serviceImpl.RankedProductsRecommendor;
import com.myproject.productrecommendor.serviceImpl.RankedProductsRecommendorImpl;

/**
 * @author NKHarish
 *	The UserSearch class contains the Main Method and the program execution starts from here.
 */
public class UserSearch {

	/**
	 * Main Method reads user search input from console, calls the business service methods and displays the Ranked Recommended Products.
	 * The rank order recommended products JSON object is shown as string.   
	 */
	public static void main(String[] args) {
		System.out.print("Please enter whitespace separated sequence of keywords: ");
		/* Reads User Input. */
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		/* Regex pattern to validate the input search keyword. */
		String pattern= "^\\s*[\\da-zA-Z][\\da-zA-Z\\s]*$";
		String searchString="";
		try {
			searchString = reader.readLine().trim();
		} catch (IOException e) {
			System.out.println("Exception risen while reading the input keyword.");
		}
		if(searchString!=null&&searchString.matches(pattern)){
			int productItemId;
			/* Instantiate Object for Business Service Class. */
			RankedProductsRecommendor rankedProductsRecommendorImpl=new RankedProductsRecommendorImpl();
			/* Call to getSearchAPIResults method passing input search keyword returns first Product Item ID from the search results.*/
			productItemId = rankedProductsRecommendorImpl.getSearchAPIResults(searchString);
			if(productItemId!=0){
				List<Product> productRecommendationsList=new ArrayList<Product>();
				/* Call to getProductRecommendationsAPIResults method passing first Product ItemID returns list of Recommended Products.*/	
				productRecommendationsList = rankedProductsRecommendorImpl.getProductRecommendationsAPIResults(productItemId);
				if(productRecommendationsList!=null){
					/* Call to getReviewsAPIResults method passing list of Recommended products returns rank order recommended products JSON object as String.*/
					String finalOutput = rankedProductsRecommendorImpl.getReviewsAPIResults(productRecommendationsList.subList((int) Constants.ZERO,Constants.MAX_ITEMS));
					if(finalOutput!=""){
						System.out.println("The Rank order Product Recommendations for the input "+searchString.toUpperCase()+" are:"+finalOutput);
					}else{
						System.out.println("No Reviews for Product Recommendations are available for the search "+searchString.toUpperCase()+" keyword");
					}
				}else{
					System.out.println("No Product Recommendations are available for the search "+searchString.toUpperCase()+" keyword");
				}				
			}else{
				System.out.println("No Search Results are available for the search "+searchString.toUpperCase()+" keyword");
			}
		}else{
			System.out.println("Please enter valid keyword");
		}
	}
}
