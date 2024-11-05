package fe.hsf301.ass.repo;

import java.util.List;

import fe.hsf301.ass.pojo.CarProducer;

public interface ICarProducerRepository {
	void save(CarProducer carProducer);
    void delete(int producerID);
    CarProducer findById(int producerID);
    void update(CarProducer carProducer);
    List<CarProducer> findAll();
}
