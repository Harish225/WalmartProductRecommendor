package com.myproject.productrecommendor.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author NKHarish
 *	This class consists of Project Constants which are used through out all classes.
 */
public class Constants {
	/*HashMap to hold the URI building parameters.*/
	public static Map<String, String> QUERY_PARAM_MAP=new HashMap<String,String>();
	public static final String SLASH_SEPARATOR = "/";
	public static final float ZERO = 0;
	public static final int OK_RESPONSE_CODE = 200;
	public static final int BAD_REQUEST_CODE = 400;
	public static final int MAX_ITEMS = 10;

}
