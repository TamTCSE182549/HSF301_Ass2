package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import fe.hsf301.ass.pojo.Customer;
import fe.hsf301.ass.repo.AccountRepositoryImpl;
import fe.hsf301.ass.repo.CustomerRepositoryImpl;
import fe.hsf301.ass.repo.IAccountRepository;
import fe.hsf301.ass.repo.ICustomerRepository;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CustomerController implements Initializable {

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
	private TableView<Customer> tblCustomer;

	@FXML
	private TableColumn<Customer, String> customerId;

	@FXML
	private TableColumn<Customer, String> customerName;

	@FXML
	private TableColumn<Customer, String> mobile;

	@FXML
	private TableColumn<Customer, LocalDate> birthday;

	@FXML
	private TableColumn<Customer, String> identityCard;

	@FXML
	private TableColumn<Customer, String> licenceNumber;

	@FXML
	private TableColumn<Customer, LocalDate> licenceDate;

	@FXML
	private TableColumn<Customer, String> email;

	private ObservableList<Customer> customerModel;
	
	private IAccountRepository iAccountRepository;

	private ICustomerRepository iCustomerRepository;

	public CustomerController() {
		iCustomerRepository = new CustomerRepositoryImpl("hibernate.cfg.xml");
		iAccountRepository = new AccountRepositoryImpl("hibernate.cfg.xml");
		customerModel = FXCollections.observableArrayList(iCustomerRepository.findAll());
	}

	public void showAlert(String header, String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	private void refreshDataTable() {
		this.txtCustomerId.setText("");
		this.txtCustomerName.setText("");
		this.txtMobile.setText("");
		this.txtBirthday.setValue(null);
		this.txtIdentityCard.setText("");
		this.txtLicenceNumber.setText("");
		this.txtLicenceDate.setValue(null);
		this.txtEmail.setText("");
		customerModel.setAll(iCustomerRepository.findAll());
	}

//	private void refreshDataTable() {
//	    this.txtCustomerId.setText("");
//	    this.txtCustomerName.setText("");
//	    this.txtMobile.setText("");
//	    this.txtBirthday.setValue(null);
//	    this.txtIdentityCard.setText("");
//	    this.txtLicenceNumber.setText("");
//	    this.txtLicenceDate.setValue(null);
//	    this.txtEmail.setText("");
////	    customerModel = FXCollections.observableArrayList(iCustomerRepository.findAll());
//	    tblCustomer.getSelectionModel().clearSelection();
//	    customerModel.setAll(iCustomerRepository.findAll());
////	    tblCustomer.setItems(customerModel);
//	}

	public void showCustomer(Customer customer) {
		this.txtCustomerId.setText(String.valueOf(customer.getCustomerID()));
		this.txtCustomerName.setText(customer.getCustomerName());
		this.txtMobile.setText(customer.getMobile());
		this.txtBirthday.setValue(customer.getBirthday());
		this.txtIdentityCard.setText(customer.getIdentityCard());
		this.txtLicenceNumber.setText(customer.getLicenceNumber());
		this.txtLicenceDate.setValue(customer.getLicenceDate());
		this.txtEmail.setText(customer.getEmail());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		customerId.setCellValueFactory(new PropertyValueFactory<>("customerID"));
		customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
		mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
		birthday.setCellValueFactory(new PropertyValueFactory<>("birthday")); // Ngày sinh
		identityCard.setCellValueFactory(new PropertyValueFactory<>("identityCard"));
		licenceNumber.setCellValueFactory(new PropertyValueFactory<>("licenceNumber"));
		licenceDate.setCellValueFactory(new PropertyValueFactory<>("licenceDate")); // Ngày cấp giấy phép
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		tblCustomer.setItems(customerModel);
		tblCustomer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observableValue, Object oldValue, Object index) {
				// TODO Auto-generated method stub
				if (tblCustomer.getSelectionModel().selectedItemProperty() != null) {
					TableViewSelectionModel selectionModel = tblCustomer.getSelectionModel();
					ObservableList selectedCells = selectionModel.getSelectedCells();
					TablePosition tablePosition = (TablePosition) selectedCells.get(0);
					Object customerID = tablePosition.getTableColumn().getCellData(index);
					try {
						Customer customer = iCustomerRepository.findById(Integer.valueOf(customerID.toString()));
						showCustomer(customer);
					} catch (Exception e) {

						showAlert("Information Board !", "Please choose the First Cell !");
					}
				}
			}
		});
	}

	public void handleCreate() {

	}

	public void handleUpdate() {
		Customer customer = new Customer(Integer.valueOf(txtCustomerId.getText()), txtCustomerName.getText(),
				txtMobile.getText(), txtBirthday.getValue(), txtIdentityCard.getText(), txtLicenceNumber.getText(),
				txtLicenceDate.getValue(), txtEmail.getText());
		iCustomerRepository.update(customer);
		refreshDataTable();
	}

	public void handleDelete() {
		int customerId = iCustomerRepository.findById(Integer.valueOf(txtCustomerId.getText())).getAccountID();
		iCustomerRepository.delete(Integer.valueOf(txtCustomerId.getText()));
		iAccountRepository.delete(customerId);
		refreshDataTable();
	}

	public void handleCarProducerManagement(ActionEvent actionEvent) throws IOException {
		((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/CarProducerManagement.fxml"));
		Parent root = loader.load();
		Stage primaryStage = new Stage();
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public void handleCarManagement(ActionEvent actionEvent) throws IOException {
		((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/CarManagement.fxml"));
		Parent root = loader.load();
		Stage primaryStage = new Stage();
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public void handleCarRentalManagement(ActionEvent actionEvent) throws IOException {
		((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/CarRentalManagement.fxml"));
		Parent root = loader.load();
		Stage primaryStage = new Stage();
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
}
