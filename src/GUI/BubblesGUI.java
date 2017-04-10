package GUI;

import console.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import java.io.File;
import java.util.logging.Level;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.org.apache.xerces.internal.impl.RevalidationHandler;

@SuppressWarnings({ "unused", "serial" })
public class BubblesGUI extends JFrame {
	// -----------------------------------------
	public static int myLevel;

	JMenuBar menub;
	JMenu menu, menu2;
	JMenuItem itemSave, itemLoad, itemExit, itemTopScore, itemAbout;

	// Options o = new Options();
	Game ga = new Game();
	Game loadgame;

	JLabel scoreLabel = new JLabel("The Score :  ");
	JLabel rightLBL = new JLabel("             ");
	JLabel leftLBL = new JLabel("             ");
	JLabel bottomLBL = new JLabel("Welcome ..  :)");
	JLabel topScoreLabel[] = new JLabel[10];

	JButton NewGame = new JButton("New game");
	JButton Undo = new JButton("Undo");
	JButton b[][];

	JPanel pnlTopThings = new JPanel();
	JPanel pnlGridButtons;

	// ==========================================================================================
	// -----------------------------------Methods------------------------------
	// =====================================================

	/*
	 * «Ê· „‘ﬂ·… Õ“› «·“—«— «·«·Ê‰ »œÊ‰ „« ÷— «·«“«Õ… ⁄‰ «·Ì„Ì‰ (1 (2 * «·„‘ﬂ·…
	 * * «· «‰Ì…«· ⁄·Ìﬁ… «·Õ«’·… ›Ì «· Undo & NewGame (3 * »⁄œ Õ“› „Ã„Ê⁄… ⁄‰«’—
	 *  «Œ– «·ﬂ—… «·· Ì ›ÊﬁÂ« ‰›”Â« „— Ì‰ «Ì «‰Â „„ﬂ‰ Õ“›Â« ·ÊÕœÂ« X,Y same^
	 */

	// --------------- Class click with click the button------------------------
	class Click implements ActionListener {
		int i, j;

		Click(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			ga.selectAndPlay(i, j);
			if (ga.couldPlay()) {
				// --------------------------------Score-------------------
				ga.undochecker2GUI = false;
				String s = "The Score :  ";
				s += ga.score;
				scoreLabel.setText(s);
				// ------------------
				afterShowing();

			} else {
				afterShowing();
				JOptionPane.showMessageDialog(null, "The Game is Over :P ");
			}
		}
	}

	class beforeClick implements MouseListener {

		int i, j;

		beforeClick(int i, int j) {
			this.i = i;
			this.j = j;
			;
		}

		@Override
		public void mouseClicked(MouseEvent e) { // TODO Auto-generated

		}

		@Override
		public void mousePressed(MouseEvent e) { // TODO Auto-generated

		}

		@Override
		public void mouseReleased(MouseEvent e) { // ga.listDelete
			// setBackground(ga.listDelete) //ga.lst[i][j]. //ga.listDelete.
			// Color m =
			ga.o.customerColor(ga.lst[i][j].getNameColor());
			// setBackground(m.brighter());

			// setBackground(m);

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) { // TODO Auto-generated

		}
	}

	// --------------------------Constractor-----------------------------------

	// --------------------------Class ---------------------------------------

	// --==================( methods )=============================--

	// ------------------- constractior ---------------
	public BubblesGUI() {
		new OptionsForm();
	}

	// ======================== Levels ======================

	// -------------------------------------------------
	// ---------------------Selected Level------------------------------------
	void startButtonsPanelEsy() {

		ga.o.selectionLevel(1);
		ga.o.sizeOnX = 650;
		ga.o.sizeOnY = 500;
		firstShowing();
		appearGUI();
	}

	void startButtonsPanelMid() {

		ga.o.sizeOnX = 750;
		ga.o.sizeOnY = 600;
		ga.o.selectionLevel(2);
		firstShowing();
		appearGUI();

	}

	void startButtonsPanelHrd() {

		ga.o.sizeOnX = 850;
		ga.o.sizeOnY = 700;
		ga.o.selectionLevel(3);
		firstShowing();
		appearGUI();

	}

	public void startButtonsPanelSelected(int i) {
		if (i == 1){
			myLevel=1;
			startButtonsPanelEsy();}
		if (i == 2){
			myLevel=2;
			startButtonsPanelMid();}
		if (i == 3){
			myLevel=3;
			startButtonsPanelHrd();}
		
		ga.o.level=myLevel;
	}

