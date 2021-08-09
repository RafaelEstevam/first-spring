package fatec.sp.gov.br.firstspring.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import fatec.sp.gov.br.firstspring.entity.Auth;
import fatec.sp.gov.br.firstspring.entity.Login;
import fatec.sp.gov.br.firstspring.repository.AuthRepository;
import fatec.sp.gov.br.firstspring.repository.LoginRepository;

@SpringBootTest
@Transactional
@Rollback //rollback on test data
public class AuthRepositoryTest {

    @Autowired //repository created by spring when to start the tests
	private AuthRepository authRepository;

	@Autowired
	private LoginRepository loginRepository;

    @Test
	void authRepositorySaveOk(){
		Auth auth = new Auth();
		auth.setToken("kaksdkllsdjkk");
		authRepository.save(auth);
		assertNotNull(auth.getId()); //Can't be null to pass at test
	}

	@Test
	void authRepositoryFindByTokenOk(){
		Auth auth = new Auth();
		auth.setToken("teste");
		authRepository.save(auth);
		assertNotNull(authRepository.findByToken(auth.getToken()));
	}

	@Test
	void loginRepositorySaveLoginOk(){
		Auth auth = new Auth();
		auth.setToken("jkadsjhkjj");
		authRepository.save(auth);

		Login login = new Login();
		login.setEmail("teste@testee.com");
		login.setPassword("teste123");
		login.setAuthorizations(new HashSet<Auth>()); //create a list of authorizations
		login.getAuthorizations().add(auth); //add at list the authorization created
		loginRepository.save(login);

		assertFalse(authRepository.findByLoginsEmail(login.getEmail()).isEmpty());

	}
    
}
