package entities;

import java.util.ArrayList;

public class Camareira extends Thread {
	private ArrayList<Quarto> quartos;
    private EnumCamareiraDisp disponibilidade;
    
    public Camareira(ArrayList<Quarto> quartos) {
    	this.quartos = quartos;
    	this.disponibilidade = EnumCamareiraDisp.DISPONIVEL;
    }
    
    // Getters e Setters
	protected EnumCamareiraDisp getDisponibilidade() {
		return disponibilidade;
	}

	protected void setDisponibilidade(EnumCamareiraDisp disponibilidade) {
		this.disponibilidade = disponibilidade;
	}
	
	protected ArrayList<Quarto> getQuartos() {
		return quartos;
	}

	protected void setQuartos(ArrayList<Quarto> quartos) {
		this.quartos = quartos;
	}

	// Métodos
    void limparQuarto(Quarto quarto) { 
    	if (quarto.getPosseChave().equals(EnumPosseChave.HOTEL)) {
    		this.setDisponibilidade(EnumCamareiraDisp.OCUPADA);
    		
    		quarto.setPosseChave(EnumPosseChave.CAMAREIRA);
            System.out.println("A Camareira está limpando o quarto");
            
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A Camareira terminou de limpar o quarto");
            quarto.setPosseChave(EnumPosseChave.HOTEL);
            quarto.setLimpo(true);
            this.setDisponibilidade(EnumCamareiraDisp.DISPONIVEL);
    	}
    }
    
	@Override
    public void run() {
        while (true) { //verifica se o quarto está ocupado e executa a limpeza
        	for (Quarto quarto : quartos) {
        		if (podeCamareira(quarto)) this.limparQuarto(quarto);
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
