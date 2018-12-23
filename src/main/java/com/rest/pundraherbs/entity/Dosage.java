package com.rest.pundraherbs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Dosage {
	@Id
	@GeneratedValue
	private Long dosageId;
	private String person;
	private String dosageAdvice;

	public Long getDosageId() {
		return dosageId;
	}

	public void setDosageId(Long dosageId) {
		this.dosageId = dosageId;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getDosageAdvice() {
		return dosageAdvice;
	}

	public void setDosageAdvice(String dosageAdvice) {
		this.dosageAdvice = dosageAdvice;
	}

	@Override
	public String toString() {
		return "Dosage [dosageId=" + dosageId + ", person=" + person + ", dosageAdvice=" + dosageAdvice + "]";
	}

}
