package controllers;

import entities.Address;
import entities.CheckOutRecord;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lib.dao.PersistenceDAO;
import lib.dao.PersistenceDAOImpl;
import entities.LibraryMember;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Admin_bkp {
    /*
     * Add/Edit a member
     */

    @FXML
    private TextField idMember;
    @FXML
    private TextField firstNameMember;
    @FXML
    private TextField lastNameMember;
    @FXML
    private TextField phoneMember;
    @FXML
    private TextField streetMember;
    @FXML
    private TextField cityMember;
    @FXML
    private TextField zipCodeMember;
    @FXML
    private MenuButton stateMember;
    @FXML
    private Button searchMember;
    @FXML
    private Button editMember;
    @FXML
    private Button saveMember;
    @FXML
    private Button cancelMember;
    /*
     * Add book
     */
    @FXML
    private TextField isbn;
    @FXML
    private TextField firstNameAuthor;
    @FXML
    private TextField lastNameAuthor;
    @FXML
    private TextField phoneAuthor;
    @FXML
    private TextField streetAuthor;
    @FXML
    private TextField cityAuthor;
    @FXML
    private TextField zipCodeAuthor;
    @FXML
    private MenuButton stateAuthor;
    @FXML
    private Button addAuthor;
    @FXML
    private Button saveAuthor;
    @FXML
    private Button cancelAuthor;
    @FXML
    private TableView<Author> authors;
    @FXML
    private TableColumn<Author, String> author;

    /**************************Staff and role*******************************/
    @FXML
    TextField lName;
    @FXML
    TextField fName;
    @FXML
    TextField phone;
    @FXML
    TextField street;
    @FXML
    TextField state;
    @FXML
    TextField zip;
    @FXML
    TextField city;
    @FXML
    TextField admin;
    @FXML
    TextField libr;
    
    PersistenceDAO pdao = new PersistenceDAOImpl();

    /*
     * Methods to handle the buttons in the Add/Edit Member Tab
     */
    public void onSearchMember() {
//            Staff staff = (Staff)pdao.searchObject(Staff.class, "getFirstName", idMember.getText());
//		System.out.println("Searching memeber: "+staff);

        LibraryMember libraryMember = (LibraryMember) pdao.searchObject(LibraryMember.class, "getFirstName", idMember.getText());
        System.out.println("Searching memeber: " + libraryMember);
    }

    public void onEditMember() {
        System.out.println("edit memeber");
    }

    public void onSaveMember() throws Exception {
        String fName = firstNameMember.getText();
        String lName = lastNameMember.getText();
        String phone = phoneMember.getText();
        String street = streetMember.getText();
        String city = cityMember.getText();
        String zip = zipCodeMember.getText();
        String state = stateMember.getText();

        Address address = new Address(street, city, state, zip);

        LibraryMember libraryMember = new LibraryMember(fName, lName, phone, new CheckOutRecord(), address);
        pdao.saveRecordOrUpdate(libraryMember);

//        Stage stage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("/view/dialog.fxml"));
//        Scene scene = new Scene(root);
//        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//        stage.setScene(scene);
//        stage.show();
    }

    public void onCancelMember() {
        System.out.println("Cancel memeber");
    }

    /*
     * Methods to handle the buttons in the Add/Edit Member Tab
     */
    public void onAddAuthor() {
        System.out.println("Add Author");
    }

    public void onSaveAuthor() {
        System.out.println("Save Author");
    }

    public void onCancelAuthor() {
        System.out.println("Cancel Author");
    }
    
    public void onSaveStaff() {
        System.out.println("Save staff");
    }
}
