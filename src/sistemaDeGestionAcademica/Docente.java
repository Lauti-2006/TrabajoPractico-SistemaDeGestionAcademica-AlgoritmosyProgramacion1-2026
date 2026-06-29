package sistemaDeGestionAcademica;

public class Docente extends Persona {
    private String nombre;
    private String catedra;
    private int antiguedad;

    /**
     * Inicializa un nuevo docente con el nombre normalizado, la cátedra y la antigüedad indicados.
     * 
     * @pre nombre no es nulo ni está vacío.
     * @pre catedra no es nula ni está vacía.
     * @pre antiguedad es mayor o igual a 0.
     * @post se crea un Docente con los datos proporcionados y el nombre normalizado.
     * 
     * @param nombre nombre completo del docente (no nulo, no vacío)
     * @param catedra nombre de la cátedra a cargo (no nulo, no vacío)
     * @param antiguedad años de antigüedad (mayor o igual a 0)
     * @throws Error si el nombre es nulo o está vacío
     * @throws Error si la cátedra es nula o está vacía
     * @throws Error si la antigüedad es menor a 0
     */
    public Docente(String nombre, String catedra, int antiguedad) {
        this.cambiarNombre(nombre);
        this.cambiarCatedra(catedra);
        this.cambiarAntiguedad(antiguedad);
    }

    /**
     * Devuelve el nombre del docente.
     * 
     * @post devuelve el nombre actual del docente.
     * @return nombre del docente
     */
    public String obtenerNombre() {
        return this.nombre;
    }

    /**
     * Cambia el nombre del docente por el valor indicado, normalizándolo automáticamente.
     * 
     * @pre nombre no es nulo ni está vacío.
     * @post el nombre del docente se actualiza al valor normalizado.
     * 
     * @param nombre nuevo nombre del docente (no nulo, no vacío)
     * @throws Error si el nombre es nulo o está vacío
     */
    public void cambiarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new Error("El nombre ingresado no puede estar vacío");
        }
        this.nombre = this.normalizarNombreDelDocente(nombre);
    }

    /**
     * Devuelve la cátedra del docente.
     * 
     * @post devuelve la cátedra actual del docente.
     * @return nombre de la cátedra
     */
    public String obtenerCatedra() {
        return this.catedra;
    }

    /**
     * Cambia la cátedra del docente por el valor indicado.
     * 
     * @pre catedra no es nula ni está vacía.
     * @post la cátedra del docente se actualiza al nuevo valor.
     * 
     * @param catedra nueva cátedra (no nula, no vacía)
     * @throws Error si la cátedra es nula o está vacía
     */
    public void cambiarCatedra(String catedra) {
        if (catedra == null || catedra.isBlank()) {
            throw new Error("El nombre de la cátedra ingresado no puede estar vacío");
        }
        this.catedra = catedra.trim();
    }

    /**
     * Devuelve la antigüedad del docente.
     * 
     * @post devuelve la antigüedad actual del docente.
     * @return cantidad de años de antigüedad
     */
    public int obtenerAntiguedad() {
        return this.antiguedad;
    }

    /**
     * Cambia la antigüedad del docente por el valor indicado.
     * 
     * @pre antiguedad es mayor o igual a 0.
     * @post la antigüedad del docente se actualiza al nuevo valor.
     * 
     * @param antiguedad nueva antigüedad (mayor o igual a 0)
     * @throws Error si la antigüedad es menor a 0
     */
    public void cambiarAntiguedad(int antiguedad) {
        if (antiguedad < 0) {
            throw new Error("La antigüedad no puede ser menor a 0");
        }
        this.antiguedad = antiguedad;
    }

    /**
     * Devuelve los datos del docente en formato texto.
     * 
     * @post devuelve una representación textual del docente.
     * @return cadena con los datos del docente
     */
    @Override
    public String toString() {
        return "[Docente]\n" +
               "Nombre: " + this.obtenerNombre() +
               " | Catedra: " + this.obtenerCatedra() +
               " | Antiguedad: " + this.obtenerAntiguedad();
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

    /**
     * Devuelve la identificación del docente en forma de cadena.
     * En este caso, la identificación es la cátedra que dicta.
     * 
     * @post devuelve la cátedra como cadena de texto.
     * @return nombre de la cátedra del docente
     */
    @Override
    public String obtenerIdentificacion() {
        return this.catedra;
    }
}