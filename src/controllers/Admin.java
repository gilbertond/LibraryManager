package controllers;

import entities.Address;
import entities.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import java.time.ZoneId;
import javafx.scene.control.Accordion;
import javafx.stage.Stage;
import java.time.Instant;
import lib.dao.PersistenceDAO;
import lib.dao.PersistenceDAOImpl;
import lib.util.Helper;
import entities.LibraryMember;
import java.util.Arrays;
import java.util.ArrayList;
import entities.Book;
import entities.Author;
import entities.BookCopy;
import entities.CheckOutRecord;
import entities.CheckOutRecordEntry;
import entities.Person;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.util.Callback;

public class Admin {
    /*
     * Add/Edit a member
     */

    private Accordion pane;
    @FXML
    private TextField idMember;
    @FXML
    private Label reMessage;
    @FXML
    private Label bookMessage;
    @FXML
    private Label searchBookLabel;
    @FXML
    private Label saveMessage;
    @FXML
    private Label copyID;
    @FXML
    private Label saveMessageM;
    @FXML
    private TextField firstNameMember;
    @FXML
    private TextField searchBook;
    @FXML
    private TextField addCopies;
    @FXML
    private TextField retCopyID;
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
    private ComboBox<String> stateMember;
    @FXML
    private Button searchMember;
    @FXML
    private Button editMember;
    @FXML
    private Button getCheckoit;
    @FXML
    private Button isbnSearch;
    @FXML
    private Button editStaff;
    @FXML
    private Button saveMember;
    @FXML
    private Button cancelMember;
    @FXML
    private Button returnBook;
    @FXML
    private Button checkOverdue;
    @FXML
    private Button addCopiy;
    @FXML
    private Label savedID;
    /*
     * Add book
     */
    @FXML
    private TextField isbn;
    @FXML
    private TextField title;
    @FXML
    private TextField copies;
    @FXML
    private TextField maxCheckouts;
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
    private ComboBox<String> stateAuthor;
    @FXML
    private Button addAuthor;
    @FXML
    private Button saveAuthor;
    @FXML
    private Button cancelAuthor;
    @FXML
    private TableView<String[]> authors;
    @FXML
    private TableView<String[]> membersView;
    @FXML
    private TableView<String[]> staffTableView;
    @FXML
    private TableColumn<Author, String> author;
    /*
     * Add/Edit a staff
     */
    @FXML
    private CheckBox adminBox;
    @FXML
    private CheckBox librarianBox;
    @FXML
    private TextField firstNameStaff;
    @FXML
    private TextField lastNameStaff;
    @FXML
    private TextField phoneStaff;
    @FXML
    private TextField streetStaff;
    @FXML
    private TextField cityStaff;
    @FXML
    private TextField zipCodeStaff;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField passwordConf;
    @FXML
    private ComboBox<String> stateStaff;
    @FXML
    private Button searchStaff;
    @FXML
    private Button saveStaff;
    @FXML
    private Button cancelStaff;
    /*
     * Manage Panes
     */
    @FXML
    private TitledPane bookPane;
    @FXML
    private TitledPane memberPane;
    @FXML
    private TitledPane staffPane;
    @FXML
    private TitledPane checkoutPane;
    /*
     * CheckOut pane
     */
    @FXML
    private TextField checkoutIsbn;
    @FXML
    private TextField checkoutMemberID;
    @FXML
    private TextField idStaff;
    @FXML
    private Label message;
    @FXML
    private Label messagePromp;
    @FXML
    private Button checkout;
    @FXML
    private Button cancelCheckout;
    @FXML
    private TableView checkoutTable;
    @FXML
    private TableColumn isbnClumn;
    @FXML
    private TableColumn titleColumn;
    @FXML
    private TableColumn checkoutDateColumn;
    @FXML
    private TableColumn dueDateColumn;
    @FXML
    private TableColumn firstname;
    @FXML
    private TableColumn lastname;
    @FXML
    private TableColumn phone;
    
    private ObservableList<String> options;

    PersistenceDAO pdao = new PersistenceDAOImpl();

    Book book;
    /*
     * Populate ChoiceBox and Add authors to the list of authors
     */

