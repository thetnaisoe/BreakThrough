/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package breakthrough;

/**
 *
 * @author ThetNaingSoe
 */

import javax.swing.JFrame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import java.net.URL;
import java.awt.Toolkit;

public class BaseWindow extends JFrame{
    
    public BaseWindow(){
        setTitle("BreakThrough Board Game");
        setSize(1000,700);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        addWindowListener(new WindowAdapter(){
            
            @Override
            public void windowClosing(WindowEvent e){
                showExitConfirmation();
            }
        });

    }
        
    private void showExitConfirmation(){
        int n = JOptionPane.showConfirmDialog(this, "Do you really want to quit?", "Really", JOptionPane.YES_NO_OPTION);
        if(n == JOptionPane.YES_OPTION){
            doUponExit();
        }
    }
        
    protected void doUponExit(){
        this.dispose();
    }
}
    


