package fatec.sp.gov.br.firstspring.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import fatec.sp.gov.br.firstspring.entity.Auth;
import fatec.sp.gov.br.firstspring.repository.AuthRepository;

@SpringBootTest
@Transactional
@Rollback //rollback on test data
public class AuthRepositoryTests {

    @Autowired //repository created by spring when to start the tests
	private AuthRepository authRepository;

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
    
}