    public void initialize() {
        if (Helper.staff.getRoles().contains("ADMIN")) {
            memberPane.setVisible(true);
            staffPane.setVisible(true);
        }
        if (Helper.staff.getRoles().contains("BOTH")) {
            memberPane.setVisible(true);
            bookPane.setVisible(true);
            staffPane.setVisible(true);
            checkoutPane.setVisible(true);
        }
        if (Helper.staff.getRoles().contains("LIBRARIAN")) {
            bookPane.setVisible(true);
            checkoutPane.setVisible(true);
        }
        
        List<entities.LibraryMember> members = (List<entities.LibraryMember>)pdao.getRecords(entities.LibraryMember.class);
        loadTableViewMembers(members);
        
        List<entities.Staff> staffs = (List<entities.Staff>)pdao.getRecords(entities.Staff.class);
        loadTableViewStaff(staffs);
        
        populateComboBox();
    }
    
    public void loadTableViewMembers(List<entities.LibraryMember> members){
        TableColumn<String[], String> no = new TableColumn<String[], String>("#");
            TableColumn<String[], String> fName = new TableColumn<String[], String>("First Name");
            TableColumn<String[], String> mID = new TableColumn<String[], String>("ID");
            TableColumn<String[], String> lName = new TableColumn();
            lName.setText("Last Name");
            
            TableColumn<String[], String> tel = new TableColumn();
            tel.setText("Phone");
            System.out.println("Columns: " + membersView.getColumns());
            if(membersView.getColumns().size()<1){
            membersView.getColumns().addAll(no, mID, fName, lName, tel);

                no.setCellValueFactory((TableColumn.CellDataFeatures<String[], String> p) -> {
                    String[] x = p.getValue();
                    if (x != null && x.length > 0) {
                        return new SimpleStringProperty(x[0]);
                    } else {
                        return new SimpleStringProperty("<no name>");
                    }
                });
                mID.setCellValueFactory((TableColumn.CellDataFeatures<String[], String> p) -> {
                    String[] x = p.getValue();
                    if (x != null && x.length > 0) {
                        return new SimpleStringProperty(x[1]);
                    } else {
                        return new SimpleStringProperty("<no name>");
                    }
                });
                fName.setCellValueFactory((TableColumn.CellDataFeatures<String[], String> p) -> {
                    String[] x = p.getValue();
                    if (x != null && x.length > 0) {
                        return new SimpleStringProperty(x[2]);
                    } else {
                        return new SimpleStringProperty("<no name>");
                    }
                });
                lName.setCellValueFactory((TableColumn.CellDataFeatures<String[], String> p) -> {
                    String[] x = p.getValue();
                    if (x != null && x.length > 0) {
                        return new SimpleStringProperty(x[3]);
                    } else {
                        return new SimpleStringProperty("<no name>");
                    }
                });
                tel.setCellValueFactory((TableColumn.CellDataFeatures<String[], String> p) -> {
                    String[] x = p.getValue();
                    if (x != null && x.length > 0) {
                        return new SimpleStringProperty(x[4]);
                    } else {
                        return new SimpleStringProperty("<no name>");
                    }
                });
            }
            
             String[][] data = new String[members.size()][4];
             for (int i = 0; i < members.size(); i++) {
                data[i] = new String[]{String.valueOf(i+1), members.get(i).getId(), members.get(i).getFirstName(), members.get(i).getLastName(), members.get(i).getPhone()};
            }
            membersView.setItems(FXCollections.observableArrayList(Arrays.asList(data)));
            
    }
    
