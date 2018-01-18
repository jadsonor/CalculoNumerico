package br.com.jor.metodos.aproximacao;


import Jama.Matrix;

public class InterpolacaoDeLagrange extends InterpolacaoPolinomial{
	
	public static double[] encontrarFatorespolinomiais(double[] x, double[] y){
		
		int n = x.length;

		double[][] data = new double[n][n];
		double[] rhs = new double[n];

		for (int i = 0; i < n; i++) {
			double v = 1;
			for (int j = 0; j < n; j++) {
				data[i][n - j - 1] = v;
				v *= x[i];
			}

			rhs[i] = y[i];
		}

		// Solve m * s = b

		Matrix m = new Matrix(data);
		Matrix b = new Matrix(rhs, n);

		Matrix s = m.solve(b);

		return s.getRowPackedCopy();
		
	}
	
	/*public static void main(String args[]) {
		double x[] = { -1, 0, 2 };
		double y[] = { 2, 3, 1, 5 };

		double f[] = encontrarFatorespolinomiais(x, y);

		for (int i = 0; i < 3; i++)
			System.out.println(f[i]);
	}*/

}
