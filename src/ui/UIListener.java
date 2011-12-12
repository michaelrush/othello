package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UIListener implements ActionListener {
	public enum ListenerType {
		Diff,
		Player,
		Config,
		First,
		Play
	}
	
	private ListenerType ltype;
	
	public UIListener(ListenerType lt) {
		ltype = lt;
	}
	
	public void actionPerformed(ActionEvent e) {
		switch (ltype) {
			case Diff: diffAction(e);
			case Player: playerAction(e);
			case Config: configAction(e);
			case First: firstAction(e);
			case Play: playAction(e);
			default: 
		}
    }
	
	public void diffAction(ActionEvent e) {
		JComboBox cb = (JComboBox)e.getSource();
        String diff = (String)cb.getSelectedItem();
        //updateLabel(diff);
    }
	
	public void playerAction(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String diff = (String)cb.getSelectedItem();
        //updateLabel(diff);
    }
	
	public void configAction(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String diff = (String)cb.getSelectedItem();
        //updateLabel(diff);
    }
	
	public void firstAction(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String diff = (String)cb.getSelectedItem();
        //updateLabel(diff);
    }
	
	public void playAction(ActionEvent e) {
        JButton btn = (JButton)e.getSource();
        //updateLabel(diff);
    }
}

