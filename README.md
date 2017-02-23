# WalmartProductRecommendator

As part of this project I have developed a Java application to rank order Walmart Product Recommendations by calling various Walmart Labs Open API’s. The various Walmart API’s invoked in this application are:

              •	Search API
              
              •	Product Recommendation API
              
              •	Reviews API
              

Assumptions:

1.	All the Inputs will be read from console and outputs will be printed to console.
2.	User requests data only in JSON format.
3.	The recommended products are ranked based on the AverageOverallRating value obtained from reviews associated with each product.
4.	The output ranked product recommendations contain important fields like Product itemId, Product ParentItemId, Product Name, Product UPC, Product CategoryPath, Product ProductUrl, Product AverageOverallRating.
5.	The output is presented as JSON object as String.

Project Environment:

1.	Project has been developed in 64 bit windows using Eclipse IDE Mars.
2.	The java environment used for developing this project are:
      •	java version "1.7.0_79"(min req JRE 1.7 or above)

      •	Java(TM) SE Runtime Environment (build 1.7.0_79-b15)
      
      •	Apache Maven 3.3.9
      
3.	The various external jars used for the development are:

      •	httpclient-4.5.2.jar

      •	httpcore-4.4.4.jar
      
      •	commons-logging-1.2.jar
      
      •	commons-codec-1.9.jar
      
      •	javax.json-api-1.0.jar
      
      •	javax.json-1.0.4.jar
      
      •	jettison-1.3.7.jar
      
      •	stax-api-1.0.1.jar
      
      •	junit-4.12.jar
      
      •	hamcrest-core-1.3.jar
      

Steps to Run the Program:

Min Requirements: Java 1.7 or above version. Active Internet Connection.

1.	Clone the project from the below Repository to your local environment.

    Repository: https://github.com/Harish225/WalmartProductRecommendator.git

    git clone https://github.com/Harish225/WalmartProductRecommendator.git

2.	Navigate to the Target folder present in the WalmartProductRecommendator folder and execute the following command:

    java -jar Walmart-Product-Recommendor-0.0.1-SNAPSHOT-jar-with-dependencies.jar

    
    Input: Search Keyword
    
    Output: Rank Order Product Recommendations in JSON format.
    

# WalmartProductRecommendor
