/**
 * 
 */
package com.impl.database.elements;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author kuslu
 *
 */
@Entity
@Table(name = "STUDENT_PARENT", catalog = "backenddb")
public class StudentParent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(generator = "increment")
	@Column(name = "id")
	protected int id;
	@Column(name = "student_id")
	protected String studentId;
	@Column(name = "parent_id")
	protected String parentId;

	public StudentParent() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
