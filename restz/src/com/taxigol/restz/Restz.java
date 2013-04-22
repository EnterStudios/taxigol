package com.taxigol.restz;

import java.util.Map;

import com.taxigol.restz.requests.GetRequest;
import com.taxigol.restz.requests.PostRequest;
import com.taxigol.restz.requests.PutRequest;

public interface Restz {

	/**
	 * 
	 * @param request the url of the get request must be oncoded correctly
	 * @return returns a get method object
	 */
	public GetRequest get(String request);
	/**
	 * 
	 * @param baseUrl the base url i.e. http://baseurl.com
	 * @param params the parameters i.e. name=bob&lastname=doe
	 * @return returns a get method object
	 */
	public GetRequest get(String baseUrl, String... params);
	/**
	 * 
	 * @param baseUrl
	 * @param params the params must be submitted in pairs. If for example you
	 * want a=b&b=c then params={a,b,b,c}
	 * @return returns a get method object
	 */
	public GetRequest get(String baseUrl, String params);
	/**
	 * 
	 * @param baseUrl
	 * @param params the get params encoded as a hashmap
	 * @return returns a get method object
	 */
	public GetRequest get(String baseUrl, Map<String,String> params);
	
	/**
	 * Posts to the specified url
	 * @param baseUrl the url to post
	 * @return a post method object
	 */
	public PostRequest post(String baseUrl);
	/**
	 * Posts to the specified url
	 * @param baseUrl the url to post
	 * @param params the params must be submitted in pairs. If for example you
	 * want a=b&b=c then params={a,b,b,c}
	 * @return a post method object
	 */
	public PostRequest post(String baseUrl, String... params);
	/**
	 * Put to the specified url
	 * @param baseUrl the url to post
	 * @return a put method object
	 */
	public PutRequest put(String baseUrl);
	/**
	 * Put to the specified url
	 * @param baseUrl the url to put to
	 * @param params the params must be submitted in pairs. If for example you
	 * want a=b&b=c then params={a,b,b,c}
	 * @return a put method object
	 */
	public PutRequest put(String baseUrl, String... params);
	
	/**
	 * Closes all conections
	 */
	public void close();
	
}
