/*package GUI;

import console.*;
import GUI.BubblesGUI;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings({ "serial", "unused" })
public class OptionsForm extends JFrame implements ActionListener {

	Options o = new Options();
	BubblesGUI BG = new BubblesGUI();

	JButton ESY = new JButton("Easy Playing");
	JButton MID = new JButton("Medium Playing");
	JButton HRD = new JButton("Hard Playing");

	public OptionsForm() {
		play();

	}

	void play() {

		ESY.setForeground(Color.GREEN.darker());
		MID.setForeground(Color.ORANGE);
		HRD.setForeground(Color.RED.darker());

		setLayout(new FlowLayout());

		this.add(ESY);
		this.add(MID);
		this.add(HRD);
		// -----------------Setting--------------------------
		setVisible(true);
		setLocationRelativeTo(null);
		validate();
		setTitle("Selection Optins ");
		setSize(300, 110);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ------------------ActionListener-----------------------------------

		ESY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				o.selectionLevel(1);
				BubblesGUI bf = new BubblesGUI();
				bf.BubblePlay();
			}
		});
		MID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				o.selectionLevel(2);
				BubblesGUI bf = new BubblesGUI();
				bf.BubblePlay();
			}
		});
		HRD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				o.selectionLevel(3);
				BubblesGUI bf = new BubblesGUI();
				bf.BubblePlay();
			}
		});

	}


		

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}*/