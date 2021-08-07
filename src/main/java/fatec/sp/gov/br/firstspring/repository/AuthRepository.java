package fatec.sp.gov.br.firstspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fatec.sp.gov.br.firstspring.entity.Auth;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    
    public Auth findByToken(String token);

}
