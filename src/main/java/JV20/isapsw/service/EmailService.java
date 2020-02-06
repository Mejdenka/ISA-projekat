package JV20.isapsw.service;

import JV20.isapsw.model.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    /*
     * Koriscenje klase za ocitavanje vrednosti iz application.properties fajla
     */
    @Autowired
    private Environment env;


    @Async
    public void sendConfirmedEmail(Korisnik korisnik) throws MailException, InterruptedException {

        //Simulacija duze aktivnosti da bi se uocila razlika
        Thread.sleep(1000);

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(korisnik.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Naša mala klinika - Uspešna registracija");
        mail.setText("Pozdrav " + korisnik.getIme() + ",\n\nVaša registracija je prihvaćena. \n\nDobro došli!\n\n\nNaša mala klinika");
        javaMailSender.send(mail);
    }

    @Async
    public void sendRejectedEmail(Korisnik korisnik) throws MailException, InterruptedException {

        //Simulacija duze aktivnosti da bi se uocila razlika
        Thread.sleep(1000);

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(korisnik.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Naša mala klinika - Neuspešna registracija");
        mail.setText("Pozdrav " + korisnik.getIme() + ",\n\nžao nam je. Vaša registracija je odbijena.\n\nRazlog: \n\n\nNaša mala klinika");
        javaMailSender.send(mail);

    }

}
