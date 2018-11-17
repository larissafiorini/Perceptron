import java.util.Random;

public class Genetico {

	public Genetico() {

	}

	public int[] run(int pontuacao) {

		int[][] populacao = new int[5][51];
		int[][] intermediaria = new int[5][51];

		System.out.println("\nPopulacao: ");
		popular(populacao);
		printPopulacao(populacao, 50);

		for (int i = 0; i < 20; i++) {

			aptidar(populacao, pontuacao);
			elitizar(populacao, intermediaria);
			// gerar(populacao, intermediaria);
		}

		return populacao[0];
	}

	static void popular(int[][] populacao) {

		// pesos aleatorios entre -1;1
		Random r = new Random();

		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 50; j++) {
				populacao[i][j] = r.nextInt((0 + 1 + 1) - 1);
			}
	}

	static void aptidar(int[][] populacao, int pontuacao) {

		for (int i = 0; i < 5; i++) {

			System.out.println(" pontuacao de cada pop: " + populacao[i][50]);
			
			
			//
			// for (int j = 0; j < 50; j++) {
			//
			// }
		}

	}

	static void elitizar(int[][] populacao, int[][] intermediaria) {
		int indexMenor = 0;
		for (int i = 0; i < 5; i++) {
			if (populacao[i][50] < populacao[indexMenor][50]) {
				indexMenor = i;
			}
		}
		clonar(intermediaria[0], populacao[indexMenor]);
	}

	static void clonar(int[] destino, int[] origem) {
		for (int j = 0; j < 17; j++) {
			destino[j] = origem[j];
		}
	}

	static void gerar(int[][] populacao, int[][] intermediaria) {
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
