package Sprint3Task3MONGO.org.example.exception;

/**
 * Excepció que es llença quan en un mètode de sumar valors de la capa serveis, hi ha algun error.
 * Per saber quin mètode l'ha llançat el missatge és un nº amb els següents valors...
 * 1 => sumAllTickets
 * 2 => sumTicket
 * 3 => sumStock
 */
public class SumMethodException extends Exception{
    public SumMethodException(int numMethod){
        super(String.valueOf(numMethod));
    }

}
