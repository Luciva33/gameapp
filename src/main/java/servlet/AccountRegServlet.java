package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountsReg;
import model.Account;

@WebServlet("/AccountRegServlet")
public class AccountRegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/AccountReg.jsp");
		dispatcher.forward(request, response);
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");

		System.out.println(userId + "/" + pass);
		if (userId != null && userId.length() != 0 &&
				pass != null && pass.length() != 0) {

			Account account = new Account(userId,pass);
			AccountsReg reg = new AccountsReg();	
			
			Account result = reg.findByReg(account);
			
			if(result != null) {
				
				request.setAttribute("errorMsg", "ユーザーIDが重複してます！");
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/AccountReg.jsp");
				dispatcher.forward(request, response);

			}else {
				

			reg.InsertOne(account);
			request.setAttribute("Msg", userId +"を登録しました!");
			HttpSession session = request.getSession();
			session.setAttribute("account", account);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/AccountReg.jsp");
			dispatcher.forward(request, response);
		
			}
		} else {
			//エラーメッセージ
			request.setAttribute("errorMsg", "登録できませんでした！");
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/AccountReg.jsp");
			dispatcher.forward(request, response);

		}

	}

}
