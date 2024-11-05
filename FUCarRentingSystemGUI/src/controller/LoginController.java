package controller;

import java.io.IOException;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	
	public ICustomerRepository iCustomerRepository;
	public IAccountRepository iAccountRepository;
	
	public LoginController() {
		iCustomerRepository = new CustomerRepositoryImpl("hibernate.cfg.xml");
		iAccountRepository = new AccountRepositoryImpl("hibernate.cfg.xml");
	}

	@FXML
	private TextField txtEmail;
	
	@FXML
	private PasswordField txtPassword;
	
	@FXML
	public void handleLogin(ActionEvent actionEvent) throws IOException {
//		txtEmail.setText("tranchitam@gmail.com");
//		txtPassword.setText("123");
		
		txtEmail.setText("johndoe@example.com");
		txtPassword.setText("password123");
		
		Customer customer = iCustomerRepository.login(txtEmail.getText(), txtPassword.getText());
		if(customer != null) {
			if (iAccountRepository.findById(customer.getCustomerID()).getRole().equalsIgnoreCase("ADMIN")) {
				((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/CustomerManagement.fxml"));
				Parent root = loader.load();
				Stage primaryStage = new Stage();
				primaryStage.setScene(new Scene(root));
				primaryStage.show();
			} else {
				showAlert("Login Notification", "Customer");
				((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/CustomerProfile.fxml"));
				Parent root = loader.load();
				Stage primaryStage = new Stage();
				CustomerProfileController customerProfileController = loader.getController();
				customerProfileController.setCustomerID(customer.getCustomerID());
				customerProfileController = loader.getController();
				primaryStage.setScene(new Scene(root));
				primaryStage.show();
			}
		} else {
			showAlert("Login Notification", "Your Account not exist");
		}
	}
	
	public void handleRegister(ActionEvent actionEvent) throws IOException {
		((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/Register.fxml"));
		Parent root = loader.load();
		Stage primaryStage = new Stage();
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
	
	public void showAlert(String header, String content) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
