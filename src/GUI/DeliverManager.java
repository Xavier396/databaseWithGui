package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.Utils;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class DeliverManager {

    @FXML
    private ComboBox<String> cb;
    @FXML
    private TextField name;
    @FXML
    private TextField phone;
    @FXML
    private DatePicker birthday;
    @FXML
    private ChoiceBox<String> gender;
    @FXML
    private TextField workerId;//工号
    @FXML
    private PasswordField password;
    @FXML
    private Button excute;
    @FXML
    private TextField workStart;
    @FXML
    private TextField workStop;
    @FXML
    private Button queryButton;

    //------D-A-N-G-E-R----
    @FXML
    private void turnToTime() {
        String worktime = workStart.getText();
        try {
            LocalTime lt = LocalTime.parse(worktime);
            System.out.println(lt);
        } catch (DateTimeParseException dtpe) {
            Utils.dialogCreator(3, "错误!", "请以HH:MM:SS格式输入时间", "错误的时间格式");
        }

    }

    //-------Z-O-N-E------
    @FXML
    private void addWorker() {
        String worker_name = name.getText();
        String worker_phone = phone.getText();

        LocalDate worker_birthday = birthday.getValue();
    }
    @FXML
    private int query()
    {
        return 0;
    }

    @FXML
    private void typeShift() {
        switch (cb.getValue()) {
            case "增加员工":
                name.setDisable(false);
                phone.setDisable(false);
                birthday.setDisable(false);
                gender.setDisable(false);
                password.setDisable(false);
                workerId.setDisable(true);
                workStart.setDisable(false);
                workStop.setDisable(false);
                queryButton.setDisable(true);
                excute.setOnAction(null);//将执行按钮onAction转为插入
                break;
            case "删除员工":
                name.setDisable(true);
                phone.setDisable(true);
                birthday.setDisable(true);
                gender.setDisable(true);
                password.setDisable(true);
                workStart.setDisable(true);
                workStop.setDisable(true);
                workerId.setDisable(false);
                queryButton.setDisable(false);
                break;
            case "信息修改":
                name.setDisable(false);
                phone.setDisable(false);
                birthday.setDisable(false);
                gender.setDisable(false);
                password.setDisable(false);
                workStart.setDisable(false);
                workStop.setDisable(false);
                workerId.setDisable(false);
                queryButton.setDisable(false);
                break;

            default:

                break;

        }
    }
}
