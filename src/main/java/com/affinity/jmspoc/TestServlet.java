package com.affinity.jmspoc;

import com.affinity.jmspoc.Resources;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.Topic;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class illustrate WebServlet annotation
 *
 * @author Shing Wai Chan
 * @author Daniel Guo
 */
@WebServlet(name = "TestServlet", urlPatterns = {"/"})
public class TestServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(TestServlet.class.getName());

    @Resource(mappedName = Resources.REQUEST_QUEUE)
    private Queue queue;
    
    @Resource(mappedName = Resources.REQUEST_TOPIC)
    private Topic topic;

    @Inject
    @JMSConnectionFactory(Resources.CONNECTION_FACTORY)
    private JMSContext context;

    public void sendMessage() {
        if (context == null) {
            LOG.severe("*****************************JMS Context is null.****************************");
        } else {
            JMSProducer producer = context.createProducer();
            if (queue == null) {
                LOG.severe("******************************QUEUE is null.************************************");
            } else {
                producer.send(queue, "This is a string message");
            }
        }
    }
    
    public void pulbishMessage(){
        JMSProducer producer = context.createProducer();
        producer.send(topic, "PUBLISHED MESSAGE FROM CLASS: "+this.getClass().getName());
        
    }

    private String listenerMessage = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        listenerMessage = (String) config.getServletContext().getAttribute("listenerMessage");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {
        PrintWriter writer = res.getWriter();
        sendMessage();
        pulbishMessage();
        writer.write("Hello World!! Messages have been sent. Please check the weblogic console.");
    }
}
