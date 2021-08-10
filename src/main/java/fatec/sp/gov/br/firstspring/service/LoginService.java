package fatec.sp.gov.br.firstspring.service;

import java.util.List;

import fatec.sp.gov.br.firstspring.entity.Auth;
import fatec.sp.gov.br.firstspring.entity.Login;

public interface LoginService {

    public List<Login> getAll();

    public Login getLoginById(Long id);

    public Login getLoginByEmail(String email);

    public Login postLogin(Login login);

    public Auth getAuthorizationsById(Long id);

}
