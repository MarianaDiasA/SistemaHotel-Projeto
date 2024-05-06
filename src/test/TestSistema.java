package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import entities.Camareira;
import entities.DisponibilidadeEnum;
import entities.EnumPosseChave;
import entities.Hospede;
import entities.Quarto;
import entities.Recepcionista;

class TestSistema {

	@Test
	void recepcionistaAlocaUmQuartoVago() {
		ArrayList<Quarto> quartos = new ArrayList<Quarto>();
		quartos.add(new Quarto());
		Recepcionista recep = new Recepcionista(quartos);
		Hospede hosp = new Hospede();
		recep.aloca(hosp);
	
		assertEquals(DisponibilidadeEnum.OCUPADO, quartos.get(0).getDisponibilidade());
	}
	
	@Test 
	void verificandoSeHospedeEstaNoQuarto() {
		ArrayList<Quarto> quartos = new ArrayList<Quarto>();
		quartos.add(new Quarto());
		Recepcionista recep = new Recepcionista(quartos);
		Hospede hosp = new Hospede();
		recep.aloca(hosp);
	
		assertEquals(hosp, quartos.get(0).getHospedes().get(0));
	}
	
	@Test
	void divisaoDeHospedesPorQuarto() {
		// Criação de Arrays
		ArrayList<Hospede> hospedes = new ArrayList<Hospede>();
		ArrayList<Quarto> quartos = new ArrayList<Quarto>();
		
		// Alimentando arrays
		for (int i = 0; i < 12; i++) hospedes.add(new Hospede());
		for (int i = 0; i < 10; i++) quartos.add(new Quarto());
		
		Recepcionista recep = new Recepcionista(quartos);
		recep.aloca(hospedes);
		
		int total = 0;
		for (Quarto quarto : quartos) 
			total += quarto.getQuantidade();
		
		assertEquals(12, total);
	}
	
	@Test
	void olhandoPosseChave() {
		ArrayList<Quarto> quartos = new ArrayList<Quarto>();
		quartos.add(new Quarto());
		Recepcionista recep = new Recepcionista(quartos);
		Hospede hosp = new Hospede();
		
		assertEquals(recep.getQuartos().get(0).getPosseChave(), EnumPosseChave.HOTEL);
		
		recep.aloca(hosp);
		assertEquals(recep.getQuartos().get(0).getPosseChave(), EnumPosseChave.HOSPEDE, 
				"Hóspede foi alocado, mas a chave continua no Hotel");
		
		hosp.saidaPasseio();
		assertEquals(recep.getQuartos().get(0).getPosseChave(), EnumPosseChave.HOTEL, 
				"Hóspede saiu, mas a chave está no quarto");
	}
	
	@Test
	void camareiraLimpaQuarto() {
		ArrayList<Quarto> quartos = new ArrayList<Quarto>();
		ArrayList<Camareira> camareiras = new ArrayList<>();
		
		// Alimentando arrays
		for (int i = 0; i < 10; i++) quartos.add(new Quarto());
		for (int i = 0; i < 10; i++) camareiras.add(new Camareira());
		
		Recepcionista recep = new Recepcionista(quartos, camareiras);
		Hospede hosp = new Hospede();
		
		recep.aloca(hosp);

		hosp.saidaPasseio();
		
		try {
			Thread.sleep(5001);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		assertEquals(EnumPosseChave.HOSPEDE, recep.getQuartos().get(0).getPosseChave(),
				"Hóspede saiu, mas a chave está no quarto");
	}
}
