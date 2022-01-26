package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import dao.Dragon;
import dto.DragonDto;
import expetions.UserDataException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.hibernate.HibernateException;
import service.DragonService;
import service.impl.DragonServiceImpl;
import util.GsonProvider;

import javax.persistence.OptimisticLockException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet("/api/dragons")
public class DragonController extends HttpServlet {
    private final DragonService dragonService;

    public DragonController() {
        this.dragonService = DragonServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        PrintWriter writer = resp.getWriter();
        Gson gson = GsonProvider.gson;

        Long id = (Long) req.getAttribute("id");
        DragonDto dragon = dragonService.getDragonById(id);

        if (dragon != null) {
            writer.write(gson.toJson(dragon));
        } else {
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        PrintWriter writer = resp.getWriter();
        Gson gson = GsonProvider.gson;

        DragonDto dragon = (DragonDto) req.getAttribute("dragon");

        try {

            DragonDto savedDragon = dragonService.save(dragon);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            writer.write(gson.toJson(savedDragon));
        } catch (ValidationException | HibernateException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writer.write(gson.toJson(e.getMessage()));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        PrintWriter writer = resp.getWriter();
        Gson gson = GsonProvider.gson;

        Long id = (Long) req.getAttribute("id");
        try {
            dragonService.removeElement(id);
            resp.setStatus(HttpServletResponse.SC_OK);
        } catch (UserDataException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writer.write(gson.toJson(e.getMessage()));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            writer.write(gson.toJson(e.getMessage()));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        PrintWriter writer = resp.getWriter();
        Gson gson = GsonProvider.gson;

        DragonDto dragon = (DragonDto) req.getAttribute("dragon");

        try {
            writer.write(gson.toJson(dragonService.updateElement(dragon)));

        } catch (ValidationException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writer.write(gson.toJson(e.getMessage()));
        } catch (OptimisticLockException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writer.write(gson.toJson("Update failed! Updated element does not exists"));
        }
    }
}
