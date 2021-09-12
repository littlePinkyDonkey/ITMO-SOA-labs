package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.DragonDto;
import service.DragonService;
import service.impl.DragonServiceImpl;
import util.OperandInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/main")
public class ArrayController extends HttpServlet {
    private final GsonBuilder gsonBuilder;
    private final DragonService dragonService;

    public ArrayController() {
        this.gsonBuilder = new GsonBuilder();
        this.dragonService = DragonServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        PrintWriter writer = resp.getWriter();
        Gson gson = gsonBuilder.create();

        OperandInfo orderOperand = (OperandInfo) req.getAttribute("order_by");
        OperandInfo[] filterOperands = (OperandInfo []) req.getAttribute("filter_by");

        List<DragonDto> s = dragonService.getAll(orderOperand, filterOperands);

        writer.write(gson.toJson(s));
    }
}
