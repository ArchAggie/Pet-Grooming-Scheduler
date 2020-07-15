
package models;

/**
 * Brian Parsons
 * Student ID: 001008912
 * C195 - Software II - Advanced Java Concepts
 * Performance Assessment
 */

public class City
{
    private int cityID;
    private String cityName;
    private int countryID;
    
    public City()
    {
        
    }
    
    public City(int cID, String cName, int counID)
    {
        this.cityID = cID;
        this.cityName = cName;
        this.countryID = counID;
    }

    /**
     * @return the cityID
     */
    public int getCityID()
    {
        return cityID;
    }

    /**
     * @return the cityName
     */
    public String getCityName()
    {
        return cityName;
    }

    /**
     * @return the countryID
     */
    public int getCountryID()
    {
        return countryID;
    }

    /**
     * @param cityID the cityID to set
     */
    public void setCityID(int cityID)
    {
        this.cityID = cityID;
    }

    /**
     * @param cityName the cityName to set
     */
    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    /**
     * @param countryID the countryID to set
     */
    public void setCountryID(int countryID)
    {
        this.countryID = countryID;
    }
    
    @Override
    public String toString()
    {
        return cityName;
    }
}
