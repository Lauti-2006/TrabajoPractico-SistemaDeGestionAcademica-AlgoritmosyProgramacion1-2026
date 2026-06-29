package sistemaDeGestionAcademica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AlumnoTest {

	@Test
	void testConstructor() {
		Alumno alumno = new Alumno("Juan Pérez", 12, 8.0);

		assertEquals("juan perez", alumno.obtenerNombre());
		assertEquals(12, alumno.obtenerLegajo());
		assertEquals(8.0, alumno.obtenerPromedio());
	}

	@Test
	void testCambiarNombre() {
		Alumno alumno = new Alumno("Juan", 19, 7.0);
		alumno.cambiarNombre("María Gómez");
		assertEquals("maria gomez", alumno.obtenerNombre());
	}

	@Test
	void testCambiarLegajo() {
		Alumno alumno = new Alumno("Juan", 19, 8.0);
		alumno.cambiarLegajo(12);
		assertEquals(12, alumno.obtenerLegajo());
	}

	@Test
	void testCambiarPromedio() {
		Alumno alumno = new Alumno("Juan", 4, 9.0);
		alumno.cambiarPromedio(10);
		assertEquals(10, alumno.obtenerPromedio());
	}

	@Test
	void testNombreNulo() {
		assertThrows(Error.class, () -> new Alumno(null, 11, 4.0));
	}

	@Test
	void testNombreVacio() {
		assertThrows(Error.class, () -> new Alumno("", 20, 8.0));
	}

	@Test
	void testPromedioNegativo() {
		assertThrows(Error.class, () -> new Alumno("Pedro", 13, -1.0));
	}

	@Test
	void testToString() {
		Alumno alumno = new Alumno("Ana", 1, 8.0);
		String esperado = "[Alumno]\n" + "Nombre: ana | Legajo: 1 | Promedio: 8.0";
		assertEquals(esperado, alumno.toString());
	}

	@Test
	void testNormalizacionNombre() {
		Alumno alumno = new Alumno("María", 22, 9.0);
		assertEquals("maria", alumno.obtenerNombre());
	}

	@Test
	void testEliminarEspaciosNombre() {
		Alumno alumno = new Alumno("  María  ", 22, 9.0);
		assertEquals("maria", alumno.obtenerNombre());
	}
}