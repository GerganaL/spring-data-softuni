package course.springdata.introlab.service.impl;

import course.springdata.introlab.dao.AccountRepository;
import course.springdata.introlab.entity.Account;
import course.springdata.introlab.entity.User;
import course.springdata.introlab.exceptions.InvalidAccountOperation;
import course.springdata.introlab.exceptions.NonExistingEntityException;
import course.springdata.introlab.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {
    // @Autowired  field injection
    private AccountRepository accountRepo;

    @Autowired //property base injection
    public void setAccountRepo(AccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public Account createUserAccount(User user, Account account) {
        account.setId(null);
        account.setUser(user);
        user.getAccounts().add(account);
        return accountRepo.save(account);
    }

    @Override
    public void depositMoney(BigDecimal amount, Long accountId) {
        Account account = accountRepo.findById(accountId).orElseThrow(() ->
                new NonExistingEntityException(
                        String.format("Entity wiht ID:%s does not exist.", accountId)
                ));
        account.setBalance(account.getBalance().add(amount));
    }

    @Override
    public void withdrawMoney(BigDecimal amount, Long accountId) {
        Account account = accountRepo.findById(accountId).orElseThrow(() ->
                new NonExistingEntityException(
                        String.format("Entity wiht ID:%s does not exist.", accountId)
                ));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new InvalidAccountOperation(
                    String.format("Account ID:%s balance (%s) is less than required withdraw amount: %s."
                            , accountId, account.getBalance(), amount));
        }
        account.setBalance(account.getBalance().subtract(amount));
    } //commit transaction

    @Override
    public void transferMoney(BigDecimal amount, Long fromAccountId, Long toAccountId) {
        withdrawMoney(amount,fromAccountId);
        depositMoney(amount,toAccountId);
    }//commit transaction

    @Override
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }
}
