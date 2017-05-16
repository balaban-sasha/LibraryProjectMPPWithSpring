import main.com.bsuir.library.bean.Author;
import main.com.bsuir.library.bean.User;
import main.com.bsuir.library.dao.implementation.author.implementation.AuthorDao;
import main.com.bsuir.library.dao.implementation.user.implementation.UserDao;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Al Yen on 24.04.2017.
 */
public class Test_AuthorDao {



    @Test
    public void parseResultSet() throws Exception {
        AuthorDao userDao = new AuthorDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        userDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Author> dataList = userDao.getDataFromDB("SELECT * FROM lib_author WHERE lib_author_id=1","");
        Assert.assertEquals(1, dataList.size());
    }

    @Test
    public void updateTable() throws Exception
    {
        AuthorDao authorDao = new AuthorDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        authorDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Author> newsList = authorDao.getDataFromDB("SELECT * FROM lib_author WHERE lib_author_id=1","");
        String newAuText;
        newAuText = newsList.get(0).getAuthorName();
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();

        String[] authorId=new String[1];
        authorId[0]="1";
        String[] authorName = new String[1];
        authorName[0]="Рей";
        String[] authorNameNew = new String[1];
        authorNameNew[0]="NN";
        String[] authorFemale = new String[1];
        authorFemale[0]="Брэдбери";
        String[] authorPatronymic = new String[1];
        authorPatronymic[0]="";
        String[] authorBiography = new String[1];
        authorBiography[0]="Родился 22 августа 1920 года в Уокигане (штат Иллинойс). Второе имя — Дуглас — он получил в честь знаменитого актёра того времени Дугласа Фэрбенкса. Отец — Леонард Сполдинг Брэдбери (потомок англичан-первопоселенцев). Мать — Мари Эстер Моберг, шведка по происхождению.\n" +
                "\n" +
                "В 1934 году семья Брэдбери перебирается в Лос-Анджелес, где Рэй проживает и по сей день Высшего образования не получил.\n" +
                "\n" +
                "Первый рассказ опубликовал в шестнадцать лет, «Дилемма Холлербохена» была опубликована в лос-анджелесском журнале «Imagination» в январе 1938 года; рассказ «Pendulum» (1941 — в соавт. с Генри Хассе). Дебютный сборник «Темный карнавал» (Dark Carnival) опубликовал в 1947 году.\n" +
                "\n" +
                "После выхода в 1950 году сборника связанных новелл «Марсианские хроники» («The Martian Chronicles») получил широкую известность, которую закрепил последующими книгами. Практически каждая книга Брэдбери становилась явлением в современной литературе, будь то «451 по Фаренгейту», «Вино из одуванчиков», «Что-то страшное грядет» или «Смерть — удел одиноких».\n" +
                "\n" +
                "Почетный доктор литературы колледжа Уиттьер (штат Калифорния). Лауреат премий О'Генри, Бенджамина Франклина, премии Американской академии, премии Энн Радклифф, премии «Гэндальф». Обладатель нескольких наград в области фантастики: Небьюла — 1988, Хьюго — 1954. В 1988 году удостоен титула «Гранд-мастер», год спустя получил премию Брэма Стокера «за достижения всей жизни». Было снято множество кинофильмов по его произведениям. В 70-е годы вышел сериал на сюжеты его рассказов.\n" +
                "\n" +
                "Что касается личного, то всю свою жизнь Брэдбери жил с одной женщиной — Маргарэт (Marguerite McClure). Они поженились 27 сентября 1947 года. С этого дня в течение нескольких лет она работала целыми днями, чтобы Рэй мог оставаться дома и работать над книгами, изучила четыре языка, стала истинным знатоком литературы. В семье Брэдбери родились 4 дочери: Тина, Рамона, Сюзан и Александра.\n" +
                "\n" +
                "Маргарэт умерла 24 ноября 2003 года.";
        String[] authorNameEn = new String[1];
        authorNameEn[0]="Raymond ";
        String[] authorFemaleEn = new String[1];
        authorFemaleEn[0]="Bradbury  ";
        String[] authorPatronymicEn = new String[1];
        authorPatronymicEn[0]="";
        String[] authorBiographyEn = new String[1];
        authorBiographyEn[0]="Born August 22, 1920 in Waukegan (Illinois). The second name - Douglas - he was named after the famous actor of the time Douglas Fairbanks. Father - Leonard Spaulding Bradbury (a descendant of the first settlers, the British). The mother - Esther Marie Moberg, a Swede by birth.\n" +
                "\n" +
                "In 1934, the Bradbury family moved to Los Angeles, where Ray lives to this day have not received Higher Education.\n" +
                "\n" +
                "The first story was published in sixteen years, \"Dilemma Hollerbohena\" was published in the Los Angeles \"Imagination\" magazine in January 1938; story \"The Pendulum\" (1941 - co-author with Henry Hasse.). The debut collection \"Dark Carnival» (Dark Carnival) published in 1947.\n" +
                "\n" +
                "After leaving in 1950, a collection of related short stories \"The Martian Chronicles\" ( \"The Martian Chronicles\") has been gaining popularity, which secured the subsequent books. Almost every book Bradbury became a phenomenon in the contemporary literature, whether \"Fahrenheit 451,\" \"Dandelion Wine\", \"Something terrible is coming\" or \"Death - the lot of lonely.\"\n" +
                "\n" +
                "Honorary Doctor of Literature College Whittier (California). Winner of awards O'Henry, Benjamin Franklin, the American Academy Prize, Ann Radcliffe, the award \"Gandalf\". Winner of several awards in the field of science fiction: Nebula - 1988, Hugo - 1954. In 1988 he was awarded the title of \"Grand Master\", a year later received the Bram Stoker Award for \"lifetime achievement\". a lot of movies have been filmed in his works. In the 70 years the series was released on subjects of his stories.\n" +
                "\n" +
                "As for the personal, then his whole life Bradbury lived with a woman - Margaret (Marguerite McClure). They were married on September 27, 1947. From that day, for several years, she worked all day, so Ray could stay at home and work on the books, studied four languages, has become a true connoisseur of literature. Bradbury's family were born four daughters, Tina, Ramona, and Susan Alexander.\n" +
                "\n" +
                "Margaret died on November 24, 2003.";

        String[] rowThatNeedToUpdate = new String[1];
        rowThatNeedToUpdate[0]="1";

        parameterMap.put("authorId",authorId);
        parameterMap.put("authorName",authorName);
        parameterMap.put("authorSurname",authorFemale);
        parameterMap.put("authorPatronymic",authorPatronymic);
        parameterMap.put("authorBiography",authorBiography);
        parameterMap.put("authorNameEn",authorNameEn);
        parameterMap.put("authorSurnameEn",authorFemaleEn);
        parameterMap.put("authorPatronymicEn",authorPatronymicEn);
        parameterMap.put("authorBiographyEn",rowThatNeedToUpdate);
        parameterMap.put("checkAuthor",rowThatNeedToUpdate);

        authorDao.updateAll(parameterMap,"");
        newsList=authorDao.getDataFromDB("SELECT * FROM lib_author WHERE lib_author_id=1","");
        Assert.assertEquals(newAuText,newsList.get(0).getAuthorName());
    }

