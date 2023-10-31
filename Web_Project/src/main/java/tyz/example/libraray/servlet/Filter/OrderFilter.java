package tyz.example.libraray.servlet.Filter;

import tyz.example.libraray.servlet.base.BaseServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(value="/Order")
public class OrderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       HttpServletRequest Request=(HttpServletRequest)servletRequest;
       HttpServletResponse Response=(HttpServletResponse)servletResponse;
       HttpSession session=Request.getSession();
        Object user = session.getAttribute("user");
        if(user==null){
                Response.sendRedirect(Request.getContextPath()+"/index.html");
        }
        else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
