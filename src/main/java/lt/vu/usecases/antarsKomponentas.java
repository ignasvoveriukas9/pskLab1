package lt.vu.usecases;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Date;

@Named
@RequestScoped
public class antarsKomponentas {
    public String sakykLabas(){
        return "Labas2 " + new Date() + " " + toString();
    }

    @PostConstruct
    public void init(){
        System.out.println(toString() + " constructed");
    }

    @PreDestroy
    public void aboutToDie(){
        System.out.println(toString() + " ready to die");
    }
}
