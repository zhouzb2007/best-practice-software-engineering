/**
 * 
 */
package at.ac.tuwien.ifs.bpse.web.controller.search;

import java.util.List;

/**
 * Abstract search controller.
 * 
 * @author SE Team
 * 
 */
public abstract class AbstractSearchController {
    private String searchString;
    private List result = null;

    public abstract void search();

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public List getResult() {
        return result;
    }

    public void setResult(List result) {
        this.result = result;
    }

    /**
     * Returns <code>true</code> iff there was a search request submitted.
     * 
     * @return <code>true</code> iff there was a search request submitted.
     */
    public boolean isSearchResult() {
        if (result != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns <code>true</code> iff the search result contains no matches.
     * 
     * @return <code>true</code> iff the search result contains no matches.
     */
    public boolean isNoMatches() {
        if (result != null && !result.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}
