package br.com.jor.metodos.lineares.iterativos;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Jadson - Classe responsável por realizar as iterações de um
 *         MetodoIterativo
 *
 */
public final class MetodoIterativoBuilder {

	/**
	 * 
	 * @param metodoIterativo - O método a ser utilizado para realizar a iteração
	 * @param sistema - representação do sistema linear
	 * @param solucaoInicial - valores para x0
	 * @param precisao - a precisão esperada
	 * @return Todas as iterações realizadas até a solução final, segundo a precisão escolhida
	 */
	public static List<double[]> resolverSistema(MetodoIterativo metodoIterativo, double[][] sistema, double[] solucaoInicial, double precisao) {

		List<double[]> iteracoes = new ArrayList<double[]>();

		double[] solucaoAtual, solucaoAnterior;
		double erro;

		solucaoAnterior = solucaoInicial;
		solucaoAtual = metodoIterativo.calcularProximo(sistema, solucaoInicial);
		erro = 100;

		iteracoes.add(solucaoAnterior);
		iteracoes.add(solucaoAtual);

		
		while (erro >= precisao) {
			solucaoAnterior = solucaoAtual;
			solucaoAtual = metodoIterativo.calcularProximo(sistema, solucaoAtual);
			erro = MetodoIterativo.calcularErro(solucaoAtual, solucaoAnterior);
			iteracoes.add(solucaoAtual);
		}

		return iteracoes;
	}

}
