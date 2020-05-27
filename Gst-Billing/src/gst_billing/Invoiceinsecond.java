/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gst_billing;

/**
 *
 * @author kapil
 */
public class Invoiceinsecond {
    
 private int id;
    private String name;
    private String qty;
    private String rate;
    private String total;
    private String discamt;
    private String taxvalue;
    private String taxper;
    private String sgst;
    private String cgst;
    private String igst;
    private String searchtime;
        
    public Invoiceinsecond(int pid,String pname, String pqty, String prate,String ptotal, String  pdiscamt, String ptaxvalue, String ptaxper,String psgst,String pcgst,String pigst,String psearchtime)
    {
        this.id = pid;
        this.name = pname;
        this.qty = pqty;
        this.rate = prate;
        this.total = ptotal;
        this.discamt = pdiscamt;
        this.taxvalue = ptaxvalue;
        this.taxper = ptaxper;
        this.sgst = psgst;
        this.cgst = pcgst;
        this.igst = pigst;
        this.searchtime = psearchtime;   
    }

  
    
   public int  getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
        public String getQty()
    {
        return qty;
    }
        
        public String getRate()
    {
        return rate;
    }
        
        public String getTotal()
    {
        return total;
    }    
    
    public String getDiscamt()
    {
        return discamt;
    }
    
    public String getTaxvalue()
    {
        return taxvalue;
    }
    
     public String getTaxper()
    {
        return taxper;
    }
     
     public String getSgst()
    {
        return sgst;
    }
     
      public String getCgst()
    {
        return cgst;
    }
      
       public String getIgst()
    {
        return igst;
    }
     
       
        public String getSearchtime()
    {
        return searchtime;
    }
          
    
}
