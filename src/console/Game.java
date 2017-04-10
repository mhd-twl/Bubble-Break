package console;

import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

//import bricks.Brick;
//import bricks.BricksBrakerGame;

public class Game implements Serializable {
	private static final long serialVersionUID = 1L;
	// -----------------------------------------------------------------------
	private int count = 0;// not initialy
	public int score = 0;
	protected int score2top = 0;
	private int countundo = 0;
	private int scoreundo = 0;
	
	//int[] top10Score = new int[10]; // not in console
	//int score2top;
	public static LinkedList<Integer> top10List = new LinkedList<Integer>();

	boolean undochecker = false;
	public boolean undochecker2GUI = false;

	transient Scanner input = new Scanner(System.in);
	private Random rInt = new Random();

	public Options o = new Options();

	public Node[][] lst;
	public Node[][] lst2undo;

	public ArrayList<Node> listDelete = new ArrayList<Node>();
	private int me;

	// private Object brc;
	// private Object undoBrc;

	// /----------- Constractor ------------------
	public Game() {

		o.selectionLevel(o.level);
		me = o.level ;
		// lst = new Node[o.numberOfrow][o.numberOfcol];
		// lst2undo = new Node[o.numberOfrow][o.numberOfcol];// / was in
		// playconsole
	}

	public Game(File f) {
		this();
		ObjectInputStream ois = null;
		try {
			FileInputStream fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			Object obj = null;
			obj = ois.readObject();
			if (obj instanceof Game) {
				this.setCount(((Game) obj).getCount());
				this.score = ((Game) obj).score;
				//this.iofTop = ((Game) obj).iofTop;
				this.setCountundo(((Game) obj).getCountundo());
				this.setScoreundo(((Game) obj).getScoreundo());
				//this.level = ((Game) obj).level;
				// this.top10List = ((Game) obj).top10List;
				this.undochecker = ((Game) obj).undochecker;
				this.undochecker2GUI = ((Game) obj).undochecker2GUI;
				this.setrInt(((Game) obj).getrInt());
				this.o = ((Game) obj).o;
				this.lst = ((Game) obj).lst;
				this.lst2undo = ((Game) obj).lst2undo;
				this.listDelete = ((Game) obj).listDelete;
			}

		} catch (EOFException ex) {

			System.out.println("End of file reached.");
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
	}

	// -------------------------------------------------------------------------------------------
	void setME() {
		if (me == 1)
			o.selectionLevel(1);
		if (me == 2)
			o.selectionLevel(2);
		if (me == 3)
			o.selectionLevel(3);

	}
	
	// ====================== Methods===============================
	// -----------playGame console ------------------
	public void playGameConsole() {

		int inputLevel;

		System.out
				.println("Press the button you want: ((( Easy 1))), ((( Normal 2))) ,((( Hard 3)))  ");

		inputLevel = input.nextInt();

		if (inputLevel == 1) {
			o.selectedOptionESY();
		}
		if (inputLevel == 2) {
			o.selectedOptionMID();
		}
		if (inputLevel == 3) {
			o.selectedOptionHRD();
		}

		virtualPrint();

		do {    ///,(((saved&laod 3 )))

			printList();
			System.out
					.println("Press the button you want: ((( ToNewGame 1))), ((( ToUndo 2))), ElseINT.. ToselectNode  ");
			int inputfirst = input.nextInt();

			if (inputfirst == 1) {
				newGame();
				virtualPrint();
				playGameConsole();
			} else if (inputfirst == 2) {

				undoGame();
				System.out.println("count = " + getCountundo());
				System.out.println("Score = " + getScore());
				undochecker = false;
			}

			/*else if (inputfirst == 3) {

			//	printSavedScore();
			}*/

			else {

				System.out.println("Press the button you want x then y : ");
				int x = input.nextInt();
				int y = input.nextInt();
				selectAndPlay(x, y);
				System.out.println("count = " + getCount());
				System.out.println("Score = " + getScore());
			}

		} while (couldPlay());
		printList();
		System.out.println("The game is over .. :P ");
	}

	// ----------- print -------------------- 
	void printList() {

		for (int i = 0; i < o.numberOfrow; i++) {
			for (int j = 0; j < o.numberOfcol; j++) {
				System.out.print(lst[i][j].getNameColor() + "\t");
				lst[i][j].setVisit(false);
			}
			System.out.println("\n");
		}

	}

	// --------Get Node value ------------------
	public void virtualPrint() { // playconsole
		lst = new Node[o.numberOfrow][o.numberOfcol];
		lst2undo = new Node[o.numberOfrow][o.numberOfcol];

		for (int i = 0; i < o.numberOfrow; i++) {
			for (int j = 0; j < o.numberOfcol; j++) {

				lst[i][j] = new Node(i, j, lst);

				int x = Math.abs(getrInt().nextInt(o.numberOfname));
				lst[i][j].setNameColor(x);
				lst[i][j].setRemoved(false);

			}
		}
	}

	// ------------saveFile-----------------
	public void save(File f) {
		ObjectOutputStream oos = null;
		try {
			String fileName = f.toString() + ".MW";
			FileOutputStream fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
		} catch (FileNotFoundException fex) {
			System.out.println(fex);
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
			} catch (Exception ex1) {

			}
		}
	}

