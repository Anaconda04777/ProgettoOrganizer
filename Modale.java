import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Modale extends JPanel implements ActionListener {
    public JButton btn;
    public ArrayList<JTextField> boxTesto = new ArrayList<JTextField>();
    public JPanel griglia;
    public String tipo;
    public Azienda azienda;
    public GrigliaDP GUI;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    public Modale(String tipo, Azienda azienda, GrigliaDP GUI) {
        this.GUI = GUI;
        this.tipo = tipo;
        this.azienda = azienda;
        if (tipo == "Dipendenti") {
            griglia = new JPanel();
            griglia.setLayout(new GridLayout(2,4));
            creaGriglia(tipo);
        }
        else {
            griglia = new JPanel();  
            griglia.setLayout(new GridLayout(2,1));
        } 

        creaBottone();
        this.setVisible(true);
        this.setSize(300, 300);

                
    }


    public void creaBottone() {
        btn = new JButton();
        btn.setText("Invia");

        btn.setSize(100, 50);
        btn.addActionListener(this);
        this.add(btn);
    }

    public void creaGriglia(String tipo) {
        if (tipo == "Dipendenti") {
            griglia.add(new JLabel("Nome", SwingConstants.CENTER));
            griglia.add(new JLabel("Cognome", SwingConstants.CENTER));
            griglia.add(new JLabel("Data di nascita", SwingConstants.CENTER));
            griglia.add(new JLabel("Stipendio", SwingConstants.CENTER));
            
            for (int i = 0; i < 4; i++) {
                JTextField txt = new JTextField();
                
                boxTesto.add(txt);
                griglia.add(txt);
            }
        }

        this.add(griglia);
        
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (tipo == "Dipendenti") {
            Dipendente i = new Programmatore(boxTesto.get(0).getText(), 
                    boxTesto.get(1).getText(), 
                    LocalDate.parse(boxTesto.get(2).getText(), formatter) , 
                    Integer.parseInt(boxTesto.get(3).getText()) 
                );
            azienda.addDipendenti(i);
            GUI.aggiungiComponente();

            
        }
        
    }


}
