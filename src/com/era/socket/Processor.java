/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.socket;
  
import com.era.ParseMessage.MessagePrase;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket; 
 

/**
 *
 * @author sultan
 */
public class Processor implements Runnable { 
    private byte[] msg;
    private Socket sock; 
    private DataOutputStream out;

    Processor(byte[] buf, Socket s) {
        msg = buf;
        sock = s; 
    }

    public void run() {
        String receivedISOMessage = new String(msg);
        try {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(sock.getOutputStream()));
            System.out.print("Request  :  " + receivedISOMessage);
            byte[] processedISOMessage = doProcessing(msg);
            if (processedISOMessage != null) {
                int length = processedISOMessage.length; 
                byte[] mybyte = new byte[2];
                
                int L1 = length/256 ; 
                int L2 = length - L1*256; 
                mybyte[0] = (byte) L1;
                mybyte[1] = (byte) L2;

                out.write(mybyte[0]);
                out.write(mybyte[1]); 
                out.write(processedISOMessage);
                out.flush();
                
            } else {
                System.out.write("error in processing".getBytes()); 
            }  
        } catch (IOException e) {
            System.out.print("exception is " + e.getMessage());
            e.printStackTrace();
        }
    }

    static byte[] doProcessing(byte[] line) {
         String receivedISOMessage = new String(line);
         MessagePrase messageParse = new MessagePrase();
         String processISOMessage = messageParse.parseAndBuildISOMessage(receivedISOMessage);
         System.out.print("response: "+processISOMessage);
         return processISOMessage.getBytes(); 
    }
}
