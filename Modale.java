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
    public JComboBox<Responsabile> comboResponsabili;
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
            
            for (int i = 0; i < 4; i++) {
                JTextField txt = new JTextField();
                
                boxTesto.add(txt);
                griglia.add(txt);
            }
        }
        else {
            griglia.add(new JLabel("Nome", SwingConstants.CENTER));
            griglia.add(new JLabel("Responsabile", SwingConstants.CENTER));
            
            JTextField txtNome = new JTextField();
            boxTesto.add(txtNome);
            griglia.add(txtNome);

            ArrayList<Responsabile> list = new ArrayList<Responsabile>();
            for (Dipendente i : azienda.dipendenti) {
                if (i.getClass().getName() == "Responsabile") {
                    list.add((Responsabile) i);
                }
            }
            

            Combo box = new Combo(list);
            comboResponsabili = box;
            griglia.add(box);


            /*for (int i = 0; i < 2; i++) {
                JTextField txt = new JTextField();
                
                boxTesto.add(txt);
                griglia.add(txt);
            }*/
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
        else {
            System.out.println((Responsabile) comboResponsabili.getSelectedItem());
            Progetto i = new Progetto((Responsabile) comboResponsabili.getSelectedItem(), boxTesto.get(0).getText());
            azienda.addProgetti(i);
            GUI.aggiungiComponente();
        }
        
    }
    
    public class Combo extends JComboBox<Responsabile> implements ActionListener, ItemListener {
        private ArrayList<Responsabile> resp;

        public Combo(ArrayList<Responsabile> resp) {
            this.resp = resp;
            this.setPreferredSize(new Dimension(150, 40));
            this.addActionListener(this);
            this.addItemListener(this);
            this.setVisible(true);

            addIt();
        }
        
        private void addIt() {
            for (Responsabile i : resp) {
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
