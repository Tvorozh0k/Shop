import java.util.HashMap;

/**
 * Класс покупка
 */
public class Purchase {

    /**
     * ID клиента
     */
    private Integer clientID;

    /**
     * Список купленных товаров: пара (ID, кол-во товара)
     */
    private HashMap<Integer, Integer> products = new HashMap<>();

    /**
     * Конструктор
     *
     * @param clientID ID клиента
     */
    public Purchase(Integer clientID) {
        setClientID(clientID);
    }

    /**
     * Устанавливаем ID клиента
     *
     * @param clientID ID клиента
     */
    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    /**
     * Получаем ID клиента
     *
     * @return ID клиента
     */
    public Integer getClientID() {
        return this.clientID;
    }

    /**
     * Добавляем товар в покупку
     *
     * @param id    ID товара
     * @param count количество товара
     */
    public void addProductByID(Integer id, Integer count) throws Exception {
        if (count <= 0)
            throw new Exception("Количество товара должно быть положительным целым числом");
        products.put(id, count);
    }

    /**
     * Удаление товара из покупки
     *
     * @param id ID покупки
     */
    public void removeProductByID(Integer id) throws Exception {
        if (!products.containsKey(id))
            throw new Exception("В списке товар нет товара с данным ID");
        products.remove(id);
    }

    /**
     * Получаем список покупок
     * @return список покупок
     */
    public HashMap<Integer, Integer> getProducts() {
        return this.products;
    }
}
