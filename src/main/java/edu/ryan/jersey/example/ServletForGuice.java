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
 * An example servlet to test my guice servlet module.
 */
public class ServletForGuice extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletForGuice.class);

    public ServletForGuice() {
        LOGGER.info("instantiating ServletForGuice");
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
        out.println("        <title>Hello Guice</title>");
        out.println("    </head>");
        out.println("    <body>");
        out.println("        <div>");
        out.println("            hello-guice!");
        out.println("        </div>");
        out.println("    </body>");
        out.println("</html>");
    }

}
