import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.event.TreeWillExpandListener;

public class Agent {
	private Maze maze;
	private Perceptron perceptron;
	
	private int chestsPositions[] = new int[8];
	int chestsPositionsCont;
	private int exitPosition[] = new int[2];
	private ArrayList coin = new ArrayList<>();
	private String currentPositionContent = "   -  ";
	private String currentDirection;
	private Random random = new Random();
	private int points = 0;
	private int movements=0;
	private int tentativas=0;
	private String log="";
	private int [] coinDistribution= new int[17];
	private ArrayList<Integer> chestA = new ArrayList<Integer>();
	private ArrayList<Integer> chestB = new ArrayList<Integer>();
	private ArrayList<Integer> chestC = new ArrayList<Integer>();
	private ArrayList<Integer> chestD = new ArrayList<Integer>();

	public ArrayList getCoin() {
		return coin;
	}

	public Agent(Maze maze) throws InterruptedException {
		this.maze = maze;
		perceptron = new Perceptron();
	}


	
	public void escolheDirecao() throws InterruptedException {
		int feedbackPerceptron=0;
		while(!currentPositionContent.contains("S")) {
			for (int j = 0; j < 50; j++) {
				System.out.println();
			}
			int[] position = maze.getAgentPosition();
			int x1, x2, x3, x4;
			if(!validRangePos(position[0] -1, position[1])) {
				x1=3;
			}
			else {
				x1 = codificaArea(maze.getMaze()[position[0] -1] [position[1]]);
			}
			
			
			if(!validRangePos(position[0], position[1]+1)) {
				x2=3;
			}
			else {
				x2 = codificaArea(maze.getMaze()[position[0]] [position[1] +1]);
			}
			
			
			if(!validRangePos(position[0] +1, position[1])) {
				x3=3;
			}
			else {
				x3 = codificaArea(maze.getMaze()[position[0] +1] [position[1]]);
			}
			
			
			if(!validRangePos(position[0], position[1] -1)) {
				x4=3;
			}
			else {
				x4 = codificaArea(maze.getMaze()[position[0]] [position[1] -1]);
			}
			//CHAMADA DO PERCEPTRON:
			int [] saidasPerceptron = perceptron.defineAcao(x1, x2, x3, x4);
			
			int acao, direcao, maiorValorDirecao=-1, maiorIndiceDirecao=-1;
			//Escolhe qual acao (andar/pular) de acordo com saída da rede neural:
			if(saidasPerceptron[0]>=saidasPerceptron[1]) {acao = 0;}else {acao=1;}
			
			//Escolhe qual direcao (cima, direita, baixo, esquerda) de acordo com saída da rede neural:
			for(int i=2; i<6;i++) {
				if(saidasPerceptron[i]>maiorValorDirecao) {
					maiorValorDirecao = saidasPerceptron[i];
					maiorIndiceDirecao = i;
				}
			}
			switch (maiorIndiceDirecao) {
				case 2:
					currentDirection = "up";
					break;
				case 3:
					currentDirection = "down";
					break;
				case 4:
					currentDirection = "left";
					break;
				case 5:
					currentDirection = "rigth";
					break;
			}
			
			feedbackPerceptron = andar(acao);
			printData();
			Thread.sleep(400);
			if(movements==0) {
				perceptron.reforcar(feedbackPerceptron, points);
				points=0;
				tentativas++;
			}
			
		}
	}
	
	
	public int codificaArea(String s) {
		switch(s) {
			case "   B  ": {
				return 1;
			}
			case "   S  ": {
				return 2;
			}
			case "   P  ": {
				return 3;
			}
			case "   -  ":{
				return 4;
			}
			case "   O  ":{
				
			}
		}
		
		if (s.replaceAll(" ", "").matches("^[0-9]{2}|^[0-9]")) {
			return 5;
		}
		return 0;
	}
	
	
	public int andar(int acao) {
		int[] position = maze.getAgentPosition();
		int[] before = new int[2];
		int[] areaDepoisDaProximaArea = new int[2];
		before[0] = position[0];
		before[1] = position[1];
		int x=0, y=0;

		switch (currentDirection) {
			case "up":
				x = position[0] - 1;
				y = position[1];
				areaDepoisDaProximaArea[0] = x-1;
				areaDepoisDaProximaArea[1] = y;
				break;
			case "down":
				x = position[0] + 1;
				y = position[1];
				areaDepoisDaProximaArea[0] = x+1;
				areaDepoisDaProximaArea[1] = y;
				break;
			case "left":
				x = position[0];
				y = position[1] -1;
				areaDepoisDaProximaArea[0] = x;
				areaDepoisDaProximaArea[1] = y-1;
				break;
			case "rigth":
				x = position[0];
				y = position[1] +1;
				areaDepoisDaProximaArea[0] = x;
				areaDepoisDaProximaArea[1] = y+1;
				break;
		}
		String conteudo = scanPos(x, y);
		if(conteudo.equals("invalid position") || conteudo.contains("P")) {
			log = log+"\nBateu em parede! ";
			movements=0;
			int [] inicialPosition = {0, 0};
			currentPositionContent = maze.getMaze()[0][0];
			int novaPosicao [] = {0, 0};
			maze.updateAgentPosition(novaPosicao, before);
			return 3;
		} else {
			switch(conteudo) {
				case "   O  ": {
					if(acao==0) {
//						cai no buraco
						log = log+"\nCaiu em buraco!";
						movements=0;
						int [] inicialPosition = {0, 0};
						currentPositionContent = maze.getMaze()[0][0];
						int novaPosicao [] = {0, 0};
						maze.updateAgentPosition(novaPosicao, before);
						return 1;
					} else {
						//tenta pular buraco
						String conteudoAreaPosterior = scanPos(areaDepoisDaProximaArea[0], areaDepoisDaProximaArea[1]); 
						if(conteudoAreaPosterior.equals("invalid position") || conteudoAreaPosterior.contains("P")) {
							log = log+"\nPosicao depois do buraco invalida! Caiu no buraco!";
							movements=0;
							int [] inicialPosition = {0, 0};
							currentPositionContent = maze.getMaze()[0][0];
							int novaPosicao [] = {0, 0};
							maze.updateAgentPosition(novaPosicao, before);
							return 1;
						} else {
							log = log+"\nPulou buraco!";
							points = points+10;
							before[0] = position[0];
							before[1] = position[1];
							movements++;
							currentPositionContent = maze.getMaze()[areaDepoisDaProximaArea[0]][areaDepoisDaProximaArea[1]];
							maze.updateAgentPosition(areaDepoisDaProximaArea, before);
							return codificaArea(conteudo);
						}
					}
				}
				case "   S  ": {
					log = log+"\nEncontrou Saida! +100 pontos";
					points = points+100;
					before[0] = position[0];
					before[1] = position[1];
					movements++;
					currentPositionContent = maze.getMaze()[x][y];
					break;
				}
				case "   -  ": {
					log = log+"\nAndou uma casa vazia!";
					points = points+1;
					before[0] = position[0];
					before[1] = position[1];
					movements++;
					currentPositionContent = maze.getMaze()[x][y];
					break;
				}
			}
			
			if(conteudo.replaceAll(" ", "").matches("^[0-9]{2}|^[0-9]")) {
				log = log+"\nColetou moedas! + "+ conteudo.replaceAll(" ", "")+" pontos";
				points = points+Integer.parseInt(conteudo.replaceAll(" ", ""));
				before[0] = position[0];
				before[1] = position[1];
				currentPositionContent = maze.getMaze()[x][y];
				movements++;
			}
		}
		position[0] = x;
		position[1] = y;
		maze.updateAgentPosition(position, before);
		return codificaArea(conteudo);
	}
	
	
	public int pularBuraco() {
		return 0;
	}
	
	
	
	
	
	
	public void explorePos() {
		if (currentPositionContent.replaceAll(" ", "").matches("^[0-9]{2}|^[0-9]")) {
			int c = Integer.parseInt(currentPositionContent.replaceAll(" ", ""));
			coin.add(c);
			points = points + (c*10);
			movements = 0;
			log = log+"\nMoedas coletadas! + "+points+" pontos";
			currentPositionContent = "  -  ";
		}
	}
	
