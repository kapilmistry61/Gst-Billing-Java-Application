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
public class Search {
    
    private int billno;
    private String billto;
    private String billdate;
    private String taxpayable;
    private String custcode;
    private String custname;
    private String billaddress;
    private String shipaddress;
    private String shipcustname;
    private String scode;
    private String state;
    private String gstinnumber;
    private double totalcgst;
    private double totalsgst;
    private double totaltax;
    private String subtotal;
    private String vehicleno;
    private String tmode;
    private String psupply;
    private String datetime;
    private String fcharge;
    private String loading;
    private String insurance;
    private String other;
    private String note;
    private double grandtotal;
    private String stime;
    private String searchtime;
    
   
     public Search(int pbillno,String pbillto,String pbilldate,String ptaxpayable,String pcustcode,String pcustname,String pbilladdress,String pshipaddress,String pshipcustname,String pscode,String pstate,String pgstinnumber,double ptotalcgst,double ptotalsgst,double ptotaltax,String psubtotal,String pvehicleno,String ptmode,String ppsupply,String pdatetime,String pfcharge,String ploading,String pinsurance,String pother,String pnote,double pgrandtotal,String pstime,String psearchtime)
    {
        this.billno = pbillno;
        this.billto = pbillto;
        this.billdate = pbilldate;
        this.taxpayable = ptaxpayable;
        this.custcode = pcustcode;
        this.custname = pcustname;
        this.billaddress = pbilladdress;
        this.shipaddress = pshipaddress;
        this.shipcustname = pshipcustname;
        this.scode = pscode;
        this.state = pstate;
        this.gstinnumber = pgstinnumber;
        this.totalcgst = ptotalcgst;
        this.totalsgst = ptotalsgst;
        this.totaltax = ptotaltax;
        this.subtotal = psubtotal;
        this.vehicleno = pvehicleno;
        this.tmode = ptmode;
        this.psupply = ppsupply;
        this.datetime = pdatetime;
        this.fcharge = pfcharge;
        this.loading = ploading;
        this.insurance = pinsurance;
        this.other= pother;
        this.note = pnote;
        this.grandtotal = pgrandtotal;
        this.stime = pstime;
        this.searchtime = psearchtime;
   
    }

 
    
     
     public int  getBillno()
    {
        return billno;
    }
     
     
      public String getBillto()
    {
        return billto;
    }
      
      
       public String getBilldate()
    {
        return billdate;
    }
       
       
        public String getTaxpayable()
    {
        return taxpayable;
    }
        
        
         public String getCustcode()
    {
        return custcode;
    }
         
               public String getCustname()
    {
        return custname;
    }
       
         
         
          public String getBilladdress()
    {
        return billaddress;
    }
          
          
           public String getShipaddress()
    {
        return shipaddress;
    }
           
           
            public String getShipcustname()
    {
        return shipcustname;
    }       
           
           
            public String getScode()
    {
        return scode;
    }
            
            
             public String getState()
    {
        return state;
    }
             
             
              public String getGstinnumber()
    {
        return gstinnumber;
    }
              
              
               public double getTotalcgst()
    {
        return totalcgst;
    }
               
               
                public double getTotalsgst()
    {
        return totalsgst;
    }
                
                
                 public double getTotaltax()
    {
        return totaltax;
    }
                 
                 
                  public String getSubtotal()
    {
        return subtotal;
    }
                  
                  
                   public String getVehicleno()
    {
        return vehicleno;
    }
                   
                   
                    public String getTmode()
    {
        return tmode;
    }
                    
                    
                     public String getPsupply()
    {
        return psupply;
    }
                     
                     
                      public String getDatetime()
    {
        return datetime;
    }
                      
                      
                       public String getFcharge()
    {
        return fcharge;
    }
                       
                       
                        public String getLoading()
    {
        return loading;
    }
                        
                        
                         public String getInsurance()
    {
        return insurance;
    }
                         
                         
                          public String getOther()
    {
        return other;
    }
                          
          public String getNote()
    {
        return note;
    }
                                                
          public double getGrandtotal()
    {
        return grandtotal;
    }
        
          
          public String getStime()
    {
        return stime;
    }
          
      
           public String getSearchtime()
    {
        return searchtime;
    }      
                          
    
    
}

