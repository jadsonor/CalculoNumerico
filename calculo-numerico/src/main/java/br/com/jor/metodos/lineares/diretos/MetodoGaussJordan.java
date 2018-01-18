package br.com.jor.metodos.lineares.diretos;


public class MetodoGaussJordan implements MetodoDireto {

    public double[] resolverSistema(double[][] matriz) {
        double pivo = 0, multiplicador;
        int qtdCol, qtdLin;
        double[] solucao;
        
        qtdLin = matriz.length;
        qtdCol = matriz[0].length;
        solucao = new double[qtdCol - 1];

        for (int x = 0; x < qtdLin; x++) {
            // Iterando sobre os pivôs
            pivo = matriz[x][x];
            for (int y = 0; y < qtdLin; y++) {
                // Descobrindo os multiplicadores da linha
                multiplicador = matriz[y][x] / pivo;
                // Iterando sobre todos os elementos de uma determinada linha 
                for (int l = 0; l < qtdCol; l++) {
                    if (y != x) {
                        matriz[y][l] = matriz[y][l]
                                - (multiplicador * matriz[x][l]);
                    }

                }
            }
        }

        for (int x = 0; x < qtdLin; x++) {
            solucao[x] = (matriz[x][qtdCol - 1]) / (matriz[x][x]);
        }
        return solucao;
    }

}
