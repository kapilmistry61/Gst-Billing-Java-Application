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
public class Item1 {

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
     * @return the itemcode
     */
    public String getItemcode() {
        return itemcode;
    }

    /**
     * @param itemcode the itemcode to set
     */
    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    /**
     * @return the itemname
     */
    public String getItemname() {
        return itemname;
    }

    /**
     * @param itemname the itemname to set
     */
    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    /**
     * @return the hsncode
     */
    public String getHsncode() {
        return hsncode;
    }

    /**
     * @param hsncode the hsncode to set
     */
    public void setHsncode(String hsncode) {
        this.hsncode = hsncode;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * @return the tax
     */
    public String getTax() {
        return tax;
    }

    /**
     * @param tax the tax to set
     */
    public void setTax(String tax) {
        this.tax = tax;
    }

    /**
     * @return the gstvalue
     */
    public String getGstvalue() {
        return gstvalue;
    }

    /**
     * @param gstvalue the gstvalue to set
     */
    public void setGstvalue(String gstvalue) {
        this.gstvalue = gstvalue;
    }
     private String ID;
     private String itemcode;
     private String itemname;
     private String hsncode;
     private String unit;
     private String price;
     private String tax;
     private String gstvalue;
    
    
       public Item1(){
        
    }
    public Item1(String ID, String itemcode, String itemname, String  hsncode,String unit,String price, String tax, String gstvalue)
    {
        this.ID = ID;
        this.itemcode = itemcode;
        this.itemname = itemname;
        this.hsncode = hsncode;
        this.unit = unit;
        this.price = price;
        this.tax = tax;
        this.gstvalue = gstvalue;
        
    }
    
    @Override
    public String toString(){
        return itemcode;
    }
     
     
}