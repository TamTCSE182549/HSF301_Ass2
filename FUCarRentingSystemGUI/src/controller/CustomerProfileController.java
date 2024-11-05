package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import fe.hsf301.ass.pojo.Account;
import fe.hsf301.ass.pojo.CarRental;
import fe.hsf301.ass.pojo.Customer;
import fe.hsf301.ass.repo.AccountRepositoryImpl;
import fe.hsf301.ass.repo.CarRentalRepositoryImpl;
import fe.hsf301.ass.repo.CustomerRepositoryImpl;
import fe.hsf301.ass.repo.IAccountRepository;
import fe.hsf301.ass.repo.ICarRentalRepository;
import fe.hsf301.ass.repo.ICustomerRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerProfileController implements Initializable {

	private int customerID;

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
		loadData();
	}
	
	private void loadData() {
        List<CarRental> carRentals = iCarRentalRepository.findByCustomerID(customerID);
        carRentalModel = FXCollections.observableArrayList();
        if (!carRentals.isEmpty()) {
            carRentalModel.setAll(carRentals);
            tblCarRentals.setItems(carRentalModel);
        }
        setProfile();
    }

	@FXML
	private TextField txtCustomerId;

	@FXML
	private TextField txtCustomerName;

	@FXML
	private TextField txtMobile;

	@FXML
	private DatePicker txtBirthday;

	@FXML
	private TextField txtIdentityCard;

	@FXML
	private TextField txtLicenceNumber;

	@FXML
	private DatePicker txtLicenceDate;

	@FXML
	private TextField txtEmail;
	
	@FXML
	private TextField txtAccountName;

	@FXML
	private TableView<CarRental> tblCarRentals;

	@FXML
	private TableColumn<CarRental, Integer> carID;

	@FXML
	private TableColumn<CarRental, String> carName;

	@FXML
	private TableColumn<CarRental, Double> rentPrice;

	@FXML
	private TableColumn<CarRental, LocalDate> pickupDate;

	@FXML
	private TableColumn<CarRental, LocalDate> returnDate;

	@FXML
	private TableColumn<CarRental, String> status;

	private ObservableList<CarRental> carRentalModel;

	private ICarRentalRepository iCarRentalRepository;
	
	private ICustomerRepository iCustomerRepository;
	
	private IAccountRepository iAccountRepository;

	public CustomerProfileController() {
		iCarRentalRepository = new CarRentalRepositoryImpl("hibernate.cfg.xml");
		iAccountRepository = new AccountRepositoryImpl("hibernate.cfg.xml");
		iCustomerRepository = new CustomerRepositoryImpl("hibernate.cfg.xml");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		carID.setCellValueFactory(new PropertyValueFactory<>("carID"));
		carName.setCellValueFactory(new PropertyValueFactory<>("carName"));
		rentPrice.setCellValueFactory(new PropertyValueFactory<>("rentPrice"));
		pickupDate.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
		returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
		status.setCellValueFactory(new PropertyValueFactory<>("status"));
		tblCarRentals.setItems(carRentalModel);
	}

	public void handleUpdate() {
		Customer customer = new Customer(customerID, 
				txtCustomerName.getText(), 
				txtMobile.getText(), 
				txtBirthday.getValue(), 
				txtIdentityCard.getText(), 
				txtLicenceNumber.getText(), 
				txtLicenceDate.getValue(), 
				txtEmail.getText());
		iCustomerRepository.update(customer);
		
		customer = iCustomerRepository.findById(Integer.valueOf(txtCustomerId.getText()));
		Account account = iAccountRepository.findById(customer.getCustomerID());
		account.setAccountName(txtAccountName.getText());
		iAccountRepository.update(account);
		showAlert("Update Profile", "Success");
		loadData();
	}
	
	public void setProfile() {
		Customer customer = iCustomerRepository.findById(customerID);
		txtCustomerId.setText(String.valueOf(customer.getCustomerID()));
		txtCustomerName.setText(customer.getCustomerName());
		txtMobile.setText(customer.getMobile());
		txtBirthday.setValue(customer.getBirthday());
		txtIdentityCard.setText(customer.getIdentityCard());
		txtLicenceNumber.setText(customer.getLicenceNumber());
		txtLicenceDate.setValue(customer.getLicenceDate());
		txtEmail.setText(customer.getEmail());
		txtAccountName.setText(customer.getAccount().getAccountName());
	}
	
	public void showAlert(String header, String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
