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

@WebServlet("/remove")
public class RemoveElementsController extends HttpServlet {
    private final DragonService dragonService;
    private final GsonBuilder gsonBuilder;

    public RemoveElementsController() {
        this.dragonService = DragonServiceImpl.getInstance();
        this.gsonBuilder = new GsonBuilder();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        PrintWriter writer = resp.getWriter();
        Gson gson = gsonBuilder.create();

        String character = req.getParameter("character");

        if (character != null) {
            int updated = dragonService.removeElementByCharacter(character);

            if (updated > 0) {
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                writer.write(gson.toJson("No elements with such character"));
            }

        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writer.write(gson.toJson("Character must be specified"));
        }
    }
}
