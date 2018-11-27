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
	private int cont=0;
	Genetico gen = new Genetico();
	

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

		int [] saidas = {camadaSaida.getNeuronioByName("n5").getY(),
						camadaSaida.getNeuronioByName("n6").getY(),
						camadaSaida.getNeuronioByName("n7").getY(),
						camadaSaida.getNeuronioByName("n8").getY(),
						camadaSaida.getNeuronioByName("n9").getY(),
						camadaSaida.getNeuronioByName("n10").getY()};
		return saidas;
		
	}
	
	
	
	
	
	
	public void reforcar(int feedbackPerceptron, int pontos) {
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
		
		pesos[50] = pontos;
		
		pesos = gen.run(pesos, pontos);
		
		atualizaPesos(pesos);
		this.cont++;
	}
	
	public void atualizaPesos(double [] pesos) {
		int j=0;
		for(int i=0; i<4; i++) {
			camadaEntrada.getNeuronios().get(i).setW0(pesos[j]);
			camadaEntrada.getNeuronios().get(i).setW0(pesos[j+1]);
			camadaEntrada.getNeuronios().get(i).setW0(pesos[j+2]);
			camadaEntrada.getNeuronios().get(i).setW0(pesos[j+3]);
			camadaEntrada.getNeuronios().get(i).setW0(pesos[j+4]);
			j = j+5;
		}
		for(int i=0; i<6; i++) {
			camadaSaida.getNeuronios().get(i).setW0(pesos[j]);
			camadaSaida.getNeuronios().get(i).setW0(pesos[j+1]);
			camadaSaida.getNeuronios().get(i).setW0(pesos[j+2]);
			camadaSaida.getNeuronios().get(i).setW0(pesos[j+3]);
			camadaSaida.getNeuronios().get(i).setW0(pesos[j+4]);
			j = j+5;
		}
	}
}
