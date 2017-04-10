package console ;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Node extends Options implements Serializable {

	private static final long serialVersionUID = 1L;
	//------------------------------------------------
	protected Node[][] MyNodes;
	private int x, y, nameColor;
	private Random rIntme = new Random();
	private boolean isVisit = false;
	private boolean isRemoved= false;
	Game g = new Game() ;
	
	
	ArrayList<Node> l = new ArrayList<Node>();

	// ----------------- Constactor ------------------------
	public Node(int i, int j, Node[][] n) {
		this.setX(i);
		this.setY(j);
		this.MyNodes = n;
		g.o.selectedOptionHRD() ;
	}

	// ====================== Node methods =====================================
	// ==========================================Not working search===============================
	/// not in usee
	ArrayList<Node> searchNode(Node n[][], int i, int j) {

		MyNodes[i][j].setVisit(true);
		l.add(MyNodes[i][j]);

		if (i - 1 >= 0
				&& this.getNameColor() == MyNodes[i - 1][j].getNameColor()) {
			if (!MyNodes[i - 1][j].isVisit()) {
				l.addAll(MyNodes[i - 1][j].searchNode(MyNodes, i - 1, j));
			}
		}
		if ( i + 1 < MyNodes.length   || i + 1 < g.o.numberOfrow
				&& this.getNameColor() == MyNodes[i + 1][j].getNameColor()) {
			if (!MyNodes[i + 1][j].isVisit()) {
				l.addAll(MyNodes[i + 1][j].searchNode(MyNodes, i + 1, j));
			}
		}
		if (j - 1 >= 0
				&& this.getNameColor() == MyNodes[i][j - 1].getNameColor()) {
			if (!MyNodes[i][j - 1].isVisit()) {
				l.addAll(MyNodes[i][j - 1].searchNode(MyNodes, i, j - 1));
			}
		}
		if (j + 1 < g.o.numberOfcol
				&& this.getNameColor() == MyNodes[i][j + 1].getNameColor()) {
			if (!MyNodes[i][j + 1].isVisit()) {
				l.addAll(MyNodes[i][j + 1].searchNode(MyNodes, i, j + 1));
			}
		}
		//MyNodes[i][j].setVisit(false);
		return l;
	}
	
	// -------------------- search same node ----------------------------
	ArrayList<Node> searchNode2(Node n[][], int i, int j) {

		MyNodes[i][j].setVisit(true);
		l.add(MyNodes[i][j]);

		if (i - 1 >= 0
				&& this.getNameColor() == MyNodes[i - 1][j].getNameColor()) {
			if (!MyNodes[i - 1][j].isVisit()) {
				l.addAll(n[i - 1][j].searchNode2(MyNodes, i - 1, j));
			}
		}
		if (i + 1 < g.o.numberOfrow
				&& this.getNameColor() == MyNodes[i + 1][j].getNameColor()) {
			if (!MyNodes[i + 1][j].isVisit()) {
				l.addAll(n[i + 1][j].searchNode2(MyNodes, i + 1, j));
			}
		}
		if (j - 1 >= 0
				&& this.getNameColor() == MyNodes[i][j - 1].getNameColor()) {
			if (!MyNodes[i][j - 1].isVisit()) {
				l.addAll(n[i][j - 1].searchNode2(MyNodes, i, j - 1));
			}
		}
		if (j + 1 < g.o.numberOfcol
				&& this.getNameColor() == MyNodes[i][j + 1].getNameColor()) {
			if (!MyNodes[i][j + 1].isVisit()) {
				l.addAll(n[i][j + 1].searchNode2(MyNodes, i, j + 1));
			}
		}
		return l;
	}

	// -------------------- Deleting Node ---------------
	void deleteNodes() {
		if (l.size() >= 2) {
			for (int i = 0; i < g.o.numberOfrow; i++)
				for (int j = 0; j < g.o.numberOfcol; j++) {
					if (MyNodes[i][j].isVisit()) {
						MyNodes[i][j].setVisit(false);
						for (int q = i; q >= 0; q--) {
							if (q == 0) {
								MyNodes[q][j].setNameColor(-1);
								MyNodes[q][j].setRemoved(true);
								
							} else {
								if (MyNodes[q - 1][j].getNameColor() != -1) {
									MyNodes[q][j]	.setNameColor(MyNodes[q - 1][j]	.getNameColor());
									MyNodes[q - 1][j].setNameColor(-1);
									MyNodes[q][j].setRemoved(true);
								} else {
									MyNodes[q][j].setNameColor(-1);
									MyNodes[q][j].setRemoved(true);
								}
							}
						}
					}
				}
		}
	}

	// ---------------------- Shefting Right --------------
	void checkDeletedSheftRight() {
		for (int i = 0; i < g.o.numberOfrow; i++)
			for (int p = 0; p < g.o.numberOfcol; p++) {
				if (MyNodes[g.o.numberOfrow - 1][p].getNameColor() == -1) {
					// q = i , p = j
					if (p == 0) {
						for (int q = 0; q < g.o.numberOfrow; q++) {
							MyNodes[q][p].setNameColor(Math.abs(rIntme
									.nextInt(g.o.numberOfname)));
						}
					} else {
						for (int q = 0; q < g.o.numberOfrow; q++) {
							MyNodes[q][p].setNameColor(MyNodes[q][p - 1]
									.getNameColor());
							MyNodes[q][p - 1].setNameColor(-1);
						}
					}

				}
			}
	}

	// ===================================================================================

	@Override
	public String toString() {
		return Integer.toString(getNameColor());
	}

	// ------------------------------ x -------------------------
	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	// ------------------------------ y -------------------------
	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	// -----------------------------isVisit-------------------------------
	public void setVisit(boolean isVisit) {
		this.isVisit = isVisit;
	}

	public boolean isVisit() {
		return isVisit;
	}

	// -----------------------------name-------------------------------
	public void setNameColor(int name) {
		this.nameColor = name;
	}	
	public int getNameColor() {
		return nameColor;
	}

	public void setRemoved(boolean removed) {
		this.isRemoved = removed;
	}

	public boolean isRemoved() {
		return isRemoved;
	}
}
	// ------------------------- color -----------------------
	
// =========================================================
// //---#########################################################
/*
 * ArrayList<Node> searchNode(Node n[][], int i, int j) {
 * 
 * n[i][j].isVisit = true; l.add(n[i][j]);
 * 
 * if (i - 1 >= 0 && this.name == n[i - 1][j].name && !n[i - 1][j].isVisit) {
 * l.addAll(n[i - 1][j].searchNode(n, i - 1, j)); } if (i + 1 < o.nuOfrow &&
 * this.name == n[i + 1][j].name && !n[i + 1][j].isVisit) { l.addAll(n[i +
 * 1][j].searchNode(n, i + 1, j)); } if (j - 1 >= 0 && this.name == n[i][j -
 * 1].name && !n[i][j - 1].isVisit) { l.addAll(n[i][j - 1].searchNode(n, i, j -
 * 1)); } if (j + 1 < o.nuOfcol && this.name == n[i][j + 1].name && !n[i][j +
 * 1].isVisit) { l.addAll(n[i][j + 1].searchNode(n, i, j + 1)); } return l; } //
 */
// //--------####################################################------------------------------


