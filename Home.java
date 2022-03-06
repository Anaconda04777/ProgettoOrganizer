import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.*;
import javax.swing.BorderFactory;

public class Home {
    public Azienda a;
    public JFrame f; // finestra principale
    public JLabel titolo; // label titolo

    public JButton btnProgetti; // bottone 1
    public JButton btnDipendenti; // botton
    public JButton btnAttivita;



    public Home(Azienda a) {
        this.a = a;
        f = new JFrame("ITIS");
        // dimensionamento
        f.setLocation(200, 200); // posizionamento
        f.addWindowListener(new GestoreFinestre());
        f.setLayout(new GridBagLayout());
        creaObj();
        f.setSize(600, 400);
        f.setVisible(true); // visualizzazione



    }

    private void creaObj() {
        JPanel pannelloPrincipale = new JPanel();
        JPanel pannelloBottoni = new JPanel();
        
        titolo = new JLabel("Organizer");
        titolo.setFont(new Font("Monospaced", Font.PLAIN, 40));

        btnProgetti = new JButton("Progetti");
        btnDipendenti = new JButton("Dipendenti");
        btnAttivita = new JButton("Attività");
        btnProgetti.addActionListener(new ActionListenerProgetti());
        btnDipendenti.addActionListener(new ActionListenerDipendenti());
        btnAttivita.addActionListener(new ActionListenerAttivita());


        //pannelloPrincipale.setLayout(new BoxLayout(pannelloPrincipale, BoxLayout.Y_AXIS));
        //pannelloBottoni.setLayout(new BoxLayout(pannelloBottoni, BoxLayout.X_AXIS));
        

        pannelloBottoni.add(btnProgetti);
        pannelloBottoni.add(btnDipendenti);
        pannelloBottoni.add(btnAttivita);


        pannelloPrincipale.add(titolo);
        pannelloPrincipale.add(pannelloBottoni);

        
        f.add(pannelloPrincipale);

        
    }

    public class ActionListenerProgetti implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GrigliaDP i = new GrigliaDP("Progetti", a, f);
            f.setVisible(false);
    
        }
    }
    public class ActionListenerDipendenti implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GrigliaDP i = new GrigliaDP("Dipendenti", a, f);
            f.setVisible(false);
        }
    }
    public class ActionListenerAttivita implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GrigliaDP i = new GrigliaDP("Attivita", a, f);
            f.setVisible(false);
        }
    }

    /*
     * Unico metodo dell'interfaccia ActionListener
     * Gestisce gli eventi sul bottone
     */
    public static void main(String[] args) {
        Azienda a = new Azienda("Sos");
        Programmatore schiavo1 = new Programmatore("Giovanni", "Bianchi", LocalDate.of(2007, 12, 01), 300);
        Responsabile schiavizzatore1 = new Responsabile("Stefan", "Gherghina", LocalDate.of(2007, 12, 01), 7000, 7);
        Progetto p1 = new Progetto(schiavizzatore1, "Sus");
        a.addDipendenti(schiavo1, schiavizzatore1);
        a.addProgetti(p1);
        

        AttivitaOraria a1 = new AttivitaOraria(1000, "osos", LocalDate.of(2007, 12, 01), p1, schiavo1);
        AttivitaOraria a2 = new AttivitaOraria(1200, "osos", LocalDate.of(2007, 12, 01), p1, schiavo1);
        AttivitaOraria a3 = new AttivitaOraria(1300, "osos", LocalDate.of(2007, 12, 01), p1, schiavo1);
        AttivitaOraria a4 = new AttivitaOraria(1400, "osos", LocalDate.of(2007, 12, 01), p1, schiavo1);

        AttivitaOraria a5 = new AttivitaOraria(1000, "osos", LocalDate.of(2007, 12, 01), p1, schiavizzatore1);
        AttivitaOraria a6 = new AttivitaOraria(1200, "osos", LocalDate.of(2007, 12, 01), p1, schiavizzatore1);
        AttivitaOraria a7 = new AttivitaOraria(1300, "osos", LocalDate.of(2007, 12, 01), p1, schiavizzatore1);
        AttivitaOraria a8 = new AttivitaOraria(1400, "osos", LocalDate.of(2007, 12, 01), p1, schiavizzatore1);
        
        a.addAttivita(a1,a2,a3,a4,a5,a6,a7,a8);
        Home h = new Home(a);

        
        
    }
}
