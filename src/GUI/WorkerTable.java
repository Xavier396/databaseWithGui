package GUI;

import Dao.BasicDao;
import Dao.ExpressWorkerDao;
import Table.TExpressWorker;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.Utils;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;


public class WorkerTable {
    @FXML
    private Button query;
    @FXML
    private TextField input;
    @FXML
    private TableView<TExpressWorker> workerTable;
    @FXML
    private TableColumn<TExpressWorker, String> workerId;
    @FXML
    private TableColumn<TExpressWorker, String> workerName;
    @FXML
    private TableColumn<TExpressWorker, String> workerPwd;
    @FXML
    private TableColumn<TExpressWorker, String> workerPhone;
    @FXML
    private TableColumn<TExpressWorker, String> workerWorkStart;
    @FXML
    private TableColumn<TExpressWorker, String> workerWorkStop;
    @FXML
    private ChoiceBox<String> searchWay;

    @FXML
    private void setWorkerTable() {
        switch (searchWay.getValue()) {
            case "全部":
                List<TExpressWorker> workers = ExpressWorkerDao.getAllWorkers();
                ObservableList<TExpressWorker> expressWorkerObservableList = FXCollections.observableList(workers);
                workerId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_id()));
                workerName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_name()));
                workerPwd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_pwd()));
                workerPhone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_phone()));
                workerWorkStart.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_work_time()));
                workerWorkStop.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_finish_time()));
                workerTable.setItems(expressWorkerObservableList);
                break;
            case "按名字查找":
                String name = input.getText();
                if (name.equals("") || name.isEmpty()) {
                    Utils.dialogCreator(3, "错误", "您没有输入相关信息", "请检查输入");
                    break;
                }
                ResultSet rs = BasicDao.executeQuery("select * from express_workers where worker_name like '%" + name + "%'");
                List<TExpressWorker> select = ExpressWorkerDao.getSelected(rs);
                ObservableList<TExpressWorker> selectedWorkerObservableList = FXCollections.observableList(select);
                workerId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_id()));
                workerName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_name()));
                workerPwd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_pwd()));
                workerPhone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_phone()));
                workerWorkStart.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_work_time()));
                workerWorkStop.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_finish_time()));
                workerTable.setItems(selectedWorkerObservableList);

                break;
            case "按工号查找":
                String code = input.getText();
                if (code.equals("") || code.isEmpty()) {
                    Utils.dialogCreator(3, "错误", "您没有输入相关信息", "请检查输入");
                    break;
                }
                ResultSet resultSet = BasicDao.executeQuery("select * from express_workers where worker_id like '%" + code + "%'");
                List<TExpressWorker> selected = ExpressWorkerDao.getSelected(resultSet);
                ObservableList<TExpressWorker> workerObservableList = FXCollections.observableList(selected);
                workerId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_id()));
                workerName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_name()));
                workerPwd.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_pwd()));
                workerPhone.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_phone()));
                workerWorkStart.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_work_time()));
                workerWorkStop.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getWorker_finish_time()));
                workerTable.setItems(workerObservableList);
                break;
        }
    }

}
