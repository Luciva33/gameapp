package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Login;
import model.LoginLogic;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/login.jsp");
		dispatcher.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		
		Login login = new Login(name,pass);
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(login);
		
		if(result) {
			HttpSession session = request.getSession();
			session.setAttribute("Login", login);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/LoginOK.jsp");
			dispatcher.forward(request, response);
			
		}else {
			//エラーメッセージ
			request.setAttribute("errorMsg", "入力ミス！" );
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/login.jsp");
			dispatcher.forward(request, response);
			//response.sendRedirect("LoginServlet");
			
		}
		

	}

}