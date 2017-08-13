/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Library implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private final String libraryname;
    private String location;
    private final List<User> users;

    public Library(String libraryname, String location, List<User> users) {
        this.libraryname = libraryname;
        this.location = location;
        this.users = users;
    }

    public String getLibraryname() {
        return libraryname;
    }

    public String getLocation() {
        return location;
    }
    
    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }    

    @Override
    public String toString() {
        return "Library{" + "libraryname=" + libraryname + ", location=" + location + ", users=" + users + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.libraryname);
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
        final Library other = (Library) obj;
        if (!Objects.equals(this.libraryname, other.libraryname)) {
            return false;
        }
        return true;
    }        

    public void setLocation(String location) {
        this.location = location;
    }
}
