package lt.vu.usecases;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
//@RequestScoped
@SessionScoped
public class PirmasKomponentas implements Serializable { // added Serializable for SessionScoped to support passivation and activation

    @Inject // field injection
    private antarsKomponentas antras;

    // Constructor injection
    //private final antarsKomponentas antras; // Field to hold the injected component
    //@Inject
    //public PirmasKomponentas(antarsKomponentas antras) {
       // this.antras = antras;
    //}

    public String sakykLabas(){
        try{
            System.out.println(antras.getClass().getName());
        } catch (Exception e){
            e.printStackTrace();
        }
        return "Labas " + new Date() + " " + toString();
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