    public void loadTableViewStaff(List<entities.Staff> staff){
        TableColumn<String[], String> no = new TableColumn<String[], String>("#");
            TableColumn<String[], String> fName = new TableColumn<String[], String>("First Name");
            TableColumn<String[], String> mID = new TableColumn<String[], String>("ID");
            TableColumn<String[], String> lName = new TableColumn();
            lName.setText("Last Name");
            
            TableColumn<String[], String> tel = new TableColumn();
            tel.setText("Phone");
            
            if(staffTableView.getColumns().size()<1){
            staffTableView.getColumns().addAll(no, mID, fName, lName, tel);

                no.setCellValueFactory((TableColumn.CellDataFeatures<String[], String> p) -> {
                    String[] x = p.getValue();
                    if (x != null && x.length > 0) {
                        return new SimpleStringProperty(x[0]);
                    } else {
                        return new SimpleStringProperty("<no name>");
                    }
                });
                mID.setCellValueFactory((TableColumn.CellDataFeatures<String[], String> p) -> {
                    String[] x = p.getValue();
                    if (x != null && x.length > 0) {
                        return new SimpleStringProperty(x[1]);
                    } else {
                        return new SimpleStringProperty("<no name>");
                    }
                });
                fName.setCellValueFactory((TableColumn.CellDataFeatures<String[], String> p) -> {
                    String[] x = p.getValue();
                    if (x != null && x.length > 0) {
                        return new SimpleStringProperty(x[2]);
                    } else {
                        return new SimpleStringProperty("<no name>");
                    }
                });
                lName.setCellValueFactory((TableColumn.CellDataFeatures<String[], String> p) -> {
                    String[] x = p.getValue();
                    if (x != null && x.length > 0) {
                        return new SimpleStringProperty(x[3]);
                    } else {
                        return new SimpleStringProperty("<no name>");
                    }
                });
                tel.setCellValueFactory((TableColumn.CellDataFeatures<String[], String> p) -> {
                    String[] x = p.getValue();
                    if (x != null && x.length > 0) {
                        return new SimpleStringProperty(x[4]);
                    } else {
                        return new SimpleStringProperty("<no name>");
                    }
                });
            }
            
             String[][] data = new String[staff.size()][4];
             for (int i = 0; i < staff.size(); i++) {
                data[i] = new String[]{String.valueOf(i+1), staff.get(i).getId(), staff.get(i).getFirstName(), staff.get(i).getLastName(), staff.get(i).getPhone()};
            }
            staffTableView.setItems(FXCollections.observableArrayList(Arrays.asList(data)));
            
    }

    private void populateComboBox() {
        options = FXCollections.observableArrayList("Alabama", "Alaska",
                "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
                "Delaware", "Florida", "Georgia", "Hawaii", "Idaho",
                "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky",
                "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan",
                "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska",
                "Nevada", "New Hampshire", "New Jersey", "New Mexico",
                "New York", "North Carolina", "North Dakota", "Ohio",
                "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
                "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah",
                "Vermont", "Virginia", "Washington", "West Virginia",
                "Wisconsin", "Wyoming"
        );
        stateMember.setItems(options);
        stateAuthor.setItems(options);
        stateStaff.setItems(options);
    }
    /*
     * Methods to handle the buttons in the Add/Edit Member Tab
     */

    public void onSearchMember() {
        String memberid = idMember.getText();
        LibraryMember member = (LibraryMember) pdao.searchObject(LibraryMember.class, "getId", memberid);
        System.out.println(memberid + "$$$$$$$$$$$$" + member);
        if (member == null) {
//            idMember.setVisible(true);
            idMember.setText("Member not found!!");
            idMember.setStyle("-fx-text-fill: red;");
        } else {
            firstNameMember.setText(member.getFirstName());
            lastNameMember.setText(member.getLastName());
            streetMember.setText(member.getAddress().getStreet());
            cityMember.setText(member.getAddress().getCity());
            zipCodeMember.setText(member.getAddress().getZip());
            phoneMember.setText(member.getPhone());
            stateMember.getSelectionModel().select(member.getAddress().getState());
        }
    }

    public void onSearchBook() {
        String isbnSearch = searchBook.getText();
        book = (Book) pdao.searchObject(Book.class, "getISBN", isbnSearch);
        System.out.println(isbnSearch + "$$$$$$$$$$$$" + book);
        if (book == null) {
            bookMessage.setText("Book not found!!");
            bookMessage.setStyle("-fx-text-fill: red;");
        } else {
            isbn.setText(book.getISBN());
            title.setText(book.getTitle());
            maxCheckouts.setText("" + book.getMaxCheckOutLength());
            copies.setText("" + book.getBookCopy().size());
            firstNameAuthor.setText(book.getAuthor().get(0).getFirstName());
            lastNameAuthor.setText(book.getAuthor().get(0).getLastName());
            phoneAuthor.setText(book.getAuthor().get(0).getPhone());
            streetAuthor.setText(book.getAuthor().get(0).getAddress().getStreet());
            cityAuthor.setText(book.getAuthor().get(0).getAddress().getCity());
            zipCodeAuthor.setText(book.getAuthor().get(0).getAddress().getZip());
            stateAuthor.getSelectionModel().select(book.getAuthor().get(0).getAddress().getState());

            loadTableViewData();
                         
        }
    }
    
