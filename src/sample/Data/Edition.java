package sample.Data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Edition {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty typ;
    private final StringProperty edition;

    public Edition(int id, String name, String typ, String edition) {
        this.id = new SimpleIntegerProperty(id);
        this.typ = new SimpleStringProperty(typ);
        this.name = new SimpleStringProperty(name);
        this.edition = new SimpleStringProperty(edition);
    }

    public Edition (int id){
        this.id = new SimpleIntegerProperty(id);
        this.typ = new SimpleStringProperty("");
        this.name = new SimpleStringProperty("");
        this.edition = new SimpleStringProperty("");
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setTyp (String typ){
        this.typ.set(typ);
    }

    public void setEdition(String edition) {
        this.edition.set(edition);
    }

    public int getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return new SimpleStringProperty(Integer.toString(id.get()));
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getTyp() {
        return typ.get();
    }

    public StringProperty typProperty (){
        return typ;
    }

    public String getEdition() {
        return edition.get();
    }

    public StringProperty editionProperty() {
        return edition;
    }

    public String toString(){
        return name.get();
    }
}
