package Action;

import Dao.UserDao;
import Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginAction")
public class LoginAction extends HttpServlet {
    UserDao udao = new UserDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equals("login"))
            this.login(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = null;
        String password = null;
        if(request.getParameter("username")!= null && request.getParameter("password") != null){
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            if(udao.login(user)){
                HttpSession session = request.getSession();
                session.setAttribute("name", user.getName());
                request.getRequestDispatcher("main.jsp").forward(request, response);
            }
        }
    }
}

