package controllers;

import service.DragonService;
import service.ExtraOperationsService;
import service.impl.DragonServiceImpl;
import service.impl.ExtraOperationsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/min")
public class MinIdController extends HttpServlet {
    private final ExtraOperationsService extraOperationsService;

    public MinIdController() {
        this.extraOperationsService = ExtraOperationsServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        PrintWriter writer = resp.getWriter();
        writer.write(extraOperationsService.findMinId());
    }
}
