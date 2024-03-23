package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountsReg;
import model.Account;

@WebServlet("/AccountRegServlet")
public class AccountRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher
				("WEB-INF/view/AccountReg.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		
		System.out.println(name+ "/"+pass );
		if(name != null && name.length() != 0 &&
				pass != null && pass.length() != 0) {
			
			Account account  = new Account(pass,name);
			
			
					AccountsReg reg = new AccountsReg();
					List<Account>list = reg.findAll();
					reg.InsertOne(account);
			
					RequestDispatcher dispatcher = request.getRequestDispatcher
							("WEB-INF/view/AccountReg.jsp");
					dispatcher.forward(request, response);
			
			
			
			
		}else {
			//エラーメッセージ
			request.setAttribute("errorMsg", "入力ミス！" );
			RequestDispatcher dispatcher = request.getRequestDispatcher
					("WEB-INF/view/AccountReg.jsp");
			dispatcher.forward(request, response);
			
		}
	
				
				
				
			
	
	}

}
