package fe.hsf301.ass.repo;

import java.util.List;

import fe.hsf301.ass.dao.CarProducerDAO;
import fe.hsf301.ass.pojo.CarProducer;

public class CarProducerRepositoryImpl implements ICarProducerRepository{
	
	private CarProducerDAO carProducerDAO;

    public CarProducerRepositoryImpl(String fileConfig) {
        carProducerDAO = new CarProducerDAO(fileConfig);
    }

	@Override
	public void save(CarProducer carProducer) {
		// TODO Auto-generated method stub
		carProducerDAO.save(carProducer);
	}

	@Override
	public void delete(int producerID) {
		// TODO Auto-generated method stub
		carProducerDAO.delete(producerID);
	}

	@Override
	public CarProducer findById(int producerID) {
		// TODO Auto-generated method stub
		return carProducerDAO.findById(producerID);
	}

	@Override
	public void update(CarProducer carProducer) {
		// TODO Auto-generated method stub
		carProducerDAO.update(carProducer);
	}

	@Override
	public List<CarProducer> findAll() {
		// TODO Auto-generated method stub
		return carProducerDAO.findAll();
	}
  
}
