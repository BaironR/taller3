import ucn.StdOut;

public class ListaInstrumentos {

    private final Instrumento[] lista;
    int cantMax;
    int cantActual;

    public ListaInstrumentos(int cantMax) {
        this.cantMax = cantMax;
        this.lista = new Instrumento[cantMax];
        cantActual = 0;
    }

    public void agregar(Instrumento instrumento){

        if (cantActual == cantMax){
            return;
        }

        this.lista[cantActual] = instrumento;
        cantActual++;
    }

    public Instrumento buscar(int pos){

        return this.lista[pos];
    }

    public void venderInstrumento(int pos){

        int stock = this.lista[pos].getStock()-1;

        if (stock < 0){

            StdOut.println("No hay stock del producto");
            return;
        }

        this.lista[pos].setStock(stock);
    }

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
