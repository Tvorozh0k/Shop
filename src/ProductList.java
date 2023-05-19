import java.util.Collections;
import java.util.HashMap;

/**
 * Класс список товаров
 */
public class ProductList {

    /**
     * Словарь товаров: пара (ID, товар)
     */
    private HashMap<Integer, Product> products = new HashMap<>();

    /**
     * Уникальный идентификатор товара
     */
    private Integer id = 0;

    /**
     * Конструктор
     * @param fileName название файла
     */
    public ProductList(String fileName) throws Exception {
        products = new JSONReader().getProducts(fileName);
        id = Collections.max(products.keySet());
    }

    /**
     * Устанавливаем в JSON товары
     * @param fileName название файла
     */
    public void setProducts(String fileName) throws Exception {
        new JSONWriter().setProducts(fileName, products);
    }

    /**
     * Добавляем новый товар в БД
     *
     * @param name  название товара
     * @param price стоимость товара
     * @param unit  единица измерения товара
     */
    public void addProduct(String name, Integer price, String unit) throws Exception {
        products.put(++id, new Product(name, price, unit));
    }

    /**
     * Поиск товара по его ID
     *
     * @param id ID товара
     * @return true, если товар существует, иначе - false
     */
    public Boolean findProductById(Integer id) {
        return products.containsKey(id);
    }

    /**
     * Получение товара по ID
     *
     * @param id id товара
     * @return товар
     */
    public Product getProductById(Integer id) throws Exception {
        if (!products.containsKey(id))
            throw new Exception("Товара с заданным ID не существует");
        return products.get(id);
    }

    /**
     * Удаление товара по ID
     *
     * @param id id товара
     */
    public void removeProductById(Integer id) throws Exception {
        if (!products.containsKey(id))
            throw new Exception("Товара с заданным ID не существует");
        products.remove(id);
    }

    /**
     * Полная информация о товарах в формате таблицы
     *
     * @return полная информация о товарах
     */
    @Override
    public String toString() {
        String res = "";

        res += "+-------+----------------------------------------------------+----------+-------+\n";
        res += "| ID    | Название                                           | Цена     | Ед.   |\n";
        res += "+-------+----------------------------------------------------+----------+-------+\n";

        for (var entry : products.entrySet()) {
            Integer id = entry.getKey();
            Product product = entry.getValue();
            res += String.format("| %-5s | %-50s | %-8d | %-5s |\n", id,
                    product.getName(), product.getPrice(), product.getUnit());
        }

        res += "+-------+----------------------------------------------------+----------+-------+\n";
        return res;
    }

    HashMap<Integer, Product> getProducts() {
        return this.products;
    }
}
