import ucn.StdOut;

import java.io.IOException;

/**
 * Main
 */
public class Main {

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        try{

            SistemaImpl sistema = new SistemaImpl();
            sistema.menu();

        } catch (IOException e){

            e.printStackTrace();
        }
    }
}
