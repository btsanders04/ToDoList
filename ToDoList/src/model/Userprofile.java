package model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the USERPROFILE database table.
 * 
 */
@Entity
@Table(name="USERPROFILE", schema="TESTDB")
@NamedQuery(name="Userprofile.findAll", query="SELECT u FROM Userprofile u")
public class Userprofile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USERPROFILE_USERID_GENERATOR", sequenceName="SEQ_USERPROF", allocationSize = 1, initialValue = 1, schema="TESTDB")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USERPROFILE_USERID_GENERATOR")
	@Column(name="USER_ID")
	private int userId;

	@Column(name="IMAGE_LINK")
	private String imageLink;

	@Temporal(TemporalType.DATE)
	@Column(name="JOIN_DATE")
	private Date joinDate;

	private String motto;

	@Column(name="USER_EMAIL")
	private String userEmail;

	@Column(name="USER_NAME")
	private String userName;

	@Column(name="USER_PASS")
	private String userPass;

	@Column(name="USER_ZIPCODE")
	private String userZipcode;

	//bi-directional many-to-one association to ToDoList
	@OneToMany(mappedBy="userprofile")
	private List<ToDoList> toDoLists;

	public Userprofile() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getImageLink() {
		return this.imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public Date getJoinDate() {
		return this.joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getMotto() {
		return this.motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return this.userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserZipcode() {
		return this.userZipcode;
	}

	public void setUserZipcode(String userZipcode) {
		this.userZipcode = userZipcode;
	}

	public List<ToDoList> getToDoLists() {
		return this.toDoLists;
	}

	public void setToDoLists(List<ToDoList> toDoLists) {
		this.toDoLists = toDoLists;
	}

	public ToDoList addToDoList(ToDoList toDoList) {
		getToDoLists().add(toDoList);
		toDoList.setUserprofile(this);

		return toDoList;
	}

	public ToDoList removeToDoList(ToDoList toDoList) {
		getToDoLists().remove(toDoList);
		toDoList.setUserprofile(null);

		return toDoList;
	}

}