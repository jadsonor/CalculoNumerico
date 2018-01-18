package br.com.jor.metodos.lineares.iterativos;

import java.util.Arrays;

/**
 * 
 * @author Jadson - Classe que implementa o método de Gauss-Seidel para a solução de um sistema linear.
 *
 */
public class MetodoGaussSeidel extends MetodoIterativo {
	
	@Override
	public double[] calcularProximo(double[][] matriz, double[] solucaoAtual) {

		double multiplicadorIncognita = 0;
		double[] proximaSolucao = new double[solucaoAtual.length];

		// Inicializando todas as posições do vetor
		Arrays.fill(proximaSolucao, -1000);
		// Variável para armazenar todos os valores que ficarão isolados de xn
		double outroLadoDaIgualdade;
		double aux = 0;

		for (int l = 0; l < matriz.length; l++) {
			// Isolando o valor de x
			multiplicadorIncognita = matriz[l][l];
			outroLadoDaIgualdade = 0;
			// passando todos os outros valores do outro lado da igualdade
			for (int c = 0; c < matriz[0].length - 1; c++) {
				/*
				 * Exceto a posição da incógnita atual, todas as outras são
				 * passadas para o outro lado da igualdade que nada mais é que
				 * inverter o sinal, dividir pela constante do elemento isolado
				 * e multiplicar pelo valor desta incógnita no vetor atual
				 */
				if (c != l) {
					aux = (matriz[l][c] / multiplicadorIncognita);
					aux = -(aux);
					/*
					 * Se caso o valor desta incóginita já foi descoberto na
					 * iteração anterior, usamos o valor na iterção atual, o
					 * valor -1000 é um valor usado para marcar se este valor
					 * ainda não foi encontrado.
					 * 
					 */
					if (l > 0 && proximaSolucao[c] != -1000) {
						aux = aux * proximaSolucao[c];
					} else {
						aux = aux * solucaoAtual[c];
					}
					outroLadoDaIgualdade += aux;
				}
			}
			/*
			 * A ultima posicao de uma linha é o termo independente, que também
			 * é incluido na somatória
			 */
			outroLadoDaIgualdade += matriz[l][matriz[0].length - 1] / multiplicadorIncognita;
			proximaSolucao[l] = outroLadoDaIgualdade;

		}

		return proximaSolucao;
	}

}
