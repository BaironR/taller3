
import java.io.IOException;

/**
 * Main
 */
public class Main {

    /**
     *
     * @param args
     * @throws IOException en caso de error
     */
    public static void main(String[] args){

        try{

            SistemaImpl sistema = new SistemaImpl();
            sistema.menu();

        } catch (IOException e){

            e.printStackTrace();
        }
    }
}
