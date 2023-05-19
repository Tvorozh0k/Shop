import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static boolean isDateBetween(String date, String date1, String date2) {

        return !LocalDate.parse(date).isBefore(LocalDate.parse(date1)) &&
                !LocalDate.parse(date).isAfter(LocalDate.parse(date2));
    }

    public static void query1(HashMap<Integer, Client> clients, HashMap<Integer, Product> products, HashMap<Integer, Purchase> purchases) {

        ///////////////////////////////////////////////
        // 1. Список покупателей, купивших данных товар
        ///////////////////////////////////////////////

        String productName = "Телевизор Hisense 40A4BG";

        // a) получаем ID товара по его названию
        Integer productID = products.entrySet()
                .stream()
                .filter(p -> Objects.equals(p.getValue().getName(), productName))
                .findFirst()
                .get()
                .getKey();

        // б) получаем список покупателей, купивших данный товар

        var query1 = purchases.values()
                .stream()
                .filter(purchase -> purchase.getProducts().containsKey(productID))
                .map(purchase -> clients.get(purchase.getClientID()));

        query1.forEach(System.out::println);
    }

    public static void query2(HashMap<Integer, Client> clients, HashMap<Integer, Product> products, HashMap<Integer, Purchase> purchases) {

        /////////////////////////////////////////////////////////////////////////
        // 2. Список покупателей, купивших данных товар в какой-то период времени
        /////////////////////////////////////////////////////////////////////////

        String productName = "Телевизор Hisense 40A4BG";

        // a) получаем ID товара по его названию
        Integer productID = products.entrySet()
                .stream()
                .filter(p -> Objects.equals(p.getValue().getName(), productName))
                .findFirst()
                .get()
                .getKey();

        // б) получаем список покупателей, купивших данный товар

        String date1 = "2023-04-10";
        String date2 = "2023-04-19";

        var query2 = purchases.values()
                .stream()
                .filter(purchase -> purchase.getProducts().containsKey(productID))
                .filter(purchase -> isDateBetween(purchase.getPurchaseDate(), date1, date2))
                .map(purchase -> clients.get(purchase.getClientID()));

        query2.forEach(System.out::println);
    }

    public static void query3(HashMap<Integer, Client> clients, HashMap<Integer, Product> products, HashMap<Integer, Purchase> purchases) {

        //////////////////////////////////////////////////////////////////////////////////////////////
        // 3. Список покупателей, купивших хотя бы один из товаров из списка в какой-то период времени
        //////////////////////////////////////////////////////////////////////////////////////////////

        ArrayList<String> productNames = new ArrayList<>();

        productNames.add("Телевизор Hisense 40A4BG");
        productNames.add("Смартфон Samsung Galaxy A13 3/32GB White");

        // a) получаем ID товаров по их названиям
        Set<Integer> productsID = productNames.stream()
                .map(name -> {
                    return products.entrySet()
                            .stream()
                            .filter(p -> Objects.equals(p.getValue().getName(), name))
                            .findFirst()
                            .get()
                            .getKey();
                })
                .collect(Collectors.toSet());

        // б) получаем список покупателей

        String date1 = "2023-04-01";
        String date2 = "2023-04-15";

        var query3 = purchases.values()
                .stream()
                .filter(purchase -> {
                    Set<Integer> copyset = new HashSet<Integer>(productsID);
                    copyset.retainAll(purchase.getProducts().keySet());
                    return copyset.size() > 0;
                })
                .filter(purchase -> isDateBetween(purchase.getPurchaseDate(), date1, date2))
                .map(purchase -> clients.get(purchase.getClientID()));

        query3.forEach(System.out::println);
    }

    public static void query4(HashMap<Integer, Client> clients) {

        ////////////////////////////////////////////////////
        // 4. Список покупателей с конкретной датой рождения
        ////////////////////////////////////////////////////

        String birthDate = "-03-09";

        var query4 = clients.values()
                .stream()
                .filter(client -> client.getBirthDate().matches(String.format(".{4}%s", birthDate)));

        query4.forEach(System.out::println);
    }

    public static void main(String[] args) throws Exception {
        // Считываем данные из JSON
        HashMap<Integer, Client> clients = new ClientList("Clients.json").getClients();
        HashMap<Integer, Product> products = new ProductList("Products.json").getProducts();
        HashMap<Integer, Purchase> purchases = new PurchaseList("Purchases.json").getPurchases();

        /*
        
 Результат запроса:
 
ФИО: Поликарпова Ольга Данииловна
Дата рождения: 1962-07-27
Место рождения: гор. Каменск
Серия и номер паспорта: 4485 880633
Дата выдачи паспорта: 2013-12-01
Код подразделения: 700-903

ФИО: Козырев Никита Михаилович
Дата рождения: 1998-03-09
Место рождения: гор. Пенза
Серия и номер паспорта: 4438 219386
Дата выдачи паспорта: 2013-09-02
Код подразделения: 500-373
        
        */
        
        // query2(clients, products, purchases);
        
        /*

Результат запроса:

ФИО: Козырев Никита Михаилович
Дата рождения: 1998-03-09
Место рождения: гор. Пенза
Серия и номер паспорта: 4438 219386
Дата выдачи паспорта: 2013-09-02
Код подразделения: 500-373 
        
        */
        
        // query3(clients, products, purchases);
        
        /*

Результат запроса:

ФИО: Базаров Дмитрий Ильич
Дата рождения: 1995-04-28
Место рождения: гор. Коломна
Серия и номер паспорта: 4259 101807
Дата выдачи паспорта: 2016-10-23
Код подразделения: 410-203

ФИО: Козырев Никита Михаилович
Дата рождения: 1998-03-09
Место рождения: гор. Пенза
Серия и номер паспорта: 4438 219386
Дата выдачи паспорта: 2013-09-02
Код подразделения: 500-373
        
        */
        
        query4(clients);
        
        /*
        
Результат запроса:
 
ФИО: Козырев Никита Михаилович
Дата рождения: 1998-03-09
Место рождения: гор. Пенза
Серия и номер паспорта: 4438 219386
Дата выдачи паспорта: 2013-09-02
Код подразделения: 500-373

ФИО: Приказчикова Пелагея Игнатьевна
Дата рождения: 1961-03-09
Место рождения: гор. Волжский
Серия и номер паспорта: 4960 243361
Дата выдачи паспорта: 2022-01-26
Код подразделения: 430-606
        
        */
    }
}
