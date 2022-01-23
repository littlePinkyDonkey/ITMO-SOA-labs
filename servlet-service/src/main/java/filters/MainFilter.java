package filters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import expetions.UserDataException;
import util.OperandInfo;
import util.enums.FilterOperands;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/")
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

        resp.setContentType("application/json");

        String sortingOperand = req.getParameter("order_by");
        String filterOperand = req.getParameter("filter_by");

        String pageNumber = req.getParameter("page");
        String pageSize = req.getParameter("size");

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

            if (pageNumber != null) {
                int page = Integer.parseInt(pageNumber);
                if (page < 0) {
                    throw new NumberFormatException();
                } else {
                    req.setAttribute("page", page);
                }
            } else {
                req.setAttribute("page", 0);
            }

            if (pageSize != null) {
                int size = Integer.parseInt(pageSize);
                if (size < 0) {
                    throw new NumberFormatException();
                } else {
                    req.setAttribute("size", size);
                }
            } else {
                req.setAttribute("size", 5);
            }

            chain.doFilter(req, resp);

        } catch (JsonSyntaxException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(gson.toJson("Incorrect filters!"));
        } catch (UserDataException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(gson.toJson(e.getMessage()));
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(gson.toJson("Incorrect page parameters!"));
        }
    }

    @Override
    public void destroy() {

    }
}
