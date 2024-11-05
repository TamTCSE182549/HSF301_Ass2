package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import fe.hsf301.ass.pojo.Car;
import fe.hsf301.ass.pojo.CarProducer;
import fe.hsf301.ass.pojo.CarRental;
import fe.hsf301.ass.repo.CarProducerRepositoryImpl;
import fe.hsf301.ass.repo.CarRentalRepositoryImpl;
import fe.hsf301.ass.repo.CarRepositoryImpl;
import fe.hsf301.ass.repo.ICarProducerRepository;
import fe.hsf301.ass.repo.ICarRentalRepository;
import fe.hsf301.ass.repo.ICarRepository;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CarManagementController implements Initializable {

	@FXML
	private TextField txtCarID;

	@FXML
	private TextField txtCarName;

	@FXML
	private TextField txtCarModelYear;

	@FXML
	private TextField txtColor;

	@FXML
	private TextField txtCapacity;

	@FXML
	private TextField txtDescription;

	@FXML
	private DatePicker txtImportDate;

	@FXML
	private TextField txtRentPrice;

	@FXML
	private TextField txtStatus;

	@FXML
	private TextField txtProducerID1; // Thêm trường cho producerID

	@FXML
	private TextField txtProducerID2;

	@FXML
	private TextField txtProducerName;

	@FXML
	private TextField txtAddress;

	@FXML
	private TextField txtCountry;

	@FXML
	private TableView<Car> tblCars;

	@FXML
	private TableColumn<Car, String> carID;

	@FXML
	private TableColumn<Car, String> carName;

	@FXML
	private TableColumn<Car, String> carModelYear;

	@FXML
	private TableColumn<Car, String> color;

	@FXML
	private TableColumn<Car, String> capacity;

	@FXML
	private TableColumn<Car, String> description;

	@FXML
	private TableColumn<Car, LocalDate> importDate;

	@FXML
	private TableColumn<Car, String> rentPrice;

	@FXML
	private TableColumn<Car, String> status;

	@FXML
	private TableColumn<Car, String> producerName1; // Hiển thị tên nhà sản xuất

	@FXML
	private TableView<CarProducer> tblProducers;

	@FXML
	private TableColumn<CarProducer, Integer> producerID;

	@FXML
	private TableColumn<CarProducer, String> producerName2;

	@FXML
	private TableColumn<CarProducer, String> address;

	@FXML
	private TableColumn<CarProducer, String> country;

	private ObservableList<Car> carModel;
	private ObservableList<CarProducer> carProducerModel;

	private ICarRepository iCarRepository;
	private ICarProducerRepository iCarProducerRepository;
	private ICarRentalRepository iCarRentalRepository;

	public CarManagementController() {
		iCarRepository = new CarRepositoryImpl("hibernate.cfg.xml");
		iCarProducerRepository = new CarProducerRepositoryImpl("hibernate.cfg.xml");
		iCarRentalRepository = new CarRentalRepositoryImpl("hibernate.cfg.xml");
		carModel = FXCollections.observableArrayList(iCarRepository.findAll());
		carProducerModel = FXCollections.observableArrayList(iCarProducerRepository.findAll());
	}

	public void showAlert(String header, String content) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	private void refreshDataTable() {
		this.txtCarID.setText("");
		this.txtCarName.setText("");
		this.txtCarModelYear.setText("");
		this.txtColor.setText("");
		this.txtCapacity.setText("");
		this.txtDescription.setText("");
		this.txtImportDate.setValue(null);
		this.txtRentPrice.setText("");
		this.txtStatus.setText("");
		this.txtProducerID1.setText("");

		this.txtProducerID2.setText("");
		this.txtProducerName.setText("");
		this.txtAddress.setText("");
		this.txtCountry.setText("");

		carProducerModel.setAll(iCarProducerRepository.findAll());
		carModel.setAll(iCarRepository.findAll());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		carID.setCellValueFactory(new PropertyValueFactory<>("carID"));
		carName.setCellValueFactory(new PropertyValueFactory<>("carName"));
		carModelYear.setCellValueFactory(new PropertyValueFactory<>("carModelYear"));
		color.setCellValueFactory(new PropertyValueFactory<>("color"));
		capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
		description.setCellValueFactory(new PropertyValueFactory<>("description"));
		importDate.setCellValueFactory(new PropertyValueFactory<>("importDate"));
		rentPrice.setCellValueFactory(new PropertyValueFactory<>("rentPrice"));
		status.setCellValueFactory(new PropertyValueFactory<>("status"));
		producerName1.setCellValueFactory(new PropertyValueFactory<>("producerName"));

		producerID.setCellValueFactory(new PropertyValueFactory("producerID"));
		producerName2.setCellValueFactory(new PropertyValueFactory("producerName"));
		address.setCellValueFactory(new PropertyValueFactory("address"));
		country.setCellValueFactory(new PropertyValueFactory("country"));

		tblCars.setItems(carModel);
		tblCars.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observableValue, Object oldValue, Object index) {
				// TODO Auto-generated method stub
				if (tblCars.getSelectionModel().selectedItemProperty() != null) {
					TableViewSelectionModel selectionModel = tblCars.getSelectionModel();
					ObservableList selectedCells = selectionModel.getSelectedCells();
					TablePosition tablePosition = (TablePosition) selectedCells.get(0);
					Object carID = tablePosition.getTableColumn().getCellData(index);
					try {
						Car car = iCarRepository.findById(Integer.valueOf(carID.toString()));
						showCar(car);
					} catch (Exception e) {

						showAlert("Information Board !", "Please choose the First Cell !");
					}
				}
			}
		});

		tblProducers.setItems(carProducerModel);
		tblProducers.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observableValue, Object oldValue, Object index) {
				// TODO Auto-generated method stub
				if (tblProducers.getSelectionModel().selectedItemProperty() != null) {
					TableViewSelectionModel selectionModel = tblProducers.getSelectionModel();
					ObservableList selectedCells = selectionModel.getSelectedCells();
					TablePosition tablePosition = (TablePosition) selectedCells.get(0);
					Object carProducerID = tablePosition.getTableColumn().getCellData(index);
					try {
						CarProducer carProducer = iCarProducerRepository
								.findById(Integer.valueOf(carProducerID.toString()));
						showCarProducer(carProducer);
					} catch (Exception e) {

						showAlert("Information Board !", "Please choose the First Cell !");
					}
				}
			}
		});
	}

	private void showCar(Car car) {
		txtCarID.setText(String.valueOf(car.getCarID()));
		txtCarName.setText(car.getCarName());
		txtCarModelYear.setText(String.valueOf(car.getCarModelYear()));
		txtColor.setText(car.getColor());
		txtCapacity.setText(String.valueOf(car.getCapacity()));
		txtDescription.setText(car.getDescription());
		txtImportDate.setValue(car.getImportDate());
		txtRentPrice.setText(String.valueOf(car.getRentPrice()));
		txtStatus.setText(car.getStatus());
		txtProducerID1.setText(String.valueOf(car.getProducer().getProducerID()));
	}

	private void showCarProducer(CarProducer carProducer) {
		txtProducerID2.setText(String.valueOf(carProducer.getProducerID()));
		txtProducerName.setText(carProducer.getProducerName());
		txtAddress.setText(carProducer.getAddress());
		txtCountry.setText(carProducer.getCountry());
	}

	public void handleCreateProducer() {
		// TODO Auto-generated method stub
		CarProducer carProducer = new CarProducer(txtProducerName.getText(), txtAddress.getText(),
				txtCountry.getText());
		iCarProducerRepository.save(carProducer);
		refreshDataTable();
	}
	
	public void handleUpdateProducer() {
		CarProducer carProducer = new CarProducer(Integer.parseInt(txtProducerID2.getText()) , txtProducerName.getText(), txtAddress.getText(),
				txtCountry.getText());
		iCarProducerRepository.update(carProducer);
		refreshDataTable();
	}
	
	public void handleDeleteProducer() {
		List<Car> cars = iCarRepository.findByProducerID(Integer.parseInt(txtProducerID2.getText()));
		if(!cars.isEmpty()) {
			showAlert("Delete Car Producer", "Fail");
		} else {
			iCarProducerRepository.delete(Integer.parseInt(txtProducerID2.getText()));	
		}
		refreshDataTable();
	}

	public void handleCreateCar() {
		CarProducer carProducer = iCarProducerRepository.findById(Integer.parseInt(txtProducerID1.getText()));
		if (carProducer == null) {
			showAlert("Create Car Fail", "Car Producer ID not FOUND");
			return;
		}
		Car car = new Car(txtCarName.getText(), Integer.parseInt(txtCarModelYear.getText()), txtColor.getText(),
				Integer.parseInt(txtCapacity.getText()), txtDescription.getText(), txtImportDate.getValue(),
				Double.parseDouble(txtRentPrice.getText()), txtStatus.getText(), carProducer);
		iCarRepository.save(car);
		refreshDataTable();
	}
	
	public void handleUpdateCar() {
		Car car = iCarRepository.findById(Integer.parseInt(txtCarID.getText()));
		if(car==null) {
			showAlert("Update Car Fail", "Car not found");
			return;
		}
		CarProducer carProducer = iCarProducerRepository.findById(Integer.parseInt(txtProducerID1.getText()));
		if (carProducer == null) {
			showAlert("Update Car Fail", "Car Producer not FOUND");
			return;
		}
		car = new Car(Integer.parseInt(txtCarID.getText()), txtCarName.getText(), Integer.parseInt(txtCarModelYear.getText()), txtColor.getText(),
				Integer.parseInt(txtCapacity.getText()), txtDescription.getText(), txtImportDate.getValue(),
				Double.parseDouble(txtRentPrice.getText()), txtStatus.getText(), carProducer); 
		iCarRepository.update(car);
		refreshDataTable();
	}
	
	public void handleDeleteCar() {
		Car car = iCarRepository.findById(Integer.parseInt(txtCarID.getText()));
		if(car==null) {
			showAlert("Delete Car Fail", "Car not found");
			return;
		}
		List<CarRental> carRentals = iCarRentalRepository.findByCarID(car.getCarID());
		if(!carRentals.isEmpty()) {
			car.setStatus("deleted");
			iCarRepository.update(car);
		} else {
			iCarRepository.delete(car.getCarID());
		}
		refreshDataTable();
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
