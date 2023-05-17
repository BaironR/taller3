/**
 * Clase de objetos tipo Percusion, extiende a Instrumento
 */
public final class Percusion extends Instrumento{

    private String tipoPercusion;
    private String altura;

    /**
     * Constructor de la clase
     *
     * @param codigo heredado de Instrumento
     * @param precio heredado de Instrumento
     * @param stock heredado de Instrumento
     * @param instrumento heredado de Instrumento
     * @param material heredado de Instrumento
     * @param tipoPercusion define el tipo de percusion del Instrumento de tipo Percusion
     * @param altura define si la altura es definida o indefinida en el Instrumento de tipo Percusion
     */
    public Percusion(String codigo, double precio, int stock, String instrumento,
                     String material, String tipoPercusion, String altura) {
        super(codigo, precio, stock, instrumento, material);
        this.tipoPercusion = tipoPercusion;
        this.altura = altura;
    }

    /**
     *
     * @return tipoPercusion
     */
    public String getTipoPercusion() {
        return tipoPercusion;
    }

    /**
     *
     * @return altura
     */
    public String getAltura() {
        return altura;
    }
}
