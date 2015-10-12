package sample.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.prefs.Preferences;

/**
 * Created by himmel on 02.05.15.
 */
public class SubscriptionList {
    private ObservableList<Subscription> subscriptionData = FXCollections.observableArrayList();
    private static int caunter = 0;
    private static Preferences pref =Preferences.userNodeForPackage(Subscription.class);

    public SubscriptionList(ObservableList<Subscription> subscription) {
        caunter = pref.getInt("id_sub", 0);
        this.subscriptionData = subscription;
        for (Subscription sub : subscriptionData)
            if (sub.getIntegerId()>caunter)
                caunter=sub.getIntegerId();
        pref.putInt("id_sub", caunter);
    }

    public int getCaunter (){
        return caunter+1;
    }

    public void add (Subscription subscription){
        caunter++;
        subscriptionData.add(subscription);
    }

    public void remove (Subscription subscription){
        subscriptionData.remove(subscription);
    }

    public ObservableList<Subscription> getSubscriptionData (){
        return subscriptionData;
    }
}
