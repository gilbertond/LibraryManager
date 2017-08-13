package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Book {
	@FXML
	private Label isbn;
	@FXML
	private Label title;
	@FXML
	private Button maxCheckOutLength;
	@FXML
	private Button author;
	@FXML
	private Button back;
	@FXML
	private Button cancel;
	@FXML
	private Label bookCopy;
	@FXML
	private ImageView bookView;

	public void onAuthor() {
		isbn.setText("author");
		application.Author author = new application.Author();
		author.start(new Stage());

	}

	public void onBack() {
		isbn.setText("back");
	}

	public void onCancel() {
		isbn.setText("cancel");
	}

}
