import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Класс, работающий с JSON
 */
public class JSONWriter {

    /**
     * Записываем данные о клиентах в JSON
     *
     * @param fileName название файла
     * @param clients  клиенты
     */
    public void setClients(String fileName, HashMap<Integer, Client> clients) throws Exception {
        JSONArray jo = new JSONArray();

        for (var entry : clients.entrySet()) {
            Integer id = entry.getKey();
            Client client = entry.getValue();

            JSONObject obj = new JSONObject();

            obj.put("ID", id);
            obj.put("Фамилия", client.getLastName());
            obj.put("Имя", client.getFirstName());
            obj.put("Отчество", client.getPatronymic());
            obj.put("Дата рождения", client.getBirthDate());
            obj.put("Место рождения", client.getBirthPlace());
            obj.put("Серия и номер паспорта", client.getPassportNumber());
            obj.put("Дата выдачи", client.getIssueDate());
            obj.put("Код подразделения", client.getDepartmentCode());

            jo.add(obj);
        }

        PrintWriter pw = new PrintWriter(".\\src\\Output.json");
        pw.write(jo.toJSONString());

        pw.flush();
        pw.close();
    }

    /**
     * Записываем данные о товарах в JSON
     *
     * @param fileName название файла
     * @param products товары
     */
    public void setProducts(String fileName, HashMap<Integer, Product> products) throws Exception {
        JSONArray jo = new JSONArray();

        for (var entry : products.entrySet()) {
            Integer id = entry.getKey();
            Product product = entry.getValue();

            JSONObject obj = new JSONObject();

            obj.put("ID", id);
            obj.put("Название", product.getName());
            obj.put("Стоимость", product.getPrice());
            obj.put("Единица измерения", product.getUnit());

            jo.add(obj);
        }

        PrintWriter pw = new PrintWriter(".\\src\\Output.json");
        pw.write(jo.toJSONString());

        pw.flush();
        pw.close();
    }

    /**
     * Записываем данные о покупках в JSON
     *
     * @param fileName название файла
     * @param purchases покупки
     */
    public void setPurchases(String fileName, HashMap<Integer, Purchase> purchases) throws Exception {
        JSONArray jo = new JSONArray();

        for (var entry1 : purchases.entrySet()) {
            Integer id1 = entry1.getKey();
            Purchase purchase = entry1.getValue();

            JSONObject obj1 = new JSONObject();

            obj1.put("ID", id1);
            obj1.put("ID клиента", purchase.getClientID());

            JSONArray ja = new JSONArray();

            for (var entry2 : purchase.getProducts().entrySet()) {
                Integer id2 = entry2.getKey();
                Integer count = entry2.getValue();

                JSONObject obj2 = new JSONObject();

                obj2.put("ID", id2);
                obj2.put("Количество", count);

                ja.add(obj2);
            }

            obj1.put("Товары", ja);
            jo.add(obj1);
        }

        PrintWriter pw = new PrintWriter(".\\src\\Output.json");
        pw.write(jo.toJSONString());

        pw.flush();
        pw.close();
    }
}