    public void loadTableViewData(){
        TableColumn<String[], String> no = new TableColumn<String[], String>("#");
            TableColumn<String[], String> fName = new TableColumn<String[], String>("First Name");
            
            TableColumn<String[], String> lName = new TableColumn();
            lName.setText("Last Name");
            
            TableColumn<String[], String> tel = new TableColumn();
            tel.setText("Phone");
            System.out.println("Columns: " + authors.getColumns());
            if(authors.getColumns().size()<1){
            authors.getColumns().addAll(no, fName, lName, tel);

                no.setCellValueFactory((TableColumn.CellDataFeatures<String[], String> p) -> {
                    String[] x = p.getValue();
                    if (x != null && x.length > 0) {
                        return new SimpleStringProperty(x[0]);
                    } else {
                        return new SimpleStringProperty("<no name>");
                    }
                });
                fName.setCellValueFactory((TableColumn.CellDataFeatures<String[], String> p) -> {
                    String[] x = p.getValue();
                    if (x != null && x.length > 0) {
                        return new SimpleStringProperty(x[1]);
                    } else {
                        return new SimpleStringProperty("<no name>");
                    }
                });
                lName.setCellValueFactory((TableColumn.CellDataFeatures<String[], String> p) -> {
                    String[] x = p.getValue();
                    if (x != null && x.length > 0) {
                        return new SimpleStringProperty(x[2]);
                    } else {
                        return new SimpleStringProperty("<no name>");
                    }
                });
                tel.setCellValueFactory((TableColumn.CellDataFeatures<String[], String> p) -> {
                    String[] x = p.getValue();
                    if (x != null && x.length > 0) {
                        return new SimpleStringProperty(x[3]);
                    } else {
                        return new SimpleStringProperty("<no name>");
                    }
                });
            }
            
             String[][] data = new String[book.getAuthor().size()][4];
             for (int i = 0; i < book.getAuthor().size(); i++) {
                data[i] = new String[]{String.valueOf(i+1), book.getAuthor().get(i).getFirstName(), book.getAuthor().get(i).getLastName(), book.getAuthor().get(i).getPhone()};
            }
            authors.setItems(FXCollections.observableArrayList(Arrays.asList(data)));
    }        

    public void onSearchStaff() {
        String staffid = idStaff.getText();
        Staff staff = (Staff) pdao.searchObject(Staff.class, "getId", staffid);
        System.out.println(staffid + "$$$$$$$$$$$$" + staff);
        if (staff == null) {
            idStaff.setText("Staff not found!!");
            idStaff.setStyle("-fx-text-fill: red;");
        } else {
            firstNameStaff.setText(staff.getFirstName());
            lastNameStaff.setText(staff.getLastName());
            streetStaff.setText(staff.getAddress().getStreet());
            cityStaff.setText(staff.getAddress().getCity());
            zipCodeStaff.setText(staff.getAddress().getZip());
            phoneStaff.setText(staff.getPhone());
            stateStaff.getSelectionModel().select(staff.getAddress().getState());
            username.setText(staff.getUserName());
            password.setText(staff.getPassword());
            passwordConf.setText(staff.getPassword());
            for (String role : staff.getRoles()) {
                if (role.contains("Librarian")) {
                    librarianBox.setSelected(true);
                }
                if (role.contains("Administrator")) {
                    adminBox.setSelected(true);
                }
            }
        }
    }

//201707063011
    public void onEditMember() {
        if (idMember == null || idMember.equals("")) {
            idMember.setText("Provide ID");
            idMember.setStyle("-fx-text-fill: red;");
        }
        String memberid = idMember.getText();
        LibraryMember member = (LibraryMember) pdao.searchObject(LibraryMember.class, "getId", memberid);

        if (member == null) {
            idMember.setText("Nothing to edit!!");
            idMember.setStyle("-fx-text-fill: red;");
        } else {
            LibraryMember libraryMember = new LibraryMember(firstNameMember.getText(), lastNameMember.getText(), phoneMember.getText(), member.getCheckOutRecord(),
                    new Address(streetMember.getText(), cityMember.getText(), stateMember.getValue(), zipCodeMember.getText()));

            pdao.updateRecord(libraryMember, "getId", memberid);

            List<entities.LibraryMember> members = (List<entities.LibraryMember>)pdao.getRecords(entities.LibraryMember.class);
            loadTableViewMembers(members);
            
            firstNameMember.setText("");
            lastNameMember.setText("");
            phoneMember.setText("");
            streetMember.setText("");
            cityMember.setText("");
            stateMember.getSelectionModel().select("--Choose--");
            zipCodeMember.setText("");
            idMember.setText(libraryMember.getId());

            saveMessageM.setVisible(true);
            saveMessageM.setText("Successifully updated member");
        }
    }

