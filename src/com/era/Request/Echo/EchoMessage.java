/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.Request.Echo;
 
import com.jpos.iso8583.ISOField;
import com.jpos.iso8583.ISOMessageBuild;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author sultan
 */
public class EchoMessage {
    
    private InputStream stream ; 
    
    public EchoMessage(InputStream stream){
        this.stream=stream;
    }
    
    private List<ISOField> getListAndProcess(List<ISOField> list){
        ISOField isoField = new ISOField();
        isoField.setIndex(39);
        isoField.setValue("00000"); 
        list.add(isoField);
        return list;
    }
    
    public String receiveMsgAndParse(List<ISOField> list){
        try {
            List<ISOField> processISOField = getListAndProcess(list);
            ISOMessageBuild isoMessageBuild = new ISOMessageBuild(stream);
            String[] saDaE = isoMessageBuild.convertListToSringArray(processISOField);
            String message2 = isoMessageBuild.Build(saDaE, "0810","A4M08000");
            return message2;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(EchoMessage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EchoMessage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(EchoMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
