package core.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rrr.core.models.entities.Account;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Brandon Paw on 7/4/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
public class AccountRepoTest {

//    @Autowired
//    private AccountRepo repo;
//
//    private Account account;
//
//    @Before
//    @Transactional
//    @Rollback(false)
//    public void setup() {
//
//        account = new Account();
//        account.setUsername("name");
//        account.setPassword("password");
//        account.setEmail("test@test.com");
//        repo.createAccount(account);
//    }

    @Test
    public void testFind() {

//        assertNotNull(repo.findAccount(account.getId()));
    }
}
