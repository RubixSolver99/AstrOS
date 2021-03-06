package HomePanels;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Commands.Commands;
import HomePanels.OptionsPanels.OptionPanel;
import Main.MenuItemButton;
import Vars.LocalSQL;
import Vars.Vars;

public class Subs {
	public static JPanel panel = new JPanel();
	public static ArrayList<ArrayList<String>> subs;
	public static ArrayList<MenuItemButton> subButtons = new ArrayList<MenuItemButton>();

	public static void createView(Graphics g) {
		panel.setLayout(null);
		panel.setBackground(Vars.colorMainBG);

		subs = LocalSQL.Subs;
		
		JLabel label = new JLabel("Subs");
		label.setBounds(5, 20, 200, 80);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(Vars.fontDefault);
		panel.add(label);

		setSubsFromSQL();

		panel.setBounds(0, 80, (Vars.dimensionFullScreen.width * 2 / 3), Vars.dimensionFullScreen.height);
	}
	
	public static void setSubsFromSQL() {
		for (int i = 0; i < subs.size(); i++) {
			if (i < 4) {
				subButtons.add(Commands.createMenuItemButton(subs.get(i).get(0), ((i * 210) + 5), 100,Double.parseDouble(subs.get(i).get(1))));
			} else if (i < 8) {				
				subButtons.add(Commands.createMenuItemButton(subs.get(i).get(0), (((i - 4) * 210) + 5), 210,Double.parseDouble(subs.get(i).get(1))));
			} else {
				subButtons.add(Commands.createMenuItemButton(subs.get(i).get(0), (((i - 8) * 210) + 5), 320,Double.parseDouble(subs.get(i).get(1))));
			}
		}

		for (int i = 0; i < subButtons.size(); i++) {
			String nameOfItem = subButtons.get(i).getName();
			double priceOfItem = subButtons.get(i).getPrice();
			subButtons.get(i).addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					 Options.assignPanel(nameOfItem);
					 Commands.addToTicket(Commands.ticketSpacing(nameOfItem, priceOfItem),priceOfItem);
					 OptionPanel.updatePanel();
				}});
			panel.add(subButtons.get(i));
		}
	}
}
