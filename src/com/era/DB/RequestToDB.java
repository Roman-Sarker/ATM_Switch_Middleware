/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.era.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author sultan
 */
public class RequestToDB {
    
    public void getReqType(String reqName){
        Connection conn = DBConnection.getConnection();
        
        try { 
            String selectSQL = "insert ";
            PreparedStatement preparedStatement = conn.prepareStatement(selectSQL);
//            preparedStatement.setLong(1, username);
//             preparedStatement.setString(2, password);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return true;
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        DBConnection.clossConnection(conn);        
    }
    
}
