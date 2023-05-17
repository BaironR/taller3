import ucn.*;

import java.io.*;

/**
 * Implementacion de sistema
 */
public class SistemaImpl implements Sistema {

    /**
     * Constructor de SistemaImpl
     */
    public SistemaImpl() throws IOException {

        // Cada vez que se carga el programa, se inicia el auxiliar con los valores de la base de datos
        ListaInstrumentos lista = lecturaBaseDatos();
        escrituraArchivo(lista, "auxiliar.csv", false);
    }

    /**
     * menu del sistema
     *
     * @throws IOException en caso de que los archivos implicados tiren error
     */
    @Override
    public void menu() throws IOException {

        int opcion = opcionMenu();

        if (opcion == 4) {

            StdOut.println("Programa finalizado! muchas gracias");

        } else {

            while (opcion != 4) {

                switch (opcion) {

                    // Opcion 1: Agregar instrumento

                    case 1 -> {
                        ArchivoEntrada archivoEntrada = new ArchivoEntrada("aniadirInstrumentos.csv");
                        StdOut.println("Modifique el archivo aniadirInstrumentos.csv para añadir instrumentos\n" + "Ingrese 1 cuando este listo");
                        String listo = StdIn.readString();
                        while (!listo.equalsIgnoreCase("1")) {

                            StdOut.println("Escriba 1 cuando este listo");
                            listo = StdIn.readString();
                        }
                        // lectura del archivo aniadirInstrumentos.csv
                        leerArchivoInstrumentos(archivoEntrada);
                    }

                    // Opcion 2: Vender instrumento
                    case 2 -> {
                        StdOut.println("************ Venta de instrumentos ************\n");
                        desplegarInventario();
                        StdOut.println("Ingrese el codigo del instrumento que desea vender");
                        String codigo = StdIn.readString();
                        // Vender instrumento si el codigo es correcto
                        venderInstrumento(codigo);
                    }

                    // Opcion 3: Consultar inventario
                    case 3 -> consultarInventario();
                }

                // Vuelve a preguntar la opcion para continuar el loop
                opcion = opcionMenu();

                if (opcion == 4) {

                    StdOut.println("Programa finalizado! muchas gracias");
                }
            }
        }
    }

    /**
     * consultar la base de datos, ya sea por completo o un solo instrumento
     *
     */
    @Override
    public void consultarInventario() throws IOException {


        StdOut.println("""
                  ************ Consultar inventario ************
                  Ingrese la opcion que desea:
                  
                  [1] Consultar todo el inventario
                  [2] Consultar mediante un codigo""");

        String opcionInventario = StdIn.readString();

        while (!opcionInventario.equalsIgnoreCase("1") && !opcionInventario.equalsIgnoreCase("2")) {

            StdOut.println("""
                      ************ Consultar inventario ************
                      Ingrese la opcion que desea:
                      
                      [1] Consultar todo el inventario
                      [2] Consultar mediante un codigo""");

            opcionInventario = StdIn.readString();
        }

        // Desplegar el inventario completo
        if (opcionInventario.equalsIgnoreCase("1")) {

            desplegarInventario();

            // Preguntar por el codigo para desplegarlo
        } else {

            desplegarInstrumento();
        }
    }

