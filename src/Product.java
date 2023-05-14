/**
 * Класс товар
 */
public class Product {

    /**
     * Название товара и его единица
     * измерения
     */
    private String name, unit;

    /**
     * Стоимость товара
     */
    private Integer price;

    /**
     * Конструктор
     *
     * @param name  название товара
     * @param price стоимость товара
     * @param unit  единица измерения товара
     */
    public Product(String name, Integer price, String unit) throws Exception {
        setName(name);
        setPrice(price);
        setUnit(unit);
    }

    /**
     * Устанавливаем название товара
     *
     * @param name название товара
     */
    public void setName(String name) throws Exception {
        if (!name.matches("[0-9A-ZА-Я].*"))
            throw new Exception("Некорректное название товара");
        this.name = name;
    }

    /**
     * Получаем название товара
     *
     * @return название товара
     */
    public String getName() {
        return this.name;
    }

    /**
     * Устанавливаем единицу измерения товара
     *
     * @param unit единица измерения товара
     */
    public void setUnit(String unit) throws Exception {
        if (!unit.matches(".*\\."))
            throw new Exception("Некорректная единица измерения товара");
        this.unit = unit;
    }

    /**
     * Получаем единицу измерения товара
     *
     * @return единица измерения товара
     */
    public String getUnit() {
        return this.unit;
    }

    /**
     * Устанавливаем цену товара
     *
     * @param price цена товаа
     */
    public void setPrice(Integer price) throws Exception {
        if (price <= 0)
            throw new Exception("Цена товара должна быть положительным целым числом");
        this.price = price;
    }

    /**
     * Получаем цену товара
     *
     * @return цена товара
     */
    public Integer getPrice() {
        return this.price;
    }

    /**
     * Получаем полную информацию о товаре
     *
     * @return полная информация о товаре
     */
    @Override
    public String toString() {
        String res = "";

        res += String.format("Название: %s", name);
        res += String.format("Стоимость: %d", price);
        res += String.format("Единица измерения: %s", unit);

        return res;
    }
}