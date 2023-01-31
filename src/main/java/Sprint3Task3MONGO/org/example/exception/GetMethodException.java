package Sprint3Task3MONGO.org.example.exception;

/**
 * Excepció que es llença quan en un mètode de 'get' de la capa serveis, hi ha algun error.
 * Per saber quin mètode l'ha llançat l'execpció, en el missatge és un nº amb els següents valors...
 * 1 => getAllProducts
 * 2 => getDecorationList
 * 3 => getFlowerList
 * 4 => getTreeList
 * 5 => getStock
 */
public class GetMethodException extends Exception{
    public GetMethodException(int numMethod){
        super(String.valueOf(numMethod));
    }
}
