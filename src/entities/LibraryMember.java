package entities;

import java.io.Serializable;
import java.util.*;

public class LibraryMember extends Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String phone;
    private CheckOutRecord checkOutRecord;

    public LibraryMember(String firstName, String lastName,
            String phone, CheckOutRecord checkOutRecord, Address address) {
        super(firstName, lastName, phone, address);
        this.checkOutRecord = new CheckOutRecord();
        this.checkOutRecord.setCheckOutEntrys(new ArrayList<>());
        // save the librayMember object in the librayMember.txt.
    }

    public LibraryMember() {
        super();
    }        

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getPhone() {
        return super.getPhone();
    }
    
    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CheckOutRecord getCheckOutRecord() {
        return checkOutRecord;
    }

    public void setCheckOutRecord(CheckOutRecord checkOutRecord) {
        this.checkOutRecord = checkOutRecord;
    }

    @Override
    public String toString() {
        return "LibraryMember{ID " + super.getId() + "firstName=" + super.getFirstName() + ", lastName=" + super.getLastName() + ", phone=" + super.getPhone() + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + super.getId().hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LibraryMember other = (LibraryMember) obj;
        System.out.println(this.getId() +"((((((((((("+other.getId());
        
        return this.getId().equals(other.getId());
    }
}
