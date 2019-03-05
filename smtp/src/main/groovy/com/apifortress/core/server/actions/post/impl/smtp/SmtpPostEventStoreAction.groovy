package com.apifortress.core.server.actions.post.impl.smtp

import com.apifortress.core.core.actions.post.IPostEventStoreAction
import com.apifortress.core.core.beans.MEvent
import com.apifortress.core.server.context.ConfigContext
import org.springframework.beans.factory.annotation.Autowired

import javax.annotation.PostConstruct
import javax.mail.Address
import javax.mail.Authenticator
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

/**
 * © 2019 API Fortress
 * @author Diego Brach
 *
 * This module takes in charge to send mail in case of warnings or failure durign test execution
 **/
class SmtpPostEventStoreAction implements IPostEventStoreAction {

    Properties javamailProperties;
    static String JM_KEY_DO_AUTH = "mail.smtp.auth";
    static String JM_KEY_TRANSPORT = "mail.transport.protocol";
    static String JM_VALUE_TRANSPORT = "smtp";
    static String JM_KEY_SOCKERFACTORY = "mail.smtp.socketFactory.class";
    static String JM_VALUE_SOCKERFACTORY = "javax.net.ssl.SSLSocketFactory";
    static String JM_KEY_HOST = "mail.smtp.host";
    static String JM_KEY_PORT = "mail.smtp.port";
    static String JM_KEY_USER = "mail.smtp.user";
    static String JM_KEY_PASSWORD = "mail.smtp.password";
    static String JM_START_TLS = "mail.smtp.starttls.enable";

    @Autowired
    ConfigContext configContext

    @PostConstruct
    void init(){
        javamailProperties = new Properties();
        javamailProperties.put(JM_KEY_DO_AUTH, configContext.smtp.smtpAuth);
        javamailProperties.put(JM_KEY_TRANSPORT, JM_VALUE_TRANSPORT);
        javamailProperties.put(JM_KEY_SOCKERFACTORY, JM_VALUE_SOCKERFACTORY);
        javamailProperties.put(JM_KEY_HOST, configContext.smtp.smtpHost);
        javamailProperties.put(JM_KEY_PORT, configContext.smtp.smtpPort);
        javamailProperties.put(JM_START_TLS, configContext.smtp.smtpStartTLS);
        javamailProperties.put(JM_KEY_USER, configContext.smtp.smtpUsername);
        javamailProperties.put(JM_KEY_PASSWORD, configContext.smtp.smtpPassword);
    }

    @Override
    void perform(MEvent event) {
        StringBuilder sb = new StringBuilder()
        sb.append("Date assertion: $event.date <br>")
        sb.append("Test id: $event.test.id <br>")
        sb.append("Test name: $event.test.name <br>")
        sb.append("Execution time: $event.executionTimeSeconds <br>")
        sb.append(event.warningsCount >0 ? "Warnings : $event.warningsCount <br>" : "")
        sb.append(event.failuresCount >0 ? "Failures : $event.failuresCount <br>" : "")

        if (event.warningsCount > 0 || event.failuresCount > 0)
            sendMail(sb.toString())
    }

    private void sendMail(String mailBody){
        Session session = Session.getInstance(javamailProperties,new SmtpAuthenticator(javamailProperties.get(JM_KEY_USER),javamailProperties.get(JM_KEY_PASSWORD)));
        Transport transport = session.getTransport();
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(javamailProperties.getProperty(JM_KEY_USER)));
        message.setSubject("POST EVENT");
        message.setRecipients(MimeMessage.RecipientType.TO,new InternetAddress(javamailProperties.getProperty(JM_KEY_USER)));
        message.setContent(mailBody, "text/HTML");
        transport.connect();
        Address[] addresses = new Address[1];
        addresses[0] = new InternetAddress(javamailProperties.getProperty(JM_KEY_USER));
        transport.sendMessage(message, addresses);
        transport.close();
    }

    private class SmtpAuthenticator extends Authenticator{
        String user
        String password
        public SmtpAuthenticator(String user, String password){
            this.user = user
            this.password = password
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(this.user,this.password);
        }
    }



}
