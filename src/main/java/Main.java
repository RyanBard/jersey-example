import java.net.URL;
import java.security.ProtectionDomain;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * A main class to start up jetty.
 *
 * Started with http://uguptablog.blogspot.com/2012/09/embedded-jetty-executable-war-with.html and
 * http://git.eclipse.org/c/jetty/org.eclipse.jetty.project.git/tree/examples/embedded/src/main/java/org/eclipse/jetty/embedded/ManyConnectors.java
 * and pruned it to only what I thought I needed.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server();

        HttpConfiguration httpConfiguration = new HttpConfiguration();
        httpConfiguration.setOutputBufferSize(32768);

        ServerConnector http = new ServerConnector(
                server,
                new HttpConnectionFactory(httpConfiguration)
        );
        http.setPort(8080);
        http.setIdleTimeout(30000);

        server.setConnectors(new Connector[] {http});

        ProtectionDomain domain = Main.class.getProtectionDomain();
        URL location = domain.getCodeSource().getLocation();
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar(location.toExternalForm());
        server.setHandler(webapp);

        server.start();
        server.join();
    }
}
