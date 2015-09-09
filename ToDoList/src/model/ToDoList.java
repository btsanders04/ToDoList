package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TO_DO_LIST database table.
 * 
 */
@Entity
@Table(name="TO_DO_LIST", schema="TESTDB")
@NamedQuery(name="ToDoList.findAll", query="SELECT t FROM ToDoList t")
public class ToDoList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TO_DO_LIST_LISTID_GENERATOR", sequenceName="SEQ_TODOLIST", allocationSize = 1, initialValue = 1, schema="TESTDB")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TO_DO_LIST_LISTID_GENERATOR")
	@Column(name="LIST_ID")
	private int listId;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_COMPLETED")
	private Date dateCompleted;

	private String description;

	@Temporal(TemporalType.DATE)
	@Column(name="DUE_DATE")
	private Date dueDate;

	@Column(name="LIST_PRIORITY")
	private int listPriority;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="STATUS_ID")
	private Status status;

	//bi-directional many-to-one association to Userprofile
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private Userprofile userprofile;

	public ToDoList() {
	}

	public int getListId() {
		return this.listId;
	}

	public void setListId(int listId) {
		this.listId = listId;
	}

	public Date getDateCompleted() {
		return this.dateCompleted;
	}

	public void setDateCompleted(Date dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public int getListPriority() {
		return this.listPriority;
	}

	public void setListPriority(int listPriority) {
		this.listPriority = listPriority;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Userprofile getUserprofile() {
		return this.userprofile;
	}

	public void setUserprofile(Userprofile userprofile) {
		this.userprofile = userprofile;
	}

}