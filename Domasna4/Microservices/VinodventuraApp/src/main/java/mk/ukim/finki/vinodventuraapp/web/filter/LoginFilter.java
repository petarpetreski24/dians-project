package mk.ukim.finki.vinodventuraapp.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.vinodventuraapp.model.User;

import java.io.IOException;

@WebFilter(filterName = "auth-filter", urlPatterns = "*",
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
        initParams = {@WebInitParam(name = "ignore-path", value = "/login"),
        @WebInitParam(name = "register-path",value = "/register")})
public class LoginFilter implements Filter {

    private String ignorePath;
    private String registerPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        ignorePath = filterConfig.getInitParameter("ignore-path");
        registerPath = filterConfig.getInitParameter("register-path");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        User user = (User) request.getSession().getAttribute("user");
        String path = request.getServletPath();

        if(request.getSession().getAttribute("lang") == null){
            request.getSession().setAttribute("lang","mk");
        }

        if ((path.startsWith(ignorePath) || path.startsWith(registerPath)) && user != null){
            response.sendRedirect("/home");
        }
        if (path.equals("/wish-list") && user == null){
            response.sendRedirect("/home");
        }
        if(path.equals("/favicon.ico")) {
            response.sendRedirect("/images/favicon.ico");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

