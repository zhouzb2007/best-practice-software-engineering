package at.ac.tuwien.ifs.bpse.basic.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import at.ac.tuwien.ifs.bpse.basic.domain.Student;

public class RowMappers {

	public static final class StudentMapper implements RowMapper<Student> {
	      public Student mapRow(ResultSet rs, int rowNumber)
			throws SQLException {
	    	  at.ac.tuwien.ifs.bpse.basic.domain.Student student = new at.ac.tuwien.ifs.bpse.basic.domain.Student();
				student.setId(rs.getInt("id"));
				student.setMatnr(rs.getString("matnr"));
				student.setFirstname(rs.getString("vorname"));
				student.setLastname(rs.getString("nachname"));
				student.setEmail(rs.getString("email"));
				return student;
			}
	  } 
	
}
