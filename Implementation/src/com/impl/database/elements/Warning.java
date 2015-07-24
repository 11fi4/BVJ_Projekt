/**
 * 
 */
package com.impl.database.elements;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author kuslu
 *
 */
@Entity
@Table(name = "warning")
public class Warning implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	// (generator = "increment")
	@Column(name = "warning_id", unique = true, nullable = false)
	protected int warning_id;
	@Column(name = "date", nullable = false)
	protected Date date;
	@Column(name = "comment")
	protected String comment;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", nullable = false)
	protected Student student;

	public Warning() {
	}

	public int getWarning_id() {
		return warning_id;
	}

	public void setWarning_id(int warning_id) {
		this.warning_id = warning_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
