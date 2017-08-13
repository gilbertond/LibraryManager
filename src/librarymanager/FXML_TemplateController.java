/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarymanager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lib.dao.PersistenceDAO;
import lib.dao.PersistenceDAOImpl;
import lib.domain.Library;
import javafx.scene.control.*;

public class FXML_TemplateController implements Initializable {
    
    @FXML
    private Label label;    
    @FXML
    private Label uLabel;    
    @FXML
    private Label pLabel;
    
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    
    @FXML
    private Button closeButton;
    
    public void onClose() {
        
    }
    
    PersistenceDAO pdao = new PersistenceDAOImpl();
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Username: "+ username.getText() + " Password: "+password.getText());
        Library lib = (Library)pdao.searchObject(Library.class, "getLibraryname", "MUM1");
        System.out.println("Log in: "+lib);
        label.setText("Hello: "+lib);
    }
    
    @FXML
    private void handleCancelAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
