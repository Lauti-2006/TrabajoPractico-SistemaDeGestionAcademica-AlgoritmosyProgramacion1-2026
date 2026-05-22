package docente;

public class Docente {
	private String nombre;
	private String catedra;
	private int antiguedad;

	/**
	 * pre : nombre no es nulo ni está vacío, catedra no es nula ni está vacía, antiguedad es mayor o igual a 1.
	 * post: inicializa el Docente con el nombre, la cátedra y la antigüedad indicados.
	 * @param nombre nombre completo del docente
	 * @param catedra nombre de la cátedra a cargo
	 * @param antiguedad años de antigüedad 
	 * @throws Error si el nombre es nulo o esta vacio 
	 * @throws Error si el nombre de la catedra es nulo o esta vacio
	 * @throws Error si la antiguedad es menor o igual a cero 
	 */
	public Docente(String nombre, String catedra, int antiguedad) {
		if (nombre == null || nombre.isBlank()) {
			throw new Error("El Nombre Ingresado No Puede estar Vacio");
		}

		if (catedra == null || catedra.isBlank()) {
			throw new Error("El Nombre de la Catedra Ingresado No Puede estar Vacio");
		}

		if (antiguedad < 1) {
			throw new Error("La Antiguedad No Puede ser Menor a 1");
		}

		this.nombre = nombre.trim();
		this.catedra = catedra.trim();
		this.antiguedad = antiguedad;
	}
	
	/**
	 * post: devuelve el nombre del docente.
	 * @return nombre del docente
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * pre : nombre no es nulo ni está vacío.
	 * post: cambia el nombre del docente por el valor indicado.
	 * @param nombre nuevo nombre del docente
	 * @throws Error si el nombre es nulo o esta vacio
	 */
	public void setNombre(String nombre) {
		if (nombre == null || nombre.isBlank()) {
			throw new Error("El Nombre Ingresado No Puede estar Vacio");
		}

		this.nombre = nombre.trim();
	}
	
	/**
	 * post: devuelve la cátedra del docente.
	 * @return nombre de la cátedra
	 */
	public String getCatedra() {
		return catedra;
	}
	
	/**
	 * pre : catedra no es nula ni está vacía.
	 * post: cambia la cátedra del docente.
	 * @param catedra nueva cátedra
	 * @throws Error si el nombre de la catedra es nulo o esta vacio
	 */
	public void setCatedra(String catedra) {
		if (catedra == null || catedra.isBlank()) {
			throw new Error("El Nombre de la Catedra Ingresado No Puede estar Vacio");
		}
		this.catedra = catedra.trim();
	}
	
	/**
	 * post: devuelve la antigüedad del docente.
	 * @return cantidad de años de antigüedad
	 */
	public int getAntiguedad() {
		return antiguedad;
	}
	
	/**
	 * pre : antiguedad es mayor o igual a 1.
	 * post: cambia la antigüedad del docente.
	 * @param antiguedad nueva antigüedad
	 * @throws Error si la antiguedad es menor o igual a cero
	 */
	public void setAntiguedad(int antiguedad) {
		if (antiguedad < 1) {
			throw new Error("La Antiguedad No Puede ser Menor a 1");
		}
		this.antiguedad = antiguedad;
	}
	
	/**
	 * post: devuelve los datos del docente en formato texto.
	 * @return datos del docente
	 */
	public String mostrarDatos() {
		return "[Docente]" + "\nNombre: " + getNombre() + " | Catedra: " + getCatedra() + " | Antiguedad: " + getAntiguedad();
	}
}