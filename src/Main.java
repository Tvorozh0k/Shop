public class Main {
    public static void checkClients() throws Exception {
        // Работа с клиентами

        ClientList cl = new ClientList("Clients.json");

        cl.addClient("Фадеев", "Ренат", "Кириллович",
                "1966-03-05", "гор. Энгельс", "4985 137554",
                "2022-10-28", "580-726");

        cl.removeClientById(3);
        cl.setClients("Output.json");
        System.out.println(cl);
    }

    public static void checkProducts() throws Exception {
        // Работа с товарами

        ProductList pl = new ProductList("Products.json");

        pl.addProduct("Консоль Sony PlayStation 5 Blu-Ray Edition", 56999, "шт.");

        pl.removeProductById(3);
        pl.setProducts("Output.json");
        System.out.println(pl);
    }

    public static void checkPurchases() throws Exception {
        // Работа с покупками

        PurchaseList pl = new PurchaseList("Purchases.json");
        pl.setPurchases("Output.json");
        System.out.println(pl);
    }


    public static void main(String[] args) throws Exception {
        // checkClients();
        // checkProducts();
        checkPurchases();
    }
}