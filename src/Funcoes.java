/**
 * @author Lívia de Azevedo da Silva
 * Trabalho de Inteligência Artificial
 * Universidade Federal Rural do Rio de Janeiro
 * Tema: Aplicando o Minimax no Jogo da Velha
 * Período: 3º (2015-1)
 * 
 * O trabalho possui 4 arquivos .java:
 * 
 * -Main.java
 * -Tabuleiro.java
 * -Funcoes.java
 * -Minimax.java
 * 
 *
 * 
 * 
 */
public class Funcoes {

	public static void imprimirJogo(char[][]tabuleiro){
		int i,j;

		for(i = 0;i < 3;i++){
			for(j = 0;j < 3;j++){
				
				if(j < 2){
					if(tabuleiro[i][j] == '0'){
						System.out.print("   |");
					}else{
						System.out.print(" " + tabuleiro[i][j] + " |");
					}
				}else{
					if(tabuleiro[i][j] == '0'){
						System.out.print("   ");
					}else{
						System.out.print(" " + tabuleiro[i][j] + " ");
					}
				}
				
				if(j == 2){
					System.out.println();
					System.out.println("------------");
				}
			}
		}
	}

	public static boolean fimJogo(char[][]tabuleiro,char caracterAComparar){
		int i;
		boolean vitoriaLinha = false,vitoriaColuna = false,vitoriaDiagonal1 = false,vitoriaDiagonal2 = false;

		//Comparar as linhas
		for(i = 0;i < 3;i++){
			if(tabuleiro[i][0] == caracterAComparar && tabuleiro[i][1] == caracterAComparar && tabuleiro[i][2] == caracterAComparar){
				vitoriaLinha = true;
			}
		}

		//Comparar as colunas
		for(i = 0;i < 3;i++){
			if(tabuleiro[0][i] == caracterAComparar && tabuleiro[1][i] == caracterAComparar && tabuleiro[2][i] == caracterAComparar){
				vitoriaColuna = true;
			}
		}

		//Comparar as diagonais

		//Primeira diagonal
		if(tabuleiro[0][0] == caracterAComparar && tabuleiro[1][1] == caracterAComparar && tabuleiro[2][2] == caracterAComparar){
			vitoriaDiagonal1 = true;
		}

		//Segunda diagonal
		if(tabuleiro[0][2] == caracterAComparar && tabuleiro[1][1] == caracterAComparar && tabuleiro[2][0] == caracterAComparar){
			vitoriaDiagonal2 = true;
		}

		if(vitoriaLinha || vitoriaColuna || vitoriaDiagonal1 || vitoriaDiagonal2){
			return true;
		}else{
			return false;
		}
	}

	/*A funcao de avaliacao é calculada da seguinte maneira:
	
	Linha: Pegamos cada elemento(X ou O) do tabuleiro e calculamos quantas chances de vitória em linha ira-se ter a partir daquela posicao(soma-se +1 a cada chance).
	Coluna: Mesma ideia aplicada a linha, só que aplicada a coluna.
	Diagonal: Se a partir da posicao analisada ter chance de vitoria, ira somar mais 1. 
	
	*/
	public static int funcaoAvaliacao(char[][]tabuleiro,char caracterAcomparar){
		int i,j,k,l;
		int valorMinimax = 0;
		
		//Se o estado representar uma vitória para X...setaremos com o valor máximo.
		if(caracterAcomparar == 'X'){
			if(Funcoes.fimJogo(tabuleiro,'X')){
				return 100;
			}
		}

		//Se o estado representar uma vitória para O...setaremos com o valor mínimo.
		if(caracterAcomparar == 'O'){
			if(Funcoes.fimJogo(tabuleiro,'O')){
				return -100;
			}
		}

		//Comparando a linha	
		for(i = 0;i < 3;i++){
			for(j = 0;j < 3;j++){
				if(tabuleiro[i][j] == caracterAcomparar){
					for(k = 0;k < 3;k++){
						if(tabuleiro[i][k] != tabuleiro[i][j]){
							if(caracterAcomparar == 'X'){
								if(tabuleiro[i][k] == 'O'){
									break;
								}
							}
							if(caracterAcomparar == 'O'){
								if(tabuleiro[i][k] == 'X'){
									break;
								}
							}
						}
						//Se chegamos ao final do for e não entramos no if do break, então é uma possibilidade de vitoria!
						if(k == 2){
							valorMinimax++;
						}
					}
					break;
				}
			}
		}
	
		//Comparando a coluna
		for(j = 0;j < 3;j++){
			for(i = 0;i < 3;i++){
				if(tabuleiro[i][j] == caracterAcomparar){
					for(k = 0;k < 3;k++){
						if(tabuleiro[k][j] != tabuleiro[i][j]){
							if(caracterAcomparar == 'X'){
								if(tabuleiro[k][j] == 'O'){
									break;
								}
							}
							if(caracterAcomparar == 'O'){
								if(tabuleiro[k][j] == 'X'){
									break;
								}
							}
						}
						//Se chegamos ao final do for e não entramos no if do break, então é uma possibilidade de vitoria!
						if(k == 2){
							valorMinimax++;
						}
					}
					break;
				}
			}
		}

		//Comparando diagonais
		
		//Primeira
		for(i = 0,j = 0;i < 3;i++,j++){
			if(tabuleiro[i][j] == caracterAcomparar){
				for(k = 0,l = 0;k < 3;k++,l++){
					if(tabuleiro[k][l] != tabuleiro[i][j]){
						if(caracterAcomparar == 'X'){
							if(tabuleiro[k][l] == 'O'){
								break;
							}
						}
						if(caracterAcomparar == 'O'){
							if(tabuleiro[k][l] == 'X'){
								break;
							}
						}
					}
					//Se chegamos ao final do for e não entramos no if do break, então é uma possibilidade de vitoria!
					if(k == 2){
						valorMinimax++;
					}
				}
			}
		}

		
	
		//Segunda
		for(i = 0,j = 2;i < 3;i++,j--){
			if(tabuleiro[i][j] == caracterAcomparar){
				for(k = 0,l = 2;k < 3;k++,l--){
					if(tabuleiro[k][l] != tabuleiro[i][j]){
						if(caracterAcomparar == 'X'){
							if(tabuleiro[k][l] == 'O'){
								break;
							}
						}
						if(caracterAcomparar == 'O'){
							if(tabuleiro[k][l] == 'X'){
								break;
							}
						}
					}
					//Se chegamos ao final do for e não entramos no if do break, então é uma possibilidade de vitoria!
					if(k == 2){
						valorMinimax++;
					}
				}
			}
		}

		if(caracterAcomparar == 'X'){
			return valorMinimax;
		}else{
			return -1 * valorMinimax;
		}
	}	
}
