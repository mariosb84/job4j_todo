package ru.job4j.filter;

import org.springframework.stereotype.Component;
import ru.job4j.utilites.Any;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (Any.any(uri)) {
            chain.doFilter(req, res);
            return;
        }

        if (req.getSession().getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/users/loginPage");
            return;
        }
        chain.doFilter(req, res);
    }

}