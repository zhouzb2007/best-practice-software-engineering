package info.schatten.infoweek.htmlunit;

import org.apache.log4j.Logger;

import net.sourceforge.jwebunit.junit.WebTestCase;
import net.sourceforge.jwebunit.util.TestingEngineRegistry;


/**
 * Unit test for simple App.
 */
public class NumberGuessTest extends WebTestCase {

	private static Logger log = Logger.getLogger(WebTestCase.class);

	public NumberGuessTest() {
	}
	
	
	public void setUp() {
		setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);
		getTestContext().setBaseUrl("http://localhost:8080/examples/jsp/num");
     }


	public void testMainPage() {
		// check if initial page is ok
		beginAt("numguess.jsp");
		assertTitleEquals("Number Guess");
		assertTextPresent("Welcome to the Number Guess game");
		assertFormElementPresent("guess");		
	}
	
	public void testZahl() {
		beginAt("numguess.jsp");
		setTextField("guess", "text");
		submit();
		String text = getElementTextByXPath("//b").trim();
		if (!text.equals("a number next time"))
			fail ("A text was submitted; error message expected");

	}
	
	public void testUp() {
		beginAt("numguess.jsp");
		int zahl = 1;
		String text="";
		boolean found = false;
		while (!found) {
			setTextField("guess", "" + zahl);
			submit();
			text = getElementTextByXPath("//body").trim();
			if (text.contains("Congratulations")) {
				found = true;
			} else {
				assertTextPresent("higher");
				log.info("" + zahl);
				zahl++;
			}
		};
		if (zahl > 100)
			fail ("Number too high; should be between 1-100");
		assertTextPresent("Congratulations");
		log.info("Found \n");
	}
	
	public void testDown() {
		beginAt("numguess.jsp");
		int zahl = 100;
		String text="";
		boolean found = false;
		while (!found) {
			setTextField("guess", "" + zahl);
			submit();
			text = getElementTextByXPath("//body").trim();
			if (text.contains("Congratulations")) {
				found = true;
			} else {
				assertTextPresent("lower");
				log.info("" + zahl);
				zahl--;
			}
		};
		if (zahl < 1)
			fail ("Number too high; should be between 1-100");
		assertTextPresent("Congratulations");
		log.info("Found \n");
	}
}
