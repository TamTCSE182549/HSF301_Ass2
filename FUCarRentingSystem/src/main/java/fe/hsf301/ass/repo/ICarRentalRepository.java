package fe.hsf301.ass.repo;

import java.util.List;

import fe.hsf301.ass.pojo.CarRental;
import fe.hsf301.ass.pojo.CarRentalId;

public interface ICarRentalRepository {
	void save(CarRental carRental);

	void delete(CarRentalId rentalID);

	CarRental findById(CarRentalId rentalID);

	void update(CarRental carRental);

	List<CarRental> findAll();
	
	List<CarRental> findByCustomerID(Integer customerID);
	
	List<CarRental> findByCarID(Integer carID);
}