	public boolean validPos(int x, int y) {
		return validRangePos(x, y) && !maze.getMaze()[x][y].contains("P") && !maze.getMaze()[x][y].contains("O");
	}
	
	public boolean validRangePos(int i, int j) {
		return (i >= 0 && i < maze.getMaze().length && j >= 0 && j < maze.getMaze()[0].length);
	}
	
	
	
	public String scanPos(int x, int y) {
		if (!validRangePos(x, y))
			return "invalid position";
		if (maze.getMaze()[x][y].contains("S")) {
			exitPosition[0] = x;
			exitPosition[1] = y;
		}
		return maze.getMaze()[x][y];
	}

	
	public void p(int [] v) {
		for(int i=0; i<v.length; i++) {
			System.out.print(v[i]+" ");
		}
	}
	
	public void printData() {
		System.out.println(log+"      <------- log"+"\n_________________________________________________________");
		System.out.println("Tentativas: "+tentativas);
		System.out.print("Moedas coletadas: ");
		coin.stream().forEach(c -> {
			System.out.print("  " + c);
		});
		System.out.print("\nPosicao atual   X: " + maze.getAgentPosition()[0]);
		System.out.println("  Y: " + maze.getAgentPosition()[1]);
		System.out.println("Direcao atual: " + currentDirection);
		System.out.println("Pontuacao: "+points);

		maze.printMaze();
	}
	
	public void gameOver(String reason) {
		for (int j = 0; j < 50; j++) {System.out.println();}
		System.out.println(log+"      <------- log"+"\n_________________________________________________________");
		System.out.println("------GAME OVER------");
		System.out.println(reason);
		System.out.println("Pontuacao: "+points);
	}
}
