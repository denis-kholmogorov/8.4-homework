import java.io.IOException;
import java.util.HashMap;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws EmailIncorrectInput, IncorrectPhoneNumber {
        String[] components = data.split("\\s+");
        if(components.length != 4){
            throw new ArrayIndexOutOfBoundsException("Data entered incorrectly!\nExample: add Василий Петров " +
                                                     "vasily.petrov@gmail.com +79215637722 ");
        }
        if(!components[2].matches("^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$")){
            throw new EmailIncorrectInput("Email entered a wrong!\nRepeat command \"add\"  E-mail example: vasily.petrov@gmail.com");
        }
        if(!components[3].matches("\\+[0-9]{11}")){
            throw new IncorrectPhoneNumber("Incorrect phone number!\nRepeat command \"add\" the number consists of \"+\" and 11 symbols Example: +79993331122 ");
        }
        String name = components[0] + " " + components[1];
        storage.put(name, new Customer(name, components[3], components[2]));
    }

    public void listCustomers()
    {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) throws Exception {
        if(!name.matches("^[a-zA-Zа-яА-Я]+ [a-zA-Zа-яА-Я]+$")){
            throw new IOException("Input name is a incorrect for remove\nRepeat command \"remove\" Example: remove Василий Петров");
        }else if(!storage.containsKey(name)){
            throw new NameNotFound("Name don't found in list! Repeat command with a correct name");
        }
        storage.remove(name);
    }

    public int getCount()
    {
        return storage.size();
    }
}

