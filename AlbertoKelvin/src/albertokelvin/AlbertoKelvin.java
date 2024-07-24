/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package albertokelvin;

import javax.swing.SwingUtilities;

/**
 *
 * @author ASUS
 */
public class AlbertoKelvin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                KursRupiahGUI gui = new KursRupiahGUI();
                gui.setVisible(true);
                gui.setLocationRelativeTo(null);
            }
        });
    }
    
}
