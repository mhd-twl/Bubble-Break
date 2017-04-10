package console;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class NodeExploding extends Node {
	private static final long serialVersionUID = 1L;

	// -----------------------------------------------------
	public NodeExploding(int i, int j, Node[][] n) {
		super(i, j, n);
		
		
		// TODO Auto-generated constructor stub
	}

	public void Boom(int x, int y) {

		int i = x;
		int j = y;

		if (MyNodes[i][j].equals(4)) {
			if (i == 0) {
				if (j == 0) {
					MyNodes[i][j].setNameColor(-1);
					;
					MyNodes[i][j + 1].setNameColor(-1);
					;
					MyNodes[i + 1][j + 1].setNameColor(-1);
					;
					MyNodes[i + 1][j].setNameColor(-1);
					;
				} else {
					// --------------J-------------------
					MyNodes[i + 1][j].setNameColor(-1);
					;

					MyNodes[i][j].setNameColor(-1);
					;
					// --------------J-------------------
					MyNodes[i][j - 1].setNameColor(-1);
					;
					MyNodes[i][j + 1].setNameColor(-1);
					;

					// --------------I-------------------

					MyNodes[i + 1][j].setNameColor(-1);
					;

					// --------------I+j-------------------
					MyNodes[i + 1][j + 1].setNameColor(-1);
					;
					MyNodes[i + 1][j - 1].setNameColor(-1);
					;

					// --------------I+j-------------------
				}
			}

			else if (j == 0) {
				MyNodes[i][j].setNameColor(-1);
				;
				// --------------J-------------------
				MyNodes[i - 1][j].setNameColor(-1);
				;
				MyNodes[i + 1][j].setNameColor(-1);
				;

				// --------------I-------------------
				MyNodes[i - 1][j + 1].setNameColor(-1);
				;
				MyNodes[i][j + 1].setNameColor(-1);
				;

				// --------------I+j-------------------
				MyNodes[i + 1][j + 1].setNameColor(-1);
				;

			}

			else {

				MyNodes[i][j].setNameColor(-1);
				;
				MyNodes[i][j].setNameColor(-1);
				;
				// --------------J-------------------
				MyNodes[i][j - 1].setNameColor(-1);
				;
				MyNodes[i][j + 1].setNameColor(-1);
				;

				// --------------I-------------------
				MyNodes[i - 1][j].setNameColor(-1);
				;
				MyNodes[i + 1][j].setNameColor(-1);
				;

				// --------------I+j-------------------
				MyNodes[i + 1][j + 1].setNameColor(-1);
				;
				MyNodes[i + 1][j - 1].setNameColor(-1);
				;

				// --------------I+j-------------------
				MyNodes[i - 1][j - 1].setNameColor(-1);
				;
				MyNodes[i - 1][j + 1].setNameColor(-1);
				;
			}

		}

	}
}
