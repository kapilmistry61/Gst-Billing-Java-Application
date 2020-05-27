/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gst_billing;

import java.awt.FlowLayout;
 
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;


public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        refresh();
        connect();
        Show_Invoiceinsecond_In_JTable();
        Show_Search_In_JTable();
        Show_Customers_In_JTable();
        Show_Item_In_JTable();
        Show_Tax_In_JTable();
        Show_Financialyear_In_JTable();
        Show_Invoicein_In_JTable();
        setBackground(new  Color(0));
        //customer color table
        JTable_Customers.getTableHeader().setFont(new Font("Tahome", Font.BOLD, 15));
        JTable_Customers.getTableHeader().setOpaque(false);
        JTable_Customers.getTableHeader().setBackground(Color.red);
        JTable_Customers.getTableHeader().setForeground(Color.white); 
        //Item color table
        JTable_Item.getTableHeader().setFont(new Font("Tahome", Font.BOLD, 15));
        JTable_Item.getTableHeader().setOpaque(false);
        JTable_Item.getTableHeader().setBackground(Color.red);
        JTable_Item.getTableHeader().setForeground(Color.white); 
        //Tax color table
        JTable_Tax.getTableHeader().setFont(new Font("Tahome", Font.BOLD, 15));
        JTable_Tax.getTableHeader().setOpaque(false);
        JTable_Tax.getTableHeader().setBackground(Color.red);
        JTable_Tax.getTableHeader().setForeground(Color.white);
        //Financial year color table  
        JTable_Financialyear.getTableHeader().setFont(new Font("Tahome", Font.BOLD, 15));
        JTable_Financialyear.getTableHeader().setOpaque(false);
        JTable_Financialyear.getTableHeader().setBackground(Color.red);
        JTable_Financialyear.getTableHeader().setForeground(Color.white); 
        //invoice color table  
        jTable_invoicein.getTableHeader().setFont(new Font("Tahome", Font.BOLD, 12));
        jTable_invoicein.getTableHeader().setOpaque(false);
        jTable_invoicein.getTableHeader().setBackground(Color.black);
        jTable_invoicein.getTableHeader().setForeground(Color.white);
        //Search color table  
        jTable1_search.getTableHeader().setFont(new Font("Tahome", Font.BOLD, 15));
        jTable1_search.getTableHeader().setOpaque(false);
        jTable1_search.getTableHeader().setBackground(Color.red);
        jTable1_search.getTableHeader().setForeground(Color.white);
        

        

    }
    

      
    Connection con;
    PreparedStatement pst;
    
    
    public void connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/gst_billing", "root", "");
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    
    
    public void refresh(){
        
        Date date = new Date();
        bill_date.setDate(date);
        datetimesuppy.setDate(date);
         
        DateFormat dateFormat = new SimpleDateFormat("  hh:mm aa");
    	String dateString = dateFormat.format(new Date()).toString();
    	stime.setText(dateString); 
        
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
    	String dateString1 = dateFormat1.format(new Date()).toString();
    	txt_searcht.setText(dateString1);
        
        
               try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://Localhost:3307/gst_billing","root","");
        
        String sql = "TRUNCATE TABLE invoicein";
        PreparedStatement pstmt = conn.prepareStatement (sql);
       
        pstmt.executeUpdate();
       
        Show_Invoicein_In_JTable();
      
    }
        catch(Exception e)
        {
         
        } 
    }
    
  
   
    
    //database connection
    public Connection getConnection()
    {
        Connection con = null;
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://Localhost:3307/gst_billing","root","");
         return con;
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
         return null; 
        }
        
    }
    
    

  // Display Data In JTable for customers : 
    //      1 - Fill ArrayList With The Data
    
    public ArrayList<Search> ListSearch(String ValToSearch)
    {
        ArrayList<Search> searchList  = new ArrayList<Search>();
            Connection con = getConnection();
            String query = "SELECT * FROM `invoice` WHERE CONCAT(`billno`, `billdate`, `custname`,`custcode`,`grandtotal`)LIKE'%"+ValToSearch+"%'";
            
            Statement st;
            ResultSet rs;
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query);
            Search search;
            
            while(rs.next())
            {
                search = new Search(
                        rs.getInt("billno"),
                        rs.getString("billto"),
                        rs.getString("billdate"),
                        rs.getString("taxpayable"),
                        rs.getString("custcode"),
                        rs.getString("custname"),
                        rs.getString("billaddress"),
                        rs.getString("shipaddress"),
                        rs.getString("shipcustname"),
                        rs.getString("scode"),
                        rs.getString("state"),
                        rs.getString("gstinnumber"),
                        rs.getDouble("totalcgst"),
                        rs.getDouble("totalsgst"),
                        rs.getDouble("totaltax"),
                        rs.getString("subtotal"),
                        rs.getString("vehicleno"),
                        rs.getString("tmode"),
                        rs.getString("psupply"),
                        rs.getString("datetime"),
                        rs.getString("fcharge"),
                        rs.getString("loading"),
                        rs.getString("insurance"),
                        rs.getString("other"),
                        rs.getString("note"),
                        rs.getDouble("grandtotal"),
                        rs.getString("stime"),
                        rs.getString("searchtime")
                );
                searchList.add(search);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return searchList;
         
    }
   
     //      2 - Populate The JTable for customers
    
      public void Show_Search_In_JTable()
    {
        ArrayList<Search> list = ListSearch(txt_search.getText());
        DefaultTableModel model = (DefaultTableModel)jTable1_search.getModel();
        //clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[28];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getBillno();
            row[1] = list.get(i).getBillto();
            row[2] = list.get(i).getBilldate();
            row[3] = list.get(i).getTaxpayable();
            row[4] = list.get(i).getCustcode();
            row[5] = list.get(i).getCustname();
            row[6] = list.get(i).getBilladdress();
            row[7] = list.get(i).getShipaddress();
            row[8] = list.get(i).getShipcustname();
            row[9] = list.get(i).getScode();
            row[10] = list.get(i).getState();
            row[11] = list.get(i).getGstinnumber();
            row[12] = list.get(i).getTotalcgst();
            row[13] = list.get(i).getTotalsgst();
            row[14] = list.get(i).getTotaltax();
            row[15] = list.get(i).getSubtotal();
            row[16] = list.get(i).getVehicleno();
            row[17] = list.get(i).getTmode();
            row[18] = list.get(i).getPsupply();
            row[19] = list.get(i).getDatetime();
            row[20] = list.get(i).getFcharge();
            row[21] = list.get(i).getLoading();
            row[22] = list.get(i).getInsurance();
            row[23] = list.get(i).getOther();
            row[24] = list.get(i).getNote();
            row[25] = list.get(i).getGrandtotal();
            row[26] = list.get(i).getStime();
            row[27] = list.get(i).getSearchtime();
           
            
           
            
            model.addRow(row);
        }
    
    }



  
    
  
     // Display Data In JTable for customers : 
    //      1 - Fill ArrayList With The Data
    
    public ArrayList<Customer> getCustomerList()
    {
        ArrayList<Customer> customerList  = new ArrayList<Customer>();
            Connection con = getConnection();
            String query = "SELECT * FROM customer";
            
            Statement st;
            ResultSet rs;
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query);
            Customer customer;
            
            while(rs.next())
            {
                customer = new Customer(rs.getInt("id"),rs.getString("code"),rs.getString("name"),rs.getString("address"),rs.getString("mobile"),rs.getString("phone"),rs.getString("gst"),rs.getString("state"),rs.getString("statecode"),rs.getString("remark"));
                customerList.add(customer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return customerList;
         
    }
   
     //      2 - Populate The JTable for customers
    
      public void Show_Customers_In_JTable()
    {
        ArrayList<Customer> list = getCustomerList();
        DefaultTableModel model = (DefaultTableModel)JTable_Customers.getModel();
        //clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[10];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getCode();
            row[2] = list.get(i).getName();
            row[3] = list.get(i).getAddress();
            row[4] = list.get(i).getMobile();
            row[5] = list.get(i).getPhone();
            row[6] = list.get(i).getGst();
            row[7] = list.get(i).getState();
            row[8] = list.get(i).getStatecode();
            row[9] = list.get(i).getRemark();
           
            
            model.addRow(row);
        }
    
    }
    
    
 // Display Data In JTable for Item : 
    //      1 - Fill ArrayList With The Data
    
    public ArrayList<Item> getItemList()
    {
        ArrayList<Item> itemList  = new ArrayList<Item>();
            Connection con = getConnection();
            String query = "SELECT * FROM item";
            
            Statement st;
            ResultSet rs;
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query);
            Item item;
            
            while(rs.next())
            {
                item = new Item(rs.getInt("id"),rs.getString("itemcode"),rs.getString("itemname"),rs.getString("hsncode"),rs.getString("unit"),rs.getString("price"),rs.getString("tax"),rs.getString("gstvalue"));
                itemList.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return itemList;
         
    }
    
     //      2 - Populate The JTable for item
    
      public void Show_Item_In_JTable()
    {
        ArrayList<Item> list = getItemList();
        DefaultTableModel model = (DefaultTableModel)JTable_Item.getModel();
        //clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[8];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getItemcode();
            row[2] = list.get(i).getItemname();
            row[3] = list.get(i).getHsncode();
            row[4] = list.get(i).getUnit();
            row[5] = list.get(i).getPrice();
            row[6] = list.get(i).getTax();
            row[7] = list.get(i).getGstvalue();
            model.addRow(row);
        }
    
    }
      
     // Display Data In JTable for Tax : 
    //      1 - Fill ArrayList With The Data
    
    public ArrayList<Tax> getTaxList()
    {
        ArrayList<Tax> taxList  = new ArrayList<Tax>();
            Connection con = getConnection();
            String query = "SELECT * FROM tax";
            
            Statement st;
            ResultSet rs;
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query);
            Tax tax;
            
            while(rs.next())
            {
                tax = new Tax(rs.getInt("id"),rs.getString("taxname"),rs.getString("taxvalue"));
                taxList.add(tax);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return taxList;
         
    }  
     
      public void Show_Tax_In_JTable()
    {
        ArrayList<Tax> list = getTaxList();
        DefaultTableModel model = (DefaultTableModel)JTable_Tax.getModel();
        //clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[3];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getTaxname();
            row[2] = list.get(i).getTaxvalue();
          
            model.addRow(row);
        }
    
    }
      
      
     // Display Data In JTable for Financial year : 
    //      1 - Fill ArrayList With The Data
    
    public ArrayList<Financialyear> getFinancialyearList()
    {
        ArrayList<Financialyear> financialyearList  = new ArrayList<Financialyear>();
            Connection con = getConnection();
            String query = "SELECT * FROM financialyear";
            
            Statement st;
            ResultSet rs;
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query);
            Financialyear financialyear;
            
            while(rs.next())
            {
                financialyear = new Financialyear(rs.getInt("id"),rs.getString("financialyear"));
                financialyearList.add(financialyear);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return financialyearList;
         
    }  
    
      public void Show_Financialyear_In_JTable()
    {
        ArrayList<Financialyear> list = getFinancialyearList();
        DefaultTableModel model = (DefaultTableModel)JTable_Financialyear.getModel();
        //clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[2];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getFinancialyear();
            
            model.addRow(row);
        }
    
    }
      
      
    //display data in jtale of invoice in   
      
   public ArrayList<Invoicein> getInvoiceinList()
    {
        ArrayList<Invoicein> invoiceinList  = new ArrayList<Invoicein>();
            Connection con = getConnection();
            String query = "SELECT * FROM invoicein";
            
            Statement st;
            ResultSet rs;
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query);
            Invoicein invoicein;
            
            while(rs.next())
            {
                invoicein = new Invoicein(rs.getInt("id"),rs.getString("name"),rs.getString("qty"),rs.getString("rate"),rs.getString("total"),rs.getString("discamt"),rs.getString("taxvalue"),rs.getString("taxper"),rs.getString("sgst"),rs.getString("cgst"),rs.getString("igst"),rs.getString("searchtime"));
                invoiceinList.add(invoicein);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return invoiceinList;
         
    } 
   
   
     public void Show_Invoicein_In_JTable()
    {
        ArrayList<Invoicein> list = getInvoiceinList();
        DefaultTableModel model = (DefaultTableModel)jTable_invoicein.getModel();
        //clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[13];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getId();
            row[2] = list.get(i).getName();
            row[3] = list.get(i).getQty();
            row[4] = list.get(i).getRate();
            row[5] = list.get(i).getTotal();
            row[6] = list.get(i).getDiscamt();
            row[7] = list.get(i).getTaxvalue();
            row[8] = list.get(i).getTaxper();
            row[9] = list.get(i).getSgst();
            row[10] = list.get(i).getCgst();
            row[11] = list.get(i).getIgst();
            row[12] = list.get(i).getSearchtime();
            
            model.addRow(row);
        }
    
    }
     
     
     
     
     
     
     
     
      //display data in jtale of invoiceinsecond   
      
   public ArrayList<Invoiceinsecond> listInvoiceinsecond(String ValToSearch)
    {
        ArrayList<Invoiceinsecond> invoiceinsecondList  = new ArrayList<Invoiceinsecond>();
            Connection con = getConnection();
            String query = "SELECT * FROM `invoiceinsecond` WHERE CONCAT(`searchtime`)LIKE'%"+ValToSearch+"%'";
            
            Statement st;
            ResultSet rs;
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query);
            Invoiceinsecond invoiceinsecond;
            
            while(rs.next())
            {
                invoiceinsecond = new Invoiceinsecond(rs.getInt("id"),rs.getString("name"),rs.getString("qty"),rs.getString("rate"),rs.getString("total"),rs.getString("discamt"),rs.getString("taxvalue"),rs.getString("taxper"),rs.getString("sgst"),rs.getString("cgst"),rs.getString("igst"),rs.getString("searchtime"));
                invoiceinsecondList.add(invoiceinsecond);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return invoiceinsecondList;
         
    } 
   
   
     public void Show_Invoiceinsecond_In_JTable()
    {
        ArrayList<Invoiceinsecond> list = listInvoiceinsecond(txt_searcht.getText());
        DefaultTableModel model = (DefaultTableModel)jTable_invoicein.getModel();
        //clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[13];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getId();
            row[2] = list.get(i).getName();
            row[3] = list.get(i).getQty();
            row[4] = list.get(i).getRate();
            row[5] = list.get(i).getTotal();
            row[6] = list.get(i).getDiscamt();
            row[7] = list.get(i).getTaxvalue();
            row[8] = list.get(i).getTaxper();
            row[9] = list.get(i).getSgst();
            row[10] = list.get(i).getCgst();
            row[11] = list.get(i).getIgst();
            row[12] = list.get(i).getSearchtime();
            
            model.addRow(row);
        }
    
    } 
     
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        hidebar = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bill_no = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        bill_date = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jComboBox_payable = new javax.swing.JComboBox<>();
        txt_icode = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        list = new javax.swing.JList<>();
        jLabel23 = new javax.swing.JLabel();
        txt_custname = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txt_iname = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txt_istatecode = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txt_istate = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txt_igst = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        txt_des = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txt_qty = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txt_unitprice = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        disc = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        totalprice = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txt_icode1 = new javax.swing.JTextField();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        list2 = new javax.swing.JList<>();
        jLabel34 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable_invoicein = new javax.swing.JTable();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        totalcgst = new javax.swing.JTextField();
        totalsgst = new javax.swing.JTextField();
        totalc = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        totaltaxv = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        vchnumber = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        transmode = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        placesupply = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        datetimesuppy = new com.toedter.calendar.JDateChooser();
        jLabel49 = new javax.swing.JLabel();
        fcharge = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        remark = new javax.swing.JEditorPane();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        loadingcha = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        unsu = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        othch = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        txt_shipadd = new javax.swing.JEditorPane();
        jScrollPane12 = new javax.swing.JScrollPane();
        txt_billadd = new javax.swing.JEditorPane();
        grandtotal = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        igst = new javax.swing.JTextField();
        sgst = new javax.swing.JTextField();
        cgst = new javax.swing.JTextField();
        totaltax = new javax.swing.JTextField();
        taxval = new javax.swing.JTextField();
        taxred = new javax.swing.JTextField();
        txt_invoiceid = new javax.swing.JTextField();
        a1 = new javax.swing.JTextField();
        a2 = new javax.swing.JTextField();
        a3 = new javax.swing.JTextField();
        a4 = new javax.swing.JTextField();
        a5 = new javax.swing.JTextField();
        a6 = new javax.swing.JTextField();
        a7 = new javax.swing.JTextField();
        a8 = new javax.swing.JTextField();
        a9 = new javax.swing.JTextField();
        a10 = new javax.swing.JTextField();
        a11 = new javax.swing.JTextField();
        a12 = new javax.swing.JTextField();
        a13 = new javax.swing.JTextField();
        x1 = new javax.swing.JTextField();
        jButton19 = new javax.swing.JButton();
        x2 = new javax.swing.JTextField();
        x3 = new javax.swing.JTextField();
        x4 = new javax.swing.JTextField();
        x5 = new javax.swing.JTextField();
        x6 = new javax.swing.JTextField();
        x7 = new javax.swing.JTextField();
        x8 = new javax.swing.JTextField();
        x9 = new javax.swing.JTextField();
        x10 = new javax.swing.JTextField();
        a14 = new javax.swing.JTextField();
        totalrate = new javax.swing.JTextField();
        qtycount = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        stime = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        txt_financialyear = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        JTable_Financialyear = new javax.swing.JTable();
        jButton21 = new javax.swing.JButton();
        financialyear = new javax.swing.JLabel();
        txt_financialid = new javax.swing.JTextField();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        txt_idtax2 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txt_taxname = new javax.swing.JTextField();
        txt_taxvalue = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        JTable_Tax = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        taxname = new javax.swing.JLabel();
        taxvalue = new javax.swing.JLabel();
        txt_id1 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        txt_idtax = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        JTable_Item = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txt_itemcode = new javax.swing.JTextField();
        txt_itemname = new javax.swing.JTextField();
        txt_hsncode = new javax.swing.JTextField();
        txt_unit = new javax.swing.JTextField();
        txt_price = new javax.swing.JTextField();
        jComboBox_tax = new javax.swing.JComboBox<>();
        itemname = new javax.swing.JLabel();
        itemcode = new javax.swing.JLabel();
        hsncode = new javax.swing.JLabel();
        price = new javax.swing.JLabel();
        unit = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        txt_itemid = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        txt_valuety = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        valuetax = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_code = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        txt_mob = new javax.swing.JTextField();
        txt_phone = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Customers = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_gst = new javax.swing.JTextField();
        txt_state = new javax.swing.JTextField();
        txt_scode = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        code = new javax.swing.JLabel();
        pass = new javax.swing.JLabel();
        add = new javax.swing.JLabel();
        mob = new javax.swing.JLabel();
        gst = new javax.swing.JLabel();
        state = new javax.swing.JLabel();
        add4 = new javax.swing.JLabel();
        scode = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        txt_add = new javax.swing.JEditorPane();
        jScrollPane14 = new javax.swing.JScrollPane();
        txt_remark = new javax.swing.JEditorPane();
        jPanel9 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton27 = new javax.swing.JButton();
        txt_todate = new com.toedter.calendar.JDateChooser();
        txt_fdate = new com.toedter.calendar.JDateChooser();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        txt_todate1 = new com.toedter.calendar.JDateChooser();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        txt_fdate1 = new com.toedter.calendar.JDateChooser();
        jTextField2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton28 = new javax.swing.JButton();
        jLabel61 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1_search = new javax.swing.JTable();
        jPanel19 = new javax.swing.JPanel();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        txt_search = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        txt_searcht = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        b2.setBackground(new java.awt.Color(0, 0, 0));
        b2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        b2.setForeground(new java.awt.Color(255, 255, 255));
        b2.setText("Invoice");
        b2.setBorder(null);
        b2.setContentAreaFilled(false);
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });

        b3.setBackground(new java.awt.Color(0, 0, 0));
        b3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        b3.setForeground(new java.awt.Color(255, 255, 255));
        b3.setText("Financial Year");
        b3.setBorder(null);
        b3.setContentAreaFilled(false);
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });

        b4.setBackground(new java.awt.Color(0, 0, 0));
        b4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        b4.setForeground(new java.awt.Color(255, 255, 255));
        b4.setText("Tax");
        b4.setBorder(null);
        b4.setContentAreaFilled(false);
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });

        b6.setBackground(new java.awt.Color(0, 0, 0));
        b6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        b6.setForeground(new java.awt.Color(255, 255, 255));
        b6.setText("Customer");
        b6.setBorder(null);
        b6.setContentAreaFilled(false);
        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b6ActionPerformed(evt);
            }
        });

        b5.setBackground(new java.awt.Color(0, 0, 0));
        b5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        b5.setForeground(new java.awt.Color(255, 255, 255));
        b5.setText("Item");
        b5.setBorder(null);
        b5.setContentAreaFilled(false);
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });

        b7.setBackground(new java.awt.Color(0, 0, 0));
        b7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        b7.setForeground(new java.awt.Color(255, 255, 255));
        b7.setText("Report");
        b7.setBorder(null);
        b7.setContentAreaFilled(false);
        b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b7ActionPerformed(evt);
            }
        });

        b1.setBackground(new java.awt.Color(0, 0, 0));
        b1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        b1.setForeground(new java.awt.Color(255, 255, 255));
        b1.setText("Log-out");
        b1.setBorder(null);
        b1.setContentAreaFilled(false);
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 857, Short.MAX_VALUE)
                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1380, 30));

        hidebar.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout hidebarLayout = new javax.swing.GroupLayout(hidebar);
        hidebar.setLayout(hidebarLayout);
        hidebarLayout.setHorizontalGroup(
            hidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1380, Short.MAX_VALUE)
        );
        hidebarLayout.setVerticalGroup(
            hidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(hidebar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1380, 30));

        jTabbedPane1.setBackground(new java.awt.Color(0, 0, 0));
        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Home", "Away" }));
        jComboBox1.setBorder(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Bill To");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Bill No");

        bill_no.setEditable(false);
        bill_no.setBackground(new java.awt.Color(255, 255, 255));
        bill_no.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        bill_no.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Tax is Payable on Reverse charge");

        bill_date.setBackground(new java.awt.Color(255, 255, 255));
        bill_date.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bill_date.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Bill Date");

        jComboBox_payable.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBox_payable.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Yes", "No" }));
        jComboBox_payable.setBorder(null);

        txt_icode.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_icode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_icode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_icodeKeyReleased(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel22.setText("Cust Code");

        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        list.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        list.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                listMousePressed(evt);
            }
        });
        jLayeredPane1.add(list, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 190, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel23.setText("Shipping Cust Name");
        jLayeredPane1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 150, 30));

        txt_custname.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_custname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jLayeredPane1.add(txt_custname, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 190, 30));

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel26.setText("Cust Name");

        txt_iname.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_iname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel27.setText("Billing Address");

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel29.setText("Shipping Address");

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel30.setText("State Code");

        txt_istatecode.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_istatecode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel31.setText("State");

        txt_istate.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_istate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel32.setText("GSTIN Number");

        txt_igst.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_igst.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jPanel15.setBackground(new java.awt.Color(103, 117, 226));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Item Code  ");

        txt_des.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_des.setForeground(new java.awt.Color(255, 0, 0));
        txt_des.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Description");

        txt_qty.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_qty.setForeground(new java.awt.Color(255, 0, 0));
        txt_qty.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_qtyKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_qtyKeyReleased(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Qty");

        txt_unitprice.setEditable(false);
        txt_unitprice.setBackground(new java.awt.Color(255, 255, 255));
        txt_unitprice.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_unitprice.setForeground(new java.awt.Color(255, 0, 0));
        txt_unitprice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Unit Price");

        disc.setEditable(false);
        disc.setBackground(new java.awt.Color(255, 255, 255));
        disc.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        disc.setForeground(new java.awt.Color(255, 0, 0));
        disc.setText("0");
        disc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Disc %");

        totalprice.setEditable(false);
        totalprice.setBackground(new java.awt.Color(255, 255, 255));
        totalprice.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        totalprice.setForeground(new java.awt.Color(255, 0, 0));
        totalprice.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        totalprice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                totalpriceKeyPressed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Total Price");

        txt_icode1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_icode1.setForeground(new java.awt.Color(255, 0, 0));
        txt_icode1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_icode1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_icode1ActionPerformed(evt);
            }
        });
        txt_icode1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_icode1KeyReleased(evt);
            }
        });

        jLayeredPane3.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        list2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        list2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        list2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                list2MousePressed(evt);
            }
        });
        jLayeredPane3.add(list2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 130, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLayeredPane3.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 10, 10));

        jTable_invoicein.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jTable_invoicein.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable_invoicein.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SNO", "SN", "NAME", "QTY", "RATE", "TOTAL", "DISC AMT", "TAX VALUE", "TAX%", "SGST", "CGST", "IGST", "scgstrate", "searchtime", "GSTNUMBER", "TAXPAYABLEl", "INVOICENUMBERl", "DATE", "TRANSMODE", "VECHNUMB", "DATETIME", "PLACESUPPLY", "PARTYNAME", "BILLADDRESS", "STATE", "SCODE", "SHIPADDRESS", "REMARK", "SGSTTOT", "CGSTTOT", "IGSTTOT", "GRANDTOTAL", "TOTALTAX", "SU BTOTAL", "Freight", "Loading", "Insurance", "Othercharge", "unknow", "stime"
            }
        ));
        jTable_invoicein.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTable_invoiceinAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jTable_invoicein.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_invoiceinMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jTable_invoicein);
        if (jTable_invoicein.getColumnModel().getColumnCount() > 0) {
            jTable_invoicein.getColumnModel().getColumn(1).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(1).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(1).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(2).setMinWidth(300);
            jTable_invoicein.getColumnModel().getColumn(2).setPreferredWidth(300);
            jTable_invoicein.getColumnModel().getColumn(2).setMaxWidth(300);
            jTable_invoicein.getColumnModel().getColumn(11).setMinWidth(45);
            jTable_invoicein.getColumnModel().getColumn(11).setPreferredWidth(45);
            jTable_invoicein.getColumnModel().getColumn(11).setMaxWidth(45);
            jTable_invoicein.getColumnModel().getColumn(12).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(12).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(12).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(13).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(13).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(13).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(14).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(14).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(14).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(15).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(15).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(15).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(16).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(16).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(16).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(17).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(17).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(17).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(18).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(18).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(18).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(19).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(19).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(19).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(20).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(20).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(20).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(21).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(21).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(21).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(22).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(22).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(22).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(23).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(23).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(23).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(24).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(24).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(24).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(25).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(25).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(25).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(26).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(26).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(26).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(27).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(27).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(27).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(28).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(28).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(28).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(29).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(29).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(29).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(30).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(30).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(30).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(31).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(31).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(31).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(32).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(32).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(32).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(33).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(33).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(33).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(34).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(34).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(34).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(35).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(35).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(35).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(36).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(36).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(36).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(37).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(37).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(37).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(38).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(38).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(38).setMaxWidth(0);
            jTable_invoicein.getColumnModel().getColumn(39).setMinWidth(0);
            jTable_invoicein.getColumnModel().getColumn(39).setPreferredWidth(0);
            jTable_invoicein.getColumnModel().getColumn(39).setMaxWidth(0);
        }

        jLayeredPane3.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1020, 130));

        jButton16.setBackground(new java.awt.Color(255, 255, 255));
        jButton16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton16.setText("+");
        jButton16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(255, 255, 255));
        jButton17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton17.setText("-");
        jButton17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_icode1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addComponent(txt_des, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(215, 215, 215)))
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(txt_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_unitprice, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(disc, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(totalprice, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14))))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLayeredPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1040, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_des, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_unitprice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(disc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalprice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_icode1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jLayeredPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel40.setText(" Total CGST");

        totalcgst.setEditable(false);
        totalcgst.setBackground(new java.awt.Color(255, 255, 255));
        totalcgst.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        totalcgst.setText("0");
        totalcgst.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        totalsgst.setEditable(false);
        totalsgst.setBackground(new java.awt.Color(255, 255, 255));
        totalsgst.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        totalsgst.setText("0");
        totalsgst.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        totalc.setEditable(false);
        totalc.setBackground(new java.awt.Color(255, 255, 255));
        totalc.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        totalc.setText("0");
        totalc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(255, 255, 255));
        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextField5.setText("0");
        jTextField5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        totaltaxv.setEditable(false);
        totaltaxv.setBackground(new java.awt.Color(255, 255, 255));
        totaltaxv.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        totaltaxv.setText("0");
        totaltaxv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jTextField7.setEditable(false);
        jTextField7.setBackground(new java.awt.Color(255, 255, 255));
        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextField7.setText("0");
        jTextField7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel41.setText(" Total IGST");

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel42.setText(" Total Disc");

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel43.setText(" Total Tax");

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel44.setText(" Total SGST");

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel45.setText(" Total");

        vchnumber.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        vchnumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel46.setText("Vehicle No");

        transmode.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        transmode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel47.setText("Transport Mode");

        placesupply.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        placesupply.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel48.setText("Place of Supply");

        datetimesuppy.setBackground(new java.awt.Color(255, 255, 255));
        datetimesuppy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        datetimesuppy.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel49.setText("Datetime of Supply");

        fcharge.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        fcharge.setText("0");
        fcharge.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        fcharge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fchargeKeyPressed(evt);
            }
        });

        remark.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        remark.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jScrollPane10.setViewportView(remark);

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel50.setText("Freight Charge");

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel51.setText("Loading & Packing");

        loadingcha.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        loadingcha.setText("0");
        loadingcha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        loadingcha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                loadingchaKeyPressed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel52.setText("Insurance");

        unsu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        unsu.setText("0");
        unsu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        unsu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                unsuKeyPressed(evt);
            }
        });

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel53.setText("Other Charge");

        othch.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        othch.setText("0");
        othch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        othch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                othchKeyPressed(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel54.setText("Note");

        txt_shipadd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_shipadd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jScrollPane11.setViewportView(txt_shipadd);

        txt_billadd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_billadd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jScrollPane12.setViewportView(txt_billadd);

        grandtotal.setEditable(false);
        grandtotal.setBackground(new java.awt.Color(255, 255, 255));
        grandtotal.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        grandtotal.setText("0");
        grandtotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel55.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel55.setText("Grand Total");

        igst.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        igst.setText("0");
        igst.setBorder(null);

        sgst.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        sgst.setBorder(null);

        cgst.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        cgst.setBorder(null);

        totaltax.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        totaltax.setBorder(null);

        taxval.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        taxval.setBorder(null);

        taxred.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        taxred.setBorder(null);

        txt_invoiceid.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        txt_invoiceid.setBorder(null);

        a1.setBorder(null);

        a2.setBorder(null);

        a3.setBorder(null);

        a4.setBorder(null);

        a5.setBorder(null);

        a6.setBorder(null);

        a7.setBorder(null);

        a8.setBorder(null);

        a9.setBorder(null);

        a10.setBorder(null);

        a11.setBorder(null);

        a12.setBorder(null);

        a13.setBorder(null);

        x1.setBorder(null);

        jButton19.setBackground(new java.awt.Color(255, 255, 255));
        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/print.png"))); // NOI18N
        jButton19.setBorder(null);
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        x2.setBorder(null);

        x3.setBorder(null);

        x4.setBorder(null);

        x5.setBorder(null);

        x6.setBorder(null);

        x7.setBorder(null);

        x8.setBorder(null);

        x9.setBorder(null);

        x10.setBorder(null);

        a14.setBorder(null);

        totalrate.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        totalrate.setBorder(null);
        totalrate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalrateActionPerformed(evt);
            }
        });

        qtycount.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        qtycount.setBorder(null);
        qtycount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qtycountActionPerformed(evt);
            }
        });

        jButton18.setBackground(new java.awt.Color(255, 255, 255));
        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/save.png"))); // NOI18N
        jButton18.setBorder(null);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton20.setBackground(new java.awt.Color(255, 255, 255));
        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/new (3).png"))); // NOI18N
        jButton20.setBorder(null);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton26.setBackground(new java.awt.Color(255, 255, 255));
        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/clear.png"))); // NOI18N
        jButton26.setBorder(null);
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton29.setBackground(new java.awt.Color(255, 255, 255));
        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/find.png"))); // NOI18N
        jButton29.setBorder(null);
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton30.setBackground(new java.awt.Color(255, 255, 255));
        jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/update (1).png"))); // NOI18N
        jButton30.setBorder(null);

        jButton31.setBackground(new java.awt.Color(255, 255, 255));
        jButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/delete.png"))); // NOI18N
        jButton31.setBorder(null);
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        stime.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        stime.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_icode, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_iname, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_istatecode, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(bill_no, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(bill_date, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jComboBox_payable, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel31)
                                            .addComponent(txt_istate, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(42, 42, 42)
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel13Layout.createSequentialGroup()
                                                .addComponent(jLabel32)
                                                .addGap(50, 50, 50)
                                                .addComponent(a1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(a2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(a3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(37, 37, 37)
                                                .addComponent(a4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(25, 25, 25)
                                                .addComponent(a5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(25, 25, 25)
                                                .addComponent(a6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(25, 25, 25)
                                                .addComponent(a7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(a8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(a9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(a10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29))
                                            .addGroup(jPanel13Layout.createSequentialGroup()
                                                .addComponent(txt_igst, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(37, 37, 37)
                                                .addComponent(x1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(x2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(37, 37, 37)
                                                .addComponent(x3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(25, 25, 25)
                                                .addComponent(x4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(25, 25, 25)
                                                .addComponent(x5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(25, 25, 25)
                                                .addComponent(x6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(26, 26, 26)))
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel13Layout.createSequentialGroup()
                                                .addComponent(x7)
                                                .addGap(25, 25, 25)
                                                .addComponent(x8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(25, 25, 25)
                                                .addComponent(x9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(9, 9, 9))
                                            .addGroup(jPanel13Layout.createSequentialGroup()
                                                .addComponent(a11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(a12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(a13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(384, 384, 384)
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(x10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(a14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(15, 15, 15))))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(fcharge, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                .addComponent(vchnumber, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(transmode, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(placesupply, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 93, Short.MAX_VALUE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(loadingcha, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(unsu)
                                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(othch, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(datetimesuppy, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(stime, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(igst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(totalcgst, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel13Layout.createSequentialGroup()
                                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel43, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel42, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                            .addGroup(jPanel13Layout.createSequentialGroup()
                                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(4, 4, 4)))
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(totalsgst, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(totaltaxv, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(totalc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(grandtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(taxred, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(taxval, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(totaltax, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cgst, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(sgst, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_invoiceid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(23, 23, 23)
                                        .addComponent(qtycount, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(totalrate, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(21, 21, 21))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jButton20)
                        .addGap(30, 30, 30)
                        .addComponent(jButton18)
                        .addGap(30, 30, 30)
                        .addComponent(jButton19)
                        .addGap(30, 30, 30)
                        .addComponent(jButton29)
                        .addGap(30, 30, 30)
                        .addComponent(jButton30)
                        .addGap(30, 30, 30)
                        .addComponent(jButton26)
                        .addGap(30, 30, 30)
                        .addComponent(jButton31)
                        .addGap(150, 150, 150))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBox_payable, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(2, 2, 2))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(bill_date, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bill_no, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, 0)
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txt_iname, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_icode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel32)
                                            .addComponent(jLabel31)))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(a1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(a13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(a12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(a11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(a10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(a9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(a8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(a7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(a6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(a5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(a4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(a3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(a2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(a14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, 0)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_istatecode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_istate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_igst, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(x1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(x10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(x9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(x8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(x7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(x6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(x5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(x4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(x3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(x2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalcgst, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel44, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalsgst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(totaltaxv, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(totalc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(datetimesuppy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                                    .addComponent(igst, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(stime, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(vchnumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(transmode, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel13Layout.createSequentialGroup()
                                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(placesupply, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(fcharge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(loadingcha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(unsu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(othch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(taxred, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(taxval, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totaltax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cgst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sgst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(qtycount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalrate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_invoiceid, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(grandtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton18)
                            .addComponent(jButton20)
                            .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton29, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton26)
                                .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 69, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab2", jPanel4);

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel14.setPreferredSize(new java.awt.Dimension(1179, 563));

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel28.setText("Financial Year");

        txt_financialyear.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_financialyear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_financialyear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_financialyearKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_financialyearKeyReleased(evt);
            }
        });

        JTable_Financialyear.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JTable_Financialyear.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTable_Financialyear.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Financial Year"
            }
        ));
        JTable_Financialyear.setGridColor(new java.awt.Color(0, 0, 0));
        JTable_Financialyear.setRowHeight(24);
        JTable_Financialyear.setSelectionBackground(new java.awt.Color(103, 117, 226));
        JTable_Financialyear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_FinancialyearMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(JTable_Financialyear);
        if (JTable_Financialyear.getColumnModel().getColumnCount() > 0) {
            JTable_Financialyear.getColumnModel().getColumn(0).setMinWidth(0);
            JTable_Financialyear.getColumnModel().getColumn(0).setPreferredWidth(0);
            JTable_Financialyear.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jButton21.setBackground(new java.awt.Color(255, 255, 255));
        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/save.png"))); // NOI18N
        jButton21.setBorder(null);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        financialyear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        financialyear.setForeground(new java.awt.Color(255, 0, 51));
        financialyear.setText(" ");

        txt_financialid.setBorder(null);
        txt_financialid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_financialidActionPerformed(evt);
            }
        });

        jButton22.setBackground(new java.awt.Color(255, 255, 255));
        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/delete.png"))); // NOI18N
        jButton22.setBorder(null);
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setBackground(new java.awt.Color(255, 255, 255));
        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/update (1).png"))); // NOI18N
        jButton23.setBorder(null);
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setBackground(new java.awt.Color(255, 255, 255));
        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/clear.png"))); // NOI18N
        jButton24.setBorder(null);
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton25.setBackground(new java.awt.Color(255, 255, 255));
        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/new (3).png"))); // NOI18N
        jButton25.setBorder(null);
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGap(146, 146, 146)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(financialyear, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_financialyear, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txt_financialid, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46)
                                .addComponent(txt_idtax2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton25)
                        .addGap(18, 18, 18)
                        .addComponent(jButton21)
                        .addGap(18, 18, 18)
                        .addComponent(jButton22)
                        .addGap(18, 18, 18)
                        .addComponent(jButton23)
                        .addGap(18, 18, 18)
                        .addComponent(jButton24)
                        .addGap(82, 82, 82)))
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_financialyear, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(financialyear, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_financialid, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(txt_idtax2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab3", jPanel5);

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.setPreferredSize(new java.awt.Dimension(1179, 563));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setText("Tax Name");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setText("Tax Value");

        txt_taxname.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_taxname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_taxname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_taxnameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_taxnameKeyReleased(evt);
            }
        });

        txt_taxvalue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_taxvalue.setText("0");
        txt_taxvalue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_taxvalue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_taxvalueActionPerformed(evt);
            }
        });
        txt_taxvalue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_taxvalueKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_taxvalueKeyReleased(evt);
            }
        });

        JTable_Tax.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JTable_Tax.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTable_Tax.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Tax Name", "Tax Value"
            }
        ));
        JTable_Tax.setGridColor(new java.awt.Color(0, 0, 0));
        JTable_Tax.setRowHeight(24);
        JTable_Tax.setSelectionBackground(new java.awt.Color(103, 117, 226));
        JTable_Tax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_TaxMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(JTable_Tax);
        if (JTable_Tax.getColumnModel().getColumnCount() > 0) {
            JTable_Tax.getColumnModel().getColumn(0).setMinWidth(0);
            JTable_Tax.getColumnModel().getColumn(0).setPreferredWidth(0);
            JTable_Tax.getColumnModel().getColumn(0).setMaxWidth(0);
            JTable_Tax.getColumnModel().getColumn(2).setMinWidth(0);
            JTable_Tax.getColumnModel().getColumn(2).setPreferredWidth(0);
            JTable_Tax.getColumnModel().getColumn(2).setMaxWidth(0);
            JTable_Tax.getColumnModel().getColumn(2).setHeaderValue("Tax Value");
        }

        jButton11.setBackground(new java.awt.Color(255, 255, 255));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/save.png"))); // NOI18N
        jButton11.setBorder(null);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        taxname.setBackground(new java.awt.Color(255, 255, 255));
        taxname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        taxname.setForeground(new java.awt.Color(255, 0, 51));
        taxname.setText(" ");

        taxvalue.setBackground(new java.awt.Color(255, 255, 255));
        taxvalue.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        taxvalue.setForeground(new java.awt.Color(255, 0, 51));
        taxvalue.setText(" ");

        txt_id1.setBorder(null);

        jButton12.setBackground(new java.awt.Color(255, 255, 255));
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/delete.png"))); // NOI18N
        jButton12.setBorder(null);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(255, 255, 255));
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/update (1).png"))); // NOI18N
        jButton13.setBorder(null);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(255, 255, 255));
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/clear.png"))); // NOI18N
        jButton14.setBorder(null);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(255, 255, 255));
        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/new (3).png"))); // NOI18N
        jButton15.setBorder(null);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(146, 146, 146)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                                .addGap(67, 67, 67)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(taxname, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_taxname, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_taxvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(taxvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(txt_idtax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_id1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap(117, Short.MAX_VALUE)
                        .addComponent(jButton15)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11)
                        .addGap(18, 18, 18)
                        .addComponent(jButton12)
                        .addGap(18, 18, 18)
                        .addComponent(jButton13)
                        .addGap(18, 18, 18)
                        .addComponent(jButton14)
                        .addGap(77, 77, 77)))
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_taxname, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taxname, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_taxvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taxvalue, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_id1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txt_idtax, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jScrollPane5)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab4", jPanel6);

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        JTable_Item.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JTable_Item.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTable_Item.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "ITEM CODE", "ITEM NAME", "HCODE", "UNIT", "PRICE", "Tax value", "Tax"
            }
        ));
        JTable_Item.setGridColor(new java.awt.Color(0, 0, 0));
        JTable_Item.setRowHeight(24);
        JTable_Item.setSelectionBackground(new java.awt.Color(103, 117, 226));
        JTable_Item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_ItemMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(JTable_Item);
        if (JTable_Item.getColumnModel().getColumnCount() > 0) {
            JTable_Item.getColumnModel().getColumn(0).setMinWidth(0);
            JTable_Item.getColumnModel().getColumn(0).setPreferredWidth(0);
            JTable_Item.getColumnModel().getColumn(0).setMaxWidth(0);
            JTable_Item.getColumnModel().getColumn(1).setMinWidth(160);
            JTable_Item.getColumnModel().getColumn(1).setPreferredWidth(160);
            JTable_Item.getColumnModel().getColumn(1).setMaxWidth(160);
            JTable_Item.getColumnModel().getColumn(3).setMinWidth(0);
            JTable_Item.getColumnModel().getColumn(3).setPreferredWidth(0);
            JTable_Item.getColumnModel().getColumn(3).setMaxWidth(0);
            JTable_Item.getColumnModel().getColumn(4).setMinWidth(0);
            JTable_Item.getColumnModel().getColumn(4).setPreferredWidth(0);
            JTable_Item.getColumnModel().getColumn(4).setMaxWidth(0);
            JTable_Item.getColumnModel().getColumn(5).setMinWidth(0);
            JTable_Item.getColumnModel().getColumn(5).setPreferredWidth(0);
            JTable_Item.getColumnModel().getColumn(5).setMaxWidth(0);
            JTable_Item.getColumnModel().getColumn(6).setMinWidth(0);
            JTable_Item.getColumnModel().getColumn(6).setPreferredWidth(0);
            JTable_Item.getColumnModel().getColumn(6).setMaxWidth(0);
            JTable_Item.getColumnModel().getColumn(7).setMinWidth(0);
            JTable_Item.getColumnModel().getColumn(7).setPreferredWidth(0);
            JTable_Item.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Item Code");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("HSN Code");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Item Name");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("Price");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setText("Tax Name");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("Unit");

        txt_itemcode.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_itemcode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_itemcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_itemcodeKeyReleased(evt);
            }
        });

        txt_itemname.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_itemname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_itemname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_itemnameKeyReleased(evt);
            }
        });

        txt_hsncode.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_hsncode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_hsncode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_hsncodeKeyReleased(evt);
            }
        });

        txt_unit.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_unit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_unit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_unitKeyReleased(evt);
            }
        });

        txt_price.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_price.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_priceKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_priceKeyReleased(evt);
            }
        });

        jComboBox_tax.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox_tax.setBorder(null);

        itemname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        itemname.setForeground(new java.awt.Color(255, 0, 51));
        itemname.setText(" ");

        itemcode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        itemcode.setForeground(new java.awt.Color(255, 0, 51));
        itemcode.setText(" ");

        hsncode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        hsncode.setForeground(new java.awt.Color(255, 0, 51));
        hsncode.setText(" ");

        price.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        price.setForeground(new java.awt.Color(255, 0, 51));
        price.setText(" ");

        unit.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        unit.setForeground(new java.awt.Color(255, 0, 51));
        unit.setText(" ");

        jButton6.setBackground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/save.png"))); // NOI18N
        jButton6.setBorder(null);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/update (1).png"))); // NOI18N
        jButton7.setBorder(null);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/delete.png"))); // NOI18N
        jButton8.setBorder(null);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/clear.png"))); // NOI18N
        jButton9.setBorder(null);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        txt_itemid.setBorder(null);

        jButton10.setBackground(new java.awt.Color(255, 255, 255));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/new (3).png"))); // NOI18N
        jButton10.setBorder(null);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        txt_valuety.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_valuety.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_valuety.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_valuetyKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_valuetyKeyReleased(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel58.setText("Tax Value");

        valuetax.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        valuetax.setForeground(new java.awt.Color(255, 0, 51));
        valuetax.setText(" ");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton10))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)))
                        .addGap(16, 16, 16)
                        .addComponent(jButton6)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(unit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_price)
                                    .addComponent(txt_itemcode)
                                    .addComponent(txt_itemname)
                                    .addComponent(txt_hsncode)
                                    .addComponent(txt_unit)
                                    .addComponent(jComboBox_tax, 0, 420, Short.MAX_VALUE)
                                    .addComponent(itemcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(itemname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(hsncode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(price, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txt_valuety, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(valuetax, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(txt_itemid, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(24, 24, 24)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_itemcode, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(itemcode, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_itemname, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(itemname, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_hsncode, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(hsncode, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_unit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(unit, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_tax, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_valuety, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(valuetax, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(txt_itemid, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab5", jPanel7);

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel10.setPreferredSize(new java.awt.Dimension(1179, 563));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("GSTIN Number");

        txt_code.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_code.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_code.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_codeKeyReleased(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Address");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Name");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Mobile");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Phone");

        txt_name.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_nameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_nameKeyReleased(evt);
            }
        });

        txt_mob.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_mob.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_mob.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_mobKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_mobKeyReleased(evt);
            }
        });

        txt_phone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_phone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_phone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_phoneKeyPressed(evt);
            }
        });

        JTable_Customers.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        JTable_Customers.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        JTable_Customers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Code", "Name", "Address", "Mobile", "Phone", "gst", "state", "statecode", "remark"
            }
        ));
        JTable_Customers.setGridColor(new java.awt.Color(0, 0, 0));
        JTable_Customers.setRowHeight(24);
        JTable_Customers.setSelectionBackground(new java.awt.Color(103, 117, 226));
        JTable_Customers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_CustomersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Customers);
        if (JTable_Customers.getColumnModel().getColumnCount() > 0) {
            JTable_Customers.getColumnModel().getColumn(0).setMinWidth(0);
            JTable_Customers.getColumnModel().getColumn(0).setPreferredWidth(0);
            JTable_Customers.getColumnModel().getColumn(0).setMaxWidth(0);
            JTable_Customers.getColumnModel().getColumn(2).setMinWidth(0);
            JTable_Customers.getColumnModel().getColumn(2).setPreferredWidth(0);
            JTable_Customers.getColumnModel().getColumn(2).setMaxWidth(0);
            JTable_Customers.getColumnModel().getColumn(3).setMinWidth(0);
            JTable_Customers.getColumnModel().getColumn(3).setPreferredWidth(0);
            JTable_Customers.getColumnModel().getColumn(3).setMaxWidth(0);
            JTable_Customers.getColumnModel().getColumn(4).setMinWidth(0);
            JTable_Customers.getColumnModel().getColumn(4).setPreferredWidth(0);
            JTable_Customers.getColumnModel().getColumn(4).setMaxWidth(0);
            JTable_Customers.getColumnModel().getColumn(5).setMinWidth(0);
            JTable_Customers.getColumnModel().getColumn(5).setPreferredWidth(0);
            JTable_Customers.getColumnModel().getColumn(5).setMaxWidth(0);
            JTable_Customers.getColumnModel().getColumn(6).setMinWidth(0);
            JTable_Customers.getColumnModel().getColumn(6).setPreferredWidth(0);
            JTable_Customers.getColumnModel().getColumn(6).setMaxWidth(0);
            JTable_Customers.getColumnModel().getColumn(7).setMinWidth(0);
            JTable_Customers.getColumnModel().getColumn(7).setPreferredWidth(0);
            JTable_Customers.getColumnModel().getColumn(7).setMaxWidth(0);
            JTable_Customers.getColumnModel().getColumn(8).setMinWidth(0);
            JTable_Customers.getColumnModel().getColumn(8).setPreferredWidth(0);
            JTable_Customers.getColumnModel().getColumn(8).setMaxWidth(0);
            JTable_Customers.getColumnModel().getColumn(9).setMinWidth(0);
            JTable_Customers.getColumnModel().getColumn(9).setPreferredWidth(0);
            JTable_Customers.getColumnModel().getColumn(9).setMaxWidth(0);
        }

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Code");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("State Code");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("State");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Remark");

        txt_gst.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_gst.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_gst.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_gstKeyReleased(evt);
            }
        });

        txt_state.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_state.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_state.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_stateKeyReleased(evt);
            }
        });

        txt_scode.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_scode.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        txt_scode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_scodeKeyReleased(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/save.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        code.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        code.setForeground(new java.awt.Color(255, 0, 51));
        code.setText(" ");
        code.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                codeKeyReleased(evt);
            }
        });

        pass.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pass.setForeground(new java.awt.Color(255, 0, 51));
        pass.setText(" ");

        add.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add.setForeground(new java.awt.Color(255, 0, 51));
        add.setText(" ");

        mob.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mob.setForeground(new java.awt.Color(255, 0, 51));
        mob.setText(" ");

        gst.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        gst.setForeground(new java.awt.Color(255, 0, 51));
        gst.setText(" ");

        state.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        state.setForeground(new java.awt.Color(255, 0, 51));
        state.setText(" ");

        add4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        add4.setForeground(new java.awt.Color(255, 0, 51));
        add4.setText(" ");

        scode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        scode.setForeground(new java.awt.Color(255, 0, 51));
        scode.setText(" ");

        txt_id.setBorder(null);

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/delete.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/update (1).png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/clear.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/new (3).png"))); // NOI18N
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        txt_add.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_add.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_addKeyReleased(evt);
            }
        });
        jScrollPane13.setViewportView(txt_add);

        txt_remark.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_remark.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jScrollPane14.setViewportView(txt_remark);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txt_name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                                            .addComponent(code, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addGap(182, 182, 182)
                                                .addComponent(gst, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(pass, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(mob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(add, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(txt_phone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                                                    .addComponent(txt_mob, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addGap(3, 3, 3))
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(32, 32, 32)
                                                        .addComponent(txt_code, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jLabel8))
                                                .addGap(0, 3, Short.MAX_VALUE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton5)
                                        .addGap(31, 31, 31)
                                        .addComponent(jButton1)
                                        .addGap(25, 25, 25)))
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                        .addGap(177, 178, Short.MAX_VALUE)
                                        .addComponent(scode, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jButton2)
                                        .addGap(28, 28, 28)
                                        .addComponent(jButton3)
                                        .addGap(26, 26, 26)
                                        .addComponent(jButton4)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel10Layout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addComponent(txt_gst, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 1, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                                .addGap(34, 34, 34)
                                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txt_state, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                                                    .addComponent(txt_scode, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                                                    .addComponent(state, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                                                .addGap(35, 35, 35)
                                                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))))))
                        .addGap(27, 27, 27)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(581, 581, 581)
                    .addComponent(add4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(337, 337, 337)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_code, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_gst, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(code, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gst, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_state, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(state))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_scode, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scode, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(27, 27, 27)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(1, 1, 1)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_mob, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addComponent(mob, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(20, 20, 20)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jScrollPane1)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(103, 103, 103)
                    .addComponent(add4)
                    .addContainerGap(443, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab6", jPanel8);

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 984, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton27.setBackground(new java.awt.Color(255, 255, 255));
        jButton27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/Go (1).png"))); // NOI18N
        jButton27.setBorder(null);
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        txt_todate.setBackground(new java.awt.Color(255, 255, 255));
        txt_todate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_todate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txt_fdate.setBackground(new java.awt.Color(255, 255, 255));
        txt_fdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_fdate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel56.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel56.setText("To Date");

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel57.setText("From Date");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Overall Customer Transaction");

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        jTextField1.setText("1");
        jTextField1.setBorder(null);

        txt_todate1.setBackground(new java.awt.Color(255, 255, 255));
        txt_todate1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_todate1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel59.setText("To Date");

        jLabel60.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel60.setText("From Date");

        txt_fdate1.setBackground(new java.awt.Color(255, 255, 255));
        txt_fdate1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txt_fdate1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jTextField2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Cust Code");

        jButton28.setBackground(new java.awt.Color(255, 255, 255));
        jButton28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gst_billing/Go (1).png"))); // NOI18N
        jButton28.setBorder(null);
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jLabel61.setBackground(new java.awt.Color(255, 255, 255));
        jLabel61.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 0, 0));
        jLabel61.setText("      Customer Wise Report");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txt_todate1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                        .addComponent(txt_fdate1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField2)))
                                .addGroup(jPanel16Layout.createSequentialGroup()
                                    .addGap(39, 39, 39)
                                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton28)
                                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(jButton27)
                        .addGap(18, 18, 18)))
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel57)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_todate, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                    .addComponent(txt_fdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(1000, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_fdate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_todate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addComponent(jButton27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_fdate1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_todate1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab7", jPanel9);

        jPanel17.setBackground(new java.awt.Color(51, 51, 51));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTable1_search.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1_search.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1_search.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bill No", "billto", "Bill Date", "tatpayable", "Customer Code", "Customer Name", "billaddress", "shipaddress", "shipcustname", "scode", "state", "gstinnumberll", "totalcgstll", "totalsgst", "totaltax", "subtotal", "vehicleno", "tmode", "psupply", "datetime", "fcharge", "loading", "insurance", "other", "notell", "Amount", "stime", "searchtime"
            }
        ));
        jTable1_search.setRowHeight(24);
        jTable1_search.setSelectionBackground(new java.awt.Color(103, 117, 226));
        jScrollPane2.setViewportView(jTable1_search);
        if (jTable1_search.getColumnModel().getColumnCount() > 0) {
            jTable1_search.getColumnModel().getColumn(0).setMinWidth(120);
            jTable1_search.getColumnModel().getColumn(0).setPreferredWidth(120);
            jTable1_search.getColumnModel().getColumn(0).setMaxWidth(120);
            jTable1_search.getColumnModel().getColumn(1).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(1).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(1).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(2).setMinWidth(210);
            jTable1_search.getColumnModel().getColumn(2).setPreferredWidth(210);
            jTable1_search.getColumnModel().getColumn(2).setMaxWidth(210);
            jTable1_search.getColumnModel().getColumn(3).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(3).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(3).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(4).setMinWidth(210);
            jTable1_search.getColumnModel().getColumn(4).setPreferredWidth(210);
            jTable1_search.getColumnModel().getColumn(4).setMaxWidth(210);
            jTable1_search.getColumnModel().getColumn(5).setMinWidth(400);
            jTable1_search.getColumnModel().getColumn(5).setPreferredWidth(400);
            jTable1_search.getColumnModel().getColumn(5).setMaxWidth(400);
            jTable1_search.getColumnModel().getColumn(6).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(6).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(6).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(7).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(7).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(7).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(8).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(8).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(8).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(9).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(9).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(9).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(10).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(10).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(10).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(11).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(11).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(11).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(12).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(12).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(12).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(13).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(13).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(13).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(14).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(14).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(14).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(15).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(15).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(15).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(16).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(16).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(16).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(17).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(17).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(17).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(18).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(18).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(18).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(19).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(19).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(19).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(20).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(20).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(20).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(21).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(21).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(21).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(22).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(22).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(22).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(23).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(23).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(23).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(24).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(24).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(24).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(26).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(26).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(26).setMaxWidth(0);
            jTable1_search.getColumnModel().getColumn(27).setMinWidth(0);
            jTable1_search.getColumnModel().getColumn(27).setPreferredWidth(0);
            jTable1_search.getColumnModel().getColumn(27).setMaxWidth(0);
        }

        jPanel19.setBackground(new java.awt.Color(103, 117, 226));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton32.setBackground(new java.awt.Color(255, 255, 255));
        jButton32.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton32.setText("OK");
        jButton32.setBorder(null);
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jButton33.setBackground(new java.awt.Color(255, 255, 255));
        jButton33.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jButton33.setText("CLEAR");
        jButton33.setBorder(null);
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        txt_search.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_search.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_searchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });

        jLabel62.setBackground(new java.awt.Color(103, 117, 226));
        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 255, 255));
        jLabel62.setText("Search For ");

        txt_searcht.setEditable(false);
        txt_searcht.setBackground(new java.awt.Color(103, 117, 226));
        txt_searcht.setFont(new java.awt.Font("Tahoma", 0, 1)); // NOI18N
        txt_searcht.setBorder(null);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(226, Short.MAX_VALUE)
                .addComponent(jLabel62)
                .addGap(58, 58, 58)
                .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(226, 226, 226))
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_searcht, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_search, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(txt_searcht, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab7", jPanel17);

        jPanel20.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1375, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 701, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("tab8", jPanel20);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 30, 1390, 740));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1393, 844));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
       jTabbedPane1.setSelectedIndex(0);
       
       
    }//GEN-LAST:event_b2ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
      jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
       jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_b4ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
        try {
     
     
     Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://Localhost:3307/gst_billing","root","");
        
            jComboBox_tax.removeAllItems();

           
        String sql = "select taxname from tax";
        PreparedStatement pstmt = conn.prepareStatement (sql);
        
           ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String val = rs.getString("taxname");
            jComboBox_tax.addItem(val);
        }
    } catch (SQLException ex) {
        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }   catch (ClassNotFoundException ex) {  
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } 
         
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_b5ActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
     jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_b6ActionPerformed

    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
        jTabbedPane1.setSelectedIndex(5);
        
        Date date = new Date();
        txt_fdate.setDate(date);
        
        txt_todate.setDate(date);
        txt_fdate1.setDate(date);
        txt_todate1.setDate(date);
        
    }//GEN-LAST:event_b7ActionPerformed

    private void txt_mobKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_mobKeyPressed
 char c = evt.getKeyChar();
      if (Character.isLetter(c)){
          txt_mob.setEditable(false);
      }else {
          txt_mob.setEditable(true);
      }     
    }//GEN-LAST:event_txt_mobKeyPressed

    private void txt_phoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_phoneKeyPressed
 char c = evt.getKeyChar();
      if (Character.isLetter(c)){
          txt_phone.setEditable(false);
      }else {
          txt_phone.setEditable(true);
      }
        
    }//GEN-LAST:event_txt_phoneKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     if(txt_code.getText().trim().isEmpty() && txt_name.getText().trim().isEmpty() && txt_add.getText().trim().isEmpty()
      &&  txt_mob.getText().trim().isEmpty()   &&  txt_gst.getText().trim().isEmpty() && txt_state.getText().trim().isEmpty() 
         && txt_scode.getText().trim().isEmpty()    ) 
     {
         code.setText("Enter the code");
         pass.setText("Enter the name");
         add.setText("Enter the address");
         mob.setText("Enter the mobile number"); 
         gst.setText("Enter the gstin number "); 
         state.setText("Enter the state");
         scode.setText("Enter the state code");
     }else if(txt_code.getText().trim().isEmpty()){
         code.setText("Enter the code");
     }else if(txt_name.getText().trim().isEmpty()){
         pass.setText("Enter the name");
     }else if(txt_add.getText().trim().isEmpty()){
          add.setText("Enter the address");
     }else if(txt_mob.getText().trim().isEmpty()){
          mob.setText("Enter the mobile number"); 
     }else if(txt_gst.getText().trim().isEmpty()){
         gst.setText("Enter the gstin number "); 
     }else if(txt_state.getText().trim().isEmpty()){
          state.setText("Enter the state");
     }else if(txt_scode.getText().trim().isEmpty()){
          scode.setText("Enter the state code");
     }else
        
           try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO customer(code,name,address,mobile,phone,gst,state,statecode,remark)"
                        + "values(?,?,?,?,?,?,?,?,?) ");
                
                ps.setString(1, txt_code.getText());
                ps.setString(2, txt_name.getText());
                ps.setString(3, txt_add.getText());
                ps.setString(4, txt_mob.getText());
                ps.setString(5, txt_phone.getText());
                ps.setString(6, txt_gst.getText());
                ps.setString(7, txt_state.getText());
                ps.setString(8, txt_scode.getText());
                ps.setString(9, txt_remark.getText());
              
             ps.executeUpdate();
            
              Show_Customers_In_JTable();
             JOptionPane.showMessageDialog(null, "Saved");
              txt_id.setText("");
              txt_code.setText("");
              txt_name.setText("");
              txt_add.setText("");
              txt_mob.setText("");
              txt_phone.setText("");
              txt_gst.setText("");
              txt_state.setText("");
              txt_scode.setText("");
              txt_remark.setText("");
        
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
      
        System.out.println("Namef => "+txt_code.getText());
        System.out.println("Namel => "+txt_name.getText());
        System.out.println("Namelf => "+txt_add.getText());
        System.out.println("Namelm => "+txt_mob.getText());
        System.out.println("Rollnof => "+txt_phone.getText());
          System.out.println("Rollnog => "+txt_gst.getText());
            System.out.println("Rollnoh => "+txt_state.getText());
              System.out.println("Rollnoj => "+txt_scode.getText());
                System.out.println("Rollnok => "+txt_remark.getText());
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nameKeyPressed
  
    }//GEN-LAST:event_txt_nameKeyPressed

    private void txt_codeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_codeKeyReleased
       code.setText("");
    }//GEN-LAST:event_txt_codeKeyReleased

    private void txt_nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_nameKeyReleased
        pass.setText("");
    }//GEN-LAST:event_txt_nameKeyReleased

    private void txt_mobKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_mobKeyReleased
        mob.setText("");
    }//GEN-LAST:event_txt_mobKeyReleased

    private void txt_gstKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_gstKeyReleased
        gst.setText("");
    }//GEN-LAST:event_txt_gstKeyReleased

    private void txt_stateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_stateKeyReleased
         state.setText("");
    }//GEN-LAST:event_txt_stateKeyReleased

    private void txt_scodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_scodeKeyReleased
        scode.setText("");
    }//GEN-LAST:event_txt_scodeKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://Localhost:3307/gst_billing","root","");
        
        String sql = "delete from customer where id = ?";
        PreparedStatement pstmt = conn.prepareStatement (sql);
        pstmt.setInt(1, Integer.parseInt(txt_id.getText()));
        pstmt.executeUpdate();
        Show_Customers_In_JTable();
        JOptionPane.showMessageDialog(null, "record deleted");
        txt_id.setText("");
        txt_code.setText("");
        txt_name.setText("");
        txt_add.setText("");
        txt_mob.setText("");
        txt_phone.setText("");
        txt_gst.setText("");
        txt_state.setText("");
        txt_scode.setText("");
        txt_remark.setText("");
        conn.close();
    }
        catch(Exception e)
        {
         JOptionPane.showMessageDialog(null,e);   
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void JTable_CustomersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_CustomersMouseClicked
       
        int i = JTable_Customers.getSelectedRow();
        TableModel model = JTable_Customers.getModel();
        txt_id.setText(model.getValueAt(i,0).toString());
        txt_code.setText(model.getValueAt(i,1).toString());
        txt_name.setText(model.getValueAt(i,2).toString());
        txt_add.setText(model.getValueAt(i,3).toString());
        txt_mob.setText(model.getValueAt(i,4).toString());
        txt_phone.setText(model.getValueAt(i,5).toString());
        txt_gst.setText(model.getValueAt(i,6).toString());
        txt_state.setText(model.getValueAt(i,7).toString());
        txt_scode.setText(model.getValueAt(i,8).toString());
        txt_remark.setText(model.getValueAt(i,9).toString());
       
   
    }//GEN-LAST:event_JTable_CustomersMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
          if(txt_code.getText().trim().isEmpty() && txt_name.getText().trim().isEmpty() && txt_add.getText().trim().isEmpty()
      &&  txt_mob.getText().trim().isEmpty()   &&  txt_gst.getText().trim().isEmpty() && txt_state.getText().trim().isEmpty() 
         && txt_scode.getText().trim().isEmpty()    ) 
     {
         code.setText("Enter the code");
         pass.setText("Enter the name");
         add.setText("Enter the address");
         mob.setText("Enter the mobile number"); 
         gst.setText("Enter the gstin number "); 
         state.setText("Enter the state");
         scode.setText("Enter the state code");
     }else if(txt_code.getText().trim().isEmpty()){
         code.setText("Enter the code");
     }else if(txt_name.getText().trim().isEmpty()){
         pass.setText("Enter the name");
     }else if(txt_add.getText().trim().isEmpty()){
          add.setText("Enter the address");
     }else if(txt_mob.getText().trim().isEmpty()){
          mob.setText("Enter the mobile number"); 
     }else if(txt_gst.getText().trim().isEmpty()){
         gst.setText("Enter the gstin number "); 
     }else if(txt_state.getText().trim().isEmpty()){
          state.setText("Enter the state");
     }else if(txt_scode.getText().trim().isEmpty()){
          scode.setText("Enter the state code");
     }else
        
        try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("UPDATE customer SET code = ?, name = ?, address = ?, mobile = ?, phone = ?, gst = ?, state = ?, statecode = ?, remark = ? WHERE id = ?");
                 
                
                ps.setString(1, txt_code.getText());
                ps.setString(2, txt_name.getText());
                ps.setString(3, txt_add.getText());
                ps.setString(4, txt_mob.getText());
                ps.setString(5, txt_phone.getText());
                ps.setString(6, txt_gst.getText());
                ps.setString(7, txt_state.getText());
                ps.setString(8, txt_scode.getText());
                ps.setString(9, txt_remark.getText());
                ps.setInt(10, Integer.parseInt(txt_id.getText()));
              
             ps.executeUpdate();
            
              Show_Customers_In_JTable();
             JOptionPane.showMessageDialog(null, "updated");
              txt_id.setText("");
              txt_code.setText("");
              txt_name.setText("");
              txt_add.setText("");
              txt_mob.setText("");
              txt_phone.setText("");
              txt_gst.setText("");
              txt_state.setText("");
              txt_scode.setText("");
              txt_remark.setText("");
        
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       txt_id.setText("");
       txt_code.setText("");
       txt_name.setText("");
       txt_add.setText("");
       txt_mob.setText("");
       txt_phone.setText("");
       txt_gst.setText("");
       txt_state.setText("");
       txt_scode.setText("");
       txt_remark.setText("");
       
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       txt_id.setText("");
       txt_code.setText("");
       txt_name.setText("");
       txt_add.setText("");
       txt_mob.setText("");
       txt_phone.setText("");
       txt_gst.setText("");
       txt_state.setText("");
       txt_scode.setText("");
       txt_remark.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void JTable_ItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_ItemMouseClicked
       int i = JTable_Item.getSelectedRow();
        TableModel model = JTable_Item.getModel();
        txt_itemid.setText(model.getValueAt(i,0).toString());
        txt_itemcode.setText(model.getValueAt(i,1).toString());
        txt_itemname.setText(model.getValueAt(i,2).toString());
        txt_hsncode.setText(model.getValueAt(i,3).toString());
        txt_unit.setText(model.getValueAt(i,4).toString());
        txt_price.setText(model.getValueAt(i,5).toString());
        
        
        jComboBox_tax.setSelectedItem(model.getValueAt(i,6).toString());
      txt_valuety.setText(model.getValueAt(i,7).toString());
    }//GEN-LAST:event_JTable_ItemMouseClicked

    private void txt_itemcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_itemcodeKeyReleased
      itemcode.setText("");
    }//GEN-LAST:event_txt_itemcodeKeyReleased

    private void txt_itemnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_itemnameKeyReleased
       itemname.setText("");
    }//GEN-LAST:event_txt_itemnameKeyReleased

    private void txt_hsncodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_hsncodeKeyReleased
        hsncode.setText("");
    }//GEN-LAST:event_txt_hsncodeKeyReleased

    private void txt_unitKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_unitKeyReleased
         unit.setText("");
    }//GEN-LAST:event_txt_unitKeyReleased

    private void txt_priceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_priceKeyReleased
           price.setText("");
    }//GEN-LAST:event_txt_priceKeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
             if(txt_itemcode.getText().trim().isEmpty() && txt_itemname.getText().trim().isEmpty() && txt_hsncode.getText().trim().isEmpty()
      &&  txt_unit.getText().trim().isEmpty()   &&  txt_price.getText().trim().isEmpty() && txt_valuety.getText().trim().isEmpty() ) 
     {
         itemcode.setText("Enter the item code");
         itemname.setText("Enter the item name");
         hsncode.setText("Enter the hsn code");
         unit.setText("Enter the unit"); 
         price.setText("Enter the price"); 
         valuetax.setText("Enter the tax value");
         
     }else if(txt_itemcode.getText().trim().isEmpty()){
         itemcode.setText("Enter the item code");
     }else if(txt_itemname.getText().trim().isEmpty()){
        itemname.setText("Enter the item name");
     }else if(txt_hsncode.getText().trim().isEmpty()){
          hsncode.setText("Enter the hsn code");
     }else if(txt_unit.getText().trim().isEmpty()){
          unit.setText("Enter the unit"); 
     }else if(txt_price.getText().trim().isEmpty()){
         price.setText("Enter the price"); 
     }else if(txt_valuety.getText().trim().isEmpty()){
         valuetax.setText("Enter the tax value");
     }else
        
        try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO item(itemcode,itemname,hsncode,unit,price,tax,gstvalue)"
                        + "values(?,?,?,?,?,?,?) ");
                
                ps.setString(1, txt_itemcode.getText());
                ps.setString(2, txt_itemname.getText());
                ps.setString(3, txt_hsncode.getText());
                ps.setString(4, txt_unit.getText());
                ps.setString(5, txt_price.getText());
                String value=jComboBox_tax.getSelectedItem().toString();                
                ps.setString(6, value);
                ps.setString(7, txt_valuety.getText());
                ps.executeUpdate();
            
             Show_Item_In_JTable();
             JOptionPane.showMessageDialog(null, "Saved");
              txt_itemid.setText("");
              txt_itemcode.setText("");
              txt_itemname.setText("");
              txt_hsncode.setText("");
              txt_unit.setText("");
              txt_price.setText("");
              txt_valuety.setText("");
              
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
      
        System.out.println("Namef => "+txt_itemcode.getText());
        System.out.println("Namel => "+txt_itemname.getText());
        System.out.println("Namelf => "+txt_hsncode.getText());
        System.out.println("Namelm => "+txt_unit.getText());
        System.out.println("Rollnof => "+txt_price.getText());
        System.out.println("Rollnofg => "+txt_valuety.getText());
          
    }//GEN-LAST:event_jButton6ActionPerformed

    private void codeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_codeKeyReleased

    private void txt_priceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_priceKeyPressed
       char c = evt.getKeyChar();
      if (Character.isLetter(c)){
          txt_price.setEditable(false);
      }else {
          txt_price.setEditable(true);
      }   
    }//GEN-LAST:event_txt_priceKeyPressed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://Localhost:3307/gst_billing","root","");
        
        String sql = "delete from item where id = ?";
        PreparedStatement pstmt = conn.prepareStatement (sql);
        pstmt.setInt(1, Integer.parseInt(txt_itemid.getText()));
        pstmt.executeUpdate();
        Show_Item_In_JTable();
        JOptionPane.showMessageDialog(null, "record deleted");
        txt_itemid.setText("");
        txt_itemcode.setText("");
        txt_itemname.setText("");
        txt_hsncode.setText("");
        txt_unit.setText("");
        txt_price.setText("");
        jComboBox_tax.setSelectedItem("");
        txt_valuety.setText("");
        conn.close();
    }
        catch(Exception e)
        {
         JOptionPane.showMessageDialog(null,e);   
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
         if(txt_itemcode.getText().trim().isEmpty() && txt_itemname.getText().trim().isEmpty() && txt_hsncode.getText().trim().isEmpty()
      &&  txt_unit.getText().trim().isEmpty()   &&  txt_price.getText().trim().isEmpty() && txt_valuety.getText().trim().isEmpty() ) 
     {
         itemcode.setText("Enter the item code");
         itemname.setText("Enter the item name");
         hsncode.setText("Enter the hsn code");
         unit.setText("Enter the unit"); 
         price.setText("Enter the price"); 
         valuetax.setText("Enter the tax value");
     }else if(txt_itemcode.getText().trim().isEmpty()){
         itemcode.setText("Enter the item code");
     }else if(txt_itemname.getText().trim().isEmpty()){
        itemname.setText("Enter the item name");
     }else if(txt_hsncode.getText().trim().isEmpty()){
          hsncode.setText("Enter the hsn code");
     }else if(txt_unit.getText().trim().isEmpty()){
          unit.setText("Enter the unit"); 
     }else if(txt_price.getText().trim().isEmpty()){
         price.setText("Enter the price"); 
     }else if(txt_valuety.getText().trim().isEmpty()){
         valuetax.setText("Enter the tax value");
     }else
         
       try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("UPDATE item SET itemcode = ?, itemname = ?, hsncode = ?, unit = ?, price = ?, gstvalue = ?, tax = ?  WHERE id = ?");
                 
                
                ps.setString(1, txt_itemcode.getText());
                ps.setString(2, txt_itemname.getText());
                ps.setString(3, txt_hsncode.getText());
                ps.setString(4, txt_unit.getText());
                ps.setString(5, txt_price.getText());
                ps.setString(6, txt_valuety.getText());
                
                
                 String value=jComboBox_tax.getSelectedItem().toString();
                ps.setString(7, value);
                
                ps.setInt(8, Integer.parseInt(txt_itemid.getText()));
              
             ps.executeUpdate();
            
             Show_Item_In_JTable();
             JOptionPane.showMessageDialog(null, "updated");
              txt_itemid.setText("");
              txt_itemcode.setText("");
              txt_itemname.setText("");
              txt_hsncode.setText("");
              txt_unit.setText("");
              txt_price.setText("");
              txt_valuety.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }         
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
              txt_itemid.setText("");
              txt_itemcode.setText("");
              txt_itemname.setText("");
              txt_hsncode.setText("");
              txt_unit.setText("");
              txt_price.setText("");
              jComboBox_tax.setSelectedItem(""); 
              txt_valuety.setText("");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
       
              txt_itemid.setText("");
              txt_itemcode.setText("");
              txt_itemname.setText("");
              txt_hsncode.setText("");
              txt_unit.setText("");
              txt_price.setText("");
              jComboBox_tax.setSelectedItem(""); 
              txt_valuety.setText("");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void txt_taxnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_taxnameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_taxnameKeyPressed

    private void txt_taxnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_taxnameKeyReleased
       taxname.setText("");
    }//GEN-LAST:event_txt_taxnameKeyReleased

    private void txt_taxvalueKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_taxvalueKeyPressed
        char c = evt.getKeyChar();
      if (Character.isLetter(c)){
          txt_taxvalue.setEditable(false);
      }else {
          txt_taxvalue.setEditable(true);
      } 
    }//GEN-LAST:event_txt_taxvalueKeyPressed

    private void txt_taxvalueKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_taxvalueKeyReleased
       taxvalue.setText("");
    }//GEN-LAST:event_txt_taxvalueKeyReleased

    private void JTable_TaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_TaxMouseClicked
       int i = JTable_Tax.getSelectedRow();
        TableModel model = JTable_Tax.getModel();
        txt_id1.setText(model.getValueAt(i,0).toString());
        txt_taxname.setText(model.getValueAt(i,1).toString());
        txt_taxvalue.setText(model.getValueAt(i,2).toString());
    }//GEN-LAST:event_JTable_TaxMouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        if(txt_taxname.getText().trim().isEmpty() && txt_taxvalue.getText().trim().isEmpty() ) 
     {
         taxname.setText("Enter the tax name");
         taxvalue.setText("Enter the tax value");
         
     }else if(txt_taxname.getText().trim().isEmpty()){
         taxname.setText("Enter the tax name");
     }else if(txt_taxvalue.getText().trim().isEmpty()){
        taxvalue.setText("Enter the tax value");
     }else
        try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO tax(taxname,taxvalue)"
                        + "values(?,?) ");
                
                ps.setString(1, txt_taxname.getText());
                ps.setString(2, txt_taxvalue.getText());
      
             ps.executeUpdate();
            
             Show_Tax_In_JTable();
             JOptionPane.showMessageDialog(null, "Saved");
             txt_id1.setText("");
             txt_taxname.setText("");
              txt_taxvalue.setText("");
              
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
      
        System.out.println("Namef => "+txt_taxname.getText());
        System.out.println("Namel => "+txt_taxvalue.getText());
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
       try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://Localhost:3307/gst_billing","root","");
        
        String sql = "delete from tax where id = ?";
        PreparedStatement pstmt = conn.prepareStatement (sql);
        pstmt.setInt(1, Integer.parseInt(txt_id1.getText()));
        pstmt.executeUpdate();
        Show_Tax_In_JTable();
        JOptionPane.showMessageDialog(null, "record deleted");
        txt_id1.setText("");
        txt_taxname.setText("");
        txt_taxvalue.setText("");  
        conn.close();
    }
        catch(Exception e)
        {
         JOptionPane.showMessageDialog(null,e);   
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
         if(txt_taxname.getText().trim().isEmpty() && txt_taxvalue.getText().trim().isEmpty() ) 
     {
         taxname.setText("Enter the tax name");
         taxvalue.setText("Enter the tax value");
         
     }else if(txt_taxname.getText().trim().isEmpty()){
         taxname.setText("Enter the tax name");
     }else if(txt_taxvalue.getText().trim().isEmpty()){
        taxvalue.setText("Enter the tax value");
     }else
         
        try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("UPDATE tax SET taxname = ?, taxvalue = ? WHERE id = ?");
                 
                
                ps.setString(1, txt_taxname.getText());
                ps.setString(2, txt_taxvalue.getText());
                 
                ps.setInt(3, Integer.parseInt(txt_id1.getText()));
              
             ps.executeUpdate();
            
             Show_Tax_In_JTable();
             JOptionPane.showMessageDialog(null, "updated");
              txt_id1.setText("");
              txt_taxname.setText("");
              txt_taxvalue.setText(""); 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }   
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        txt_id1.setText("");
        txt_taxname.setText("");
        txt_taxvalue.setText("0"); 
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        txt_id1.setText("");
        txt_taxname.setText("");
        txt_taxvalue.setText("0"); 
    }//GEN-LAST:event_jButton15ActionPerformed

    private void txt_financialyearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_financialyearKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_financialyearKeyPressed

    private void txt_financialyearKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_financialyearKeyReleased
      financialyear.setText("");
    }//GEN-LAST:event_txt_financialyearKeyReleased

    private void JTable_FinancialyearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_FinancialyearMouseClicked
       int i = JTable_Financialyear.getSelectedRow();
        TableModel model = JTable_Financialyear.getModel();
        txt_financialid.setText(model.getValueAt(i,0).toString());
        txt_financialyear.setText(model.getValueAt(i,1).toString());
       
    }//GEN-LAST:event_JTable_FinancialyearMouseClicked

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
         if(txt_financialyear.getText().trim().isEmpty() ) 
     {
         financialyear.setText("Enter the financial year");
  
     }else if(txt_financialyear.getText().trim().isEmpty()){
         financialyear.setText("Enter the financial year");
     }else
        try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO financialyear(financialyear)"
                        + "values(?) ");
                
                ps.setString(1, txt_financialyear.getText());
              
      
             ps.executeUpdate();
             Show_Financialyear_In_JTable();
             JOptionPane.showMessageDialog(null, "Saved");
             txt_financialid.setText("");
             txt_financialyear.setText("");
             
              
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
      
        System.out.println("Namef => "+txt_financialyear.getText());
                
        
       
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
       try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://Localhost:3307/gst_billing","root","");
        
        String sql = "delete from financialyear where id = ?";
        PreparedStatement pstmt = conn.prepareStatement (sql);
        pstmt.setInt(1, Integer.parseInt(txt_financialid.getText()));
        pstmt.executeUpdate();
        Show_Financialyear_In_JTable();
        JOptionPane.showMessageDialog(null, "record deleted");
        txt_financialid.setText("");
        txt_financialyear.setText("");
        conn.close();
    }
        catch(Exception e)
        {
         JOptionPane.showMessageDialog(null,e);   
        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
         if(txt_financialyear.getText().trim().isEmpty() ) 
     {
         financialyear.setText("Enter the financial year");
  
     }else if(txt_financialyear.getText().trim().isEmpty()){
         financialyear.setText("Enter the financial year");
     }else
         
        try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("UPDATE financialyear SET financialyear = ? WHERE id = ?");
                 
                
                ps.setString(1, txt_financialyear.getText());
       
                ps.setInt(2, Integer.parseInt(txt_financialid.getText()));
              
             ps.executeUpdate();
            
             Show_Financialyear_In_JTable();
             JOptionPane.showMessageDialog(null, "updated");
              txt_financialid.setText("");
              txt_financialyear.setText("");
              
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }  
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        txt_financialid.setText("");
        txt_financialyear.setText("");  
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        txt_financialid.setText("");
        txt_financialyear.setText("");
    }//GEN-LAST:event_jButton25ActionPerformed

    private void txt_financialidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_financialidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_financialidActionPerformed

    
    private void listMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMousePressed
       if (!list.isSelectionEmpty()){
            Customer1 cus = (Customer1) mode.getElementAt(list.getSelectedIndex());
            txt_icode.setText(cus.getCode());
            txt_iname.setText(cus.getName());
            txt_billadd.setText(cus.getAddress());
            txt_istatecode.setText(cus.getGst());
            txt_istate.setText(cus.getState());
            txt_igst.setText(cus.getStatecode());
            txt_shipadd.setText(cus.getAddress());
            txt_custname.setText(cus.getName());
            mode.removeAllElements();
        }  

    }//GEN-LAST:event_listMousePressed

    private DefaultListModel mode;
  private DefaultListModel mode1;
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
       try {
          
            DataCon.ConnectionDB();
            
            //set model to list
            mode = new DefaultListModel();
           mode1 = new DefaultListModel();
            list.setModel(mode);
            list2.setModel(mode1);
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
       
  
       
    }//GEN-LAST:event_formWindowOpened

    private void txt_icodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_icodeKeyReleased
         try {
          
          mode.removeAllElements(); 
            String val=txt_icode.getText().trim();
            if(!val.equals("")){
                Statement s = DataCon.getDataCon().createStatement();
                ResultSet r = s.executeQuery("select * from customer where code like '%"+val+"%' limit 5");
                while (r.next()){
                    String ID=r.getString(1);
                    String  code=r.getString(2);
                    String  name=r.getString(3);
                    String  address=r.getString(4);
                    String  mobile=r.getString(5);
                    String  phone=r.getString(6);
                    String  statecode=r.getString(7);
                    String  state=r.getString(8);
                    String  gst=r.getString(9);
                    Customer1 obj=new Customer1(ID, code, name, address,mobile,phone, gst, state, statecode);
                    mode.addElement(obj);
                }
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txt_icodeKeyReleased

    private void txt_icode1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_icode1KeyReleased
        try {

            mode1.removeAllElements();
            String val=txt_icode1.getText().trim();
            if(!val.equals("")){
                Statement s = DataCon.getDataCon().createStatement();
                ResultSet r = s.executeQuery("select * from item where itemcode like '%"+val+"%' limit 5");
                while (r.next()){
                    String ID=r.getString(1);
                    String  itemcode=r.getString(2);
                    String  itemname=r.getString(3);
                    String  hsncode=r.getString(4);
                    String  unit=r.getString(5);
                    String  price=r.getString(6);
                    String  tax=r.getString(7);
                    String  gstvalue=r.getString(8);
                    Item1 obj=new Item1(ID, itemcode, itemname, hsncode, unit, price, tax, gstvalue);
                    mode1.addElement(obj);
                }
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txt_icode1KeyReleased

    private void list2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_list2MousePressed
        if (!list2.isSelectionEmpty()){
            Item1 cus = (Item1) mode1.getElementAt(list2.getSelectedIndex());
            txt_icode1.setText(cus.getItemcode());
            txt_des.setText(cus.getItemname());
            txt_unitprice.setText(cus.getPrice());
            taxval.setText(cus.getTax());
            mode1.removeAllElements();
        }
    }//GEN-LAST:event_list2MousePressed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
       
         if(txt_icode1.getText().length() <=0 || txt_des.getText().length() <=0 || txt_qty.getText().length() <=0 || txt_unitprice.getText().length() <=0)
        {
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
            
        }
        else
        {
        
        double a,b,c,d,e,f,g,h,i,j,k,l,m,n,o;
        a= Double.parseDouble(txt_qty.getText());
        b= Double.parseDouble(txt_unitprice.getText());
        
        c=a*b;
         totalprice.setText(""+c); 
         
         
         m = Double.parseDouble(taxval.getText());
        o = m / 2;
        taxred.setText(""+o); 
        
        d = Double.parseDouble(totalprice.getText());
        m = Double.parseDouble(taxred.getText());
        f= d * m / 100; 
         sgst.setText(""+f); 
         
        g = Double.parseDouble(totalsgst.getText()); 
        n = Double.parseDouble(taxred.getText());
        h = d * n / 100; 
        cgst.setText(""+h); 
        
        j = b= Double.parseDouble(sgst.getText());
        k= Double.parseDouble(cgst.getText());
        l=j+k;
        totaltax.setText(""+l); 
     
   
             
  
       try {
           Connection con = getConnection();
           PreparedStatement ps = con.prepareStatement("INSERT INTO invoicein(name,qty,rate,total,discamt,taxvalue,taxper,	sgst,cgst,igst,scgstrate,searchtime,taxpayable,invoicenumber,date,transmode,vechnumber,datetime,placesupply,gstnumber,partyname,billaddress,state,scode,shipaddress,remark,sgsttotal,cgsttotal,igsttotal,grandtotal,totaltaxvalue,subtotal,fright,loading,insurance,othercharge,ratecount,qtycount,stime)"
                   + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
           
           ps.setString(1, txt_des.getText());
           ps.setString(2, txt_qty.getText());
           ps.setString(3, txt_unitprice.getText());
           ps.setString(4, totalprice.getText());
           ps.setString(5, disc.getText());
           ps.setString(6, totaltax.getText());
           ps.setString(7, taxval.getText());
           ps.setString(8, sgst.getText());
           ps.setString(9, cgst.getText());
           ps.setString(10, igst.getText());
           ps.setString(11, taxred.getText());
           ps.setString(12, txt_searcht.getText());
           
           
           
           ps.setString(13, a1.getText());
           ps.setString(14, a2.getText());
           ps.setString(15, a3.getText());
           ps.setString(16, a4.getText());
           ps.setString(17, a5.getText());
           ps.setString(18, a6.getText());
           ps.setString(19, a7.getText());
           ps.setString(20, a8.getText());
           ps.setString(21, a9.getText());
           ps.setString(22, a10.getText());
           ps.setString(23, a11.getText());
           ps.setString(24, a12.getText());
           ps.setString(25, a13.getText());
           ps.setString(26, a14.getText());
           ps.setString(27, a10.getText());
           ps.setString(28, a11.getText());
           ps.setString(29, a12.getText());
           ps.setString(30, a13.getText());
           ps.setString(31, a14.getText());
           ps.setString(32, a14.getText());
           ps.setString(33, a14.getText());
           ps.setString(34, a14.getText());
           ps.setString(35, a14.getText());
           ps.setString(36, a14.getText());
           ps.setString(37, a14.getText());
           ps.setString(38, a14.getText());
           ps.setString(39, stime.getText());
           
           
           ps.executeUpdate();
           
           Show_Invoicein_In_JTable();
           JOptionPane.showMessageDialog(null, "Item Added");
           txt_icode1.setText("");
           txt_des.setText("");
           txt_qty.setText("");
           txt_unitprice.setText("");
           totalprice.setText("");
           taxred.setText("");
           taxval.setText("");
           totaltax.setText("");
           cgst.setText("");
           sgst.setText("");
           txt_invoiceid.setText("");
           
           
           
       } catch (Exception ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage());
       }
       
   
        
        System.out.println("Namef => "+txt_des.getText());
        System.out.println("Namel => "+txt_qty.getText());
        System.out.println("Namelf => "+txt_unitprice.getText());
        System.out.println("Namelm => "+totalprice.getText());
        System.out.println("Rollnof => "+disc.getText());
        System.out.println("Rollnofg => "+totaltax.getText());
        System.out.println("Rollnbofg => "+taxval.getText());
        System.out.println("Rollnyofg => "+sgst.getText());
        System.out.println("Rollnjofg => "+cgst.getText());
        System.out.println("Rollnlofg => "+igst.getText());
         System.out.println("fyyug => "+taxred.getText());
         System.out.println("fybbyug => "+txt_searcht.getText());
        
        System.out.println("Rolqlnlofg => "+a1.getText());
        System.out.println("Rowllnlofg => "+a2.getText());
        System.out.println("Rolelnlofg => "+a3.getText());
        System.out.println("Rollrnlofg => "+a4.getText());
        System.out.println("Rollntlofg => "+a5.getText());
        System.out.println("Rollnlyofg => "+a6.getText());
        System.out.println("Rollnloufg => "+a7.getText());
        System.out.println("Rollnlofig => "+a8.getText());
        System.out.println("Rollnlofgo => "+a9.getText());
        System.out.println("Roollnlofg => "+a10.getText());
        System.out.println("Ropllnlofg => "+a11.getText());
        System.out.println("Rolsldnlofg => "+a12.getText());
        System.out.println("Rollsnlofg => "+a13.getText());
        System.out.println("Rollfg => "+a14.getText()); 
        System.out.println("RoFllfg => "+a14.getText());
        System.out.println("RolGGlfg => "+a14.getText());
        System.out.println("RoGGllfg => "+a14.getText());
        System.out.println("RoFFllfg => "+a14.getText());
        System.out.println("RolFRElfg => "+a14.getText());
        System.out.println("RolttFRElfg => "+a14.getText()); 
        
        System.out.println("Rofg => "+a14.getText());
        System.out.println("RFRElfg => "+a14.getText());
        System.out.println("Rg => "+a14.getText());
        System.out.println("fg => "+a14.getText());
        
        System.out.println("Rttg => "+a14.getText());
        System.out.println("fyyug => "+a14.getText());
        System.out.println("fyytnnug => "+stime.getText());
        
        
        
        
       
        
        int numrow = jTable_invoicein.getRowCount();
        double tot = 0;
        for(int z = 0; z < numrow; z++)
        {
            double val = Double.valueOf(jTable_invoicein.getValueAt(z,5).toString());    
            tot +=val ;
            
        }  
        totalc.setText(Double.toString(tot));
        
        
        
        int num33 = jTable_invoicein.getRowCount();
        double tot33 = 0;
        for(int s = 0; s < num33; s++)
        {
            double val33 = Double.valueOf(jTable_invoicein.getValueAt(s,4).toString());    
            tot33 +=val33;    
        }  
        totalrate.setText(Double.toString(tot33));
        
        
        int num44 = jTable_invoicein.getRowCount();
        double tot44 = 0;
        for(int s = 0; s < num44; s++)
        {
            double val44 = Double.valueOf(jTable_invoicein.getValueAt(s,3).toString());    
            tot44 +=val44;    
        }  
        qtycount.setText(Double.toString(tot44));
        
        
        
        int num = jTable_invoicein.getRowCount();
        double tot2 = 0;
        for(int s = 0; s < num; s++)
        {
            double val2 = Double.valueOf(jTable_invoicein.getValueAt(s,9).toString());    
            tot2 +=val2;    
        }  
        totalsgst.setText(Double.toString(tot2));
        
        
        int num2 = jTable_invoicein.getRowCount();
        double tot3 = 0;
        for(int ss = 0; ss < num2; ss++)
        {
            double val3 = Double.valueOf(jTable_invoicein.getValueAt(ss,10).toString());    
            tot3 +=val3;    
        }  
        totalcgst.setText(Double.toString(tot3));
        
        int num3 = jTable_invoicein.getRowCount();
        double tot4 = 0;
        for(int mm = 0; mm < num3; mm++)
        {
            double val4 = Double.valueOf(jTable_invoicein.getValueAt(mm,7).toString());    
            tot4 +=val4;    
        }  
        totaltaxv.setText(Double.toString(tot4));
        
        
         } 
        
    }//GEN-LAST:event_jButton16ActionPerformed

    private void txt_valuetyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_valuetyKeyPressed
          char c = evt.getKeyChar();
      if (Character.isLetter(c)){
          txt_valuety.setEditable(false);
      }else {
          txt_valuety.setEditable(true);
      }  
    }//GEN-LAST:event_txt_valuetyKeyPressed

    private void txt_valuetyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_valuetyKeyReleased
       valuetax.setText("");
    }//GEN-LAST:event_txt_valuetyKeyReleased

    private void jTable_invoiceinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_invoiceinMouseClicked
         int i = jTable_invoicein.getSelectedRow();
        TableModel model = jTable_invoicein.getModel();
        txt_invoiceid.setText(model.getValueAt(i,0).toString());
    }//GEN-LAST:event_jTable_invoiceinMouseClicked

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
      try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://Localhost:3307/gst_billing","root","");
        
        String sql = "delete from invoicein where id = ?";
        PreparedStatement pstmt = conn.prepareStatement (sql);
        pstmt.setInt(1, Integer.parseInt(txt_invoiceid.getText()));
        pstmt.executeUpdate();
        Show_Invoicein_In_JTable();
        JOptionPane.showMessageDialog(null, "Item Deleted");
        txt_id.setText("");;
        conn.close();
    }
        catch(Exception e)
        {
         JOptionPane.showMessageDialog(null,e);   
        } 
      
      int numrow = jTable_invoicein.getRowCount();
        double tot = 0;
        for(int z = 0; z < numrow; z++)
        {
            double val = Double.valueOf(jTable_invoicein.getValueAt(z,5).toString());    
            tot +=val ;
            
        }  
        totalc.setText(Double.toString(tot));
        
        
        
        int num33 = jTable_invoicein.getRowCount();
        double tot33 = 0;
        for(int s = 0; s < num33; s++)
        {
            double val33 = Double.valueOf(jTable_invoicein.getValueAt(s,4).toString());    
            tot33 +=val33;    
        }  
        totalrate.setText(Double.toString(tot33));
        
        
        int num44 = jTable_invoicein.getRowCount();
        double tot44 = 0;
        for(int s = 0; s < num44; s++)
        {
            double val44 = Double.valueOf(jTable_invoicein.getValueAt(s,3).toString());    
            tot44 +=val44;    
        }  
        qtycount.setText(Double.toString(tot44));
        
        
        
        int num = jTable_invoicein.getRowCount();
        double tot2 = 0;
        for(int s = 0; s < num; s++)
        {
            double val2 = Double.valueOf(jTable_invoicein.getValueAt(s,9).toString());    
            tot2 +=val2;    
        }  
        totalsgst.setText(Double.toString(tot2));
        
        
        int num2 = jTable_invoicein.getRowCount();
        double tot3 = 0;
        for(int ss = 0; ss < num2; ss++)
        {
            double val3 = Double.valueOf(jTable_invoicein.getValueAt(ss,10).toString());    
            tot3 +=val3;    
        }  
        totalcgst.setText(Double.toString(tot3));
        
        int num3 = jTable_invoicein.getRowCount();
        double tot4 = 0;
        for(int mm = 0; mm < num3; mm++)
        {
            double val4 = Double.valueOf(jTable_invoicein.getValueAt(mm,7).toString());    
            tot4 +=val4;    
        }  
        totaltaxv.setText(Double.toString(tot4));
      
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
            if(
              bill_date.getDate() == null 
              || txt_icode.getText().length() <=0 
              || txt_iname.getText().length() <=0 
              || txt_billadd.getText().length() <=0
              || txt_shipadd.getText().length() <=0
              || txt_custname.getText().length() <=0
              || txt_istatecode.getText().length() <=0
              || txt_istate.getText().length() <=0
              || txt_igst.getText().length() <=0
              || datetimesuppy.getDate()  == null 
             )
        {
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
            
        }
        else
        {

        
         
         HashMap a = new  HashMap();
        
        a.put("searchtime", txt_searcht.getText());
       
        
        jPanel24.removeAll();
        
        jPanel24.repaint();
        jPanel24.revalidate();
       
       
           
         try {
                JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\kapil\\Documents\\NetBeansProjects\\Gst_Billing\\src\\gst_billing\\Invoiceprint.jrxml");
              
                
                JasperReport jreport = JasperCompileManager.compileReport(jdesign);
                
                JasperPrint jprint = JasperFillManager.fillReport(jreport, a, con);
                
                JRViewer v = new JRViewer(jprint);
                
                jPanel24.setLayout(new BorderLayout());
                jPanel24.add(v);
                
                
            } catch (JRException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
       jTabbedPane1.setSelectedIndex(7);
        }
            
    }//GEN-LAST:event_jButton19ActionPerformed

    private void totalrateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalrateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalrateActionPerformed

    private void qtycountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qtycountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qtycountActionPerformed

    private void jTable_invoiceinAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable_invoiceinAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable_invoiceinAncestorAdded

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed

           if(
              bill_date.getDate() == null 
              || txt_icode.getText().length() <=0 
              || txt_iname.getText().length() <=0 
              || txt_billadd.getText().length() <=0
              || txt_shipadd.getText().length() <=0
              || txt_custname.getText().length() <=0
              || txt_istatecode.getText().length() <=0
              || txt_istate.getText().length() <=0
              || txt_igst.getText().length() <=0
              || datetimesuppy.getDate()  == null 
             )
        {
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
            
        }
        else
        {
        
        
        double aa,bb,dd,ee,ff,gg,hh,ii;
        aa= Double.parseDouble(totalcgst.getText());
        bb= Double.parseDouble(totalsgst.getText());
        
        dd= Double.parseDouble(totalc.getText());
        ee= Double.parseDouble(fcharge.getText());
        ff= Double.parseDouble(loadingcha.getText());
        gg= Double.parseDouble(unsu.getText());
        hh= Double.parseDouble(othch.getText());
        ii=aa + bb + dd + ee + ff + gg + hh;
         grandtotal.setText(""+ii);   
         
        try { 
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO invoice(billto,billdate,taxpayable,custcode,custname,billaddress,shipaddress,shipcustname,scode,state,gstinnumber,totalcgst,totalsgst,totaltax,subtotal,vehicleno,tmode,psupply,datetime,fcharge,loading,insurance,other,note,grandtotal,stime,searchtime)"
                        + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
                
                String value=jComboBox1.getSelectedItem().toString();
                ps.setString(1, value);
                
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String billdate = dateFormat.format(bill_date.getDate());
                ps.setString(2, billdate);
                
                 String value2=jComboBox_payable.getSelectedItem().toString();
                ps.setString(3, value2);
                
       
                ps.setString(4, txt_icode.getText());
                ps.setString(5, txt_iname.getText());
                ps.setString(6, txt_billadd.getText());
                ps.setString(7, txt_shipadd.getText());
                ps.setString(8, txt_custname.getText());
                ps.setString(9, txt_istatecode.getText()); 
                ps.setString(10, txt_istate.getText());
                ps.setString(11, txt_igst.getText());
                
                ps.setString(12, totalcgst.getText());
                ps.setString(13, totalsgst.getText());
                ps.setString(14, totaltaxv.getText());
                ps.setString(15, totalc.getText());
                ps.setString(16, vchnumber.getText());
                ps.setString(17, transmode.getText());
                ps.setString(18, placesupply.getText());
                
                 String DT = dateFormat.format(datetimesuppy.getDate());
                ps.setString(19, DT);
                
                ps.setString(20, fcharge.getText());
                ps.setString(21, loadingcha.getText());
                ps.setString(22, unsu.getText());
                ps.setString(23, othch.getText());
                ps.setString(24, remark.getText());
                ps.setString(25, grandtotal.getText());
                ps.setString(26, stime.getText());
                ps.setString(27, txt_searcht.getText());
                
             
                ps.executeUpdate();
                Show_Search_In_JTable();
               
             JOptionPane.showMessageDialog(null, "Saved");
 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
      
        System.out.println("Namef => "+txt_icode.getText());
        System.out.println("Namef => "+txt_iname.getText());
        System.out.println("Namef => "+txt_billadd.getText());
        System.out.println("Namef => "+txt_shipadd.getText());
        System.out.println("Namef => "+txt_custname.getText());
        System.out.println("Namef => "+txt_istatecode.getText());
        System.out.println("Namef => "+txt_istate.getText());
        System.out.println("Namef => "+txt_igst.getText());
        System.out.println("Namef => "+totalcgst.getText());
        System.out.println("Namef => "+totalsgst.getText());
        System.out.println("Namef => "+totaltaxv.getText());
        System.out.println("Namef => "+totalc.getText());
        System.out.println("Namef => "+vchnumber.getText());
        System.out.println("Namef => "+transmode.getText());
        System.out.println("Namef => "+placesupply.getText());
        System.out.println("Namef => "+fcharge.getText());
        System.out.println("Namef => "+loadingcha.getText());
        System.out.println("Namef => "+unsu.getText());
        System.out.println("Namef => "+othch.getText());
        System.out.println("Namef => "+remark.getText());
        System.out.println("Namef => "+grandtotal.getText());
        System.out.println("Namef => "+stime.getText());
        System.out.println("Namef => "+txt_searcht.getText());
       
        
           
          
        try {
             Class.forName("com.mysql.jdbc.Driver");
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("UPDATE invoicein SET  gstnumber = ?,taxpayable = ?,invoicenumber = ?,date = ?,transmode = ?,vechnumber = ?,datetime = ?,placesupply = ?,partyname = ?,billaddress = ?,state = ?,scode = ?,shipaddress = ?,remark = ?,sgsttotal = ?,cgsttotal = ?,igsttotal = ?,grandtotal = ?,totaltaxvalue = ?,subtotal = ?,fright = ?,loading = ?,insurance = ?, othercharge = ?,ratecount = ?,qtycount = ?,stime = ? order by id DESC limit 1;");
                 
                
               
               ps.setString(1, txt_igst.getText());
                String value=jComboBox_payable.getSelectedItem().toString();                
                ps.setString(2, value);
                ps.setString(3, bill_no.getText());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String billdate = dateFormat.format(bill_date.getDate());
                ps.setString(4, billdate);
                ps.setString(5, transmode.getText());
                ps.setString(6, vchnumber.getText());
                String datetime = dateFormat.format(datetimesuppy.getDate());
                ps.setString(7, datetime);
                ps.setString(8, placesupply.getText());
                ps.setString(9, txt_iname.getText());
                ps.setString(10, txt_billadd.getText());
                ps.setString(11, txt_istate.getText());
                ps.setString(12, txt_istatecode.getText());
                ps.setString(13, txt_shipadd.getText());
                ps.setString(14, remark.getText());
                ps.setString(15, totalsgst.getText());
                ps.setString(16, totalcgst.getText());
                ps.setString(17, jTextField5.getText());
                ps.setString(18, grandtotal.getText());
                ps.setString(19, totaltaxv.getText());
                ps.setString(20, totalc.getText());
                 ps.setString(21, fcharge.getText());
                  ps.setString(22, loadingcha.getText());
                   ps.setString(23, unsu.getText());
                    ps.setString(24, othch.getText());
                    
                    ps.setString(25, totalrate.getText());
                    ps.setString(26, qtycount.getText());
                     ps.setString(27, stime.getText());
                        
             ps.executeUpdate();
      
            } catch (Exception ex) {
               
            }  
        
           
              try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://Localhost:3307/gst_billing","root","");
        
        String sql = "insert into invoiceinsecond select * from invoicein;";
        PreparedStatement pstmt = conn.prepareStatement (sql);
       
        pstmt.executeUpdate();
       
       
      
    }
        catch(Exception e)
        {
         
        }   
           
        }     
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        Date date = new Date();
        bill_date.setDate(date);
        datetimesuppy.setDate(date);
         
        DateFormat dateFormat = new SimpleDateFormat("  hh:mm aa");
    	String dateString = dateFormat.format(new Date()).toString();
    	stime.setText(dateString); 
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
    	String dateString1 = dateFormat1.format(new Date()).toString();
    	txt_searcht.setText(dateString1);
        
        
        bill_no.setText("");
       txt_icode.setText("");
       txt_iname.setText("");
       txt_billadd.setText("");
       txt_shipadd.setText("");
       txt_custname.setText("");
       txt_istatecode.setText("");
       txt_istate.setText("");
       txt_igst.setText("");
       totalcgst.setText("0");
       totalsgst.setText("0");
       jTextField5.setText("0");
       totaltaxv.setText("0");
       jTextField7.setText("0");
       totalc.setText("0");
       vchnumber.setText("");
       transmode.setText("");
       placesupply.setText("");
       fcharge.setText("0");
       loadingcha.setText("0");
       unsu.setText("0");
       othch.setText("0");
       remark.setText("");
       grandtotal.setText("0");
       
       
                   try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://Localhost:3307/gst_billing","root","");
        
        String sql = "TRUNCATE TABLE invoicein";
        PreparedStatement pstmt = conn.prepareStatement (sql);
       
        pstmt.executeUpdate();
        Show_Invoicein_In_JTable();
       
      
    }
        catch(Exception e)
        {
         
        } 
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        Date date = new Date();
        bill_date.setDate(date);
        datetimesuppy.setDate(date);
         
        DateFormat dateFormat = new SimpleDateFormat("  hh:mm aa");
    	String dateString = dateFormat.format(new Date()).toString();
    	stime.setText(dateString); 
        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
    	String dateString1 = dateFormat1.format(new Date()).toString();
    	txt_searcht.setText(dateString1);
        
        bill_no.setText("");
       txt_icode.setText("");
       txt_iname.setText("");
       txt_billadd.setText("");
       txt_shipadd.setText("");
       txt_custname.setText("");
       txt_istatecode.setText("");
       txt_istate.setText("");
       txt_igst.setText("");
       totalcgst.setText("0");
       totalsgst.setText("0");
       jTextField5.setText("0");
       totaltaxv.setText("0");
       jTextField7.setText("0");
       totalc.setText("0");
       vchnumber.setText("");
       transmode.setText("");
       placesupply.setText("");
       fcharge.setText("0");
       loadingcha.setText("0");
       unsu.setText("0");
       othch.setText("0");
       remark.setText("");
       grandtotal.setText("0");
       
                    try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://Localhost:3307/gst_billing","root","");
        
        String sql = "TRUNCATE TABLE invoicein";
        PreparedStatement pstmt = conn.prepareStatement (sql);
       
        pstmt.executeUpdate();
        Show_Invoicein_In_JTable();
       
      
    }
        catch(Exception e)
        {
         
        } 
    }//GEN-LAST:event_jButton26ActionPerformed

    private void txt_icode1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_icode1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_icode1ActionPerformed

    private void txt_taxvalueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_taxvalueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_taxvalueActionPerformed

    private void fchargeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fchargeKeyPressed
            char c = evt.getKeyChar();
      if (Character.isLetter(c)){
          fcharge.setEditable(false);
      }else {
          fcharge.setEditable(true);
      }
    }//GEN-LAST:event_fchargeKeyPressed

    private void loadingchaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loadingchaKeyPressed
            char c = evt.getKeyChar();
      if (Character.isLetter(c)){
          loadingcha.setEditable(false);
      }else {
          loadingcha.setEditable(true);
      }
    }//GEN-LAST:event_loadingchaKeyPressed

    private void unsuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unsuKeyPressed
           char c = evt.getKeyChar();
      if (Character.isLetter(c)){
          unsu.setEditable(false);
      }else {
          unsu.setEditable(true);
      }
    }//GEN-LAST:event_unsuKeyPressed

    private void othchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_othchKeyPressed
            char c = evt.getKeyChar();
      if (Character.isLetter(c)){
         othch.setEditable(false);
      }else {
          othch.setEditable(true);
      }
    }//GEN-LAST:event_othchKeyPressed

    private void totalpriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totalpriceKeyPressed
            char c = evt.getKeyChar();
      if (Character.isLetter(c)){
          txt_taxvalue.setEditable(false);
      }else {
          txt_taxvalue.setEditable(true);
      }
    }//GEN-LAST:event_totalpriceKeyPressed

    private void txt_qtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_qtyKeyPressed
          char c = evt.getKeyChar();
      if (Character.isLetter(c)){
          txt_qty.setEditable(false);
      }else {
          txt_qty.setEditable(true);
      }
    }//GEN-LAST:event_txt_qtyKeyPressed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        Login obj=new Login();
        obj.setVisible(true);
        dispose();
    }//GEN-LAST:event_b1ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
   if( txt_fdate.getDate() == null
           || txt_todate.getDate() == null )
        {
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
            
        }
        else
        {     
             try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("UPDATE datecwise SET fromdate = ?, todate = ? WHERE id = ?");
                 
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String admissionDate = dateFormat.format(txt_fdate.getDate());
                ps.setString(1, admissionDate);
               
               
                String DOB = dateFormat.format(txt_todate.getDate());
                ps.setString(2, DOB);
                 
                ps.setInt(3, Integer.parseInt(jTextField1.getText()));
              
             ps.executeUpdate();

            } catch (Exception ex) {
                
            } 
        
        
        
        
        
        
        
        
        SimpleDateFormat date_format1 = new SimpleDateFormat("yyyy-MM-dd");
        String fdate = date_format1.format(txt_fdate.getDate());
        
        SimpleDateFormat date_format2 = new SimpleDateFormat("yyyy-MM-dd");
        String todate = date_format2.format(txt_todate.getDate());
        
        
        HashMap a = new  HashMap();
        
        a.put("fromd", fdate);
        a.put("tod", todate);
        
        jPanel3.removeAll();
        
        jPanel3.repaint();
        jPanel3.revalidate();
       
       
           
         try {
                JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\kapil\\Documents\\NetBeansProjects\\Gst_Billing\\src\\gst_billing\\reportinvoice.jrxml");
             
                
                JasperReport jreport = JasperCompileManager.compileReport(jdesign);
                
                JasperPrint jprint = JasperFillManager.fillReport(jreport, a, con);
                
                JRViewer v = new JRViewer(jprint);
                
                jPanel3.setLayout(new BorderLayout());
                jPanel3.add(v);
                
                
            } catch (JRException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
   if(jTextField2.getText().length() <=0 
           || txt_fdate1.getDate() == null
           || txt_todate1.getDate() == null )
        {
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
            
        }
        else
        {
        
        
        
        try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("UPDATE datecwise SET fromdate = ?, todate = ? WHERE id = ?");
                 
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String admissionDate = dateFormat.format(txt_fdate.getDate());
                ps.setString(1, admissionDate);
               
               
                String DOB = dateFormat.format(txt_todate.getDate());
                ps.setString(2, DOB);
                 
                ps.setInt(3, Integer.parseInt(jTextField1.getText()));
              
             ps.executeUpdate();
            
            } catch (Exception ex) {
                
            } 
        
          
        
        SimpleDateFormat date_format1 = new SimpleDateFormat("yyyy-MM-dd");
        String fdate = date_format1.format(txt_fdate1.getDate());
        
        SimpleDateFormat date_format2 = new SimpleDateFormat("yyyy-MM-dd");
        String todate = date_format2.format(txt_todate1.getDate());
        
        
        HashMap a = new  HashMap();
        
        a.put("custcode", jTextField2.getText());
        a.put("fromd", fdate);
        a.put("tod", todate);
        
        jPanel3.removeAll();
        
        jPanel3.repaint();
        jPanel3.revalidate();
       
       
           
         try {
                JasperDesign jdesign = JRXmlLoader.load("C:\\Users\\kapil\\Documents\\NetBeansProjects\\Gst_Billing\\src\\gst_billing\\cutreport.jrxml");
              
                
                JasperReport jreport = JasperCompileManager.compileReport(jdesign);
                
                JasperPrint jprint = JasperFillManager.fillReport(jreport, a, con);
                
                JRViewer v = new JRViewer(jprint);
                
                jPanel3.setLayout(new BorderLayout());
                jPanel3.add(v);
                
                
            } catch (JRException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        }
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
       jTabbedPane1.setSelectedIndex(6);
       
        Show_Invoiceinsecond_In_JTable();
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed


        
        int i = jTable1_search.getSelectedRow();
        TableModel model = jTable1_search.getModel();
        
        bill_no.setText(model.getValueAt(i,0).toString());
        jComboBox1.setSelectedItem(model.getValueAt(i,1).toString());
        Date billdate;
        try {
            billdate = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i,2));
            bill_date.setDate(billdate);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        jComboBox_payable.setSelectedItem(model.getValueAt(i,3).toString());
        txt_icode.setText(model.getValueAt(i,4).toString());
        txt_iname.setText(model.getValueAt(i,5).toString());
        txt_billadd.setText(model.getValueAt(i,6).toString());
        txt_shipadd.setText(model.getValueAt(i,7).toString());
        txt_custname.setText(model.getValueAt(i,8).toString());
        txt_istatecode.setText(model.getValueAt(i,9).toString());
        txt_istate.setText(model.getValueAt(i,10).toString());
        txt_igst.setText(model.getValueAt(i,11).toString());
        totalcgst.setText(model.getValueAt(i,12).toString());
        totalsgst.setText(model.getValueAt(i,13).toString());
        totaltaxv.setText(model.getValueAt(i,14).toString());
        totalc.setText(model.getValueAt(i,15).toString());
        vchnumber.setText(model.getValueAt(i,16).toString());
        transmode.setText(model.getValueAt(i,17).toString());
        placesupply.setText(model.getValueAt(i,18).toString());
        Date billdate1;
        try {
            billdate1 = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i,19));
            datetimesuppy.setDate(billdate1);
        } catch (ParseException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        fcharge.setText(model.getValueAt(i,20).toString());
        loadingcha.setText(model.getValueAt(i,21).toString());
        unsu.setText(model.getValueAt(i,22).toString());
        othch.setText(model.getValueAt(i,23).toString());
        remark.setText(model.getValueAt(i,24).toString());
        grandtotal.setText(model.getValueAt(i,25).toString());
        stime.setText(model.getValueAt(i,26).toString());
        txt_searcht.setText(model.getValueAt(i,27).toString());
       
        Show_Invoiceinsecond_In_JTable();
        jTabbedPane1.setSelectedIndex(0);
        
    }//GEN-LAST:event_jButton32ActionPerformed

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed
     
    }//GEN-LAST:event_txt_searchKeyPressed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
        Show_Search_In_JTable();
    }//GEN-LAST:event_txt_searchKeyReleased

    private void txt_qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_qtyKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_qtyKeyReleased

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        
        txt_search.setText("");
        Show_Search_In_JTable();
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
         try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://Localhost:3307/gst_billing","root","");
        
        String sql = "delete from invoice where billno = ?";
        PreparedStatement pstmt = conn.prepareStatement (sql);
        pstmt.setInt(1, Integer.parseInt(bill_no.getText()));
        pstmt.executeUpdate();
        Show_Search_In_JTable();
        
        JOptionPane.showMessageDialog(null, "Bill Deleted");
       bill_no.setText("");
       txt_icode.setText("");
       txt_iname.setText("");
       txt_billadd.setText("");
       txt_shipadd.setText("");
       txt_custname.setText("");
       txt_istatecode.setText("");
       txt_istate.setText("");
       txt_igst.setText("");
       totalcgst.setText("0");
       totalsgst.setText("0");
       jTextField5.setText("0");
       totaltaxv.setText("0");
       jTextField7.setText("0");
       totalc.setText("0");
       vchnumber.setText("");
       transmode.setText("");
       placesupply.setText("");
       fcharge.setText("0");
       loadingcha.setText("0");
       unsu.setText("0");
       othch.setText("0");
       remark.setText("");
       grandtotal.setText("0");

        conn.close();
    }
        catch(Exception e)
        {
         JOptionPane.showMessageDialog(null,e);   
        } 
         
         
         
              try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://Localhost:3307/gst_billing","root","");
        
        String sql = "delete from invoiceinsecond where searchtime = ?";
        PreparedStatement pstmt = conn.prepareStatement (sql);
        pstmt.setString(1, txt_searcht.getText());
        pstmt.executeUpdate();
        Show_Invoiceinsecond_In_JTable();

        conn.close();
    }
        catch(Exception e)
        {
         
        } 
         
  
         
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://Localhost:3307/gst_billing","root","");
        
        String sql = "TRUNCATE TABLE invoicein";
        PreparedStatement pstmt = conn.prepareStatement (sql);
       
        pstmt.executeUpdate();
        Show_Invoicein_In_JTable();
       
      
    }
        catch(Exception e)
        {
         
        }
    }//GEN-LAST:event_jButton31ActionPerformed

    private void txt_addKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_addKeyReleased
        add.setText("");
    }//GEN-LAST:event_txt_addKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTable_Customers;
    private javax.swing.JTable JTable_Financialyear;
    private javax.swing.JTable JTable_Item;
    private javax.swing.JTable JTable_Tax;
    private javax.swing.JTextField a1;
    private javax.swing.JTextField a10;
    private javax.swing.JTextField a11;
    private javax.swing.JTextField a12;
    private javax.swing.JTextField a13;
    private javax.swing.JTextField a14;
    private javax.swing.JTextField a2;
    private javax.swing.JTextField a3;
    private javax.swing.JTextField a4;
    private javax.swing.JTextField a5;
    private javax.swing.JTextField a6;
    private javax.swing.JTextField a7;
    private javax.swing.JTextField a8;
    private javax.swing.JTextField a9;
    private javax.swing.JLabel add;
    private javax.swing.JLabel add4;
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private com.toedter.calendar.JDateChooser bill_date;
    private javax.swing.JTextField bill_no;
    private javax.swing.JTextField cgst;
    private javax.swing.JLabel code;
    private com.toedter.calendar.JDateChooser datetimesuppy;
    private javax.swing.JTextField disc;
    private javax.swing.JTextField fcharge;
    private javax.swing.JLabel financialyear;
    private javax.swing.JTextField grandtotal;
    private javax.swing.JLabel gst;
    private javax.swing.JPanel hidebar;
    private javax.swing.JLabel hsncode;
    private javax.swing.JTextField igst;
    private javax.swing.JLabel itemcode;
    private javax.swing.JLabel itemname;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox_payable;
    private javax.swing.JComboBox<String> jComboBox_tax;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1_search;
    private javax.swing.JTable jTable_invoicein;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JList<String> list;
    private javax.swing.JList<String> list2;
    private javax.swing.JTextField loadingcha;
    private javax.swing.JLabel mob;
    private javax.swing.JTextField othch;
    private javax.swing.JLabel pass;
    private javax.swing.JTextField placesupply;
    private javax.swing.JLabel price;
    private javax.swing.JTextField qtycount;
    private javax.swing.JEditorPane remark;
    private javax.swing.JLabel scode;
    private javax.swing.JTextField sgst;
    private javax.swing.JLabel state;
    private javax.swing.JTextField stime;
    private javax.swing.JLabel taxname;
    private javax.swing.JTextField taxred;
    private javax.swing.JTextField taxval;
    private javax.swing.JLabel taxvalue;
    private javax.swing.JTextField totalc;
    private javax.swing.JTextField totalcgst;
    private javax.swing.JTextField totalprice;
    private javax.swing.JTextField totalrate;
    private javax.swing.JTextField totalsgst;
    private javax.swing.JTextField totaltax;
    private javax.swing.JTextField totaltaxv;
    private javax.swing.JTextField transmode;
    private javax.swing.JEditorPane txt_add;
    private javax.swing.JEditorPane txt_billadd;
    private javax.swing.JTextField txt_code;
    private javax.swing.JTextField txt_custname;
    private javax.swing.JTextField txt_des;
    private com.toedter.calendar.JDateChooser txt_fdate;
    private com.toedter.calendar.JDateChooser txt_fdate1;
    private javax.swing.JTextField txt_financialid;
    private javax.swing.JTextField txt_financialyear;
    private javax.swing.JTextField txt_gst;
    private javax.swing.JTextField txt_hsncode;
    private javax.swing.JTextField txt_icode;
    private javax.swing.JTextField txt_icode1;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_id1;
    private javax.swing.JTextField txt_idtax;
    private javax.swing.JTextField txt_idtax2;
    private javax.swing.JTextField txt_igst;
    private javax.swing.JTextField txt_iname;
    private javax.swing.JTextField txt_invoiceid;
    private javax.swing.JTextField txt_istate;
    private javax.swing.JTextField txt_istatecode;
    private javax.swing.JTextField txt_itemcode;
    private javax.swing.JTextField txt_itemid;
    private javax.swing.JTextField txt_itemname;
    private javax.swing.JTextField txt_mob;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_phone;
    private javax.swing.JTextField txt_price;
    private javax.swing.JTextField txt_qty;
    private javax.swing.JEditorPane txt_remark;
    private javax.swing.JTextField txt_scode;
    private javax.swing.JTextField txt_search;
    private javax.swing.JTextField txt_searcht;
    private javax.swing.JEditorPane txt_shipadd;
    private javax.swing.JTextField txt_state;
    private javax.swing.JTextField txt_taxname;
    private javax.swing.JTextField txt_taxvalue;
    private com.toedter.calendar.JDateChooser txt_todate;
    private com.toedter.calendar.JDateChooser txt_todate1;
    private javax.swing.JTextField txt_unit;
    private javax.swing.JTextField txt_unitprice;
    private javax.swing.JTextField txt_valuety;
    private javax.swing.JLabel unit;
    private javax.swing.JTextField unsu;
    private javax.swing.JLabel valuetax;
    private javax.swing.JTextField vchnumber;
    private javax.swing.JTextField x1;
    private javax.swing.JTextField x10;
    private javax.swing.JTextField x2;
    private javax.swing.JTextField x3;
    private javax.swing.JTextField x4;
    private javax.swing.JTextField x5;
    private javax.swing.JTextField x6;
    private javax.swing.JTextField x7;
    private javax.swing.JTextField x8;
    private javax.swing.JTextField x9;
    // End of variables declaration//GEN-END:variables
}
