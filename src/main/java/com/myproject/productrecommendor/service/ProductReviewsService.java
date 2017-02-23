package com.myproject.productrecommendor.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.apache.http.HttpResponse;
import com.myproject.productrecommendor.constants.Constants;
import com.myproject.productrecommendor.exception.CustomException;
import com.myproject.productrecommendor.model.Product;
import com.myproject.productrecommendor.utilities.APIDataAccess;
import com.myproject.productrecommendor.utilities.JSONProcessor;
/**
 * @author NKHarish
 * This class consists of invokesReviewsAPI method which invokes Reviews API and process results.
 */
public class ProductReviewsService {
	/**
	 * This method calls processURI method which builds URI, invokes API and returns Response object. Parses JSON Response.
	 * @param rankedProductRecommendationsList List<Product> 
	 * @return  jsonStringOutput java.lang.String
	 */
	public String invokeReviewsAPI(List<Product> rankedProductRecommendationsList) {
		
		HttpResponse response=null;
		APIDataAccess apiDataAccess = new APIDataAccess();
		String errorMessage;
		String jsonStringOutput="";
		/* Inputs required URI parameters to HashMap */
		Constants.QUERY_PARAM_MAP.put("reviewsAPIKeyword", "reviews");
		try {
			for (int i = 0; i < rankedProductRecommendationsList.size(); i++) {
				/* Passing each ProductItemId to build URI and invoke Reviews API.*/
	    		Constants.QUERY_PARAM_MAP.put("productItemId", String.valueOf(rankedProductRecommendationsList.get(i).getItemId()));
	    		/* Call to processURI method passing "reviews" keyword to invoke corresponding Recommendations API.*/
	    		response=apiDataAccess.processURI(Constants.QUERY_PARAM_MAP.get("reviewsAPIKeyword"));
				if(response!=null){
					BufferedReader br=null;
					try {
						/*Reading and Parsing the JSON Response Object.*/
						br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
						JsonReader jsonReader = Json.createReader(br);
					    JsonObject jsonObject = jsonReader.readObject(); 
					    /*Reading and parsing Reviews JSON Object and setting the AverageOverallRating value to the Model Object*/
					    if (jsonObject.containsKey("reviewStatistics")){
						    	JsonObject reviewStatisticsObject;
						    	reviewStatisticsObject= jsonObject.getJsonObject("reviewStatistics");
						    	if(reviewStatisticsObject.containsKey("averageOverallRating")){
						    		/*Reading the averageOverallRating value and setting to the Model Object variable.*/
				    				float averageOverallRating =reviewStatisticsObject.containsKey("averageOverallRating")?Float.parseFloat(reviewStatisticsObject.getString("averageOverallRating")):0.0f;
							    	rankedProductRecommendationsList.get(i).setAverageOverallRating(averageOverallRating);
						    	}
				    		}else{
				    			/*Setting zero value in the absence of ReviewStatistics Object.*/
				    			rankedProductRecommendationsList.get(i).setAverageOverallRating(Constants.ZERO);
			    			}
					    jsonReader.close();
				    	}   /*Catching the Exceptions and throwing a Custom Exception.*/
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
				}
			/*Sorting the Product Recommendations List based on AverageOverallRating.*/
		    Collections.sort(rankedProductRecommendationsList, new Comparator<Product>(){
				public int compare(Product product1, Product product2) {
					 return Double.compare(product1.getAverageOverallRating(), product2.getAverageOverallRating());
				}
		    });
		    /*Reverse the list to obtain in descending order.*/
	        Collections.reverse(rankedProductRecommendationsList);
		    /*System.out.println("The rank ordered product recommendations for the input Keyword are:");
		    for(int i=0;i<rankedProductRecommendationsList.size();i++){
				System.out.println("[ ItemID: "+String.valueOf(rankedProductRecommendationsList.get(i).getItemId())+", AverageRating: "
					    			   +String.valueOf(rankedProductRecommendationsList.get(i).getAverageOverallRating())+", Parent Item ID: "
									   +String.valueOf(rankedProductRecommendationsList.get(i).getParentItemId())+", Name: "
									   +rankedProductRecommendationsList.get(i).getName()+", CategoryPath: "
									   +rankedProductRecommendationsList.get(i).getCategoryPath()+", Product URL: "
									   +rankedProductRecommendationsList.get(i).getProductUrl()+", UPC: "
									   +String.valueOf(rankedProductRecommendationsList.get(i).getUpc())+" ]");
			}*/
		    JSONProcessor jsonProcessor=new JSONProcessor();
		    /*Call to listJSONConverter which converts List Object to JSON Object and returns as String*/
			jsonStringOutput = jsonProcessor.listJSONConverter(rankedProductRecommendationsList);
			}/* Catching Custom Exceptions and displaying the error message.*/ 
			catch (CustomException ex) {
				System.out.println("Exception Cause:"+ex.getCause());
				System.out.println("Exception Message:"+ex.getMessage());
				System.exit(0);
			}
		/*Final JSON Object Output as String. */
		return jsonStringOutput;
	}
}



