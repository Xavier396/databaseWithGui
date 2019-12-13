package GUI;

import Dao.ExpressWorkerDao;
import Table.TExpressWorker;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;


public class WorkerTable {
    @FXML
    private Button query;
    @FXML
    private TableView<TExpressWorker>workerTable;
    @FXML
    private TableColumn<TExpressWorker,String> workerId;
    @FXML
    private TableColumn<TExpressWorker,String> workerName;
    @FXML
    private TableColumn<TExpressWorker,String>workerPwd;
    @FXML
    private TableColumn<TExpressWorker,String>workerPhone;
    @FXML
    private TableColumn<TExpressWorker,String>workerWorkStart;
    @FXML
    private TableColumn<TExpressWorker,String>workerWorkStop;
    @FXML
    private void setWorkerTable()
    {
        List<TExpressWorker> workers= ExpressWorkerDao.getAllWorkers();
        ObservableList<TExpressWorker> expressWorkerObservableList = FXCollections.observableList(workers);
        workerId.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getWorker_id()));
        workerName.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getWorker_name()));
        workerPwd.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getWorker_pwd()));
        workerPhone.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getWorker_phone()));
        workerWorkStart.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getWorker_work_time()));
        workerWorkStop.setCellValueFactory(data->new SimpleStringProperty(data.getValue().getWorker_finish_time()));
        workerTable.setItems(expressWorkerObservableList);
    }

}
