import java.util.ArrayList;
import java.util.List;

public class Camada {
	private List<Neuronio> neuronios = new ArrayList<>();

	public Camada() {

	}

	public List<Neuronio> getNeuronios() {
		return neuronios;
	}

	public void setNeuronios(List<Neuronio> neuronios) {
		this.neuronios = neuronios;
	}

	public void addNeuronio(Neuronio neuronio) {
		neuronios.add(neuronio);
	}
	
	public Neuronio getNeuronioByName(String name) {
		for(Neuronio n: neuronios) {
			if(name.equals(n.getNome())) {
				return n;
			}
		}
		return null;
	}
}
