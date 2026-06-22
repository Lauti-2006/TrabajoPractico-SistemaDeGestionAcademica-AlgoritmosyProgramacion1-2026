package sistemaDeGestionAcademica;

public class CronogramaGeneral {
	private String[][] asignaciones;
	
	/**
	 * post: se crea un cronograma con 6 días y 3 turnos por día, sin comisiones asignadas.
	 */
	public CronogramaGeneral() {
		this.asignaciones = new String[6][3];
	}
	
	/**
	 * pre: el día y el turno son válidos y el nombre de la comisión no es nulo ni vacío.
	 * post: si el horario estaba libre, la comisión queda asignada.
	 * @param dia día en el que se desea asignar la comisión, entre 0 y 5.
	 * @param turno turno en el que se desea asignar la comisión, entre 0 y 2.
	 * @param nombreComision nombre de la comisión a asignar.
	 * @throws Error si el día no es válido.
	 * @throws Error si el turno no es válido.
	 * @throws Error si se intenta asignar un turno no permitido el día sábado.
	 * @throws Error si el nombre de la comisión es nulo o vacío.
	 * @return true si la comisión pudo asignarse, false en caso contrario.
	 */
	public boolean asignarComision(int dia, int turno, String nombreComision) {
		boolean yaAsignado = false;
		if (dia < 0 || dia > 5) {
			throw new Error("dia erroneo");
		}
		if (turno < 0 || turno > 2) {
			throw new Error("turno invalido");
		}
		if (dia == 5 && turno != 0) {
			throw new Error("sabado solo puede ser a la mañana");
		}
		if (nombreComision == null || nombreComision.equals("")) {
			throw new Error("nombre invalido");
		}
		if (asignaciones[dia][turno] != null) {
			yaAsignado = false;
		} else {
			this.asignaciones[dia][turno] = nombreComision;
			yaAsignado = true;
		}
		return yaAsignado;
	}
	
	/**
	 * pre: el día y el turno son válidos.
	 * post: si existía una comisión asignada, el horario queda libre.
	 * @param dia día cuyo horario se desea liberar, entre 0 y 5.
	 * @param turno turno cuyo horario se desea liberar, entre 0 y 2.
	 * @throws Error si el día no es válido.
	 * @throws Error si el turno no es válido.
	 * @throws Error si se intenta liberar un turno no permitido el día sábado.
	 * @return true si el horario fue liberado, false si ya estaba libre.
	 */
	public boolean liberarHorario(int dia, int turno) {

		boolean liberado = false;

		if (dia < 0 || dia > 5) {
			throw new Error("dia erroneo");
		}

		if (turno < 0 || turno > 2) {
			throw new Error("turno invalido");
		}

		if (dia == 5 && turno != 0) {
			throw new Error("sabado solo puede ser a la mañana");
		}

		if (this.asignaciones[dia][turno] == null) {
			liberado = false;
		} else {
			this.asignaciones[dia][turno] = null;
			liberado = true;
		}

		return liberado;
	}
	
	/**
	 * pre: el día debe estar entre 0 y 5 y el turno entre 0 y 2.
	 * post: devuelve el nombre de la comisión asignada al día y turno indicados, o null si el horario está libre.
	 * @param dia día a consultar.
	 * @param turno turno a consultar.
	 * @throws Error si el día no es válido.
	 * @throws Error si el turno no es válido.
	 * @return el nombre de la comisión asignada o null si el horario está libre.
	 */
	public String consultarHorario(int dia, int turno) {
		if (dia < 0 || dia > 5) {
			throw new Error("dia erroneo");
		}

		if (turno < 0 || turno > 2) {
			throw new Error("turno invalido");
		}

		return this.asignaciones[dia][turno];

	}
	
	/**
	 * post: devuelve una representación textual del cronograma sin modificar su estado.
	 * @return una cadena con el detalle completo de las asignaciones del cronograma.
	 */
	@Override
	public String toString() {
		String[] dias = { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado" };
		String[] turnos = { "Mañana", "Tarde", "Noche" };

		String resultado = "";

		for (int i = 0; i < this.asignaciones.length; i++) {
			resultado += dias[i] + "\n";

			for (int j = 0; j < this.asignaciones[i].length; j++) {
				resultado += "  " + turnos[j] + ": ";

				if (this.asignaciones[i][j] == null) {
					resultado += "Libre";
				} else {
					resultado += this.asignaciones[i][j];
				}

				resultado += "\n";
			}

			resultado += "\n";
		}

		return resultado;
	}
	
	/**
	 * pre: el nombre de la comisión no debe ser nulo ni vacío.
	 * post: devuelve la posición de la comisión buscada o null si no se encuentra.
	 * @param nombreComision nombre de la comisión a buscar.
	 * @throws Error si el nombre de la comisión es nulo o vacío.
	 * @return un arreglo de dos posiciones con el día y el turno donde se encuentra
	 * la comisión; devuelve null si la comisión no existe en el cronograma.
	 */
	public int[] buscarComision(String nombreComision) {
		boolean encontrado = false;
		int[] posicion = null;

		if (nombreComision == null || nombreComision.equals("")) {
			throw new Error("nombre invalido");
		}

		for (int i = 0; i < this.asignaciones.length && !encontrado; i++) {
			for (int j = 0; j < this.asignaciones[i].length && !encontrado; j++) {
				if (this.asignaciones[i][j] != null && this.asignaciones[i][j].equalsIgnoreCase(nombreComision)) {

					posicion = new int[2];
					posicion[0] = i;
					posicion[1] = j;
					encontrado = true;
				}
			}
		}

		return posicion;
	}
}