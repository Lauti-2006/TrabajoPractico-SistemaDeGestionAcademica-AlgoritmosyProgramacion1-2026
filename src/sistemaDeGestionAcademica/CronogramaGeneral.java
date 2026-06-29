package sistemaDeGestionAcademica;

public class CronogramaGeneral {
    private String[][] asignaciones;

    /**
     * Crea un cronograma con 6 días y 3 turnos por día, inicialmente sin comisiones asignadas.
     * 
     * @post se crea un cronograma con todas las posiciones en null (libres).
     */
    public CronogramaGeneral() {
        this.asignaciones = new String[6][3];
    }

    /**
     * Asigna una comisión a un día y turno específicos.
     * 
     * @pre día debe estar entre 0 y 5 (inclusive).
     * @pre turno debe estar entre 0 y 2 (inclusive).
     * @pre nombreComision no es nulo ni está vacío.
     * @pre si día es 5 (Sábado), turno debe ser 0 (Mañana).
     * @post si el horario estaba libre, la comisión queda asignada con el nombre indicado.
     * Si el horario ya estaba ocupado, no se modifica y retorna false.
     * 
     * @param dia día en el que se desea asignar la comisión (0=Lunes, ..., 5=Sábado)
     * @param turno turno en el que se desea asignar (0=Mañana, 1=Tarde, 2=Noche)
     * @param nombreComision nombre de la comisión a asignar (no nulo, no vacío)
     * @return true si la comisión pudo asignarse, false si el horario ya estaba ocupado
     * @throws Error si el día no es válido (fuera de 0-5)
     * @throws Error si el turno no es válido (fuera de 0-2)
     * @throws Error si se intenta asignar un turno no permitido en Sábado (solo Mañana)
     * @throws Error si el nombre de la comisión es nulo o vacío
     */
    public boolean asignarComision(int dia, int turno, String nombreComision) {
        if (dia < 0 || dia > 5) {
            throw new Error("Día erróneo. Debe estar entre 0 y 5.");
        }
        if (turno < 0 || turno > 2) {
            throw new Error("Turno inválido. Debe estar entre 0 y 2.");
        }
        if (dia == 5 && turno != 0) {
            throw new Error("El sábado solo puede ser a la mañana.");
        }
        if (nombreComision == null || nombreComision.isBlank()) {
            throw new Error("El nombre de la comisión no puede ser nulo ni vacío.");
        }
        if (this.asignaciones[dia][turno] != null) {
            return false;
        }
        this.asignaciones[dia][turno] = nombreComision;
        return true;
    }

    /**
     * Libera un horario (día y turno) dejándolo libre.
     * 
     * @pre día debe estar entre 0 y 5 (inclusive).
     * @pre turno debe estar entre 0 y 2 (inclusive).
     * @pre si día es 5 (Sábado), turno debe ser 0 (Mañana).
     * @post si existía una comisión asignada, el horario queda libre y retorna true.
     * Si ya estaba libre, no se modifica y retorna false.
     * 
     * @param dia   día cuyo horario se desea liberar (0=Lunes, ..., 5=Sábado)
     * @param turno turno cuyo horario se desea liberar (0=Mañana, 1=Tarde, 2=Noche)
     * @return true si el horario fue liberado (había una comisión), false si ya estaba libre
     * @throws Error si el día no es válido (fuera de 0-5)
     * @throws Error si el turno no es válido (fuera de 0-2)
     * @throws Error si se intenta liberar un turno no permitido en Sábado (solo Mañana)
     */
    public boolean liberarHorario(int dia, int turno) {
        if (dia < 0 || dia > 5) {
            throw new Error("Día erróneo. Debe estar entre 0 y 5.");
        }
        if (turno < 0 || turno > 2) {
            throw new Error("Turno inválido. Debe estar entre 0 y 2.");
        }
        if (dia == 5 && turno != 0) {
            throw new Error("El sábado solo puede ser a la mañana.");
        }
        if (this.asignaciones[dia][turno] == null) {
            return false;
        }
        this.asignaciones[dia][turno] = null;
        return true;
    }

