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
public class LoginCheck {

    public static boolean checkLogin(String username, String password) {
        Connection con = DBConnection.getConnection();
        try {
            String selectSQL = "SELECT user_id FROM logdb.user_pass where user_id = ? and pass = ?";
            selectSQL = "SELECT b.user_id FROM logdb.user_mst a,logdb.user_pass b where a.user_id=b.user_id \n"
                    + "AND a.user_code=? AND b.pass=?";
            PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DBConnection.clossConnection(con);
        return false;
    }

    public static void main(String[] args) {
        boolean flag = LoginCheck.checkLogin("fardaus", "123456");
        System.out.println(flag);
    }

}
