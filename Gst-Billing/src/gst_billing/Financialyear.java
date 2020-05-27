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
public class Financialyear {
   
    private int id;
    private String financialyear;
   
    public Financialyear(int pid, String pfinancialyear)
    {
        this.id = pid;
        this.financialyear = pfinancialyear;
 
    }
 
    
   public int  getId()
    {
        return id;
    }
    
    public String getFinancialyear()
    {
        return financialyear;
    }
    
          
}
