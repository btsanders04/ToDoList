package tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Before;
import org.junit.Test;

import customTools.DBUtil;

public class displayList {
	model.Userprofile user = new model.Userprofile();
	@Before
	public void setUp() throws Exception {
		user = DBUtil.createQuery("select u from Userprofile u where u.userId = :userId", model.Userprofile.class)
				.setParameter("userId", 3).getSingleResult();
	}

	@Test
	public void testListAllToDo() {
		TypedQuery<model.ToDoList> qString = DBUtil.createQuery(
				"select t from ToDoList t where t.userprofile =:user",
				model.ToDoList.class).setParameter("user", user);
		List<model.ToDoList> toDoList = qString.getResultList();
		assertTrue(toDoList.size() == 3);
	}

	@Test
	public void testGetStatus() {
		String q = "select s from Status s where s.statusId = :id";
		TypedQuery<model.Status> sQuery = DBUtil.createQuery(q,
				model.Status.class).setParameter("id", 1);
		assertTrue(sQuery.getSingleResult().getStatus().equals("Incomplete"));
	}

	@Test
	public void testGetPriority() {
		assertTrue(getPriority(1).equals("low"));
		assertTrue(getPriority(2).equals("medium"));
		assertTrue(getPriority(3).equals("high"));
	}
	
	public String getPriority(int priority) {
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
