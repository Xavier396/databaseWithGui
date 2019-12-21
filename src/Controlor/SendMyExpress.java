package Controlor;

import Controlor.DataStore;
import Dao.BasicDao;
import Dao.ExpressWorkerDao;
import Table.TExpressWorker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import utils.Utils;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class SendMyExpress {
    @FXML
    private ChoiceBox<String> book;
    @FXML
    private TextField itemForSend;
    @FXML
    private ChoiceBox<String> posterman;
    @FXML
    private DatePicker deliverDate;//f送货日期
    @FXML
    private TextField deliverTime;//送货时间
    @FXML
    private TextArea startAddress;
    @FXML
    private TextArea endAddress;
    @FXML
    private TextField notes;

    @FXML
    private void modeSwitch() {
        switch (book.getValue()) {
            case "直送":
                deliverDate.setDisable(true);
                deliverTime.setDisable(true);
                break;
            case "预约":
                deliverTime.setDisable(false);
                deliverDate.setDisable(false);
                break;
        }
    }


    @FXML
    private int send() {
        if (book.getValue().equals("直送")) {
            try {
                String matter = itemForSend.getText().trim();
                String date = LocalDateTime.now().toString().trim();
                String poster = posterman.getValue().trim();
                String startAdd = startAddress.getText().trim();
                String endAdd = endAddress.getText().trim();
                String hint = notes.getText().trim();
                if (matter.isEmpty() || matter.equals("") || matter.length() == 0) {
                    Utils.dialogCreator(3, "错误", "未输入物品名", "请检查输入");
                    return -1;
                }

                if (poster.isEmpty() || poster.equals("") || poster.length() == 0) {
                    Utils.dialogCreator(3, "错误", "未选择快递员", "请检查输入");
                    return -1;
                }
                if (startAdd.isEmpty() || startAdd.equals("") || startAdd.length() == 0) {
                    Utils.dialogCreator(3, "错误", "未输入起始地址", "请检查输入");
                    return -1;
                }
                if (endAdd.isEmpty() || endAdd.equals("") || endAdd.length() == 0) {
                    Utils.dialogCreator(3, "错误", "未输入结束地址", "请检查输入");
                    return -1;
                }
                BasicDao.executeUpdate("insert into express_service(user_id,worker_id,delivery,delivery_status,delivery_date,start_address,aim_address,hint)values('" + DataStore.userId + "','" + poster + "','" + matter + "','Waiting','" + date + "','" + startAdd + "','" + endAdd + "','" + hint + "')");
                Utils.dialogCreator(1, "成功", "已收到您的订单，快递员会上门处理", "请耐心等待");
                return 0;
            }
            catch (NullPointerException npe)
            {
                Utils.dialogCreator(3, "错误", "存在未填写的内容", "请检查输入");
            }
        } else if (book.getValue().equals("预约")) {
            try {
                String matter = itemForSend.getText().trim();
                String date = deliverDate.getValue().toString();
                String time = deliverTime.getText().trim();

                String poster = posterman.getValue().trim();


                String startAdd = startAddress.getText().trim();
                String endAdd = endAddress.getText().trim();
                String hint = notes.getText().trim();

                if (matter.isEmpty() || matter.equals("") || matter.length() == 0 || matter == null) {
                    Utils.dialogCreator(3, "错误", "未输入物品名", "请检查输入");
                    return -1;
                }

                if (poster.isEmpty() || poster.equals("") || poster.length() == 0 || poster == null) {
                    Utils.dialogCreator(3, "错误", "未选择快递员", "请检查输入");
                    return -1;
                }
                if (date == null | date.isEmpty() || date.equals("") || date.length() == 0) {
                    Utils.dialogCreator(3, "错误", "未设置日期", "请检查输入");
                    return -1;
                }
                if (time == null || time.isEmpty() || time.equals("") || time.length() == 0) {
                    Utils.dialogCreator(3, "错误", "未设置时间", "请检查输入");
                    return -1;
                }

                if (startAdd.isEmpty() || startAdd.equals("") || startAdd.length() == 0 || startAdd == null) {
                    Utils.dialogCreator(3, "错误", "未输入起始地址", "请检查输入");
                    return -1;
                }
                if (endAdd.isEmpty() || endAdd.equals("") || endAdd.length() == 0 || endAdd == null) {
                    Utils.dialogCreator(3, "错误", "未输入结束地址", "请检查输入");
                    return -1;
                }
                try {
                    LocalTime lt = LocalTime.parse(time);
                    LocalDateTime ldt = LocalDateTime.of(deliverDate.getValue(), lt);
                    BasicDao.executeUpdate("insert into express_service(user_id,worker_id,delivery,delivery_status,delivery_date,start_address,aim_address,hint)values('" + DataStore.userId + "','" + poster + "','" + matter + "','Waiting','" + ldt.toString() + "','" + startAdd + "','" + endAdd + "','" + hint + "')");
                    Utils.dialogCreator(1, "成功", "已收到您的订单，快递员会上门处理", "请耐心等待");
                    return 0;
                } catch (DateTimeParseException dtpe) {
                    Utils.dialogCreator(3, "错误", "时间格式不正确，请以HH:MM:SS 格式输入时间", "请检查输入");
                }
            }
            catch (NullPointerException npe)
            {
                Utils.dialogCreator(3, "错误", "存在未填写的内容", "请检查输入");
            }
        }
        return 0;

    }

    @FXML
    private void query() {
        if (book.getValue().equals("直送")) {
            LocalTime lc = LocalTime.now();
            ResultSet rs = BasicDao.executeQuery("select * from express_workers where worker_work_time<' " + lc.toString() + "' and worker_role='D'");
            List<TExpressWorker> allworkerList = ExpressWorkerDao.getSelected(rs);
            List<String> allworkname = new ArrayList<>();
            for (TExpressWorker tExpressWorker : allworkerList) {
                allworkname.add(tExpressWorker.getWorker_id());
            }
            ObservableList<String> oc = FXCollections.observableList(allworkname);
            posterman.setItems(oc);
        } else if (book.getValue().equals("预约")) {
            try {
                LocalTime lc = LocalTime.parse(deliverTime.getText().trim());
                ResultSet rs = BasicDao.executeQuery("select * from express_workers where worker_work_time<' " + lc.toString() + "' and worker_role='D'");
                List<TExpressWorker> allworkerList = ExpressWorkerDao.getSelected(rs);
                List<String> allworkname = new ArrayList<>();
                for (TExpressWorker tExpressWorker : allworkerList) {
                    allworkname.add(tExpressWorker.getWorker_id());
                }
                ObservableList<String> oc = FXCollections.observableList(allworkname);
                posterman.setItems(oc);
            } catch (DateTimeParseException dte) {
                Utils.dialogCreator(3, "错误", "请输入HH:MM:SS格式的时间", "请检查输入");
            }


        }

    }


}
