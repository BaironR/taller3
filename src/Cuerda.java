/**
 * Clase de objetos tipo Cuerda, extiende a Instrumento
 */
public final class Cuerda extends Instrumento {
    private final String tipoCuerda;
    private final int numCuerdas;
    private final String tipoInstrumento;

    /**
     * Constructor de Cuerda
     *
     * @param codigo heredado de Instrumento
     * @param precio heredado de Instrumento
     * @param stock heredado de Instrumento
     * @param instrumento heredado de Instrumento
     * @param tipoCuerda define el tipo de cuerda del Instrumento tipo cuerda
     * @param numCuerdas define el numero de cuerdas del Instrumento tipo cuerda
     * @param material heredado de Instrumento
     * @param tipoInstrumento define el tipo de Instrumento de cuerda (acustico, electrico)
     */
    public Cuerda(String codigo, double precio, int stock, String instrumento, String
            tipoCuerda, int numCuerdas, String material, String tipoInstrumento) {

        super(codigo, precio, stock, instrumento, material);
        this.tipoCuerda = tipoCuerda;
        this.numCuerdas = numCuerdas;
        this.tipoInstrumento= tipoInstrumento;
    }

    /**
     *
     * @return tipoCuerda
     */
    public String getTipoCuerda() {
        return tipoCuerda;
    }

    /**
     *
     * @return numCuerdas
     */
    public int getNumCuerdas() {
        return numCuerdas;
    }

    /**
     *
     * @return tipoInstrumento
     */
    public String getTipoInstrumento() {
        return tipoInstrumento;
    }
}
