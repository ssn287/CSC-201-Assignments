/**
 * Cramers class solves 3x3 systems of equations using Cramer's rule
 * @author sneal
 * Emplid: 6030859
 * Email: ssn287@email.vccs.edu
 * Purpose: Programming Assignment #9
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Cramers extends JFrame implements ActionListener {
	private static final int SIZE = 3;
	private static JButton jbtCalculate = new JButton("Calculate");
	private static JTextField[] jtxtA = new JTextField[SIZE];
	private static JTextField[] jtxtB = new JTextField[SIZE];
	private static JTextField[] jtxtC = new JTextField[SIZE];
	private static JTextField[] jtxtD = new JTextField[SIZE];
	private static JLabel[] jlblModuleName = new JLabel[SIZE];
	private static JLabel[] jlblTitles = new JLabel[5];
	private static JLabel jlblOutput = new JLabel("x = ?, y = ?, z = ?");
	private static JPanel inputPanel = new JPanel();
	private static JPanel outputPanel = new JPanel();
	private static double[][] coeffs = new double[SIZE][SIZE + 1];
	public Cramers() {
		jlblModuleName[0] = new JLabel("Eq 1");
		jlblModuleName[1] = new JLabel("Eq 2");
		jlblModuleName[2] = new JLabel("Eq 3");
		jlblTitles[0] = new JLabel("Cramer's Rule: ");
		jlblTitles[1] = new JLabel("Ax +");
		jlblTitles[2] = new JLabel("By +");
		jlblTitles[3] = new JLabel("Cz =");
		jlblTitles[4] = new JLabel("D");
		inputPanel.setBackground(Color.CYAN);
		inputPanel.setLayout(new GridLayout(4, 5));
		for(int i = 0; i < 5; i++) {
			inputPanel.add(jlblTitles[i]);
		}
		for(int i = 0; i < SIZE; i++) {
			inputPanel.add(jlblModuleName[i]);
			jtxtA[i] = new JTextField(8);
			inputPanel.add(jtxtA[i]);
			jtxtB[i] = new JTextField(8);
			inputPanel.add(jtxtB[i]);
			jtxtC[i] = new JTextField(8);
			inputPanel.add(jtxtC[i]);
			jtxtD[i] = new JTextField(8);
			inputPanel.add(jtxtD[i]);
		}
		outputPanel.setBackground(Color.GREEN);
		outputPanel.setLayout(new GridLayout(2, 1));
		jbtCalculate.addActionListener(this);
		outputPanel.add(jbtCalculate);
		outputPanel.add(jlblOutput);
		add(inputPanel, BorderLayout.CENTER);
		add(outputPanel, BorderLayout.SOUTH);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
	}
	public static double getDet(double m[][]) {
	    return m[0][0] * (m[1][1] * m[2][2] - m[2][1] * m[1][2])
	    	 - m[0][1] * (m[1][0] * m[2][2] - m[1][2] * m[2][0])
	         + m[0][2] * (m[1][0] * m[2][1] - m[1][1] * m[2][0]);
	}
	public static String implementCramers() {
		String output = "";
		for(int i = 0; i < SIZE; i++) {
			coeffs[i][0] = Double.parseDouble(jtxtA[i].getText());
			coeffs[i][1] = Double.parseDouble(jtxtB[i].getText());
			coeffs[i][2] = Double.parseDouble(jtxtC[i].getText());
			coeffs[i][3] = Double.parseDouble(jtxtD[i].getText());
		}
	    double d[][] =  {{coeffs[0][0], coeffs[0][1], coeffs[0][2]}, {coeffs[1][0], coeffs[1][1], coeffs[1][2]}, {coeffs[2][0], coeffs[2][1], coeffs[2][2]}}; 
	    double d1[][] = {{coeffs[0][3], coeffs[0][1], coeffs[0][2]}, {coeffs[1][3], coeffs[1][1], coeffs[1][2]}, {coeffs[2][3], coeffs[2][1], coeffs[2][2]}};
	    double d2[][] = {{coeffs[0][0], coeffs[0][3], coeffs[0][2]}, {coeffs[1][0], coeffs[1][3], coeffs[1][2]}, {coeffs[2][0], coeffs[2][3], coeffs[2][2]}};
	    double d3[][] = {{coeffs[0][0], coeffs[0][1], coeffs[0][3]}, {coeffs[1][0], coeffs[1][1], coeffs[1][3]}, {coeffs[2][0], coeffs[2][1], coeffs[2][3]}};
	    double D = getDet(d);
	    double D1 = getDet(d1);
	    double D2 = getDet(d2);
	    double D3 = getDet(d3);
	    if(D != 0) {
	        double x = D1 / D;
	        double y = D2 / D;
	        double z = D3 / D;
	        output = "x = " + x + ", y = " + y + ", z = " + z;
	    }
	    else {
	        if (D1 == 0 && D2 == 0 && D3 == 0) {
	            output = "Infinite solutions";
	        }
	        else {
	        	if(D1 != 0 || D2 != 0 || D3 != 0) {
	        		output = "No solutions";
	        	}
	        }
	    }
	    return output;
	}
	public void actionPerformed(ActionEvent e) {
		jlblOutput.setText(implementCramers());
	}
}