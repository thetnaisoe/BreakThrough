/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package breakthrough;

/**
 *
 * @author ThetNaingSoe
 */
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Dimension;

public class MainWindow extends BaseWindow{
    
    private List<BreakThroughWindow> gameWindows = new ArrayList<>();
    
    public MainWindow() {
        
        
        JButton small = new JButton();
        small.setText("6 x 6");
        small.addActionListener(getActionListener(6));

        JButton medium = new JButton();
        medium.setText("8 x 8");        
        medium.addActionListener(getActionListener(8));

        JButton large = new JButton();
        large.setText("10 x 10");        
        large.addActionListener(getActionListener(10));
        
        
        getContentPane().setLayout(
        new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(small);
        getContentPane().add(medium);
        getContentPane().add(large);
        
    }
    
    private ActionListener getActionListener(final int size){
        return new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
            BreakThroughWindow window = new BreakThroughWindow(size, MainWindow.this);
            window.setVisible(true);
            gameWindows.add(window);
        }
    };
    }

    
    public List<BreakThroughWindow> getWindowList(){
        return gameWindows;
    }
    
    @Override
    protected void doUponExit(){
        System.exit(0);
    }
}
