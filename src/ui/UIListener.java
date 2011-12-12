package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import control.Runner;
import common.*;

public class UIListener implements ActionListener {
  private Util.ListenerType ltype;

  public UIListener(Util.ListenerType lt) {
    ltype = lt;
  }

  public void actionPerformed(ActionEvent e) {
    switch (ltype) {
    case Diff:
      diffAction(e);
      break;
    case Player:
      playerAction(e);
      break;
    case Config:
      configAction(e);
      break;
    case First:
      firstAction(e);
      break;
    default:
    }
  }

  public void diffAction(ActionEvent e) {
    JComboBox cb = (JComboBox) e.getSource();
    String diff = (String) cb.getSelectedItem();
    // updateLabel(diff);
  }

  public void playerAction(ActionEvent e) {
    JComboBox cb = (JComboBox) e.getSource();
    String diff = (String) cb.getSelectedItem();
    // updateLabel(diff);
  }

  public void configAction(ActionEvent e) {
    JComboBox cb = (JComboBox) e.getSource();
    String diff = (String) cb.getSelectedItem();
    // updateLabel(diff);
  }

  public void firstAction(ActionEvent e) {
    JComboBox cb = (JComboBox) e.getSource();
    String diff = (String) cb.getSelectedItem();
    // updateLabel(diff);
  }
}

class PlayUIListener extends UIListener {
  private JComboBox _diff;
  private JComboBox _player;
  private JComboBox _config;
  private JComboBox _first;
  
  public PlayUIListener(JComboBox diff, JComboBox player, JComboBox config, JComboBox first) {
    super(Util.ListenerType.Play);
    _diff = diff;
    _player = player;
    _config = config;
    _first = first;
  }
  
  public void actionPerformed(ActionEvent e) {
    Runner.run((String) _diff.getSelectedItem(), (String) _player.getSelectedItem(), (String) _config.getSelectedItem(), (String) _first.getSelectedItem());
  }
}
