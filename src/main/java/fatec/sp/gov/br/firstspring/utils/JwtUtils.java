package fatec.sp.gov.br.firstspring.utils;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import fatec.sp.gov.br.firstspring.dto.LoginDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {

    private static final String KEY = "spring.jwt.sec"; //chave para validar o token
    
    public static String generateToken(Authentication user) throws JsonProcessingException {
        
        ObjectMapper mapper = new ObjectMapper();
        LoginDto userWithoutPass = new LoginDto();

        userWithoutPass.setEmail(user.getName());

        //Se tiver uma autorização, pega a primeira e salva no usuário.
        if(!user.getAuthorities().isEmpty()){
            userWithoutPass.setPermission(user.getAuthorities().iterator().next().getAuthority());
        }

        //Gerar Json dos dados do usuário
        String userJson = mapper.writeValueAsString(userWithoutPass);

        Date now = new Date();
        Long hour = (1000L * 60L * 60L) * 24; //One Day

        return Jwts.builder().claim("userDetails", userJson)
            .setIssuer("br.gov.sp.fatec")//Quem está gerando
            .setSubject(user.getName()) //Para quem se destina
            .setExpiration(new Date(now.getTime() + hour))//Tempo de expiração (24 horas)
            .signWith(SignatureAlgorithm.HS512, KEY)//Assinatura do JWT
            .compact(); //Retorna um token com os dados 
      }
    
      public static Authentication parseToken(String token) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        //Abrir o token e pega os detalhes dele
        String credentialsJson = Jwts.parser()//Responsavel por validar o Token
            .setSigningKey(KEY)
            .parseClaimsJws(token)//Abrir o token
            .getBody()//Ler o corpo
            .get("userDetails",String.class);//Pergar dados do usuário
        
        //Transforma os dados na clase Login
        LoginDto login = mapper.readValue(credentialsJson, LoginDto.class);

        UserDetails userDetails = User.builder().username(login.getEmail()).password("secret")
            .authorities(login.getPermission()).build();
            
        return new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword(),
            userDetails.getAuthorities());
      }

}
