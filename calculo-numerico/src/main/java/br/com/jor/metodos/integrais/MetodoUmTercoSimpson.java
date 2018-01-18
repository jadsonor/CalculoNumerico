package br.com.jor.metodos.integrais;

public class MetodoUmTercoSimpson {

	public static double calcular(double[] valoresX, double[] valoresFx) {
		
		double fx0, fxn, h, resultado, somaIntermediaria;
		int n;
		
		n = valoresX.length;
		somaIntermediaria = 0;
		
		h = (valoresX[n-1] - valoresX[0])/(n-1);
		
		for(int x = 1; x < n-1; x++){
			if(x%2 == 0)
				somaIntermediaria += (4*valoresFx[x]);
			else
				somaIntermediaria += (2*valoresFx[x]);
				
		}
		
		fx0 = valoresFx[0];
		fxn = valoresFx[n-1];
		
		resultado = h/3 * (fx0 + somaIntermediaria + fxn);
		
		
		return resultado;
	}
	
	

}
