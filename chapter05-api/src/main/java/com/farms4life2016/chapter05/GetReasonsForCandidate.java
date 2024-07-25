package com.farms4life2016.chapter05;

import com.farms4life2016.chapter03.WaifumonService;
import com.farms4life2016.chapter03.model.Reason;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/candidates")
public class GetReasonsForCandidate extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // grabs a spring application context from the request
        ApplicationContext context = (ApplicationContext) req.getServletContext().getAttribute("context");

        // get a waifumonservice from the application context
        WaifumonService service = context.getBean(WaifumonService.class);

        // prepare a Java Object to JSON file writer
        ObjectMapper mapper = new ObjectMapper();

        // get servlet params
        String candidate = req.getParameter("candidate");

        // validate params
        if (candidate == null) {
            log("Missing data in request: requires candidate parameter");
            resp.setStatus(500);
        } else {

            // generate output from waifmon service and convert output to JSON
            log("Getting a list of Reasons for candidate: " + candidate);
            List<Reason> data = service.getReasonsForCandidate(candidate);
            resp.setStatus(200);
            resp.getWriter().println(
                mapper.writeValueAsString(data)
            );
        }
    }
    /* Stimulate your website!
     * http://localhost:8080/vote?candidate=Gardevoir&reason=human-like
     * http://localhost:8080/vote?candidate=Gardevoir&reason=feminine+appearance
     * http://localhost:8080/vote?candidate=Braixen&reason=field
     * http://localhost:8080/vote?candidate=Braixen&reason=furry
     * http://localhost:8080/candidates?candidate=Braixen
     * http://localhost:8080/candidates?candidate=Gardevoir
     */
}
