package GUI;

import Dao.BasicDao;
import Dao.ExpressUserDao;
import Table.TExpressUser;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.Utils;

import java.sql.ResultSet;
import java.util.List;


public class UserTable {
    @FXML
    private Button query;
    @FXML
    private TextField info;
    @FXML
    private ChoiceBox <String> searchWay;
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
    private void getAllUser() {
        switch (searchWay.getValue()) {
            case "全部":
                List<TExpressUser> users = ExpressUserDao.getAllUser();
                ObservableList<TExpressUser> userObservableList = FXCollections.observableList(users);
                userId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser_account()));
                userName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser_name()));
                userGender.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getUser_gender())));
                userBirthday.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBirthday()));
                userPhone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser_phone()));
                userPwd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser_pwd()));
                userTable.setItems(userObservableList);
                break;
            case "按名字查找":
                String ls = info.getText();
                if (ls.isEmpty() || ls.equals("")) {
                    Utils.dialogCreator(3, "错误", "您没有输入相关信息", "请检查输入");
                    break;
                }
                ResultSet resultSet = BasicDao.executeQuery("select * from express_user where user_name like'%" + ls + "%'");
                List<TExpressUser> selectUser = ExpressUserDao.getSelected(resultSet);
                ObservableList<TExpressUser> userList = FXCollections.observableList(selectUser);
                userId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser_account()));
                userName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser_name()));
                userGender.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getUser_gender())));
                userBirthday.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBirthday()));
                userPhone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser_phone()));
                userPwd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser_pwd()));
                userTable.setItems(userList);
                break;
            case "按帐号查找":
                String s = info.getText();
                if (s.isEmpty() || s.equals("")) {
                    Utils.dialogCreator(3, "错误", "您没有输入相关信息", "请检查输入");
                    break;
                }
                ResultSet rs = BasicDao.executeQuery("select * from express_user where user_account like'%" + s + "%'");
                List<TExpressUser> su = ExpressUserDao.getSelected(rs);
                ObservableList<TExpressUser> ul = FXCollections.observableList(su);
                userId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser_account()));
                userName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser_name()));
                userGender.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getUser_gender())));
                userBirthday.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBirthday()));
                userPhone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser_phone()));
                userPwd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser_pwd()));
                userTable.setItems(ul);
                break;
        }
    }

    }



