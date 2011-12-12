package control;

import java.util.Scanner;

import board.BoardState;
import player.*;
import ui.*;

import java.awt.*;
import javax.swing.*;
import java.applet.*;
/*
 * Initializes game settings, including game modes and players
 */
public class Runner {
	public static void main(String[] args) {
		/*
	        public void run() {
	            try {
	                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	            } catch (ClassNotFoundException e) {
	            } catch (InstantiationException e) {
	            } catch (IllegalAccessException e) {
	            } catch (UnsupportedLookAndFeelException e) {
	            }
	 
	            new UI().setVisible(true);
	        }
	    }); */
		
		//Randomly decide who goes first
		IPlayer p1;
		IPlayer p2;
		char c1 = 'B';
		char c2 = 'W';
		
		//Select a game mode
		System.out.println("Let's play Othello!");
		System.out.println("Please select a game mode: ");
		System.out.println("\t(1) PC vs CPU: ");
		System.out.println("\t(2) PC vs PC: ");
		System.out.println("\t(3) CPU vs CPU: ");
		System.out.print("Input: ");
		Scanner sc = new Scanner(System.in);
		int in = sc.nextInt();
		if(in == 1) {
			System.out.println("Who is first?");
			System.out.println("\t(1) Black: ");
			System.out.println("\t(2) White: ");
			System.out.print("Input: ");
			in = sc.nextInt();
			if(in == 1) {
				c1 = 'B';
				c2 = 'W';
			} else {
				c1 = 'W';
				c2 = 'B';
			}
			System.out.println("Please select who is first: ");
			System.out.println("\t(1) PC first: ");
			System.out.println("\t(2) CPU first: ");
			System.out.print("Input: ");
			in = sc.nextInt();
			if(in == 1) {
				p1 = new PlayerPC(c1);
				p2 = new PlayerCPU(c2);
			} else {
				p1 = new PlayerCPU(c1);
				p2 = new PlayerPC(c2);
			}
		} else if(in == 2) {
			p1 = new PlayerPC(c1);
			p2 = new PlayerPC(c2);
		} else {
			p1 = new PlayerCPU(c1);
			p2 = new PlayerCPU(c2);
		}
		
		//Run the game
		Game g1 = new Game();
		BoardState b1 = new BoardState();
		g1.playGame(p1, p2, b1);
		
	}
}
