package filters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import dto.DragonDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebFilter("/dragons")
public class DragonFilter implements Filter {
    private final GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.gson = gsonBuilder.create();
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        try {
            if (req.getMethod().equalsIgnoreCase("post") || req.getMethod().equalsIgnoreCase("put")) {

                String jsonDragon = servletRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                DragonDto dto = gson.fromJson(jsonDragon, DragonDto.class);

                req.setAttribute("dragon", dto);
            } else {
                Long id = Long.valueOf(req.getParameter("id"));

                req.setAttribute("id", id);
            }

            filterChain.doFilter(req, resp);
        } catch (Exception e) {
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            servletResponse.getWriter().write(gson.toJson("Incorrect data!"));
        }
    }

    @Override
    public void destroy() {

    }
}
