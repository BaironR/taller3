import ucn.ArchivoEntrada;

import java.io.IOException;

/**
 * Interface de Sistema
 */
public interface Sistema {

    /**
     * Metodo del menu
     *
     * @throws IOException en caso de error
     */
    void menu() throws IOException;

    /**
     * Metodo de lectura del archivo para leer los instrumentos ingresados
     *
     * @param archivoEntrada
     * @throws IOException en caso de error
     */
    void leerArchivoInstrumentos(ArchivoEntrada archivoEntrada) throws IOException;

    /**
     * Escritura de la base de datos del sistema
     *
     * @throws IOException en caso de error
     */
    void escribirBaseDatos() throws IOException;

    /**
     * Metodo de venta de instrumentos
     *
     * @param codigo para vender el instrumento
     * @throws IOException en caso de error
     */
    void venderInstrumento(String codigo) throws IOException;

    void consultarInventario() throws IOException;
}