	// --------------------------------------------------------


	// ------------------play game method -------------------------
	// ---------------Could Continue the Game ------

	// ----------------Could_Play------------------------------------------
	boolean stillCouldPlay() {
		return ga.couldPlay();
	}

	// ------------DisAppearButtons-----------

	void disAppearingButtons() {

		for (int i = 0; i < ga.o.numberOfrow; i++)
			for (int j = 0; j < ga.o.numberOfcol; j++)
				if (ga.lst[i][j].getNameColor() == -1)
					b[i][j].setVisible(false);

	}

	// ---------------------firstShowingValues-----------------------------------

	// ===((OrganizeThePanelOfButtons_))================

	void beforShowing() {

		pnlGridButtons.setVisible(false);
		pnlGridButtons.hide();
		b = new JButton[ga.o.numberOfrow][ga.o.numberOfcol];
		pnlGridButtons = new JPanel();
		pnlGridButtons.setVisible(true);
		pnlGridButtons.setLayout(new GridLayout(ga.o.numberOfrow,
				ga.o.numberOfcol));

		for (int i = 0; i < ga.o.numberOfrow; i++) {
			for (int j = 0; j < ga.o.numberOfcol; j++) {

				b[i][j] = new JButton();
				if (ga.lst2undo[i][j].getNameColor() == -1) {
					b[i][j].setVisible(true);
					pnlGridButtons.add(b[i][j]);
				} else {
					ga.lst[i][j].setNameColor(ga.lst2undo[i][j].getNameColor());
					Color m = ga.o.customerColor(ga.lst[i][j].getNameColor());
					b[i][j].setBackground(m);
					pnlGridButtons.add(b[i][j]);
					b[i][j].addActionListener(new Click(i, j));// new

				}
				b[i][j].addActionListener(new Click(i, j));// new
			}
		}
		pnlGridButtons.setVisible(true);
		this.add(pnlGridButtons);
		appearGUI();
		validate();
		repaint();
		afterShowing();

	}

	// /---not in use// /----------
	void firstShowing() {
		ga.lst = new Node[ga.o.numberOfrow][ga.o.numberOfcol];
		ga.lst2undo = new Node[ga.o.numberOfrow][ga.o.numberOfcol];

		b = new JButton[ga.o.numberOfrow][ga.o.numberOfcol];

		pnlGridButtons = new JPanel();
		pnlGridButtons.setVisible(true);
		pnlGridButtons.setLayout(new GridLayout(ga.o.numberOfrow,
				ga.o.numberOfcol));
		for (int i = 0; i < ga.o.numberOfrow; i++) {
			for (int j = 0; j < ga.o.numberOfcol; j++) {

				b[i][j] = new JButton();
				ga.lst[i][j] = new Node(i, j, ga.lst);
				// ga.lst2undo[i][j] = new Node(i, j, ga.lst);

				int x = ga.o.numberOfname;
				int xOldRand = Math.abs(ga.getrInt().nextInt(x));
				ga.lst[i][j].setNameColor(xOldRand);
				ga.lst[i][j].setRemoved(false);
				// ga.lst2undo[i][j].setNameColor(xOldRand);
				// ga.lst2undo[i][j].setRemoved(false);

				Color m = ga.o.customerColor(ga.lst[i][j].getNameColor());
				b[i][j].setBackground(m);

				pnlGridButtons.add(b[i][j]);
				b[i][j].addActionListener(new Click(i, j));
				// b[i][j].addMouseListener(new beforeClick(i,j));
			}
		}
	}

	void afterShowing() {
		for (int i = 0; i < ga.o.numberOfrow; i++) {
			for (int j = 0; j < ga.o.numberOfcol; j++) {
				Color m = ga.o.customerColor(ga.lst[i][j].getNameColor());
				b[i][j].setBackground(m);

				// for (int i = 0; i < ga.o.numberOfrow; i++)
				// for (int j = 0; j < ga.o.numberOfcol; j++)
				// if (ga.lst[i][j].getNameColor() == -1)
				// b[i][j].setVisible(false);

			}
		}
	}

	// ----------------- appearing method----------------

