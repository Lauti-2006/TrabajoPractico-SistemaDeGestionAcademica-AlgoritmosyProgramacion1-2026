package sistemaDeGestionAcademica;

public class Alumno extends Persona {
    private String nombre;
    private int legajo;
    private double promedio;

    /**
     * Inicializa un nuevo alumno con el nombre normalizado, el legajo y el promedio indicados.
     * 
     * @pre nombre no es nulo ni está vacío, legajo es mayor a 0, promedio está entre 0 y 10 (inclusive).
     * @post se crea un Alumno con los datos proporcionados y el nombre normalizado.
     * 
     * @param nombre   nombre completo del alumno (no nulo, no vacío)
     * @param legajo   número de legajo único (mayor a 0)
     * @param promedio promedio académico (entre 0.0 y 10.0 inclusive)
     * @throws Error si el nombre es nulo o está vacío
     * @throws Error si el legajo es menor o igual a 0
     * @throws Error si el promedio es menor a 0.0 o mayor a 10.0
     */
    public Alumno(String nombre, int legajo, double promedio) {
        this.cambiarNombre(nombre);
        this.cambiarLegajo(legajo);
        this.cambiarPromedio(promedio);
    }

    /**
     * Devuelve el nombre del alumno.
     * 
     * @post devuelve el nombre actual del alumno.
     * @return nombre del alumno
     */
    public String obtenerNombre() {
        return this.nombre;
    }

    /**
     * Cambia el nombre del alumno por el valor indicado, normalizándolo automáticamente.
     * 
     * @pre nombre no es nulo ni está vacío.
     * @post el nombre del alumno se actualiza al valor normalizado.
     * 
     * @param nombre nuevo nombre del alumno (no nulo, no vacío)
     * @throws Error si el nombre es nulo o está vacío
     */
    public void cambiarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new Error("El nombre ingresado no puede estar vacío.");
        }
        this.nombre = this.normalizarNombreDelAlumno(nombre);
    }

    /**
     * Devuelve el legajo del alumno.
     * 
     * @post devuelve el legajo actual del alumno.
     * @return legajo del alumno
     */
    public int obtenerLegajo() {
        return this.legajo;
    }

    /**
     * Cambia el legajo del alumno por el valor indicado.
     * 
     * @pre legajo es mayor a 0.
     * @post el legajo del alumno se actualiza al nuevo valor.
     * 
     * @param legajo nuevo legajo del alumno (mayor a 0)
     * @throws Error si el legajo es menor o igual a 0
     */
    public void cambiarLegajo(int legajo) {
        if (legajo <= 0) {
            throw new Error("El legajo debe ser un número entero positivo.");
        }
        this.legajo = legajo;
    }

    /**
     * Devuelve el promedio del alumno.
     * 
     * @post devuelve el promedio actual del alumno.
     * @return promedio del alumno
     */
    public double obtenerPromedio() {
        return this.promedio;
    }

    /**
     * Cambia el promedio del alumno por el valor indicado.
     * 
     * @pre promedio está comprendido entre 0.0 y 10.0 (inclusive).
     * @post el promedio del alumno se actualiza al nuevo valor.
     * 
     * @param promedio nuevo promedio del alumno (entre 0.0 y 10.0)
     * @throws Error si el promedio es menor a 0.0 o mayor a 10.0
     */
    public void cambiarPromedio(double promedio) {
        if (promedio < 0.0 || promedio > 10.0) {
            throw new Error("El promedio debe estar entre 0.0 y 10.0.");
        }
        this.promedio = promedio;
    }

    /**
     * Devuelve los datos del alumno en formato texto.
     * 
     * @post devuelve una representación textual del alumno.
     * @return cadena con los datos del alumno
     */
    @Override
    public String toString() {
        return "[Alumno]\n" +
               "Nombre: " + this.obtenerNombre() +
               " | Legajo: " + this.obtenerLegajo() +
               " | Promedio: " + this.obtenerPromedio();
    }

    /**
     * Normaliza el nombre eliminando acentos, caracteres especiales y espacios sobrantes,
     * y convirtiendo todo a minúsculas.
     * 
     * @pre nombre no es nulo ni está vacío.
     * @post devuelve el nombre normalizado en minúsculas, sin acentos ni caracteres especiales,
     * y sin espacios redundantes.
     * 
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

    /**
     * Devuelve la identificación del alumno en forma de cadena.
     * 
     * @post devuelve el legajo como cadena de texto.
     * @return legajo del alumno como String
     */
    @Override
    public String obtenerIdentificacion() {
        return String.valueOf(this.legajo);
    }
}