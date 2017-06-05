/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.linkmanager;
import java.awt.Desktop;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
/**
 *
 * @author Cukier
 */
public class Test {
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LinkManagerUI f = new LinkManagerUI();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setVisible(true);

        }
    });
                }
}
