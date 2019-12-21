package Controlor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserControlPane {
    @FXML
    private void send()
    {
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("../GUI/send_my_express.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("送快递");
            anotherStage.setScene(new Scene(anotherRoot, 720, 550));
            anotherStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void check()
    {
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("../GUI/check_my_express.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("查快递");
            anotherStage.setScene(new Scene(anotherRoot, 720, 550));
            anotherStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void profile()
    {
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("../GUI/user_password_change.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("用户信息修改");
            anotherStage.setScene(new Scene(anotherRoot, 720, 550));
            anotherStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
