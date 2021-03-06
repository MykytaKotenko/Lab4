package pl.lublin.wsei.java.cwiczenia;


import javafx.application.HostServices;
import javafx.scene.control.Button;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Controller {

    private Infografika selInfografika;
    public TextField txtAdresStrony;
    public Button btnPrzejdzDoStrony;
    public Label lbFile;
    public ImageView imgMiniaturka;
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter xmlFilter = new FileChooser.ExtensionFilter("Plik XML (*.xml)", "*.xml");

    public ListView lstInfografiki;
    ObservableList<String> tytuly = FXCollections.observableArrayList();
    GusInfoGraphicList iglist;

    @FXML
    public void initialize() {
        fileChooser.getExtensionFilters().add(xmlFilter);
        lstInfografiki.getSelectionModel().selectedIndexProperty().addListener(
                new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number old_val, Number new_val) {
                        int index = new_val.intValue();
                        if (index != -1) {
                            txtAdresStrony.setText(iglist.infografiki.get(index).adresStrony);
                            Image image = new Image(iglist.infografiki.get(index).adresMiniaturki);
                            imgMiniaturka.setImage(image);
                        }
                        else {
                            txtAdresStrony.setText("");
                            imgMiniaturka.setImage(null);
                        }
                    }
                }
        );
    }

    public void btnOpenFileAction(ActionEvent actionEvent) {
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            iglist = new GusInfoGraphicList(file.getAbsolutePath());
            lbFile.setText(file.getAbsolutePath());
            for (Infografika ig: iglist.infografiki) tytuly.add(ig.tytul);
            lstInfografiki.setItems(tytuly);
        }
        else {
            lbFile.setText("Prosz?? wczytac plik ...");
        }
    }

    private HostServices hostServices;
    private Stage stage;
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
    public void setHostServices(HostServices hostServices)
    {
        this.hostServices = hostServices;
    }

    public void btnZaladujStrone(ActionEvent actionEvent) {
        if(selInfografika != null)
            hostServices.showDocument(selInfografika.adresStrony);
    }
}
