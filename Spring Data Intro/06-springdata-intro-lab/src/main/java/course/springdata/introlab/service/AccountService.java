package course.springdata.introlab.service;

import course.springdata.introlab.entity.Account;
import course.springdata.introlab.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface AccountService {
  Account  createUserAccount(User user, Account account);
  void depositMoney(BigDecimal amount, Long accountId);
  void withdrawMoney(BigDecimal amount, Long accountId);
  void transferMoney(BigDecimal amount, Long fromAccountId, Long toAccountId);

  List<Account> getAllAccounts();
}
