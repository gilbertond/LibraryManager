package controllers;

import entities.Staff;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lib.dao.PersistenceDAO;
import lib.dao.PersistenceDAOImpl;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import lib.util.Helper;

public class Login {

    @FXML
    private TextField login;
    @FXML
    private PasswordField password;
    @FXML
    private Button submit;
    @FXML
    private Button cancel;
    @FXML
    private Label message;
    
    PersistenceDAO pdao = new PersistenceDAOImpl();

    public void onSubmit() throws IOException {
        File file = new File(pdao.getDB_Dir() + Staff.class.getSimpleName() + ".dbo");
        if(!file.exists()){
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.close();
            
            String[] roles = {"LIBRARIAN", "ADMIN", "BOTH"};
            Staff staff = new Staff("root", "fatima", Arrays.asList(roles), null, null, null, null);
            pdao.saveRecordOrUpdate(staff);
        }
        
        Staff staff = (Staff) pdao.searchObject(Staff.class, new String[]{"getUserName", "getPassword"}, new Object[]{login.getText(), password.getText()});
        if (staff == null) {
            message.setText("Wrong username or password, please retry!");
        } else {
            Helper.staff = staff;
            application.Admin admin = new application.Admin();
            admin.start(new Stage());
        }
    }
    
    public void onCancel() {
        message.setText("Cancel");
    }
}
