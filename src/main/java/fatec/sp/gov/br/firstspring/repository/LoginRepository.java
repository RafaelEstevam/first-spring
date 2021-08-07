package fatec.sp.gov.br.firstspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.sp.gov.br.firstspring.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Long>{
    
    public Login findByEmail(String email);

}
