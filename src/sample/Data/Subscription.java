package sample.Data;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by himmel on 02.05.15.
 */
public class Subscription {
    private final IntegerProperty id;
    private Person subscriber;
    private Edition edition;
    private final StringProperty susubscriber_pr;
    private final StringProperty edition_pr;

    public Subscription(int id, Edition edition, Person subscriber) {
        this.subscriber = subscriber;
        this.id = new SimpleIntegerProperty(id);
        this.edition = edition;
        susubscriber_pr = new SimpleStringProperty(subscriber.getLastname() + " " + subscriber.getName().substring(0, 1) + ". " + subscriber.getMiddlename().substring(0, 1) + ".");
        edition_pr = new SimpleStringProperty(edition.getName());
    }

    public Subscription (int id){
        this.id = new SimpleIntegerProperty(id);
        subscriber = new Person(0);
        edition = new Edition(0);
        susubscriber_pr = new SimpleStringProperty("");
        edition_pr = new SimpleStringProperty("");
    }

    public String getId() {
        return Integer.toString(id.get());
    }

    public int getIntegerId(){
        return id.get();
    }

    public StringProperty idProperty() {
        return new SimpleStringProperty(Integer.toString(id.get()));
    }

    public StringProperty subscriberProperty() {
        return susubscriber_pr;
    }

    public StringProperty editionProperty () {
        return edition_pr;
    }

    public void setSubscriber(Person subscriber) {
        this.subscriber = subscriber;
        susubscriber_pr.set(subscriber.getLastname() + " " + subscriber.getName().substring(0, 1) + ". " + subscriber.getMiddlename().substring(0, 1) + ".");
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
        edition_pr.set(edition.getName());
    }

    public Person getSubscriber (){
        return subscriber;
    }

    public Edition getEdition (){
        return edition;
    }
}
