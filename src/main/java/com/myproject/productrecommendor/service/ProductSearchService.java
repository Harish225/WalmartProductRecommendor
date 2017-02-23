package com.myproject.productrecommendor.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.apache.http.HttpResponse;

import com.myproject.productrecommendor.constants.Constants;
import com.myproject.productrecommendor.exception.CustomException;
import com.myproject.productrecommendor.utilities.APIDataAccess;
/**
 * @author NKHarish
 * This class consists of invokesSearchAPI method which invokes Search API and process search results.
 */
public class ProductSearchService {

	/**
	 * This method calls processURI method which builds URI, invokes API and returns Response object. Parses JSON Response.
	 * @param searchString java.lang.String
	 * @return ProductItemID Integer
	 */
	public int invokeSearchAPI(String searchString) {
		
		int itemID=0;
		HttpResponse response=null;
		String errorMessage;
		APIDataAccess apiDataAccess = new APIDataAccess();
		/* Inputs required URI parameters to HashMap */
		Constants.QUERY_PARAM_MAP.put("searchString", searchString);
		Constants.QUERY_PARAM_MAP.put("searchAPIKeyword", "search");
		try {
			/* Call to processURI method passing "search" keyword to invoke corresponding API.*/
			response=apiDataAccess.processURI(Constants.QUERY_PARAM_MAP.get("searchAPIKeyword"));
		 		if(response!=null){
			 		BufferedReader reader=null;
					try {
						/*Reading and Parsing the JSON Response Object.*/
						reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
					    JsonReader jsonReader = Json.createReader(reader);
					    JsonObject jsonObject = jsonReader.readObject();
					    if(jsonObject.containsKey("items")){
					    	 	JsonArray itemsList= jsonObject.getJsonArray("items");
							    if(itemsList.size()>0){
							    	/*Reading the First Product ItemID from the Product Items List.*/
							    	itemID = itemsList.getJsonObject(0).getInt("itemId");
							    }
					    	}
					    jsonReader.close();
					    } /*Catching the Exceptions and throwing a Custom Exception.*/
							catch(JsonException e){
					    	errorMessage="JSON Exception occurred while processing the API Response Object.";
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
				System.out.println("Exception Cause:"+ex.getCause());
				System.out.println("Exception Message:"+ex.getMessage());
				System.exit(0);
			}
		/*Returning the first Product ItemID.*/
		return itemID;
	}
}
