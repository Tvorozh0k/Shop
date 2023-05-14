import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Класс, работающий с JSON
 */
public class JSONReader {

    /**
     * Считываем клиентов из JSON
     *
     * @param fileName название файла
     * @return список клиентов
     */
    public HashMap<Integer, Client> getClients(String fileName) throws Exception {
        HashMap<Integer, Client> cl = new HashMap<>();

        JSONArray jo = (JSONArray) (new JSONParser().parse(new FileReader(String.format(".\\src\\%s", fileName))));
        Iterator itr = jo.iterator();

        while (itr.hasNext())
        {
            JSONObject obj = (JSONObject) itr.next();

            cl.put(Integer.parseInt(obj.get("ID").toString()), new Client(obj.get("Фамилия").toString(), obj.get("Имя").toString(),
                    obj.get("Отчество").toString(), obj.get("Дата рождения").toString(), obj.get("Место рождения").toString(),
                    obj.get("Серия и номер паспорта").toString(), obj.get("Дата выдачи").toString(), obj.get("Код подразделения").toString()));
        }

        return cl;
    }

    /**
     * Считываем товары из JSON
     * @param fileName название файла
     * @return список товаров
     */
    public HashMap<Integer, Product> getProducts(String fileName) throws Exception {
        HashMap<Integer, Product> pr = new HashMap<>();

        JSONArray jo = (JSONArray) (new JSONParser().parse(new FileReader(String.format(".\\src\\%s", fileName))));
        Iterator itr = jo.iterator();

        while (itr.hasNext())
        {
            JSONObject obj = (JSONObject) itr.next();

            pr.put(Integer.parseInt(obj.get("ID").toString()), new Product(obj.get("Название").toString(),
                    Integer.parseInt(obj.get("Стоимость").toString()), obj.get("Единица измерения").toString()));
        }

        return pr;
    }

    /**
     * Считываем покупки из JSON
     * @param fileName название файла
     * @return список покупок
     */
    public HashMap<Integer, Purchase> getPurchases(String fileName) throws Exception {
        HashMap<Integer, Purchase> pl = new HashMap<>();

        JSONArray jo = (JSONArray) (new JSONParser().parse(new FileReader(String.format(".\\src\\%s", fileName))));
        Iterator itr1 = jo.iterator();

        while (itr1.hasNext())
        {
            JSONObject obj = (JSONObject) itr1.next();

            Integer id = Integer.parseInt(obj.get("ID").toString());
            Integer clientID = Integer.parseInt(obj.get("ID клиента").toString());
            Purchase purchase = new Purchase(clientID);

            JSONArray ja = (JSONArray) obj.get("Товары");
            Iterator itr2 = ja.iterator();

            while (itr2.hasNext())
            {
                obj = (JSONObject) itr2.next();

                purchase.addProductByID(Integer.parseInt(obj.get("ID").toString()),
                        Integer.parseInt(obj.get("Количество").toString()));
            }

            pl.put(id, purchase);
        }

        return pl;
    }
}
