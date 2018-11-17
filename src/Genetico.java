import java.util.Random;

public class Genetico {

	public Genetico() {

	}

	public int[] run() {

		int[][] populacao = new int[5][51];
		System.out.println("Populacao: ");
		popular(populacao);
		printPopulacao(populacao, 17);

		aptidar(populacao);
		// elitizar(populacao, intermediaria);
		// gerar(populacao, intermediaria);

		return populacao[0];
	}

	static void popular(int[][] populacao) {

		// pesos aleatorios entre -1;1
		Random r = new Random();

		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 51; j++) {
				populacao[i][j] = r.nextInt((0 + 1 + 1) - 1);
			}
	}

	static void aptidar(int[][] populacao) {
	}

	static void printPopulacao(int[][] populacao, int limite) {
		System.out.println();
		for (int i = 0; i < 5; i++) {
			System.out.print("P: ");
			for (int j = 0; j < limite - 1; j++) {
				System.out.print(populacao[i][j] + " ");
			}
			System.out.print("   S: ");
			System.out.print(populacao[i][limite - 1] + " ");
			System.out.println("");
		}
	}

}
