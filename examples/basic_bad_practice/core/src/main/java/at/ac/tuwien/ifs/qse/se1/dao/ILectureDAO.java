package at.ac.tuwien.ifs.qse.se1.dao;

import java.util.List;

import at.ac.tuwien.ifs.qse.se1.bo.Lecture;

/**
 * Lecture DAO.
 * @author Philip Langer <langer[at]big[dot]tuwien[dot]ac[dot]at>
 */

public interface ILectureDAO {
    
        /**
     * Searches all lectures using set DAO that are matching the specified <code>String</code>.
     * @param str search string.
     * @return <code>List</code> of <code>Lecture</code>s.
     */
    public List<Lecture> searchLectures(String str);


}
