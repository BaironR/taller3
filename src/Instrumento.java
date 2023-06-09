/**
 * Clase abstracta de tipo Instrumento, hereda Cuerda, Percusion y Viento
 */
abstract class Instrumento {

    private final String codigo;
    private final double precio;
    private int stock;
    private final String instrumento;
    private final String material;

    /**
     * Constructor de la clase
     *
     * @param codigo define el codigo del instrumento
     * @param precio define su precio
     * @param stock define su stock
     * @param instrumento define el nombre del instrumento
     * @param material define el material de construccion del instrumento
     */
    public Instrumento(String codigo, double precio, int stock, String instrumento, String material) {
        this.codigo = codigo;
        this.precio = precio;
        this.stock = stock;
        this.instrumento = instrumento;
        this.material = material;
    }

    /**
     *
     * @return codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     *
     * @return precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     *
     * @return stock
     */
    public int getStock() {
        return stock;
    }

    /**
     *
     * @return instrumento
     */

    public String getInstrumento() {
        return instrumento;
    }

    /**
     *
     * @return material
     */
    public String getMaterial() {
        return material;
    }

    /**
     *
     * @param stock setea el stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}
