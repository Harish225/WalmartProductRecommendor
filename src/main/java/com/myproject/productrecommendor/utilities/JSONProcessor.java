package com.myproject.productrecommendor.utilities;

import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.myproject.productrecommendor.exception.CustomException;
import com.myproject.productrecommendor.model.Product;

/**
 * @author NKHarish
 *	This is a Utility Class and contains listJSONConverter utility method.
 */
public class JSONProcessor {

	/**
	 * This method converts List<Product> to JSON Object and returns it as String.
	 * @param rankedPRList List<Product>
	 * @return rankedPRJSONObject.toString() java.lang.String	
	 * @throws CustomException
	 */
	public String listJSONConverter(List<Product> rankedPRList) throws CustomException{
		JSONObject rankedPRJSONObject = new JSONObject();
		String errorMessage;
		try
		{
		    JSONArray rankedPRJSONArray = new JSONArray();
		    for (Product product : rankedPRList)
		    {
		    	/**
		    	 * Instantiate JSON Object set the Keys and Values add to the JSONArray. 
		    	 */
		         JSONObject productJSON = new JSONObject();
		         productJSON.put("ItemID", String.valueOf(product.getItemId()));
		         productJSON.put("AverageOverallRating", String.valueOf(product.getAverageOverallRating()));
		         productJSON.put("ParentItemID", String.valueOf(product.getParentItemId()));
		         productJSON.put("ProductName", product.getName());
		         productJSON.put("CategoryPath", product.getCategoryPath());
		         productJSON.put("ProductURL", product.getProductUrl());
		         productJSON.put("ProductUPC", String.valueOf(product.getUpc()));
		         rankedPRJSONArray.put(productJSON);
		    }
		    rankedPRJSONObject.put("RankedProductRecommendations", rankedPRJSONArray);
		} catch (JSONException e) {
			errorMessage = "JSON Exception occurred while converting List Object to JSON Array.";
			throw new CustomException(errorMessage,e.getCause());
		}
		/*Returning JSON Object as String.*/
		return rankedPRJSONObject.toString();
		
	}
}
