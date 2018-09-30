package com.company;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class InputPanel extends JPanel {

    // Panel Color
    private Color panelCrl = new Color(30,120,120);

    // Labels
    private JLabel lbName = new JLabel();
    private JLabel lbInitBon = new JLabel();
    private JLabel lbPreroll = new JLabel();

    // TextFields
    private JTextField tfName = new JTextField();
    private JTextField tfInitBon = new JTextField();
    private JTextField tfPreroll = new JTextField();

    // ComboBox
    private JComboBox cbColor = new JComboBox();

    // CheckBox
    private JCheckBox chPreroll = new JCheckBox();


    // *** sets component "final" size ***
    private void setCompSize (Component comp, Dimension dim){
        comp.setMinimumSize(dim);
        comp.setPreferredSize(dim);
        comp.setMaximumSize(dim);
    }

    // *** methods for reading input panel
    // // gets name
    public String getName(){

        String name = tfName.getText();

        if (name.equals("")){
            return "[no name]";
        }

        return name;
        // TODO errors if wrong input
    }

    // // gets init bonus
    public int getInitBonus(){

        String ib = tfInitBon.getText();

        if (ib.equals("")){
            return 0;
        }

        return Integer.parseInt(ib);
        // TODO errors if wrong input
    }

    // // gets color
    public Color getColor(){

        String selColorName = (String)cbColor.getSelectedItem();

        if (selColorName.equals("WHITE") || selColorName.equals(" ")){
            return Color.WHITE;                                         // redundant
        } else if (selColorName.equals("RED")){
            return Color.RED;
        } else if (selColorName.equals("BLUE")){
            return Color.BLUE;
        } else if (selColorName.equals("GREEN")){
            return Color.GREEN;
        } else if (selColorName.equals("YELLOW")){
            return Color.YELLOW;
        } else if (selColorName.equals("BLACK")){
            return Color.BLACK;
        }

        return Color.WHITE; // default
        // TODO errors if wrong input also colored comboBox pls

    }

    // // gets preRoll
    public int getPreRoll(){

        String pr = tfPreroll.getText();

        if (pr.equals("")){
            return 10;
        }

        return Integer.parseInt(pr);
        // TODO errors if wrong input
    }

    // // gets preRoll checkbox state
    public boolean usePreRoll(){

        if (chPreroll.isSelected()){
            return true;
        } else {
            return false;
        }

    }

    /*
     * Constructor
     */

    public InputPanel(){

        // JPanel
        setBackground(panelCrl);
        Dimension dim = new Dimension(500,34);
        setPreferredSize(dim);
        setVisible(true);

        // adds Components

        // // Name
        lbName.setText("Name:");
        add(lbName);
        setCompSize(lbName,new Dimension(40,20));

        tfName.setEditable(true);
        add(tfName);
        setCompSize(tfName,new Dimension(80,20));

        // adds filler box component
        add(new Box.Filler(new Dimension(10,0),new Dimension(10,0),new Dimension(10,0)));

        // // Initiative Bonus
        lbInitBon.setText("Init. Bonus:");
        add(lbInitBon);
        setCompSize(lbInitBon, new Dimension(70,20));

        tfInitBon.setEditable(true);
        add(tfInitBon);
        setCompSize(tfInitBon, new Dimension(25,20));

        // adds filler box component
        add(new Box.Filler(new Dimension(10,0),new Dimension(10,0),new Dimension(10,0)));

        // // Color
        String [] colors = {" ", "WHITE" ,"RED", "GREEN", "BLUE", "YELLOW", "BLACK"};
        cbColor = new JComboBox(colors);
        cbColor.setEditable(false);
        add(cbColor);
        setCompSize(cbColor, new Dimension(75,20));

        // adds filler box component
        add(new Box.Filler(new Dimension(10,0),new Dimension(10,0),new Dimension(10,0)));

        // // Pre-roll
        chPreroll.setBackground(panelCrl);                                 // sets checkbox's background
        chPreroll.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (tfPreroll.isEditable()){                               // "isEditable" boolean
                    tfPreroll.setEditable(false);
                    tfPreroll.setBackground(Color.LIGHT_GRAY);
                    tfInitBon.setEditable(true);
                    tfInitBon.setBackground(Color.WHITE);
                } else if (!tfPreroll.isEditable()){
                    tfPreroll.setEditable(true);
                    tfPreroll.setBackground(Color.WHITE);
                    tfInitBon.setEditable(false);
                    tfInitBon.setBackground(Color.LIGHT_GRAY);
                }
            }
        });
        add(chPreroll);
        setCompSize(chPreroll, new Dimension(20,20));

        lbPreroll.setText("Pre-roll:");
        add(lbPreroll);
        setCompSize(lbPreroll,new Dimension(50,20));

        tfPreroll.setEditable(false);                       // default
        tfPreroll.setBackground(Color.LIGHT_GRAY);          // default
        add(tfPreroll);
        setCompSize(tfPreroll, new Dimension(30,20));

    }
}
