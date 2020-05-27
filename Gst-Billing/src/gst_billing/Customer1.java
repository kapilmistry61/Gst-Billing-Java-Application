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
public class Customer1 {

    /**
     * @return the ID
     */
    public String getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the gst
     */
    public String getGst() {
        return gst;
    }

    /**
     * @param gst the gst to set
     */
    public void setGst(String gst) {
        this.gst = gst;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the statecode
     */
    public String getStatecode() {
        return statecode;
    }

    /**
     * @param statecode the statecode to set
     */
    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }
     private String ID;
     private String code;
     private String name;
     private String address;
     private String mobile;
     private String phone;
     private String gst;
     private String state;
     private String statecode;
     
     
     
     public Customer1(){
        
    }
    public Customer1(String ID, String code, String name, String  address,String mobile,String phone, String gst, String state, String statecode )
    {
        this.ID = ID;
        this.code = code;
        this.name = name;
        this.address = address;
        this.mobile = mobile;
        this.phone = phone;
        this.gst = gst;
        this.state = state;
        this.statecode =statecode;
    }
    
    @Override
    public String toString(){
        return code;
    }
    

}