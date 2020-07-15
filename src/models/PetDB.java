
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DBConnection;

/**
 * Brian Parsons
 * Student ID: 001008912
 * C195 - Software II - Advanced Java Concepts
 * Performance Assessment
 */

public class PetDB
{
    private final static ObservableList<Pet> allPets = FXCollections.observableArrayList();
    
    public static Pet getPet(int id)
    {
        try
        {
            Statement statement = DBConnection.getConnection().createStatement();
            String query = "SELECT * FROM pet WHERE pet_id = " + id;
            ResultSet results = statement.executeQuery(query);
            
            if(results.next())
            {
                Pet pet = new Pet();
                pet.setPetName(results.getString("pet_name"));
                statement.close();
                return pet;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public static ObservableList<Pet> getAllPets()
    {
        System.out.println("Retrieving all pets from database");
        allPets.clear();
        try
        {
            Statement statement = DBConnection.getConnection().createStatement();
            String query = "SELECT `pet`.`pet_id`, `pet`.`pet_name`, `pettype`.`pettype_description`, `pet`.`owner_name`, "
                    + "`address`.`addressline_1`, `address`.`address_id`, `city`.`city_name`, `address`.`postal_code`, `address`.`phone`, "
                    + "`pet`.`pet_birthdate`, `country`.`country_name` FROM pet, pettype, address, city, country "
                    + "WHERE pet.pettype_id = pettype.pettype_id AND pet.address_id = `address`.`address_id` AND "
                    + "`address`.`city_id` = `city`.`city_id` AND `city`.`country_id` = `country`.`country_id` "
                    + "ORDER BY `pet`.`pet_id`";
            ResultSet results = statement.executeQuery(query);
            
            while(results.next())
            {
                Pet pet = new Pet(
                        results.getInt("pet_id"), 
                        results.getString("pet_name"), 
                        results.getString("pettype_description"), 
                        results.getString("owner_name"), 
                        results.getString("addressline_1"), 
                        results.getInt("address_id"), 
                        results.getString("city_name"),
                        results.getString("postal_code"),
                        results.getString("phone"), 
                        results.getString("pet_birthdate"),
                        results.getString("country_name"));
                allPets.add(pet);
                System.out.println("Pet ID: " + results.getInt("pet_id"));
            }
            return allPets;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public static City getCity(int cityID)
    {
        try
        {
            Statement statement = DBConnection.getConnection().createStatement();
            String query = "SELECT city_name FROM city WHERE city_id = " + cityID;
            ResultSet results = statement.executeQuery(query);
            if (results.next())
            {
                City city = new City();
                city.setCityName(results.getString("city_name"));
                return city;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    public static boolean addPet(String petName, int petTypeID, LocalDate petBDay, String ownerName, 
            String petAddress, int petCityID, String petZip, String petPhone)
    {
        int addressID = 0;
        int petID = 0;
        
        try
        {
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet results1 = statement.executeQuery("SELECT MAX(address_id) FROM address");
            if (results1.next())
            {
                addressID = results1.getInt(1);
                addressID++;
            }
            ResultSet results2 = statement.executeQuery("SELECT MAX(pet_id) FROM pet");
            if (results2.next())
            {
                petID = results2.getInt(1);
                petID++;
            }
            String queryOne = "INSERT INTO address SET address_id = " + addressID + ", addressline_1 = '"
                    + petAddress + "', addressline_2 = '', city_id = " + petCityID + ", postal_code = '" 
                    + petZip + "', phone = '" + petPhone + "', created_at = NOW(), created_by = '', "
                    + "updated_at = NOW(), updated_by = ''";
            int updateOne = statement.executeUpdate(queryOne);
            if (updateOne == 1)
            {
                String queryTwo = "INSERT INTO pet SET pet_id = " + petID + ", pettype_id = " 
                        + petTypeID + ", pet_name = '" + petName + "', owner_name = '" 
                        + ownerName + "', address_id = " + addressID + ", pet_birthdate = '" 
                        + petBDay + "', pet_status = 1, created_at = NOW(), created_by = '', "
                        + "updated_at = NOW(), updated_by = ''";
                int updateTwo = statement.executeUpdate(queryTwo);
                if (updateTwo == 1)
                {
                    return true;
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean modifyPet(int id, int addressID, String petName, int petTypeID, LocalDate petBirthdate, 
            String ownerName, String petAddress, int cityID, String petZip, String petPhone)
    {
        try
        {
            Statement statement = DBConnection.getConnection().createStatement();
            String queryOne = "UPDATE address SET addressline_1 = '" + petAddress +"', city_id = " + cityID + ", "
                    + "postal_code = '" + petZip + "', phone = '" + petPhone + "', updated_at = NOW() "
                    + "WHERE address_id = " + addressID;
            
            int updateOne = statement.executeUpdate(queryOne);
            
            if (updateOne == 1)
            {
                String queryTwo = "UPDATE pet SET pet_name = '" + petName + "', pettype_id = " + petTypeID + ", "
                        + "owner_name = '" + ownerName + "', pet_birthdate = '" + petBirthdate + "', updated_at = NOW() "
                        + "WHERE pet_id = " + id;
                
                int updateTwo = statement.executeUpdate(queryTwo);
                
                if (updateTwo == 1)
                {
                    return true;
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean deletePet(int id)
    {
        try
        {
            Statement statementA = DBConnection.getConnection().createStatement();
            String queryA = "DELETE FROM appointment WHERE pet_id = " + id;
            int updateA = statementA.executeUpdate(queryA);
            
            Statement statement = DBConnection.getConnection().createStatement();
            String queryOne = "DELETE FROM pet WHERE pet_id = " + id;
            int updateOne = statement.executeUpdate(queryOne);
            
            if(updateOne == 1)
            {
                String queryTwo = "DELETE FROM address WHERE address_id = " + id;
                int updateTwo = statement.executeUpdate(queryTwo);
                if(updateTwo == 1)
                {
                    return true;
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
