/**
 * Clase de objetos tipo Viento, extiende a Instrumento
 */
public final class Viento extends Instrumento{

    /**
     * Constructor de la clase
     *
     * @param codigo heredado de Instrumento
     * @param precio heredado de Instrumento
     * @param stock heredado de Instrumento
     * @param instrumento heredado de Instrumento
     * @param material heredado de Instrumento
     */
    public Viento(String codigo, double precio, int stock, String instrumento, String material) {
        super(codigo, precio, stock, instrumento, material);
    }

}
