package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
		/**
		 * Teste criado para alocar um hóspede em um quarto
		 */
		ArrayList<Quarto> quartos = new ArrayList<Quarto>();
		quartos.add(new Quarto());
		Recepcionista recep = new Recepcionista(quartos);
		Hospede hosp = new Hospede();
		recep.aloca(hosp);

		assertEquals(DisponibilidadeEnum.OCUPADO, quartos.get(0).getDisponibilidade());
	}

	@Test
	void verificandoSeHospedeEstaNoQuarto() {
		/**
		 * Teste criado para olhar se o hóspede está de fato no quarto após ser alocado
		 */
		ArrayList<Quarto> quartos = new ArrayList<Quarto>();
		quartos.add(new Quarto());
		Recepcionista recep = new Recepcionista(quartos);
		Hospede hosp = new Hospede();
		recep.aloca(hosp);

		assertEquals(hosp, quartos.get(0).getHospedes().get(0));
	}

	@Test
	void divisaoDeHospedesPorQuarto() {
		/**
		 * Teste criado para testar se o código se comporta corretamente quando se há mais de um hóspede
		 */
		// Criação de Arrays
		ArrayList<Hospede> hospedes = new ArrayList<Hospede>();
		ArrayList<Quarto> quartos = new ArrayList<Quarto>();

		// Alimentando arrays
		for (int i = 0; i < 12; i++)
			hospedes.add(new Hospede());
		for (int i = 0; i < 10; i++)
			quartos.add(new Quarto());

		Recepcionista recep = new Recepcionista(quartos);
		recep.aloca(hospedes);

		int total = 0;
		for (Quarto quarto : quartos)
			total += quarto.getQuantidade();

		assertEquals(12, total);
	}

	@Test
	void olhandoPosseChave() {
		/**
		 * Teste criado para olhar em diversos pontos de execução a posse da chave de um quarto
		 */
		ArrayList<Quarto> quartos = new ArrayList<Quarto>();
		ArrayList<Camareira> camareiras = new ArrayList<>();

		for (int i = 0; i < 10; i++)
			quartos.add(new Quarto());
		for (int i = 0; i < 10; i++)
			camareiras.add(new Camareira(quartos));

		Recepcionista recep = new Recepcionista(quartos, camareiras);
		Hospede hosp = new Hospede();

		assertEquals(recep.getQuartos().get(0).getPosseChave(), EnumPosseChave.HOTEL);

		recep.aloca(hosp);
		assertEquals(recep.getQuartos().get(0).getPosseChave(), EnumPosseChave.HOSPEDE,
				"Hóspede foi alocado, mas a chave continua no Hotel");

		hosp.saidaPasseio();
		assertEquals(recep.getQuartos().get(0).getPosseChave(), EnumPosseChave.HOSPEDE,
				"Hóspede saiu, mas a chave está no quarto");
	}

	@Test
	void camareiraLimpaQuarto() {
		/**
		 * Teste que verifica se a camareira está limpando o quarto
		 */
		ArrayList<Quarto> quartos = new ArrayList<Quarto>();
		ArrayList<Camareira> camareiras = new ArrayList<>();

		// Alimentando arrays
		for (int i = 0; i < 10; i++)
			quartos.add(new Quarto());
		for (int i = 0; i < 10; i++)
			camareiras.add(new Camareira(quartos));

		Recepcionista recep = new Recepcionista(quartos, camareiras);
		Hospede hosp = new Hospede();

		recep.aloca(hosp);
		hosp.start();

		try {
			hosp.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(EnumPosseChave.HOSPEDE, recep.getQuartos().get(0).getPosseChave(),
				"Hóspede saiu, mas a chave está no quarto");
	}

	@Test
	void hospedeVaiEmbora() {
		/**
		 * Teste com o objetivo de verificar se a thread do hóspede está indo embora.
		 */
		// Criação de objetos
		ArrayList<Quarto> quartos = new ArrayList<Quarto>();
		ArrayList<Camareira> camareiras = new ArrayList<>();

		// Alimentando arrays
		for (int i = 0; i < 10; i++)
			quartos.add(new Quarto());
		for (int i = 0; i < 10; i++)
			camareiras.add(new Camareira(quartos));

		Recepcionista recep = new Recepcionista(quartos, camareiras);
		Hospede hosp = new Hospede();

		recep.aloca(hosp);
		hosp.start();

		try {
			hosp.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(quartos.get(0).getHospedes());
		assertTrue(quartos.get(0).getHospedes().isEmpty(), "O array de hospedes não está vazio");
	}
}
