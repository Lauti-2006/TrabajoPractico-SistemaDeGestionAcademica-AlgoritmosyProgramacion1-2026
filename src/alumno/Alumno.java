package alumno;

public class Alumno {
	private String nombre;
	private int legajo;
	private double promedio;
	
	/**
	 * pre : nombre no es nulo ni está vacío, legajo es mayor a 0, promedio está entre 0 y 10 (inclusive).
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
		if (nombre == null || nombre.isBlank()) {
			throw new Error("El Nombre Ingresado No Puede estar Vacio");
		}

		if (legajo <= 0) {
			throw new Error("El Legajo debe ser Mayor a 0");
		}

		if (promedio <= 0 || promedio > 10) {
			throw new Error("El Promedio debe estar Entre 1 y 10");
		}

		this.nombre = normalizarNombre(nombre);
		this.legajo = legajo;
		this.promedio = promedio;
	}
	
	/**
	 * post: devuelve el nombre del alumno.
	 * @return nombre del alumno
	 */
	public String getNombre() {
		return this.nombre;
	}
	
	/**
	 * pre : nombre no es nulo ni está vacío.
	 * post: cambia el nombre del alumno por el valor indicado (sin normalizar automáticamente).
	 * @param nombre nuevo nombre del alumno
	 * @throws Error si el nombre es nulo o esta vacio.
	 */
	public void setNombre(String nombre) {
		if (nombre == null || nombre.isBlank()) {
			throw new Error("El Nombre Ingresado No Puede estar Vacio");
		}

		this.nombre = normalizarNombre(nombre);
	}
	
	/**
	 * post: devuelve el legajo del alumno.
	 * @return legajo del alumno
	 */
	public int getLegajo() {
		return this.legajo;
	}
	
	/**
	 * post: devuelve el promedio del alumno.
	 * @return promedio del alumno
	 */
	public double getPromedio() {
		return this.promedio;
	}
	
	/**
	 * pre : promedio está comprendido entre 0 y 10.
	 * post: cambia el promedio del alumno por el valor indicado.
	 * @param promedio nuevo promedio del alumno
	 * @throws Error si el promedio es menor o igual a cero, o mayor a diez
	 */
	public void setPromedio(double promedio) {
		if (promedio <= 0 || promedio > 10) {
			throw new Error("El Promedio debe estar Entre 1 y 10");
		}

		this.promedio = promedio;
	}
	
	/**
	 * post: devuelve los datos del alumno en formato texto.
	 * @return datos del alumno
	 */
	public String mostrarDatos() {
		return "[Alumno]" + "\nNombre: " + getNombre() + " | Legajo: " + getLegajo() + " | Promedio: " + getPromedio();
	}
	
	/**
	 * pre : nombre no es nulo ni esta vacio.
	 * post: devuelve el nombre normalizado con primera letra de cada palabra en mayúscula,
	 * sin espacios al inicio ni al final.
	 * @param nombre nombre a normalizar
	 * @return nombre normalizado
	 */
	private String normalizarNombre(String nombre) {
		nombre = nombre.trim().toLowerCase(); // Quitamos los espacios del principio y del final del parametro nombre. Y tambien convertimos todas las letras en minusculas
		String[] partes = nombre.split(" "); // El metodo split retorna un Array de String donde cada posicion es una parte. Cada Parte se divide apartir del parametro del metodo split
		String resultado = "";
		
		for(int i = 0; i < partes.length; i++) {
			if(!partes[i].isBlank()) { //Verifica que cada Parte no este en Blanco
				String primeraLetra = partes[i].substring(0, 1).toUpperCase(); // Extraemos a un Nuevo String el primer caracter y lo convertimos a mayuscula
				
				String restoDeLaPalabra = partes[i].substring(1); //Extraemos a otro String desde la posicion 1 hasta el final de la parte actual.
				
				resultado = primeraLetra + restoDeLaPalabra + " "; //Sumamos el resultado final, la primerLetra que ahora esta en mayuscula mas el resto de la parte
			}
		}
		
		return resultado.trim(); //El trim quita el espacio vacio que agregamos
	}
}