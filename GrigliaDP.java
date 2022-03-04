import java.awt.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;

import javax.swing.*;

public class GrigliaDP {
    public JFrame home;
    public JFrame f;
    public JButton btnNuovo;
    public JButton btnMenu;
    public ArrayList<JButton> bottoni = new ArrayList<JButton>();
    public JPanel griglia;
    public String tipo;

    public JDialog frame;
    public Azienda azienda;
    public JPanel pannello;

    public GrigliaDP(String tipo, Azienda azienda, JFrame home) {
        this.home = home;
        this.azienda = azienda;
        this.tipo = tipo;
        impostazione();
        listaDaIstanziare(azienda);
        f.setVisible(true);	
        

    }

    public void impostazione() {
        f = new JFrame("Finestra " + tipo);
        	                    //visualizzazione
				//dimensionamento 
		f.setLocation(300,200);	//posizionamento
        f.setBackground(Color.CYAN);
        f.setSize(700, 200);
        
		// Registriamo un ascoltatore per gli eventi
		f.addWindowListener(new GestoreFinestre()); 
    }


    private void creaPulsanti() {
        //Panel
        pannello = new JPanel();
        pannello.setLayout(new BoxLayout(pannello, BoxLayout.X_AXIS));
        pannello.setSize(100, 50);
        f.add(pannello, BorderLayout.PAGE_END);

        //bottone nuovo
        btnNuovo = new JButton();
        
        btnNuovo.setText("Aggiungi " + tipo);
        btnNuovo.addActionListener(new ActionListenerNuovo()); //vedi classe sotto
        pannello.add(btnNuovo);
        
        btnNuovo.setVisible(true);

        //bottoneMenu
        btnMenu = new JButton();
        
        btnMenu.setText("Torna al menu");
        btnMenu.addActionListener(new ActionListenerMenu()); //vedi classe sotto
        pannello.add(btnMenu, BorderLayout.WEST);
       
        btnMenu.setVisible(true);

        //modale
       

    }

    private void listaDaIstanziare(Azienda azienda) {
        System.out.println(tipo);
        ArrayList<Dipendente> dipendenti;
        ArrayList<Progetto> progetti;
        if (tipo == "Dipendenti") {
            dipendenti = azienda.dipendenti;
            creaGriglia(dipendenti);
        }
        else {
            progetti = azienda.progetti;
            creaGriglia(progetti);
        }
    }

    private void creaGriglia(ArrayList<?> lista) {
        
        //lista.forEach(i -> System.out.println(i));
        griglia = new JPanel();
        griglia.setSize(1200, 1200);
        
        if (tipo == "Dipendenti") {
            griglia.setLayout(new GridLayout(lista.size() + 1, 6));

            griglia.add(new JLabel("Nome", SwingConstants.CENTER));
            griglia.add(new JLabel("Cognome", SwingConstants.CENTER));
            griglia.add(new JLabel("Data di nascita", SwingConstants.CENTER));
            griglia.add(new JLabel("Stipendio", SwingConstants.CENTER));
            griglia.add(new JLabel("Lista attività", SwingConstants.CENTER));
            griglia.add(new JLabel("", SwingConstants.CENTER));
        }
        else {
            griglia.setLayout(new GridLayout(lista.size() + 1, 4));

            griglia.add(new JLabel("Nome", SwingConstants.CENTER));
            griglia.add(new JLabel("Responsabile", SwingConstants.CENTER));
            griglia.add(new JLabel("Attività Orarie", SwingConstants.CENTER));
            griglia.add(new JLabel("", SwingConstants.CENTER));
        }

        for (Object i : lista) {
            if (i instanceof Dipendente) {
                
                
                for (int j = 0; j < 6; j++) {
                    Dipendente dip = (Dipendente) i;
                    switch (j) {
                        case 0:
                            System.out.println("ciao");
                            griglia.add(new BottoniGriglia(dip.getNome(), dip, griglia));
                            break;
                        case 1:
                            griglia.add(new BottoniGriglia(dip.getCognome(), dip, griglia));
                            break;
                        case 2:
                            griglia.add(new BottoniGriglia(dip.getDataNascita().toString(), dip, griglia));
                            break;
                        case 3:
                            griglia.add(new BottoniGriglia(dip.getStipendio() + "", dip, griglia));
                            break;
                        case 4:
                            griglia.add(new Combo(dip.getOreProgetti()));
                            break;
                        case 5:
                            griglia.add(new BtnRemove(dip));
                            break;
                        default:
                            break;
                    }
                }

            } 
            else {
                System.out.println(lista.size()+1);


                for (int j = 0; j < 4; j++) {
                    Progetto prgt = (Progetto) i;

                    switch (j) {
                        case 0:
                            griglia.add(new BottoniGriglia(prgt.getNome(), prgt, griglia));
                            break;
                        case 1:
                            griglia.add(new BottoniGriglia(prgt.getResponsabile().toString(), prgt, griglia));
                            break;
                        case 2:
                            griglia.add(new Combo(prgt.getAttivitaConQuestoProgetto())); 
                            break;
                        case 3:
                            griglia.add(new BtnRemove(prgt));
                        default:
                            break;
                    }
                }
            }   
        }
        f.add(griglia, "Center");
        //f.pack();
        creaPulsanti();
        
    }
    


    private void creaModale() {
        frame = new JDialog(f, tipo, true);
        frame.getContentPane().add(new Modale(tipo, azienda, this));
        frame.pack();
        frame.setVisible(true);
    }

    public void aggiungiComponente() {
        f.remove(griglia);
        f.remove(pannello);
        listaDaIstanziare(azienda);
        f.pack();
        frame.dispose();
    }

    public class Combo extends JComboBox<AttivitaOraria> implements ActionListener, ItemListener {
        ArrayList<AttivitaOraria> lista;

        public Combo(ArrayList<AttivitaOraria> lista) {
            this.lista = lista;
            this.setPreferredSize(new Dimension(50, 40));
            this.addActionListener(this);
            this.addItemListener(this);
            this.setVisible(true);

            addIt();
        }
        
        private void addIt() {
            for (AttivitaOraria i : lista) {
                this.addItem(i);   
            }
            
        }
         
        @Override
        public void itemStateChanged(ItemEvent e) {
            // TODO Auto-generated method stub
            
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            
        }
    }

    public class BottoniGriglia extends JButton implements ActionListener {
        String attributo;
        Object oggetto;
        JPanel griglia;

        public BottoniGriglia(String attributo, Object oggetto, JPanel griglia) {
            this.attributo = attributo;
            this.oggetto = oggetto;
            this.griglia = griglia;
            this.setSize(100, 50);
            this.setText(attributo);
            this.setVisible(true);
            this.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
    
        }

    
    
    }

    public class ActionListenerNuovo implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            creaModale();
    
        }
    }
    public class ActionListenerMenu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            f.setVisible(false);
            home.setVisible(true);

    
        }
    }

    public class BtnRemove extends JButton implements ActionListener {
        private Object ogg;


        public BtnRemove(Object ogg) {
            this.ogg = ogg;
            this.setSize(100, 50);
            this.setText("Remove");
            this.setVisible(true);
            this.addActionListener(this);
        } 

        @Override
        public void actionPerformed(ActionEvent e) {
            if (tipo == "Dipendenti") {
                azienda.dipendenti.remove(ogg);
            }
            else azienda.progetti.remove(ogg);
            aggiungiComponente();
        }
    }

    

    

}

