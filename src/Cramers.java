/**
 * Cramers class solves 3x3 systems of equations using Cramer's rule
 * @author Shelby Neal
 * Emplid: 6030859
 * Email: ssn287@email.vccs.edu
 * Purpose: Programming Assignment #9
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Cramers extends JFrame implements ActionListener {
	private static JButton jbtCalculate = new JButton("Solve Using Cramer's Rule");
	private static JTextField[][] jtxtInput = new JTextField[3][4];
	private static JLabel[] jlblRow = new JLabel[3];
	private static JLabel[] jlblCol = new JLabel[5];
	private static JLabel jlblTitle = new JLabel("Please Input Coefficients A, B, C, and D for Three (3) Equations");
	private static JLabel jlblOutput = new JLabel("x = ?, y = ?, z = ?");
	private static JPanel titlePanel = new JPanel();
	private static JPanel inputPanel = new JPanel();
	private static JPanel outputPanel = new JPanel();
	private static double[][] co = new double[3][4];
	/**
	 * GUI constructor
	 */
	public Cramers() {
		jlblRow[0] = new JLabel("Equation 1:");
		jlblRow[1] = new JLabel("Equation 2:");
		jlblRow[2] = new JLabel("Equation 3:");
		jlblCol[0] = new JLabel("");
		jlblCol[1] = new JLabel("         Ax       +");
		jlblCol[2] = new JLabel("         By       +");
		jlblCol[3] = new JLabel("         Cz       =");
		jlblCol[4] = new JLabel("         D");
		titlePanel.setLayout(new GridLayout(2, 1));
		titlePanel.add(jlblTitle);
		inputPanel.setLayout(new GridLayout(4, 5));
		for(int i = 0; i < 5; i++) {
			inputPanel.add(jlblCol[i]);
		}
		for(int i = 0; i < 3; i++) {
			inputPanel.add(jlblRow[i]);
			for(int j = 0; j < 4; j++) {
				jtxtInput[i][j] = new JTextField(8);
				inputPanel.add(jtxtInput[i][j]);
			}
		}
		outputPanel.setLayout(new GridLayout(2, 1));
		jbtCalculate.addActionListener(this);
		outputPanel.add(jbtCalculate);
		outputPanel.add(jlblOutput);
		add(titlePanel, BorderLayout.NORTH);
		add(inputPanel, BorderLayout.CENTER);
		add(outputPanel, BorderLayout.SOUTH);
		setVisible(true);
		pack();
		setSize(400, 200);
		setLocationRelativeTo(null);
	}
	/**
	 * getDet finds the determinant of a 3x3 matrix
	 * {{a, b, c}, {d, e, f}, {g, h, i}}
	 * @return a(ei - fh) - b(di - fg) + c(dh - eg)
	 */
	public static double getDet(double m[][]) {
		return m[0][0] * (m[1][1] * m[2][2] - m[2][1] * m[1][2])
			 - m[0][1] * (m[1][0] * m[2][2] - m[1][2] * m[2][0])
			 + m[0][2] * (m[1][0] * m[2][1] - m[1][1] * m[2][0]);
	}
	public static String solve() {
		String result = "";
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++) {
				co[i][j] = Double.parseDouble(jtxtInput[i][j].getText());
			}
		}
		double d[][] =  {{co[0][0], co[0][1], co[0][2]}, {co[1][0], co[1][1], co[1][2]}, {co[2][0], co[2][1], co[2][2]}}; 
		double d1[][] = {{co[0][3], co[0][1], co[0][2]}, {co[1][3], co[1][1], co[1][2]}, {co[2][3], co[2][1], co[2][2]}};
		double d2[][] = {{co[0][0], co[0][3], co[0][2]}, {co[1][0], co[1][3], co[1][2]}, {co[2][0], co[2][3], co[2][2]}};
		double d3[][] = {{co[0][0], co[0][1], co[0][3]}, {co[1][0], co[1][1], co[1][3]}, {co[2][0], co[2][1], co[2][3]}};
		double D =  getDet(d); double D1 = getDet(d1); double D2 = getDet(d2); double D3 = getDet(d3);
		if(D != 0) {
			double x = D1 / D; double y = D2 / D; double z = D3 / D;
			result = "x = " + x + ", y = " + y + ", z = " + z;
		}
		else {
			if(D1 == 0 && D2 == 0 && D3 == 0) {
				result = "Infinite solutions";
			}
			else {
				if(D1 != 0 || D2 != 0 || D3 != 0) {
					result = "No solutions";
				}
			}
		}
		return result;
	}
	public void actionPerformed(ActionEvent e) {
		jlblOutput.setText(solve());
	}
}