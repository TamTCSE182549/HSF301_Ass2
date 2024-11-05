package fe.hsf301.ass.repo;

import java.util.List;

import fe.hsf301.ass.pojo.Account;

public interface IAccountRepository {
	void save(Account account);
    void delete(int accountID);
    Account findById(int accountID);
    void update(Account account);
    List<Account> findAll();
}
