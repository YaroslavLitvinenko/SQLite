package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Data.Data;
import sample.Data.Edition;
import sample.Data.Typ;

/**
 * Created by himmel on 04.05.15.
 */
public class ControllerEdit {
    public Label idField;
    public TextField NameField;
    public TextField editionField;
    public ComboBox<String> typField;

    private Stage dialogStage;
    private Edition edition;
    private Data data;
    private boolean upd_new;
    private ObservableList<String> list = FXCollections.observableArrayList();

    public void initialize() {
        list.add("Книга");
        list.add("Брощюра");
        list.add("Билютень");
        list.add("Каталог");
        list.add("Журнал");
        list.add("Газета");
        typField.setItems(list);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
        idField.setText(Integer.toString(edition.getId()));
        NameField.setText(edition.getName());
        typField.setValue(edition.getTyp());
        editionField.setText(edition.getEdition());
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setUpd_new(boolean upd_new) {
        this.upd_new = upd_new;
    }

    public void handleOk(ActionEvent actionEvent) {
        edition.setName(NameField.getText());
        edition.setTyp(typField.getValue());
        edition.setEdition(editionField.getText());
        if(upd_new){
            data.updateDataEdition(edition);
        } else data.newDataEdition(edition);
        dialogStage.close();
    }

    public void handleCancel(ActionEvent actionEvent) {
        dialogStage.close();
    }
}
