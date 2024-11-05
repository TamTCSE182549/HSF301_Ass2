package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import fe.hsf301.ass.pojo.Car;
import fe.hsf301.ass.pojo.CarRental;
import fe.hsf301.ass.pojo.CarRentalId;
import fe.hsf301.ass.pojo.Customer;
import fe.hsf301.ass.repo.CarRentalRepositoryImpl;
import fe.hsf301.ass.repo.CarRepositoryImpl;
import fe.hsf301.ass.repo.CustomerRepositoryImpl;
import fe.hsf301.ass.repo.ICarRentalRepository;
import fe.hsf301.ass.repo.ICarRepository;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CarRentalManagementController implements Initializable{
	@FXML
	private TextField txtCarID;
	
	@FXML
	private TextField txtCustomerID;
	
	@FXML
	private TextField txtRentPrice;
	
	@FXML
	private DatePicker txtPickupDate;
	
	@FXML
	private DatePicker txtReturnDate;
	
	@FXML
	private TextField txtStatus;

	@FXML
	private TableView<CarRental> tblCarRentals;
	
	@FXML
	private TableColumn<CarRental, Integer> carID;
	
	@FXML
	private TableColumn<CarRental, String> carName;
	
	@FXML
	private TableColumn<CarRental, Integer> customerID;
	
	@FXML
	private TableColumn<CarRental, String> customerName;
	
	@FXML
	private TableColumn<CarRental, Double> rentPrice;
	
	@FXML
	private TableColumn<CarRental, LocalDate> pickupDate;
	
	@FXML
	private TableColumn<CarRental, LocalDate> returnDate;
	
	@FXML
	private TableColumn<CarRental, String> status;
	
	private ObservableList<CarRental> carRentalModel;
	
	private ICarRepository iCarRepository;
	
	private ICustomerRepository iCustomerRepository;
	
	private ICarRentalRepository iCarRentalRepository;

	public CarRentalManagementController() {
		iCarRentalRepository = new CarRentalRepositoryImpl("hibernate.cfg.xml");
		iCarRepository = new CarRepositoryImpl("hibernate.cfg.xml");
		iCustomerRepository = new CustomerRepositoryImpl("hibernate.cfg.xml");
		carRentalModel = FXCollections.observableArrayList(iCarRentalRepository.findAll());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		carID.setCellValueFactory(new PropertyValueFactory<>("carID"));
		carName.setCellValueFactory(new PropertyValueFactory<>("carName"));
		customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
		customerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        rentPrice.setCellValueFactory(new PropertyValueFactory<>("rentPrice"));
        pickupDate.setCellValueFactory(new PropertyValueFactory<>("pickupDate"));
        returnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
		tblCarRentals.setItems(carRentalModel);
//		tblCarRentals.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
//			@Override
//			public void changed(ObservableValue observableValue, Object oldValue, Object index) {
//				// TODO Auto-generated method stub
//				if (tblCarRentals.getSelectionModel().selectedItemProperty() != null) {
//					TableViewSelectionModel selectionModel = tblCarRentals.getSelectionModel();
//					ObservableList selectedCells = selectionModel.getSelectedCells();
//					TablePosition tablePosition = (TablePosition) selectedCells.get(0);
//					Object carRental = tablePosition.getTableColumn().getCellData(index);
//					try {
//						CarRental carRentalObject = iCarRentalRepository.findById(new CarRentalId(Integer.parseInt(txtCustomerID.getText()), Integer.parseInt(txtCarID.getText())));
//						showCarRental(carRentalObject);
//					} catch (Exception e) {
//						showAlert("Information Board !", "Please choose the First Cell !");
//					}
//				}
//			}
//		});
	}
	
	public void showAlert(String header, String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public void showCarRental(CarRental carRental) {
		txtCarID.setText(String.valueOf(carRental.getCar().getCarID()));
		txtCustomerID.setText(String.valueOf(carRental.getCustomer().getCustomerID()));
		txtRentPrice.setText(String.valueOf(carRental.getRentPrice()));
		txtPickupDate.setValue(carRental.getPickupDate());
		txtReturnDate.setValue(carRental.getReturnDate());
		txtStatus.setText(carRental.getStatus());
	}
	
	public void handleCreate() {
		Customer customer = iCustomerRepository.findById(Integer.parseInt(txtCustomerID.getText()));
		if(customer==null) {
			showAlert("Create Rental Fail", "Customer not FOUND");
			return;
		}
		Car car = iCarRepository.findById(Integer.parseInt(txtCarID.getText()));
		if(car==null) {
			showAlert("Create Rental Fail", "Car not FOUND");
			return;
		}
		CarRentalId carRentalId = new CarRentalId(Integer.parseInt(txtCustomerID.getText()), Integer.parseInt(txtCarID.getText()));
		CarRental carRental = new CarRental(carRentalId, txtPickupDate.getValue(), txtReturnDate.getValue(), Double.parseDouble(txtRentPrice.getText()), txtStatus.getText(), customer, car);
		iCarRentalRepository.save(carRental);
		refeshDataTable();
	}
	
	public void handleUpdate() {
		Customer customer = iCustomerRepository.findById(Integer.parseInt(txtCustomerID.getText()));
		if(customer==null) {
			showAlert("Create Rental Fail", "Customer not FOUND");
			return;
		}
		Car car = iCarRepository.findById(Integer.parseInt(txtCarID.getText()));
		if(car==null) {
			showAlert("Create Rental Fail", "Car not FOUND");
			return;
		}
		CarRentalId carRentalId = new CarRentalId(Integer.parseInt(txtCustomerID.getText()), Integer.parseInt(txtCarID.getText()));
		CarRental carRental = new CarRental(carRentalId, txtPickupDate.getValue(), txtReturnDate.getValue(), Double.parseDouble(txtRentPrice.getText()), txtStatus.getText(), customer, car);
		iCarRentalRepository.update(carRental);
		refeshDataTable();
	}
	
	private void refeshDataTable() {
        txtCarID.setText("");
        txtCustomerID.setText("");
        txtRentPrice.setText("");
        txtPickupDate.setValue(null);
        txtReturnDate.setValue(null);
        txtStatus.setText("");
        carRentalModel.setAll(iCarRentalRepository.findAll());
    }
	
	
	public void handleCustomerManagement(ActionEvent actionEvent) throws IOException {
		((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../application/CustomerManagement.fxml"));
		Parent root = loader.load();
		Stage primaryStage = new Stage();
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}
}
