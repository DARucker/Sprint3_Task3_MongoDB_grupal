package Sprint3Task3MONGO.org.example.service;


import Sprint3Task3MONGO.org.example.domain.*;
import Sprint3Task3MONGO.org.example.exception.GetMethodException;
import Sprint3Task3MONGO.org.example.exception.SumMethodException;

import java.util.List;

public interface Serv {

    //region METHODS: CHECK
    /**
     * Mètode x comprovar si hi ha suficient stock d'un producte.
     *
     * @param proSale La classe 'ProductforSale' per realitzar la comprovació.
     * @return Tipus boolena; False = no hi ha suficient stock; True: sí hi ha stock suficient.
     */
    boolean checkStock(ProductforSale proSale);

    //endregion METHODS: CHECK


    //region METHODS: CREATE
    /**
     * Metode per crear un nou tipus de producte, de qualsevol mena.
     *
     * @param product Necessita una classe del tipus de producte que s'ha de crear.
     * @return Tipus boolean. false=> No s'ha pogut crear el producte; true => s'ha creat el producte correctament
     */
    boolean createProduct(Product product);

    /**
     * Mètode per crear un nou tiket.
     *
     * @param ticket La classe ticket amb tota la informació.
     * @return Tipus boolean. False = Hi hagut algun error. True=> No hi hagut cap error.
     */
    boolean createTicket(Ticket ticket);

    //endregion METHODS: CREATE


    //region METHODS: GET
    /**
     * Mètode per aconseguir la llista de tots els productes
     *
     * @return Tipus List<Product>. Llista de tots els productes.
     * @throws GetMethodException En el cas que hi hagi algun error, saltarà aquesta execpció.
     */
    List<FlowerShop_stock> getAllProducts() throws GetMethodException;

    /**
     * Mètode per aconseguir la llista de totes les decoracions.
     *
     * @return Tipus List<Decoration>. La llista de totes les decoracions.
     * @throws GetMethodException En el cas que hi hagi algun error, saltarà aquesta execpció.
     */
    List<Decoration> getDecorationList() throws GetMethodException;

    /**
     * Mètode per aconseguir la llista de totes les flors.
     *
     * @return Tipus List<Flower>. La llista de totes les flors. Si es retorna un null, vol dir que hi hagut.
     * @throws GetMethodException En el cas que hi hagi algun error, saltarà aquesta execpció.
     */
    List<Flower> getFlowerList() throws GetMethodException;

    /**
     * Mètode que retorna una matriu de 3 integers amb el stock de cada element, on...
     * [0] és el stock de decorations
     * [1] és el stock de flowers
     * [2] és el stock d2 trees
     *
     * @return Tipus int[]. La matriu de 3 integers.
     * @throws GetMethodException En el cas que hi hagi algun error, saltarà aquesta execpció.
     */
    int[] getStock() throws GetMethodException;

    /**
     * Mètode per aconseguir la llista de tots els arbres.
     *
     * @return Tipus List<Tree>. La llista de tots els arbres. Si es retorna un null, vol dir que hi hagut.
     * @throws GetMethodException En el cas que hi hagi algun error, saltarà aquesta execpció.
     */
    List<Tree> getTreeList() throws GetMethodException;

    //endregion METHODS: GET


    //region METHODS: UPDATE

    /**
     * Mètode per actualitzar la info d'un producte.
     *
     * @param ticket agafa els productes de dins del ticket, el metode s'instancia cada vegada que es crea un ticket.
     * @return Tipus boolena. false = hi hagut algun error; true = tot ha sortit bé.
     */
    boolean updateStock(Ticket ticket);

    //endregion METHODS: UPDATE


    //region METHODS: OTHERS (INIT, SUM,...)
    /**
     * Mètode per sumar el valor de tot el stock de la floristeria.
     *
     * @return Tipus double. El valor de la suma.
     * @throws SumMethodException En el cas que hi hagi algun error, saltarà aquesta execpció.
     */
    double sumStock() throws SumMethodException;

    /**
     * Mètode per sumar el valor de tots els productesque hi ha en el ticket.
     *
     * @param ticket Necessita una classe ticket amb tota la info.
     * @return Tipus double. El valor del ticket.
     * @throws SumMethodException En el cas que hi hagi algun error, saltarà aquesta execpció.
     */
    double sumTicket(Ticket ticket) throws SumMethodException;

    /**
     * Mètode per sumar el valor de tots els tickets que s'han creat
     *
     * @return Tipus double. El valor de la suma. NOTA! Si el valor retornat és
     * @throws SumMethodException En el cas que hi hagi algun error, saltarà aquesta execpció.
     */
    double sumAllTickets() throws SumMethodException;

    boolean checkExistOnTicket(List<ProductforSale> ticketDetail, String name);

    //endregion METHODS: OTHERS (INIT, SUM,...)

}
