package Controlor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DeliverControlPane {
    @FXML
    private void goToTask()
    {

        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("../GUI/express_panel.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("快递管理界面");
            anotherStage.setScene(new Scene(anotherRoot, 600, 400));
            anotherStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
