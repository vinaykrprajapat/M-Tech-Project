package algo;

public class Multiply {

	public static double[][] multiply(double[][] pt, double[][] kinverse) {
	       int rowsInA = pt.length;
	       int columnsInA = pt[0].length; // same as rows in B
	       int columnsInB = kinverse[0].length;
	       double[][] c = new double[rowsInA][columnsInB];
	       for (int i = 0; i < rowsInA; i++) {
	           for (int j = 0; j < columnsInB; j++) {
	               for (int k = 0; k < columnsInA; k++) {
	                   c[i][j] =  (c[i][j] + pt[i][k] * kinverse[k][j]);
	               }
	           }
	       }
	       return c;
	   }


	
}
