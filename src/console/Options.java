package console;

import java.awt.Color;
import java.io.Serializable;

public class Options implements Serializable {

	private static final long serialVersionUID = 1L;
	// ---------------------------------------------------
	public int sizeOnX, sizeOnY;
	public int level;
	
	public int numberOfrow;
	public int numberOfcol;
	public int numberOfname;
	Color deletedOne = Color.lightGray; // doesn't in use
	Color btnMyColors[] = new Color[20];;

	
	int allmyXes = 10 ; 
	int allmyYes = 10 ; 
	
	
	int rowEsy = allmyXes, colEsy = allmyYes, nameEsy = 4;

	int rowMid = allmyXes, colMid = allmyYes, nameMid = 8;

	int rowHrd = allmyXes, colHrd = allmyYes, nameHrd = 12;

	// just for numb color...
	// int nuOfColorEasy = 5 ,nuOfColorMid =10 , nuOfColorHard=115 ;

	Color btnColorESY[] = { Color.yellow, Color.red, Color.green, Color.blue, };

	Color btnColorMID[] = { Color.yellow, Color.red, Color.green, Color.blue,
			Color.CYAN, Color.orange, Color.pink, Color.magenta };

	Color btnColorHRD[] = { Color.yellow, Color.red, Color.green, Color.blue,
			Color.CYAN, Color.orange, Color.pink, Color.magenta,
			Color.yellow.darker(), Color.red.darker(), Color.green.darker(),
			Color.blue.darker() };

	// transient Scanner input = new Scanner(System.in);

	// ====================== levels methods ==============================
	void selectedOptionESY() {
		numberOfrow = rowEsy;
		numberOfcol = colEsy;
		numberOfname = nameEsy;
		sizeOnX = 650;
		sizeOnY = 500;
		level =1 ;
		btnMyColors = btnColorESY;
	}

	void selectedOptionMID() {
		numberOfrow = rowMid;
		numberOfcol = colMid;
		numberOfname = nameMid;

		sizeOnX = 750;
		sizeOnY = 600;
		level =2 ;
		btnMyColors = btnColorMID;
	}

	void selectedOptionHRD() {
		numberOfrow = rowHrd;
		numberOfcol = colHrd;
		numberOfname = nameHrd;
		sizeOnX = 850;
		sizeOnY = 700;
		level =3 ;
		btnMyColors = btnColorHRD;
	}

	// ---------------------------------------------
	
	
	//-----------------
	public void selectionLevel(int x) {
		if (x == 1)
			{selectedOptionESY();
			;}

		else if (x == 2)
			selectedOptionMID();

		else if (x == 3)
			selectedOptionHRD();

	}

	// -----------------------------------------
	public Color customerColor(int i) {
		Color c = null;
		if (i == -1) {
		} else {
			c = btnColorHRD[i];
		}
		return c;
	}

}
