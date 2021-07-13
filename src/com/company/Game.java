package com.company;

import java.awt.event.*;
import javax.swing.*;


public class Game implements ActionListener{

    protected static final JFrame f = new JFrame();
    protected static Tower t;
    private static final JMenuBar menu_bar = new JMenuBar();

    private final JMenuItem new_game = new JMenuItem("Select Disks");
    private final JMenuItem exit = new JMenuItem("Exit");


    private final JMenu game = new JMenu("New Game");

    public Game(String title) {
        f.setTitle(title);
        build();
    }

    public void actionPerformed(ActionEvent ev){
        if (ev.getSource() == new_game) {
            Object[] values = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16 };
            Object val = JOptionPane.showInputDialog(f,"Number of Disks : ","Input",
                    JOptionPane.INFORMATION_MESSAGE,null, values, values[0]);

            if((int) val!= JOptionPane.CANCEL_OPTION) {
                t.init((int) val);
            }
        }
    }

    private void build(){
        game.add(new_game);
        game.add(exit);

        menu_bar.removeAll();
        menu_bar.add(game);

        new_game.addActionListener(this);
        exit.addActionListener(this);

        f.setJMenuBar(menu_bar);
        f.setSize(660, 280);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
