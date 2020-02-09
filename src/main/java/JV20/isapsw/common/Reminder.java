package JV20.isapsw.common;

import JV20.isapsw.model.Klinika;
import JV20.isapsw.model.Pregled;
import JV20.isapsw.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.Timer;
import java.util.TimerTask;

public class Reminder {
    Timer timer;

    @Autowired
    private SalaService salaService;

    private Pregled pregled;
    private Klinika klinika;

    public Reminder(int seconds, Pregled pregled, Klinika klinika) {
        timer = new Timer();
        this.setPregled(pregled);
        this.setKlinika(klinika);
        timer.schedule(new RemindTask(), seconds*1000);
    }

    class RemindTask extends TimerTask {
        public void run() {
            System.out.println("Time's up!");

            try {
                salaService.dodijeliSaluPregleduAutomatski(pregled, klinika);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            timer.cancel(); //Terminate the timer thread
        }
    }

    public Pregled getPregled() {
        return pregled;
    }

    public void setPregled(Pregled pregled) {
        this.pregled = pregled;
    }

    public Klinika getKlinika() {
        return klinika;
    }

    public void setKlinika(Klinika klinika) {
        this.klinika = klinika;
    }
}