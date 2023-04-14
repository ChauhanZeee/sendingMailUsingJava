package org.example.email;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.File;
import java.util.Properties;

public class EmailSender {

    public boolean sendEmail(String to, String from, String subject, String text){
        boolean flag = false;
        //logic

        //SMTP properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String userName = "submchauhan007@gmail.com";
        String password = "bbhymoumtvyhtwgd";

        //session get
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        try{
            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            flag = true;
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return flag;
    }

    public boolean sendEmailWithAttachment(String to, String from, String subject, String text, File file){
        boolean flag = false;
        //logic

        //SMTP properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String userName = "submchauhan007@gmail.com";
        String password = "bbhymoumtvyhtwgd";

        //session get
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        try{
            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);

            MimeBodyPart part1 = new MimeBodyPart();
            part1.setText(text);

            MimeBodyPart part2 = new MimeBodyPart();
            part2.attachFile(file);

            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(part1);
            mimeMultipart.addBodyPart(part2);

            message.setContent(mimeMultipart);

            Transport.send(message);
            flag = true;

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
