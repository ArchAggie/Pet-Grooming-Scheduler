
package models;

/**
 * Brian Parsons
 * Student ID: 001008912
 * C195 - Software II - Advanced Java Concepts
 * Performance Assessment
 */

public class Address
{
    private int addressID;
    private String address1;
    private String address2;
    private String city;
    private int cityID;
    private boolean isAmerica;
    private String state;
    private String zipCode;
    private String country;
    
    public Address()
    {
        
    }
    
    public Address(int addID, String add1, String add2, String cty, int ctyID, boolean isUSA, String sta, String zCode, String cntry)
    {
        this.addressID = addID;
        this.address1 = add1;
        this.address2 = add2;
        this.city = cty;
        this.cityID = ctyID;
        this.isAmerica = isUSA;
        this.state = sta;
        this.zipCode = zCode;
        this.country = cntry;
    }
    
    public int getAddressID()
    {
        return addressID;
    }
    
    public String getAddress1()
    {
        return address1;
    }
    
    public String getAddress2()
    {
        return address2;
    }
    
    public String getCity()
    {
        return city;
    }
    
    public int getCityID()
    {
        return cityID;
    }
    
    public boolean getIsAmerica()
    {
        return isAmerica;
    }
    
    public String getState()
    {
        return state;
    }
    
    public String getZipCode()
    {
        return zipCode;
    }
    
    public String getCountry()
    {
        return country;
    }
    
    public void setAddressID(int addID)
    {
        addID = addressID;
    }
    
    public void setAddress1(String add1)
    {
        add1 = address1;
    }
    
    public void setAddress2(String add2)
    {
        add2 = address2;
    }
    
    public void setCity(String cty)
    {
        cty = city;
    }
    
    public void setCityID(int ctyID)
    {
        ctyID = cityID;
    }
    
    public void setIsAmerica(boolean isUSA)
    {
        isUSA = isAmerica;
    }
    
    public void setState(String sta)
    {
        sta = state;
    }
    
    public void setZipCode(String zCode)
    {
        zCode = zipCode;
    }
    
    public void setCountry(String cntry)
    {
        cntry = country;
    }
}
