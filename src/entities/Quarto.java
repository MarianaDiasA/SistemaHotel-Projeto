package entities;

import java.util.ArrayList;

public class Quarto {
	private DisponibilidadeEnum disponibilidadeQuarto;
	private EnumPosseChave posseChave;
	private ArrayList<Hospede> hospedes;
	private boolean isLimpo;
	
	// Constructors
	public Quarto(){
		this.setDisponibilidade(DisponibilidadeEnum.VAGO);
		this.setHospedes(new ArrayList<Hospede>());
		this.posseChave = EnumPosseChave.HOTEL;
		this.isLimpo = false;
	}
	
	// Getters e Setters
	public DisponibilidadeEnum getDisponibilidade() {
		return disponibilidadeQuarto;
	}

	public void setDisponibilidade(DisponibilidadeEnum disponibilidade) {
		this.disponibilidadeQuarto = disponibilidade;
	}

	public ArrayList<Hospede> getHospedes() {
		return hospedes;
	}

	public void setHospedes(ArrayList<Hospede> hospedes) {
		this.hospedes = hospedes;
	}
	
	public void setHospedes(Hospede hospede) {
		this.hospedes.add(hospede);
	}
	
	public EnumPosseChave getPosseChave() {
		return posseChave;
	}

	protected void setPosseChave(EnumPosseChave posseChave) {
		System.out.println("\nPosse da chave mudou de: " + this.getPosseChave().name() +
				"\nPARA: " + posseChave.name());
		this.posseChave = posseChave;
	}
	
	public boolean isLimpo() {
		return isLimpo;
	}

	public void setLimpo(boolean isLimpo) {
		this.isLimpo = isLimpo;
	}

	public int getQuantidade() {
		return this.getHospedes().size();
	}
}