    /**
     * Desplegar datos de todos los instrumentos de la base de datos
     *
     * @throws IOException en caso de error
     */
    public void desplegarInventario() throws IOException {

        ListaInstrumentos lista = lecturaBaseDatos();

        StdOut.println("");
        StdOut.println("************ Instrumentos de cuerda ************");
        for (int i = 0; i < lista.cantActual; i++) {

            if (lista.buscar(i).getStock() > 0) {

                Instrumento e1 = lista.buscar(i);

                if (e1 instanceof Cuerda) {

                    String codigo = e1.getCodigo();
                    double precio = e1.getPrecio();
                    int stock = e1.getStock();
                    String instrumento = e1.getInstrumento();
                    String tipoCuerda = ((Cuerda) e1).getTipoCuerda();
                    int numCuerdas = ((Cuerda) e1).getNumCuerdas();
                    String material = e1.getMaterial();
                    String tipoInstrumento = ((Cuerda) e1).getTipoInstrumento();

                    StdOut.println(codigo+", "+precio+", "+stock+", "+instrumento+", "+tipoCuerda+", "+numCuerdas+", "+material+", "+tipoInstrumento);
                }
            }
        }

        StdOut.println("");
        StdOut.println("************ Instrumentos de percusion ************");
        for (int i = 0; i < lista.cantActual; i++) {

            if (lista.buscar(i).getStock() > 0) {

                Instrumento e1 = lista.buscar(i);

                if (e1 instanceof Percusion) {

                    String codigo = e1.getCodigo();
                    double precio = e1.getPrecio();
                    int stock = e1.getStock();
                    String instrumento = e1.getInstrumento();
                    String tipoPercusion = ((Percusion) e1).getTipoPercusion();
                    String material = e1.getMaterial();
                    String altura = ((Percusion) e1).getAltura();

                    StdOut.println(codigo+", "+precio+", "+stock+", "+instrumento+", "+tipoPercusion+", "+material+", "+altura);
                }
            }
        }

        StdOut.println("");
        StdOut.println("************ Instrumentos de viento ************");
        for (int i = 0; i < lista.cantActual; i++) {

            if (lista.buscar(i).getStock() > 0) {

                Instrumento e1 = lista.buscar(i);

                if (e1 instanceof Viento) {

                    String codigo = e1.getCodigo();
                    double precio = e1.getPrecio();
                    int stock = e1.getStock();
                    String instrumento = e1.getInstrumento();
                    String material = e1.getMaterial();
                    StdOut.println(codigo+", "+precio+", "+stock+", "+instrumento+", "+material);
                }
            }
        }

        StdOut.println("");
    }

    /**
     * Preguntar por el codigo del instrumento y desplegarlo
     *
     * @throws IOException en caso de error
     */
    public void desplegarInstrumento() throws IOException {

        StdOut.println("Ingrese el codigo del instrumento que desea buscar");
        String codigoBuscado = StdIn.readString();

        ListaInstrumentos lista = lecturaBaseDatos();

        for (int i=0; i<lista.cantActual; i++){

            // El codigo es encontrado en la base de datos
            if (lista.buscar(i).getCodigo().equalsIgnoreCase(codigoBuscado)){

                Instrumento e1 = lista.buscar(i);
                String codigo = e1.getCodigo();
                double precio = e1.getPrecio();
                int stock = e1.getStock();
                String instrumento = e1.getInstrumento();
                String material = e1.getMaterial();

                if (e1 instanceof Viento) {

                    StdOut.println(codigo+", "+precio+", "+stock+", "+instrumento+", "+material);

                } else if (e1 instanceof Percusion){

                    String tipoPercusion = ((Percusion) e1).getTipoPercusion();
                    String altura = ((Percusion) e1).getAltura();

                    StdOut.println(codigo+", "+precio+", "+stock+", "+instrumento+", "+tipoPercusion+", "+material+", "+altura);

                } else {

                    String tipoCuerda = ((Cuerda) e1).getTipoCuerda();
                    int numCuerdas = ((Cuerda) e1).getNumCuerdas();
                    String tipoInstrumento = ((Cuerda) e1).getTipoInstrumento();

                    StdOut.println(codigo+", "+precio+", "+stock+", "+instrumento+", "+tipoCuerda+", "+numCuerdas+", "+material+", "+tipoInstrumento);
                }

                StdOut.println("");
                // Como solo es un codigo, acaba la funcion al encontrarlo en la base de datos
                return;
            }
        }

        // Si no retorna, no se encontro el codigo
        StdOut.println("No existe el codigo introducido en la base de datos");
    }

