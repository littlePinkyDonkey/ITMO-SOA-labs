package filters;

import com.google.gson.*;
import dto.DragonDto;
import util.GsonProvider;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@WebFilter("/api/dragons")
public class DragonFilter implements Filter {
    private Gson gson;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        gson = GsonProvider.gson;
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
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write(gson.toJson("Incorrect data! Please check your request"));
        }
    }

    @Override
    public void destroy() {

    }
}
