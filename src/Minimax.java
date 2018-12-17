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

import java.util.ArrayList;

public class Minimax {
	
	public static void JogadaMaquina(char[][]tabuleiro,boolean vezMaquina,int casasNaoPreenchidas){
		
		ArrayList<Tabuleiro>estadosPossiveis = new ArrayList<Tabuleiro>();
		boolean[][]jaPreenchido = {{true,true,true},{true,true,true},{true,true,true}};
		int i,j;
		char[][]copiaTabuleiro = new char[3][3];
		int max = -10000; //Para que possamos atribuir o primeiro valor comparado posteriormente a variavel max.
		int melhorEstado = 0;
		int valorMinimax = 0;
		boolean estadoInicialVitoria = false;
				
		for(i = 0;i < 3;i++){
			for(j = 0;j < 3;j++){
				copiaTabuleiro[i][j] = tabuleiro[i][j];
			}
		}
	
		for(i = 0;i < 3;i++){
			for(j = 0;j < 3;j++){
				if(tabuleiro[i][j] == '0'){
					jaPreenchido[i][j] = false;
				}
			}
		}
		
			for(i = 0;i < 3;i++){
				for(j = 0;j < 3;j++){
					if(jaPreenchido[i][j] == false){
							copiaTabuleiro[i][j] = 'X';
							estadosPossiveis.add(new Tabuleiro(copiaTabuleiro));
							copiaTabuleiro[i][j] = '0';
							jaPreenchido[i][j] = true;
					}
				}
			}
			
			//Calculando a avaliação
			for(i = 0;i < casasNaoPreenchidas;i++){
				//Se em algum estado tivermos a vitoria da Máquina, esse estado sera a proxima jogada automaticamente!
				if(Funcoes.fimJogo(estadosPossiveis.get(i).getEstadoTabuleiro(),'X')){
					melhorEstado = i;
					estadoInicialVitoria = true;
					break;
				}

				valorMinimax = Funcoes.funcaoAvaliacao(estadosPossiveis.get(i).getEstadoTabuleiro(), 'X');

				if(valorMinimax == 100){
					estadosPossiveis.get(i).setValorMinimax(valorMinimax);
					estadosPossiveis.get(i).setAbrirArvore(false);
				}

				if(estadosPossiveis.get(i).getAbrirArvore()){
					valorMinimax += Minimax.minimaxIA(estadosPossiveis.get(i).getEstadoTabuleiro(),false, casasNaoPreenchidas - 1);
					estadosPossiveis.get(i).setValorMinimax(valorMinimax);
					valorMinimax = 0;
				}
			}
			
			if(!estadoInicialVitoria){
				//Verificar o max dos nós do estado correspondente
				for(i = 0;i < casasNaoPreenchidas;i++){
					if(estadosPossiveis.get(i).getValorMinimax() > max){
						max = estadosPossiveis.get(i).getValorMinimax();
						melhorEstado = i;
					}
				}
			}
			//Atribuição da jogada no tabuleiro
			for(i = 0;i < 3;i++){
				for(j = 0;j < 3;j++){
					tabuleiro[i][j] = estadosPossiveis.get(melhorEstado).getEstadoTabuleiroElemento(i,j);
				}
			}
	}
	
	public static int minimaxIA(char[][]tabuleiro,boolean vezMaquina,int casasNaoPreenchidas){
		
		ArrayList<Tabuleiro>estadosPossiveis = new ArrayList<Tabuleiro>();
		boolean[][]jaPreenchido = {{true,true,true},{true,true,true},{true,true,true}};
		int i,j;
		char[][]copiaTabuleiro = new char[3][3];
		boolean MAX,MIN; //MAX corresponde a jogada da Máquina e MIN a jogada do jogador.
		int max = -10000,min = 10000; //Para que possamos atribuir o primeiro valor comparado posteriormente a variavel max e min.
		int valorMinimax = 0;
		
		if(vezMaquina){
			MAX = true;
			MIN = false;
		}else{
			MAX = false;
			MIN = true;
		}
		
		for(i = 0;i < 3;i++){
			for(j = 0;j < 3;j++){
				copiaTabuleiro[i][j] = tabuleiro[i][j];
			}
		}
	
		for(i = 0;i < 3;i++){
			for(j = 0;j < 3;j++){
				if(tabuleiro[i][j] == '0'){
					jaPreenchido[i][j] = false;
				}
			}
		}
		
			for(i = 0;i < 3;i++){
				for(j = 0;j < 3;j++){
					if(jaPreenchido[i][j] == false){
						if(vezMaquina){
							copiaTabuleiro[i][j] = 'X';
							estadosPossiveis.add(new Tabuleiro(copiaTabuleiro));
							copiaTabuleiro[i][j] = '0';
							jaPreenchido[i][j] = true;
						}else{
							copiaTabuleiro[i][j] = 'O';
							estadosPossiveis.add(new Tabuleiro(copiaTabuleiro));
							copiaTabuleiro[i][j] = '0';
							jaPreenchido[i][j] = true;
						}
						
					}
				}
			}
			
			if(casasNaoPreenchidas - 1 == 0){
				if(MAX){			
					return Funcoes.funcaoAvaliacao(estadosPossiveis.get(0).getEstadoTabuleiro(), 'X');
				}
				
				if(MIN){
					return Funcoes.funcaoAvaliacao(estadosPossiveis.get(0).getEstadoTabuleiro(), 'O');
				}
			}

			//Calculando a avaliação
			for(i = 0;i < casasNaoPreenchidas;i++){
				if(MAX){
					valorMinimax = Funcoes.funcaoAvaliacao(estadosPossiveis.get(i).getEstadoTabuleiro(), 'X');
					
						if(valorMinimax == 100){
							estadosPossiveis.get(i).setValorMinimax(valorMinimax);
							estadosPossiveis.get(i).setAbrirArvore(false);
						}
						
						if(estadosPossiveis.get(i).getAbrirArvore()){
							valorMinimax += Minimax.minimaxIA(estadosPossiveis.get(i).getEstadoTabuleiro(),false, casasNaoPreenchidas - 1);
							estadosPossiveis.get(i).setValorMinimax(valorMinimax);
							valorMinimax = 0;
						}
				}
				
				if(MIN){	
					valorMinimax = Funcoes.funcaoAvaliacao(estadosPossiveis.get(i).getEstadoTabuleiro(), 'O');
					
						if(valorMinimax == -100){
							estadosPossiveis.get(i).setValorMinimax(valorMinimax);
							estadosPossiveis.get(i).setAbrirArvore(false);
						}
						
						if(estadosPossiveis.get(i).getAbrirArvore()){
							valorMinimax += Minimax.minimaxIA(estadosPossiveis.get(i).getEstadoTabuleiro(),true, casasNaoPreenchidas - 1);
							estadosPossiveis.get(i).setValorMinimax(valorMinimax);
							valorMinimax = 0;
						}
				}
			}

			//Verificar o max ou min dos nós do estado correspondente
			for(i = 0;i < casasNaoPreenchidas;i++){
				if(MAX){
					if(estadosPossiveis.get(i).getValorMinimax() > max){
						max = estadosPossiveis.get(i).getValorMinimax();
					}
				}
				
				if(MIN){
					if(estadosPossiveis.get(i).getValorMinimax() < min){
						min = estadosPossiveis.get(i).getValorMinimax();
					}
				}
			}
			
			if(MAX){
				return max;
			}else{
				return min;
			}		
	}		
}