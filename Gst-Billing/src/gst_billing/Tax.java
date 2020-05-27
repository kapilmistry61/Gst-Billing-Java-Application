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
public class Tax {
   private int id;
    private String taxname;
    private String taxvalue;
   
    public Tax(int pid,String ptaxname, String ptaxvalue)
    {
        this.id = pid;
        this.taxname = ptaxname;
        this.taxvalue = ptaxvalue;
    
    }
 
    
   public int  getId()
    {
        return id;
    }
    
    public String getTaxname()
    {
        return taxname;
    }
    
        public String getTaxvalue()
    {
        return taxvalue;
    }
          
}
  