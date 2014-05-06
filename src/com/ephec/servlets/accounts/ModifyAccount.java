package com.ephec.servlets.accounts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModifyAccount
 */
@WebServlet("/ModifyAccount")
public class ModifyAccount extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final String MODIFYACCOUNT = "/WEB-INF/modifyAccount.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyAccount() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(MODIFYACCOUNT)
                .forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

}
