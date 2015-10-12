package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Data.Data;
import sample.Data.Edition;
import sample.Data.Person;
import sample.Data.Subscription;

import java.io.IOException;

public class Main extends Application {
    Stage st;

    @Override
    public void start(Stage primaryStage) throws Exception{
        st=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Database v3.1.2");
        primaryStage.setScene(new Scene(root, 650, 300));
        Controller.main1 = this;
        primaryStage.show();
    }

    public void showPersonEditDialog(Person person, Data d, boolean new_read) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("Dialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(st);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            DialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);
            controller.setData(d);
            controller.setUpd_new(new_read);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEditionEditDialog(Edition edition, Data d, boolean new_read) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("DialogEdit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Edition");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(st);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ControllerEdit controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setEdition(edition);
            controller.setData(d);
            controller.setUpd_new(new_read);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSubscriptionEditDialog(Subscription subscription, Data d, boolean new_read) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("DialogSub.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Subscription");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(st);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ControllerSub controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setData(d);
            controller.setUpd_new(new_read);
            controller.setSubscription(subscription);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
