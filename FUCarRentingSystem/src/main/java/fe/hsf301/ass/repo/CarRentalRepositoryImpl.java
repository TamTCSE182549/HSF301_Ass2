package fe.hsf301.ass.repo;

import java.util.List;

import fe.hsf301.ass.dao.CarRentalDAO;
import fe.hsf301.ass.pojo.CarRental;
import fe.hsf301.ass.pojo.CarRentalId;

public class CarRentalRepositoryImpl implements ICarRentalRepository  {
	
	private CarRentalDAO carRentalDAO;

    public CarRentalRepositoryImpl(String fileConfig) {
        carRentalDAO = new CarRentalDAO(fileConfig);
    }

	@Override
	public void save(CarRental carRental) {
		// TODO Auto-generated method stub
		carRentalDAO.save(carRental);
	}

	@Override
	public void delete(CarRentalId rentalID) {
		// TODO Auto-generated method stub
		carRentalDAO.delete(rentalID);
	}

	@Override
	public CarRental findById(CarRentalId rentalID) {
		// TODO Auto-generated method stub
		return carRentalDAO.findById(rentalID);
	}

	@Override
	public void update(CarRental carRental) {
		// TODO Auto-generated method stub
		carRentalDAO.update(carRental);
	}

	@Override
	public List<CarRental> findAll() {
		// TODO Auto-generated method stub
		return carRentalDAO.findAll();
	}

	@Override
	public List<CarRental> findByCustomerID(Integer customerID) {
		// TODO Auto-generated method stub
		return carRentalDAO.findRentalsByCustomerID(customerID);
	}

	@Override
	public List<CarRental> findByCarID(Integer carID) {
		// TODO Auto-generated method stub
		return carRentalDAO.findRentalsByCarID(carID);
	}

}
