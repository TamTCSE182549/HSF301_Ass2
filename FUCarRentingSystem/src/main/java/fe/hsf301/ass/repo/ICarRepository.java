package fe.hsf301.ass.repo;

import java.util.List;

import fe.hsf301.ass.pojo.Car;

public interface ICarRepository {
	void save(Car car);
    void delete(int carID);
    Car findById(int carID);
    void update(Car car);
    List<Car> findAll();
    List<Car> findByProducerID(int producerID);
}
