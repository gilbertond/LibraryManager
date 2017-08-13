package controllers;

import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import entities.BookCopy;
import entities.CheckOutRecord;
import entities.CheckOutRecordEntry;

public class LibraryMember {
	/*
	 * My profil tab
	 */
	@FXML
	private Label id;
	@FXML
	private Label firstName;
	@FXML
	private Label lastName;
	@FXML
	private Label phone;
	@FXML
	private Label adresse;
	@FXML
	private TableView<CheckOutRecordEntry> checkOutRecordEntries;
	@FXML
	private TableColumn<CheckOutRecordEntry, Integer> isbn;
	@FXML
	private TableColumn<CheckOutRecordEntry, String> title;
	@FXML
	private TableColumn<CheckOutRecordEntry, String> checkOutDate;
	@FXML
	private TableColumn<CheckOutRecordEntry, String> dueDate;

	private ObservableList<CheckOutRecordEntry> data;

	public void initialize() {
		isbn.setCellValueFactory(new PropertyValueFactory<CheckOutRecordEntry, Integer>(
				"isbn"));
		title.setCellValueFactory(new PropertyValueFactory<CheckOutRecordEntry, String>(
				"title"));
		checkOutDate
				.setCellValueFactory(new PropertyValueFactory<CheckOutRecordEntry, String>(
						"checkOutDateP"));
		dueDate.setCellValueFactory(new PropertyValueFactory<CheckOutRecordEntry, String>(
				"dueDateP"));
		data = FXCollections.observableArrayList();
//		data.add(new entities.CheckOutRecordEntry());
		// data.add(new entities.Book(123, "wer", "11"));
		// data.add(new entities.Book(123, "wer", "11"));
		// import data from book file
		checkOutRecordEntries.setItems(data);
	}
}
