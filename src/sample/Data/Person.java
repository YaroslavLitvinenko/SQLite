package sample.Data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by himmel on 29.04.15.
 */

public class Person {
    private final IntegerProperty id;
    private final StringProperty Name;
    private final StringProperty Lastname;
    private final StringProperty Middlename;
    private final StringProperty Birthday;
    private final StringProperty Adds;

    public Person(int id, String name, String patronamyk, String middlename, String birthday, String adds) {
        this.id = new SimpleIntegerProperty(id);
        Name = new SimpleStringProperty(name);
        Lastname = new SimpleStringProperty(patronamyk);
        Middlename = new SimpleStringProperty(middlename);
        Birthday = new SimpleStringProperty(birthday);
        Adds = new SimpleStringProperty(adds);
    }

    public Person (){
        this.id = new SimpleIntegerProperty(0);
        Name = new SimpleStringProperty("");
        Lastname = new SimpleStringProperty("");
        Middlename = new SimpleStringProperty("");
        Birthday = new SimpleStringProperty("");
        Adds = new SimpleStringProperty("");
    }

    public Person(int id){
        this.id = new SimpleIntegerProperty(id);
        Name = new SimpleStringProperty("");
        Lastname = new SimpleStringProperty("");
        Middlename = new SimpleStringProperty("");
        Birthday = new SimpleStringProperty("");
        Adds = new SimpleStringProperty("");
    }

    public String getAdds() {
        return Adds.get();
    }

    public StringProperty addsProperty() {
        return Adds;
    }

    public String getBirthday() {
        return Birthday.get();
    }

    public StringProperty birthdayProperty() {
        return Birthday;
    }

    public String getMiddlename() {
        return Middlename.get();
    }

    public StringProperty middlenameProperty() {
        return Middlename;
    }

    public String getLastname() {
        return Lastname.get();
    }

    public StringProperty lastnameProperty() {
        return Lastname;
    }

    public String getName() {
        return Name.get();
    }

    public StringProperty nameProperty() {
        return Name;
    }

    public int getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return new SimpleStringProperty(Integer.toString(id.get()));
    }

    public void setBirthday(String birthday) {
        this.Birthday.set(birthday);
    }

    public void setAdds(String adds) {
        this.Adds.set(adds);
    }

    public void setMiddlename(String middlename) {
        this.Middlename.set(middlename);
    }

    public void setLastname(String lastname) {
        this.Lastname.set(lastname);
    }

    public void setName(String name) {
        this.Name.set(name);
    }

    @Override
    public String toString() {
        String name = Lastname.get() +  " ";
        name+=Name.get().substring(0, 1);
        name+=". ";
        name+=Lastname.get().substring(0, 1);
        name+=".";//Litvinenko Y. V.
        return name;
    }
}
