package ru.shome.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import ru.shome.Main;

/**
 * Smart Homne Project. ilya.golovachev9@gmail.com
 *
 * @author ILYA_GOLOVACHEV.
 */
public class SMSService implements Runnable {

    private Properties props = null;

    public SMSService() {
        props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    public void send(String subject, String text) {
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Main.pr.getGmailAccaunt(), Main.pr.getGmailAccauntPassword());
            }

        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("SHome"));            
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Main.pr.getSmsruAccauntToSendSMS()));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("kinokrim@ya.ru"));
            Transport.send(message);
            System.out.println("SMS sent");
        } catch (MessagingException ex) {
            Logger.getLogger(SMSService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        Timer timer = new Timer();
        TimerTask ttask = new TimerTask() {
            @Override
            public void run() {
                Calendar today = new GregorianCalendar();
                String subject = "Report: " + today.getTime().toString();
                String text = "Температура: Спальня=" + Main.pr.getLiveRoomTemperature() + "; Зал=" + Main.pr.getHoleTemperature() + "; Котел=" + Main.pr.getBoilerTemperature();
                send(subject, text);
            }
        };
        timer.scheduleAtFixedRate(ttask, Main.pr.getRunUpdateTime(), Main.pr.getIntervalToSendReports());
    }

}
