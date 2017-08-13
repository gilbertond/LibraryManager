package entities;

import java.util.*;
import java.io.Serializable;

public class Author extends Person implements Serializable {

    private static final long serialVersionUID = 1L;
    private String credentials;

    public Author(String firstName, String lastName, String credentials, String phone, Address address) {
        super(firstName, lastName, phone, address);
        this.credentials = credentials;
//        book.writtenBy(this);
    }

    public Author() {
    }

    /*
     * Accessory methods. 
     */
    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "Author [firstName=" + getFirstName() + ", lastName="+getLastName()+", phone="+getPhone()+"]" + super.toString();
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
        Author other = (Author) obj;
        return this.getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        int result = 17;
        return (result * 31 + getId().hashCode());
    }
}
