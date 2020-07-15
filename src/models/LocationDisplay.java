
package models;

/**
 * Brian Parsons
 * Student ID: 001008912
 * C195 - Software II - Advanced Java Concepts
 * Performance Assessment
 */

public class LocationDisplay
{
    private final String location;
    private final int count;

    public LocationDisplay(String location, int count)
    {
        this.location = location;
        this.count = count;
    }

    public String getLocation()
    {
        return location;
    }

    public int getCount()
    {
        return count;
    }
    
}
