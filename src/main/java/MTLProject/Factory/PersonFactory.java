package MTLProject.Factory;

public class PersonFactory {
    public static Person newPerson(String name, int age) {
        Person person = new Person(name, age);
        return person;
    }
}