    /**
     * Metodo de venta de instrumento
     *
     * @param codigo (instrumento a vender)
     * @throws IOException en caso de error
     */
    @Override
    public void venderInstrumento(String codigo) throws IOException {

        ListaInstrumentos lista = lecturaBaseDatos();

        for (int i=0; i<lista.cantActual; i++){

            if (codigo.equalsIgnoreCase(lista.buscar(i).getCodigo())){

                // Se actualiza tanto el archivo auxiliar como la base de datos
                lista.venderInstrumento(i);
                escrituraArchivo(lista,"basedatos.csv",false);

                ListaInstrumentos lista1 = lecturaBaseDatos();
                escrituraArchivo(lista1,"auxiliar.csv",false);
                StdOut.println("************ Boleta ************\n"+ "\n" + "Instrumento vendido: "+lista.buscar(i).getInstrumento()+"\n"+ "Precio: "+lista.buscar(i).getPrecio()+ "\n");

                // Como solo es un codigo, acaba la funcion al encontrarlo en la base de datos
                return;
            }
        }

        // Si no retorna, no se encontro el codigo
        StdOut.println("No existe el codigo introducido en la base de datos");
    }

    /**
     * lectura del archivo aniadirInstrumentos.csv
     *
     * @param archivoEntrada "aniadirInstrumentos.csv"
     * @throws IOException en caso de que el archivo implicado tire error
     */
    @Override
    public void leerArchivoInstrumentos(ArchivoEntrada archivoEntrada) throws IOException {

        // lectura de archivo
        while (!archivoEntrada.isEndFile()) {

            Registro registro = archivoEntrada.getRegistro();
            String codigo = registro.getString();

            if (codigo != null) {

                String precioString = registro.getString();

                if (precioString != null) {

                    // validacion de que el precio sea un numero
                    double precio = validarNumero(precioString);

                    if (precio != -1) {

                        String stockString = registro.getString();

                        // Validacion de que el stock sea un numero entero
                        if (stockString != null) {

                            double stockDouble = validarNumero(stockString);
                            int stock = validarEntero(stockDouble);

                            if (stock != -1) {

                                String instrumento = registro.getString();

                                if (instrumento != null) {

                                    // Puede ser material(viento), tipoPercusion(percusion) o tipoCuerda(cuerda)
                                    String variableIndefinida1 = registro.getString();

                                    if (variableIndefinida1 != null) {

                                        // Puede ser null(viento), material(percusion), numCuerdas(cuerda)
                                        String variableIndefinida2 = registro.getString();

                                        if (variableIndefinida2 != null) {

                                            // Puede ser null(viento), altura(percusion), material(cuerda)
                                            String variableIndefinida3 = registro.getString();

                                            if (variableIndefinida3 != null) {

                                                // Puede ser null(viento), null (percusion), tipoInstrumento(cuerda)
                                                String variableIndefinida4 = registro.getString();

                                                // Es cuerda
                                                if (variableIndefinida4 != null) {

                                                    // Validacion de que el numero de cuerdas sea entero
                                                    double numCuerdasDouble = validarNumero(variableIndefinida2);
                                                    int numCuerdas = validarEntero(numCuerdasDouble);

                                                    if (numCuerdas != -1) {

                                                        Cuerda instrumentoCuerda = new Cuerda(codigo, precio, stock, instrumento, variableIndefinida1, numCuerdas, variableIndefinida3, variableIndefinida4);
                                                        escribirAuxiliar(instrumentoCuerda);
                                                    }

                                                    // Es percusion
                                                } else {

                                                    Percusion instrumentoPercusion = new Percusion(codigo, precio, stock, instrumento, variableIndefinida2, variableIndefinida1, variableIndefinida3);
                                                    escribirAuxiliar(instrumentoPercusion);
                                                }
                                            }

                                            // Es viento
                                        } else {

                                            Viento instrumentoViento = new Viento(codigo, precio, stock, instrumento, variableIndefinida1);
                                            escribirAuxiliar(instrumentoViento);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * escritura del archivo auxiliar.csv
     */

    // Archivo auxiliar que almacena los datos, esten repetidos o no (sin validar el dato)
    public void escribirAuxiliar(Instrumento instrumento){

        String csvFile = "auxiliar.csv";

        // Escritura de archivo auxiliar.csv, manteniendo el archivo anterior
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile, true))) {

            // Variables generales de todos los instrumentos
            String codigo = instrumento.getCodigo();
            String precio = String.valueOf(instrumento.getPrecio());
            String stock = String.valueOf(instrumento.getStock());
            String nombreInstrumento = instrumento.getInstrumento();
            String material = instrumento.getMaterial();

            // Variables que dependen de la clase del objeto (Cuerda, percusion o viento)
            if (instrumento instanceof Cuerda) {

                String tipoCuerda = ((Cuerda) instrumento).getTipoCuerda();
                String numeroCuerdas = String.valueOf(((Cuerda) instrumento).getNumCuerdas());
                String tipoInstrumento = ((Cuerda) instrumento).getTipoInstrumento();
                String[] data = {codigo, precio, stock, nombreInstrumento, tipoCuerda, numeroCuerdas, material, tipoInstrumento};

                // Escritura del vector data delimitado con comas
                bw.write(String.join(",", data));
                // Salto de linea o registro
                bw.newLine();

            } else if (instrumento instanceof Percusion) {

                String tipoPercusion = ((Percusion) instrumento).getTipoPercusion();
                String altura = ((Percusion) instrumento).getAltura();
                String[] data = {codigo, precio, stock, nombreInstrumento, tipoPercusion, material, altura};
                bw.write(String.join(",", data));
                bw.newLine();

            } else if (instrumento instanceof Viento) {

                String[] data = {codigo, precio, stock, nombreInstrumento, material};
                bw.write(String.join(",", data));
                bw.newLine();
            }

            // Cerrar escritor
            bw.close();

            escribirBaseDatos();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * escritura de la base de datos del sistema
     *
     * @throws IOException en caso de que el archivo tire error
     */

    @Override
    // Archivo con los datos sin repetir, en caso de que los instrumentos sean iguales se suma el stock
    public void escribirBaseDatos() throws IOException {

        ArchivoEntrada archivoEntrada = new ArchivoEntrada("auxiliar.csv");
        ListaInstrumentos lista = new ListaInstrumentos(100);

        // Lectura del archivo auxiliar y almacenamiento en lista tipo ListaInstrumento
        lista = lecturaArchivo(archivoEntrada,lista);

        // Eliminacion de instrumentos repetidos, si los instrumentos son iguales se suma el stock
        lista.filtrarLista();

        // Escritura del archivo basedatos.csv, con los datos ya filtrados

        escrituraArchivo(lista,"basedatos.csv",false);
    }

        /**
         * validacion de que numeroString sea un numero
         *
         * @param numeroString a validar
         * @return numero
         */
  public double validarNumero(String numeroString){

        double numero;

      try {

          numero = Double.parseDouble(numeroString);

          if (numero < 0){

              throw new NumberFormatException();
          }

      }catch (NumberFormatException e){

          numero = -1;
      }

      return numero;
  }

    /**
     * validacion de que el numero sea int y no double
     *
     * @param numero tipo variable double
     * @return numero
     */
  public int validarEntero(double numero){

      // Si el resto de dividir al numero por 1 es cero, es entero
        if (numero % 1 == 0){

            return (int) numero;

        }else{

            return -1;
        }
  }

    /**
     * Lectura del archivo basedatos.csv
     *
     * @return lista (almacena la base de datos)
     * @throws IOException en caso de error
     */
  public ListaInstrumentos lecturaBaseDatos() throws IOException {

      ArchivoEntrada archivoEntrada = new ArchivoEntrada("basedatos.csv");
      ListaInstrumentos lista = new ListaInstrumentos(100);

      return lecturaArchivo(archivoEntrada,lista);
  }

    /**
     * Metodo general de lectura de archivos
     *
     * @param archivoEntrada archivo a leer
     * @param lista lista para almacenar los datos
     * @return lista con los datos
     * @throws IOException en caso de error
     */
  public ListaInstrumentos lecturaArchivo(ArchivoEntrada archivoEntrada, ListaInstrumentos lista) throws IOException {

      while (!archivoEntrada.isEndFile()) {

          Registro registro = archivoEntrada.getRegistro();
          String codigo = registro.getString();

          if (codigo != null) {

              String precioString = registro.getString();

              if (precioString != null) {

                  double precio = validarNumero(precioString);
                  int stock = registro.getInt();
                  String instrumento = registro.getString();

                  // Puede ser material(viento), tipoPercusion(percusion) o tipoCuerda(cuerda)
                  String variableIndefinida1 = registro.getString();

                  // Puede ser null(viento), material(percusion), numCuerdas(cuerda)
                  String variableIndefinida2 = registro.getString();

                  // No es de viento
                  if (variableIndefinida2 != null) {

                      // Puede ser null(viento), altura(percusion), material(cuerda)
                      String variableIndefinida3 = registro.getString();

                      // Puede ser null(viento), null (percusion), tipoInstrumento(cuerda)
                      String variableIndefinida4 = registro.getString();

                      // Es de cuerda
                      if (variableIndefinida4 != null) {

                          int numCuerdas = Integer.parseInt(variableIndefinida2);
                          Cuerda instrumentoCuerda = new Cuerda(codigo, precio, stock, instrumento, variableIndefinida1, numCuerdas, variableIndefinida3, variableIndefinida4);
                          lista.agregar(instrumentoCuerda);


                          // Es percusion
                      } else {

                          Percusion instrumentoPercusion = new Percusion(codigo, precio, stock, instrumento, variableIndefinida2, variableIndefinida1, variableIndefinida3);
                          lista.agregar(instrumentoPercusion);
                      }

                      // Es de viento
                  } else {

                      Viento instrumentoViento = new Viento(codigo, precio, stock, instrumento, variableIndefinida1);
                      lista.agregar(instrumentoViento);
                  }
              }
          }
      }

      return lista;
  }

    /**
     * Metodo general de escritura de archivo
     *
     * @param lista con los datos
     * @param nombreArchivo nombre del archivo de salida
     * @param append dependiendo de su valor permite sobreescribir el archivo o mantener el anterior antes de escribir
     */
  public void escrituraArchivo(ListaInstrumentos lista, String nombreArchivo, boolean append){

      try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo, append))) {

          // Lectura y escritura de la lista de tipo ListaInstrumentos
          for (int i = 0; i < lista.cantActual; i++) {

              // Variables generales de todos los instrumentos
              Instrumento instrumento = lista.buscar(i);
              String codigo = instrumento.getCodigo();
              String precio = String.valueOf(instrumento.getPrecio());
              String stock = String.valueOf(instrumento.getStock());
              String nombreInstrumento = instrumento.getInstrumento();
              String material = instrumento.getMaterial();

              // Variables que dependen de la clase del objeto (Cuerda, percusion o viento)
              if (instrumento instanceof Cuerda) {

                  String tipoCuerda = ((Cuerda) instrumento).getTipoCuerda();
                  String numeroCuerdas = String.valueOf(((Cuerda) instrumento).getNumCuerdas());
                  String tipoInstrumento = ((Cuerda) instrumento).getTipoInstrumento();
                  String[] data = {codigo, precio, stock, nombreInstrumento, tipoCuerda, numeroCuerdas, material, tipoInstrumento};

                  // Escritura del vector data delimitado con comas
                  bw.write(String.join(",", data));
                  // Salto de linea o registro
                  bw.newLine();

              } else if (instrumento instanceof Percusion) {

                  String tipoPercusion = ((Percusion) instrumento).getTipoPercusion();
                  String altura = ((Percusion) instrumento).getAltura();
                  String[] data = {codigo, precio, stock, nombreInstrumento, tipoPercusion, material, altura};
                  bw.write(String.join(",", data));
                  bw.newLine();

              } else if (instrumento instanceof Viento) {

                  String[] data = {codigo, precio, stock, nombreInstrumento, material};
                  bw.write(String.join(",", data));
                  bw.newLine();
              }
          }

          bw.close();

      } catch (IOException e) {
          e.printStackTrace();
      }

  }

    /**
     * validacion de que la opcion sea correcta
     *
     * @return opcion
     */
  public int opcionMenu(){

        int opcion;

        do{
            try{

                StdOut.println("""
                        ************ Menú ************
                        [1] Agregar instrumento
                        [2] Vender instrumento
                        [3] Consultar inventario
                        [4] Cierre""");

                String opcionString = StdIn.readString();
                opcion = Integer.parseInt(opcionString);

                if (opcion < 1 || opcion > 4){

                    throw new NumberFormatException();
                }

                break;

            } catch (NumberFormatException e){

                StdOut.println("Ingrese un numero valido");
            }

        }while (true);

       return opcion;
  }

}
