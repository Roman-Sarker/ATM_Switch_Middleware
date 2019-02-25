
package com.era.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sultan Ahmed Sagor BUET , CSE-08 Batch , Dhaka .
 */
public class Client extends Thread {

    int number;

    public Client(int i) {
        number = i;
    }

    public static void main(String[] args) {
        Thread t = new Client(0);
        t.start();
    }

    @Override
    public void run() {
        try {

            System.out.println("***********************************************");
            System.out.println(number + "th client is started");
            System.out.println("***********************************************");
            Socket socket = null;
            DataOutputStream out = null;
            DataInputStream in = null;
            String host = "127.0.0.1";
            String message = "A4M040000800822000000000000004000000000000001101094753130037301";
            message = "A4M040000200F238669128A0900000000000048000A016434185******33240300000000000000000121717063984623723063912176011050901000000D0000\n"
                    + "0000110000000000632434185******3324=2710626********00052648954008MTutrA30MBL                           UTTARA\n"
                    + "     000050Uttara Branch ATM             7777                          DHAKA                         00120181218ITCL      MBL\n"
                    + "                       0000000000000000050FFFFFFFFFFFFFFFF01?0000000000000000000000000001***\n"
                    + "     035CC=0^PIB=1999^PIC=50^PTC=2^PTL=1^PTLC=en";

            // message= "H1)A4MÝH2)04ÝH3)000ÝF2)434185******3324ÝF3.1)030³F3.2)00³F3.3)00³ÝF4)000000000000ÝF7)1217170639ÝF11)846237ÝF12)230639ÝF13)1217ÝF18)6011ÝF19)050ÝF22)901ÝF23)000ÝF25)000ÝF28)00000000ÝF32)00000000006ÝF35)434185******3324=2710626********ÝF37)000526489540ÝF41)MTutrA30ÝF43.1)MBL                           ³F43.2)UTTARA                        ³F43.3)000³F43.4)050³F43.5)Uttara Branch ATM             ³F43.6)7777                          ³F43.7)DHAKA                         ³F43.8)001³F43.9)20181218³F43.10)ITCL      ³F43.11)MBL ³F43.12)                         ³F43.13)000³F43.14)000000000³F43.15)0000³ÝF49)050ÝF52)****************ÝF102)?ÝF105.1)000000000000³F105.2)000000000000³F105.3)0³ÝF121.1)00³F121.2)1³F121.3)***³F121.4)                ³F121.5)                ³F121.6)         ³ÝF123)CC=0^PIB=1999^PIC=50^PTC=2^PTL=1^PTLC=enÝ";
            // message="A4M080000800822000000000000004000000000000001223100659511620001";
            message = "H1)A4MÝH2)04ÝH3)000ÝF2)434185******3324ÝF3.1)030³F3.2)00³F3.3)00³ÝF4)000000000000ÝF7)1217170639ÝF11)846237ÝF12)230639ÝF13)1217ÝF18)6011ÝF19)050ÝF22)901ÝF23)000ÝF25)000ÝF28)00000000ÝF32)00000000006ÝF35)434185******3324=2710626********ÝF37)000526489540ÝF41)MTutrA30ÝF43.1)MBL                           ³F43.2)UTTARA                        ³F43.3)000³F43.4)050³F43.5)Uttara Branch ATM             ³F43.6)7777                          ³F43.7)DHAKA                         ³F43.8)001³F43.9)20181218³F43.10)ITCL      ³F43.11)MBL ³F43.12)                         ³F43.13)000³F43.14)000000000³F43.15)0000³ÝF49)050ÝF52)****************ÝF102)?ÝF105.1)000000000000³F105.2)000000000000³F105.3)0³ÝF121.1)00³F121.2)1³F121.3)***³F121.4)                ³F121.5)                ³F121.6)         ³ÝF123)CC=0^PIB=1999^PIC=50^PTC=2^PTL=1^PTLC=enÝ";

            message = "A4M040000200F238669128A0900000000000048000A016434185******33240300000000000000000121717063984623723063912176011050901000000D0000\n"
                    + "0000110000000000632434185******3324=2710626********00052648954008MTutrA30MBL                           UTTARA\n"
                    + "     000050Uttara Branch ATM             7777                          DHAKA                         00120181218ITCL      MBL\n"
                    + "                       0000000000000000050FFFFFFFFFFFFFFFF01?0000000000000000000000000001***\n"
                    + "     035CC=0^PIB=1999^PIC=50^PTC=2^PTL=1^PTLC=en";

            message = "A4M040000200F238669128A0900000000000048000A016434185******33240300000000000000000121717063984623723063912176011050901000000D0000\n"
                    + "0000110000000000632434185******3324=2710626********00052648954008MTutrA30MBL                           UTTARA\n"
                    + "     000050Uttara Branch ATM             7777                          DHAKA                         00120181218ITCL      MBL\n"
                    + "                       0000000000000000050FFFFFFFFFFFFFFFF01?0000000000000000000000000001***\n"
                    + "     035CC=0^PIB=1999^PIC=50^PTC=2^PTL=1^PTLC=en";

            message = "A4M080000200F638669128B0A00800000000064000A016434184******00330\n"
                    + "300000000000000000000000000000011407380151599213291101146011050\n"
                    + "051000091D000000000692900132434184******0033=2707201********000\n"
                    + "00001355008NRBGUL01NRBB                          DHAKA\n"
                    + "                000050HEAD OFFICE                   9999\n"
                    + "                                                 00120190114NRB\n"
                    + "B      NRBB                         00000000000000001 050840NRB\n"
                    + "BNRBB      1330110800680080810101001000000000000000000000000000\n"
                    + "0007013157800000000001***\n"
                    + "   004TC=5";

            message = "A4M080000200F638669128B0A00800000000064000A016434184******00330\n"
                    + "300000000000000000000000000000011407380151599213291101146011050\n"
                    + "051000091D000000000692900132434184******0033=2707201********000\n"
                    + "00001355008NRBGUL01NRBB                          DHAKA\n"
                    + "                000050HEAD OFFICE                   9999\n"
                    + "                                                 00120190114NRB\n"
                    + "B      NRBB                         00000000000000001 050840NRB\n"
                    + "BNRBB      1330110800680080810101001000000000000000000000000000\n"
                    + "0007013157800000000001***\n"
                    + "   004TC=5";

            message = "0200F638669128B0A00800000000064000A016434184******00330\n"
                    + "300000000000000000000000000000011407380151599213291101146011050\n"
                    + "051000091D000000000692900132434184******0033=2707201********000\n"
                    + "00001355008NRBGUL01NRBB                          DHAKA\n"
                    + "                000050HEAD OFFICE                   9999\n"
                    + "                                                 00120190114NRB\n"
                    + "B      NRBB                         00000000000000001 050840NRB\n"
                    + "BNRBB      1330110800680080810101001000000000000000000000000000\n"
                    + "0007013157800000000001***\n"
                    + "   004TC=5";

            message = "A4M080000200F638669128B0A00800000000064000A01643418400000000330300000000000000000000000000000011707263052113313173401176011050901000000D0000000006929001324341840000000033=27072011961179800000001356208NRBGUL01NRBB                          DHAKA                         000050HEAD OFFICE                   9999                                                        00120190117NRBB      NRBB                         00000000000000001 050840NRBBNRBB      13998199000013008101010010000000000000000000000000000007013157800000000001                                            004TC=5";
          //  message = "A4M040000800822000000000000004000000000000001101094753130037301";
            socket = new Socket(host, 20008);
            out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            System.out.println("connection is done and now data will be sent" + message.getBytes().length);

            //byte[] bytes = new byte[(int) message.getBytes().length];
            int length = message.getBytes().length;
            int L1 = length / 256, L2 = length - L1 * 256;
            byte[] valByte = {0, (byte) 63};
            valByte[0] = (byte) L1;
            valByte[1] = (byte) L2;

            out.write(valByte[0]);
            out.write(valByte[1]);
            out.write(message.getBytes());
            out.flush();

            System.out.println("data sending from client is done and now data will be read");

            // int count ; 
            StringBuilder responseFromServer = new StringBuilder();
            String isoMessage = "", str = "";

            L1 = (byte) in.readByte();
            L2 = (byte) in.readByte();
            System.out.println("L1 size is " + L1 + " and L2 size is " + L2);
            byte[] data = new byte[L2 + L1 * 256];
            int count = in.read(data);
            byte[] real = new byte[count + 1];
            for (int i = 0; i < count; i++) {
                real[i] = data[i];
            }
            str = new String(real);
            isoMessage = str.trim();
            System.out.println("response from server " + isoMessage);

            /*  byte[] buffer = new byte[1024];  
            while ((count = in.read(buffer)) > 0) {
               // out.write(buffer, 0, count);
               // System.out.println(buffer.toString());
               str = new String(buffer);
               responseFromServer.append(str);
            }
            isoMessage= responseFromServer.toString();
            isoMessage  = isoMessage.trim();
            System.out.println("response from server "+isoMessage); */
            out.close();
            in.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("***********************************************");
        System.out.println(number + "th client is ended");
        System.out.println("***********************************************");
    }
}
