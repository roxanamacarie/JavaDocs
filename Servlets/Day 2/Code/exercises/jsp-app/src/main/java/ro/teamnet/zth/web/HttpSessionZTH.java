package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by user on 7/13/2016.
 */
public class HttpSessionZTH extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("username");
        String password = req.getParameter("password");
        Cookie[] cookies = req.getCookies();

        if(user.equals("admin")&& password.equals("admin")){
            resp.getWriter().write("Welcome back! <br> Username:"+user);

           /* for(Cookie cookie: cookies){
                resp.getWriter().write("<br"+cookie.getName() + " " +cookie.getValue() + "</br>");
            }*/
        }
        else{
            HttpSession session= req.getSession();
            session.setAttribute("user",user);
            session.setAttribute("session",session);

            resp.sendRedirect("views/loginFail.jsp");
        }
    }
}
