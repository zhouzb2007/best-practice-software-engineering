package at.ac.tuwien.ifs.bpse.scratchpad.model;

import java.util.Date;

public class Exam {

	private Date exdate;
	private String location;
	private Course course;

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Date getExdate() {
		return exdate;
	}

	public void setExdate(Date exDate) {
		this.exdate = exDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
}
