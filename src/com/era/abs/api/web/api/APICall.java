package com.era.abs.api.web.api;

import com.era.abs.api.xml.data.XMLDataBuild;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.RequestData;
import model.ResponseData;
import xml.data.*;

public class APICall {

    public String mankeXMLDataOneLine(String xmlData) {
        Reader inputString = new StringReader(xmlData);
        BufferedReader reader = new BufferedReader(inputString);
         
        String line;
        StringBuilder sb = new StringBuilder();

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line.trim());
            }
        } catch (IOException ex) {
            Logger.getLogger(APICall.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }

    public ResponseData balanaceAPICall(long accountNo) { 
        XMLDataBuild xmlDataBuild = new XMLDataBuild();
        String xmlRequestData = mankeXMLDataOneLine(xmlDataBuild.getXMLData(accountNo));
        BalanceAPICall balanceAPICall = new BalanceAPICall();
        String xmlResponseData = balanceAPICall.balanceAPICall(xmlRequestData);
        XMLDataParse xmlDataParse = new XMLDataParse();
        return xmlDataParse.getResponseDataFromXML(xmlResponseData);
    }

    public ResponseData cashWithdrawlAPICall(RequestData requestData) { 
        return null;
    }

    public ResponseData transactionReversalAPICall(RequestData requestData) {
        return null;
    } 
    
    public static void main(String[] args) {
        APICall apiCall = new APICall();
        ResponseData responseData = apiCall.balanaceAPICall(9982990000001L);
        System.out.println(responseData.toString());
        if(responseData != null){ 
            System.out.println(responseData.getOperatingBALANCE()); 
        }
    }
}
