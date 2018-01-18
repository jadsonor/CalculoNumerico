package br.com.jor.metodos.lineares.diretos;


public class MetodoEliminacaoGauss implements MetodoDireto {

    @Override
    public double[] resolverSistema(double[][] matriz) {

        double pivo = 0, multiplicador, resultado = 0;
        int qtdCol, qtdLin, cont;
        double[] solucao;

        cont = 0;
        qtdLin = matriz.length;
        qtdCol = matriz[0].length;
        solucao = new double[qtdCol - 1];
        /*
         * Escalonamento da Matriz
         */
        // Iteração sobre os pivôs
        cont = 0;
        for (int x = 0; x < qtdLin; x++) {
            pivo = matriz[x][x];
            // Iteração sobre os elementos abaixo e na mesma coluna do pivô
            for (int y = x + 1; y < qtdLin; y++) {
                // Descobrindo o multiplicador da linha
                multiplicador = matriz[y][x] / pivo;
                // Iterando sobre as colunas da linha com o multiplicador atual
                for (int l = 0; l < qtdCol; l++) {
                    matriz[y][l] = matriz[y][l]
                            - (multiplicador * matriz[x][l]);
                }
            }
        }
        /*
         * Solução das equações
         */
        // Começando pela incognita da ultima linha
        solucao[qtdCol - 2] = matriz[qtdLin - 1][qtdCol - 1]
                / matriz[qtdLin - 1][qtdCol - 2];
        // marcando a primeira incógnita encontrada
        cont = qtdCol - 3;
        // Iterando as linhas acima da última
        for (int linha = qtdLin - 2; linha >= 0; linha--) {
            // separando o termo independente
            resultado = matriz[linha][qtdCol - 1];
            for (int coluna = linha; coluna < qtdCol; coluna++) {
                // isolando pivo, que desta vez é a incógnita a ser descoberta
                if (coluna == linha) {
                    pivo = matriz[linha][coluna];
                } // Agrupando os demais -> "Passando para o outro lado da igualdade, digamos"
                else if (coluna >= linha && coluna <= qtdLin - 1) {
                    if (matriz[linha][coluna] * solucao[coluna] >= 0) {
                        resultado = resultado
                                - (matriz[linha][coluna] * solucao[coluna]);
                    } else {
                        resultado = resultado
                                + (Math.abs(matriz[linha][coluna]
                                        * solucao[coluna]));
                    }
                }
            }
            solucao[cont] = resultado / pivo;
            cont--;
        }

        return solucao;
    }

}
