import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import customTools.DBUtil;
import model.Userprofile;

/**
 * Servlet implementation class displayList
 */
@WebServlet("/displayList")
public class displayList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	model.Userprofile user;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public displayList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		user = (Userprofile) session.getAttribute("User");

		String display = listAllToDo(user);

		request.setAttribute("display", display);
		getServletContext().getRequestDispatcher("/displayList.jsp").forward(
				request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("desc")!=null){
			model.ToDoList todo = new model.ToDoList();
		todo.setDescription(request.getParameter("desc"));

		try {
			todo.setDueDate(new SimpleDateFormat("yyyy-MM-dd").parse(request
					.getParameter("dueDate")));
		} catch (ParseException e) {
			System.out.println("PARSE DATE ERROR");
			e.printStackTrace();
		}
		todo.setListPriority(Integer.parseInt(request.getParameter("priority")));
		int status = Integer.parseInt(request.getParameter("status"));
		todo.setStatus(getStatus(status));
		
		todo.setUserprofile(user);
		DBUtil.addToDB(todo);
		}
		 doGet(request, response);
	}

	protected String listAllToDo(model.Userprofile user) {
		StringBuilder display = new StringBuilder();
		TypedQuery<model.ToDoList> qString = DBUtil.createQuery(
				"select t from ToDoList t where t.userprofile =:user",
				model.ToDoList.class).setParameter("user", user);
		List<model.ToDoList> toDoList = qString.getResultList();
		for (model.ToDoList t : toDoList) {
			display.append("<div class = \"container\"> "
					+ "<div style = \"border: 2px solid black\">"
					+ "<div class = \"row\"><div class = \"col-sm-2\">" + "<p>"
					+ t.getDescription()
					+ "</p></div><div class =\"col-sm-2\"><p>"
					+ new SimpleDateFormat("MM/dd/yyy").format(t.getDueDate())
					+ "</p></div>" + "<div class=\"col-sm-2\">" + "<p>"
					+ getPriority(t.getListPriority()) + "</p></div>"
					+ "<div class=\"col-sm-2\">" + "<p>"
					+ t.getStatus().getStatus() + "</p></div>");
			if (t.getDateCompleted() == null) {
				display.append("<form role=\"form\" method = \"Post\" action =\"UpdateCompDate\" <div class=\"col-sm-2\"><button name =\"done\" value =\""
						+ t.getListId()
						+ "\" type=\"submit\" class=\"btn btn-info\">Done</button></div></form>"
						+ "</div></div></div>");
			} else {
				display.append("<div class=\"col-sm-2\">" + "<p>"
						+ t.getDateCompleted() + "</p></div>");
			}
		}
		return display.toString();
	}

	protected model.Status getStatus(int id) {
		String q = "select s from Status s where s.statusId = :id";
		TypedQuery<model.Status> sQuery = DBUtil.createQuery(q,
				model.Status.class).setParameter("id", id);
		return sQuery.getSingleResult();
	}

	protected String getPriority(int priority) {
		switch (priority) {
		case (1):
			return "low";
		case (2):
			return "medium";
		case (3):
			return "high";
		}
		return "";

	}

}
