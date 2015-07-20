package com.impl.database.elements;

import java.io.Serializable;

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
 */
@Entity
@Table(name = "DETENSION")
public class Detension implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue  //(generator = "increment")
	@Column(name = "detention_id", unique = true, nullable = false)
	protected int detention_id;

	@Column(name = "detention_from", nullable = false)
	protected String detention_from;
	@Column(name = "detention_to")
	protected String detention_to;
	@Column(name = "supervisor")
	protected String supervisor;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", nullable = false)
	protected Student student;
	
	public Detension(){}

	public int getDetention_id() {
		return detention_id;
	}

	public void setDetention_id(int detention_id) {
		this.detention_id = detention_id;
	}

	public String getDetention_from() {
		return detention_from;
	}

	public void setDetention_from(String detention_from) {
		this.detention_from = detention_from;
	}

	public String getDetention_to() {
		return detention_to;
	}

	public void setDetention_to(String detention_to) {
		this.detention_to = detention_to;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
}
