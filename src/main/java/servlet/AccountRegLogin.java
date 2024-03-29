package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.Login;


@WebServlet("/AccountRegLogin")
public class AccountRegLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Account account = (Account) session.getAttribute("account");
		Login   login = new Login(account.getUserId(),account.getPass()); 
		
		System.out.println(login.getUserId() +"/"+login.getPass() );
		session.setAttribute("Login", login);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/LoginOK.jsp");
		dispatcher.forward(request, response);
		
	
	
	}


}
