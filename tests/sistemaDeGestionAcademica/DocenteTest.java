package sistemaDeGestionAcademica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DocenteTest {

	@Test
	void testConstructor() {
		Docente docente = new Docente("Juan Pérez", "Programación I", 10);

		assertEquals("juan perez", docente.obtenerNombre());
		assertEquals("Programación I", docente.obtenerCatedra());
		assertEquals(10, docente.obtenerAntiguedad());
	}

	@Test
	void testCambiarNombre() {
		Docente docente = new Docente("Juan", "AyP", 5);
		docente.cambiarNombre("María Gómez");
		assertEquals("maria gomez", docente.obtenerNombre());
	}

	@Test
	void testCambiarCatedra() {
		Docente docente = new Docente("Juan", "AyP", 5);
		docente.cambiarCatedra("Programación II");
		assertEquals("Programación II", docente.obtenerCatedra());
	}

	@Test
	void testCambiarAntiguedad() {
		Docente docente = new Docente("Juan", "AyP", 5);
		docente.cambiarAntiguedad(20);
		assertEquals(20, docente.obtenerAntiguedad());
	}

	@Test
	void testNombreNulo() {
		assertThrows(Error.class, () -> new Docente(null, "AyP", 5));
	}

	@Test
	void testNombreVacio() {
		assertThrows(Error.class, () -> new Docente("", "AyP", 5));
	}

	@Test
	void testCatedraNula() {
		assertThrows(Error.class, () -> new Docente("Juan", null, 5));
	}

	@Test
	void testCatedraVacia() {
		assertThrows(Error.class, () -> new Docente("Juan", "", 5));
	}

	@Test
	void testAntiguedadNegativa() {
		assertThrows(Error.class, () -> new Docente("Juan", "AyP", -1));
	}

	@Test
	void testToString() {
		Docente docente = new Docente("pepusito", "Programación I", 8);
		String esperado = "[Docente]\n" + "Nombre: pepusito | Catedra: Programación I | Antiguedad: 8";
		assertEquals(esperado, docente.toString());
	}

	@Test
	void testNormalizacionNombre() {
		Docente docente = new Docente("pepusito", "AyP", 5);
		assertEquals("pepusito", docente.obtenerNombre());
	}

	@Test
	void testEliminarEspaciosNombre() {
		Docente docente = new Docente("pepusito", "AyP", 5);
		assertEquals("pepusito", docente.obtenerNombre());
	}
}