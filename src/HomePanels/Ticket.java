package HomePanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Commands.Commands;
import Main.MenuItem;
import Vars.Vars;

public class Ticket {
	public static JPanel panel = new JPanel();
	public static JPanel itemsPanel = new JPanel();
	public static String ticket;
	public static JTextField totalPrice;
	public static JScrollPane scrollPane;
	public static ArrayList<MenuItem> ticketItems = new ArrayList<MenuItem>();
	public static int buttonSize = 50;

	public static void createView(Graphics g) {
		panel.setLayout(null);
		panel.setBackground(new Color(200, 200, 200));
		panel.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.BLACK));
		totalPrice = new JTextField("Total: $0.00");
		totalPrice.setBounds(5, Vars.dimensionFullScreen.height - 100, (Vars.dimensionFullScreen.width / 3) - 10, 95);
		totalPrice.setEditable(false);
		totalPrice.setFont(new Font("Lucida Console", Font.PLAIN, 25));
		totalPrice.setBackground(Color.white);

		itemsPanel.setLayout(null);
		itemsPanel.setBackground(new Color(200, 200, 200));
		
		scrollPane = new JScrollPane(itemsPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(5, 0, (Vars.dimensionFullScreen.width / 3) - 10, Vars.dimensionFullScreen.height - 100);
		itemsPanel.setBounds(10, 5, (Vars.dimensionFullScreen.width / 3) - 5, Vars.dimensionFullScreen.height - 5);
		panel.setBounds((Vars.dimensionFullScreen.width * 2 / 3), 0, (Vars.dimensionFullScreen.width / 3),Vars.dimensionFullScreen.height);
		refresh();
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		panel.add(scrollPane);
		panel.add(totalPrice);
	}
	
	public static void removeSelectedItem() {
		for(int x = 0; x < ticketItems.size(); x++) {
			if(ticketItems.get(x).isSelected()) {
				Commands.removeFromTicket(x);
				removeSelectedItem();
			}
		}
	}
	
	public static double getPrice() {
		double temp = 0.0;
		for(int x = 0; x < ticketItems.size(); x++) {
			temp+=ticketItems.get(x).getPrice();
		}
		return temp;
	}
	
	public static void refresh() {
		itemsPanel.removeAll();
		for(int x = 0; x < ticketItems.size(); x++) {
			ticketItems.get(x).setFont(new Font("Lucida Console", Font.PLAIN, 15));
			ticketItems.get(x).setBackground(new Color(200, 200, 200));
			ticketItems.get(x).setBounds(5, buttonSize*x, (Vars.dimensionFullScreen.width / 3) - 45, buttonSize);
			ticketItems.get(x).setIndex(x);
			itemsPanel.add(ticketItems.get(x));
		}
		itemsPanel.setPreferredSize(new Dimension((Vars.dimensionFullScreen.width / 3) - 5, (ticketItems.size()*buttonSize)));
		String price = Double.toString(getPrice());
		if (price.endsWith(".0")) {
			price += "0";
		}
		if (price.charAt(price.length() - 3) != '.') {
			for (int i = 0; price.charAt(price.length() - 3) != '.'; i++) {
				Commands.removeLastChar(price);
			}
		}
		totalPrice.setText("Total: $"+ price);
	}
}
