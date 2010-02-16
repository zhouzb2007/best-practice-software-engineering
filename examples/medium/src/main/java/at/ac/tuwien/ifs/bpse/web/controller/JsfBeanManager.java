/**
 * 
 */
package at.ac.tuwien.ifs.bpse.web.controller;

import javax.faces.context.FacesContext;

/**
 * todo
 * @author SE Team
 */
public class JsfBeanManager {
    public JsfBeanManager() {
    }

    /**
     * Update a bean in the context. The scope of the bean is already defined in the
     * faces-config.xml
     * @param beanname String the managed bean name.
     * @param bean Object the object to insert
     */
    public static void setBean(String beanname, Object bean) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().createValueBinding("#{" + beanname + "}").setValue(context, bean);
    }

    /**
     * Retrieves a bean from the faces context
     * @param beanname String
     * @return Object
     */
    public static Object getBean(String beanname) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getVariableResolver().resolveVariable(context, beanname);
    }
}
