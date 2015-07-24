/**
 * 
 */
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
 *
 */
@Entity
@Table(name = "absent")
public class Absent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	// (generator = "increment")
	@Column(name = "absent_id")
	protected int absent_id;
	@Column(name = "absent_from")
	protected String absent_from;
	@Column(name = "absent_to")
	protected int absent_to;
	@Column(name = "medicalCertificate")
	protected String medicalCertificate;
	@Column(name = "excused")
	protected boolean excused;
	@Column(name = "parentsContacted")
	protected boolean parentsContacted;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id", nullable = false)
	protected Student student;

	public Absent() {
	}

	public int getAbsent_id() {
		return absent_id;
	}

	public void setAbsent_id(int absent_id) {
		this.absent_id = absent_id;
	}

	public String getAbsent_from() {
		return absent_from;
	}

	public void setAbsent_from(String absent_from) {
		this.absent_from = absent_from;
	}

	public int getAbsent_to() {
		return absent_to;
	}

	public void setAbsent_to(int absent_to) {
		this.absent_to = absent_to;
	}

	public String getMedicalCertificate() {
		return medicalCertificate;
	}

	public void setMedicalCertificate(String medicalCertificate) {
		this.medicalCertificate = medicalCertificate;
	}

	public boolean isExcused() {
		return excused;
	}

	public void setExcused(boolean excused) {
		this.excused = excused;
	}

	public boolean isParentsContacted() {
		return parentsContacted;
	}

	public void setParentsContacted(boolean parentsContacted) {
		this.parentsContacted = parentsContacted;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}