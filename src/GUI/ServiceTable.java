package GUI;

import Dao.BasicDao;
import Dao.ExpressServiceDao;
import Table.TExpressService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import utils.Utils;

import java.sql.ResultSet;
import java.util.List;

public class ServiceTable {
    @FXML
    private TableView<TExpressService> userTable;
    @FXML
    private TableColumn<TExpressService,String> userId; //用户Id" />
    @FXML
    private TableColumn<TExpressService,String> userName; //员工Id" />

    @FXML
    private TableColumn<TExpressService,String> userPhone; //货物内容" />
    @FXML
    private TableColumn<TExpressService,String> userPwd; //运送状态" />
    @FXML
    private TableColumn<TExpressService,String> addSend; //发货地址" />
    @FXML
    private TableColumn<TExpressService,String> addArrive; //收货地址" />
    @FXML
    private TableColumn<TExpressService,String > hint;

    @FXML
    private TextField info;
    @FXML
    private ChoiceBox<String> searchWay;

    @FXML
    private void getAllService() {
        switch (searchWay.getValue()) {
            case "全部":
                List<TExpressService> users = ExpressServiceDao.getAllService();
                ObservableList<TExpressService> userObservableList = FXCollections.observableList(users);
                userId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser_id()));
                userName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_id()));
                userPhone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDelivery()));
                userPwd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDelivery_status()));
                addSend.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getStart_address()));
                addArrive.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getAim_address()));
                hint.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getHint()));
                userTable.setItems(userObservableList);
                break;
            case "按用户id查找":
                String ls = info.getText();
                if (ls.isEmpty() || ls.equals("")) {
                    Utils.dialogCreator(3, "错误", "您没有输入相关信息", "请检查输入");
                    break;
                }
                ResultSet resultSet = BasicDao.executeQuery("select * from express_service where user_id like'%" + ls + "%'");
                List<TExpressService> selectUser = ExpressServiceDao.getSelected(resultSet);
                ObservableList<TExpressService> userList = FXCollections.observableList(selectUser);
                userId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser_id()));
                userName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_id()));

                userPhone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDelivery()));
                userPwd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDelivery_status()));
                addSend.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getStart_address()));
                addArrive.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getAim_address()));
                hint.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getHint()));
                userTable.setItems(userList);
                break;
            case "按快递员id查找":
                String s = info.getText();
                if (s.isEmpty() || s.equals("")) {
                    Utils.dialogCreator(3, "错误", "您没有输入相关信息", "请检查输入");
                    break;
                }
                ResultSet rs = BasicDao.executeQuery("select * from express_service where worker_id like'%" + s + "%'");
                List<TExpressService> su = ExpressServiceDao.getSelected(rs);
                ObservableList<TExpressService> ul = FXCollections.observableList(su);
                userId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser_id()));
                userName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_id()));

                userPhone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDelivery()));
                userPwd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDelivery_status()));
                addSend.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getStart_address()));
                addArrive.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getAim_address()));
                hint.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getHint()));
                userTable.setItems(ul);
                break;

        }
    }
}
