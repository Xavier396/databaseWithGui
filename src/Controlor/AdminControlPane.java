package Controlor;

import Dao.ExpressWorkerDao;
import Table.TExpressService;
import Table.TExpressWorker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AdminControlPane {
    @FXML
    private Menu dManage;//快递员管理
    @FXML
    private Menu iManage;//快递管理功能
    @FXML
    private Menu uManage;//密码更改
    @FXML
    private Menu show_status;//运送状态
    @FXML
    private Menu show_worker;//员工表
    @FXML
    private Menu show_user;//用户表
    @FXML
    private void goTodManager()
    {
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("deliver_manager.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("快递员管理");
            anotherStage.setScene(new Scene(anotherRoot, 800, 600));
            anotherStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void goToiManager()//快递状态管理
    {
        //TODO:这里要实现对快递接货的代码
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("../GUI/express_manager.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("快递信息管理");
            anotherStage.setScene(new Scene(anotherRoot, 800, 600));
            anotherStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void goTouManager()//个人信息管理
    {
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("../GUI/admin_password_change.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("管理员信息更改");
            anotherStage.setScene(new Scene(anotherRoot, 800, 600));
            anotherStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void getAllStatus()
    {
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("../GUI/service_table.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("所有快递状态");
            anotherStage.setScene(new Scene(anotherRoot, 1024, 768));
            anotherStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    @FXML
    private void getAllWorker()
    {
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("../GUI/worker_table.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("所有员工信息");
            anotherStage.setScene(new Scene(anotherRoot, 640, 480));
            anotherStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void getAllUser()
    {
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("../GUI/user_table.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("所有用户信息");
            anotherStage.setScene(new Scene(anotherRoot, 640, 480));
            anotherStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
