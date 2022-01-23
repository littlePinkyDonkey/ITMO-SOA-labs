package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.DragonDto;
import service.DragonService;
import service.impl.DragonServiceImpl;
import util.GsonProvider;
import util.OperandInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api")
public class ArrayController extends HttpServlet {
    private final DragonService dragonService;

    public ArrayController() {
        this.dragonService = DragonServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        PrintWriter writer = resp.getWriter();
        Gson gson = GsonProvider.gson;

        OperandInfo orderOperand = (OperandInfo) req.getAttribute("order_by");
        OperandInfo[] filterOperands = (OperandInfo []) req.getAttribute("filter_by");
        Integer page = (Integer) req.getAttribute("page");
        Integer size = (Integer) req.getAttribute("size");

        try {
            List<DragonDto> s = dragonService.getAll(orderOperand, filterOperands, page, size);

            writer.write(gson.toJson(s));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            writer.write(gson.toJson("Illegal filter parameter!"));
        }
    }
}
