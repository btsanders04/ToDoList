import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Userprofile;
import customTools.DBUtil;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String header = "Create Profile";
		request.setAttribute("headName", header);
		request.setAttribute("action", "SignUp");
		request.setAttribute("name", "Enter name");
		request.setAttribute("email", "Enter email");
		request.setAttribute("password", "Enter password");
		request.setAttribute("username", "Enter User Name");
		getServletContext().getRequestDispatcher("/SignUp.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String username = request.getParameter("username");
		HttpSession session = request.getSession();
		String alert = "";
		if (foundUser(email)) {
			model.Userprofile user = new model.Userprofile();
			user.setUserEmail(email);
			user.setUserName(name);
			user.setUserPass(pwd);
			DBUtil.addToDB(user);
			session.setAttribute("User", user);
			session.setAttribute("loggedIn", true);
			alert = "<div class=\"container\"><div class=\"alert alert-success\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>"
					+ "<strong>Success!</strong> You have Signed Up.</div></div>";
		} else {
			alert = "<div class=\"container\"><div class=\"alert alert-danger\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>"
					+ "<strong>Error!</strong> You are already a user.</div></div>";
			session.setAttribute("loggedIn", false);
		}

		request.setAttribute("alertMessage", alert);
		getServletContext().getRequestDispatcher("/index.jsp").forward(request,
				response);
	}

	public boolean foundUser(String email) {
		String qString = "SELECT u FROM Userprofile u WHERE u.userEmail = :userEmail";
		TypedQuery<model.Userprofile> q = DBUtil.createQuery(qString,
				model.Userprofile.class);
		q.setParameter("userEmail", email);
		List<model.Userprofile> user = q.getResultList();
		System.out.println(user.size());
		if(user.size()>0){
			return false;
		}
		else return true;
	}
}
