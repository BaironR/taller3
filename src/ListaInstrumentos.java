import ucn.StdOut;

/**
 * Lista de variables tipo Instrumento
 */
public class ListaInstrumentos {

    private final Instrumento[] lista;
    int cantMax;
    int cantActual;

    /**
     * Constructor de ListaInstrumentos
     *
     * @param cantMax tamanio del arreglo
     */
    public ListaInstrumentos(int cantMax) {
        this.cantMax = cantMax;
        this.lista = new Instrumento[cantMax];
        cantActual = 0;
    }

    /**
     * Metodo para agregar instrumentos a la lista
     *
     * @param instrumento a agregar
     */
    public void agregar(Instrumento instrumento){

        if (cantActual == cantMax){
            return;
        }

        this.lista[cantActual] = instrumento;
        cantActual++;
    }

    /**
     * Metodo para buscar instrumentos en la lista
     *
     * @param pos del instrumento a buscar
     * @return this.lista[pos]: Instrumento almacenado en la lista en la posicion pos
     */
    public Instrumento buscar(int pos){

        return this.lista[pos];
    }

    /**
     * Metodo para vender un instrumento, le resta 1 al stock y lo setea
     *
     * @param pos del instrumento a vender
     */
    public void venderInstrumento(int pos){

        int stock = this.lista[pos].getStock()-1;

        if (stock < 0){

            StdOut.println("No hay stock del producto");
            return;
        }

        this.lista[pos].setStock(stock);
    }

    /**
     * Metodo para eliminar instrumentos repetidos, si son iguales en todos sus atributos se suma su stock
     */
    public void filtrarLista() {

        for (int i=0; i< (this.cantActual-1) ; i++){

            boolean existeRepetido = true;

            while (existeRepetido){

                existeRepetido = false;

                for (int j= (i+1); j < this.cantActual; j++){

                    boolean esIgual = esIgual(this.lista[i], this.lista[j]);

                    if (esIgual){

                        int stockTotal = this.lista[i].getStock()+this.lista[j].getStock();

                        for (int k=j ; k<(this.cantActual-1); k++){

                            lista[k] = lista[(k+1)];
                        }

                        existeRepetido = true;
                        this.lista[i].setStock(stockTotal);
                        this.cantActual--;
                        break;

                    } else if (this.lista[i].getCodigo().equalsIgnoreCase(this.lista[j].getCodigo()) || this.lista[i].getInstrumento().equalsIgnoreCase(this.lista[j].getInstrumento())){

                        for (int k=j ; k<(this.cantActual-1); k++){

                            lista[k] = lista[(k+1)];
                        }

                        existeRepetido = true;
                        this.cantActual--;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Metodo para comparar dos instrumentos en base a sus atributos
     *
     * @param e1 instrumento 1
     * @param e2 instrumento 2
     * @return true si son iguales, false si no lo son
     */
    public boolean esIgual(Instrumento e1, Instrumento e2){

        if (e1 instanceof Cuerda && e2 instanceof Cuerda){

            if (e1.getCodigo().equalsIgnoreCase(e2.getCodigo()) && e1.getInstrumento().equalsIgnoreCase(e2.getInstrumento())
                    && ((Cuerda) e1).getNumCuerdas() == ((Cuerda) e2).getNumCuerdas() && ((Cuerda) e1).getTipoCuerda().equalsIgnoreCase(((Cuerda) e2).getTipoCuerda())
                    && e1.getMaterial().equalsIgnoreCase(e2.getMaterial()) && ((Cuerda) e1).getTipoInstrumento().equalsIgnoreCase(((Cuerda) e2).getTipoInstrumento())){

                return true;
            }
        }

        else if (e1 instanceof Percusion && e2 instanceof Percusion){

            if (e1.getCodigo().equalsIgnoreCase(e2.getCodigo()) && e1.getInstrumento().equalsIgnoreCase(e2.getInstrumento())
                    && ((Percusion) e1).getTipoPercusion().equalsIgnoreCase(((Percusion) e2).getTipoPercusion()) && e1.getMaterial().equalsIgnoreCase(e2.getMaterial())
                    && ((Percusion) e1).getAltura().equalsIgnoreCase(((Percusion) e2).getAltura())){

                return true;
            }
        }

        else if (e1 instanceof Viento && e2 instanceof Viento){

            if (e1.getCodigo().equalsIgnoreCase(e2.getCodigo()) && e1.getInstrumento().equalsIgnoreCase(e2.getInstrumento()) && e1.getMaterial().equalsIgnoreCase(e2.getMaterial())){

                return true;
            }
        }

        return false;
    }
}
