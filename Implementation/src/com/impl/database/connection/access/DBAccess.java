package com.impl.database.connection.access;

import java.util.List;

import com.impl.database.elements.Absent;
import com.impl.database.elements.Comment;
import com.impl.database.elements.Detension;
import com.impl.database.elements.Parent;
import com.impl.database.elements.Student;
import com.impl.database.elements.UserAccount;
import com.impl.database.elements.Warning;

public interface DBAccess {
	
	public List<Student> getAllStudentsInClass(String className);
	
	public Student getStudentByName(String className, String studentName);
	
	public UserAccount getUser(String username, String password);
	
	public List<Absent> getStudentAbsents(String studentId);
	
	// Detension should be Detention
	public List<Detension> getStudentDetensions(String studentId);
	
	public List<Comment> getStudentComments(String studentId);
	
	public List<Parent> getStudentParents(String studentId);
	
	public List<Warning> getStudentWarnings(String studentId);
	
	public void setUser(String firstName, String lastName, String username, String password);
}
