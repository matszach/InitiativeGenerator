package com.company;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CreaturePanel extends JPanel {

    private Random rand = new Random();

    private String name;         // CreaturePanel's name
    private int initBonus;       // CreaturePanel's initiative bonus
    private Color color;         // CreaturePanel's (JPanel) color

    private int initScore;       // used to sort Creatures

    private JLabel lbCreature = new JLabel();           // displays Name
    private JTextField tfCreature = new JTextField();   // displays Initiative Score
    private JButton btnDelCreature = new JButton();     // deletes Panel when pressed (after the CreaturePanel died / left combat);


    // 1. sets initScore if no pre-roll given
    private void setInitScore(){
        initScore = initBonus + rand.nextInt(20)+1;
    }

    // 2. sets initScore if pre-roll was given
    private void setInitScore(int preRoll){
        initScore = preRoll;
    }


    // gets initScore ( for sorting )
    public int getInitScore(){
        return initScore;
    }

    // gets initBonus ( for sorting )
    public int getInitBonus(){
        return initBonus;
    }


    // *** sets component "final" size ***
    private void setCompSize (Component comp, Dimension dim){
        comp.setMinimumSize(dim);
        comp.setPreferredSize(dim);
        comp.setMaximumSize(dim);
    }


    // Prepares JFrame to be put in the tracker
    private void setupPanel(){

        // Panel itself
        setBackground(color);
        Dimension dim = new Dimension(350,34);
        setPreferredSize(dim);
        //setMaximumSize(dim);      // not necessary
        //setMinimumSize(dim);      // not necessary
        setVisible(true);


        // adds Label
        lbCreature.setText(name + "'s initiative score:");
        add(lbCreature);
        setCompSize(lbCreature, new Dimension(170,20));


        // adds TextField
        tfCreature.setText(initScore + "");
        tfCreature.setEditable(false);
        tfCreature.setBackground(getHue(color,-50));
        add(tfCreature);
        setCompSize(tfCreature, new Dimension(30,20));

        // adds filler box component
        add(new Box.Filler(new Dimension(20,0),new Dimension(20,0),new Dimension(20,0))); // TODO cleaner way?


        // adds Button
        btnDelCreature.setText("Delete");
        btnDelCreature.setBackground(getHue(color,-50));
        btnDelCreature.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setVisible(false);

            }
        });
        add(btnDelCreature);
        setCompSize(btnDelCreature,new Dimension(80,20));

    }

    // get TextField and Button color
    private Color getHue (Color color, int hueDiff){

        int r = color.getRed() + hueDiff;
        if (r < 0){
            r = 0;
        } else if (r > 255){
            r = 255;
        }

        int g = color.getGreen() + hueDiff;
        if (g < 0){
            g = 0;
        } else if (g > 255){
            g = 255;
        }


        int b = color.getBlue() + hueDiff;
        if (b < 0){
            b = 0;
        } else if (b > 255){
            b = 255;
        }

        return new Color(r,g,b);
    }


    /*
     * Constructor 1 , no pre-roll
     */

    public CreaturePanel(String name, int initBonus, Color color){

        // copies stats
        this.name = name;
        this.initBonus = initBonus;
        this.color = color;

        // sets init score
        setInitScore();

        // set up the Panel
        setupPanel();

    }


    /*
     * Constructor 2 , with pre-roll
     */

    public CreaturePanel(String name, Color color, int preRoll){

        // copies stats
        this.name = name;
        this.color = color;
        this.initBonus = 0; // still used for sorting

        // sets init score
        setInitScore(preRoll);

        // set up the Panel
        setupPanel();

    }

}
