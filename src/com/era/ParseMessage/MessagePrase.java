/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.ParseMessage;

import com.era.Request.Balance_Enquiry.BalanceEnquery;
import com.era.Request.Cash.Withdraw.CashWithDraw;
import com.era.Request.Echo.EchoMessage;
import com.era.Request.reversal.Acquirer_Reversal_Advice;
import com.era.Request.transaction.advice.FinTransactionAdvice;
import com.jpos.iso8583.ISOField; 
import com.jpos.iso8583.ISOMessageParse; 
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
public class MessagePrase { 
    
    public String extractTransactionCode(String processingCode){
        return processingCode.substring(0, 3);
    }
    
    public String parseAndBuildISOMessage(String isoMessage){
        try {
            InputStream stream = MessagePrase.class.getResourceAsStream("field.xml");
            ISOMessageParse st = new ISOMessageParse(stream);
            List<ISOField> list = st.Parse(isoMessage);
            
            ISOField isoField = list.get(list.size()-1);
            String sMTI = isoField.getValue() ; 
            if(sMTI.trim().equals("0800")){
                stream =  MessagePrase.class.getResourceAsStream("field.xml");
                EchoMessage echoMessage = new EchoMessage(stream);
                String message2= echoMessage.receiveMsgAndParse(list); 
                return message2;
            }else if(sMTI.trim().equals("0200")){
                String processingCode = ""; 
                for(ISOField iso:list){
                    if(iso.getIndex() == 3)
                        processingCode = iso.getValue(); 
                }
                stream =  MessagePrase.class.getResourceAsStream("field.xml");
                String message2=null;
                String transactionCode = extractTransactionCode(processingCode);
                if(transactionCode.equals("030")){
                    BalanceEnquery balanceInquiry = new BalanceEnquery(stream);
                    message2 =  balanceInquiry.receiveMsgAndParse(list); 
                }else if(transactionCode.equals("010")){
                    CashWithDraw cashWithDraw = new CashWithDraw(stream);
                    message2 =  cashWithDraw.receiveMsgAndParse(list); 
                }else{
                    System.out.println("Operation not supported yet");
                }
                return message2;
            }else if(sMTI.trim().equals("0220")){
                stream =  MessagePrase.class.getResourceAsStream("field.xml");
                FinTransactionAdvice finTransactionAdvice = new FinTransactionAdvice(stream);
                String message2 =  finTransactionAdvice.receiveMsgAndParse(list); 
                return message2;
            }else if(sMTI.trim().equals("0420")){
                stream =  MessagePrase.class.getResourceAsStream("field.xml");
                Acquirer_Reversal_Advice acquirer_Reversal_Advice = new Acquirer_Reversal_Advice(stream);
                String message2 =  acquirer_Reversal_Advice.receiveMsgAndParse(list); 
                return message2;
            }else{
                System.out.println("operation is not supported yet");
                return null;
            } 
        } catch (IOException ex) {
            Logger.getLogger(MessagePrase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(MessagePrase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MessagePrase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void main(String[] args)  throws ParserConfigurationException, IOException, SAXException {
         MessagePrase messageParse = new MessagePrase();
         String str = "A4M040000200F238669128A0920000000000048000A016479161******37790100000000000500000021909204271499515204202196011050051000091D000000000692900137479161******3779=2303626*************00000525407108NRBGUL01NRBB                          DHAKA         000050Principal Branch ATM-1        1                                          00120190219NRBBNRBB                        0000000000000000050FFFFFFFFFFFFFFFF097<82>^B^\\\\0<95>^E<80>\\0^D<80>\\0<9a>^C^Y^B^Y<9c>^A^A_*^B\\0P<9f>^B^F\\0\\0\\0\\0P\\0<9f>^P^G^F^A\\n^C  \\0<9f>^Z^B\\0P<9f>&\\b!5ÙW\\r^NRµ<9f>\\'^A<80><9f>3^C`@ <9f>6^B^B\\0<9f>7^D<92>HýF_4^A\\0<9f>4^C^B^C\\0P^DVisa01?0000000000000000000000000001***                                         021CC=0";
         str = "A4M080000200F638669128B0A00800000000064000A01643418400000000330300000000000000000000000000000011707263052113313173401176011050901000000D0000000006929001324341840000000033=27072011961179800000001356208NRBGUL01NRBB                          DHAKA                         000050HEAD OFFICE                   9999                                                        00120190117NRBB      NRBB                         00000000000000001 050840NRBBNRBB      13998199000013008101010010000000000000000000000000000007013157800000000001                                            004TC=5" ;
         String isoMessage = null;// messageParse.parseAndBuildISOMessage("A4M080000800822000000000000004000000000000001101094753130037301");
         isoMessage = messageParse.parseAndBuildISOMessage(str);
         System.out.println("isoMessage: "+isoMessage);        
    }
}
