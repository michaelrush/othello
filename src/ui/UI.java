package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;

import common.*;

public class UI extends JFrame {

  public UI() {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(600, 600);

    JPanel panel = new JPanel();
    JPanel body = new JPanel();
    JPanel score = new JPanel();
    JPanel footer = new JPanel();
    
    
    getContentPane().add(panel);
    panel.add(body);
    panel.add(score);
    panel.add(footer);

    body.add(new gameBoardUI());

    score.add(new JLabel("Player 1"));
    score.add(new JTextField(10));
    score.add(new JLabel("Player 2"));
    score.add(new JTextField(10));
    
    String[] diffStrings = { "Easy", "Normal", "Hard" };
    JComboBox diffList = new JComboBox(diffStrings);
    diffList.setSelectedIndex(1);
    diffList.addActionListener(new UIListener(Util.ListenerType.Diff));
    footer.add(diffList);

    String[] playerStrings = { "PC vs CPU", "PC vs PC", "CPU vs CPU" };
    JComboBox playerList = new JComboBox(playerStrings);
    playerList.addActionListener(new UIListener(Util.ListenerType.Player));
    footer.add(playerList);

    String[] configStrings = { "BW/WB", "WB/BW" };
    JComboBox configList = new JComboBox(configStrings);
    configList.addActionListener(new UIListener(Util.ListenerType.Config));
    footer.add(configList);

    String[] firstStrings = { "White first", "Black first" };
    JComboBox firstList = new JComboBox(firstStrings);
    firstList.addActionListener(new UIListener(Util.ListenerType.First));
    footer.add(firstList);

    JButton play = new JButton("Play");
    play.addActionListener(new PlayUIListener(diffList, playerList, configList, firstList));
    footer.add(play);

    repaint();
  }
}

class gameBoardUI extends JPanel {
  public gameBoardUI() {
    setBorder(BorderFactory.createLineBorder(Color.black));
    setBackground(Color.black);
  }
  
  public Dimension getPreferredSize() {
    return new Dimension(400,400);
}
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);     

    g.drawString("This is my custom Panel!",10,20);
    g.setColor(Color.green);
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        g.fillRect(i*50, j*50, 49, 49);
      }
    }  
  }
}