package edu.ryan.jersey.example;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An example servlet to test wiring of my web.xml + jetty + executable war.
 */
public class ServletForJetty extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletForJetty.class);

    public ServletForJetty() {
        LOGGER.info("instantiating ServletForJetty");
    }

    @Override
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("    <head>");
        out.println("        <title>Hello Jetty</title>");
        out.println("    </head>");
        out.println("    <body>");
        out.println("        <div>");
        out.println("            hello-jetty!");
        out.println("        </div>");
        out.println("    </body>");
        out.println("</html>");
    }

}
