package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "job")
public class Job {
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Id
	@GeneratedValue
	private int id;
	private String jobtitle;
	private String jobdescription;
	private String yearsofexperince;
	private String skillsrequired;
	private String salary;
	private String postedon;
	private String location;
	private String companyname;

	public String getJobtitle() {
		return jobtitle;
	}

	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}

	public String getJobdescription() {
		return jobdescription;
	}

	public void setJobdescription(String jobdescription) {
		this.jobdescription = jobdescription;
	}

	public String getYearsofexperince() {
		return yearsofexperince;
	}

	public void setYearsofexperince(String yearsofexperince) {
		this.yearsofexperince = yearsofexperince;
	}

	public String getSkillsrequired() {
		return skillsrequired;
	}

	public void setSkillsrequired(String skillsrequired) {
		this.skillsrequired = skillsrequired;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getPostedon() {
		return postedon;
	}

	public void setPostedon(String postedon) {
		this.postedon = postedon;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

}
