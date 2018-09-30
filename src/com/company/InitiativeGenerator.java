package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class InitiativeGenerator extends JFrame {


    // this Frame
    private final int FRAME_WIDTH = 600;
    private final int FRAME_HEIGHT = 800;

    private Container cp = getContentPane();

    private GridBagConstraints frConst = new GridBagConstraints();  // Frame's


    // Panels
    private JPanel pnUp = new JPanel();
    private JPanel pnMid = new JPanel();
    private JPanel pnDown = new JPanel();

    // Buttons
    private JButton btnAddPnIn = new JButton();
    private JButton btnDelPnIn = new JButton();
    private JButton btnGen = new JButton();


    // Sorter
    private InitiativeSorter sorter = new InitiativeSorter();


    // Input ArrayList
    private ArrayList<InputPanel> arInput = new ArrayList<>();
    
    // Output ArrayList
    private ArrayList<CreaturePanel> arOutput = new ArrayList<>();



    
    // Upper Panel Setup
    private void setupPnUp(){

        // adds Panel Itself
        pnUp.setBackground(new Color(20,140,140));

        Dimension dimPnUp = new Dimension(FRAME_WIDTH*10,FRAME_HEIGHT*4/10);
        pnUp.setMinimumSize(dimPnUp);
        pnUp.setPreferredSize(dimPnUp);
        pnUp.setMaximumSize(dimPnUp);

        cp.add(pnUp);

        // sets up Panel's Layout
        // * default FlowLayout

        // adds its Components
        arInput.add(new InputPanel());                      // adds anon. InputPanel to arInput
        pnUp.add(arInput.get(arInput.size()-1));            // adds it to the pnUp

    }

    // Middle Panel Setup
    private void setupPnMid(){

        // adds Panel Itself
        pnMid.setBackground(new Color(20,70,70));

        Dimension dimPnMid = new Dimension(FRAME_WIDTH*10,FRAME_HEIGHT*1/20);
        pnMid.setMinimumSize(dimPnMid);
        pnMid.setPreferredSize(dimPnMid);
        pnMid.setMaximumSize(dimPnMid);

        cp.add(pnMid);

        // sets up Panel's Layout
        // * default FlowLayout

        // adds its Components

        // 1. Add CreaturePanel Button
        pnMid.add(btnAddPnIn);
        btnAddPnIn.setText("Add a Creature");
        btnAddPnIn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (arInput.size() < 24) {                              // limits to 8 CreaturePanels (because of gui size)
                    arInput.add(new InputPanel());                      // adds anon. InputPanel to arInput
                    pnUp.add(arInput.get(arInput.size() - 1));          // adds it to the pnUp
                }

                if (arInput.size() > 1){
                    btnDelPnIn.setEnabled(true);                        // sets Delete a Creature button active if 2 or more creatures
                }


                if (arInput.size() == 24){
                    btnAddPnIn.setEnabled(false);                       // disables button if creature limit reached
                }

                setVisible(true);                                       // resets Frame.setVisible
            }
        });

        // 2. Remove CreaturePanel Button
        pnMid.add(btnDelPnIn);
        btnDelPnIn.setText("Remove a Creature");
        btnDelPnIn.setEnabled(false);                                   // starts disabled
        btnDelPnIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (arInput.size() > 1) {
                    pnUp.remove(arInput.get(arInput.size() - 1));
                    arInput.remove(arInput.get(arInput.size() - 1));
                }

                if (arInput.size() == 1){
                    btnDelPnIn.setEnabled(false);                       // disables button if only 1 creature left
                }

                if (arInput.size() < 24){                               // enables Add a creature button when less than 8 creatures
                    btnAddPnIn.setEnabled(true);
                }

                setResizable(true);                                     // todo when removing set resizable is needed?

            }
        });

        // 3. Roll Initiative Button
        pnMid.add(btnGen);
        btnGen.setText("Roll Initiative!");
        btnGen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Clears Bottom Panel
                pnDown.removeAll();
                arOutput.clear();
                setResizable(true);


                // Adds InputPanels to their ArrayList
                for (InputPanel in : arInput
                     ) {
                    if (in.usePreRoll()){
                        arOutput.add(new CreaturePanel(in.getName(),in.getColor(),in.getPreRoll()));
                    } else {
                        arOutput.add(new CreaturePanel(in.getName(), in.getInitBonus(),in.getColor()));
                    }
                }


                // sorts ArrayList
                arOutput = sorter.sortByInitScore(arOutput);


                // Displays the sorted arOutput
                for  (int i = 0; i < arOutput.size(); i++){
                    pnDown.add(arOutput.get(i));
                    setVisible(true);
                }

            } // actonPerformed e
        }); // ActionListener
    } // setupMidPn

    // Lower Panel Setup
    private void setupPnDown(){

        // adds Panel
        pnDown.setBackground(new Color(20,100,100));

        Dimension dimPnMid = new Dimension(FRAME_WIDTH*10,FRAME_HEIGHT*11/20);
        pnDown.setMinimumSize(dimPnMid);
        pnDown.setPreferredSize(dimPnMid);
        pnDown.setMaximumSize(dimPnMid);

        cp.add(pnDown);

        // sets up Panel's Layout
        // * default FlowLayout

    }


    /*
     * CONSTRUCTOR
     */

    public InitiativeGenerator(){

        // this Frame
        setTitle("Initiative Generator");
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        cp.setLayout(new BoxLayout(cp,BoxLayout.PAGE_AXIS));


        // stops program on window close
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panels

        setupPnUp();

        setupPnMid();

        setupPnDown();


        // this Frame (again)
        setVisible(true);
        setResizable(true);



    }
}
