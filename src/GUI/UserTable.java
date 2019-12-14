package GUI;

import Dao.ExpressUserDao;
import Table.TExpressUser;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;


public class UserTable {
    @FXML
    private Button query;
    @FXML
    private TableView<TExpressUser> userTable;
    @FXML
    private TableColumn<TExpressUser,String>userId;
    @FXML
    private TableColumn<TExpressUser,String>userName;
    @FXML
    private TableColumn<TExpressUser,String>userGender;
    @FXML
    private TableColumn<TExpressUser,String>userBirthday;
    @FXML
    private TableColumn<TExpressUser,String>userPhone;
    @FXML
    private TableColumn<TExpressUser,String>userPwd;

    @FXML
    private void getAllUser()
    {
        List<TExpressUser> users= ExpressUserDao.getAllUser();
        ObservableList<TExpressUser> userObservableList= FXCollections.observableList(users);
        userId.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getUser_account()));
        userName.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getUser_name()));
        userGender.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getUser_gender())));
        userBirthday.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getBirthday()));
        userPhone.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getUser_phone()));
        userPwd.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getUser_pwd()));
        userTable.setItems(userObservableList);
    }

}

