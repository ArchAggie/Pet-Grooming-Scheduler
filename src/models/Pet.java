
package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Brian Parsons
 * Student ID: 001008912
 * C195 - Software II - Advanced Java Concepts
 * Performance Assessment
 */

public final class Pet
{
    private final IntegerProperty   petID         = new SimpleIntegerProperty();
    private final StringProperty    petName       = new SimpleStringProperty();
    private final StringProperty    petType       = new SimpleStringProperty();
    private final IntegerProperty   petTypeID     = new SimpleIntegerProperty();
    private final StringProperty    petOwnerName  = new SimpleStringProperty();
    private final StringProperty    petBirthdate  = new SimpleStringProperty();
    private final StringProperty    petAddress    = new SimpleStringProperty();
    private final IntegerProperty   petAddressID  = new SimpleIntegerProperty();
    private final StringProperty    petCity       = new SimpleStringProperty();
    private final IntegerProperty   petCityID     = new SimpleIntegerProperty();
    private final StringProperty    petZip        = new SimpleStringProperty();
    private final StringProperty    petCountry    = new SimpleStringProperty();
    private final StringProperty    petPhone      = new SimpleStringProperty();
    
    public Pet ()
    {
        
    }
    
//    // Pet 
//    public Pet (int pID)
//    {
//        setPetID(pID);
//    }
    
    // Pet Table Constructor
    public Pet (int pID, String pName, String pType, String pOwnName, String pAddress, int pAddressID, 
            String pCity, String pZip, String pPhone, String pBDay, String pCountry)
    {
        setPetID(pID);
        setPetName(pName);
        setPetType(pType);
        setPetOwnerName(pOwnName);
        setPetBirthdate(pBDay);
        setPetAddress(pAddress);
        setPetAddressID(pAddressID);
        setPetCity(pCity);
        setPetZip(pZip);
        setPetPhone(pPhone);
        setPetCountry(pCountry);
    }

    /**
     * @return the petID
     */
    public int getPetID()
    {
        return petID.get();
    }

    /**
     * @return the petName
     */
    public String getPetName()
    {
        return petName.get();
    }

    /**
     * @return the petType
     */
    public String getPetType()
    {
        return petType.get();
    }

    /**
     * @return the petTypeID
     */
    public int getPetTypeID()
    {
        return petTypeID.get();
    }

    /**
     * @return the petOwnerName
     */
    public String getPetOwnerName()
    {
        return petOwnerName.get();
    }

    /**
     * @return the petBirthDate
     */
    public String getPetBirthdate()
    {
        return petBirthdate.get();
    }

    /**
     * @return the petAddress
     */
    public String getPetAddress()
    {
        return petAddress.get();
    }

    /**
     * @return the petAddressID
     */
    public int getPetAddressID()
    {
        return petAddressID.get();
    }

    /**
     * @return the petCity
     */
    public String getPetCity()
    {
        return petCity.get();
    }

    /**
     * @return the petCityID
     */
    public int getPetCityID()
    {
        return petCityID.get();
    }

    /**
     * @return the petZip
     */
    public String getPetZip()
    {
        return petZip.get();
    }

    /**
     * @return the petCountry
     */
    public String getPetCountry()
    {
        return petCountry.get();
    }

    /**
     * @return the petPhone
     */
    public String getPetPhone()
    {
        return petPhone.get();
    }
    
    public void setPetID(int petID)
    {
        this.petID.set(petID);
    }
    
    public void setPetName(String petName)
    {
        this.petName.set(petName);
    }
    
    public void setPetType(String petType)
    {
        this.petType.set(petType);
    }
    
    public void setPetTypeID(int petTypeID)
    {
        this.petTypeID.set(petTypeID);
    }
    
    public void setPetOwnerName(String petOwnName)
    {
        this.petOwnerName.set(petOwnName);
    }
    
    public void setPetBirthdate(String petBirthdate)
    {
        this.petBirthdate.set(petBirthdate);
    }
    
    public void setPetAddress(String petAddress)
    {
        this.petAddress.set(petAddress);
    }
    
    public void setPetAddressID(int petAddressID)
    {
        this.petAddressID.set(petAddressID);
    }
    public void setPetCity(String petCity)
    {
        this.petCity.set(petCity);
    }
    
    public void setPetCityID(int petCityID)
    {
        this.petCityID.set(petCityID);
    }
    
    public void setPetZip(String petZip)
    {
        this.petZip.set(petZip);
    }
    
    public void setPetCountry(String petCountry)
    {
        this.petCountry.set(petCountry);
    }
    
    public void setPetPhone(String petPhone)
    {
        this.petPhone.set(petPhone);
    }
    
    public StringProperty getPetNameProperty()
    {
        return this.petName;
    }
    
    public StringProperty getPetTypeProperty()
    {
        return this.petType;
    }
    
    public StringProperty getPetOwnerNameProperty()
    {
        return this.petOwnerName;
    }
    
    public StringProperty getPetAddressProperty()
    {
        return this.petAddress;
    }
    
    public StringProperty getPetCityProperty()
    {
        return this.petCity;
    }
    
    public StringProperty getPetZipProperty()
    {
        return this.petZip;
    }
    
    public StringProperty getPetCountryProperty()
    {
        return this.petCountry;
    }
    
    public StringProperty getPetPhoneProperty()
    {
        return this.petPhone;
    }
    
    public StringProperty getPetBirthdateProperty()
    {
        String datePattern = "yyyy-MM-dd";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(datePattern);
        LocalDateTime ldt = LocalDateTime.parse(this.petBirthdate.getValue(), dtf);
        ZonedDateTime zdt = ldt.atZone(ZoneId.of("UTC"));
        ZoneId zid = ZoneId.systemDefault();
        ZonedDateTime utcDate = zdt.withZoneSameInstant(zid);
        StringProperty birthdate = new SimpleStringProperty(utcDate.toLocalDateTime().toString());
        
        return birthdate;
    }
    
    public LocalDate getDateOnly()
    {
        String datePattern = "yyyy-MM-dd";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(datePattern);
        LocalDate ld = LocalDate.parse(this.petBirthdate.getValue(), dtf);
        return ld;
    }
    
    @Override
    public String toString()
    {
        return petName.get();
    }
}
