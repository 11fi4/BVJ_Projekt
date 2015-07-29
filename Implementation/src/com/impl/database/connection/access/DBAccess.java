package com.impl.database.connection.access;

import java.util.Date;
import java.util.List;

import com.impl.database.elements.Absent;
import com.impl.database.elements.Comment;
import com.impl.database.elements.Detension;
import com.impl.database.elements.Parent;
import com.impl.database.elements.Student;
import com.impl.database.elements.UserAccount;
import com.impl.database.elements.Warning;

public interface DBAccess {
	
	public List<Student> requestAllStudentsInClass(String className);
	
	public List<Student> requestStudentByName(String firstName, String lastName);

	public List<Student> requestStudentByName(String firstName, String lastName, String classId);
	
	public UserAccount requestUser(String username, String password);
	
	public List<Absent> requestStudentAbsents(String studentId);
	
	// Detension should be Detention
	public List<Detension> requestStudentDetensions(String studentId);
	
	public List<Comment> requestStudentComments(String studentId);
	
	public List<Parent> requestStudentParents(String studentId);
	
	public List<Warning> requestStudentWarnings(String studentId);
	
	public void insertUser(String firstName, String lastName, String username, String password);
	
	public void addUserRole(String userId, String roleId);
	
	public void insertStudent(String firstName, String lastName, Date birthdate, String phone, String eMail,
			String address, String gender);
	
	public void deleteUser(String userId);
}
