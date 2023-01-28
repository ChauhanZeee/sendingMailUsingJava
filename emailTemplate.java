package org.email;
import javax.mail.*;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class emailTemplate {

    private static Session getSession(){
        Properties properties = System.getProperties();// properties of my local machine
        //we need below properties for establishing connection with email service provider-- gmail and my local machine
        //host name
        //port number
        //ssl layer
        //authentication parameter
        properties.put("mail.smtp.host","smtp.gmail.com"); //key, value;
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        //1st step-- creating session for establishing connection with gmail server
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("yourMail@gmail.com", "yourPassword");
            }
        });
        return session;
    }
    public static void main(String[] args) throws MessagingException {
        String fromAddress = "submchauhan007@gmail.com";
        String toAddress = "chauhanzeee007@gmail.com";
        String ccAddress = "rawatsuchit@gmail.com";
        String messageSubject = "WELCOME";
        String messageBody = "HI i am shubham chauhan, i continuously spying on you";

        try{
            sendMailWithAttachment(fromAddress, toAddress, ccAddress, messageBody, messageSubject);
        }
        catch(MessagingException | IOException e){
            System.out.println(e);
        }
    }
    public static void sendMailWithoutAttachment(String fromAddress, String toAddress, String ccAddress, String messageBody, String messageSubject) throws MessagingException {

        Session session = getSession();
        //2nd step composing the mail.
        //addFrom setFrom
        MimeMessage message = new MimeMessage(session);
        message.setFrom(fromAddress);
        message.addRecipients(Message.RecipientType.TO, toAddress);
        message.addRecipients(Message.RecipientType.CC, ccAddress);
        message.setSubject(messageSubject);
        message.setText(messageBody);

        //3rd step-- send mail.
        Transport.send(message);
        System.out.println("Email send successfully....");

    }
    public static void sendMailWithAttachment(String fromAddress, String toAddress, String ccAddress, String messageBody, String messageSubject) throws MessagingException, IOException {
        Session session = getSession();
        //2nd step composing the mail.
        //addFrom setFrom
        MimeMessage message = new MimeMessage(session);
        message.setFrom(fromAddress);
        message.addRecipients(Message.RecipientType.TO, toAddress);
        message.addRecipients(Message.RecipientType.CC, ccAddress);
        message.setSubject(messageSubject);

        MimeMultipart mimeMultipart = new MimeMultipart();
        //for body text;
        MimeBodyPart bodyText = new MimeBodyPart();
        bodyText.setText(messageBody);

        //for attachment;
        MimeBodyPart bodyAttachment = new MimeBodyPart();
        //setting body with attachment;
        String path = "C://Users//submc//OneDrive//Desktop//WhatsApp Image 2023-01-27 at 10.18.09.jpg";
        File file = new File(path);
        bodyAttachment.attachFile(file);

        //setting bodypart to multipart object;
        mimeMultipart.addBodyPart(bodyText);
        mimeMultipart.addBodyPart(bodyAttachment);

        message.setContent(mimeMultipart);

        //3rd step send the mail;
        Transport.send(message);
        System.out.println("Email send successfully....");
    }
}
