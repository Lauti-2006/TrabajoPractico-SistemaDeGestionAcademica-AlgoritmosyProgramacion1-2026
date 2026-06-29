package sistemaDeGestionAcademica;

public class Comision {
    private String nombre;
    private Alumno[] alumnos;
    private Docente[] docentes;
    private boolean abierta;
    private CronogramaGeneral cronograma;

    /**
     * Inicializa una nueva comisión con el nombre indicado, los arreglos de alumnos y docentes
     * con los tamaños especificados, y la inscripción abierta. Además, crea un cronograma vacío.
     * 
     * @pre nombre no es nulo ni está vacío.
     * @pre cupoAlumnos es mayor a 0.
     * @pre cupoDocentes es mayor a 0.
     * @post se crea una Comision con el nombre normalizado (sin espacios al inicio/final),
     * los arreglos de tamaño fijo, inscripción abierta y cronograma vacío.
     * 
     * @param nombre nombre de la comisión (no nulo, no vacío)
     * @param cupoAlumnos cantidad máxima de alumnos que puede contener la comisión (mayor a 0)
     * @param cupoDocentes cantidad máxima de docentes que puede contener la comisión (mayor a 0)
     * @throws Error si el nombre es nulo o está vacío
     * @throws Error si el cupo de alumnos es menor o igual a 0
     * @throws Error si el cupo de docentes es menor o igual a 0
     */
    public Comision(String nombre, int cupoAlumnos, int cupoDocentes) {
        this.cambiarNombre(nombre);

        if (cupoAlumnos <= 0) {
            throw new Error("El cupo de alumnos no debe ser menor o igual a 0.");
        }
        if (cupoDocentes <= 0) {
            throw new Error("El cupo de docentes no debe ser menor o igual a 0.");
        }

        this.alumnos = new Alumno[cupoAlumnos];
        this.docentes = new Docente[cupoDocentes];
        this.abierta = true;
        this.cronograma = new CronogramaGeneral();
    }

    /**
     * Devuelve el nombre de la comisión.
     * 
     * @post devuelve el nombre actual de la comisión.
     * @return nombre de la comisión
     */
    public String obtenerNombre() {
        return this.nombre;
    }

