package fatec.sp.gov.br.firstspring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fatec.sp.gov.br.firstspring.entity.Auth;
import fatec.sp.gov.br.firstspring.repository.AuthRepository;

@Service("authService")
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public List<Auth> getAll() {
        List<Auth> auths = new ArrayList<Auth>();
        for(Auth auth: authRepository.findAll()){
            auths.add(auth);
        }
        return auths;
    }

    @Override
    public Auth getAuthByName(String permission) {
        Auth auth = authRepository.findByPermission(permission);
        if(auth != null){
            return auth;
        }
        throw new RuntimeException("Permission not found");
    }

}
