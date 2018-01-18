package br.com.jor.metodos.aproximacao;

import br.com.jor.metodos.lineares.diretos.MetodoGaussJordan;

import java.util.ArrayDeque;
import java.util.Deque;

public class InterpolacaoPolinomial {

	public static String encontrarFuncao(double[] valoresX, double[] valoresFx) {
		double[][] matriz = null;

		int cont;

		String funcao;
		String[] split;

		double[] solucao;

		int qtdPontos = valoresX.length;
		matriz = new double[qtdPontos][qtdPontos + 1];

		for (int l = 0; l < matriz.length; l++) {
			for (int c = 1; c < matriz[0].length; c++) {
				matriz[l][c] = Math.pow(valoresX[l], c);
			}
		}

		for (int i = 0; i < matriz.length; i++) {
			matriz[i][0] = 1;
		}

		for (int i = 0; i < matriz.length; i++) {
			matriz[i][matriz.length] = valoresFx[i];

		}

		solucao = new MetodoGaussJordan().resolverSistema(matriz);
		
		funcao = "";
		cont = 0;
		
		for (int i = 0; i < solucao.length; i++) {
			solucao[i] = Double.parseDouble(String.format("%.2f", solucao[i]).replaceAll(",", "."));
		}

		for (int x = 0; x < solucao.length; x++) {
			funcao += "@" + solucao[x] + ((cont != 0) ? "ELV" + cont : "");
			cont++;
		}

		split = funcao.split("@");

		funcao = montarExpressao(split);

		return funcao;

	}

	public static String montarExpressao(String[] split) {
		String expressao = "";
		String bloco;
		String[] baseEPotencia;
		boolean flag = false;
		Double vlr;
		boolean primeiro;

		Deque<String> stack = new ArrayDeque<String>();

		for (String s : split) {
			s = s.replaceAll(",", ".");
			stack.push(s);
		}

		primeiro = true;
		while (!stack.isEmpty()) {

			String s = stack.pop();

			bloco = "";

			s = s.trim();
			if (!s.isEmpty() && s != null && s != " ") {
				flag = s.contains("ELV");

				if (flag) {
					baseEPotencia = s.split("ELV");
					vlr = Double.valueOf(baseEPotencia[0]);
					if (vlr != 0) {
						bloco = ((vlr == 1.0) ? "x" : baseEPotencia[0] + "x")
								+ (Double.valueOf(baseEPotencia[1]) == 1 ? " " : " ^ " + baseEPotencia[1] + " ");

						if (vlr > 0 && !primeiro) {
							bloco = "+ " + bloco;
						}
					} else {
						bloco = "";
					}
				} else {
					if (Double.valueOf(s) != 0) {
						if (Double.valueOf(s) > 0) {
							bloco = "+ " + s;
						}
					} else {
						bloco = "";
					}
				}

				expressao += bloco;
			}
			primeiro = false;
		}


		return expressao;
	}

}
