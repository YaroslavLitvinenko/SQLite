package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Data.Data;
import sample.Data.Edition;
import sample.Data.Person;
import sample.Data.Subscription;

/**
 * Created by himmel on 05.05.15.
 */
public class ControllerSub {
    public Label idField;
    public ComboBox<Edition> editionField;
    public ComboBox<Person> personField;

    private Stage dialogStage;
    private Subscription subscription;
    private Data data;
    private boolean upd_new;

    public void handleOk(ActionEvent actionEvent) {
        subscription.setEdition(editionField.getValue());
        subscription.setSubscriber(personField.getValue());
        if (upd_new)
            data.updateDataSubscription(subscription);
        else data.newDataSubscription(subscription);
        dialogStage.close();
    }

    public void handleCancel(ActionEvent actionEvent) {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
        idField.setText(subscription.getId());
        if (subscription.getEdition().getId()!=0) {
            editionField.setValue(subscription.getEdition());
            personField.setValue(subscription.getSubscriber());
        }
    }

    public void setData(Data data) {
        this.data = data;
        editionField.setItems(data.getDataOfEdition());
        personField.setItems(data.getDataOfPerson());
    }

    public void setUpd_new(boolean upd_new) {
        this.upd_new = upd_new;
    }
}
