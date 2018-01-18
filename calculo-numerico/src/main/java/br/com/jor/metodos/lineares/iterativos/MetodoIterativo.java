package br.com.jor.metodos.lineares.iterativos;

public abstract class MetodoIterativo {

	/**
	 * 
	 * @param matriz - representação do sistema linear
	 * @param solucaoAtual - valores das incógnitas para a iteração atual
	 * @return os próximos valores das incógnitas
	 */
	public abstract double[] calcularProximo(double[][] matriz, double[] solucaoAtual);

	/**
	 * *
	 * @param valoresAtuais - os valores das incógnitas da iteração atual 
	 * @param valoresAnteriores - os valores das incógnitas da iteração anterior
	 * @return o valor do erro(dr)
	 */
	public static double calcularErro(double[] valoresAtuais, double[] valoresAnteriores) {

		int tam = valoresAtuais.length;
		double[] diferencas = new double[tam];
		double erro, maiorDif, maiorAtual;

		for (int x = 0; x < tam; x++) {
			diferencas[x] = Math.abs(valoresAtuais[x]) - Math.abs(valoresAnteriores[x]);
		}

		// descobrindo o maior das diferenças
		maiorDif = diferencas[0];
		for (double n : diferencas) {
			if (Math.abs(n) > Math.abs(maiorDif))
				maiorDif = n;
		}

		// descobrindo o maior dos valores atuais
		maiorAtual = valoresAtuais[0];
		for (double n : valoresAtuais) {
			if (Math.abs(n) > Math.abs(maiorAtual))
				maiorAtual = n;
		}

		erro = Math.abs(maiorDif) / Math.abs(maiorAtual);

		return erro;
	}

}
