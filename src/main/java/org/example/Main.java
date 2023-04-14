package org.example;

import org.example.email.EmailSender;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        EmailSender emailSender = new EmailSender();
        String to = "chauhanzeee007@gmail.com";
        String from = "submchauhan007@gmail.com";
        String subject = "message with attachment";
        String text = "cristiano ronaldo";
        File file = new File("C:\\Users\\submc\\OneDrive\\Desktop\\wall\\siu.jpg");

        boolean b = emailSender.sendEmailWithAttachment(to, from, subject, text, file);
        if(b){
            System.out.println("message is sent successfully");
        }else{
            System.out.println("not sent");
        }

//        boolean b = emailSender.sendEmail(to, from, subject, text);
//        if(b){
//            System.out.println("message is sent successfully");
//        }else{
//            System.out.println("not sent");
//        }
    }
}