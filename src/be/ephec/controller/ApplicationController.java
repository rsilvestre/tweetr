package be.ephec.controller;

import javax.servlet.GenericServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by michaelsilvestre on 24/05/14.
 */
public abstract class ApplicationController extends ValidationController {
    private final GenericServlet servlet;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public ApplicationController(GenericServlet servlet, HttpServletRequest request, HttpServletResponse response) {
        this.servlet = servlet;
        this.request = request;
        this.response = response;
    }

    protected GenericServlet getServlet() {
        return servlet;
    }

    protected HttpServletRequest getRequest() {
        return request;
    }

    protected HttpServletResponse getResponse() {
        return response;
    }
}
