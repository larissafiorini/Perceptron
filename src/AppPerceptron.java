
/**
 * Write a description of class AppPerceptron here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class AppPerceptron {
	public static void main(String args[]) {
		Maze maze = new Maze();
		Agent agent = new Agent(maze); //Perceptron é inicializado dentro do Agent
		agent.escolheDirecao();
	}
}
