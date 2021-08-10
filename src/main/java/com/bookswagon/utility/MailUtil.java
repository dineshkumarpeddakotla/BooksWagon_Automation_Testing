/*
 *Purpose : Class is implemented for sending the mail
 *
 * @author Dinesh Kumar Peddakotla
 * @version 1.0
 * @since 31-07-2021
 */
package com.bookswagon.utility;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Properties;

public class MailUtil {
    private static final String USER_NAME = "dineshkumar.icon.dk@gmail.com";  // GMail user name (just the part before "@gmail.com")
    private static final String PASSWORD = "1324124318"; // GMail password
    private static final String RECIPIENT = "dineshkumar.icon.dk@gmail.com";

    //this method is used to send mail by giving the details of body subject, to address, attachment path
    public static void sendMail() {

        String[] to = {RECIPIENT}; // list of recipient email addresses
        String subject = "Test Report";
        String body = "Bookswagon test report";
        String reportPath = "C:\\Users\\dinnu\\Testing\\BooksWagon\\testReport\\BooksWagon_Testing_Report.html";
        sendFromGMail(to, subject, body, reportPath);
    }

    private static void sendFromGMail(String[] to, String subject, String body, String reportPath) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USER_NAME, PASSWORD);
            }
        });

        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(USER_NAME));
            InternetAddress[] toAddress = new InternetAddress[to.length];

// To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for (InternetAddress address : toAddress) {
                message.addRecipient(Message.RecipientType.TO, address);
            }
            message.setSubject(subject);

// Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

// Now set the actual message
            messageBodyPart.setText(body);

// Create a multipart message
            Multipart multipart = new MimeMultipart();

// Set text message part
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            String filename = "Bookswagon test report";
            File file = new File(reportPath);
            if (!file.exists()) {
                throw new FileNotFoundException("file is not found");
            }
            DataSource source = new FileDataSource(file);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
// Send the complete message parts
            message.setContent(multipart);
            Transport.send(message, message.getAllRecipients());
        } catch (MessagingException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}