import java.util.Random;

public class Neuronio {
	// Neuronio para 4 entradas

	private String nome;
	

	private double w0; // pesos
	private double w1;
	private double w2;
	private double w3;
	private double w4;
	private int y;

	public Neuronio(String nome) {
		this.nome = nome;
		gerarPesos();
	}
	
	public void gerarPesos() {
		Random random = new Random();
		w0 = random.nextDouble();
		w1 = random.nextDouble();
		w2 = random.nextDouble();
		w3 = random.nextDouble();
		w4 = random.nextDouble();
	}

	public double getW3() {
		return w3;
	}

	public void setW3(double w3) {
		this.w3 = w3;
	}

	public double getW4() {
		return w4;
	}

	public void setW4(double w4) {
		this.w4 = w4;
	}

	public double getW0() {
		return w0;
	}

	public double getW1() {
		return w1;
	}

	public double getW2() {
		return w2;
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double calculaV(int x1, int x2, int x3, int x4) {
		return w0 + w1 * x1 + w2 * x2 + w3 * x3 + w4 * x4;
	} // calcula o campo local induzido

	public void calculaY(int x1, int x2, int x3, int x4) { // aplica a funcao
		double v = calculaV(x1, x2, x3, x4);

		if (v >= 0)
			y = 1;
		else
			y = 0;
	}

	public void setW0(double w0) {
		this.w0 = w0;
	}

	public void setW1(double w1) {
		this.w1 = w1;
	}

	public void setW2(double w2) {
		this.w2 = w2;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String toString() {
		return "NOME: "+nome+"   w0 = " + w0 + " w1= " + w1 + " w2= " + w2 + " w3= " + w3 + " w4= " + w4+"   Y: "+y;
	}
}
