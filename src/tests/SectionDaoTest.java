import main.com.bsuir.library.bean.Section;
import main.com.bsuir.library.bean.User;
import main.com.bsuir.library.dao.implementation.section.implementation.SectionDao;
import main.com.bsuir.library.dao.implementation.user.implementation.UserDao;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static org.junit.Assert.*;

/**
 * Created by Al Yen on 25.04.2017.
 */
public class SectionDaoTest {
    @Test
    public void parseResultSet() throws Exception {
        SectionDao sectionDao = new SectionDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        sectionDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Section> dataList = sectionDao.getDataFromDB("SELECT * FROM lib_section WHERE lib_section_id=1","");
        Assert.assertEquals(1, dataList.size());
    }

    @Test
    public void insertToTable() throws Exception {
        SectionDao sectionDao = new SectionDao();
        String idThatNeedToDelete="5";
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        sectionDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();
         String[] sectionId=new String[1];
        sectionId[0]="1";
        String[] sectionName = new String[1];
        sectionName[0]="Главная";
        String[] sectionNameNew = new String[1];
        sectionNameNew[0]="Авторы";
        String[] sectionHeader=new String[1];
        sectionHeader[0]="Главная страница сайта";
        String[] sectionDescription = new String[1];
        sectionDescription[0]="Тут будет инфа о нашей библиотеке, что в ней есть, чем она лучше др. и т.д.";
        String[] sectionNameEn = new String[1];
        sectionNameEn[0] = "Main";
        String[] sectionHeaderEn = new String[1];
        sectionHeaderEn[0] = "Main page";
        String[] sectionDescriptionEn = new String[1];
        sectionDescriptionEn[0] = "Here will be description about our library.";
        String[] sectionNumber = new String[1];
        sectionNumber[0] = "1";


         parameterMap.put("sectionId",sectionId);
        parameterMap.put("sectionName",sectionNameNew);
        parameterMap.put("sectionHeader",sectionHeader);
        parameterMap.put("sectionDescription",sectionDescription);
        parameterMap.put("sectionNameEn",sectionNameEn);
        parameterMap.put("sectionHeaderEn",sectionHeaderEn);
        parameterMap.put("sectionDescriptionEn",sectionDescriptionEn);
        parameterMap.put("sectionNumber",sectionNumber);



        sectionDao.insert(parameterMap);


        List<Section> sectionList = sectionDao.getDataFromDB("SELECT * FROM lib_section WHERE lib_section_id=1"+sectionId[0]+"1","");
        Assert.assertEquals(1,sectionList.size());
    }

    @Test
    public void updateTable() throws Exception {
        SectionDao sectionDao = new SectionDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        sectionDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Section> sectionList = sectionDao.getDataFromDB("SELECT * FROM lib_section WHERE lib_section_id=1","");
        String sectionName;
        sectionName = sectionList.get(0).getName();
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();

        String[] id= new String[1];
        id[0]="1";
        String[] sectionNameNew = new String[1];
        sectionNameNew[0]="Главная";
        String[] sectionHeader=new String[1];
        sectionHeader[0]="Главная страница сайта";
        String[] sectionDescription = new String[1];
        sectionDescription[0]="Тут будет инфа о нашей библиотеке, что в ней есть, чем она лучше др. и т.д.";
        String[] sectionNameEn = new String[1];
        sectionNameEn[0] = "Main";
        String[] sectionHeaderEn = new String[1];
        sectionHeaderEn[0] = "Main page";
        String[] sectionDescriptionEn = new String[1];
        sectionDescriptionEn[0] = "Here will be description about our library.";
        String[] sectionNumber = new String[1];
        sectionNumber[0] = "1";
        String[] rowThatNeedToUpdate = new String[1];
        rowThatNeedToUpdate[0]="1";

        parameterMap.put("sectionId",id);
        parameterMap.put("sectionName",sectionNameNew);
        parameterMap.put("sectionHeader",sectionHeader);
        parameterMap.put("sectionDescription",sectionDescription);
        parameterMap.put("sectionNameEn",sectionNameEn);
        parameterMap.put("sectionHeaderEn",sectionHeaderEn);
        parameterMap.put("sectionDescriptionEn",sectionDescriptionEn);
        parameterMap.put("sectionNumber",rowThatNeedToUpdate);
        parameterMap.put("checkSection",rowThatNeedToUpdate);

        sectionDao.updateAll(parameterMap,"");
        sectionList=sectionDao.getDataFromDB("SELECT * FROM lib_section WHERE lib_section_id=1","");
        Assert.assertEquals(sectionName,sectionList.get(0).getName());
    }

    @Test
    public void deleteFromTable() throws Exception {
        SectionDao sectionDao = new SectionDao();
        String idThatNeedToDelete="5";
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        sectionDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Section> sectionList = sectionDao.getDataFromDB("SELECT * FROM lib_section WHERE lib_section_id="+idThatNeedToDelete,"");
        Assert.assertEquals(1,sectionList.size());
        Map<String,String[]> parametrMap =new HashMap<String,String[]>();
        String[] id=new String[1];
        id[0]=idThatNeedToDelete;
        String[] checkId=new String[1];
        checkId[0]="1";
        parametrMap.put("sectionId",id);
        parametrMap.put("checkSection",checkId);
        sectionDao.deleteAll(parametrMap);
        sectionList=sectionDao.getDataFromDB("SELECT * FROM lib_user WHERE lib_user_id="+idThatNeedToDelete,"");
        Assert.assertNotEquals(1,sectionList.size());
    }

}