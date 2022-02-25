import java.time.LocalDate;
import java.util.ArrayList;

public class Azienda {
   public ArrayList<Dipendente> dipendenti = new ArrayList<Dipendente>();
   public ArrayList<Progetto> progetti = new ArrayList<Progetto>();
   public String nome;

    public Azienda(String nome) {
        this.nome = nome;
    }

    public void addProgetti(Progetto...p) {
        for (int i = 0; i < p.length; i++) {
            progetti.add(p[i]); 
        }
    }

    public void addDipendenti(Dipendente...d) {
        for (int i = 0; i < d.length; i++) {
            dipendenti.add(d[i]); 
        }
    }
    
    
    
}
