package sistemaDeGestionAcademica;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComisionTest {
	Comision com;

	@BeforeEach
	void inicializar() {
		com = new Comision("Algebra", 3, 2);
	}
	
	@Test
	void buscarAlumnoPorLegajoTest() {
		Alumno alumno = new Alumno("martina", 1287, 6.5);
		com.darAlumnoDeAlta(alumno);
		assertEquals(alumno, com.buscarAlumnoPorLegajo(1287));
	}
	
	@Test
	void buscarAlumnoPorLegajoErroneoTest() {
		Alumno alumno = new Alumno("martina", 1287, 6.5);
		Alumno alumno2 = new Alumno("carlos", 5155, 8.0);
		com.darAlumnoDeAlta(alumno);
		com.darAlumnoDeAlta(alumno2);
		assertEquals(null, com.buscarAlumnoPorLegajo(1255));
	}
	
	@Test
	void buscarAlumnoPorLegajoEnComisionVaciaTest() {
	    assertEquals(null, com.buscarAlumnoPorLegajo(1287));
	}
	
	@Test
	void buscarAlumnoPorMismoLegajoDevuelvePrimeroTest() {
	    Alumno alumno = new Alumno("martina", 1287, 6.5);
	    Alumno alumno2 = new Alumno("jesus", 1287, 6.0);
	    com.darAlumnoDeAlta(alumno);
	    com.darAlumnoDeAlta(alumno2);
	    assertEquals(alumno, com.buscarAlumnoPorLegajo(1287));
	}
	
	@Test
	void buscarUnSoloAlumnoEnComisionTest() {
		Alumno alumno = new Alumno("carlos", 5155, 8.0);
		com.darAlumnoDeAlta(alumno);
		assertEquals(alumno, com.buscarAlumnoPorNombre("carlos"));
	}

	@Test
	void buscarAlumnoEnComisionTest() {
		Alumno[] alumnos = {
				new Alumno("Carlos", 5155, 8.0),
				new Alumno("Ana", 3216, 9.5),
				new Alumno("Juan", 4120, 7.0)
				};
		for (int i = 0; i < alumnos.length; i++) {
			com.darAlumnoDeAlta(alumnos[i]);
		}
		assertEquals(alumnos[1], com.buscarAlumnoPorNombre("ana"));
	}
	
	@Test
	void buscarAlumnoQueNoEstaEnComisionTest() {
		Alumno[] alumnos = {
				new Alumno("Juanchi", 4141, 5.7),
				new Alumno("Susana", 1235, 8.9),
				new Alumno("Facundo", 6163, 2.6),
		};
		for (int i = 0; i < alumnos.length; i++) {
			com.darAlumnoDeAlta(alumnos[i]);
		}
		assertThrows(Error.class, () -> com.buscarAlumnoPorNombre("juan"));
	}
	
	@Test
	void buscarAlumnoEnComisionVaciaTest() {
	    assertThrows(Error.class, () -> com.buscarAlumnoPorNombre("carlos"));
	}
	
	@Test
	void buscarAlumnoEliminadoTest() {
	    Alumno alumno = new Alumno("Carlos", 5155, 8.0);
	    com.darAlumnoDeAlta(alumno);
	    com.eliminarAlumnoPorLegajo(5155);
	    assertThrows(Error.class, () -> com.buscarAlumnoPorNombre("carlos"));
	}
	
	//test de ordenamiento: validar la jerarquía de promedios (consigna).
	@Test
	void obtenerAlumnosOrdenadosPorPromedioTest() {
	    Alumno[] alumnos = {
	            new Alumno("Fernando", 4141, 5.7),
	            new Alumno("Susana", 1235, 8.9),
	            new Alumno("Facundo", 6163, 2.6),
	    };
	    for (int i = 0; i < alumnos.length; i++) {
	        com.darAlumnoDeAlta(alumnos[i]);
	    }
	    Alumno[] esperado = {
	            alumnos[1],
	            alumnos[0],
	            alumnos[2]
	    };
	    assertArrayEquals(esperado, com.obtenerAlumnosOrdenados());
	}
	
	@Test
	void obtenerAlumnosOrdenadosComisionVaciaTest() {
	    Alumno[] vacio = com.obtenerAlumnosOrdenados();
	    assertEquals(0, vacio.length);
	}
	
	@Test
	void obtenerAlumnosOrdenadosUnSoloAlumnoTest() {
	    Alumno alumno = new Alumno("Juana", 1163, 7.4);
	    com.darAlumnoDeAlta(alumno);
	    Alumno[] resultado = com.obtenerAlumnosOrdenados();
	    assertEquals(alumno, resultado[0]);
	}
	
	@Test
	void obtenerAlumnosOrdenadosEmpatesTest() {
	    Alumno alumno = new Alumno("Ian", 1544, 8.2);
	    Alumno alumno2 = new Alumno("Facundo", 2256, 8.2);
	    Alumno alumno3 = new Alumno("Cristian", 3159, 5.6);
	    com.darAlumnoDeAlta(alumno);
	    com.darAlumnoDeAlta(alumno2);
	    com.darAlumnoDeAlta(alumno3);
	    Alumno[] resultado = com.obtenerAlumnosOrdenados();
	    assertEquals(8.2, resultado[0].obtenerPromedio());
	    assertEquals(8.2, resultado[1].obtenerPromedio());
	    assertEquals(5.6, resultado[2].obtenerPromedio());
	}
	
	@Test
	void darAlumnoDeAltaTest() {
	    Alumno alumno = new Alumno("Santiago", 1566, 5.5);
	    assertTrue(com.darAlumnoDeAlta(alumno));
	    assertEquals(alumno, com.buscarAlumnoPorLegajo(1566));
	}
	
	@Test
	void darAlumnoDeAltaDuplicadoTest() {
	    Alumno alumno = new Alumno("alexis", 47727, 6.5);
	    Alumno alumno2 = new Alumno("german", 47727, 9.0);
	    com.darAlumnoDeAlta(alumno);
	    assertFalse(com.darAlumnoDeAlta(alumno2));
	}
	
	//este test es para dar de alta al alumno(pedia la consigna).
	@Test
	void darAlumnoDeAltaComisionCerradaTest() {
	    com.cerrarInscripcion();
	    Alumno alumno = new Alumno("sofia", 5417, 7.1);
	    assertFalse(com.darAlumnoDeAlta(alumno));
	}
	
	@Test
	void darAlumnoDeAltaNullTest() {
	    assertFalse(com.darAlumnoDeAlta(null));
	}
	
	//este test es para dar de baja al alumno.
	@Test
	void eliminarAlumnoExistenteTest() {
	    Alumno alumno = new Alumno("valentin", 8335, 5.8);
	    com.darAlumnoDeAlta(alumno);
	    com.eliminarAlumnoPorLegajo(8335);
	    assertEquals(null, com.buscarAlumnoPorLegajo(8335));
	}
	
	@Test
	void eliminarAlumnoInexistenteTest() {
	    assertFalse(com.eliminarAlumnoPorLegajo(1544));
	}
	
	@Test
	void modificarAlumnoTest() {
	    Alumno alumno = new Alumno("ignasio", 5417, 5.5);
	    com.darAlumnoDeAlta(alumno);
	    assertTrue(com.modificarAlumno(5417, "ignacio", 5.5));
	    assertEquals("ignacio", com.buscarAlumnoPorLegajo(5417).obtenerNombre());
	    assertEquals(5.5, com.buscarAlumnoPorLegajo(5417).obtenerPromedio());
	}
	
	@Test
	void modificarAlumnoInexistenteTest() {
	    assertFalse(com.modificarAlumno(6549, "lautaro", 7.0));
	}
	
	@Test
	void listarAlumnosVacioTest() {
	    assertEquals("No Hay Alumnos Cargados", com.listarAlumnos());
	}
	
	@Test
	void darDeAltaCuandoCupoCompletoTest() {
	    com.darAlumnoDeAlta(new Alumno("fausto", 1, 1));
	    com.darAlumnoDeAlta(new Alumno("jazmin", 2, 1));
	    com.darAlumnoDeAlta(new Alumno("hector", 3, 1));
	    assertFalse(com.darAlumnoDeAlta(new Alumno("guido", 4, 1)));
	}

	@Test
	void cerrarInscripcionTest() {
	    com.cerrarInscripcion();
	    assertFalse(com.estaAbierta());
	}
	
	@Test
	void abrirInscripcionTest() {
	    assertTrue(com.estaAbierta());
	}
	
	@Test
	void crearComisionTest() {
	    assertEquals("Algebra", com.obtenerNombre());
	    assertTrue(com.estaAbierta());
	}
}