    /**
     * Consulta el nombre de la comisión asignada a un día y turno específicos.
     * 
     * @pre día debe estar entre 0 y 5 (inclusive).
     * @pre turno debe estar entre 0 y 2 (inclusive).
     * @post devuelve el nombre de la comisión asignada, o null si el horario está libre.
     * 
     * @param dia día a consultar (0=Lunes, ..., 5=Sábado)
     * @param turno turno a consultar (0=Mañana, 1=Tarde, 2=Noche)
     * @return el nombre de la comisión asignada, o null si el horario está libre
     * @throws Error si el día no es válido (fuera de 0-5)
     * @throws Error si el turno no es válido (fuera de 0-2)
     */
    public String consultarHorario(int dia, int turno) {
        if (dia < 0 || dia > 5) {
            throw new Error("Día erróneo. Debe estar entre 0 y 5.");
        }
        if (turno < 0 || turno > 2) {
            throw new Error("Turno inválido. Debe estar entre 0 y 2.");
        }
        return this.asignaciones[dia][turno];
    }

    /**
     * Devuelve una representación textual del cronograma.
     * Muestra todos los días, turnos y las comisiones asignadas o "Libre" si no hay.
     * 
     * @post devuelve una cadena con el detalle completo de las asignaciones del cronograma.
     * @return cadena con el cronograma en formato texto
     */
    @Override
    public String toString() {
        String[] dias = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado" };
        String[] turnos = { "Mañana", "Tarde", "Noche" };

        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < this.asignaciones.length; i++) {
            resultado.append(dias[i]).append("\n");
            for (int j = 0; j < this.asignaciones[i].length; j++) {
                resultado.append("  ").append(turnos[j]).append(": ");
                if (this.asignaciones[i][j] == null) {
                    resultado.append("Libre");
                } else {
                    resultado.append(this.asignaciones[i][j]);
                }
                resultado.append("\n");
            }
            resultado.append("\n");
        }

        return resultado.toString();
    }

    /**
     * Busca una comisión por su nombre y devuelve su posición en el cronograma.
     * 
     * @pre nombreComision no es nulo ni está vacío.
     * @post devuelve un arreglo de dos posiciones [día, turno] si la comisión existe,
     * o null si no se encuentra.
     * 
     * @param nombreComision nombre de la comisión a buscar (no nulo, no vacío)
     * @return arreglo de 2 elementos con [día, turno] de la comisión, o null si no existe
     * @throws Error si el nombre de la comisión es nulo o vacío
     */
    public int[] buscarComision(String nombreComision) {
        if (nombreComision == null || nombreComision.isBlank()) {
            throw new Error("El nombre de la comisión no puede ser nulo ni vacío.");
        }

        int[] posicion = null;
        boolean encontrado = false;

        for (int i = 0; i < this.asignaciones.length && !encontrado; i++) {
            for (int j = 0; j < this.asignaciones[i].length && !encontrado; j++) {
                if (this.asignaciones[i][j] != null &&
                    this.asignaciones[i][j].equalsIgnoreCase(nombreComision)) {
                    posicion = new int[]{i, j};
                    encontrado = true;
                }
            }
        }

        return posicion;
    }

    /**
     * Devuelve una copia de la matriz de asignaciones para evitar modificaciones externas.
     * 
     * @post devuelve una copia independiente de la matriz interna.
     * @return copia de la matriz de asignaciones (6 días x 3 turnos)
     */
    public String[][] obtenerAsignaciones() {
        String[][] copia = new String[this.asignaciones.length][];
        for (int i = 0; i < this.asignaciones.length; i++) {
            copia[i] = this.asignaciones[i].clone();
        }
        return copia;
    }
}