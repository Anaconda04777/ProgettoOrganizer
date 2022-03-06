import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.*;
import javax.swing.JComboBox;
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


    public JCheckBox ruolo;
    public JComboBox<Object> comboResponsabili;
    public JComboBox<Object> comboProgetti;
    public JComboBox<Object> comboDipendenti;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    public Modale(String tipo, Azienda azienda, GrigliaDP GUI) {
        this.GUI = GUI;
        this.tipo = tipo;
        this.azienda = azienda;
        griglia = new JPanel();
        if (tipo == "Dipendenti") {
            griglia.setLayout(new GridLayout(2,5));
            creaGriglia(tipo);
        }
        else if (tipo == "Attivita") {
            griglia.setLayout(new GridLayout(2,5));
            creaGriglia(tipo);
        }
        else { 
            griglia.setLayout(new GridLayout(2,3));
            creaGriglia(tipo);
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
            griglia.add(new JLabel("", SwingConstants.CENTER));

            for (int i = 0; i < 4; i++) {
                JTextField txt = new JTextField();
                
                boxTesto.add(txt);
                griglia.add(txt);
            }

            ruolo = new JCheckBox("Responsabile?");
            griglia.add(ruolo);
        }   
        else if (tipo == "Attivita") {
            griglia.add(new JLabel("Durata", SwingConstants.CENTER));
            griglia.add(new JLabel("Descrizione", SwingConstants.CENTER));
            griglia.add(new JLabel("Data Svolgimento", SwingConstants.CENTER));
            griglia.add(new JLabel("Progetto", SwingConstants.CENTER));
            griglia.add(new JLabel("Dipendente", SwingConstants.CENTER));

            for (int i = 0; i < 3; i++) {
                JTextField txt = new JTextField();
                
                boxTesto.add(txt);
                griglia.add(txt);
            }

            ArrayList<?> progetto = azienda.progetti;
            comboProgetti = new Combo((ArrayList<Object>) progetto);
            griglia.add(comboProgetti);

            ArrayList<?> dipendente = azienda.dipendenti;
            comboDipendenti = new Combo((ArrayList<Object>) dipendente);
            griglia.add(comboDipendenti);

        }
        else {
            griglia.add(new JLabel("Nome", SwingConstants.CENTER));
            griglia.add(new JLabel("Responsabile", SwingConstants.CENTER));
            
            JTextField txtNome = new JTextField();
            boxTesto.add(txtNome);
            griglia.add(txtNome);

            ArrayList<Object> list = new ArrayList<Object>();
            for (Dipendente i : azienda.dipendenti) {
                if (i.getClass().getName() == "Responsabile") {
                    list.add(i);
                }
            }
            

           comboResponsabili = new Combo(list);
           griglia.add(comboResponsabili);
           
        }

        this.add(griglia);
        
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (tipo == "Dipendenti") {
            Dipendente i;
            if (ruolo.isSelected()) {
                i = new Responsabile(boxTesto.get(0).getText(), 
                boxTesto.get(1).getText(), 
                LocalDate.parse(boxTesto.get(2).getText(), formatter) , 
                Integer.parseInt(boxTesto.get(3).getText()) 
                );
            }
            else {
                i = new Programmatore(boxTesto.get(0).getText(), 
                boxTesto.get(1).getText(), 
                LocalDate.parse(boxTesto.get(2).getText(), formatter) , 
                Integer.parseInt(boxTesto.get(3).getText()) 
                );
            }

            azienda.addDipendenti(i);
            
            
        }
        else if (tipo == "Attivita") {
            AttivitaOraria i = new AttivitaOraria(Integer.parseInt(boxTesto.get(0).getText()), 
                    boxTesto.get(1).getText(), 
                    LocalDate.parse(boxTesto.get(2).getText(), formatter) , 
                    (Progetto) comboProgetti.getSelectedItem(), 
                    (Dipendente) comboDipendenti.getSelectedItem()
                );
            azienda.addAttivita(i);
            
        }
        else {
            System.out.println((Responsabile) comboResponsabili.getSelectedItem());
            Progetto i = new Progetto((Responsabile) comboResponsabili.getSelectedItem(), boxTesto.get(0).getText());
            azienda.addProgetti(i);
            
        }
        GUI.aggiungiComponente();
    }
    
    /* controllo non funziona diventa sempre null
    private LocalDate erroreData() {
        try {
            return LocalDate.parse(boxTesto.get(2).getText(), formatter);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Inserisci correttamente la data: gg/mm/yyyy");
            return null;
        }
        
    }*/


    public class Combo extends JComboBox<Object> implements ActionListener, ItemListener {
        private ArrayList<?> resp;

        public Combo(ArrayList<Object> resp) {
            this.resp = resp;
            this.setPreferredSize(new Dimension(150, 40));
            this.addActionListener(this);
            this.addItemListener(this);
            this.setVisible(true);

            addIt();
        }
        
        private void addIt() {
            for (Object i : resp) {
                this.addItem(i);   
            }
            
        }
         
        @Override
        public void itemStateChanged(ItemEvent e) {
            
            
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println(((JComboBox<Responsabile>) e.getSource()).getSelectedItem());
            
        }

    
    }

    
}
