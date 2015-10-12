package sample.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.prefs.Preferences;

/**
 * Created by himmel on 29.04.15.
 */
class PersonList {
    private ObservableList<Person> personData = FXCollections.observableArrayList();
    private static int caunter = 0;
    private static Preferences pref =Preferences.userNodeForPackage(PersonList.class);

    public PersonList (ResultSet resSet){
        caunter = pref.getInt("id_person", 0);
        try {
            while(resSet.next()){
                personData.add(new Person(resSet.getInt("id"), resSet.getString("Name"), resSet.getString("Lastname"), resSet.getString("Middelname"), resSet.getString("Birthday"), resSet.getString("Adds")));
                if (resSet.getInt("id")>caunter)
                    caunter = resSet.getInt("id");
            }
            pref.putInt("id_person", caunter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add (Person person){
        caunter++;
        personData.add(person);
    }

    public void remove (Person person){
        personData.remove(person);
    }

    public int getCaunter (){
        return caunter+1;
    }

    public Person getPerson (int id) throws NullPointerException {
        for (Person per : personData)
            if (id == per.getId())
                return per;
        throw new NullPointerException("По такому индексу нет значения");
    }

    public ObservableList<Person> getPersonData() {
        return personData;
    }
}
