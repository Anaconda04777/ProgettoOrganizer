import java.util.ArrayList;

public class Progetto {
    protected Responsabile responsabile;
    protected String nome;
    protected ArrayList<AttivitaOraria> attivitaConQuestoProgetto = new ArrayList<AttivitaOraria>();
    
    public Progetto(String nome) {
        this.nome = nome;
    }

    public Progetto(Responsabile responsabile, String nome) {
        this.responsabile = responsabile;
        this.nome = nome;
    }

    public void addAttivita(AttivitaOraria...a) {
        for (int i = 0; i < a.length; i++) {
            attivitaConQuestoProgetto.add(a[i]);
        }
    }
    

    public Responsabile getResponsabile() {
        return responsabile;
    }

    public void setResponsabile(Responsabile responsabile) {
        this.responsabile = responsabile;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<AttivitaOraria> getAttivitaConQuestoProgetto() {
        return attivitaConQuestoProgetto;
    }

    public void setAttivitaConQuestoProgetto(ArrayList<AttivitaOraria> attivitaConQuestoProgetto) {
        this.attivitaConQuestoProgetto = attivitaConQuestoProgetto;
    }

    @Override
    public String toString() {
        return "Progetto  [nome=" + nome + ", responsabile="
                + responsabile + "]";
    }
    
}
