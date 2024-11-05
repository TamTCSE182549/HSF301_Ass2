package controller;

import java.io.IOException;

import fe.hsf301.ass.pojo.Account;
import fe.hsf301.ass.pojo.Customer;
import fe.hsf301.ass.repo.AccountRepositoryImpl;
import fe.hsf301.ass.repo.CustomerRepositoryImpl;
import fe.hsf301.ass.repo.IAccountRepository;
import fe.hsf301.ass.repo.ICustomerRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class RegisterController {
	
	@FXML
	private TextField txtAccountName;
	
	@FXML
	private TextField txtEmail;
	
	@FXML
	private TextField txtPassword;
	
	@FXML
	private TextField txtPasswordAgain;
	
	private IAccountRepository iAccountRepository;
	
	private ICustomerRepository iCustomerRepository;
	
	public RegisterController() {
		iAccountRepository = new AccountRepositoryImpl("hibernate.cfg.xml");
		iCustomerRepository = new CustomerRepositoryImpl("hibernate.cfg.xml");
	}

	public void handleSubmit(ActionEvent actionEvent) throws IOException {
		if(!txtEmail.getText().contains("@gmail.com")) {
			showAlert("Submit Fail", "Your Email not correct Form");
			return;
		}
		
		if(!txtPassword.getText().equals(txtPasswordAgain.getText())) {
			showAlert("Submit Fail", "You again password not same password");
			return;
		}
		Account account = new Account(txtAccountName.getText(), "customer");
		iAccountRepository.save(account);
		
		Customer customer = new Customer(txtEmail.getText(), txtPassword.getText(), account);
		iCustomerRepository.save(customer);
		
		showAlert("Congratulations!!!", "Create Account Success");
		
		((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/Login.fxml"));
		Parent root = loader.load();
		Stage primaryStage = new Stage();
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	public void showAlert(String header, String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public void handleBack(ActionEvent actionEvent) throws IOException {
		((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/Login.fxml"));
		Parent root = loader.load();
		Stage primaryStage = new Stage();
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
}
