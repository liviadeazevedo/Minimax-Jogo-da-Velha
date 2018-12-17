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

public class Tabuleiro {
	
	private char[][]estadoTabuleiro = new char[3][3];
	private boolean abrirArvore;
	private int valorMinimax = 0;
	
	public Tabuleiro(char[][]tabuleiro){
		int i,j;
		
		for(i = 0;i < 3;i++){
			for(j = 0;j < 3;j++){
				this.estadoTabuleiro[i][j] = tabuleiro[i][j];
			}
		}
		this.abrirArvore = true;
	}
	
	public boolean getAbrirArvore(){
		return abrirArvore;
	}
	
	public void setAbrirArvore(boolean b){
		this.abrirArvore = b;
	}
		
	public int getValorMinimax(){
		return valorMinimax;
	}
	
	public void setValorMinimax(int valorMinimax){
		this.valorMinimax = valorMinimax;
	}
	
	public char getEstadoTabuleiroElemento(int posicaoX,int posicaoY){
		return estadoTabuleiro[posicaoX][posicaoY];
	}
	
	public char[][] getEstadoTabuleiro(){
		return estadoTabuleiro;
	}
}
