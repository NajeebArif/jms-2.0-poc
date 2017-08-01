/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.affinity.jmspoc;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Najeeb
 */
@MessageDriven(activationConfig = {
     @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = Resources.REQUEST_QUEUE),
     @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
 })
public class JMSReader implements MessageListener{

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("[JMS Reader example MDB] Message received: " + message.getBody(String.class));
        } catch (JMSException ex) {
            Logger.getLogger(JMSReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
