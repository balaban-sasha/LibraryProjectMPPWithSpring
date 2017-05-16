package main.com.bsuir.library.dao.implementation.section.implementation;

import main.com.bsuir.library.bean.Section;
import main.com.bsuir.library.dao.AbstractDaoController;
import main.com.bsuir.library.dao.exception.DaoException;
import main.com.bsuir.library.dao.implementation.section.ISectionDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Саша on 28.03.2017.
 */
public class SectionDao extends AbstractDaoController<Section,Integer> implements ISectionDao {
    @Override
    public Section getByPrimaryKey(Integer integer) throws DaoException {
        return null;
    }

    @Override
    public Section update(Section section) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer integer) throws DaoException {
        return false;
    }

    @Override
    protected List<Section> parseResultSet(ResultSet rs, String dbDataLanguage) throws DaoException {
        LinkedList<Section> result = new LinkedList<Section>();
        try {
            while (rs.next()) {
                Section section = new Section();
                section.setDescription(rs.getString("lib_section_description"+dbDataLanguage));
                section.setHeader(rs.getString("lib_section_header"+dbDataLanguage));
                section.setId(rs.getInt("lib_section_id"));
                section.setName(rs.getString("lib_section_name"+dbDataLanguage));
                section.setNumber(rs.getInt("lib_section_number"));
                result.add(section);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    @Override
    protected void insertToTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] sectionName=parameterMap.get("sectionName");
        String[] sectionHeader = parameterMap.get("sectionHeader");
        String[] sectionDescription = parameterMap.get("sectionDescription");
        String[] sectionNameEn = parameterMap.get("sectionNameEn");
        String[] sectionHeaderEn = parameterMap.get("sectionHeaderEn");
        String[] sectionDescriptionEn = parameterMap.get("sectionDescriptionEn");
        String[] sectionNumber = parameterMap.get("sectionNumber");
        String sql = "INSERT INTO lib_section(lib_section_name,lib_section_header,lib_section_description,lib_section_name_en," +
                "lib_section_header_en,lib_section_description_en,lib_section_number)VALUES (?,?,?,?,?,?,?)";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setString(1,sectionName[0]);
        stmt.setString(2,sectionHeader[0]);
        stmt.setString(3,sectionDescription[0]);
        stmt.setString(4,sectionNameEn[0]);
        stmt.setString(5,sectionHeaderEn[0]);
        stmt.setString(6,sectionDescriptionEn[0]);
        stmt.setInt(7,Integer.parseInt(sectionNumber[0]));
        stmt.execute();
    }

    @Override
    protected void updateTable(Map<String, String[]> parameterMap, Connection connection, String dbDataLanguage) throws SQLException {
        String[] id=parameterMap.get("sectionId");
        String[] sectionName=parameterMap.get("sectionName");
        String[] sectionHeader = parameterMap.get("sectionHeader");
        String[] sectionDescription = parameterMap.get("sectionDescription");
        String[] sectionNumber = parameterMap.get("sectionNumber");
        String[] rowThatNeedToUpdate = parameterMap.get("checkSection");
        String sql = "UPDATE lib_section SET lib_section_name"+dbDataLanguage+"=?,lib_section_description"+dbDataLanguage+"=?," +
                "lib_section_header"+dbDataLanguage+"=?,lib_section_number=? WHERE lib_section_id=?";
        for(String i:rowThatNeedToUpdate)
        {
            int j= Integer.valueOf(i)-1;
            /**/
            PreparedStatement stmt=connection.prepareStatement(sql);
            stmt.setString(1,sectionName[j]);
            stmt.setString(2,sectionDescription[j]);
            stmt.setString(3,sectionHeader[j]);
            stmt.setInt(4,Integer.parseInt(sectionNumber[j]));
            stmt.setInt(5,Integer.parseInt(id[j]));
            stmt.execute();

        }
    }

    @Override
    protected void deleteFromTable(Map<String, String[]> parameterMap, Connection connection) throws SQLException {
        String[] rowThatNeedDelete = parameterMap.get("checkSection");
        String[] id=parameterMap.get("sectionId");
        String sql = "DELETE FROM lib_section WHERE lib_section_id=?";
        for (String i : rowThatNeedDelete) {
            int j = Integer.valueOf(i);
            /**/
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id[j-1]));
            stmt.execute();

        }
    }
}
