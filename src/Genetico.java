import java.util.Random;

public class Genetico {

	public Genetico() {

	}

	public double[] run(double[] pesos, int pontuacao) {

		double[][] populacao = new double[5][51];
		double[][] intermediaria = new double[5][51];

		System.out.println("\nPopulacao: ");
		popular(populacao, pesos);
		printPopulacao(populacao, 50);

		
			
		aptidar(populacao, pontuacao);
		
		elitizar(populacao, intermediaria);
		
		// gerar(populacao, intermediaria);
		

		return populacao[0];
	}

	static void popular(double[][] populacao, double[] pesos) {

		// pesos aleatorios entre -1;1
		// Random r = new Random();

		for (int i = 0; i < 5; i++) {

			populacao[i] = pesos;
		}
		// for (int j = 0; j < 50; j++) {
		// populacao[i][j] = r.nextInt((0 + 1 + 1) - 1);
		// }
	}

	static void aptidar(double[][] populacao, int pontuacao) {

		for (int i = 0; i < 5; i++) {

			System.out.println(" pontuacao de cada pop: " + populacao[i][50]);

			//
			// for (int j = 0; j < 50; j++) {
			//
			// }
		}

	}

	static void elitizar(double[][] populacao, double[][] intermediaria) {
		int indexMenor = 0;
		for (int i = 0; i < 5; i++) {
			if (populacao[i][50] < populacao[indexMenor][50]) {
				indexMenor = i;
			}
		}
		clonar(intermediaria[0], populacao[indexMenor]);
	}

	static void clonar(double[] destino, double[] origem) {
		for (int j = 0; j < 17; j++) {
			destino[j] = origem[j];
		}
	}

	static void gerar(double[][] populacao, double[][] intermediaria) {
	}

	static void printPopulacao(double[][] populacao, int limite) {
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
