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
public class Item {
    
     private int id;
    private String itemcode;
    private String itemname;
    private String hsncode;
    private String unit;
    private String price;
    private String tax;
    private String gstvalue;
  
        
    public Item(int pid,String pitemcode, String pitemname, String phsncode,String punit, String  pprice, String ptax, String pgstvalue)
    {
        this.id = pid;
        this.itemcode = pitemcode;
        this.itemname = pitemname;
        this.hsncode = phsncode;
        this.unit = punit;
        this.price = pprice;
        this.tax = ptax;
        this.gstvalue = pgstvalue;
       
      
    }

  
    
   public int  getId()
    {
        return id;
    }
    
    public String getItemcode()
    {
        return itemcode;
    }
    
        public String getItemname()
    {
        return itemname;
    }
        
        public String getHsncode()
    {
        return hsncode;
    }
        
        public String getUnit()
    {
        return unit;
    }    
    
    public String getPrice()
    {
        return price;
    }
    
    public String getTax()
    {
        return tax;
    }
    
     public String getGstvalue()
    {
        return gstvalue;
    }
     
    
}
    

