package com.era.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParserPost {

    InputStream is;
    //JSONObject jObj;
    String jObj;
    String json;

    InputStream iStream;
    JSONArray jarray;

    public JSONParserPost() {
        json = "";
    }

    public String makeHttpRequest(String url, String method, List<NameValuePair> params) {

        // Making HTTP request
        try {

            // check for request method
            if (method.equals("POST")) {
                // request method is POST
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                
                // Set URL of matching server
                HttpPost httpPost = new HttpPost(url);
                
                // Set parameter
                httpPost.setEntity(new UrlEncodedFormEntity(params));
                
                // Call API & Receive data from matching server.
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                
                is = httpEntity.getContent();

            } else if (method.equals("GET")) {
                // request method is GET
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);  
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
        } catch (Exception e) {
            Logger.getLogger(JSONParser.class.getName()).log(Level.SEVERE, null, e);
        }

        // try parse the string to a JSON object
        try {
            jObj = json;
        } catch (Exception e) {
            Logger.getLogger(JSONParser.class.getName()).log(Level.SEVERE, null, e);
        }

        // return JSON String
        return jObj;

    }
    // constructor

    // function get json from url
    // by making HTTP POST or GET mehtod
     public String makeHttpRequest(String url, String method, String jsonData) {

        // Making HTTP request
        try {
            // check for request method
            if (method.equals("POST")) {
                // request method is POST

                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                //StringEntity params = new StringEntity(jsonData);
                httpPost.addHeader("content-type", "application/xml");
                httpPost.setHeader("requestinfo",jsonData);
                
                // This two line used when json parameter transfer by body (in Post method)
                //httpPost.addHeader("content-type", "application/x-www-form-urlencoded");
                //httpPost.setEntity(params);
                
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
                

            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            
            json = sb.toString();
            
           //System.out.println(json);
        } catch (Exception e) {
            Logger.getLogger(JSONParser.class.getName()).log(Level.SEVERE, null, e);
        }

        // try parse the string to a JSON object
        try {
            //jObj = new JSONObject(json);
            jObj = json;
        } catch (Exception e) {
            Logger.getLogger(JSONParser.class.getName()).log(Level.SEVERE, null, e);
        }

        // return JSON String
        return jObj;

    }
    
}
