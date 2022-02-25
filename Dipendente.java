import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Dipendente
 */
public abstract class Dipendente {
    protected String nome;
    protected String cognome;
    protected LocalDate dataNascita;
    protected int stipendio;
    protected ArrayList<AttivitaOraria> oreProgetti = new ArrayList<AttivitaOraria>();
    
    public Dipendente(String nome, String cognome, LocalDate dataNascita, int stipendio) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.stipendio = stipendio;
    }

    public void addAttivita(AttivitaOraria...a) {
        for (int i = 0; i < a.length; i++) {
            oreProgetti.add(a[i]);
        }
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public LocalDate getDataNascita() {
        return dataNascita;
    }
    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }
    public int getStipendio() {
        return stipendio;
    }
    public void setStipendio(int stipendio) {
        this.stipendio = stipendio;
    }
    public ArrayList<AttivitaOraria> getOreProgetti() {
        return oreProgetti;
    }
    public void setOreProgetti(ArrayList<AttivitaOraria> oreProgetti) {
        this.oreProgetti = oreProgetti;
    }
    

    @Override
    public String toString() {
        return "Dipendente [cognome=" + cognome + ", dataNascita=" + dataNascita + ", nome=" + nome + ", stipendio=" + stipendio + "]";
    }
    


}