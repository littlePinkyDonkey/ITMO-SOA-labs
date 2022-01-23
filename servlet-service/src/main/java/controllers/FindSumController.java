package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import service.DragonService;
import service.impl.DragonServiceImpl;
import util.GsonProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/api/sum")
public class FindSumController extends HttpServlet {
    private final DragonService dragonService;

    public FindSumController() {
        this.dragonService = DragonServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        PrintWriter writer = resp.getWriter();
        Gson gson = GsonProvider.gson;

        writer.write(gson.toJson(dragonService.findSum()));
    }
}
