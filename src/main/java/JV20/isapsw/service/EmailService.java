package JV20.isapsw.service;

import JV20.isapsw.dto.GodisnjiOdsustvoTerminDTO;
import JV20.isapsw.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Async
    public void sendPregledEmail(Pregled pregled, Long brojSale) throws MailException, InterruptedException {

        //Simulacija duze aktivnosti da bi se uocila razlika
        Thread.sleep(1000);
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(pregled.getLekar().getEmail(), pregled.getPacijent().getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Naša mala klinika - Zakazan pregled");
        mail.setText("Pozdrav,\n\npregled je rezervisan za " + pregled.getTermin().getPocetak()
                +" - " + pregled.getTermin().getKraj()+  " za pacijenta "+ pregled.getPacijent().getIme() +" " +pregled.getPacijent().getPrezime() +
                " i za lekara "+ pregled.getLekar().getIme()  +" " +pregled.getLekar().getPrezime() +".\n\nNaša mala klinika");
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }

    @Async
    public void sendPregledLekara(Pregled pregled, Klinika klinika) throws MailException, InterruptedException {

        //Simulacija duze aktivnosti da bi se uocila razlika
        Thread.sleep(1000);
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        for(AdministratorKlinike a : klinika.getAdminiKlinike()){
            mail.setTo(a.getEmail());
            mail.setFrom(env.getProperty("spring.mail.username"));
            mail.setSubject("Naša mala klinika - Zakazan pregled");
            mail.setText("Pozdrav,\n\npregled je rezervisan za " + pregled.getTermin().getPocetak()
                    +" - " + pregled.getTermin().getKraj()+  " za pacijenta "+ pregled.getPacijent().getIme() +" " +pregled.getPacijent().getPrezime() +
                    " i za lekara "+ pregled.getLekar().getIme()  +" " +pregled.getLekar().getPrezime() +".\nČeka se na Vaše odobrenje." +
                    "\n\nNaša mala klinika");
            javaMailSender.send(mail);
        }

        System.out.println("Email poslat!");
    }


    @Async
    public void sendOperacijaLekara(Operacija operacija, Klinika klinika) throws MailException, InterruptedException {

        //Simulacija duze aktivnosti da bi se uocila razlika
        Thread.sleep(1000);
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        for(AdministratorKlinike a : klinika.getAdminiKlinike()){
            mail.setTo(a.getEmail());
            mail.setFrom(env.getProperty("spring.mail.username"));
            mail.setSubject("Naša mala klinika - Zakazana operacija");
            mail.setText("Pozdrav,\n\noperacija je rezervisana za " + operacija.getTermin().getPocetak()
                    +" - " + operacija.getTermin().getKraj()+  " za pacijenta "+ operacija.getPacijent().getIme() +" " +operacija.getPacijent().getPrezime() +
                    " i za lekara "+ operacija.getLekar().getIme()  +" " +operacija.getLekar().getPrezime() +".\nČeka se na Vaše odobrenje." +
                    "\n\nNaša mala klinika");
            javaMailSender.send(mail);
        }

        System.out.println("Email poslat!");
    }

    @Async
    public void sendGoOdsEmail(GodisnjiOdsustvoTerminDTO godisnjiOdsustvoTerminDTO, Korisnik korisnik) throws MailException, InterruptedException {
        //Simulacija duze aktivnosti da bi se uocila razlika
        Thread.sleep(1000);
        System.out.println("Slanje emaila...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(korisnik.getEmail());
        mail.setFrom(env.getProperty("spring.mail.username"));
        mail.setSubject("Naša mala klinika - Rezervacija godisnjeg odmora/odsustva");

        String odobren;
        String razlogOdbijanja = "";

        if(godisnjiOdsustvoTerminDTO.isOdobren()){
            odobren = "odobrena";
        } else {
            odobren = "odbijena";
            razlogOdbijanja = "\n\nRazlog odbijanja: " + godisnjiOdsustvoTerminDTO.getRazlogOdbijanja();
        }

        String tipOdmora = "";
        if(godisnjiOdsustvoTerminDTO.isGodisnji()){
            tipOdmora = "godišnjeg odmora ";
        } else {
            tipOdmora = "odsustva ";
        }


        String text = "Pozdrav " + korisnik.getIme() + ",\n\nobaveštavamo Vas da je rezervacija " + tipOdmora + odobren + "." + razlogOdbijanja +
                "\n\nTermin: " + godisnjiOdsustvoTerminDTO.getPocetak() + " - " + godisnjiOdsustvoTerminDTO.getKraj() +"\n\n\nNaša mala klinika";

        mail.setText(text);
        javaMailSender.send(mail);

        System.out.println("Email poslat!");
    }

}
