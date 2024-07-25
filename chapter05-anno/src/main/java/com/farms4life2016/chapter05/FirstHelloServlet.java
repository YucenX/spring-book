package com.farms4life2016.chapter05;

import com.samskivert.mustache.Mustache;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;

/**
 * A simple Servlet that will display Hello World or something
 */
@WebServlet(urlPatterns = "/hello1")
public class FirstHelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // my guess is that we try to open and read the html file
        try (var in = Objects.requireNonNull(this.getClass().getResourceAsStream("/hello.html"))) {
            try (var reader = new InputStreamReader(in)) {
                // then we send the html file to Mustache, which will replace all {{ name }} instances
                // with whatever we specify in the Map. we could probably expand the Map and make more
                // variables to replace
                var output = Mustache.compiler().compile(reader).execute(Map.of("name", "world"));

                // after Mustache replaces the variables, the output is sent to the web page
                response.getWriter().println(output);
            }
        }

        /*
         * to run the webpage, run the command below in the Maven root directory:
         *      mvn -pl chapter05-anno jetty:run
         */
    }
}
