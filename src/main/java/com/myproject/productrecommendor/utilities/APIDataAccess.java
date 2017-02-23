package com.myproject.productrecommendor.utilities;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import com.myproject.productrecommendor.constants.Constants;
import com.myproject.productrecommendor.exception.CustomException;

/**
 * @author NKHarish
 *	This class consists of processURI method which invokes API and returns Response. 
 */
public class APIDataAccess {

	/**
	 * This method builds URI, invokes various API based on Input String and returns HTTP Response.
	 * @param requestType java.lang.String
	 * @return response HTTPResponse
	 * @throws CustomException
	 */
	public HttpResponse processURI(String requestType) throws CustomException{
		
		URI url=null;
		URIBuilder builder =new URIBuilder();
		HttpClient client=null;
		HttpGet request = null;
		HttpResponse response=null;
		String errorMessage;
		/*Setting the URI default parameters to the HashMap.*/
		Constants.QUERY_PARAM_MAP.put("scheme", "http");
		Constants.QUERY_PARAM_MAP.put("host","api.walmartlabs.com/v1/" );
		Constants.QUERY_PARAM_MAP.put("apiKey", "xhtd6jeuvub334nkjyqcupyj");
		Constants.QUERY_PARAM_MAP.put("format", "json");
		/**
		 * Building URI based on Request Type. 
		 */
		switch(requestType){
	    case "search" :
	    			builder.removeQuery();
	    			builder.setScheme(Constants.QUERY_PARAM_MAP.get("scheme")).setHost(Constants.QUERY_PARAM_MAP.get("host"))
	    				   .setPath(requestType)
	    				   .setParameter("apiKey", Constants.QUERY_PARAM_MAP.get("apiKey"))
	    				   .setParameter("query",  Constants.QUERY_PARAM_MAP.get("searchString"))
	    				   .setParameter("format", Constants.QUERY_PARAM_MAP.get("format"));
	    			break;
					
	    case "nbp" :
	    			builder.removeQuery();
	    			builder.setScheme(Constants.QUERY_PARAM_MAP.get("scheme")).setHost(Constants.QUERY_PARAM_MAP.get("host"))
						   .setPath(requestType)
						   .setParameter("apiKey", Constants.QUERY_PARAM_MAP.get("apiKey"))
						   .setParameter("itemId", Constants.QUERY_PARAM_MAP.get("productItemId"));
	    			break;
						   
	    case "reviews":   
	    			builder.removeQuery();
	    			builder.setScheme(Constants.QUERY_PARAM_MAP.get("scheme")).setHost(Constants.QUERY_PARAM_MAP.get("host"))
						   .setPath(requestType+Constants.SLASH_SEPARATOR+Constants.QUERY_PARAM_MAP.get("productItemId"))
						   .setParameter("apiKey", Constants.QUERY_PARAM_MAP.get("apiKey"))
						   .setParameter("format", Constants.QUERY_PARAM_MAP.get("format"));
	    			break;
	    default : 
	    		System.out.println("Call to Incorrect API is requested."+requestType.toUpperCase()+" is not valid Keyword.");
	    		break;
		}
		try {
			/**
			 * Instantiate HTTP Client, Response Objects and execute the HTTP GET request.
			 */
			url = builder.build();
			client = HttpClientBuilder.create().build();
			request = new HttpGet(url);
			System.out.println(request);
			try {
					response = client.execute(request);
					} /* Catch exceptions and throw Custom Exception.*/
					  catch (ClientProtocolException e) {
						errorMessage="Client Protocol Exception occurred while accessing the "+requestType.toUpperCase()+" API.";
						throw new CustomException(errorMessage,e.getCause());
					} catch (UnknownHostException e) {
						errorMessage="Server Not Found. Please check your Internet Connection.";
						throw new CustomException(errorMessage,e.getCause());
					} catch (IOException e) {
						errorMessage="IO Exception occurred while accessing the "+requestType.toUpperCase()+" API.";
						throw new CustomException(errorMessage,e.getCause());
					} catch (Exception e) {
						System.out.println("In Exception");
						errorMessage="Exception occurred while accessing the "+requestType.toUpperCase()+" API.";
						throw new CustomException(errorMessage,e.getCause());
				}
			} catch (URISyntaxException e) {
				errorMessage="URI Syntax Exception occurred while accessing the "+requestType.toUpperCase()+" API.";
				throw new CustomException(errorMessage,e.getCause());
			}
		/*Returning the HTTP Response Object.*/
		return response;
	}
}
