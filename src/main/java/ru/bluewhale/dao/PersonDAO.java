package ru.bluewhale.dao;

import org.springframework.stereotype.Component;
import ru.bluewhale.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static int PEOPLE_INDEX;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_INDEX, "Tom"));
        people.add(new Person(++PEOPLE_INDEX, "Ben"));
        people.add(new Person(++PEOPLE_INDEX, "Katy"));
        people.add(new Person(++PEOPLE_INDEX, "Mike"));
    }


    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people
                .stream()
                .filter(p -> id == p.getId())
                .findAny()
                .orElse(null);
    }


    public void save(Person person) {
        person.setId(++PEOPLE_INDEX);
        people.add(person);
    }

    public void update(int id, Person person) {
        Person p = show(id);
        p.setName(person.getName());
        p.setSurname(person.getSurname());
        p.setEmail(person.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> id == p.getId());
    }
}
