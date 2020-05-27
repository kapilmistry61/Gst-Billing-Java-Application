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
public class Customer {
    private int id;
    private String code;
    private String name;
    private String address;
    private String mobile;
    private String phone;
    private String gst;
    private String state;
    private String statecode;
    private String remark;
  
    
    public Customer(int pid,String pcode, String pname, String paddress,String pmobile, String  pphone, String pgst, String pstate, String pstatecode, String premark)
    {
        this.id = pid;
        this.code = pcode;
        this.name = pname;
        this.address = paddress;
        this.mobile = pmobile;
        this.phone = pphone;
        this.gst = pgst;
        this.state = pstate;
        this.statecode = pstatecode;
        this.remark = premark;
      
    }

  
    
   public int  getId()
    {
        return id;
    }
    
    public String getCode()
    {
        return code;
    }
    
        public String getName()
    {
        return name;
    }
        
        public String getAddress()
    {
        return address;
    }
        
        public String getMobile()
    {
        return mobile;
    }    
    
    public String getPhone()
    {
        return phone;
    }
    
    public String getGst()
    {
        return gst;
    }
    
    public String getState()
    {
        return  state;
    }
 
    public String getStatecode()
    {
        return  statecode;
    }
    
    public String getRemark()
    {
        return  remark;
    }
    
    
    
}

