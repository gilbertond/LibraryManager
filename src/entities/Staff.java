package entities;

import java.util.List;
import java.io.Serializable;

public class Staff extends Person implements Serializable{

    private static final long serialVersionUID = 1L;
    private String userName;
    private String password;
    private List<String> roles;

    public Staff(String userName, String password, List<String> roles, String fName, String lName, String phone, Address address) {
        super(fName, lName, phone, address);
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public Staff() {
        super();
    }
    
    /*
     * Accessory methods. 
     */
    public String getUserName() {
        return this.userName;
    }

    @Override
    public String getFirstName() {
        return super.getLastName();
    }
    
    @Override
    public String getLastName() {
        return super.getLastName();
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    public String getPassword() {
        return this.password;
    }
    
    @Override
    public String getId() {
        return super.getId();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
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
        Staff other = (Staff) obj;
        return this.getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        int result = 17;
        return (result * 31 + getId().hashCode());

    }

    @Override
    public String toString() {
        return "Staff{ firstName: " + super.getFirstName() + ", lastName: "+ super.getLastName() + 
                ", ID: " + super.getId() + " :userName=" + userName + ", passWord="+ password + ", Roles=" +getRoles()+ "}";
    }
    
    
}
