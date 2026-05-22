package comision;

import alumno.Alumno;
import docente.Docente;

public class Comision {
	private String nombre;
	private Alumno[] alumnos;
	private Docente[] docentes;
	private boolean abierta;
	
	/**
	 * pre : nombre no es nulo ni está vacío, cupoAlumnos es mayor a 0, cupoDocentes es mayor a 0.
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
		if (nombre == null || nombre.isBlank()) {
			throw new Error("El Nombre Ingresado No Puede estar Vacio");
		}

		if (cupoAlumnos <= 0) {
			throw new Error("El Cupo de Alumnos No Debe ser Menor o Igual a 0");
		}

		if (cupoDocentes <= 0) {
			throw new Error("El Cupo de Docentes No Debe ser Menor o Igual a 0");
		}

		this.nombre = nombre.trim();
		this.alumnos = new Alumno[cupoAlumnos];
		this.docentes = new Docente[cupoDocentes];
		this.abierta = true;
	}
	
	/**
	 * post: devuelve el nombre de la comisión.
	 * @return nombre de la comisión
	 */
	public String getNombre() {
		return this.nombre;
	}
	
	/**
	 * pre : nombre no es nulo ni está vacío.
	 * post: cambia el nombre de la comisión.
	 * @param nombre nuevo nombre de la comisión
	 * @throws Error si el nombre es nulo o esta vacio
	 */
	public void setNombre(String nombre) {
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

		if (abierta && alumno != null && buscarAlumnoPorLegajo(alumno.getLegajo()) == null) {
			int index = 0;
			while (index < alumnos.length && !agregado) {
				if (alumnos[index] == null) {
					alumnos[index] = alumno;
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
			if (this.alumnos[index] != null && this.alumnos[index].getLegajo() == legajo) { // Ponemos el "alumnos[index] != null" por si creamos un Array de Alumnos de tamaño 10, pero solo agregamos 3, las otras posiciones serian null
				alumnoEncontrado = this.alumnos[index];
			}
			index++;
		}

		return alumnoEncontrado;
	}
	
	/**
	 * pre : legajo pertenece a un alumno existente en la comision
	 * post: si encuentra un alumno con ese legajo, lo elimina del arreglo, dejando su posicion en null
	 * @param alumno alumno a eliminar
	 * @return true si el alumno fue eliminado exitosamente, false en caso contrario
	 */
	public boolean eliminarAlumnoPorLegajo(int legajo) {
		boolean alumnoEliminado = false;

		int index = 0;
		while (index < this.alumnos.length && !alumnoEliminado) {
			if (this.alumnos[index] != null && this.alumnos[index].getLegajo() == legajo) {
				this.alumnos[index] = null;
				alumnoEliminado = true;
			}
			index++;
		}

		return alumnoEliminado;
	}
	
	/**
	 * pre: legajo pertenece a un alumno existente a la comision
	 * post: si encuentra el alumno modifica su nombre y el promedio
	 * @param legajo legajo del alumno a modificar
	 * @param nuevoNombre nuevo nombre del alumno
	 * @param nuevoPromedio nuevo promedio del alumno
	 * @return true si el alumno fue modificado, false en caso contrario
	 */
	public boolean modificarAlumno(int legajo, String nuevoNombre, double nuevoPromedio) {
		boolean alumnoModificado = false;
		
		Alumno alumnoEncontrado = buscarAlumnoPorLegajo(legajo);
		
		if(alumnoEncontrado != null) {
			alumnoEncontrado.setNombre(nuevoNombre);
			alumnoEncontrado.setPromedio(nuevoPromedio);
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

		if (abierta && docente != null) {
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
	 * pre: la comision debe estar abierta, el docente no puede ser nulo.
	 * post: si se encuentra al docente, lo elimina dejando su posicion en nulo.
	 * @param docente docente a eliminar
	 * @return true si el docente fue eliminado, false en caso contrario
	 */
	public boolean eliminarDocente(Docente docente) {
		boolean docenteEliminado = false;

		if (abierta && docente != null) {
			int index = 0;

			while (index < this.docentes.length && !docenteEliminado) {
				if (this.docentes[index] == docente) {
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
		boolean hayAlumnos = false;
		
		for(int i = 0; i < this.alumnos.length; i++) {
			if(this.alumnos[i] != null) {
				resultado += this.alumnos[i].mostrarDatos() + "\n";
				hayAlumnos = true;
			}
		}
		
		if(!hayAlumnos) {
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
		boolean hayDocentes = false;
		
		for(int i = 0; i < this.docentes.length; i++) {
			if(this.docentes[i] != null) {
				resultado += this.docentes[i].mostrarDatos() + "\n";
				hayDocentes = true;
			}
		}
		
		if(!hayDocentes) {
			resultado = "No Hay Docentes Cargados";
		}
		
		return resultado;
	}
	
	/**
	 * post: devuelve los datos de la comision en formato texto.
	 * @return datos de la comision
	 */
	public String mostrarDatos() {
		return "[Comision]" + "\nNombre: " + getNombre() + " | Esta Abierta: " + (estaAbierta() ? "Si" : "No");
	}
}