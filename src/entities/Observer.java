package entities;

import java.util.ArrayList;

public class Observer extends Thread {
	/**
	 * Quartos <- Observados por OBSERVER <- que notifica -> Camareira
	 */
	private ArrayList<Quarto> quartos;
	private ArrayList<Camareira> camareiras;

	// Constructors
	public Observer(ArrayList<Quarto> quartos, ArrayList<Camareira> camareiras) {
		this.quartos = quartos;
		this.camareiras = camareiras;
	}
	
	// Getter and Setters
	public ArrayList<Quarto> getQuartos() {
		return quartos;
	}

	public void setQuartos(ArrayList<Quarto> quartos) {
		this.quartos = quartos;
	}

	public ArrayList<Camareira> getCamareiras() {
		return camareiras;
	}

	public void setCamareiras(ArrayList<Camareira> camareiras) {
		this.camareiras = camareiras;
	}
	
	// Métodos
	void solicitaCamareira(Quarto quarto, Camareira camareira) {
		camareira.limparQuarto(quarto);
	}
	
	// Thread
	@Override
    public void run() {
		while(true) {
			for (Quarto quarto : quartos) {
				if(podeCamareira(quarto)) {
					for (Camareira camareira : camareiras) {
						if(camareira.getDisponibilidade().equals(EnumCamareiraDisp.DISPONIVEL)) {
							System.out.printf("Indice da camareira: %d\n", camareiras.indexOf(camareira));
							camareira.limparQuarto(quarto);
							break;
						}
					}
				}
			}
		}
	}
	
	// Métodos auxiliares
	private boolean podeCamareira(Quarto quarto) {
		return (quarto.getPosseChave().equals(EnumPosseChave.HOTEL) &&  // Chave está na recepção
				quarto.getDisponibilidade().equals(DisponibilidadeEnum.OCUPADO) &&  // Quarto está ocupado
				!quarto.isLimpo()); // Quarto está sujo
	}
}
