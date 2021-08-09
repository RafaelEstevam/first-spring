package fatec.sp.gov.br.firstspring.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "auth") //table
public class Auth {
    
    @Id
    @Column(name = "id") //column
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto generate of ID
    private long id;

    @Column(name = "token")
    private String token;

    @ManyToMany(mappedBy = "authorizations", fetch = FetchType.LAZY) //mapping of relation
    private Set<Login> logins;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<Login> getLogins() {
        return logins;
    }

    public void setLogins(Set<Login> logins) {
        this.logins = logins;
    }

}
