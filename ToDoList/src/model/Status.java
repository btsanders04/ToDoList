package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the STATUS database table.
 * 
 */
@Entity
@Table(name="STATUS", schema="TESTDB")
@NamedQuery(name="Status.findAll", query="SELECT s FROM Status s")
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="STATUS_ID")
	private int statusId;

	private String status;

	//bi-directional many-to-one association to ToDoList
	@OneToMany(mappedBy="status")
	private List<ToDoList> toDoLists;

	public Status() {
	}

	public int getStatusId() {
		return this.statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ToDoList> getToDoLists() {
		return this.toDoLists;
	}

	public void setToDoLists(List<ToDoList> toDoLists) {
		this.toDoLists = toDoLists;
	}

	public ToDoList addToDoList(ToDoList toDoList) {
		getToDoLists().add(toDoList);
		toDoList.setStatus(this);

		return toDoList;
	}

	public ToDoList removeToDoList(ToDoList toDoList) {
		getToDoLists().remove(toDoList);
		toDoList.setStatus(null);

		return toDoList;
	}

}