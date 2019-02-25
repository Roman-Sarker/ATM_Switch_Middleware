/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.socket;

/**
 *
 * @author sultan
 */ 
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;  
 

/**
 * Example of a small server app that listens on a port, receives connections
 * and reads messages and responds back.
 *
 * @author Enrique Zamudio
 */
public class  Server implements Runnable { 
    private Socket socket;

    public Server(Socket sock) throws IOException {
        socket = sock;
    }

    public void run() {
        int count = 0;
        byte[] lenbuf = new byte[2];
        try {
             
            while (socket != null && socket.isConnected()
                    && Thread.currentThread().isAlive()
                    && !Thread.currentThread().isInterrupted()) {
                if (socket.getInputStream().read(lenbuf) == 2) {
                    int size = ((lenbuf[0] & 0xff) << 8) | (lenbuf[1] & 0xff);
                   // System.out.println("Data is read "+size);
                    byte[] buf = new byte[size];
                    
                    // We're not expecting ETX in this case
                    int len = socket.getInputStream().read(buf);
                 //   System.out.println("Data is read "+new String(buf));
                    count++;
                    Thread th = new Thread(new Processor(buf, socket));
                    th.run(); 
                }
            }
        } catch (IOException ex) {
            System.out.print("exception occurred: "+ex.getMessage());
        }
        System.out.print("Exiting after reading "+count+" requests");
        try {
            socket.close();
        } catch (IOException ex) {
        }
    } 

    public static void main(String[] args) throws Exception { 
        
    }

}