    public void onSaveMember() {

        LibraryMember member = (LibraryMember) pdao.searchObject(LibraryMember.class, new String[]{"getFirstName", "getLastName"}, new Object[]{firstNameMember.getText(), lastNameMember.getText()});
        if (member != null) {
            saveMessageM.setVisible(true);
            saveMessageM.setText("Member already exists");
        } else {
            LibraryMember libraryMember = new LibraryMember(firstNameMember.getText(), lastNameMember.getText(), phoneMember.getText(), null,
                    new Address(streetMember.getText(), cityMember.getText(), stateMember.getValue(), zipCodeMember.getText()));
            pdao.saveRecordOrUpdate(libraryMember);

            List<entities.LibraryMember> members = (List<entities.LibraryMember>)pdao.getRecords(entities.LibraryMember.class);
            loadTableViewMembers(members);
            
            savedID.setText("Saved ID: " + libraryMember.getId());

            firstNameMember.setText("");
            lastNameMember.setText("");
            phoneMember.setText("");
            streetMember.setText("");
            cityMember.setText("");
            stateMember.getSelectionModel().select("--Choose--");
            zipCodeMember.setText("");

            idMember.setText(libraryMember.getId());

            saveMessageM.setVisible(true);
            saveMessageM.setText("Successifully saved member");
        }
    }

    public void onCancelMember() {
        ((Stage) pane.getScene().getWindow()).close();
    }

    /*
     * Methods to handle the buttons in the Add/Edit Member Tab
     */
    public void onAddAuthor() {
        String isbnSearch = searchBook.getText();
        book = (Book) pdao.searchObject(Book.class, "getISBN", isbnSearch);
        System.out.println(isbnSearch + "$$$$$$$$$$$$" + book);

        if (book == null) {
            book = new Book(isbn.getText(), title.getText(), Integer.parseInt(maxCheckouts.getText()),
                    new Author(firstNameAuthor.getText(), lastNameAuthor.getText(), null, phoneAuthor.getText(),
                            new Address(streetAuthor.getText(), cityAuthor.getText(), stateAuthor.getValue(), zipCodeAuthor.getText())), Integer.parseInt(copies.getText()));
            pdao.saveRecordOrUpdate(book);

        } else {
            book.addAuthor(new Author(firstNameAuthor.getText(), lastNameAuthor.getText(), null, phoneAuthor.getText(),
                    new Address(streetAuthor.getText(), cityAuthor.getText(), stateAuthor.getValue(), zipCodeAuthor.getText())));

            pdao.updateRecord(book, "getISBN", isbnSearch);
        }
        
        loadTableViewData();
        
        firstNameAuthor.setText("");
        lastNameAuthor.setText("");
        phoneAuthor.setText("");
        streetAuthor.setText("");
        cityAuthor.setText("");
        stateAuthor.getSelectionModel().select("--Choose--");
        zipCodeAuthor.setText("");
    }

