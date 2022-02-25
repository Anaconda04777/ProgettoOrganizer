import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GestoreFinestre implements WindowListener {
    // 
        public void windowIconified(WindowEvent e){	}
        public void windowDeiconified(WindowEvent e){ }
        public void windowActivated(WindowEvent e){	}	
        public void windowDeactivated(WindowEvent e){ }
        public void windowOpened(WindowEvent e){ }
        public void windowClosed(WindowEvent e){ }
        /**
         * La chiusura della finestra determina il termine dell'esecuzione del programma
         * @param e non utilizzato
         */
        public void windowClosing(WindowEvent e){
            System.exit(0);
        }				
    }
