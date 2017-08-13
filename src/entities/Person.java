package entities;

import java.io.Serializable;
import java.util.*;

import lib.util.PrimaryKeyGenerator;

public class Person implements Serializable{

    private String id;
    private String firstName;
    private String lastName;
    private String phone;
    private Address address;

    public Person(String firstName, String lastName, String phone, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.id = PrimaryKeyGenerator.primaryKey();
    }

    public Person() {
    }
    
    /*
     * Accessory methods. 
     */
    public String getId() {
        System.out.println("Is ID: "+id);
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass().getName() != obj.getClass().getName()) {
            return false;
        }
        Person other = (Person) obj;
        return this.getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        int result = 17;
        return (result * 31 + getId().hashCode());
    }
}