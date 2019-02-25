/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sultan
 */
public class InsertIntoLogDB {
    
    String sql1 = "INSERT INTO logdb.req_log(req_type_id,req_log_id,timestamp,status,card_no,Location,decline_reason,amount,insert_date,"+
"insert_by,merchant_category_code,aquirer_fee,acq_ins_code,frd_ins_code,terminal_id,terminal_location)VALUES"
    + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";


    public static boolean insertDataIntoDB(String cardNo){
        Connection conn = DBConnection.getConnection();
         
        String sql = "INSERT INTO logdb.req_log(req_type_id,card_no,timestamp) values(2,?,sysdate())";
        try{
            PreparedStatement pstmt = conn.prepareStatement(sql); 
            pstmt.setString(1, cardNo); 
            int flag = pstmt.executeUpdate();
            System.out.print("transaction log insert into database flag is "+flag);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        DBConnection.clossConnection(conn);
        return false;
    }
    
}
