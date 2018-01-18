package br.com.jor.metodos.lineares.diretos;

public interface MetodoDireto {

    /**
     * @param matriz a matriz que representa o sistema de equação linear
     * @return vetor com a solução para o sistema
     *
     * *
     */
    double[] resolverSistema(double[][] matriz);

}
