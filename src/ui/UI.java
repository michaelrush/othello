package ui;

import javax.swing.*;

public class UI extends JFrame  {

	public UI()  {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 265);
		 
		JPanel body = new JPanel();
		getContentPane().add(body);

		String[] diffStrings = { "Easy", "Normal", "Hard" };
		JComboBox diffList = new JComboBox(diffStrings);
		diffList.setSelectedIndex(1);
		diffList.addActionListener(new UIListener(UIListener.ListenerType.Diff));
		body.add(diffList);
		
		String[] playerStrings = { "PC vs CPU", "PC vs PC", "CPU vs CPU" };
		JComboBox playerList = new JComboBox(playerStrings);
		playerList.addActionListener(new UIListener(UIListener.ListenerType.Player));
		body.add(playerList);
		
		String[] configStrings = { "BW/WB", "WB/BW"};
		JComboBox configList = new JComboBox(configStrings);
		configList.addActionListener(new UIListener(UIListener.ListenerType.Config));
		body.add(configList);
		
		String[] firstStrings = { "White first", "Black first"};
		JComboBox firstList = new JComboBox(firstStrings);
		firstList.addActionListener(new UIListener(UIListener.ListenerType.First));
		body.add(firstList);
		
		JButton play =  new JButton("Play");
		play.addActionListener(new UIListener(UIListener.ListenerType.Play));
		body.add(play);
	}
}