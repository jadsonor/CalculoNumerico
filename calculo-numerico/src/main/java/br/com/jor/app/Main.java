package br.com.jor.app;

import br.com.jor.metodos.aproximacao.InterpolacaoDeLagrange;
import br.com.jor.metodos.aproximacao.InterpolacaoPolinomial;
import br.com.jor.metodos.integrais.MetodoTrapezios;
import br.com.jor.metodos.integrais.MetodoUmTercoSimpson;
import br.com.jor.metodos.lineares.diretos.MetodoEliminacaoGauss;
import br.com.jor.metodos.lineares.diretos.MetodoGaussJordan;
import br.com.jor.metodos.lineares.iterativos.MetodoGaussJacobi;
import br.com.jor.metodos.lineares.iterativos.MetodoGaussSeidel;
import br.com.jor.metodos.lineares.iterativos.MetodoIterativoBuilder;
import br.com.jor.metodos.minimosquadrados.MetodoRegressaoLinear;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Jadson - Classe principal da aplicação
 */
public class Main {

	private static final int SAIR = 10;
	private static final int QTD_COLUNAS = 4;
	private static final int QTD_LINHAS = 3;
	private static final Scanner scan = new Scanner(System.in);;

	public static void main(String[] args) {
		MetodoGaussSeidel metodoGaussSeidel = new MetodoGaussSeidel();
		MetodoGaussJacobi metodoGaussJacobi = new MetodoGaussJacobi();

		int opc = 0, cont;

		double[] valoresX, valoresFx, solucao, x0, fatoresPolinomiais;
		double[][] valores, matriz;
		
		String funcao, sinal;

		double resultado, precisao;

		List<double[]> iteracoes;

		while (opc != SAIR) {
			exibirMenu();
			opc = scan.nextInt();
			switch (opc) {
			case 1:
				MetodoEliminacaoGauss metodoEliminacaoGauss = new MetodoEliminacaoGauss();
				System.out.println("########## Método da eliminação de Gauss ##########");
				matriz = lerMatriz();

				System.out.println("Matriz lida:");
				imprimirMatriz(matriz);

				solucao = metodoEliminacaoGauss.resolverSistema(matriz);
				System.out.println("Matriz final:");
				imprimirMatriz(matriz);

				imprimirSolucao(solucao);
				break;
			case 2:
				MetodoGaussJordan metodoGaussJordan = new MetodoGaussJordan();
				System.out.println("########## Método de Gauss-Jordan ##########");
				matriz = lerMatriz();

				System.out.println("Matriz lida:");
				imprimirMatriz(matriz);

				solucao = metodoGaussJordan.resolverSistema(matriz);
				System.out.println("Matriz final:");
				imprimirMatriz(matriz);

				imprimirSolucao(solucao);
				break;
			case 3:
				System.out.println("########## Método de Gauss-Jacobi ##########");

				matriz = lerMatriz();

				x0 = new double[3];
				Arrays.fill(x0, 0);

				System.out.println("Digite os valores para x0 (na mesma linha separados por espaço):");
				for (int x = 0; x < 3; x++) {
					x0[x] = scan.nextInt();
				}
				System.out.println("Digite a precisao: ");
				precisao = scan.nextDouble();
				iteracoes = MetodoIterativoBuilder.resolverSistema(metodoGaussJacobi, matriz, x0, precisao);
				imprimirIteracoes(iteracoes);
				break;
			case 4:
				System.out.println("########## Método de Gauss-Seidel ##########");
				matriz = lerMatriz();

				x0 = new double[3];
				Arrays.fill(x0, 0);

				System.out.println("Digite os valores para x0 (na mesma linha separados por espaço):");
				for (int x = 0; x < 3; x++) {
					x0[x] = scan.nextInt();
				}
				System.out.println("Digite a precisao: ");
				precisao = scan.nextDouble();
				iteracoes = MetodoIterativoBuilder.resolverSistema(metodoGaussSeidel, matriz, x0, precisao);
				imprimirIteracoes(iteracoes);
				break;

			case 5:
				System.out.println("########## Método de Interpolação Polinomial ##########");
				valores = lerPontos();
				valoresX = valores[0];
				valoresFx = valores[1];

				funcao = InterpolacaoPolinomial.encontrarFuncao(valoresX, valoresFx);

				System.out.println("Função: " + funcao);

				break;
				
			case 6:
				valores = lerPontos();
				valoresX = valores[0];
				valoresFx = valores[1];
				
				fatoresPolinomiais = InterpolacaoDeLagrange.encontrarFatorespolinomiais(valoresX, valoresFx);
				cont = fatoresPolinomiais.length - 1;
				funcao = "";
				
				for(double fator : fatoresPolinomiais){
					sinal = (fator > 0 && cont != fatoresPolinomiais.length - 1) ? "+" : "";
					if(cont > 1)
						funcao += String.format("%s %.2fx ^ %d ",sinal, fator,cont);
					else
						if(cont == 1)
							funcao += String.format("%s %.2fx ",sinal, fator);
						else if(cont == 0)
							funcao += String.format("%s %.2f ",sinal,fator);
					cont --;
				}
				
				System.out.println("Função: " + funcao);
				System.out.println();
				
				
				break;

			case 7:
				System.out.println("########## Método dos trapézios ##########");
				valores = lerPontos();
				valoresX = valores[0];
				valoresFx = valores[1];

				resultado = MetodoTrapezios.calcular(valoresX, valoresFx);

				System.out.printf("Resultado: %.4f\n\n", resultado);

			case 8:
				System.out.println("########## Método de 1/3 de Simpson ##########");
				valores = lerPontos();
				valoresX = valores[0];
				valoresFx = valores[1];

				resultado = MetodoUmTercoSimpson.calcular(valoresX, valoresFx);

				System.out.printf("Resultado: %.4f\n\n", resultado);

				break;

			case 9:
				System.out.println("########## Método de regressão linear ##########");

				valores = lerPontos();
				valoresX = valores[0];
				valoresFx = valores[1];

				System.out.println("Função: " + MetodoRegressaoLinear.encontrarFuncao(valoresX, valoresFx));

				break;
			case 10:
				scan.close();
				System.out.println("Programa encerrado.");
				break;
			default:
				System.out.println("Opção inválida.");
				break;
			}
		}

	}

