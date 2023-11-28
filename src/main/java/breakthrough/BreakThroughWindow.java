/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package breakthrough;

/**
 *
 * @author ThetNaingSoe
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BreakThroughWindow extends BaseWindow{
    private final int size;
    private final Game game;
    private final JLabel label;
    private final JLabel red_label;
    private final JLabel blue_label;
    private final MainWindow mainWindow;
    private final JButton[][] cells;
    
    public BreakThroughWindow(int size, MainWindow mainWindow){
        cells = new JButton[size][size];
        this.size = size;
        this.mainWindow = mainWindow;
        game = new Game(size);
        
        JPanel top = new JPanel();
        
        label = new JLabel();
        red_label = new JLabel();
        blue_label = new JLabel();
        updateLabelText();
        
        JButton newGameButton = new JButton();
        newGameButton.setText("New game");
        newGameButton.addActionListener(e -> newGame());
        
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        
        top.add(label);
        top.add(red_label);
        top.add(blue_label);
        top.add(newGameButton);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(size, size));
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (game.getPlayer(i, j) == Player.X) {
                    cells[i][j] = new JButton("X");
                } else if (game.getPlayer(i, j) == Player.O) {
                    cells[i][j] = new JButton("O");
                }else {
                    cells[i][j] = new JButton();
                }
                
                mainPanel.add(cells[i][j]);
            }
        }
        
        addButton(cells);
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        
        
    }
    
    private void addButton(JButton[][] cell){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int I = i;
                int J = j;
                cell[i][j].addActionListener(e -> {
                    if (game.getPlayer(I, J) == Player.X || game.getPlayer(I, J) == Player.O) {
                        game.step(I, J);
                        System.out.println("Step");
                    }else {
                        game.move(I, J);
                        System.out.println("Move");
                    }
                    for (int k = 0; k < size; k++) {
                        for (int l = 0; l < size; l++) {
                            cells[k][l].setBackground(null);
                            if (game.getPlayer(k, l) == Player.X) {
                                cells[k][l].setText("X");
                            }else if (game.getPlayer(k, l) == Player.O) {
                                cells[k][l].setText("O");
                            }else if (game.getPlayer(k, l) == Player.S) {
                                cells[k][l].setBackground(Color.GRAY);
                            }else if (game.getPlayer(k, l) == Player.SX) {
                                cells[k][l].setText("X");
                                cells[k][l].setBackground(Color.GRAY);
                            }else if (game.getPlayer(k, l) == Player.SO) {
                                cells[k][l].setText("O");
                                cells[k][l].setBackground(Color.GRAY);
                            }else {
                                cells[k][l].setText("");
                            }
                        }
                    }
                    updateLabelText();

                    Player winner = game.findWinner();
                    if (winner != Player.NOBODY) {
                        showGameOverMessage(winner);
                    }
                });
            }
        }
    }
    
    
    private void showGameOverMessage(Player winner){
        JOptionPane.showMessageDialog(this, "Game is over. Winner: " + winner.name());
        newGame();
    }
    
    private void newGame(){
        BreakThroughWindow newWindow = new BreakThroughWindow(size, mainWindow);
        newWindow.setVisible(true);
        
        this.dispose();
        mainWindow.getWindowList().remove(this);
    }
    
    private void updateLabelText(){
        label.setText("Current player: " + game.getActualPlayer().name());
        
    }
    
    @Override
    protected void doUponExit(){
        super.doUponExit();
        mainWindow.getWindowList().remove(this);
    }
    
}