    public void onAddCopy() {
        book = (Book) pdao.searchObject(Book.class, "getISBN", isbn.getText());
        System.out.println(isbnSearch + "$$$$$$$$$$$$" + book);
        
        int numCopy = Integer.parseInt(addCopies.getText());
        if (book != null) {
            numCopy += book.getBookCopy().size();
            book.addCopy(Integer.parseInt(addCopies.getText()));            
            pdao.updateRecord(book, "getISBN", book.getISBN());
        }      
        copies.setText(String.valueOf(numCopy));
    }
    
    public void onSaveAuthor() {
        book = (Book) pdao.searchObject(Book.class, "getISBN", isbn.getText());
        System.out.println(isbnSearch + "$$$$$$$$$$$$" + book);

        Book bookNew = new Book(isbn.getText(), title.getText(), Integer.parseInt(maxCheckouts.getText()),
                new Author(firstNameAuthor.getText(), lastNameAuthor.getText(), null, phoneAuthor.getText(),
                        new Address(streetAuthor.getText(), cityAuthor.getText(), stateAuthor.getValue(), zipCodeAuthor.getText())), Integer.parseInt(copies.getText()));

        if (book == null) {
            pdao.saveRecordOrUpdate(bookNew);
        } else {
            if(book.getAuthor().contains(bookNew.getAuthor())){
                bookNew.setAuthor(book.getAuthor());
            }else{
                bookNew.getAuthor().addAll(book.getAuthor());
            }
            
            pdao.updateRecord(bookNew, "getISBN", book.getISBN());
        }
        book = bookNew;

        loadTableViewData();
        
        isbn.setText("");
        title.setText("");
        maxCheckouts.setText("");
        firstNameAuthor.setText("");
        lastNameAuthor.setText("");
        phoneAuthor.setText("");
        streetAuthor.setText("");
        cityAuthor.setText("");
        stateAuthor.getSelectionModel().select("--Choose--");
        zipCodeAuthor.setText("");
        copies.setText("");
        addCopies.setText("");
        book = null;
    }

    public void onCancelAuthor() {
        ((Stage) pane.getScene().getWindow()).close();
    }

    /*
     * Methods to handle the buttons in the Add staff Tab
     */
    public void onSaveStaff() {
        Staff staff = (Staff) pdao.searchObject(Staff.class, new String[]{"getFirstName", "getLastName"}, new Object[]{firstNameStaff.getText(), lastNameStaff.getText()});
        if (!password.getText().equals(passwordConf.getText())) {
            System.out.println("un-matched password");
            saveMessage.setVisible(true);
            saveMessage.setText("Password confirmation wrong!");
        } else if (staff != null) {
            saveMessage.setVisible(true);
            saveMessage.setText("Staff already exists");
        } else {

            String[] roles = new String[2];
            if (librarianBox.isSelected()) {
                roles[0] = adminBox.getText();
            }
            if (librarianBox.isSelected()) {
                roles[1] = librarianBox.getText();
            }

            staff = new Staff(username.getText(), password.getText(), Arrays.asList(roles), firstNameStaff.getText(), lastNameStaff.getText(), phoneStaff.getText(),
                    new Address(streetStaff.getText(), cityStaff.getText(), stateStaff.getValue(), zipCodeStaff.getText()));
            pdao.saveRecordOrUpdate(staff);

            List<entities.Staff> staffs = (List<entities.Staff>)pdao.getRecords(entities.Staff.class);
            loadTableViewStaff(staffs);
            
            idStaff.setText(staff.getId());
            firstNameStaff.setText("");
            lastNameStaff.setText("");
            phoneStaff.setText("");
            streetStaff.setText("");
            cityStaff.setText("");
            stateStaff.getSelectionModel().select("--Choose--");
            zipCodeStaff.setText("");
            username.setText("");
            password.setText("");
            passwordConf.setText("");

            saveMessage.setVisible(true);
            saveMessage.setText("Successifully saved staff");
        }
        memberPane.setVisible(false);
    }

