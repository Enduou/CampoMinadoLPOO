package mainPacote;

import javax.swing.SwingUtilities;

import guiPacote.Menu;

public class Main {

    public static void main(String[] args) {
        
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Menu();
            }
        });
    }
}
