/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.Request.reversal;
  
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
public class Acquirer_Reversal_Advice {
    private InputStream stream ;

    public Acquirer_Reversal_Advice(InputStream stream ) {
        this.stream = stream ; 
    }
    
    public ISOField getISOField(int index,String value){
        ISOField isoField = new ISOField();
        isoField.setIndex(index);
        isoField.setValue(value); 
        return isoField;
    }

     private List<ISOField> getListAndProcess(List<ISOField> list){
        ISOField isoField = getISOField(38,"00001");
        list.add(isoField);
        isoField = getISOField(39,"00001");
        list.add(isoField);
        return list;
    }
    
    public String receiveMsgAndParse(List<ISOField> list){
        try {
            List<ISOField> processISOField = getListAndProcess(list);  
            ISOMessageBuild isoMessageBuild = new ISOMessageBuild(stream);
            String[] saDaE = isoMessageBuild.convertListToSringArray(processISOField);
            String message2 = isoMessageBuild.Build(saDaE, "0430","A4M08000");
            return message2;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Acquirer_Reversal_Advice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Acquirer_Reversal_Advice.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Acquirer_Reversal_Advice.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
    
    
}