    public void onEditStaff() {
        Staff staff = (Staff) pdao.searchObject(Staff.class, "getId", idStaff.getText());
        if (!password.getText().equals(passwordConf.getText())) {
            System.out.println("un-matched password");
            saveMessage.setVisible(true);
            saveMessage.setText("Password confirmation wrong!");
        } else {

            String[] roles = new String[2];
            if (librarianBox.isSelected()) {
                roles[0] = adminBox.getText();
            }
            if (librarianBox.isSelected()) {
                roles[1] = librarianBox.getText();
            }

            staff = new Staff(username.getText(), password.getText(), Arrays.asList(roles), firstNameStaff.getText(), lastNameStaff.getText(), phoneStaff.getText(),
                    new Address(streetStaff.getText(), cityStaff.getText(), stateStaff.getValue(), zipCodeStaff.getText()));
            pdao.updateRecord(staff, "getId", idStaff.getText());

            idStaff.setText(staff.getId());
            firstNameStaff.setText("");
            lastNameStaff.setText("");
            phoneStaff.setText("");
            streetStaff.setText("");
            cityStaff.setText("");
            stateStaff.getSelectionModel().select("--Choose--");
            zipCodeStaff.setText("");
            username.setText("");
            password.setText("");
            passwordConf.setText("");
            adminBox.setSelected(false);
            librarianBox.setSelected(false);
            saveMessage.setVisible(true);
            saveMessage.setText("Successifully updated staff");
        }
        memberPane.setVisible(false);
    }

    public void onCancelStaff() {
        ((Stage) pane.getScene().getWindow()).close();
    }

    /*
     * Methods to handle the buttons in the checkout tab
     */
    public void onCheckout() {
        String memberid = checkoutMemberID.getText();

        LibraryMember member = (LibraryMember) pdao.searchObject(LibraryMember.class, "getId", memberid);
        Book bookSearch = (Book) pdao.searchObject(Book.class, "getISBN", checkoutIsbn.getText());

        if (member == null) {
            message.setVisible(true);
            message.setText("Member not found!!");
        }
        if (bookSearch == null) {
            message.setVisible(true);
            message.setText("Book not found in store!!");
        }

        if (bookSearch != null && member != null) {
            List<BookCopy> copies = bookSearch.getBookCopy();

            int maxCheckouts = bookSearch.getMaxCheckOutLength();
            int checkRecords = bookSearch.getCheckoutLength();

            if (maxCheckouts == checkRecords) {
                message.setVisible(true);
                message.setText("Book and member found but no available books for cheching. Please come back later!!");
            } else {
                LocalDate localDate = LocalDate.now();
                localDate.plusDays(bookSearch.getMaxCheckOutLength());
                Date date = fromLocalDate(localDate);

                //Pick an available book
                BookCopy toIssue = null;
//                int userCheckouts = 0;
                for (BookCopy copy : copies) {
                    if (copy.isIsAvailable()) {
                        toIssue = copy;
//                        userCheckouts += 1; //Make sure the user doesnt exceed max
                        break;
                    }
                }
                if (toIssue == null) {
                    message.setVisible(true);
                    message.setText("No available books found. Please come back later!!");
                } else {
//                if(userCheckouts == book.getMaxCheckOutLength())
                    toIssue.setIsAvailable(false);
                    CheckOutRecordEntry checkOutRecordEntry = new CheckOutRecordEntry(new Date(), date, null, toIssue);
                    bookSearch.setCheckoutLength(bookSearch.getCheckoutLength() + 1);
                    
                    member.getCheckOutRecord().getCheckOutEntrys().add(checkOutRecordEntry);

                    //Update DB records for the new checkout
                    pdao.updateRecord(bookSearch, "getISBN", checkoutIsbn.getText());
//                    pdao.updateRecord(LibraryMember.class, "getID", memberid);

                    message.setVisible(true); 
                    message.setText("Book [ISBN: " + bookSearch.getISBN() + ", Title: " + bookSearch.getTitle() + " given to " + member.getFirstName() + " " + member.getLastName());
                }
            }
        }
    }

    public static Date fromLocalDate(LocalDate date) {
        Instant instant = date.atStartOfDay().atZone(ZoneId.systemDefault())
                .toInstant();
        return Date.from(instant);
    }

    public void onCancelCheckout() {
        ((Stage) pane.getScene().getWindow()).close();
    }
    
