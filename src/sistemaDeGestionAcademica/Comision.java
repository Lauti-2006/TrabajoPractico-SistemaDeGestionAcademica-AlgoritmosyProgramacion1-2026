package sistemaDeGestionAcademica;

public class Comision {
	private String nombre;
	private Alumno[] alumnos;
	private Docente[] docentes;
	private boolean abierta;
	
	/**
	 * pre: nombre no es nulo ni está vacío, cupoAlumnos es mayor a 0, cupoDocentes es mayor a 0.
	 * post: inicializa la Comisión con el nombre indicado (sin espacios al inicio/final),
	 * crea los arreglos de alumnos y docentes con el tamaño de los cupos, y deja la inscripción abierta.
	 * @param nombre nombre de la comisión
	 * @param cupoAlumnos cantidad máxima de alumnos que puede contener la comisión
	 * @param cupoDocentes cantidad máxima de docentes que puede contener la comisión
	 * @throws Error si el nombre es nulo o esta vacio
	 * @throws Error si el cupo de alumnos es cero o negativo
	 * @throws Error si el cupo de docentes es cero o negativo 
	 */
	public Comision(String nombre, int cupoAlumnos, int cupoDocentes) {
		cambiarNombre(nombre);

		if (cupoAlumnos <= 0) {
			throw new Error("El Cupo de Alumnos No Debe ser Menor o Igual a 0");
		}

		if (cupoDocentes <= 0) {
			throw new Error("El Cupo de Docentes No Debe ser Menor o Igual a 0");
		}

		this.alumnos = new Alumno[cupoAlumnos];
		this.docentes = new Docente[cupoDocentes];
		this.abierta = true;
	}
	
	/**
	 * post: devuelve el nombre de la comisión.
	 * @return nombre de la comisión
	 */
	public String obtenerNombre() {
		return this.nombre;
	}
	
	/**
	 * pre : nombre no es nulo ni está vacío.
	 * post: cambia el nombre de la comisión.
	 * @param nombre nuevo nombre de la comisión
	 * @throws Error si el nombre es nulo o esta vacio
	 */
	public void cambiarNombre(String nombre) {
		if (nombre == null || nombre.isBlank()) {
			throw new Error("El Nombre Ingresado No Puede estar Vacio");
		}

		this.nombre = nombre.trim();
	}
	
	/**
	 * post: devuelve si la comisión esta abierta para inscripciones.
	 * @return true si esta abierta, false si esta cerrada
	 */
	public boolean estaAbierta() {
		return this.abierta;
	}
	
	/**
	 * post: abre las inscripciones de la comisión.
	 */
	public void abrirInscripcion() {
		this.abierta = true;
	}
	
	/**
	 * post: cierra las inscripciones de la comisión.
	 */
	public void cerrarInscripcion() {
		this.abierta = false;
	}
	
	/**
	 * pre : la comision esta abierta, el alumno no es nulo y no existe otro alumno con el mismo legajo.
	 * post: si hay lugar disponible, agrega el alumno al primer espacio libre del arreglo. 
	 * Si no se puede agregar, no modifica el arreglo.
	 * @param alumno alumno a agregar
	 * @return true si el alumno fue agregado, false en caso contrario
	 */
	public boolean agregarAlumno(Alumno alumno) {
		boolean agregado = false;

		if (this.abierta && alumno != null && buscarAlumnoPorLegajo(alumno.obtenerLegajo()) == null) {
			int index = 0;
			while (index < this.alumnos.length && !agregado) {
				if (this.alumnos[index] == null) {
					this.alumnos[index] = alumno;
					agregado = true;
				}
				index++;
			}
		}
		return agregado;
	}
	
	/**
	 * post: busca un alumno dentro del arreglo por su legajo.
	 * @param legajo número de legajo del alumno buscado
	 * @return el alumno encontrado, o null si no existe en la comisión
	 */
	public Alumno buscarAlumnoPorLegajo(int legajo) {
		Alumno alumnoEncontrado = null;
		int index = 0;

		while (index < this.alumnos.length && alumnoEncontrado == null) {
			if (this.alumnos[index] != null && this.alumnos[index].obtenerLegajo() == legajo) { // Ponemos el "alumnos[index] != null" por si creamos un Array de Alumnos de tamaño 10, pero solo agregamos 3, las otras posiciones serian null
				alumnoEncontrado = this.alumnos[index];
			}
			index++;
		}

		return alumnoEncontrado;
	}
	
