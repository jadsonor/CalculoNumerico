package br.com.jor.metodos.minimosquadrados;


import br.com.jor.metodos.lineares.diretos.MetodoEliminacaoGauss;

public class MetodoRegressaoLinear {
	
	private static double[][] encontrarSistemaLinear(double[] valoresX, double[] valoresY){
		 
		double[][] sistema = new double[2][3];
		
		double qtdPontos = valoresX.length;
		
		double somatorioX = 0;
		
		double somatorioY = 0;
		
		double somatorioProdXY = 0;
		
		double somatorioXQuad = 0;
		
		for(int i = 0; i < valoresX.length; i++){
			somatorioY += valoresY[i];
			somatorioX += valoresX[i];
			somatorioProdXY += valoresX[i] * valoresY[i];
			somatorioXQuad += Math.pow(valoresX[i], 2);
		}
		
		
		sistema[0][0] = qtdPontos;
		sistema[0][1] = somatorioX;
		sistema[0][2] = somatorioY;
		
		sistema[1][0] = somatorioX;
		sistema[1][1] = somatorioXQuad;
		sistema[1][2] = somatorioProdXY;
		
		return sistema;
	}
	
	public static String encontrarFuncao(double[] valoresX, double[] valoresY){
		
		double[][] sistema = encontrarSistemaLinear(valoresX, valoresY);
		String funcao = "";

		MetodoEliminacaoGauss meg = new MetodoEliminacaoGauss();
		double[]  s = meg.resolverSistema(sistema);
		
		funcao = String.format("%.2fx + %.2f", s[1],s[0]);
				
		return funcao;
	}

}