    public void onCheckOverdue() {
        String memberid = checkoutMemberID.getText();

        LibraryMember member = (LibraryMember) pdao.searchObject(LibraryMember.class, "getId", memberid);

        if (member == null) {
            message.setVisible(true);
            message.setText("Member not found!!");
        }else{
            List<CheckOutRecordEntry> checkOutRecordEntrys = member.getCheckOutRecord().getCheckOutEntrys();
            if(checkOutRecordEntrys.isEmpty()){
                message.setVisible(true);
                message.setText("No checkout records found!!");
                System.out.println("No checkout history found");
            }else{
                message.setVisible(true);
                String s = "Member: "+ member.getFirstName() + " " + member.getLastName() + " <\n>";
                StringBuilder buffer = new StringBuilder();
                buffer.append(s);
                for (CheckOutRecordEntry checkOutRecordEntry : checkOutRecordEntrys) {
                    buffer.append(checkOutRecordEntry.toString()).append("\n");
                }
                System.out.println(buffer);
                message.setText(buffer.toString());
            }
        }
    }
    
    public void onGetCheckoit() {
        String memberid = checkoutMemberID.getText();

        LibraryMember member = (LibraryMember) pdao.searchObject(LibraryMember.class, "getId", memberid);

        if (member == null) {
            message.setVisible(true);
            message.setText("Member not found!!");
        }else{
            List<CheckOutRecordEntry> checkOutRecordEntrys = member.getCheckOutRecord()!=null?member.getCheckOutRecord().getCheckOutEntrys():new ArrayList<>();
            if(checkOutRecordEntrys.isEmpty()){
                message.setVisible(true);
                message.setText("No checkout records found!!");
                System.out.println("No checkout history found");
            }else{
                message.setVisible(true);
                String s = "Member: "+ member.getFirstName() + " " + member.getLastName() + " <\n>";
                StringBuilder buffer = new StringBuilder();
                buffer.append(s);
                for (CheckOutRecordEntry checkOutRecordEntry : checkOutRecordEntrys) {
                    buffer.append(checkOutRecordEntry.toString()).append("\n");
                }
                System.out.println(buffer);
                message.setText(buffer.toString());
            }
        }
    }

    public void onReturnBook() {
        String memberid = checkoutMemberID.getText();

        LibraryMember member = (LibraryMember) pdao.searchObject(LibraryMember.class, "getId", memberid);
        Book bookSearch = (Book) pdao.searchObject(Book.class, "getISBN", checkoutIsbn.getText());

        if (member == null) {
            message.setVisible(true);
            message.setText("Member not found!!");
        }
        if (bookSearch == null) {
            message.setVisible(true);
            message.setText("Book not found in store!!");
        }

        if (bookSearch != null && member != null && member.getCheckOutRecord()!=null) {            
            //Pick an returned copy
            List<CheckOutRecordEntry> checkOutRecordEntrys = member.getCheckOutRecord()!=null?member.getCheckOutRecord().getCheckOutEntrys():new ArrayList<>();
            for (CheckOutRecordEntry checkOutRecordEntry : checkOutRecordEntrys) {
                if(bookSearch.getCheckoutLength() > 0 && checkOutRecordEntry.getBookCopy().getCopyId().equals(retCopyID.getText())){
                    checkOutRecordEntry.setIsAvailable(true);
                    bookSearch.setCheckoutLength(bookSearch.getCheckoutLength() - 1);
                    break;
                }
            }
            if(member.getCheckOutRecord() == null){
                member.setCheckOutRecord(new CheckOutRecord());                
            }else{
                member.getCheckOutRecord().getCheckOutEntrys().clear();
            }
            
            member.getCheckOutRecord().setCheckOutEntrys(checkOutRecordEntrys);
            
            //Update DB records for the new checkout
            pdao.updateRecord(bookSearch, "getISBN", checkoutIsbn.getText());
            pdao.updateRecord(LibraryMember.class, "getID", member.getId());
            
            message.setVisible(true);
            message.setText("Thank you. Book [ISBN: " + bookSearch.getISBN() + ", Title: " + bookSearch.getTitle() + " has been return by " + member.getFirstName() + " " + member.getLastName());            
        }else{
            message.setVisible(true);
            message.setText("No checkout history found for the given entries!!");
        }
    }
}