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

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Perceptron {
	
	private Camada camadaEntrada = new Camada();
	private Camada camadaSaida = new Camada();
	private int pontuacao=0;

	public Perceptron() {
		camadaEntrada.addNeuronio(new Neuronio("n1"));
		camadaEntrada.addNeuronio(new Neuronio("n2"));
		camadaEntrada.addNeuronio(new Neuronio("n3"));
		camadaEntrada.addNeuronio(new Neuronio("n4"));
		
		camadaSaida.addNeuronio(new Neuronio("n5"));
		camadaSaida.addNeuronio(new Neuronio("n6"));
		camadaSaida.addNeuronio(new Neuronio("n7"));
		camadaSaida.addNeuronio(new Neuronio("n8"));
		camadaSaida.addNeuronio(new Neuronio("n9"));
		camadaSaida.addNeuronio(new Neuronio("n10"));
	}


	public int[] defineAcao(int x1, int x2, int x3, int x4) {
		double y, erro, s1, s2, s3, s4, s5, s6;
		int  y1, y2, y3, y4;
		
		for(Neuronio neuronio: camadaEntrada.getNeuronios()) {
			neuronio.calculaY(x1, x2, x3, x4);
		}
		y1 = camadaEntrada.getNeuronios().get(0).getY();
		y2 = camadaEntrada.getNeuronios().get(1).getY();
		y3 = camadaEntrada.getNeuronios().get(2).getY();
		y4 = camadaEntrada.getNeuronios().get(3).getY();
		for(Neuronio neuronio: camadaSaida.getNeuronios()) {
			neuronio.calculaY(y1, y2, y3, y4);
		}
		camadaSaida.getNeuronios().forEach(n -> {
			System.out.println(n.toString());
		});
		int [] saidas = {camadaSaida.getNeuronioByName("n5").getY(),
						camadaSaida.getNeuronioByName("n6").getY(),
						camadaSaida.getNeuronioByName("n7").getY(),
						camadaSaida.getNeuronioByName("n8").getY(),
						camadaSaida.getNeuronioByName("n9").getY(),
						camadaSaida.getNeuronioByName("n10").getY()};
		return saidas;
		
	}
	
	
	
	
	
	
	public void reforcar(int feedbackPerceptron) {
		//ATUALIZAÇÃO DOS PESOS COM USO DE ALG GENÉTICO AQUI;
		double [] pesos = new double[51];
		int i=0;
		for(Neuronio n: camadaEntrada.getNeuronios()){
			pesos[i] = n.getW0();
			pesos[i+1] = n.getW1();
			pesos[i+2] = n.getW2();
			pesos[i+3] = n.getW3();
			pesos[i+4] = n.getW4();
			i = i+5;
		};
		for(Neuronio n: camadaSaida.getNeuronios()){
			pesos[i] = n.getW0();
			pesos[i+1] = n.getW1();
			pesos[i+2] = n.getW2();
			pesos[i+3] = n.getW3();
			pesos[i+4] = n.getW4();
			i = i+5;
			
		};
		
//		for(int j=0; j<pesos.length; j++) {
//			System.out.print(pesos[j]+" ");
//		}
//		System.out.println("\n"+pesos.length);
		
		switch(feedbackPerceptron) {
			case 1:
				//BURACO
				pontuacao -= 300;
				System.out.println("pontuacao : "+pontuacao);
			case 2:
				//SAIDA
				pontuacao += 500;
				System.out.println("pontuacao : "+pontuacao);
			case 3:
				//PAREDE
				pontuacao -= 200;
				System.out.println("pontuacao : "+pontuacao);
			case 4:
				//VAZIO
				pontuacao += 200;
				System.out.println("pontuacao : "+pontuacao);
			case 5:
				//MOEDAS
				pontuacao += 400;
				System.out.println("pontuacao : "+pontuacao);
		}
		
		pesos[50] = pontuacao;
		
		Genetico gen = new Genetico();
		gen.run(pontuacao);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public void treinamento() { // algoritmo Regra Delta
//		// Treinamento
//		int epocas = 0, i;
//		double y, erro, erroGeral, eta = 1; // eta eh a constante (taxa) de aprendizagem
//		System.out.println("--- TREINAMENTO");
//		while (true) {
//			epocas++;
//			erroGeral = 0;
//
//			System.out.println("Epoca: " + epocas);
//			for (i = 0; i < 4; i++) {
//				// propagacao
//				y = neuronio.calculaY(x1[i], x2[i],x3[i],x4[i]);
//				// calcula do erro
//				erro = d[i] - y;
//				// ajuste dos pesos
//				if (erro != 0) {
//					neuronio.setW0(neuronio.getW0() + eta * erro);
//					neuronio.setW1(neuronio.getW1() + eta * erro * x1[i]);
//					neuronio.setW2(neuronio.getW2() + eta * erro * x2[i]);
//					neuronio.setW3(neuronio.getW3() + eta * erro * x3[i]);
//					neuronio.setW4(neuronio.getW4() + eta * erro * x4[i]);
//				}
//				System.out.println("Neuronio - pesos: " + neuronio);
//				erroGeral = erroGeral + abs(erro);
//			}
//			// para quando para todas as entradas o erro for zero
//			if (erroGeral == 0)
//				break;
//		}
//	}

//	public void generalizacao() { // uso da rede
//		// Generalizacao - Teste da rede
//		int entrada1, entrada2, entrada3, entrada4;
//		Scanner dados = new Scanner(System.in);
//		System.out.println("\n--- GENERALIZACAO");
//		while (true) {
//			
//			/*
//			 * Entradas (relativas a percepcao do agente):
//			 * 1 - Cima
//			 * 2 - Baixo
//			 * 3 - Esquerda
//			 * 4 - Direita
//			 * 
//			 * */
//			
//			
//			// digita novas entradas
//			System.out.println("Digite -100 para encerrar");
//			System.out.print("Digite a entrada (x1): ");
//			entrada1 = dados.nextInt();
//			if (entrada1 == -100)
//				break;
//
//			System.out.print("Digite a entrada (x2): ");
//			entrada2 = dados.nextInt();
//			if (entrada2 == -100)
//				break;
//
//			System.out.print("Digite a entrada (x3): ");
//			entrada3 = dados.nextInt();
//			if (entrada3 == -100)
//				break;
//
//			System.out.print("Digite a entrada (x4): ");
//			entrada4 = dados.nextInt();
//			if (entrada4 == -100)
//				break;
//
//			// propagacao
////			System.out.println("Saida Gerada pela rede: " + neuronio.calculaY(entrada1, entrada2, entrada3, entrada4));
//		}
//	}
}