	// -------------------------------
	void appearGUI() {

		// -----------------Setting--------------------------
		this.setVisible(true);
		validate();
		setTitle("My Bubble Breaker");
		setSize(ga.o.sizeOnX, ga.o.sizeOnY);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// -------------------colorfuling -----------------
		scoreLabel.setForeground(Color.BLUE.brighter());
		bottomLBL.setForeground(Color.GREEN.darker());
		NewGame.setForeground(Color.GREEN.darker());
		Undo.setForeground(Color.ORANGE);
		//
		// ------------------------menu---------------------
		menub = new JMenuBar();
		menu = new JMenu("File");
		menu2 = new JMenu("View");
		itemTopScore = new JMenuItem("Top10Score");
		itemAbout = new JMenuItem("About");
		itemSave = new JMenuItem("Save");
		itemLoad = new JMenuItem("Load");
		itemExit = new JMenuItem("Exit ...... :( ");

		setJMenuBar(menub);
		menub.add(menu);
		menub.add(menu2);
		menu2.add(itemTopScore);
		menu2.add(itemAbout);
		menu.add(itemSave);
		menu.add(itemLoad);
		menu.add(itemExit);
		// -----------------------layout-------------------------

		setLayout(new BorderLayout());

		pnlTopThings.add(NewGame);
		pnlTopThings.add(Undo);
		pnlTopThings.add(scoreLabel);

		pnlTopThings.setLayout(new FlowLayout());
		this.add(pnlTopThings, BorderLayout.NORTH);

		// ----------------------adding --------------------
		this.add(pnlGridButtons, BorderLayout.CENTER);
		this.add(rightLBL, BorderLayout.EAST);
		this.add(leftLBL, BorderLayout.WEST);
		this.add(bottomLBL, BorderLayout.SOUTH);

		// --------------------------------------------------
		actionListenButtons();
	}

	// --------------------undo action method ----------------
	void undoGame() {
		ga.undoGame();
		beforShowing();
		ga.undochecker2GUI = false;
		validate();
		String s = "The Score :  ";
		s += ga.getScore();
		scoreLabel.setText(s);
		// System.out.println("count = " + ga.getCountundo());
		// System.out.println("Score = " + ga.getScore());

	}

	// --------------------new game method ----------------
	void getNewGame() {

		pnlGridButtons.setVisible(false);
		this.firstShowing();
		this.appearGUI();

		ga.setScore(0);
		ga.setCount(0);

	}

	// -----------------------------------Load----------
	void loadFileValues() {

		pnlGridButtons.setVisible(false);
		b = new JButton[loadgame.o.numberOfrow][loadgame.o.numberOfcol];
		pnlGridButtons = new JPanel();
		pnlGridButtons.setVisible(true);
		pnlGridButtons.setLayout(new GridLayout(loadgame.o.numberOfrow,
				loadgame.o.numberOfcol));

		for (int i = 0; i < loadgame.o.numberOfrow; i++) {
			for (int j = 0; j < loadgame.o.numberOfcol; j++) {

				b[i][j] = new JButton();
				if (loadgame.lst[i][j].getNameColor() == -1) {
					b[i][j].setVisible(false);
					pnlGridButtons.add(b[i][j]);
				} else {

					Color m = loadgame.o.customerColor(loadgame.lst[i][j]
							.getNameColor());
					b[i][j].setBackground(m);
					pnlGridButtons.add(b[i][j]);
					b[i][j].addActionListener(new Click(i, j));// new
				}
			}
		}
		pnlGridButtons.setVisible(true);
		this.add(pnlGridButtons);
		appearGUI();
		validate();
		repaint();
		afterShowing();

	}

	// ========================================================================
	// -------------------Calculate & Showing All Scores---------------

	// ---------------------------TheScores------------------

	/*
	 * String[] showingAllScores() {
	 * 
	 * 
	 * 
	 * for (int i = 0 ; i < 10 ; i ++) { s[i] = ga.score; } return s; }
	 */
	/*
	 * void saveScoreGUI() {
	 * 
	 * ga.savedScores(); String s [] = new String [10]; for (ga.iofTop =
	 * ga.top10Score.length; ga.iofTop>=0 ; ga.iofTop--) {
	 * 
	 * topScoreLabel[ga.iofTop]= new JLabel (".."); s[ga.iofTop ] = "The Score "
	 * + ga.iofTop + " : "+ ga.top10Score[ga.iofTop];}
	 * topScoreLabel[ga.iofTop].setText(s[ga.iofTop ]); }
	 */

	// ==============================INNER_CLASS===================================================

	// -------------------TOP Score--------------------

	class TopScore extends JFrame implements ActionListener {
		private static final long serialVersionUID = 1L;

