import java.util.Collections;
import java.util.HashMap;

/**
 * Класс список клиентов
 */
public class ClientList {

    /**
     * Словарь клиентов: пара (ID, клиент)
     */
    private HashMap<Integer, Client> clients = new HashMap<>();

    /**
     * Уникальный идентификатор клиента
     */
    private Integer id = 0;

    /**
     * Конструктор
     *
     * @param fileName название файла
     */
    public ClientList(String fileName) throws Exception {
        clients = new JSONReader().getClients(fileName);
        id = Collections.max(clients.keySet());
    }

    /**
     * Устанавливаем в JSON клиентов
     *
     * @param fileName название файла
     */
    public void setClients(String fileName) throws Exception {
        new JSONWriter().setClients(fileName, clients);
    }

    /**
     * Добавляем нового клиента в БД
     *
     * @param lastName       фамилия клиента
     * @param firstName      имя клиента
     * @param patronymic     отчество клиента
     * @param birthDate      дата рождения клиента
     * @param birthPlace     место рождения клиента
     * @param passportNumber серия и номер паспорта клиента
     * @param issueDate      дата выдачи паспорта
     * @param departmentCode код подразделения
     */
    public void addClient(String lastName, String firstName, String patronymic, String birthDate, String birthPlace, String passportNumber, String issueDate, String departmentCode) throws Exception {
        clients.put(++id, new Client(lastName, firstName, patronymic, birthDate, birthPlace, passportNumber, issueDate, departmentCode));
    }

    /**
     * Получение клиента по ID
     *
     * @param id id клиента
     * @return клиент
     */
    public Client getClientById(Integer id) throws Exception {
        if (!clients.containsKey(id))
            throw new Exception("Клиента с заданным ID не существует");
        return clients.get(id);
    }

    /**
     * Удаление клиента по ID
     *
     * @param id id клиента
     */
    public void removeClientById(Integer id) throws Exception {
        if (!clients.containsKey(id))
            throw new Exception("Клиента с заданным ID не существует");
        clients.remove(id);
    }

    /**
     * Полная информация о клиентах в формате таблицы
     *
     * @return полная информация о клиентах
     */
    @Override
    public String toString() {
        String res = "";

        res += "+-------+----------------------+-----------------+-----------------+------------+----------------------+-------------+------------+---------+\n";
        res += "| ID    | Фамилия              | Имя             | Отчество        | Дата рожд. | Место рождения       | Сер. и ном. | Дата выд.  | Код п.  |\n";
        res += "+-------+----------------------+-----------------+-----------------+------------+----------------------+-------------+------------+---------+\n";

        for (var entry : clients.entrySet()) {
            Integer id = entry.getKey();
            Client client = entry.getValue();
            res += String.format("| %-5s | %-20s | %-15s | %-15s | %-10s | %-20s | %-11s | %-10s | %-7s |\n", id,
                    client.getLastName(), client.getFirstName(), client.getPatronymic(), client.getBirthDate(), client.getBirthPlace(),
                    client.getPassportNumber(), client.getIssueDate(), client.getDepartmentCode());
        }

        res += "+-------+----------------------+-----------------+-----------------+------------+----------------------+-------------+------------+---------+\n";
        return res;
    }

    HashMap<Integer, Client> getClients() {
        return this.clients;
    }
}