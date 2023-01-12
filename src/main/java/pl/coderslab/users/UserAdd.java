package pl.coderslab.users;

import pl.coderslab.entity.User;
import pl.coderslab.entity.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/add")
public class UserAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/users/add.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strUserName = request.getParameter("userName");
        String strUserEmail = request.getParameter("userEmail");
        String strUserPass = request.getParameter("userPass");
        UserDao userDao = new UserDao();
        User user = new User();
        user.setUserName(strUserName);
        user.setEmail(strUserEmail);
        user.setPassword(strUserPass);
        userDao.create(user);
        request.setAttribute("users", userDao.findAll());
        getServletContext().getRequestDispatcher("/users/list.jsp")
                .forward(request, response);
    }
}
