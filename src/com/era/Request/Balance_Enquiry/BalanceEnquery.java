/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.Request.Balance_Enquiry;
 
import com.jpos.iso8583.ISOField;
import com.jpos.iso8583.ISOMessageBuild; 
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import model.ResponseData;
import com.era.abs.api.web.api.APICall;
import org.xml.sax.SAXException;

/**
 *
 * @author sultan
 */

public class BalanceEnquery { 
    
    private InputStream stream ;

    public BalanceEnquery(InputStream stream ) {
        this.stream = stream ; 
    }    
    
    String getAmountFromWebAPI(long accountNo){
        APICall apiCall = new APICall();
        ResponseData responseData = apiCall.balanaceAPICall(accountNo);
       // System.out.println(responseData.toString());
        String amount = "" ; 
        if(responseData != null){ 
            amount = responseData.getOperatingBALANCE(); 
        }    
        return amount;
    }
    
    public ISOField getISOField(int index,String value){
        ISOField isoField = new ISOField();
        isoField.setIndex(index);
        isoField.setValue(value); 
        return isoField;
    }
    
    
    
    private List<ISOField> getListAndProcess(List<ISOField> list){
       
        
        String accountNo = "" ; 
        for(ISOField iso:list){
            if(iso.getIndex()==102)
                accountNo = iso.getValue();
        }
        System.out.print("account no is "+accountNo);
        String amount = getAmountFromWebAPI(Long.parseLong(accountNo));
        int indx = amount.indexOf('.');
        if(indx >= 0){
            amount = amount.replace(".", "");
            amount = amount + "0" ; 
        }
        else 
            amount=amount+"000"; 
        System.out.print("webapi result : amount is "+amount);
        ISOField isoField = getISOField(105,amount);
        list.add(isoField);
        isoField = getISOField(38,"00001");
        list.add(isoField);
        isoField = getISOField(39,"00001");
        list.add(isoField);
      //  InsertIntoLogDB.insertDataIntoDB(list.get(2).getValue()); 
        return list;
    }
    
    public String receiveMsgAndParse(List<ISOField> list){
        try {
            List<ISOField> processISOField = getListAndProcess(list);  
            ISOMessageBuild isoMessageBuild = new ISOMessageBuild(stream);
            String[] saDaE = isoMessageBuild.convertListToSringArray(processISOField);
            String message2 = isoMessageBuild.Build(saDaE, "0210","A4M08000");
            return message2;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(BalanceEnquery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BalanceEnquery.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(BalanceEnquery.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
}
