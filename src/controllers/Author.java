package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import entities.Book;

public class Author {
	@FXML
	private Label firstName;
	@FXML
	private Label lastName;
	@FXML
	private Label credentials;
	@FXML
	private Label phone;
	@FXML
	private Label adresse;
	@FXML
	private TableView<Book> books;
	@FXML
	private TableColumn<Book, Integer> isbn;
	@FXML
	private TableColumn<Book, String> title;

	private ObservableList<Book> data;

	public void initialize() {
		isbn.setCellValueFactory(new PropertyValueFactory<Book, Integer>("isbn"));
		title.setCellValueFactory(new PropertyValueFactory<Book, String>(
				"title"));
		// data = FXCollections.observableArrayList();
		// data.add(new entities.Book(123, "wer", "11"));
		// data.add(new entities.Book(123, "wer", "11"));
		// data.add(new entities.Book(123, "wer", "11"));
		// data.add(new entities.Book(123, "wer", "11"));
		// import data from book file
		books.setItems(data);
	}

	public void onBack() {

	}

	public void popTable() {
	}
}
