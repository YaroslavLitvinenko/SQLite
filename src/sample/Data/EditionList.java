package sample.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.prefs.Preferences;

class EditionList implements Runnable {
    private ObservableList<Edition> editionData = FXCollections.observableArrayList();
    private static int caunter = 0;
    private static Preferences pref =Preferences.userNodeForPackage(Edition.class);

    private ResultSet resSet;

    public Thread thread;

    public EditionList (ResultSet resSet){
        this.resSet = resSet;
        thread = new Thread(this);
        thread.start();
    }

    public void run()
    {
        caunter = pref.getInt("id_edition", 0);
        try {
            while(resSet.next()){
                editionData.add(new Edition (resSet.getInt("id"), resSet.getString("Name"), resSet.getString("Typ"), resSet.getString("Edition")));
                if (resSet.getInt("id")>caunter)
                    caunter = resSet.getInt("id");
            }
            pref.putInt("id_edition", caunter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add (Edition edition){
        caunter++;
        editionData.add(edition);
    }

    public void remove (Edition edition){
        editionData.remove(edition);
    }

    public int getCaunter (){
        return caunter+1;
    }

    public Edition getEdition (int id) throws NullPointerException {
        for (Edition edit : editionData)
        if (id == edit.getId())
            return edit;
        throw new NullPointerException("По такому индексу нет значения");
    }

    public ObservableList<Edition> getEditionData (){
        return editionData;
    }
}
