package at.ac.tuwien.ifs.qse.se1.basis.helper;

import at.ac.tuwien.ifs.qse.se1.basis.gui.MainFrame;


/**
 * Class containing just the main method, nothing more.
 * 
 * @author The SE-Team
 * @version 1.0
 */
public class Basis {

	/**
	 * Entry point for the program. 
	 * Starts the MainFrame and then terminates.
	 * 
	 * @param args
	 *            the command-line arguments
	 * @see MainFrame
	 */
	public static void main(String[] args) {
		System.out.println("Starting Application");
       
		MainFrame MF = new MainFrame();
		MF.setLocation(50, 50);
		MF.pack();
		MF.setVisible(true);
		System.out.println("Terminating Start Class");
	}

}
