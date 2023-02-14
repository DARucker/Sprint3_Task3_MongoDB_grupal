package Sprint3Task3MONGO.org.example.service;

import Sprint3Task3MONGO.org.example.domain.*;
import Sprint3Task3MONGO.org.example.exception.GetMethodException;
import Sprint3Task3MONGO.org.example.exception.SumMethodException;
import Sprint3Task3MONGO.org.example.repository.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Service implements Serv{
    //region ATTRIBUTES
    private static Repository repoCls;

    //endregion ATTRIBUTES


    //region METHODS: CHECK

    @Override
    public boolean checkStock(ProductforSale proSale) {
        //region DEFINITION VARIABLES
        boolean resul = false, contin = false;
        int i = 0;
        List<Decoration> decoList;
        List<Flower> flowerList;
        List<Tree> treeList;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // 1) GET ALL PRODUCTS OF SAME TYPE
            if (proSale.getProduct().getDecoration() != null) {
                decoList = new ArrayList<>(getDecoProducts());

                // 2) CHECK PRODUCT STOCK
                while (i < decoList.size() && !contin) {
                    if (decoList.get(i).getName().equals(proSale.getProduct().getDecoration().getName())) {
                        contin = true;
                        resul = proSale.getQuantity() <= decoList.get(i).getQuantity();
                    }
                    i++;
                }
            } else if (proSale.getProduct().getFlower() != null) {
                flowerList = new ArrayList<>(getFlowerProducts());

                // 2) CHECK PRODUCT STOCK
                while (i < flowerList.size() && !contin) {
                    if (flowerList.get(i).getName().equals(proSale.getProduct().getFlower().getName())) {
                        contin = true;
                        resul = proSale.getQuantity() <= flowerList.get(i).getQuantity();
                    }
                    i++;
                }
            } else if (proSale.getProduct().getTree() != null) {
                treeList = new ArrayList<>(getTreeProducts());

                // 2) CHECK PRODUCT STOCK
                while (i < treeList.size() && !contin) {
                    if (treeList.get(i).getName().equals(proSale.getProduct().getTree().getName())) {
                        contin = true;
                        resul = proSale.getQuantity() <= treeList.get(i).getQuantity();
                    }
                    i++;
                }
            }

        } catch (Exception ex) {
            resul = false;
        }

        //endregion ACTIONS


        // OUT
        return resul;
    }


    public boolean checkExistOnTicket(List<ProductforSale> proSafeListIn, String nameIn){
        //region DEFINITION VARIABLES
        boolean resul = false, exit = false;
        int index = 0;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try{
            do{
                if (proSafeListIn.get(index).getProduct().getDecoration() != null) {
                    if(proSafeListIn.get(index).getProduct().getDecoration().getName().equals(nameIn)){
                        exit =true;
                        resul = true;
                    }
                    }
                else if (proSafeListIn.get(index).getProduct().getTree() != null) {
                if(proSafeListIn.get(index).getProduct().getTree().getName().equals(nameIn)){
                    exit =true;
                    resul = true;
                }
                } else if (proSafeListIn.get(index).getProduct().getFlower() != null) {
                if(proSafeListIn.get(index).getProduct().getFlower().getName().equals(nameIn)){
                    exit =true;
                    resul = true;
                }
                }

                index++;
            }while (proSafeListIn.size() < index || exit != true);


        }catch (Exception ex){

        }
        //endregion ACTIONS


        // OUT
        return resul;


    }

    //endregion METHODS: CHECK


    //region METHODS: CREATE

    @Override
    public boolean createProduct(Product product) {
        //region DEFINITION VARIABLES
        boolean resul = false;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            repoCls = new Repository();

            // CALL REPOSITORY METHODS
            if (product.getClass() == Decoration.class) {
                if (repoCls.findbyName(product.getName(), "Decoration") == false) {
                    repoCls.createDeco((Decoration) product);
                    resul = true;
                }
            } else if (product.getClass() == Flower.class) {
                if (repoCls.findbyName(product.getName(), "Flower") == false) {
                    repoCls.createFlower((Flower) product);
                    resul = true;
                }
            } else if (product.getClass() == Tree.class) {
                if (repoCls.findbyName(product.getName(), "Tree") == false) {
                    repoCls.createTree((Tree) product);
                    resul = true;
                }
            }

        } catch (Exception ex) {
            resul = false;
        }

        //endregion ACTIONS


        // OUT
        return resul;

    }

    @Override
    public boolean createTicket(Ticket ticket) {
        //region DEFINITION VARIABLES
        boolean result;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            repoCls = new Repository();

            // 1) CALCULATE TOTAL PRICE
            ticket.setTotalPrice(sumTicket(ticket));

            // 2) SAVE TICKET
            repoCls.createTicket(ticket);

            // 3) UPDATE STOCKS
            result = updateStock(ticket);

        } catch (Exception ex) {
            result = false;
        }

        //endregion ACTIONS


        // OUT
        return result;

    }

    //endregion METHODS: CREATE


    //region METHODS: GET
    @Override
    public List<FlowerShop_stock> getAllProducts() throws GetMethodException {
        //region DEFINITION VARIABLES
        List<FlowerShop_stock> productList;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            repoCls = new Repository();

            // CALL REPOSITORY METHOD
            productList = new ArrayList<>(repoCls.getAllProducts());

        } catch (Exception ex) {
            throw new GetMethodException(1);
        }

        //endregion ACTIONS


        // OUT
        return productList;

    }


    @Override
    public List<Decoration> getDecorationList() throws GetMethodException {
        //region DEFINITION VARIABLES
        List<Decoration> decoList;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // GET DECORATION PRODUCTS
            decoList = new ArrayList<>(getDecoProducts());

        } catch (Exception ex) {
            throw new GetMethodException(2);
        }
        //endregion ACTIONS


        // OUT
        return decoList;
    }


    @Override
    public List<Flower> getFlowerList() throws GetMethodException {
        //region DEFINITION VARIABLES
        List<Flower> flowerList;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // GET FLOWERS PRODUCTS
            flowerList = new ArrayList<>(getFlowerProducts());

        } catch (Exception ex) {
            throw new GetMethodException(3);
        }

        //endregion ACTIONS


        // OUT
        return flowerList;

    }

    @Override
    public int[] getStock() throws GetMethodException {
        //region DEFINITION VARIABLES
        int[] results = new int[3];
        List<FlowerShop_stock> productList;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT
            repoCls = new Repository();

            // 1) GET STOCK
            productList = new ArrayList<>(repoCls.getAllProducts());

            // 3) SUM ALL STOCK
            for (FlowerShop_stock p : productList) {
                if (p.getDecoration() != null) {
                    results[0] += p.getDecoration().getQuantity();
                } else if (p.getFlower() != null) {
                    results[1] += p.getFlower().getQuantity();
                } else if (p.getTree() != null) {
                    results[2] += p.getTree().getQuantity();
                }
            }

        } catch (Exception ex) {
            throw new GetMethodException(5);
        }

        //endregion ACTIONS


        // OUT
        return results;
    }

    @Override
    public List<Tree> getTreeList() throws GetMethodException {
        //region DEFINITION VARIABLES
        List<Tree> treeList;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // GET TREE PRODUCTS
            treeList = new ArrayList<>(getTreeProducts());

        } catch (Exception ex) {
            throw new GetMethodException(4);
        }

        //endregion ACTIONS


        // OUT
        return treeList;

    }

    //endregion METHODS: GET


    //region METHODS: UPDATE

    @Override
    public boolean updateStock(Ticket ticket) {
        //region DEFINITION VARIABLES
        boolean result = false;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT
            repoCls = new Repository();

            // ITERATE FOR ALL PRODUCTS
            for (ProductforSale p : ticket.getProductforSales()) {
                int updatedquantity = 0;

                if (p.getProduct().getDecoration() != null) {
                    updatedquantity = p.getProduct().getDecoration().getQuantity() - p.getQuantity();
                    repoCls.updateDeco(p.getProduct().getDecoration(), updatedquantity);
                } else if (p.getProduct().getFlower() != null) {
                    updatedquantity = p.getProduct().getFlower().getQuantity() - p.getQuantity();
                    repoCls.updateFlower(p.getProduct().getFlower(), updatedquantity);
                } else if (p.getProduct().getTree() != null) {
                    updatedquantity = p.getProduct().getTree().getQuantity() - p.getQuantity();
                    repoCls.updateTree(p.getProduct().getTree(), updatedquantity);
                }
            }

            result = true;
        } catch (Exception ex) {
            result = false;
        }

        //endregion ACTIONS


        // OUT
        return result;

    }

    //endregion METHODS: UPDATE


    //region METHODS: OTHERS (INIT, SUM,...)

    @Override
    public double sumStock() throws SumMethodException {
        //region DEFINITION VARIABLES
        double result = 0;
        List<FlowerShop_stock> productList;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VALUES
            repoCls = new Repository();

            /// 1) GET PRODCUTS
            productList = new ArrayList<>(repoCls.getAllProducts());

            // 2) SUM SCTOCK VALUE
            for (FlowerShop_stock p : productList) {
                if (p.getDecoration() != null) {
                    result += p.getDecoration().getPrice() * p.getDecoration().getQuantity();
                } else if (p.getFlower() != null) {
                    result += p.getFlower().getPrice() * p.getFlower().getQuantity();
                } else if (p.getTree() != null) {
                    result += p.getTree().getPrice() * p.getTree().getQuantity();
                }

            }

        } catch (Exception ex) {
            throw new SumMethodException(3);
        }

        //endregion ACTIONS


        // OUT
        return result;
    }

    @Override
    public double sumTicket(Ticket ticket) throws SumMethodException {
        //region DEFINITION VARIABLES
        double result = 0.0;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // SUM VALUES
            for (ProductforSale p : ticket.getProductforSales()) {
                result += p.getQuantity() * p.getPrice();
            }


        } catch (Exception ex) {
            throw new SumMethodException(2);
        }

        //endregion ACTIONS


        // OUT
        return result;

    }

    /**
     * Mètode per sumar el valor de tots els tiquets que s'han creat.
     *
     * @return El valor de la suma. NOTA! Si el valor retornat és
     * @throws SumMethodException En el cas que hi hagi algun error, saltarà aquesta execpció.
     */
    @Override
    public double sumAllTickets() throws SumMethodException {
        //region DEFINITION VARIABLES
        double result = 0;
        List<Ticket> ticketList;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        try {
            // INIT VARIABLES
            repoCls = new Repository();

            // 1) GET ALL TICKETS ON DDBB
            ticketList = new ArrayList<>(repoCls.getAllSells());

            // 2) SUM TICKETS VALUES
            for (Ticket t : ticketList) {
                result += t.getTotalPrice();
            }

        } catch (Exception ex) {
            throw new SumMethodException(1);
        }

        //endregion ACTIONS


        // OUT
        return result;

    }

    //endregion METHODS: OTHERS (SUM,...)


    //region PRIVATE METHODS

    /**
     * Mètode que retorna tots els prodcutes tipus Decoration.
     *
     * @return Tipus List<Decoration>. La llista amb els productes tipus Decoration.
     * @throws IOException En el cas que hi hagi algun error, saltarà aquesta execpció.
     */
    private List<Decoration> getDecoProducts() throws IOException {
        //region DEFINITION VARIABLES
        List<Decoration> decoList;
        List<FlowerShop_stock> productList;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT VARIABLES
        decoList = new ArrayList<>();
        repoCls = new Repository();

        // 1) GET ALL PRODUCTS
        productList = new ArrayList<>(repoCls.getAllProducts());

        // 2) FIND DECORATION PRODUCTS
        for (FlowerShop_stock p : productList) {
            if (p.getDecoration() != null) {
                decoList.add(p.getDecoration());
            }
        }

        //endregion ACTIONS


        // OUT
        return decoList;

    }

    /**
     * Mètode que retorna tots els prodcutes tipus Flower.
     *
     * @return Tipus List<Flower>. La llista amb els productes tipus Flower.
     * @throws IOException En el cas que hi hagi algun error, saltarà aquesta execpció.
     */
    private List<Flower> getFlowerProducts() throws IOException {
        //region DEFINITION VARIABLES
        List<Flower> flowerList;
        List<FlowerShop_stock> productList;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT VARIABLES
        flowerList = new ArrayList<>();
        repoCls = new Repository();

        // 1) GET ALL PRODUCTS
        productList = new ArrayList<>(repoCls.getAllProducts());

        // 2) FIND DECORATION PRODUCTS
        for (FlowerShop_stock p : productList) {
            if (p.getFlower() != null) {
                flowerList.add(p.getFlower());
            }
        }

        //endregion ACTIONS


        // OUT
        return flowerList;

    }

    /**
     * Mètode que retorna tots els prodcutes tipus Tree.
     *
     * @return Tipus List<Decoration>. La llista amb els productes tipus Tree.
     * @throws IOException En el cas que hi hagi algun error, saltarà aquesta execpció.
     */
    private List<Tree> getTreeProducts() throws IOException {
        //region DEFINITION VARIABLES
        List<Tree> treeList;
        List<FlowerShop_stock> productList;

        //endregion DEFINITION VARIABLES


        //region ACTIONS
        // INIT VARIABLES
        treeList = new ArrayList<>();

        repoCls = new Repository();

        // 1) GET ALL PRODUCTS
        productList = new ArrayList<>(repoCls.getAllProducts());

        // 2) FIND DECORATION PRODUCTS
        for (FlowerShop_stock p : productList) {
            if (p.getTree() != null) {
                treeList.add(p.getTree());
            }
        }

        //endregion ACTIONS


        // OUT
        return treeList;

    }

    /**
     * Mètode per actualitzar el stock de tots els articles
     *
     * @param ticket Classe del tipus Ticket amb la llista de tots els productes que s'han comprat.
     * @return Tipus boolean. False = hi ha hagut algun problema; True = No hi hagut cap problema.
     */


    //endregion PRIVATE METHODS


}

