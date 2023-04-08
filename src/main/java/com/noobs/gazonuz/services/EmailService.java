package com.noobs.gazonuz.services;


import com.noobs.gazonuz.configs.properties.ApplicationProperties;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

@Service
public class EmailService {


    private final ApplicationProperties properties;

    public EmailService(ApplicationProperties properties) {
        this.properties = properties;
    }

    public void sendMessageToEmailThroughSMTP(String toUser , String messageBody , String messageHeader) {

        Runnable runnableEmailSender = () -> {

            final Properties myProperty = properties.getProperties();
            String from = myProperty.getProperty("google.send.email.email");
            String password = myProperty.getProperty("google.send.email.password");
            Session session = Session.getInstance(myProperty , new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from , password);
                }
            });

            try {

                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO , new InternetAddress(toUser));
                message.setSubject(messageHeader);
                message.setText(messageBody);
                Transport.send(message);
                System.out.println("Sent message successfully....");
            } catch ( MessagingException mex ) {
                mex.printStackTrace();
            }
        };
        CompletableFuture.runAsync(runnableEmailSender);
    }


}