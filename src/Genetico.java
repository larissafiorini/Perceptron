import java.util.Random;

public class Genetico {

	private double[][] populacao = new double[5][51];
	private double[][] intermediaria = new double[5][51];
	private int contCromossomos=0;
	
	public Genetico(double[] pesos, int pontuacao) {
		populacao[contCromossomos] = pesos;
		populacao[contCromossomos][50] = pontuacao;
		contCromossomos++;
	}

	public double[] run(double[] pesos, int pontuacao) {
		elitizar(pesos, pontuacao);
		gerar(populacao, intermediaria);
//		//		System.out.println("\nPopulacao: ");
//		popular(populacao, pesos);
//		printPopulacao(populacao, 50);
//		populacao[0][50] = pontuacao;
//
//		aptidar(populacao, pontuacao);
//		
//		elitizar(populacao, intermediaria);
		return populacao[0];
	}

	public void elitizar(double [] pesos, int pontuacao) {
		int indexMaior=0;
		for(int i=0; i<contCromossomos; i++) {
			if (populacao[i][50] > populacao[indexMaior][50]) {
				indexMaior = i;
			}
		}
		populacao[indexMaior] = pesos;
		populacao[indexMaior][50] = pontuacao;
		contCromossomos++;
		clonar(intermediaria[0], populacao[indexMaior]);
	}
	
	


	static void elitizar(double[][] populacao, double[][] intermediaria) {
		int indexMaior = 0;
		for (int i = 0; i < 5; i++) {
			if (populacao[i][50] > populacao[indexMaior][50]) {
				indexMaior = i;
			}
		}
		clonar(intermediaria[0], populacao[indexMaior]);
	}
	
	
	static void clonar(double [][]destino, double [][]origem) {
		for (int i=0; i<5; i++) {
			for (int j=0; j<17; j++) {
				destino[i][j] = origem[i][j];
			}
		}
	}

	static void clonar(double[] destino, double[] origem) {
		for (int j = 0; j < 50; j++) {
			destino[j] = origem[j];
		}
	}

	public void gerar(double[][] populacao, double[][] intermediaria) {
		int linha = 0;
		for (int i=0; i<2; i++) {
			int pai = torner(populacao);
			int mae = torner(populacao);
			linha++;
			for (int j=0; j<25; j++) {
				intermediaria[linha][j] = populacao[pai][j];
				intermediaria[linha+1][j] = populacao[mae][j];
			}
			for (int j=25; j<50; j++) {
				intermediaria[linha][j] = populacao[mae][j];
				intermediaria[linha+1][j] = populacao[pai][j];
			}
			linha++;
		}
		clonar(populacao,intermediaria);
	}
	
	public int torner(double [][]populacao) {
		Random r = new Random();
		int primeiro = r.nextInt(5);
		int segundo = r.nextInt(5);
		return (populacao[primeiro][50] > populacao[segundo][50]) ? primeiro : segundo;
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

//	static void aptidar(double[][] populacao, int pontuacao) {
//
//		for (int i = 0; i < 5; i++) {
//			
//			
//			
////			System.out.println(" pontuacao de cada pop: " + populacao[i][50]);
//
//			//
//			// for (int j = 0; j < 50; j++) {
//			//
//			// }
//		}
//
//	}
}
