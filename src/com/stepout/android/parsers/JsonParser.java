package com.stepout.android.parsers;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonParser {

	public static JSONObject getJSONfromURL(String url){
		InputStream is = null;
		String result = "";
		JSONObject jArray = null;
		
		
		
		
	    try{
	            HttpClient httpclient = new DefaultHttpClient();
	            HttpPost httppost = new HttpPost(url);
	            HttpParams params = new BasicHttpParams();
			    HttpConnectionParams.setConnectionTimeout(params, 30000);
			    HttpConnectionParams.setSoTimeout(params, 30000); 
	            HttpResponse response = httpclient.execute(httppost);
	            HttpEntity entity = response.getEntity();
	            is = entity.getContent();
	            
	            

	    }
	    catch(Exception e){
	            Log.e("JSONParser StepOut", "Error in HTTP connection "+e.toString());
	    }
	    
	  
	    try{
	            BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                    sb.append(line + "\n");
	            }
	            is.close();
	            result=sb.toString();
	    }
	    catch(Exception e){
	            Log.e("JSONParser StepOut", "Error converting result "+e.toString());
	    }
	    
	    try{
	    	
            jArray = new JSONObject(result);            
	    }catch(JSONException e){
	            Log.e("JSONParser StepOut", "Error parsing data "+e.toString());
	    }
    
	    return jArray;
	}
}
