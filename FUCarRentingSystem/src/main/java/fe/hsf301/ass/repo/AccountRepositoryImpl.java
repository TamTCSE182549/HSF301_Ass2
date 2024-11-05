package fe.hsf301.ass.repo;

import java.util.List;

import fe.hsf301.ass.dao.AccountDAO;
import fe.hsf301.ass.pojo.Account;

public class AccountRepositoryImpl implements IAccountRepository {
	
	private AccountDAO accountDAO;

    public AccountRepositoryImpl(String fileConfig) {
        accountDAO = new AccountDAO(fileConfig);
    }

	@Override
	public void save(Account account) {
		// TODO Auto-generated method stub
		accountDAO.save(account);
	}

	@Override
	public void delete(int accountID) {
		// TODO Auto-generated method stub
		accountDAO.delete(accountID);
	}

	@Override
	public Account findById(int accountID) {
		// TODO Auto-generated method stub
		return accountDAO.findById(accountID);
	}

	@Override
	public void update(Account account) {
		// TODO Auto-generated method stub
		accountDAO.update(account);
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return accountDAO.findAll();
	}
}
