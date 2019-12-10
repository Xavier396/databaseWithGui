package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminControlPane {
    @FXML
    private Menu dManage;
    @FXML
    private void goTodManager()
    {
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("deliver_manager.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("快递员管理");
            anotherStage.setScene(new Scene(anotherRoot, 800, 300));
            anotherStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
