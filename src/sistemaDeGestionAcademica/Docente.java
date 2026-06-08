package sistemaDeGestionAcademica;

public class Docente {
	private String nombre;
	private String catedra;
	private int antiguedad;

	/**
	 * pre: nombre no es nulo ni está vacío, catedra no es nula ni está vacía, antiguedad es mayor o igual a 0.
	 * post: inicializa el Docente con el nombre, la cátedra y la antigüedad indicados.
	 * @param nombre nombre completo del docente
	 * @param catedra nombre de la cátedra a cargo
	 * @param antiguedad años de antigüedad 
	 * @throws Error si el nombre es nulo o esta vacio 
	 * @throws Error si el nombre de la catedra es nulo o esta vacio
	 * @throws Error si la antiguedad es menor a cero 
	 */
	public Docente(String nombre, String catedra, int antiguedad) {
		cambiarNombre(nombre);
		cambiarCatedra(catedra);
		cambiarAntiguedad(antiguedad);
	}
	
	/**
	 * post: devuelve el nombre del docente.
	 * @return nombre del docente
	 */
	public String obtenerNombre() {
		return nombre;
	}
	
	/**
	 * pre : nombre no es nulo ni está vacío.
	 * post: cambia el nombre del docente por el valor indicado.
	 * @param nombre nuevo nombre del docente
	 * @throws Error si el nombre es nulo o esta vacio
	 */
	public void cambiarNombre(String nombre) {
		if (nombre == null || nombre.isBlank()) {
			throw new Error("El Nombre Ingresado No Puede estar Vacio");
		}

		this.nombre = normalizarNombreDelDocente(nombre);
	}
	
	/**
	 * post: devuelve la cátedra del docente.
	 * @return nombre de la cátedra
	 */
	public String obtenerCatedra() {
		return catedra;
	}
	
	/**
	 * pre : catedra no es nula ni está vacía.
	 * post: cambia la cátedra del docente.
	 * @param catedra nueva cátedra
	 * @throws Error si el nombre de la catedra es nulo o esta vacio
	 */
	public void cambiarCatedra(String catedra) {
		if (catedra == null || catedra.isBlank()) {
			throw new Error("El Nombre de la Catedra Ingresado No Puede estar Vacio");
		}
		this.catedra = catedra.trim();
	}
	
	/**
	 * post: devuelve la antigüedad del docente.
	 * @return cantidad de años de antigüedad
	 */
	public int obtenerAntiguedad() {
		return antiguedad;
	}
	
	/**
	 * pre: antiguedad es mayor o igual a 0.
	 * post: cambia la antigüedad del docente.
	 * @param antiguedad nueva antigüedad
	 * @throws Error si la antiguedad es menor a cero
	 */
	public void cambiarAntiguedad(int antiguedad) {
		if (antiguedad < 0) {
			throw new Error("La Antiguedad No Puede ser Menor a 0");
		}
		this.antiguedad = antiguedad;
	}
	
	/**
	 * post: devuelve los datos del docente en formato texto.
	 * @return datos del docente
	 */
	@Override
	public String toString() {
		return "[Docente]" + "\nNombre: " + obtenerNombre() + " | Catedra: " + obtenerCatedra() + " | Antiguedad: " + obtenerAntiguedad();
	}
	
	/**
	 * pre : nombre no es nulo ni esta vacio.
	 * post: devuelve el nombre normalizado en minuscula, sin acentos, 
	 * sin caracteres especiales y sin espacios sobrantes.
	 * @param nombre nombre a normalizar
	 * @return nombre normalizado
	 */
	private String normalizarNombreDelDocente(String nombre) {
		String normalizado = nombre.trim().toLowerCase();
		String conAcentos = "áéíóúüñ";
		String sinAcentos = "aeiouun";
		
		for (int i = 0; i < conAcentos.length(); i++) {
			normalizado = normalizado.replace(conAcentos.charAt(i), sinAcentos.charAt(i));
		}

		normalizado = normalizado.replaceAll("[^a-z0-9 ]", " ");
		normalizado = normalizado.replaceAll("\\s+", " ");

		return normalizado;
	}
}