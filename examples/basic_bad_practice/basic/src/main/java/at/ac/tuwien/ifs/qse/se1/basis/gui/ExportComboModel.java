package at.ac.tuwien.ifs.qse.se1.basis.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import at.ac.tuwien.ifs.qse.se1.basis.export_import.Export;

/**
 * This Class contains the Export-Classes, used by the "Export"-Button. New
 * Exporters can be added by adding them to the Bean-Config file ({@value at.ac.tuwien.ifs.qse.se1.basis.helper.Constants#SPRINGBEANS})
 * 
 * @author The SE-Team
 * @version 1.0
 * @see Export
 */
public class ExportComboModel implements ComboBoxModel {
	/**
	 * Retrieves the logger for this class.
	 */
	static Log log = LogFactory.getLog(ExportComboModel.class);

	/**
	 * List of available exportfilters.
	 */
	List<Export> exportFilter;

	/**
	 * Initialized by the Springframework after setter injection
	 */
	Export selectedItem;

	/**
	 * Create an new ExportComboModel and initialise the List of Exporters.
	 * 
	 * @see Export
	 */
	public ExportComboModel() {
		super();
		exportFilter = new ArrayList<Export>();
	}

	/**
	 * Initialise Method called by Springframework after setter injection
	 */
	public void init() {
		selectedItem = exportFilter.get(0);
	}

	/**
	 * Set the available Export-Filters.
	 * 
	 * @param exportFilter
	 *            List of exportFilters
	 */
	public void setExportFilter(List<Export> exportFilter) {
		StringBuffer regfilters = new StringBuffer("" + exportFilter.size()
				+ " Export Filters are registered (");
		for (Export ex : exportFilter) {
			regfilters.append(ex.toString() + ", ");
		}
		regfilters.append(")");
		log.info(regfilters.toString());
		this.exportFilter = exportFilter;
	}

	/* ***************************************************************************** */
	/* ***************************************************************************** */
	/* These methods are implementations from the ComboBoxModel Interface */
	/* ***************************************************************************** */
	/* ***************************************************************************** */

	public void setSelectedItem(Object o) {
		selectedItem = exportFilter.get(exportFilter.indexOf(o));
	}

	public Object getSelectedItem() {
		return selectedItem;
	}

	public int getSize() {
		return exportFilter.size();
	}

	public Object getElementAt(int pos) {
		return exportFilter.get(pos);
	}

	public void addListDataListener(ListDataListener arg0) {
	}

	public void removeListDataListener(ListDataListener arg0) {
	}

}
