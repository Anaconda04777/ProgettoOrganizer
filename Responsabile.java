import java.time.LocalDate;

public class Responsabile extends Dipendente{
    protected int inquadramenteSocietario;

    public Responsabile(String nome, String cognome, LocalDate dataNascita, int stipendio,
            int inquadramenteSocietario) {
        super(nome, cognome, dataNascita, stipendio);
        this.inquadramenteSocietario = inquadramenteSocietario;
    }  
    
    public int getInquadramenteSocietario() {
        return inquadramenteSocietario;
    }
    public void setInquadramenteSocietario(int inquadramenteSocietario) {
        this.inquadramenteSocietario = inquadramenteSocietario;
    }   

}
