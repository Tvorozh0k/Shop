import java.util.Collections;
import java.util.HashMap;

/**
 * Класс список покупок
 */
public class PurchaseList {

    /**
     * Словарь покупок: пара (ID, покупка)
     */
    private HashMap<Integer, Purchase> purchases = new HashMap<>();

    /**
     * Уникальный идентификатор покупки
     */
    private Integer id = 0;

    /**
     * Конструктор
     * @param fileName название файла
     */
    public PurchaseList(String fileName) throws Exception {
        purchases = new JSONReader().getPurchases(fileName);
        id = Collections.max(purchases.keySet());
    }

    /**
     * Устанавливаем в JSON покупки
     * @param fileName название файла
     */
    public void setPurchases(String fileName) throws Exception {
        new JSONWriter().setPurchases(fileName, purchases);
    }

    /**
     * Добавляем новую покупку
     * @param purchase покупка
     */
    public void addPurchase(Purchase purchase) {
        purchases.put(++id, purchase);
    }

    /**
     * Получение покупки по ID
     *
     * @param id id покупки
     * @return покупка
     */
    public Purchase getPurchaseById(Integer id) throws Exception {
        if (!purchases.containsKey(id))
            throw new Exception("Покупки с заданным ID не существует");
        return purchases.get(id);
    }

    /**
     * Удаление покупки по ID
     *
     * @param id id покупки
     */
    public void removePurchaseById(Integer id) throws Exception {
        if (!purchases.containsKey(id))
            throw new Exception("Покупки с заданным ID не существует");
        purchases.remove(id);
    }

    HashMap<Integer, Purchase> getPurchases() {
        return this.purchases;
    }
}