package AdminPanels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class AdminOptionButton extends JButton implements ActionListener, MouseListener {
	private static final long serialVersionUID = 1L;
	private boolean hovered = false;
	private boolean clicked = false;
	public boolean isSelected = false;

	public Color normalColor = null;
	public Color lightColor = null;
	public Color darkColor = null;

	public int index = 0;
	public String name = null;
	public String Price = null;
	public String Category = null;

	public AdminOptionButton(int index) {
		this.index = index;
		setForeground(new Color(47, 46, 48));
		setBackground(Color.GRAY);
		Color normalRedColor = Color.GRAY;

		this.normalColor = normalRedColor;
		addActionListener(this);
		addMouseListener(this);
		setContentAreaFilled(false);
	}
	
	public void setCategory(String Cat) {
		this.Category = Cat;
	}
	
	public String getCategory() {
		return Category;
	}

	public void setPrice(String Price) {
		this.Price = Price;
	}

	public String getPrice() {
		return Price;
	}

	public void setButtonText(String n) {
		name = n;
		this.setText(n);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// Draws the rounded opaque panel with borders
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // For High quality
		if (isSelected) {
			g2d.setColor(Color.BLUE);
		} else {
			if (!hovered) {
				g2d.setColor(Color.WHITE);
				setForeground(new Color(47, 46, 48));
			} else {
				g2d.setColor(Color.GRAY);
				setForeground(Color.white);
				if (clicked) {
					g2d.setColor(new Color(118, 117, 119));
				} else {
					g2d.setColor(Color.GRAY);
				}
			}
		}
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 7, 7);

		g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 7, 7);

		setFocusPainted(false);

		super.paintComponent(g);
	}

	public void setIndex(int i) {
		index = i;
	}

	public int getIndex() {
		return index;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		isSelected = !isSelected;
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		hovered = true;
		clicked = false;

		repaint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		hovered = false;
		clicked = false;

		repaint();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		hovered = true;
		clicked = true;

		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		hovered = true;
		clicked = false;

		repaint();
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public boolean isSelected() {
		return isSelected;
	}

}