	/**
	 * pre : legajo es mayor a 0.
	 * post: si encuentra un alumno con ese legajo, lo elimina del arreglo, dejando su posicion en nulo.
	 * Si no lo encuentra, no modifica el arreglo.
	 * @param alumno alumno a eliminar
	 * @return true si el alumno fue eliminado exitosamente, false en caso contrario
	 */
	public boolean eliminarAlumnoPorLegajo(int legajo) {
		boolean alumnoEliminado = false;

		int index = 0;
		while (index < this.alumnos.length && !alumnoEliminado) {
			if (this.alumnos[index] != null && this.alumnos[index].obtenerLegajo() == legajo) {
				this.alumnos[index] = null;
				alumnoEliminado = true;
			}
			index++;
		}

		return alumnoEliminado;
	}
	
	/**
	 * pre: legajo es mayor a 0, nuevoNombre no es nulo ni esta vacio y nuevoPromedio esta entre 1.0 y 10.0.
	 * post: si enceuntra el alumno modifica su nombre y su promedio. Sino lo encuentra, no modifica nada.  
	 * @param legajo legajo del alumno a modificar
	 * @param nuevoNombre nuevo nombre del alumno
	 * @param nuevoPromedio nuevo promedio del alumno
	 * @return true si el alumno fue modificado, false en caso contrario
	 */
	public boolean modificarAlumno(int legajo, String nuevoNombre, double nuevoPromedio) {
		boolean alumnoModificado = false;
		
		Alumno alumnoEncontrado = buscarAlumnoPorLegajo(legajo);
		
		if(alumnoEncontrado != null) {
			alumnoEncontrado.cambiarNombre(nuevoNombre);
			alumnoEncontrado.cambiarPromedio(nuevoPromedio);
			alumnoModificado = true;
		}
		
		return alumnoModificado;
	}
	
	
	/**
	 * pre: la comision esta abierta, el docente no es nulo.
	 * post: si hay lugar disponible, agrega el docente al primer espacio libre del arreglo,
	 * sino se puede agregar, no modifica el arreglo
	 * @param docente docente a agregar
	 * @return true si el docente fue agregado, false en caso contrario
	 */
	public boolean agregarDocente(Docente docente) {
		boolean agregado = false;

		if (this.abierta && docente != null) {
			int index = 0;

			while (index < this.docentes.length && !agregado) {
				if (this.docentes[index] == null) {
					this.docentes[index] = docente;
					agregado = true;
				}
				index++;
			}
		}

		return agregado;
	}
	
	/**
	 * pre: catedra no es nulo ni esta vacia.
	 * post: si se encuentra al docente con esa catedra, lo elimina del arreglo dejando su posicion en nulo.
	 * Sino lo encuentra, no modifica el arreglo.
	 * @param catedra catedra del docente a eliminar
	 * @return true si el docente fue eliminado, false en caso contrario
	 */
	public boolean eliminarDocente(String catedra) {
		boolean docenteEliminado = false;

		if (this.abierta && catedra != null && !catedra.isBlank()) {
			int index = 0;

			while (index < this.docentes.length && !docenteEliminado) {
				if (this.docentes[index] != null && 
					this.docentes[index].obtenerCatedra().equals(catedra.trim())) {
					this.docentes[index] = null;
					docenteEliminado = true;
				}
				index++;
			}
		}
		return docenteEliminado;
	}
	
	/**
	 * post: devuelve un texto con todos los alumnos cargados de la comision. 
	 * Devuelve un mensaje indicando si no hay alumnos cargados.
	 * @return listado de Alumnos
	 */
	public String listarAlumnos() {
		String resultado = "";
		
		for(int i = 0; i < this.alumnos.length; i++) {
			if(this.alumnos[i] != null) {
				resultado += this.alumnos[i].toString() + "\n";
			}
		}
		
		if(resultado.isEmpty()) {
			resultado = "No Hay Alumnos Cargados";
		}
		
		return resultado;
	}
	
	/**
	 * post: devuelve un texto con todos los docentes cargados de la comision. 
	 * Devuelve un mensaje indicando si no hay docentes cargados.
	 * @return listado de Docentes
	 */
	public String listarDocentes() {
		String resultado = "";
		
		for(int i = 0; i < this.docentes.length; i++) {
			if(this.docentes[i] != null) {
				resultado += this.docentes[i].toString() + "\n";
			}
		}
		
		if(resultado.isEmpty()) {
			resultado = "No Hay Docentes Cargados";
		}
		
		return resultado;
	}
	
	/**
	 * post: devuelve los datos de la comision en formato texto.
	 * @return datos de la comision
	 */
	@Override
	public String toString() {
		return "[Comision]" + "\nNombre: " + obtenerNombre() + " | Esta Abierta: " + (estaAbierta() ? "Si" : "No");
	}
}