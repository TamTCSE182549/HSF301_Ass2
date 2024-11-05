package fe.hsf301.ass.repo;

import java.util.List;

import fe.hsf301.ass.dao.CarDAO;
import fe.hsf301.ass.pojo.Car;

public class CarRepositoryImpl implements ICarRepository{
	
	private CarDAO carDAO;

    public CarRepositoryImpl(String fileConfig) {
        carDAO = new CarDAO(fileConfig);
    }

	@Override
	public void save(Car car) {
		// TODO Auto-generated method stub
		carDAO.save(car);
	}

	@Override
	public void delete(int carID) {
		// TODO Auto-generated method stub
		carDAO.delete(carID);
	}

	@Override
	public Car findById(int carID) {
		// TODO Auto-generated method stub
		return carDAO.findById(carID);
	}

	@Override
	public void update(Car car) {
		// TODO Auto-generated method stub
		carDAO.update(car);
	}

	@Override
	public List<Car> findAll() {
		// TODO Auto-generated method stub
		return carDAO.findAll();
	}

	@Override
	public List<Car> findByProducerID(int producerID) {
		// TODO Auto-generated method stub
		return carDAO.findCarByProducerID(producerID);
	}

}
