package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import service.DragonService;
import service.impl.DragonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sum")
public class FindSumController extends HttpServlet {
    private final DragonService dragonService;
    private final GsonBuilder gsonBuilder;

    public FindSumController() {
        this.dragonService = DragonServiceImpl.getInstance();
        this.gsonBuilder = new GsonBuilder();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        PrintWriter writer = resp.getWriter();
        Gson gson = gsonBuilder.create();

        writer.write(gson.toJson(dragonService.findSum()));
    }
}
