import java.time.LocalDate;

public class AttivitaOraria {
    protected int durata;
    protected String descrizione;
    protected LocalDate dataSvolgimento;
    protected Progetto progettoCorrente;
    protected Dipendente dipendenteAssociato;
    
    public AttivitaOraria(int durata, String descrizione, LocalDate dataSvolgimento, 
            Progetto progettoCorrente, Dipendente dipendenteAssociato) {
        this.durata = durata;
        this.descrizione = descrizione;
        this.dataSvolgimento = dataSvolgimento;
        this.progettoCorrente = progettoCorrente;
        this.dipendenteAssociato = dipendenteAssociato;
        dipendenteAssociato.addAttivita(this);
        progettoCorrente.addAttivita(this);
    }


    public Dipendente getDipendenteAssociato() {
        return dipendenteAssociato;
    }

    public void setDipendenteAssociato(Dipendente dipendenteAssociato) {
        this.dipendenteAssociato = dipendenteAssociato;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDate getDataSvolgimento() {
        return dataSvolgimento;
    }

    public void setDataSvolgimento(LocalDate dataSvolgimento) {
        this.dataSvolgimento = dataSvolgimento;
    }

    public Progetto getProgettoCorrente() {
        return progettoCorrente;
    }

    public void setProgettoCorrente(Progetto progettoCorrente) {
        this.progettoCorrente = progettoCorrente;
    }

    @Override
    public String toString() {
        return "AttivitaOraria [dataSvolgimento=" + dataSvolgimento + ", descrizione=" + descrizione + ", durata="
                + durata + ", progettoCorrente=" + progettoCorrente + "]";
    }
    
}
