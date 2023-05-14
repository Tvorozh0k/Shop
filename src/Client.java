import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Класс клиент
 */
public class Client {

    /**
     * ФИО клиента
     */
    private String firstName, lastName, patronymic;

    /**
     * Дата рождения клиента и дата
     * выдачи паспорта
     */
    private LocalDate birthDate, issueDate;

    /**
     * Место рождения клиента
     */
    private String birthPlace;

    /**
     * Серия + номер паспорта и код подразделения
     */
    private String passportNumber, departmentCode;

    /**
     * Конструктор
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
    public Client(String lastName, String firstName, String patronymic, String birthDate, String birthPlace, String passportNumber, String issueDate, String departmentCode) throws Exception {
        setLastName(lastName);
        setFirstName(firstName);
        setPatronymic(patronymic);
        setBirthDate(birthDate);
        setBirthPlace(birthPlace);
        setPassportNumber(passportNumber);
        setIssueDate(issueDate);
        setDepartmentCode(departmentCode);
    }

    /**
     * Устанавливаем имя клиента
     *
     * @param firstName имя клиента
     */
    public void setFirstName(String firstName) throws Exception {
        if (!firstName.matches("[А-я][а-я]+"))
            throw new Exception("Некорректное имя клиента");
        this.firstName = firstName;
    }

    /**
     * Получаем имя клиента
     *
     * @return имя клиента
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Устанавливаем фамилию клиента
     *
     * @param lastName фамилия клиента
     */
    public void setLastName(String lastName) throws Exception {
        if (!lastName.matches("[А-я][а-я]+"))
            throw new Exception("Некорректная фамилия клиента");
        this.lastName = lastName;
    }

    /**
     * Получаем фамилию клиента
     *
     * @return фамилия клиента
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Устанавливаем отчество клиента
     *
     * @param patronymic отчество клиента
     */
    public void setPatronymic(String patronymic) throws Exception {
        if (!patronymic.matches("(^$)|([А-я][а-я]+[ач])"))
            throw new Exception("Некорректное отчество клиента");
        this.patronymic = patronymic;
    }

    /**
     * Получаем отчество клиента
     *
     * @return отчество клиента
     */
    public String getPatronymic() {
        return this.patronymic;
    }

    /**
     * Получение ФИО клиента
     *
     * @return ФИО клиента
     */
    public String getFullName() {
        return String.join(" ", lastName, firstName, patronymic);
    }

    /**
     * Устанавливаем день рождения клиента
     *
     * @param birthDate день рождения клиента
     */
    public void setBirthDate(String birthDate) throws Exception {
        try {
            this.birthDate = LocalDate.parse(birthDate);
        } catch (DateTimeParseException e) {
            throw new Exception("Некорректная дата рождения");
        }
    }

    /**
     * Получаем день рождения клиента
     *
     * @return день рождения клиента
     */
    public String getBirthDate() {
        return this.birthDate.toString();
    }

    /**
     * Устанавливаем дату выдачи паспорта
     *
     * @param issueDate дата выдачи паспорта
     */
    public void setIssueDate(String issueDate) throws Exception {
        try {
            this.issueDate = LocalDate.parse(issueDate);
        } catch (DateTimeParseException e) {
            throw new Exception("Некорректная дата выдачи паспорта");
        }
    }

    /**
     * Получаем дату выдачи паспорта
     *
     * @return дата выдачи паспорта
     */
    public String getIssueDate() {
        return this.issueDate.toString();
    }

    /**
     * Устанавливаем место рождения клиента
     *
     * @param birthPlace место рождения клиента
     */
    public void setBirthPlace(String birthPlace) throws Exception {
        if (!birthPlace.matches("гор. [А-Я][а-я]+"))
            throw new Exception("Некорректное место рождения клиента");
        this.birthPlace = birthPlace;
    }

    /**
     * Получаем место рождения клиента
     *
     * @return место рождения клиента
     */
    public String getBirthPlace() {
        return this.birthPlace;
    }

    /**
     * Устанавливаем серию и номер паспорта
     *
     * @param passportNumber серия и номер паспорта
     */
    public void setPassportNumber(String passportNumber) throws Exception {
        if (!passportNumber.matches("\\d{4} \\d{6}"))
            throw new Exception("Некорректные серия и номер паспорта");
        this.passportNumber = passportNumber;
    }

    /**
     * Получаем серию и номер паспорта
     *
     * @return серия и номер паспорта
     */
    public String getPassportNumber() {
        return this.passportNumber;
    }

    /**
     * Устанавливаем код подразделения
     *
     * @param departmentCode код подразделения
     */
    public void setDepartmentCode(String departmentCode) throws Exception {
        if (!departmentCode.matches("[0-9]{3}-[0-9]{3}"))
            throw new Exception("Некорректный код подразделения");
        this.departmentCode = departmentCode;
    }

    /**
     * Получаем код подразделения
     *
     * @return код подразделения
     */
    public String getDepartmentCode() {
        return this.departmentCode;
    }

    /**
     * Получаем полную информацию о клиенте
     *
     * @return полная информация о клиенте
     */
    @Override
    public String toString() {
        String res = "";

        res += String.format("ФИО: %s\n", getFullName());
        res += String.format("Дата рождения: %s\n", this.birthDate);
        res += String.format("Место рождения: %s\n", this.birthPlace);
        res += String.format("Серия и номер паспорта: %s\n", this.passportNumber);
        res += String.format("Дата выдачи паспорта: %s\n", this.issueDate);
        res += String.format("Код подразделения: %s\n", this.departmentCode);

        return res;
    }
}