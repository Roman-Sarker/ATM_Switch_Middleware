/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml.data;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream; 
import model.ResponseData;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author roman
 */
public class XMLDataParse {

    public ResponseData getResponseDataFromXML(String xml) {
        SAXBuilder builder = new SAXBuilder();
        ResponseData responseData = new ResponseData();
         
        try {
            InputStream stream = new ByteArrayInputStream(xml.getBytes("UTF-8"));
            Document document = builder.build(stream);
            Element node = document.getRootElement(); 
            
            String reqNO =  node.getChild("REQ_NO").getText();
            responseData.setReqNO(reqNO); 
            String operatingBALANCE = node.getChildText("OPERATING_BALANCE");
            responseData.setOperatingBALANCE(operatingBALANCE); 
            String responseCODE =  node.getChildText("RESPONSE_CODE");
            responseData.setResponseCODE(responseCODE); 
            String responseMSG = node.getChildText("RESPONSE_MSG");
            responseData.setResponseMSG(responseMSG); 
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
        return responseData;
    }

    public static void main(String[] args) {
//         XMLDataParse xmlDataParse = new XMLDataParse();
//         xmlDataParse.getResponseDataFromXML("<BALANCE_INQUIRY><REQ_NO>71</REQ_NO><OPERATING_BALANCE>14715.17</OPERATING_BALANCE><RESPONSE_CODE>000</RESPONSE_CODE><RESPONSE_MSG>Successfully Delivered.</RESPONSE_MSG></BALANCE_INQUIRY>");
    }

}
