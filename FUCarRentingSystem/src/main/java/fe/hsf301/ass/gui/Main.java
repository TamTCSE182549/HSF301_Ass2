package fe.hsf301.ass.gui;

import fe.hsf301.ass.pojo.Account;
import fe.hsf301.ass.pojo.CarProducer;
import fe.hsf301.ass.pojo.Customer;
import fe.hsf301.ass.repo.ICarProducerRepository;
import fe.hsf301.ass.repo.ICustomerRepository;
import fe.hsf301.ass.repo.AccountRepositoryImpl;
import fe.hsf301.ass.repo.CarProducerRepositoryImpl;
import fe.hsf301.ass.repo.CustomerRepositoryImpl;
import fe.hsf301.ass.repo.IAccountRepository;

public class Main {

	public static void main(String[] args) {
		
		String fileName = "hibernate.cfg.xml";
		
//		ICarProducerRepository carProducerRepository = new CarProducerRepositoryImpl(fileName);
//		CarProducer carProducer = new CarProducer();
//		carProducer.setProducerName("Tesla");
//		carProducer.setAddress("3500 Deer Creek Road");
//		carProducer.setCountry("USA");
//		carProducerRepository.save(carProducer);
		
		IAccountRepository iAccountRepository = new AccountRepositoryImpl(fileName);
		Account account = new Account("Tien Thuan", "Customer");
		iAccountRepository.save(account);
	}

}
