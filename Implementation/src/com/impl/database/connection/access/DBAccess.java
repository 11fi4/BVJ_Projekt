package com.impl.database.connection.access;

import java.util.List;
import com.impl.database.elements.Student;

public interface DBAccess {
	
	public List<Student> getAllStudents(String className);
	
	public Student getStudentByName(String className, String studentName);
}
