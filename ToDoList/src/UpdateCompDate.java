

import java.io.IOException;
import java.util.Date;

import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customTools.DBUtil;

/**
 * Servlet implementation class UpdateDueDate
 */
@WebServlet("/UpdateCompDate")
public class UpdateCompDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCompDate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int listId = Integer.parseInt(request.getParameter("done"));
		updateCompDate(listId);
		getServletContext().getRequestDispatcher("/displayList").forward(request, response);;	
			}
	
	protected void updateCompDate(int id){
		System.out.println("Date Done " + id);
		TypedQuery<model.ToDoList> query = DBUtil.createQuery("update ToDoList t set t.dateCompleted =:date where t.listId =:id",model.ToDoList.class)
				.setParameter("id", id).setParameter("date", new Date());
		DBUtil.updateDB(query);
	
	}

}
