/*
 * Rede neural faz a escolha dos movimentos do agente.
 * 
 * Retorna o que agente vai fazer e em que direcao.
 * Ex: pular, direita
 *  
 * 
 * */



//Exemplo - Rede Perceptron com apenas 1 neuronio (trab precisa de 4/5 neuronios)
import static java.lang.Math.*;
import java.util.Scanner;

public class Perceptron {
	private Neuronio neuronio;
	private int x1[]; // 1a entrada
	private int x2[]; // 2a entrada
	// precisa de mais duas
	private int x3[]; // 3a entrada
	private int x4[]; // 4a entrada

	private int d[]; // saida desejada

	public Perceptron() {
		neuronio = new Neuronio();
		x1 = new int[4];
		x2 = new int[4];
		x3 = new int[4];
		x4 = new int[4];
		d = new int[4];
		inicializacao();
	}

	private void inicializacao() {
		/*
		 * 1 - Buraco
		 * 2 - Moeda
		 * 3 - Parede
		 * 4 - " - "
		 * 
		 * */
		
		//Conjunto de Treino : OR
        x1[0] = 0;  x2[0] = 0;  d[0] = 0;
        x1[1] = 0;  x2[1] = 1;  d[1] = 1;
        x1[2] = 1;  x2[2] = 0;  d[2] = 1;
        x1[3] = 1;  x2[3] = 1;  d[3] = 1;
	}

	public void treinamento() { // algoritmo Regra Delta
		// Treinamento
		int epocas = 0, i;
		double y, erro, erroGeral, eta = 1; // eta eh a constante (taxa) de aprendizagem
		System.out.println("--- TREINAMENTO");
		while (true) {
			epocas++;
			erroGeral = 0;

			System.out.println("Epoca: " + epocas);
			for (i = 0; i < 4; i++) {
				// propagacao
				y = neuronio.calculaY(x1[i], x2[i],x3[i],x4[i]);
				// calcula do erro
				erro = d[i] - y;
				// ajuste dos pesos
				if (erro != 0) {
					neuronio.setW0(neuronio.getW0() + eta * erro);
					neuronio.setW1(neuronio.getW1() + eta * erro * x1[i]);
					neuronio.setW2(neuronio.getW2() + eta * erro * x2[i]);
					neuronio.setW3(neuronio.getW3() + eta * erro * x3[i]);
					neuronio.setW4(neuronio.getW4() + eta * erro * x4[i]);
				}
				System.out.println("Neuronio - pesos: " + neuronio);
				erroGeral = erroGeral + abs(erro);
			}
			// para quando para todas as entradas o erro for zero
			if (erroGeral == 0)
				break;
		}
	}

	public void generalizacao() { // uso da rede
		// Generalizacao - Teste da rede
		int entrada1, entrada2, entrada3, entrada4;
		Scanner dados = new Scanner(System.in);
		System.out.println("\n--- GENERALIZACAO");
		while (true) {
			
			/*
			 * Entradas (relativas a percepcao do agente):
			 * 1 - Cima
			 * 2 - Baixo
			 * 3 - Esquerda
			 * 4 - Direita
			 * 
			 * */
			
			
			// digita novas entradas
			System.out.println("Digite -100 para encerrar");
			System.out.print("Digite a entrada (x1): ");
			entrada1 = dados.nextInt();
			if (entrada1 == -100)
				break;

			System.out.print("Digite a entrada (x2): ");
			entrada2 = dados.nextInt();
			if (entrada2 == -100)
				break;

			System.out.print("Digite a entrada (x3): ");
			entrada3 = dados.nextInt();
			if (entrada3 == -100)
				break;

			System.out.print("Digite a entrada (x4): ");
			entrada4 = dados.nextInt();
			if (entrada4 == -100)
				break;

			// propagacao
			System.out.println("Saida Gerada pela rede: " + neuronio.calculaY(entrada1, entrada2, entrada3, entrada4));
		}
	}
}
