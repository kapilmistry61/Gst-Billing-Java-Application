/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gst_billing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author kapil
 */
public class DataCon {
  
    public static  final String DB = "gst_billing";
    public static  final String USER = "root";
    public static  final String PASS = "";
    public static  final String SERVER = "localhost:3307";
    private static Connection dataCon;
    
    public static void ConnectionDB() throws Exception {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
       setDataCon(DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DB, USER, PASS));
        
    }
    
    public static  Connection getDataCon(){
        return dataCon;
    }
           
    public static void setDataCon(Connection aDataCon){
        dataCon = aDataCon;
    }
    
    public static void setAutoCommit(boolean act) throws Exception {
        dataCon.setAutoCommit(act);
    }
    
    public static int getKey(ResultSet r) throws Exception{
        r.next();
        int key = r.getInt(1);
        r.close();
        return key;        
    }
}

