package entities;

import java.util.ArrayList;

public class Quarto {
	private int numeroQuarto;
	private DisponibilidadeEnum disponibilidadeQuarto;
	private EnumPosseChave posseChave;
	private ArrayList<Hospede> hospedes;
	private boolean isLimpo;
	
	// Constructors
	public Quarto(int numeroQuarto){
		this.numeroQuarto = numeroQuarto;
		this.setDisponibilidade(DisponibilidadeEnum.VAGO);
		this.setHospedes(new ArrayList<Hospede>());
		this.posseChave = EnumPosseChave.HOTEL;
		this.isLimpo = false;
	}
	
	public Quarto(){
		numeroQuarto = -1;
		this.setDisponibilidade(DisponibilidadeEnum.VAGO);
		this.setHospedes(new ArrayList<Hospede>());
		this.posseChave = EnumPosseChave.HOTEL;
		this.isLimpo = false;
	}

	// Getters e Setters
	public int getNumeroQuarto() {
		return numeroQuarto;
	}
	
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
	
	public synchronized EnumPosseChave getPosseChave() {
		return posseChave;
	}

	protected void setPosseChave(EnumPosseChave posseChave) {
		System.out.printf("\nPosse da chave do quarto %d mudou de %s -> %s\n", 
				this.getNumeroQuarto(), this.getPosseChave().name(), posseChave.name());
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

	public void limparHospedes() {
		// Sumindo com a lista de hospedes do quarto
		this.setLimpo(false);
		System.out.println(this.getHospedes());
		
		// Tornando o quarto indisponivel
		this.setDisponibilidade(DisponibilidadeEnum.INDISPONIVEL);
		this.setPosseChave(EnumPosseChave.HOTEL);
		System.out.printf("O quarto %d se tornou indisponivel \n", this.getNumeroQuarto());
	}
}
