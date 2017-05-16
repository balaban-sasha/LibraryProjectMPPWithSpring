package main.com.bsuir.library.controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Саша on 02.04.2017.
 */
public class NavigationController {
    public void setAuthFieldsCondition(HttpServletRequest request,SessionController sessionController)
    {
        request.setAttribute("temp","1");
    }
}
