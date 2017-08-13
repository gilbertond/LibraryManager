
import entities.Address;
import entities.Author;
import entities.Book;
import entities.LibraryMember;
import entities.Staff;
import lib.dao.PersistenceDAO;
import lib.domain.Library;
import java.util.Arrays;
import lib.dao.PersistenceDAOImpl;
import lib.domain.User;
import org.junit.Assert;
import java.util.List;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Tester {
    PersistenceDAO pdao = new PersistenceDAOImpl();
    
//    @org.junit.Test
    public void testSave(){
        User user = new User("Gilbert", "Ndenzi", "rmohanraj@mum.edu");
        User user2 = new User("Fa", "Tima", "Oudejji");
        User[] u = {user, user2};
        
        Library library1 = new Library("MUM1", "Fairfield", Arrays.asList(u));
        Library library2 = new Library("MUM2", "Fairfield", Arrays.asList(u));
        Library library3 = new Library("MUM222", "Fairfield", Arrays.asList(u));
//        Library[] arr = {library1, library2, library3};
//        pdao.saveRecordOrUpdate(library1);
//        pdao.saveRecordOrUpdate(library2);
//        pdao.saveRecordOrUpdate(library3);
//        pdao.saveRecordOrUpdate(Arrays.asList(arr));
        
        Book book = new Book("332332", "Test2", 5, new Author("Fatima", "Oujeddi", "NA", "992020202", new Address("Test", "Test", "Test", "34344")), 100);
        pdao.saveRecordOrUpdate(book);
    }
    
    @org.junit.Test
    public void testRead(){          
        List<Book> lib = (List<Book>)pdao.getRecords(Book.class);
        List<LibraryMember> members = (List<LibraryMember>)pdao.getRecords(LibraryMember.class);
        System.out.println(members.get(0)+"========"+lib.get(0));
        for (Book lib1 : lib) {
            System.out.println(lib.size()+"----"+lib1.getISBN());
        }
        
        Assert.assertNotNull(lib);
    }
    
//    @org.junit.Test
    public void searchObject(){          
        LibraryMember libraryMember = (LibraryMember)pdao.searchObject(LibraryMember.class, "getId", "2017070612484");
//        Staff staff = (Staff)pdao.searchObject(Staff.class, new String[]{"getFirstName", "getLastName"}, new Object[]{"Kalibala", "Kalibala"});
        System.out.println("Got result: "+libraryMember);
        Assert.assertFalse(libraryMember==null);
    }
    
//    @Test
    public void testUpdate(){
        Library library = (Library)pdao.searchObject(Library.class, "getLibraryname", "MUM222");
        library.setLocation("Des Moinesxxxx");
        
        System.out.println("*****"+library);
        Assert.assertEquals(true, pdao.updateRecord(library, "getLibraryname", "MUM222"));
    }
    
//    @Test
    public void testDelete(){
        Library library = (Library)pdao.searchObject(Library.class, "getLibraryname", "MUM222");
        library.setLocation("Des Moines");
        
        System.out.println("*****"+library);
        Assert.assertEquals(true, pdao.deleteRecord(library, "getLibraryname", "MUM222"));
    }
}
