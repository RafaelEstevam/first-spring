package fatec.sp.gov.br.firstspring.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import fatec.sp.gov.br.firstspring.utils.JwtUtils;

public class AuthFilter extends GenericFilterBean{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        try {
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            String authorization = servletRequest.getHeader(HttpHeaders.AUTHORIZATION); //Pega o header da requisição
        
            if (authorization != null) {
                Authentication credentials = JwtUtils.parseToken(authorization.replaceAll("Bearer ", ""));
                SecurityContextHolder.getContext().setAuthentication(credentials);
                //singleton, ou seja, só pode haver uma única chamada dele em todo o sistema. Get context, pega a instância local. Força a autenticação por token.
            }
            
            chain.doFilter(request, response);
        } catch (Throwable t) { //Caso a autenticação falhe, retorna 401 (Não autorizado)
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        servletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, t.getMessage());
        }
    }
}
