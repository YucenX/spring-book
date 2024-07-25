package com.farms4life2016.chapter05;

import java.io.IOException;

import org.springframework.context.ApplicationContext;

import com.farms4life2016.chapter03.WaifumonService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * time for web design, oh boy. 
 */

 // this annotation tells us what part of the URL this Servlet will be attached to 
@WebServlet(urlPatterns = "/vote")
public class VoteForReasonServlet extends HttpServlet {
    
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
        String reason = req.getParameter("reason");

        // validate params
        if (candidate == null || reason == null) {
            log("Missing data in request: requires candidate and reason parameters");
            resp.setStatus(500);
        } else {

            // generate output from waifmon service and convert output to JSON
            log("Voting for candidate: " + candidate + " | Reason: " + reason);
            service.voteForReason(candidate, reason);
            resp.setStatus(200);
            resp.getWriter().println(
                mapper.writeValueAsString(service.getReason(candidate, reason))
            );
        }
    }
}
