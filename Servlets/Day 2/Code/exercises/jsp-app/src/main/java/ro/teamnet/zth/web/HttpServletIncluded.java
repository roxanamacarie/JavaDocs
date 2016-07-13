/**
 * HelloWorldServlet.java
 *
 * <p>
 * Copyright (c) 2014 Teamnet. All Rights Reserved.
 * <p>
 * This source file may not be copied, modified or redistributed,
 * in whole or in part, in any form or for any reason, without the express
 * written consent of Teamnet.
 **/
package ro.teamnet.zth.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Hello World Servlet expose get method to say hello to input user
 */
public class HttpServletIncluded extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = "";




        // Set the response type
        response.setContentType("text/html");

        // Obtain the user from the request instance
       // user = request.getParameter("user");


        response.getWriter().write("Hello <b> RoxanaS </b> from the Forward Servlet "+ request.getAttribute("testAttribute").toString());
    }
}
