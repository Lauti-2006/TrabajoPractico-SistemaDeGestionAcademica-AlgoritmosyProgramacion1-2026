package sistemaDeGestionAcademica;

public class Alumno {
	private String nombre;
	private int legajo;
	private double promedio;
	
	/**
	 * pre: nombre no es nulo ni está vacío, legajo es mayor a 0, promedio está entre 0 y 10 (inclusive).
	 * post: inicializa el Alumno con el nombre normalizado (primera letra de cada palabra en mayúscula),
	 * el legajo y el promedio indicados.
	 * @param nombre nombre completo del alumno
	 * @param legajo legajo unico 
	 * @param promedio promedio academico
	 * @throws Error si el nombre es nulo o esta vacio 
	 * @throws Error si el legajo es cero o negativo
	 * @throws Error si el promedio es menor o igual a cero, o mayor a diez 
	 */
	public Alumno(String nombre, int legajo, double promedio) {
		cambiarNombre(nombre);
		cambiarLegajo(legajo);
		cambiarPromedio(promedio);
	}
	
	/**
	 * post: devuelve el nombre del alumno.
	 * @return nombre del alumno
	 */
	public String obtenerNombre() {
		return this.nombre;
	}
	
	/**
	 * pre: nombre no es nulo ni está vacío.
	 * post: cambia el nombre del alumno por el valor indicado (normalizandolo automáticamente).
	 * @param nombre nuevo nombre del alumno
	 * @throws Error si el nombre es nulo o esta vacio.
	 */
	public void cambiarNombre(String nombre) {
		if (nombre == null || nombre.isBlank()) {
			throw new Error("El Nombre Ingresado No Puede estar Vacio");
		}

		this.nombre = normalizarNombreDelAlumno(nombre);
	}
	
	/**
	 * post: devuelve el legajo del alumno.
	 * @return legajo del alumno
	 */
	public int obtenerLegajo() {
		return this.legajo;
	}
	
	/**
	 * pre: legajo es mayor a 0.
	 * post: cambia el legajo del alumno por el valor indicado.
	 * @param legajo nuevo legajo del alumno
	 * @throws Error si el legajo es menor o igual a 0.
	 */
	public void cambiarLegajo(int legajo) {
	    if (legajo <= 0) {
	        throw new Error("El Legajo Debe Ser Un Número Entero Positivo");
	    }
	    this.legajo = legajo;
	}
	
	/**
	 * post: devuelve el promedio del alumno.
	 * @return promedio del alumno
	 */
	public double obtenerPromedio() {
		return this.promedio;
	}
	
	/**
	 * pre: promedio está comprendido entre 1.0 y 10.0.
	 * post: cambia el promedio del alumno por el valor indicado.
	 * @param promedio nuevo promedio del alumno
	 * @throws Error si el promedio es menor o igual a cero, o mayor a diez
	 */
	public void cambiarPromedio(double promedio) {
		if (promedio < 1.0 || promedio > 10.0) {
			throw new Error("El Promedio debe estar Entre 1.0 y 10.0");
		}

		this.promedio = promedio;
	}
	
	/**
	 * post: devuelve los datos del alumno en formato texto.
	 * @return datos del alumno
	 */
	@Override
	public String toString() {
		return "[Alumno]" + "\nNombre: " + obtenerNombre() + " | Legajo: " + obtenerLegajo() + " | Promedio: " + obtenerPromedio();
	}
	
	/**
	 * pre : nombre no es nulo ni esta vacio.
	 * post: devuelve el nombre normalizado en minuscula, sin acentos,
	 * sin caracteres especiales y sin espacios sobrantes.
	 * @param nombre nombre a normalizar
	 * @return nombre normalizado
	 */
	private String normalizarNombreDelAlumno(String nombre) {
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