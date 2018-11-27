import java.util.Random;

public class Genetico {

	private double[][] populacao = new double[5][51];
	private double[][] intermediaria = new double[5][51];
	private int contCromossomos = 0;
	private int cont = 0;
	private Random random = new Random();

	public Genetico() {
		
	}

	public double[] run(double[] pesos, int pontos) {
		if(this.cont==0) {
			populacao[0] = pesos;
			populacao[0][50] = pontos;

			for (int i = 1; i < 5; i++) {
				for (int j = 0; j < 50; j++) {
					populacao[i][j] = random.nextDouble();
				}
			}
			for (int i = 0; i < 100; i++) {
				int j = random.nextInt(4) + 1;
				int k = random.nextInt(50);
				populacao[j][k] = random.nextDouble() * -1;
			}
			contCromossomos = contCromossomos+5;
			this.cont++;
		}
		
		
		
		if (this.cont <= 5) {
			populacao[cont-1][50] = pontos;
			this.cont++;
			if(cont<5) {
				return populacao[this.cont-1];
			}
		}
		
		elitizar(pesos, pontos);
		gerar(populacao, intermediaria);
		this.cont=1;
		return populacao[0];

	}

	public void elitizar(double[] pesos, int pontuacao) {
		int indexMaior = 0;
		for (int i = 0; i < 5; i++) {
			if (populacao[i][50] > populacao[indexMaior][50]) {
				indexMaior = i;
			}
		}
		clonar(intermediaria[0], populacao[indexMaior]);
	}

	static void clonar(double[][] destino, double[][] origem) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 17; j++) {
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
		for (int i = 0; i < 2; i++) {
			int pai = torner(populacao);
			int mae = torner(populacao);
			linha++;
			for (int j = 0; j < 25; j++) {
				intermediaria[linha][j] = populacao[pai][j];
				intermediaria[linha + 1][j] = populacao[mae][j];
			}
			for (int j = 25; j < 50; j++) {
				intermediaria[linha][j] = populacao[mae][j];
				intermediaria[linha + 1][j] = populacao[pai][j];
			}
			linha++;
		}
//		MUTACAO:
		for(int l=0 ;l<1; l++) {
			
			for (int i = 0; i < 25; i++) {
				int j = random.nextInt(4) + 1;
				int k = random.nextInt(50);
				populacao[j][k] = random.nextDouble() * -1;
			}
			for (int i = 0; i < 25; i++) {
				int j = random.nextInt(4) + 1;
				int k = random.nextInt(50);
				populacao[j][k] = random.nextDouble();
			}
		}
		
		clonar(populacao, intermediaria);
	}

	public int torner(double[][] populacao) {
		Random r = new Random();
		int primeiro = r.nextInt(5);
		int segundo = r.nextInt(5);
		return (populacao[primeiro][50] > populacao[segundo][50]) ? primeiro : segundo;
	}

	static void printPopulacao(double[][] populacao, int limite) {
		System.out.println();
		for (int i = 0; i < 5; i++) {
			// System.out.print("P: ");
			for (int j = 0; j < limite - 1; j++) {
				// System.out.print(populacao[i][j] + " ");
			}
			System.out.print("   S: ");
			System.out.print(populacao[i][limite] + " ");
			System.out.println("");
		}
	}
}