	private static void exibirMenu() {

		System.out.println(" _ Métodos para resolução de sistemas lineares _");
		System.out.println("|                                               |");
		System.out.println("| Métodos Diretos                               |");
		System.out.println("|                                               |");
		System.out.println("| 1 - Método da Eliminação de Gauss             |");
		System.out.println("| 2 - Método de Gauss-Jordan                    |");
		System.out.println("|                                               |");
		System.out.println("| Métodos Iterativos                            |");
		System.out.println("|                                               |");
		System.out.println("| 3 - Método de Gauss-Jacobi                    |");
		System.out.println("| 4 - Método de Gauss-Seidel                    |");
		System.out.println("|_______________________________________________|");
		System.out.println();
		System.out.println(" _ Métodos Interpolação Polinomial _____________");
		System.out.println("|                                               |");
		System.out.println("| 5 - Método de Interpolação Polinomial         |");
		System.out.println("| 6 - Método de Lagrange                        |");
		System.out.println("|_______________________________________________|");
		System.out.println();
		System.out.println(" _ Métodos de resolução de integrais ___________");
		System.out.println("|                                               |");
		System.out.println("| 7 - Método dos trapézios                      |");
		System.out.println("| 8 - Método de 1/3 de Simpson                  |");
		System.out.println("|_______________________________________________|");
		System.out.println();
		System.out.println(" _ Método dos mínimos quadrados ________________");
		System.out.println("|                                               |");
		System.out.println("| 9 - Método de regressão linear                |");
		System.out.println("|_______________________________________________|");
		System.out.println();
		System.out.println(" 10  - Sair ");
		System.out.println();
		System.out.println("Selecione uma opção: ");

	}

	private static double[][] lerMatriz() {

		double[][] matriz = new double[QTD_LINHAS][QTD_COLUNAS];
		System.out.println();
		System.out.printf("########## Matriz %dx%d ##########\n", QTD_LINHAS, QTD_COLUNAS);
		System.out.println();
		System.out.println("Entre com os valores:");

		for (int l = 0; l < QTD_LINHAS; l++) {
			for (int c = 0; c < QTD_COLUNAS; c++) {
				System.out.printf("Célula %d-%d:\n", l, c);
				matriz[l][c] = scan.nextDouble();
			}
		}
		System.out.println();

		return matriz;
	}

	public static void imprimirMatriz(double[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void imprimirSolucao(double[] vetor) {
		System.out.print("Solução: ( ");
		for (int i = 0; i < vetor.length; i++) {
			System.out.printf("%.4f%s", vetor[i], (i == vetor.length - 1 ? " " : "; "));
		}
		System.out.print(")\n");
	}

	private static void imprimirIteracoes(List<double[]> iteracoes) {
		System.out.println("Iterações: ");

		for (double[] iteracao : iteracoes) {
			if (iteracoes.indexOf(iteracao) != iteracoes.size() - 1) {
				System.out.print("\nx" + iteracoes.indexOf(iteracao));
				System.out.print("( ");
				for (int x = 0; x < iteracao.length; x++) {
					System.out.printf("%.4f%s", iteracao[x], (x == iteracao.length - 1 ? " " : "; "));
				}
				System.out.print(")\n");
			} else {
				System.out.println();
				System.out.printf("Solução: (%.4f, %.4f, %.4f)\n", iteracao[0], iteracao[1], iteracao[2]);
			}
		}

	}

	private static double[][] lerPontos() {
		int qtdPontos;
		double[][] valores;
		System.out.println();
		System.out.println("Digite a quantidade de pontos: ");
		qtdPontos = scan.nextInt();

		valores = new double[2][qtdPontos];

		for (int c = 0; c < qtdPontos; c++) {
			System.out.printf("Digite o valor de x%d: ", c);
			valores[0][c] = scan.nextDouble();

			System.out.printf("Digite o valor de f(x%d): ", c);
			valores[1][c] = scan.nextDouble();

			System.out.println();
		}

		return valores;
	}

}
