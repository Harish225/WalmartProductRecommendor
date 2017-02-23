package com.myproject.productrecommendor.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.apache.http.HttpResponse;
import com.myproject.productrecommendor.constants.Constants;
import com.myproject.productrecommendor.exception.CustomException;
import com.myproject.productrecommendor.model.Product;
import com.myproject.productrecommendor.utilities.APIDataAccess;
/**
 * @author NKHarish
 * This class consists of invokesProductRecommendationsAPI method which invokes Recommendations API and process results.
 */
public class ProductRecommendationsService {

	/**
	 * This method calls processURI method which builds URI, invokes API and returns Response object. Parses JSON Response.
	 * @param productItemId Integer
	 * @return Product Recommendations List List<Product>
	 */
	public List<Product> invokeProductRecommendationsAPI(int productItemId) {
		HttpResponse response=null;
		ArrayList<Product> productRecommendationsList=null;
		APIDataAccess apiDataAccess = new APIDataAccess();
		String errorMessage;
		/* Inputs required URI parameters to HashMap */
		Constants.QUERY_PARAM_MAP.put("productItemId", String.valueOf(productItemId));
		Constants.QUERY_PARAM_MAP.put("productRecommendationAPIKeyword", "nbp");
		try {
			/* Call to processURI method passing "nbp" keyword to invoke corresponding Recommendations API.*/
			response=apiDataAccess.processURI(Constants.QUERY_PARAM_MAP.get("productRecommendationAPIKeyword"));
			if(response!=null){
				
				BufferedReader reader=null;
				try {
						/*Reading and Parsing the JSON Response Object.*/
						reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
						JsonReader jsonReader = Json.createReader(reader);
						JsonArray results= jsonReader.readArray();
					    productRecommendationsList = new ArrayList<Product>();
					    /* Reading the required JSON Object fields, setting the Product Model object and adding it to the List.*/
					    for(int n = 0; n < results.size(); n++) {
					    	JsonObject result=results.getJsonObject(n);
					    	Product productRecommended= new Product();
					    	productRecommended.setItemId(result.containsKey("itemId")?result.getInt("itemId"):0);
					    	productRecommended.setParentItemId(result.containsKey("parentItemId")?result.getInt("parentItemId"):0);
					    	productRecommended.setName(result.containsKey("name")?result.getString("name"):"");
					    	productRecommended.setUpc(result.containsKey("upc")?Long.parseLong(result.getString("upc")):0L);
					    	productRecommended.setProductUrl(result.containsKey("productUrl")?result.getString("productUrl"):"");
					    	productRecommended.setCategoryPath(result.containsKey("categoryPath")?result.getString("categoryPath"):"");
					    	productRecommendationsList.add(productRecommended);
					    	
					    	}
					    jsonReader.close();
					}  /*Catching the Exceptions and throwing a Custom Exception.*/
					   catch(JsonException e){
				    	errorMessage="Product Recommendations JSON Array not avaliable for the "+Constants.QUERY_PARAM_MAP.get("searchString").toUpperCase()+" Keyword.";
						throw new CustomException(errorMessage,e.getCause());
				    }  catch (Exception e) {
						errorMessage="Exception occurred while processing the API Response.";
						throw new CustomException(errorMessage,e.getCause());
					}                    
				}else{
					errorMessage="Null Response received from API Call";
					throw new CustomException(errorMessage);
					}
		    }/* Catching Custom Exceptions and displaying the error message.*/
			catch (CustomException ex) {
				System.out.println("Cause:"+ex.getCause());
				System.out.println("Message:"+ex.getMessage());
				System.exit(0);
			}
		/*Returning the Product Recommendations List.*/
		return productRecommendationsList;
	}
}
