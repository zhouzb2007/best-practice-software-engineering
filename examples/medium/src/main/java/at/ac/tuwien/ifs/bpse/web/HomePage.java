package at.ac.tuwien.ifs.bpse.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.spring.injection.annot.SpringBean;

import at.ac.tuwien.ifs.bpse.domain.Student;
import at.ac.tuwien.ifs.bpse.medium.persistence.SqlSessionFactoryBean;
import at.ac.tuwien.ifs.bpse.medium.persistence.interfaces.StudentMapper;

/**
 * Homepage
 */
public class HomePage extends WebPage {

	private static final long serialVersionUID = 1L;

	@SpringBean(name="sqlSessionFactory")
	transient private SqlSessionFactoryBean sf;

    public SqlSessionFactoryBean getSf() {
		return sf;
	}

	public void setSf(SqlSessionFactoryBean sf) {
		this.sf = sf;
	}

	/**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public HomePage(final PageParameters parameters) {
    	
    	SqlSession session = sf.openSession();
    	
    	try {
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:mem:bpse-sample-medium", "sa", "");
			ScriptRunner runner = new ScriptRunner(conn);
			runner.runScript(Resources.getResourceAsReader("bpse-sample-medium-schema.sql"));
			
			// StudentMapper has Method Scope, just like Session
			StudentMapper mapper = session.getMapper(StudentMapper.class);
			Student s = new Student("0027226","Erik", "G");
			//s.setId(null);
			s.setEmail("foo");
			mapper.insertStudent(s);
			Student stud = mapper.selectStudentByMatrNr("0027226");
			

	        // Add the simplest type of label
	        add(new Label("message", stud.getFullname()));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.close();
		}
    	
    }
}
