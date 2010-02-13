package at.ac.tuwien.ifs.bpse.designpatterns.immutable;


public class NotificationIDs {

	private String[] ids;
	
	public NotificationIDs(String [] ids) {
		this.ids = ids;
	}
	
	/**
	 * 
	 * @return number of IDs
	 */
	public int size () { return ids.length; }
	
	
	/**
	 * Get specific ID
	 * @param index starting with 0
	 * @return ID (Notification type)
	 */
	public String getId (int index) throws IndexOutOfBoundsException {
		if ((index >= ids.length) || (index < 0))
			throw new IndexOutOfBoundsException();
		return ids[index];
	}
	
}
