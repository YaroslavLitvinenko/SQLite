package sample.Data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;


    public class Data {
    private Connection conn;
    private Statement statmt;
    private ResultSet resSet;

    private PersonList listPerson;
    private EditionList listEdition;
    private SubscriptionList listSubscription;

    Alert alert = new Alert(Alert.AlertType.ERROR);

    public Data ()  {
        Connect();
        alert.setTitle("Ошибка!");
        alert.setHeaderText("Данные связаны!");
        alert.setContentText("На удаляемые данные существует ссылкка в\nтаблице 'Subscription'.");
        try {
            CreateEdition();
            resSet = statmt.executeQuery("SELECT * FROM Edition");
            listEdition = new EditionList(resSet);
            CreateClient();
            resSet = statmt.executeQuery("SELECT * FROM Client");
            listPerson = new PersonList(resSet);
            CreateSubscription();
            resSet = statmt.executeQuery("SELECT * FROM Subscription");
            ObservableList<Subscription> subscription = FXCollections.observableArrayList();
            listEdition.thread.join();
            while(resSet.next()){
                subscription.add(new Subscription(resSet.getInt("id"), listEdition.getEdition(resSet.getInt("Magazine")), listPerson.getPerson(resSet.getInt("Subscriber"))));
            }
            listSubscription = new SubscriptionList(subscription);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.exit(0);
        }
    }

    public Data (Data data){
        conn = data.conn;
        statmt = data.statmt;
        resSet = data.resSet;
        listPerson = data.listPerson;
        listEdition = data.listEdition;
        listSubscription = data.listSubscription;
    }

    public int getNextPerson (){
        return listPerson.getCaunter();
    }

    public int getNextEdition (){
        return listEdition.getCaunter();
    }

    public int getNextSubscription (){
        return listSubscription.getCaunter();
    }

    public ObservableList<Person> getDataOfPerson (){
        return listPerson.getPersonData();
    }

    public ObservableList<Edition> getDataOfEdition (){
        return listEdition.getEditionData();
    }

    public ObservableList<Subscription> getDataOfSubscription (){
        return listSubscription.getSubscriptionData();
    }

    public void newDataPerson (Person person){
        String sql = "INSERT INTO Client (Name, Lastname, Middelname, Birthday, Adds) VALUES ('";
        sql+=person.getName()+"', '";
        sql+=person.getLastname()+"', '";
        sql+=person.getMiddlename()+"', '";
        sql+=person.getBirthday()+"', '";
        sql+=person.getAdds()+"');";
        try {
            statmt.execute(sql);
            listPerson.add(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDataPerson (Person person){
        String sql = "UPDATE Client SET Name='" + person.getName();
        sql+="', Lastname='";
        sql+=person.getLastname();
        sql+="', Middelname='";
        sql+=person.getMiddlename();
        sql+="', Birthday='";
        sql+=person.getBirthday();
        sql+="', Adds='";
        sql+=person.getAdds();
        sql+="' WHERE id=" + person.getId() + ";";
        try {
            statmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDataPerson (Person person){
        try{
            resSet = statmt.executeQuery("SELECT * FROM Subscription WHERE Subscriber="+person.getId()+";");
            if (resSet.next()){
                alert.showAndWait();
            } else {
                String sql = "DELETE FROM Client WHERE id=" + person.getId() + ";";
                statmt.execute(sql);
                listPerson.remove(person);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void newDataEdition (Edition edition){
        String sql = "INSERT INTO Edition (Name, Typ, Edition) VALUES ('";
        sql+=edition.getName()+"', '";
        sql+=edition.getTyp()+"', '";
        sql+=edition.getEdition()+"');";

        try {
            statmt.execute(sql);
            listEdition.add(edition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDataEdition(Edition edition) {
        String sql = "UPDATE Edition SET Name='" + edition.getName();
        sql+="', Typ='";
        sql+=edition.getTyp();
        sql+="', Edition='";
        sql+=edition.getEdition();
        sql+="' WHERE id=" + edition.getId() + ";";
        try {
            statmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDataEdition (Edition edition){
        try{
            resSet = statmt.executeQuery("SELECT * FROM Subscription WHERE Magazine="+edition.getId()+";");
            if (resSet.next()){
                alert.showAndWait();
            } else {
                String sql = "DELETE FROM Edition WHERE id=" + edition.getId() + ";";
                statmt.execute(sql);
                listEdition.remove(edition);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void newDataSubscription (Subscription subscription){
        String sql = "INSERT INTO Subscription (Magazine, Subscriber) VALUES ('";
        sql+=subscription.getSubscriber().getId()+"', '";
        sql+=subscription.getSubscriber().getId()+"');";
        try {
            statmt.execute(sql);
            listSubscription.add(subscription);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDataSubscription(Subscription subscription) {
        String sql = "UPDATE Subscription SET Magazine='" + subscription.getEdition().getId();
        sql+="', Subscriber='";
        sql+=subscription.getSubscriber().getId();
        sql+="' WHERE id=" + subscription.getId() + ";";
        try {
            statmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDataSubscription (Subscription subscription){
        String sql = "DELETE FROM Subscription WHERE id=" + subscription.getId() + ";";
        try {
            statmt.execute(sql);
            listSubscription.remove(subscription);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void Connect (){
        conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:TEST1.s3db");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void CreateClient (){
        try {
            statmt = conn.createStatement();
            statmt.execute("CREATE TABLE if not exists 'Client'('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'Name' STRING, 'Lastname' STRING, 'Middelname' STRING, 'Birthday' DATE, 'Adds' STRING);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void CreateEdition (){
        try {
            statmt = conn.createStatement();
            statmt.execute("CREATE TABLE if not exists 'Edition'('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'Name' STRING, 'Typ' STRING, 'Edition' STRING);");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void CreateSubscription (){
        try {
            statmt = conn.createStatement();
            //statmt.execute("CREATE TABLE 'Subscription' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'Magazine' INTEGER REFERENCES 'Edition' ('id'), 'Subscriber' INTEGER REFERENCES 'Client' ('id'));");
            statmt.execute("CREATE TABLE if not exists 'Subscription' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'Magazine' INTEGER, 'Subscriber' INTEGER, FOREIGN KEY (Magazine) REFERENCES Edition (id), FOREIGN KEY (Subscriber) REFERENCES Client (id));");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
