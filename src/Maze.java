import java.util.ArrayList;
import java.util.Random;

public class Maze {

	private String[][] maze = new String[8][3];
	private int[] coins = { 10, 10, 10, 10, 10 };
	private int[] coinsPositions = new int[21];
	private int[] holesPositions = new int[4];
	private int[] chestsPositions = new int[8];
	int cont;
	boolean exitFree = false;

	public void setExitFree(boolean exitFree) {
		this.exitFree = exitFree;
	}

	public boolean isExitFree() {
		return exitFree;
	}

	public int[] getCoinsPositions() {
		return coinsPositions;
	}

	public int[] getHolesPositions() {
		return holesPositions;
	}

	public int[] getChestsPositions() {
		return chestsPositions;
	}

	private Random random = new Random();
	private String wallSide = "";
	private int[] agent_position =  new int[2];
	private String lastPositionContent = "   A  ";

	// temporario. Algoritmo de busca vai ter q achar saida no labirinto depois.
	private int[] saida = new int[2];

	public int[] getCoins() {
		return coins;
	}

	// Agente: A
	// Parede: P
	// Buraco: O
	// Baus: B
	// Saida: S
	// Moedas: Numeros
	// vazio: -
	public Maze() {
		init();
//		generateWallsAndChests();
//		generateHoles();
//		generateCoins();
//		this.agent_position = generateAgent();
		printMaze();
	}

	public void init() {
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				maze[i][j] = "   -  ";
			}
		}
		maze[0][1]="   P  ";
		maze[0][2]="   P  ";
		maze[1][1]="   O  ";
		maze[2][0]="   P  ";
		maze[2][1]="   P  ";
		maze[3][1]="   O  ";
		maze[4][0]="   O  ";
		maze[4][1]="   P  ";
		maze[4][2]="   P  ";
		maze[6][0]="   P  ";
		maze[6][1]="   P  ";
		maze[6][2]="   O  ";
		maze[7][0]="   S  ";
		maze[2][2]="  " + coins[0] + "  ";
		maze[3][2]="  " + coins[1] + "  ";
		maze[5][0]="  " + coins[2] + "  ";
		maze[5][2]="  " + coins[3] + "  ";
		maze[7][1]="  " + coins[4] + "  ";
		
		maze[0][0] = "   A  ";
		this.agent_position[0] = 0;
		this.agent_position[1] = 0;
		
		this.saida[0]=7;
		this.saida[1]=0;
		
	}



	public void printMaze() {
		System.out.println("    |0|    |1|   |2|");
		for (int i = 0; i < maze.length; i++) {
			System.out.print("|" + i + "|");
			for (int j = 0; j < maze[0].length; j++) {
				System.out.print(maze[i][j]);
			}
			System.out.println();
		}
	}

	public String[][] getMaze() {
		return maze;
	}

	public int[] getAgentPosition() {
		return agent_position;
	}

	public int[] getSaidaPosition() {
		return this.saida;
	}

	public void updateAgentPosition(int[] position, int[] before) {
		if (lastPositionContent.contains("B") || lastPositionContent.contains("S") || lastPositionContent.contains("O")) {
			maze[before[0]][before[1]] = lastPositionContent;
		} else {
			maze[before[0]][before[1]] = "   -  ";
		}

		this.agent_position[0] = position[0];
		this.agent_position[1] = position[1];
		lastPositionContent = maze[position[0]][position[1]];
		System.out.println("CONTEUDO:"+lastPositionContent);
		maze[position[0]][position[1]] = "   A  ";
	}
}
