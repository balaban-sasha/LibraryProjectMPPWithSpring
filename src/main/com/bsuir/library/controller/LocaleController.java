package main.com.bsuir.library.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Саша on 28.03.2017.
 */
public class LocaleController {

    private Locale locale;
    private ResourceBundle resourceBundle;
    private String language;
    public void changeLocale(String language,String country)
    {
        this.locale = new Locale(language,country);
        this.resourceBundle = ResourceBundle.getBundle("resource.content",locale);
        this.language=language;
        System.out.println("locale changed");
    }
    public String getLanguage()
    {
        return this.language;
    }
    public void loadData(HttpServletRequest request)
    {
        request.setAttribute("table_language",locale.getLanguage()+"_"+locale.getCountry());
        request.setAttribute("author", this.resourceBundle.getString("author"));
        request.setAttribute("id", this.resourceBundle.getString("id"));
        request.setAttribute("author_name", this.resourceBundle.getString("author_name"));
        request.setAttribute("author_female", this.resourceBundle.getString("author_female"));
        request.setAttribute("author_patronymic", this.resourceBundle.getString("author_patronymic"));
        request.setAttribute("author_biography", this.resourceBundle.getString("author_biography"));
        request.setAttribute("book", this.resourceBundle.getString("book"));
        request.setAttribute("book_name", this.resourceBundle.getString("book_name"));
        request.setAttribute("book_date", this.resourceBundle.getString("book_date"));
        request.setAttribute("description", this.resourceBundle.getString("description"));
        request.setAttribute("book_text_link", this.resourceBundle.getString("book_text_link"));
        request.setAttribute("author_id", this.resourceBundle.getString("author_id"));
        request.setAttribute("user", this.resourceBundle.getString("user"));
        request.setAttribute("login", this.resourceBundle.getString("login"));
        request.setAttribute("password", this.resourceBundle.getString("password"));
        request.setAttribute("name", this.resourceBundle.getString("name"));
        request.setAttribute("female", this.resourceBundle.getString("female"));
        request.setAttribute("gender", this.resourceBundle.getString("gender"));
        request.setAttribute("age", this.resourceBundle.getString("age"));
        request.setAttribute("user_status", this.resourceBundle.getString("user_status"));
        request.setAttribute("section", this.resourceBundle.getString("section"));
        request.setAttribute("header_of_smth", this.resourceBundle.getString("header_of_smth"));
        request.setAttribute("number", this.resourceBundle.getString("number"));
        request.setAttribute("news_page", this.resourceBundle.getString("news_page"));
        request.setAttribute("news_status", this.resourceBundle.getString("news_status"));
        request.setAttribute("news_id", this.resourceBundle.getString("news_id"));
        request.setAttribute("section_id", this.resourceBundle.getString("section_id"));
        request.setAttribute("news_comment", this.resourceBundle.getString("news_comment"));
        request.setAttribute("text", this.resourceBundle.getString("text"));
        request.setAttribute("publicate_date", this.resourceBundle.getString("publicate_date"));
        request.setAttribute("user_id", this.resourceBundle.getString("user_id"));
        request.setAttribute("news", this.resourceBundle.getString("news"));
        request.setAttribute("message", this.resourceBundle.getString("message"));
        request.setAttribute("recipient_status", this.resourceBundle.getString("recipient_status"));
        request.setAttribute("sender_status", this.resourceBundle.getString("sender_status"));
        request.setAttribute("sender_id", this.resourceBundle.getString("sender_id"));
        request.setAttribute("recipient_id", this.resourceBundle.getString("recipient_id"));
        request.setAttribute("genre", this.resourceBundle.getString("genre"));
        request.setAttribute("comment", this.resourceBundle.getString("comment"));
        request.setAttribute("book_id", this.resourceBundle.getString("book_id"));
        request.setAttribute("chat", this.resourceBundle.getString("chat"));
        request.setAttribute("book_genre", this.resourceBundle.getString("book_genre"));
        request.setAttribute("genre_id", this.resourceBundle.getString("genre_id"));
        request.setAttribute("book_catalog", this.resourceBundle.getString("book_catalog"));
        request.setAttribute("book_status", this.resourceBundle.getString("book_status"));
        request.setAttribute("author_catalog", this.resourceBundle.getString("author_catalog"));
        request.setAttribute("author_status", this.resourceBundle.getString("author_status"));
        request.setAttribute("book_rating", this.resourceBundle.getString("book_rating"));
        request.setAttribute("raiting_count", this.resourceBundle.getString("raiting_count"));
    }
}