    /**
     * Cambia el nombre de la comisión por el valor indicado.
     * 
     * @pre nombre no es nulo ni está vacío.
     * @post el nombre de la comisión se actualiza al nuevo valor (sin espacios al inicio/final).
     * 
     * @param nombre nuevo nombre de la comisión (no nulo, no vacío)
     * @throws Error si el nombre es nulo o está vacío
     */
    public void cambiarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new Error("El nombre ingresado no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    /**
     * Indica si la comisión está abierta para inscripciones.
     * 
     * @post devuelve el estado actual de la comisión.
     * @return true si está abierta, false si está cerrada
     */
    public boolean estaAbierta() {
        return this.abierta;
    }

    /**
     * Abre las inscripciones de la comisión.
     * 
     * @post la comisión queda abierta para inscripciones.
     */
    public void abrirInscripcion() {
        this.abierta = true;
    }

    /**
     * Cierra las inscripciones de la comisión.
     * 
     * @post la comisión queda cerrada para inscripciones.
     */
    public void cerrarInscripcion() {
        this.abierta = false;
    }

    /**
     * Da de alta a un alumno en la comisión.
     * 
     * @pre la comisión está abierta.
     * @pre el alumno no es nulo.
     * @pre no existe otro alumno con el mismo legajo.
     * @post si hay lugar disponible, el alumno se agrega en el primer espacio libre del arreglo.
     * Si no se puede agregar, el arreglo no se modifica.
     * 
     * @param alumno alumno a agregar
     * @return true si el alumno fue agregado, false en caso contrario
     */
    public boolean darAlumnoDeAlta(Alumno alumno) {
        boolean agregado = false;

        if (this.abierta && alumno != null && this.buscarAlumnoPorLegajo(alumno.obtenerLegajo()) == null) {
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
     * Busca un alumno dentro de la comisión por su nombre.
     * 
     * @post si el alumno existe, lo devuelve; si no, lanza una excepción.
     * 
     * @param nombre nombre del alumno buscado
     * @return el alumno encontrado
     * @throws Error si el alumno no se encuentra en la comisión
     */
    public Alumno buscarAlumnoPorNombre(String nombre) {
        Alumno alumnoEncontrado = null;
        for (int i = 0; i < this.alumnos.length; i++) {
            if (this.alumnos[i] != null && this.alumnos[i].obtenerNombre().equals(nombre)) {
                alumnoEncontrado = this.alumnos[i];
            }
        }
        if (alumnoEncontrado == null) {
            throw new Error("El alumno no está en la comisión.");
        }
        return alumnoEncontrado;
    }

    /**
     * Busca un alumno dentro de la comisión por su legajo.
     * 
     * @post devuelve el alumno encontrado o null si no existe.
     * 
     * @param legajo número de legajo del alumno buscado
     * @return el alumno encontrado, o null si no existe en la comisión
     */
    public Alumno buscarAlumnoPorLegajo(int legajo) {
        Alumno alumnoEncontrado = null;
        int index = 0;

        while (index < this.alumnos.length && alumnoEncontrado == null) {
            if (this.alumnos[index] != null && this.alumnos[index].obtenerLegajo() == legajo) {
                alumnoEncontrado = this.alumnos[index];
            }
            index++;
        }

        return alumnoEncontrado;
    }

    /**
     * Elimina un alumno de la comisión por su legajo.
     * 
     * @pre legajo es mayor a 0.
     * @post si encuentra el alumno, lo elimina del arreglo dejando su posición en null.
     * Si no lo encuentra, el arreglo no se modifica.
     * 
     * @param legajo legajo del alumno a eliminar
     * @return true si el alumno fue eliminado, false en caso contrario
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
     * Modifica el nombre y el promedio de un alumno existente.
     * 
     * @pre legajo es mayor a 0.
     * @pre nuevoNombre no es nulo ni está vacío.
     * @pre nuevoPromedio está entre 0.0 y 10.0 (inclusive).
     * @post si encuentra el alumno, modifica su nombre y su promedio.
     * Si no lo encuentra, no modifica nada.
     * 
     * @param legajo legajo del alumno a modificar
     * @param nuevoNombre nuevo nombre del alumno
     * @param nuevoPromedio nuevo promedio del alumno
     * @return true si el alumno fue modificado, false en caso contrario
     */
    public boolean modificarAlumno(int legajo, String nuevoNombre, double nuevoPromedio) {
        boolean alumnoModificado = false;
        Alumno alumnoEncontrado = this.buscarAlumnoPorLegajo(legajo);

        if (alumnoEncontrado != null) {
            alumnoEncontrado.cambiarNombre(nuevoNombre);
            alumnoEncontrado.cambiarPromedio(nuevoPromedio);
            alumnoModificado = true;
        }

        return alumnoModificado;
    }

    /**
     * Da de alta a un docente en la comisión.
     * 
     * @pre la comisión está abierta.
     * @pre el docente no es nulo.
     * @post si hay lugar disponible, agrega el docente al primer espacio libre del arreglo.
     * Si no se puede agregar, el arreglo no se modifica.
     * 
     * @param docente docente a agregar
     * @return true si el docente fue agregado, false en caso contrario
     */
    public boolean darDocenteDeAlta(Docente docente) {
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
     * Elimina un docente de la comisión por su cátedra.
     * 
     * @pre catedra no es nula ni está vacía.
     * @post si encuentra al docente con esa cátedra, lo elimina del arreglo dejando su posición en null.
     * Si no lo encuentra, el arreglo no se modifica.
     * 
     * @param catedra cátedra del docente a eliminar
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
     * Devuelve un listado textual de todos los alumnos cargados en la comisión.
     * 
     * @post devuelve una cadena con los datos de los alumnos o un mensaje si no hay.
     * @return listado de alumnos
     */
    public String listarAlumnos() {
        String resultado = "";

        for (int i = 0; i < this.alumnos.length; i++) {
            if (this.alumnos[i] != null) {
                resultado += this.alumnos[i].toString() + "\n";
            }
        }

        if (resultado.isEmpty()) {
            resultado = "No hay alumnos cargados.";
        }

        return resultado;
    }

    /**
     * Devuelve un arreglo con los alumnos de la comisión ordenados de mayor a menor promedio.
     * 
     * @post devuelve un arreglo con los alumnos registrados ordenados por promedio descendente.
     * @return arreglo de alumnos ordenados
     */
    public Alumno[] obtenerAlumnosOrdenados() {
        int contadorDeAlumnos = 0;
        for (int i = 0; i < this.alumnos.length; i++) {
            if (this.alumnos[i] != null) {
                contadorDeAlumnos++;
            }
        }

        Alumno[] alumnosOrdenadosPorMayorPromedio = new Alumno[contadorDeAlumnos];
        int indiceDeGuardado = 0;
        for (int i = 0; i < this.alumnos.length; i++) {
            if (this.alumnos[i] != null) {
                alumnosOrdenadosPorMayorPromedio[indiceDeGuardado] = this.alumnos[i];
                indiceDeGuardado++;
            }
        }

        for (int i = 0; i < alumnosOrdenadosPorMayorPromedio.length; i++) {
            for (int j = 0; j < alumnosOrdenadosPorMayorPromedio.length - 1 - i; j++) {
                if (alumnosOrdenadosPorMayorPromedio[j].obtenerPromedio() <
                    alumnosOrdenadosPorMayorPromedio[j + 1].obtenerPromedio()) {
                    Alumno alumnoAuxiliar = alumnosOrdenadosPorMayorPromedio[j];
                    alumnosOrdenadosPorMayorPromedio[j] = alumnosOrdenadosPorMayorPromedio[j + 1];
                    alumnosOrdenadosPorMayorPromedio[j + 1] = alumnoAuxiliar;
                }
            }
        }

        return alumnosOrdenadosPorMayorPromedio;
    }

    /**
     * Devuelve un listado textual de todos los docentes cargados en la comisión.
     * 
     * @post devuelve una cadena con los datos de los docentes o un mensaje si no hay.
     * @return listado de docentes
     */
    public String listarDocentes() {
        String resultado = "";

        for (int i = 0; i < this.docentes.length; i++) {
            if (this.docentes[i] != null) {
                resultado += this.docentes[i].toString() + "\n";
            }
        }

        if (resultado.isEmpty()) {
            resultado = "No hay docentes cargados.";
        }

        return resultado;
    }

    /**
     * Asigna un turno en el cronograma para esta comisión.
     * 
     * @pre turno debe estar entre 0 y 2 (0=Mañana, 1=Tarde, 2=Noche).
     * @pre día debe estar entre 0 y 5 (0=Lunes, ..., 5=Sábado).
     * @pre materia no es nula ni está vacía.
     * @post si el horario está libre, la comisión queda asignada en el cronograma con el nombre de la materia.
     * Si el horario está ocupado, se lanza un error.
     * 
     * @param turno turno a asignar (0=Mañana, 1=Tarde, 2=Noche)
     * @param dia día a asignar (0=Lunes, ..., 5=Sábado)
     * @param materia nombre de la materia (cátedra)
     * @throws Error si el turno o día son inválidos
     * @throws Error si la materia es nula o vacía
     * @throws Error si el horario ya está ocupado
     */
    public void asignarTurno(int turno, int dia, String materia) {
        if (materia == null || materia.isBlank()) {
            throw new Error("El nombre de la cátedra no puede estar vacío.");
        }
        boolean asignado = this.cronograma.asignarComision(dia, turno, materia);
        if (!asignado) {
            throw new Error("El horario seleccionado ya está ocupado por otra comisión.");
        }
    }

    /**
     * Devuelve una copia de la matriz del cronograma para su visualización.
     * 
     * @post devuelve una copia de la matriz de 6x3 con los nombres de las comisiones asignadas.
     * @return matriz de String[][] con el cronograma
     */
    public String[][] obtenerCronograma() {
        return this.cronograma.obtenerAsignaciones();
    }

    /**
     * Devuelve los datos de la comisión en formato texto.
     * 
     * @post devuelve una representación textual de la comisión.
     * @return cadena con los datos de la comisión
     */
    @Override
    public String toString() {
        return "[Comision]\n" +
               "Nombre: " + this.obtenerNombre() +
               " | Esta abierta: " + (this.estaAbierta() ? "Sí" : "No");
    }
}