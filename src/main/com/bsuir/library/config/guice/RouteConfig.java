package main.com.bsuir.library.config.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import main.com.bsuir.library.controller.ViewController;
import main.com.bsuir.library.dao.implementation.authorCatalog.IAuthorCatalogDao;
import main.com.bsuir.library.dao.implementation.authorCatalog.implementation.AuthorCatalogDao;

/**
 * Created by Саша on 28.03.2017.
 */
public class RouteConfig extends AbstractModule {

    @Override
    protected void configure() {
        bind(IAuthorCatalogDao.class).to(AuthorCatalogDao.class).in(Singleton.class);
        bind(ViewController.class).in(Singleton.class);
    }


}
