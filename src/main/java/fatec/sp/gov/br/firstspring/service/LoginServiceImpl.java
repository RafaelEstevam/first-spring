package fatec.sp.gov.br.firstspring.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.sp.gov.br.firstspring.entity.Auth;
import fatec.sp.gov.br.firstspring.entity.Login;
import fatec.sp.gov.br.firstspring.repository.AuthRepository;
import fatec.sp.gov.br.firstspring.repository.LoginRepository;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private AuthRepository authRepository;


    @Override
    public Auth getAuthorizationsById(Long id) {
        Auth auth = authRepository.getById(id);
        if(auth != null){
            return auth;
        }
        throw new RuntimeException("Authrozation not found");
    }

    @Override
    public Login getLoginByEmail(String email) {
        Login login = loginRepository.findByEmail(email);
        if(login != null){
            return login;
        }
        throw new RuntimeException("User not found");
    }

    @Override
    public Login getLoginById(Long id) {
        Optional<Login> login = loginRepository.findById(id);
        if(login.isPresent()){
            return login.get();
        }
        throw new RuntimeException("User not found");
    }

    @Override
    public List<Login> getAll() {
        List<Login> logins = new ArrayList<Login>();
        for(Login login: loginRepository.findAll()){
            logins.add(login);
        }
        return logins;
    }

    @Override
    public Login postLogin(Login login) {

        Auth auth = authRepository.findByPermission("USER");

        login.setEmail(login.getEmail());
		login.setPassword(login.getPassword());
		login.setAuthorizations(new HashSet<Auth>());
		login.getAuthorizations().add(auth);
        loginRepository.save(login);

        Login newLogin = loginRepository.getById(login.getId());

        if(auth != null && newLogin != null ){
            return newLogin;
        }

        throw new RuntimeException("User can't be save");

    }

    @Override
    public void deleteLoginById(Long id) {
        loginRepository.deleteById(id);
    }
    
}
