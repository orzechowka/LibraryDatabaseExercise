package model;

/**
 * Created by Anna on 2018-01-20.
 */
public class Reader {

    private int id;
    private String name;
    private String surname;
    private String pesel;

    public Reader(int id, String name, String surname, String pesel) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Override
    public String toString() {
        return id + ", " + name + " " + surname + ", " + pesel;
    }
}
