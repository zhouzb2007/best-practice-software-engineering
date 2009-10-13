package at.ac.tuwien.ifs.bpse.designpatterns.adapter;


/**
 * The assumption is, that this class is a part of a complex
 * system and is already available.
 * 
 * It could be e.g. a library from a vendor.
 * 
 * The assumption further is, that it is not (easily) possible
 * or desirable to change the sourcecode of this module.
 * 
 * Hence we use the Adapter pattern to still integrate this class
 * into our notification system
 *
 */
public class VoiceCall {

	
	public String callNr (String phoneNr, String text) {
		/*
		 * This method would now connect to the phone, 
		 * fire on the speech synthesizer and speak
		 * the notification to the receiver
		 */
		System.out.println("Calling " + phoneNr);
		System.out.println("Speaking: '" + text + "'");
		return "Phonecall done";
	}
	
}
