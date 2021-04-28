package course.springdata.introlab.init;

import course.springdata.introlab.entity.Account;
import course.springdata.introlab.entity.User;
import course.springdata.introlab.exceptions.InvalidAccountOperation;
import course.springdata.introlab.exceptions.NonExistingEntityException;
import course.springdata.introlab.service.AccountService;
import course.springdata.introlab.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
public class ConsoleRunner implements CommandLineRunner { // this is pojo
    //when we use @component become a bean and goes to run like main method
    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
        //Withdraw demo
        User user1 = new User("Ivan Petrov", 42);
        Account account1 = new Account(new BigDecimal(3500));

        user1 = userService.register(user1);
        account1 = accountService.createUserAccount(user1, account1);

        try {
            accountService.withdrawMoney(new BigDecimal(2000), account1.getId());
            accountService.depositMoney(new BigDecimal(200), account1.getId());
            accountService.getAllAccounts().forEach(System.out::println);
        }catch (NonExistingEntityException | InvalidAccountOperation e){
            log.error(String.format("Error executing operation for account: %s: %s",account1),e.getMessage());
        }

        System.out.println("------------------------------\n");

        //Transfer demo

        User user2 = new User("Pavel Pavlov", 25);
        Account account2 = new Account(new BigDecimal(23000));

        user2 = userService.register(user2);
        account2 = accountService.createUserAccount(user2, account2);

        try {
            accountService.transferMoney(new BigDecimal(1000), account1.getId(), account2.getId());
        }catch (NonExistingEntityException | InvalidAccountOperation e){
            log.error(String.format("Error executing operation for account: %s:",account1),e.getMessage());
        }
        accountService.getAllAccounts().forEach(System.out::println);
    }
}
