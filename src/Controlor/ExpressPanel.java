package Controlor;

import Controlor.DataStore;
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


public class ExpressPanel {

    @FXML
    private TableView<TExpressService> userTable;
    @FXML
    private TableColumn<TExpressService,String> userId;//"用户Id
    @FXML
    private TableColumn<TExpressService,String> userName;//员工Id
    @FXML
    private TableColumn<TExpressService,String> userPhone;//货物内容
    @FXML
    private TableColumn<TExpressService,String> userPwd;//运送状态
    @FXML
    private TableColumn<TExpressService,String> addSend;//发货地址
    @FXML
    private TableColumn<TExpressService,String> addArrive;//收货地址
    @FXML
    private TableColumn<TExpressService,String> hint;//附注
    @FXML
    private ChoiceBox<String> searchWay;
    @FXML
    private Button getOrder;
    @FXML
    private TextField info;
    @FXML
    private void search()
    {
        switch(searchWay.getValue())
        {
            case"全部":
                ResultSet rs=BasicDao.executeQuery("select * from express_service where worker_id='"+ DataStore.deliverId+"'");
                List<TExpressService> delivery= ExpressServiceDao.getSelected(rs);
                ObservableList<TExpressService> serviceObservableList= FXCollections.observableList(delivery);
                userId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser_id()));
                userName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_id()));
                addArrive.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getAim_address())));
                addSend.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStart_address()));
                userPhone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDelivery()));
                userPwd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDelivery_status()));
                hint.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getHint()));
                userTable.setItems(serviceObservableList);
                break;
            case"按用户id查找":
                String clause=info.getText().trim();
                if (clause==null||clause.isEmpty()||clause.equals("")||clause.length()==0)
                {
                    Utils.dialogCreator(3,"错误","位数如查询内容","请检查输入");
                    break;
                }
                ResultSet rs1=BasicDao.executeQuery("select * from express_service where user_id like'%"+clause+"%' and worker_id='"+DataStore.deliverId+"' ");
                List<TExpressService> delivery1= ExpressServiceDao.getSelected(rs1);
                ObservableList<TExpressService> serviceObservableList1= FXCollections.observableList(delivery1);
                userId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser_id()));
                userName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_id()));
                addArrive.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getAim_address())));
                addSend.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStart_address()));
                userPhone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDelivery()));
                userPwd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDelivery_status()));
                hint.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getHint()));
                userTable.setItems(serviceObservableList1);
                break;
            case "需要处理":
                ResultSet rs2=BasicDao.executeQuery("select * from express_service where delivery_status='Waiting' and worker_id='"+DataStore.deliverId+"' ");
                List<TExpressService> delivery2= ExpressServiceDao.getSelected(rs2);
                ObservableList<TExpressService> serviceObservableList2= FXCollections.observableList(delivery2);
                userId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser_id()));
                userName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_id()));
                addArrive.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getAim_address())));
                addSend.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStart_address()));
                userPhone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDelivery()));
                userPwd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDelivery_status()));
                hint.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getHint()));
                userTable.setItems(serviceObservableList2);
                break;
        }
    }
    @FXML
    private void typeShift()
    {
        if (searchWay.getValue().equals("需要处理"))
        {
            getOrder.setDisable(false);
        }
    }
    @FXML
    private int deal()
    {
        String clause=info.getText().trim();
        if (clause==null||clause.isEmpty()||clause.equals("")||clause.length()==0)
        {
            Utils.dialogCreator(3,"错误","位数如查询内容","请检查输入");
           return -1;
        }
        int x=BasicDao.executeUpdate("update express_service set delivery_status='Deliverying' where user_id='"+clause+"' and worker_id='"+DataStore.deliverId+"'");
        if (x==-1)
        {
            Utils.dialogCreator(3,"错误","错误的用户账户或者输入不完全","请检查输入");
            return -1;
        }
        Utils.dialogCreator(1,"成功","接单成功","已完成");
        //完成后刷新一遍
        ResultSet rs2=BasicDao.executeQuery("select * from express_service where delivery_status='Waiting' and worker_id='"+DataStore.deliverId+"' ");
        List<TExpressService> delivery2= ExpressServiceDao.getSelected(rs2);
        ObservableList<TExpressService> serviceObservableList2= FXCollections.observableList(delivery2);
        userId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUser_id()));
        userName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_id()));
        addArrive.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getAim_address())));
        addSend.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStart_address()));
        userPhone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDelivery()));
        userPwd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDelivery_status()));
        hint.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getHint()));
        userTable.setItems(serviceObservableList2);
        return 0;
    }
}
