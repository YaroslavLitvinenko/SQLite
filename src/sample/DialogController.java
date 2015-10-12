package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Data.Data;
import sample.Data.Person;

/**
 * Created by himmel on 03.05.15.
 */
public class DialogController {
    public Label idField;
    public TextField firstNameField;
    public TextField lastNameField;
    public TextField middleNameField;
    public TextField birthdayField;
    public TextField addsField;

    private Person person;
    private Data data;
    private Stage dialogStage;
    private boolean upd_new;

    public void initialize() {
    }

    public void handleOk(ActionEvent actionEvent) {
        person.setName(firstNameField.getText());
        person.setLastname(lastNameField.getText());
        person.setMiddlename(middleNameField.getText());
        person.setBirthday(birthdayField.getText());
        person.setAdds(addsField.getText());
        if (upd_new)
            data.updateDataPerson(person);
        else data.newDataPerson(person);
        dialogStage.close();
    }

    public void handleCancel(ActionEvent actionEvent) {
        dialogStage.close();
    }

    public void setPerson(Person person) {
        this.person = person;
        idField.setText(Integer.toString(person.getId()));
        firstNameField.setText(person.getName());
        lastNameField.setText(person.getLastname());
        middleNameField.setText(person.getMiddlename());
        birthdayField.setText(person.getBirthday());
        addsField.setText(person.getAdds());
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setUpd_new (boolean upd_new){
        this.upd_new =upd_new;
    }
}
