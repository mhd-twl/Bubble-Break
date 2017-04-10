/*package GUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import console.*;

@SuppressWarnings("serial")
public class BubbleGridPanel extends JPanel implements MouseListener,
		ActionListener {

	Options o = new Options();
	Game ga = new Game();
	JButton b[][];

	// ------------------------methodes-------------------------------------------------

public 	BubbleGridPanel() {
		
		this.setVisible(true);
		this.setLayout(new GridLayout(ga.o.numberOfrow,ga.o.numberOfcol));
		
		
		ga.lst = new Node[ga.o.numberOfrow][ga.o.numberOfcol];
		ga.lst2undo = new Node[ga.o.numberOfrow][ga.o.numberOfcol];

		b = new JButton[ga.o.numberOfrow][ga.o.numberOfcol];
		
		
		
		
	}
	public void printThePanelGotValues(){
		ga.virtualPrint();
		printTheButtons();
	}
	public void printTheButtons(){
		
		for (int i = 0; i < ga.o.numberOfrow; i++) {
			for (int j = 0; j < ga.o.numberOfcol; j++) {
				

				b[i][j] = new JButton();

				Color m = ga.o.customerColor(ga.lst[i][j].getNameColor());
				b[i][j].setBackground(m);

				this.add(b[i][j]);
			}
		}

		
	}

	
	// --------------listeners----------------------------------------------------
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

		
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
*/