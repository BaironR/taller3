
import ucn.StdOut;

/**
 * Main
 */
public class Main {

    /**
     *
     * @param args
     */
    public static void main(String[] args){

        try{

            SistemaImpl sistema = new SistemaImpl();
            sistema.menu();

        } catch (Exception e){

            StdOut.println("Hubo un problema en la ejecucion del programa: ");
            e.printStackTrace();
        }
    }
}
