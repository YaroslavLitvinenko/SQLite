package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Data.*;



import java.sql.SQLException;

public class Controller {

    public TableView <Person> Table_1;
    public TableColumn <Person, String> Column_11;
    public TableColumn <Person, String> Column_12;
    public TableColumn <Person, String> Column_13;
    public TableColumn <Person, String> Column_14;
    public TableColumn <Person, String> Column_15;
    public TableColumn <Person, String> Column_16;

    public TableView<Edition> Table_2;
    public TableColumn <Edition, String> Column_21;
    public TableColumn <Edition, String> Column_22;
    public TableColumn <Edition, String> Column_23;
    public TableColumn <Edition, String> Column_24;

    public TableView<Subscription> Table_3;
    public TableColumn<Subscription, String> Column_31;
    public TableColumn<Subscription, String> Column_32;
    public TableColumn<Subscription, String> Column_33;
    public Label Name;
    public Label Lastname;
    public Label Middlename;

    Person NayPerson;
    Edition NayEdition;
    Subscription NaySubscription;
    Data d = new Data();
    Alert alert = new Alert(Alert.AlertType.ERROR);

    public static Main main1;

    @FXML
    public void initialize() {
        Table_1.setItems(d.getDataOfPerson());
        Column_11.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        Column_12.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        Column_13.setCellValueFactory(cellData -> cellData.getValue().lastnameProperty());
        Column_14.setCellValueFactory(cellData -> cellData.getValue().middlenameProperty());
        Column_15.setCellValueFactory(cellData -> cellData.getValue().birthdayProperty());
        Column_16.setCellValueFactory(cellData -> cellData.getValue().addsProperty());
        Table_2.setItems(d.getDataOfEdition());
        Column_21.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        Column_22.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        Column_23.setCellValueFactory(cellData -> cellData.getValue().typProperty());
        Column_24.setCellValueFactory(cellData -> cellData.getValue().editionProperty());
        Table_3.setItems(d.getDataOfSubscription());
        Column_31.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        Column_32.setCellValueFactory(cellData -> cellData.getValue().editionProperty());
        Column_33.setCellValueFactory(cellData -> cellData.getValue().subscriberProperty());
        Table_1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> ShowData(newValue));
        Table_2.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> ShowData(newValue));
        Table_3.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> ShowData(newValue));
        alert.setTitle("Ошибка!");
        alert.setHeaderText("Выберите данные!");
        alert.setContentText("Не выбрано никаких данных для изменений!");
    }

    private void ShowData (Person person){
        NayPerson = person;
    }

    private void ShowData (Edition edition){
        NayEdition=edition;
    }

    private void ShowData (Subscription sub){
        NaySubscription = sub;
        Name.setText(sub.getSubscriber().getName());
        Lastname.setText(sub.getSubscriber().getLastname());
        Middlename.setText(sub.getSubscriber().getMiddlename());
    }

    public void NewPer(ActionEvent actionEvent) {
        main1.showPersonEditDialog(new Person(d.getNextPerson()), d, false);
    }

    public void RedPer(ActionEvent actionEvent) {
        if (NayPerson!=null){
            main1.showPersonEditDialog(NayPerson, d, true);
        } else {
            alert.showAndWait();
        }
    }

    public void DelPer(ActionEvent actionEvent) {
        if (NayPerson != null)
            d.deleteDataPerson(NayPerson);
        else alert.showAndWait();

    }

    public void NewEd(ActionEvent actionEvent) {
        main1.showEditionEditDialog(new Edition(d.getNextEdition()), d, false);
    }

    public void DelEd(ActionEvent actionEvent) {
        if (NayEdition != null)
            d.deleteDataEdition(NayEdition);
        else alert.showAndWait();
    }

    public void RedEd(ActionEvent actionEvent) {
        if (NayEdition!=null){
            main1.showEditionEditDialog(NayEdition, d, true);
        } else {
            alert.showAndWait();
        }
    }

    public void NewSub(ActionEvent actionEvent) {
        main1.showSubscriptionEditDialog(new Subscription(d.getNextSubscription()), d, false);
    }

    public void DelSub(ActionEvent actionEvent) {
        if (NaySubscription != null)
            d.deleteDataSubscription(NaySubscription);
        else alert.showAndWait();
    }

    public void RedSub(ActionEvent actionEvent) {
        if(NaySubscription!=null)
            main1.showSubscriptionEditDialog(NaySubscription, d, true);
        else
            alert.showAndWait();
    }
}