    @Test
    public void deleteFromTable() throws Exception {
        AuthorDao userDao = new AuthorDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        userDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        List<Author> authorList = userDao.getDataFromDB("SELECT * FROM lib_author WHERE lib_author_id=1","");

        String idThatNeedToDelete="1";
        Assert.assertEquals(1,authorList.size());
        Map<String,String[]> parametrMap =new HashMap<String,String[]>();
        String[] id=new String[1];
        id[0]=idThatNeedToDelete;
        String[] checkId=new String[1];
        checkId[0]="1";
        parametrMap.put("authorId",id);
        parametrMap.put("checkAuthor",checkId);

        userDao.deleteAll(parametrMap);
        authorList=userDao.getDataFromDB("SELECT * FROM lib_author WHERE lib_author_id="+idThatNeedToDelete,"");
        Assert.assertNotEquals(1,authorList.size());
    }

    @Test
    public void insertToTable() throws Exception
    {
        AuthorDao userDao = new AuthorDao();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resource.config");
        userDao.startConnectionToDB(resourceBundle.getString("url"), resourceBundle.getString("login"), resourceBundle.getString("password"));
        String idThatNeedToDelete="2";
        Map<String,String[]> parameterMap = new HashMap<String, String[]>();

        String[] authorId=new String[1];
        authorId[0]="1";
        String[] authorName = new String[1];
        authorName[0]="Рей";
        String[] authorNameNew = new String[1];
        authorNameNew[0]="NN";
        String[] authorFemale = new String[1];
        authorFemale[0]="Брэдбери";
        String[] authorPatronymic = new String[1];
        authorPatronymic[0]="";
        String[] authorBiography = new String[1];
        authorBiography[0]="Родился 22 августа 1920 года в Уокигане (штат Иллинойс). Второе имя — Дуглас — он получил в честь знаменитого актёра того времени Дугласа Фэрбенкса. Отец — Леонард Сполдинг Брэдбери (потомок англичан-первопоселенцев). Мать — Мари Эстер Моберг, шведка по происхождению.\n" +
                "\n" +
                "В 1934 году семья Брэдбери перебирается в Лос-Анджелес, где Рэй проживает и по сей день Высшего образования не получил.\n" +
                "\n" +
                "Первый рассказ опубликовал в шестнадцать лет, «Дилемма Холлербохена» была опубликована в лос-анджелесском журнале «Imagination» в январе 1938 года; рассказ «Pendulum» (1941 — в соавт. с Генри Хассе). Дебютный сборник «Темный карнавал» (Dark Carnival) опубликовал в 1947 году.\n" +
                "\n" +
                "После выхода в 1950 году сборника связанных новелл «Марсианские хроники» («The Martian Chronicles») получил широкую известность, которую закрепил последующими книгами. Практически каждая книга Брэдбери становилась явлением в современной литературе, будь то «451 по Фаренгейту», «Вино из одуванчиков», «Что-то страшное грядет» или «Смерть — удел одиноких».\n" +
                "\n" +
                "Почетный доктор литературы колледжа Уиттьер (штат Калифорния). Лауреат премий О'Генри, Бенджамина Франклина, премии Американской академии, премии Энн Радклифф, премии «Гэндальф». Обладатель нескольких наград в области фантастики: Небьюла — 1988, Хьюго — 1954. В 1988 году удостоен титула «Гранд-мастер», год спустя получил премию Брэма Стокера «за достижения всей жизни». Было снято множество кинофильмов по его произведениям. В 70-е годы вышел сериал на сюжеты его рассказов.\n" +
                "\n" +
                "Что касается личного, то всю свою жизнь Брэдбери жил с одной женщиной — Маргарэт (Marguerite McClure). Они поженились 27 сентября 1947 года. С этого дня в течение нескольких лет она работала целыми днями, чтобы Рэй мог оставаться дома и работать над книгами, изучила четыре языка, стала истинным знатоком литературы. В семье Брэдбери родились 4 дочери: Тина, Рамона, Сюзан и Александра.\n" +
                "\n" +
                "Маргарэт умерла 24 ноября 2003 года.";
        String[] authorNameEn = new String[1];
        authorNameEn[0]="Raymond ";
        String[] authorFemaleEn = new String[1];
        authorFemaleEn[0]="Bradbury  ";
        String[] authorPatronymicEn = new String[1];
        authorPatronymicEn[0]="";
        String[] authorBiographyEn = new String[1];
        authorBiographyEn[0]="Born August 22, 1920 in Waukegan (Illinois). The second name - Douglas - he was named after the famous actor of the time Douglas Fairbanks. Father - Leonard Spaulding Bradbury (a descendant of the first settlers, the British). The mother - Esther Marie Moberg, a Swede by birth.\n" +
                "\n" +
                "In 1934, the Bradbury family moved to Los Angeles, where Ray lives to this day have not received Higher Education.\n" +
                "\n" +
                "The first story was published in sixteen years, \"Dilemma Hollerbohena\" was published in the Los Angeles \"Imagination\" magazine in January 1938; story \"The Pendulum\" (1941 - co-author with Henry Hasse.). The debut collection \"Dark Carnival» (Dark Carnival) published in 1947.\n" +
                "\n" +
                "After leaving in 1950, a collection of related short stories \"The Martian Chronicles\" ( \"The Martian Chronicles\") has been gaining popularity, which secured the subsequent books. Almost every book Bradbury became a phenomenon in the contemporary literature, whether \"Fahrenheit 451,\" \"Dandelion Wine\", \"Something terrible is coming\" or \"Death - the lot of lonely.\"\n" +
                "\n" +
                "Honorary Doctor of Literature College Whittier (California). Winner of awards O'Henry, Benjamin Franklin, the American Academy Prize, Ann Radcliffe, the award \"Gandalf\". Winner of several awards in the field of science fiction: Nebula - 1988, Hugo - 1954. In 1988 he was awarded the title of \"Grand Master\", a year later received the Bram Stoker Award for \"lifetime achievement\". a lot of movies have been filmed in his works. In the 70 years the series was released on subjects of his stories.\n" +
                "\n" +
                "As for the personal, then his whole life Bradbury lived with a woman - Margaret (Marguerite McClure). They were married on September 27, 1947. From that day, for several years, she worked all day, so Ray could stay at home and work on the books, studied four languages, has become a true connoisseur of literature. Bradbury's family were born four daughters, Tina, Ramona, and Susan Alexander.\n" +
                "\n" +
                "Margaret died on November 24, 2003.";


        parameterMap.put("authorId",authorId);
        parameterMap.put("authorName",authorNameNew);
        parameterMap.put("authorSurname",authorFemale);
        parameterMap.put("authorPatronymic",authorPatronymic);
        parameterMap.put("authorBiography",authorBiography);
        parameterMap.put("authorNameEn",authorNameEn);
        parameterMap.put("authorSurnameEn",authorFemaleEn);
        parameterMap.put("authorPatronymicEn",authorPatronymicEn);
        parameterMap.put("authorBiographyEn",authorBiographyEn);

        userDao.insert(parameterMap);

        List<Author> AuthorList = userDao.getDataFromDB("SELECT * FROM lib_author WHERE lib_author_id=1"+authorId[0]+"1","");
        Assert.assertEquals(1,AuthorList.size());
    }

}