package filters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import util.OperandInfo;
import util.enums.FilterOperands;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/main")
public class MainFilter implements Filter {
    private final GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.gson = gsonBuilder.create();
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String sortingOperand = req.getParameter("order_by");
        String filterOperand = req.getParameter("filter_by");

        try {

            if (sortingOperand != null) {
                req.setAttribute("order_by", OperandInfo.initOperand(sortingOperand));
            }

            if (filterOperand != null) {
                String[] filters = gson.fromJson(filterOperand, String[].class);
                OperandInfo[] operands = new OperandInfo[filters.length];

                for (int i = 0; i < filters.length; i++) {
                    operands[i] = OperandInfo.initOperand(filters[i]);
                }
                req.setAttribute("filter_by", operands);
            }

            chain.doFilter(req, resp);

        } catch (JsonSyntaxException e) {
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write(gson.toJson("Incorrect filter request!"));
        }
    }

    @Override
    public void destroy() {

    }
}
