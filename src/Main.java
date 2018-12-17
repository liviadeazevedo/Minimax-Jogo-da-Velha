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

	/*O jogo tera 'O' e 'X' como preenchimento.
	* O espaco vazio sera representado com o char '0'.
	*
	* Jogador: O
	* Máquina: X
	* 
	*/

import java.util.*;

public class Main {

	private static Scanner inicioJogo;
	private static Scanner jogadaDoJogadorX;
	private static Scanner jogadaDoJogadorY;

	public static void main(String[] args) {

		char[][]tabuleiro = {{'0','0','0'},
							 {'0','0','0'},
							 {'0','0','0'}};

		int casasNaoPreenchidas = 9;
		boolean vezMaquina;
		boolean jogadorComeca;
		boolean terminoJogo = false;
		int jogadaX,jogadaY;
		String quemComeca = new String();
		boolean selecao = false;

		inicioJogo = new Scanner(System.in);
		jogadaDoJogadorX = new Scanner(System.in);
		jogadaDoJogadorY = new Scanner(System.in);

		System.out.println("Você será o jogador 'O'");
		System.out.println("Você deseja ser o primeiro a jogar? (sim ou não)");
		quemComeca = inicioJogo.nextLine();

		if(quemComeca.equalsIgnoreCase("sim")){
			vezMaquina = false;
			jogadorComeca = true;
		}else{
			vezMaquina = true;
			jogadorComeca = false;
		}

		if(jogadorComeca){
			System.out.println("Selecione a posicao X da jogada:(0 a 2)");
			jogadaX = jogadaDoJogadorX.nextInt();
			System.out.println("Selecione a posicao Y da jogada:(0 a 2)");
			jogadaY = jogadaDoJogadorY.nextInt();
			tabuleiro[jogadaX][jogadaY] = 'O';
			casasNaoPreenchidas--;
			vezMaquina = true;
			System.out.println("Você jogou!");
		}

		while(terminoJogo == false){

			if(vezMaquina){
				Minimax.JogadaMaquina(tabuleiro, vezMaquina, casasNaoPreenchidas);
				casasNaoPreenchidas--;
				vezMaquina = false;

				System.out.println("Máquina jogou!");

				if(Funcoes.fimJogo(tabuleiro, 'X')){
					System.out.println("A Máquina venceu!");
					terminoJogo = true;
				}

				Funcoes.imprimirJogo(tabuleiro);


			}else{
				while(selecao == false){
					System.out.println("Selecione a posicao X da jogada:(0 a 2)");
					jogadaX = jogadaDoJogadorX.nextInt();
					System.out.println("Selecione a posicao Y da jogada:(0 a 2)");
					jogadaY = jogadaDoJogadorY.nextInt();

					if(tabuleiro[jogadaX][jogadaY] == '0'){
						tabuleiro[jogadaX][jogadaY] = 'O';
						selecao = true;
					}

					if(selecao == false){
						System.out.println("Por favor, selecione um espaço vazio");
						System.out.println();	
					}						
				}

				selecao = false;
				casasNaoPreenchidas--;
				vezMaquina = true;

				System.out.println("Você jogou!");

				if(Funcoes.fimJogo(tabuleiro, 'O')){
					System.out.println("Você venceu!");
					terminoJogo = true;
				}
			}

			if(casasNaoPreenchidas == 0 && terminoJogo == false){
				System.out.println("O jogo terminou em Velha");
				Funcoes.imprimirJogo(tabuleiro);
				terminoJogo = true;
			}

		}
	}
}
