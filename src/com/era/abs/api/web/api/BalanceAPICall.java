/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.abs.api.web.api;

import com.era.json.JSONParserPost;
import org.json.JSONObject; 

/**
 *
 * @author roman
 */
public class BalanceAPICall {
    
    public String balanceAPICall(String request){ 
        String URL = "http://10.200.10.147:8080/ords/emob/nrb/agentbanking/balanceinquiry";
        URL ="http://10.11.201.170:8089/webservice/emob/nrb/agentbanking/balanceinquiry";
        String responeXML = null;
       // System.out.print(" request is "+request);
       
        try{
            JSONParserPost jsonParserpost = new JSONParserPost();
            String output = jsonParserpost.makeHttpRequest(URL, "POST", request); 
            
            JSONObject jsonObject =new JSONObject(output);
            if(jsonObject != null) {
                responeXML = (String)jsonObject.get("response"); 
               // System.out.println(responeXML);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return responeXML;
    }
    
}
