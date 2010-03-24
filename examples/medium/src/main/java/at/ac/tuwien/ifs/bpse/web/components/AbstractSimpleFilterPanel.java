package at.ac.tuwien.ifs.bpse.web.components;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Simple Filter Panel
 * @author mde
 *
 */
public abstract class AbstractSimpleFilterPanel extends Panel {
	/* Filter object */
	private Filter filter = new Filter();
	
	public AbstractSimpleFilterPanel(String id) {
		super(id);
		add(new FilterForm("filterForm", new CompoundPropertyModel<Filter>(filter)));
	}
	
	public String getFilter(){
		return filter.getFilter();
	}
	
	/**
	 * Abstract class that must implement by the simple filter panel. This callback
	 * usually updates a list
	 */
	public abstract void doRefresh(String filter);
	
	/**
	 * Filter Form
	 * @author mde
	 *
	 */
	private class FilterForm extends Form{

		public FilterForm(String id, IModel<Filter> model) {
			super(id, model);
			add(new TextField<Filter>("filter"));
		}

		@Override
		protected void onSubmit() {
			super.onSubmit();
			doRefresh(((Filter)getModelObject()).getFilter());
		}
		
		
	}
	
	/**
	 * Filter class
	 * @author mde
	 *
	 */
	private class Filter implements java.io.Serializable{
		private String filter;
		
		private void setFilter(String filter){
			this.filter = filter;
		}
		private String getFilter(){
			return filter;
		}
	}

}