		JButton btnOk = new JButton("O.K.");
		JPanel pTopLbl = new JPanel();

		void showingTop() {

			setLayout(new BorderLayout());

			for (int i = 0; i < 10; i++) {
				pTopLbl.add(topScoreLabel[i], new

				BoxLayout(pTopLbl, BoxLayout.PAGE_AXIS));
			}
			this.add(pTopLbl, BorderLayout.CENTER);
			this.add(btnOk, BorderLayout.SOUTH); // //
			// -----------------Setting-------------------------- //
			// setVisible(true);

			setLocationRelativeTo(null);
			validate();
			setTitle("Top 10 Score ");
			setSize(300, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			// ----------------------------------------------
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});

		}

		public void actionPerformed(ActionEvent e) {

		}
	}

	// -------------------OptionLevels--------------------

	class OptionsForm extends JFrame implements ActionListener {

		private static final long serialVersionUID = 1L;
		// ---------------------------------------------------------------

		JButton ESY = new JButton("Easy Playing");
		JButton MID = new JButton("Medium Playing");
		JButton HRD = new JButton("Hard Playing");

		OptionsForm() {
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
					startButtonsPanelSelected(1);
					myLevel = 1;
				}
			});
			MID.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					startButtonsPanelSelected(2);
					myLevel = 2;
				}
			});
			HRD.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					startButtonsPanelSelected(3);
					myLevel = 3;
				}
			});

		}

		@Override
		public void actionPerformed(ActionEvent e) {
		}

	}

	// ============ENDOthersClasses========================================

	// --------###------------actionListenButton methods--------###--------

	void actionListenButtons() {
		// -----===================---ActionListeners---=========================
		// -----------------------------undo action -------------------
		Undo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				undoGame();
				ga.score = ga.getScoreundo();
			}
		});
		// -----------------------------newGameAction------------------------------
		NewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getNewGame();
				scoreLabel.setText("The Score : " + 0);

				/*
				 * JOptionPane.showMessageDialog(null,
				 * "Do you sure to new game right now ?!! ", "new game !!",
				 * JOptionPane.showConfirmDialog(null,
				 * "Do you want to save this game ?!! "));
				 */
			}
		});
		// --=========( Menu listner )============--
		itemTopScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String S[] = new String[10];

				// S[i]= scoreNow();

				// S[i]=ga.savedScore();
				// = new String[s1,s2,s3,s4,s5,s6,s7,s8,s9,s10];
				// for (int i = 0; i < S.length; i++)
				// {S[i]=new String("Score " +aScore[i]+"");}
				// new TopScore().showingTop();
				JOptionPane.showMessageDialog(null, S, "Top 10 Scores",
						JOptionPane.DEFAULT_OPTION);

			}
		});

		// -------------------About----------------------------------

		itemAbout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Welcome to our game ;)",
						"About", JOptionPane.INFORMATION_MESSAGE);

			}
		});

		// --------------------save action ---------
		itemSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					JFileChooser saveFile = new JFileChooser();
					saveFile.addChoosableFileFilter(new FileFilter() {
						String description = "Game Files (*.MW)";
						String extension = "MW";

						public String getDescription() {
							return description;
						}

						public boolean accept(File f) {
							if (f == null)
								return false;
							if (f.isDirectory())
								return true;
							return f.getName().toLowerCase()
									.endsWith(extension);

						}
					});
					saveFile.setCurrentDirectory(new File("D:\\MyGamesSaved\\"));
					saveFile.showSaveDialog(BubblesGUI.this);
					ga.save(saveFile.getSelectedFile());
				} catch (Exception er) {
				}
			}

		});
		// ---------------------load action ---------
		itemLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser load = new JFileChooser();

				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Game Files (*.MW)", "MW");

				load.setFileFilter(filter);

				load.setCurrentDirectory(new File("D:\\MyGamesSaved\\"));
				load.setAcceptAllFileFilterUsed(false);

				int returnVal = load.showOpenDialog(BubblesGUI.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {

					File loadFile = load.getSelectedFile();
					loadgame = new Game(loadFile);
					loadFileValues();

				}
			}
		});

		// ---------------------Exit action ---------
		itemExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,
						"Are you sure to exit right now ?!! ", "Exit !!",
						JOptionPane.OK_CANCEL_OPTION);
				System.exit(0);
			}
		});

	}

}
/*
 * //============================================
 */