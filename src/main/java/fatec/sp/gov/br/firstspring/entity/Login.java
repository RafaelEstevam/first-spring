package fatec.sp.gov.br.firstspring.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "login")
public class Login {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY) //how you get data
    @JoinTable(name = "auth_login",
        joinColumns = {
            @JoinColumn(name = "login_id") //link collumn with this class (login)
        },
        inverseJoinColumns = {
            @JoinColumn( name = "auth_id") //link collumn with external table (auth)
        }
    )
    private Set<Auth> authorizations;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Auth> getAuthorizations() {
        return authorizations;
    }

    public void setAuthorizations(Set<Auth> authorizations) {
        this.authorizations = authorizations;
    }

}