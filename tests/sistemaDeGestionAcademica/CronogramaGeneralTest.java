package sistemaDeGestionAcademica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CronogramaGeneralTest {

	@Test
	void testConstructor() {
		CronogramaGeneral cronograma = new CronogramaGeneral();
		assertNull(cronograma.consultarHorario(0, 0));
		assertNull(cronograma.consultarHorario(4, 2));
	}

	
	
	@Test
	void testAsignarComision() {
		CronogramaGeneral cronograma = new CronogramaGeneral();
		assertTrue(cronograma.asignarComision(0, 0, "AyP1"));
		assertEquals("AyP1", cronograma.consultarHorario(0, 0));
	}

	
	
	@Test
	void testAsignarComisionHorarioOcupado() {
		CronogramaGeneral cronograma = new CronogramaGeneral();
		assertTrue(cronograma.asignarComision(0, 0, "AyP1"));
		assertFalse(cronograma.asignarComision(0, 0, "Matematica"));
	}

	
	
	@Test
	void testLiberarHorario() {
		CronogramaGeneral cronograma = new CronogramaGeneral();
		cronograma.asignarComision(1, 2, "Programacion");
		assertTrue(cronograma.liberarHorario(1, 2));
		assertNull(cronograma.consultarHorario(1, 2));
	}

	
	
	@Test
	void testLiberarHorarioLibre() {
		CronogramaGeneral cronograma = new CronogramaGeneral();
		assertFalse(cronograma.liberarHorario(2, 1));
	}

	
	
	@Test
	void testBuscarComision() {
		CronogramaGeneral cronograma = new CronogramaGeneral();
		cronograma.asignarComision(3, 0, "BaseDeDatos");
		int[] posicion = cronograma.buscarComision("BaseDeDatos");
		assertNotNull(posicion);
		assertEquals(3, posicion[0]);
		assertEquals(0, posicion[1]);
	}

	
	
	@Test
	void testBuscarComisionInexistente() {
		CronogramaGeneral cronograma = new CronogramaGeneral();
		assertNull(cronograma.buscarComision("Inexistente"));
	}

	
	
	@Test
	void testToString() {
		CronogramaGeneral cronograma = new CronogramaGeneral();
		cronograma.asignarComision(0, 0, "AyP1");
		String texto = cronograma.toString();
		assertTrue(texto.contains("Lunes"));
		assertTrue(texto.contains("AyP1"));
		assertTrue(texto.contains("Libre"));
	}

	
	
	@Test
	void testDiaInvalidoAsignar() {
		CronogramaGeneral cronograma = new CronogramaGeneral();
		assertThrows(Error.class, () -> {
			cronograma.asignarComision(-1, 0, "AyP1");
		});
	}

	
	
	@Test
	void testTurnoInvalidoAsignar() {
		CronogramaGeneral cronograma = new CronogramaGeneral();
		assertThrows(Error.class, () -> {
			cronograma.asignarComision(0, 3, "AyP1");
		});
	}

	
	
	@Test
	void testSabadoTurnoInvalido() {
		CronogramaGeneral cronograma = new CronogramaGeneral();
		assertThrows(Error.class, () -> {
			cronograma.asignarComision(5, 1, "AyP1");
		});
	}

	
	
	@Test
	void testNombreComisionInvalido() {
		CronogramaGeneral cronograma = new CronogramaGeneral();
		assertThrows(Error.class, () -> {
			cronograma.asignarComision(0, 0, "");
		});
	}

	
	
	@Test
	void testConsultarHorarioDiaInvalido() {
		CronogramaGeneral cronograma = new CronogramaGeneral();
		assertThrows(Error.class, () -> {
			cronograma.consultarHorario(8, 0);
		});
	}

	
	
	@Test
	void testConsultarHorarioTurnoInvalido() {
		CronogramaGeneral cronograma = new CronogramaGeneral();
		assertThrows(Error.class, () -> {
			cronograma.consultarHorario(0, 5);
		});
	}

	
	
	@Test
	void testBuscarComisionNombreNulo() {
		CronogramaGeneral cronograma = new CronogramaGeneral();
		assertThrows(Error.class, () -> {
			cronograma.buscarComision(null);
		});
	}
}