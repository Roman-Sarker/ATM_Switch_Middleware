/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.Request.transaction.advice;
  
import com.jpos.iso8583.ISOField;
import com.jpos.iso8583.ISOMessageBuild;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Administrator
 */
public class FinTransactionAdvice {
    
    private InputStream stream ;

    public FinTransactionAdvice(InputStream stream ) {
        this.stream = stream ; 
    }
    
    private List<ISOField> getListAndProcess(List<ISOField> list){
        ISOField isoField1 = new ISOField();
        isoField1.setIndex(38);
        isoField1.setValue("00001");
        ISOField isoField2 = new ISOField();
        isoField2.setIndex(39);
        isoField2.setValue("00001");
        list.add(isoField1);
        list.add(isoField2);
        return list;  
    }

    public String receiveMsgAndParse(List<ISOField> list) {
        try {
            List<ISOField> processISOField = getListAndProcess(list);  
            ISOMessageBuild isoMessageBuild = new ISOMessageBuild(stream);
            String[] saDaE = isoMessageBuild.convertListToSringArray(processISOField);
            String message2 = isoMessageBuild.Build(saDaE, "0230","A4M08000");
            return message2;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(FinTransactionAdvice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FinTransactionAdvice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(FinTransactionAdvice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    
    
}
