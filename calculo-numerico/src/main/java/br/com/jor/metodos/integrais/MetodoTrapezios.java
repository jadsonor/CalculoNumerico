package br.com.jor.metodos.integrais;

public class MetodoTrapezios {
	
	public static double calcular(double[] valoresX, double[] valoresFx){
		double fx0, fxn, h, resultado, somaIntermediaria;
		int n;
		
		n = valoresX.length;
		somaIntermediaria = 0;
		
		h = (valoresX[n-1] - valoresX[0])/(n-1);
		
		for(int x = 1; x < n-1; x++){
			somaIntermediaria += valoresFx[x];
		}
		
		fx0 = valoresFx[0];
		fxn = valoresFx[n-1];
		
		resultado = h/2 * (fx0 + 2*somaIntermediaria + fxn);
		
		
		return resultado;
		
	}

}
