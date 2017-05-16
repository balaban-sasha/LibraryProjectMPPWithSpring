package main.com.bsuir.library.controller.data;

import java.util.HashMap;

/**
 * Created by Саша on 21.04.2017.
 */
public class BeanControllerFactory {
    private HashMap<String,AbstractBeanController> beanControllerHashMap = new HashMap<String,AbstractBeanController>();
    public BeanControllerFactory()
    {
        beanControllerHashMap.put("AuthorCatalog",new AuthorDataCatalogController());
        beanControllerHashMap.put("Author",new AuthorDataController());
        beanControllerHashMap.put("Book",new BookDataController());
        beanControllerHashMap.put("BookCatalog",new BookCatalogDataController());
        beanControllerHashMap.put("BookGenre",new BookGenreDataController());
        beanControllerHashMap.put("BookRaiting",new BookRaitingDataController());
        beanControllerHashMap.put("Comment",new CommentDataController());
        beanControllerHashMap.put("Genre",new GenreDataController());
        beanControllerHashMap.put("Message",new MessageDataController());
        beanControllerHashMap.put("NewsComment",new NewsCommentDataController());
        beanControllerHashMap.put("News",new NewsDataController());
        beanControllerHashMap.put("NewsPage",new NewsPageDataController());
        beanControllerHashMap.put("Section",new SectionDataController());
        beanControllerHashMap.put("User",new UserDataController());
        beanControllerHashMap.put("Chat",new ChatDataController());

    }
    public AbstractBeanController getBeanController(String key)
    {
        return beanControllerHashMap.get(key);
    }
}