	// -----------------------------
	@SuppressWarnings("unused")
	void load(File f) {
		JFileChooser load = new JFileChooser();

		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"Game Files (*.MW)", "MW");

//		load.setFileFilter(filter);
//
//		load.setCurrentDirectory(new File("D:\\MyGamesSaved\\"));
//		load.setAcceptAllFileFilterUsed(false);

		File loadFile = load.getSelectedFile();
		Game ga = new Game(loadFile);

	}

	// ============= savedscore-==========================================
	// ------------------------the scores
	// --------//high2low-----////////////////////Score Calculation

	/*void  savedScores() {
		
		 score2top = score;
		if (score2top != score2top)
			top10Score[iofTop] = this.score;
		 //savedScores();
		
		Arrays.sort(top10Score);
		iofTop++;
		return top10Score[iofTop];
	}

	void printSavedScore() {
		savedScore();
		boolean bol = 10 > top10List.size();
		for (iofTop = 0; iofTop < 10 && bol; ++iofTop) {
			if (top10List.get(iofTop) != null) {
				int x = top10List.get(iofTop);
				System.out.println("Score " + iofTop + ": " + x);
			}
			continue;
		}
	}

	public void savedScore() {

		top10List.add(getScore());
		int x = getScore();
		int y = Collections.min(top10List);
		if ((x < y)) {
			Collections.sort(top10List, Collections.reverseOrder());
		}

	}

	*/// =================================================================================
	// ====================Uses In Gui============================================
	// -----------------------------------------------------------

	/*
	 * public void firstGivenValuesShowing() { lst = new
	 * Node[o.numberOfrow][o.numberOfcol]; lst2undo = new
	 * Node[o.numberOfrow][o.numberOfcol];// / was in
	 * 
	 * for (int i = 0; i < o.numberOfrow; i++) { for (int j = 0; j <
	 * o.numberOfcol; j++) {
	 * 
	 * 
	 * lst[i][j] = new Node(i, j,lst); lst2undo[i][j] = new Node(i, j,lst);
	 * 
	 * int x = o.numberOfname; int xOldRand = Math.abs(getrInt().nextInt(x));
	 * lst[i][j].setNameColor(xOldRand); lst[i][j].setRemoved(false);
	 * lst2undo[i][j].setNameColor(xOldRand); lst2undo[i][j].setRemoved(false);
	 * 
	 * 
	 * } } }
	 */

	// ------------------------------------

	public void selectAndPlay(int x, int y) {

		System.out.println(x + "," + y);
		System.out.println("TheColor : "
				+ Integer.toString((lst[x][y].getNameColor())));
		if (lst[x][y].getNameColor() != -1) {

			listDelete.clear();

			for (int i = 0; i < o.numberOfrow; i++) {
				for (int j = 0; j < o.numberOfcol; j++) {
					lst[i][j].setVisit(false);
				}
			}

			listDelete = lst[x][y].searchNode2(lst, x, y);
			setCount(listDelete.size());
			System.out.println("count:" + getCount());
			setCountundo(getCount());
			setScore(getScore() + (getCount() * (getCount() - 1)));
			setScoreundo(getScore());
			for (int i = 0; i < o.numberOfrow; i++) {
				for (int j = 0; j < o.numberOfcol; j++) {
					lst2undo[i][j] = new Node(i, j, lst2undo); // it was lst
																// where
																// lst2undo
					lst2undo[i][j].setNameColor(lst[i][j].getNameColor());
				}
			}
			lst[x][y].deleteNodes();
			lst[x][y].checkDeletedSheftRight();
		}

	}

	// ===========================CHECKING========================================
	// /--------- CouldPlay -----------------
	public boolean couldPlay() {
		for (int i = 0; i < o.numberOfrow; i++) {
			for (int j = 0; j < o.numberOfcol; j++) {
				if (i - 1 >= 0 && lst[i][j].getNameColor() != -1
						&& lst[i - 1][j].getNameColor() != -1) {
					if (lst[i][j].getNameColor() == lst[i - 1][j]
							.getNameColor()) {
						return true;
					}
				} else if (i + 1 < o.numberOfcol
						&& lst[i][j].getNameColor() != -1
						&& lst[i + 1][j].getNameColor() != -1) {
					if (lst[i][j].getNameColor() == lst[i + 1][j]
							.getNameColor()) {
						return true;
					}
				} else if (j - 1 >= 0 && lst[i][j].getNameColor() != -1
						&& lst[j][j - 1].getNameColor() != -1) {
					if (lst[i][j].getNameColor() == lst[j - 1][j]
							.getNameColor()) {
						return true;
					}
				} else if (j + 1 < o.numberOfcol
						&& lst[i][j].getNameColor() != -1
						&& lst[i][j + 1].getNameColor() != -1) {
					if (lst[i][j].getNameColor() == lst[j + 1][j]
							.getNameColor()) {
						return true;
					}
				}
			}

		}
		return false;

	}

	// /--------- new game -------------
	void newGame() {
		//savedScore();
		score2top = score;
		setCount(0);
		setScore(0);
		
	}

	// ----------- undo ----------------
	public void undoGame() {
		if (!undochecker) {
			undochecker = true;
			for (int i = 0; i < o.numberOfrow; i++) {
				for (int j = 0; j < o.numberOfcol; j++) {
					lst[i][j].setNameColor(lst2undo[i][j].getNameColor());
				}
			}
			setScoreundo(getScoreundo() - (getCount() * (getCount() - 1)));
			if (getScoreundo() == 0)
				setScoreundo(0);

			setCount(0);
			setScore(getScoreundo());
		}
	}

	// --------------------------------------

	public void setScore(int Score) {
		score = Score;
	}

	public int getScore() {
		return score;
	}

	public void setrInt(Random rInt) {
		this.rInt = rInt;
	}

	public Random getrInt() {
		return rInt;
	}

	public void setCountundo(int countundo) {
		this.countundo = countundo;
	}

	public int getCountundo() {
		return countundo;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setScoreundo(int scoreundo) {
		this.scoreundo = scoreundo;
	}

	public int getScoreundo() {
		return scoreundo;
	}

	// ==========================ENDGameClass========================
}

/*
 * lst2setNew = new Node[o.numberOfrow][o.numberOfcol]; for (int i = 0; i <
 * o.numberOfrow; i++) { for (int j = 0; j < o.numberOfcol; j++) {
 * lst2setNew[i][j] = new Node(i, j, lst2setNew);
 * lst2setNew[i][j].setNameColor(lst[i][j].getNameColor()); } }
 */
