package Controlor;

import Dao.BasicDao;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;


public class ExpressManager {
    //TODO:这里需要实现一些代码,让订单管理成为可能
    @FXML
    private TextField uid;
    @FXML
    private TextField did;
    @FXML
    private Button queryButton;
    @FXML
    private Button executeButton;
    @FXML
    private DatePicker birthday;
    @FXML
    private ChoiceBox<String> cb;
    @FXML
    private TextField timeOfpost;
    @FXML
    private TextArea outAddress;
    @FXML
    private TextArea aimAddress;
    @FXML
    private TextField content;
    private int id;
    @FXML
    private void modeShift() {
        switch (cb.getValue()) {
            case "--请选择--":
                queryButton.setDisable(true);
                executeButton.setDisable(true);
                uid.setText("");
                did.setText("");
                birthday.setValue(null);

                content.setText("");
                outAddress.setText("");
                aimAddress.setText("");
                content.setDisable(true);
                outAddress.setDisable(true);
                aimAddress.setDisable(true);
                break;
            case "删除订单":
                queryButton.setDisable(true);
                executeButton.setDisable(true);
                uid.setText("");
                did.setText("");
                birthday.setValue(null);
                content.setText("");
                outAddress.setText("");
                aimAddress.setText("");
                queryButton.setDisable(false);
                content.setDisable(false);
                outAddress.setDisable(false);
                aimAddress.setDisable(false);
                content.setEditable(false);
                outAddress.setEditable(false);
                aimAddress.setEditable(false);
                break;
            case "信息修改":
                queryButton.setDisable(true);
                executeButton.setDisable(true);
                uid.setText("");
                did.setText("");
                birthday.setValue(null);
                content.setText("");
                outAddress.setText("");
                aimAddress.setText("");
                queryButton.setDisable(false);
                content.setDisable(false);
                outAddress.setDisable(false);
                aimAddress.setDisable(false);
                content.setEditable(true);
                outAddress.setEditable(true);
                aimAddress.setEditable(true);
                break;

        }
    }

    @FXML
    private int query() {
        String userId = uid.getText().trim();
        if (userId.isEmpty() || userId.equals("") || userId.length() == 0) {
            Utils.dialogCreator(3, "错误", "你没有输入用户id", "请检查输入");
            return -1;
        }
        String workerId = did.getText().trim();
        if (workerId.isEmpty() || workerId.equals("") || workerId.length() == 0) {
            Utils.dialogCreator(3, "错误", "你没有输入快递员工号", "请检查输入");
            return -1;
        }
        String postDay = birthday.getValue().toString();
        if (postDay.isEmpty() || postDay.equals("") || postDay.length() == 0) {
            Utils.dialogCreator(3, "错误", "你没有输入日期", "请检查输入");
            return -1;
        }
        String postTime = timeOfpost.getText();
        if (postTime.isEmpty() || postTime.equals("") || postTime.length() == 0) {
            Utils.dialogCreator(3, "错误", "你没有输入时间", "请检查输入");
            return -1;
        }
        try {
            LocalTime lt = LocalTime.parse(postTime);
        } catch (DateTimeParseException dtpe) {

            Utils.dialogCreator(3, "错误!", "请以HH:MM:SS格式输入时间", "错误的时间格式");
            return -1;
        }
        ResultSet rs = BasicDao.executeQuery("select * from express_service where worker_id='" + workerId + "' and user_id='" + userId + "' and delivery_date='" + (postDay + " " + postTime) + "'");
        try {
            if (rs.next()) {
                id=rs.getInt("id");
                content.setText(rs.getString("delivery"));
                outAddress.setText(rs.getString("start_address"));
                aimAddress.setText(rs.getString("aim_address"));
            }
            else{
                Utils.dialogCreator(3, "错误", "无此订单", "请检查输入");
            }
        } catch (SQLException se) {
            se.printStackTrace();

            return -1;
        }
        executeButton.setDisable(false);
        //Todo:not finished


        return 0;
    }

    @FXML
    private int execute() {
        switch (cb.getValue())
        {
            case"删除订单":
                String userId = uid.getText().trim();
                if (userId.isEmpty() || userId.equals("") || userId.length() == 0) {
                    Utils.dialogCreator(3, "错误", "你没有输入用户id", "请检查输入");
                    return -1;
                }
                String workerId = did.getText().trim();
                if (workerId.isEmpty() || workerId.equals("") || workerId.length() == 0) {
                    Utils.dialogCreator(3, "错误", "你没有输入快递员工号", "请检查输入");
                    return -1;
                }
                String postDay = birthday.getValue().toString();
                if (postDay.isEmpty() || postDay.equals("") || postDay.length() == 0) {
                    Utils.dialogCreator(3, "错误", "你没有输入日期", "请检查输入");
                    return -1;
                }
                String postTime = timeOfpost.getText();
                if (postTime.isEmpty() || postTime.equals("") || postTime.length() == 0) {
                    Utils.dialogCreator(3, "错误", "你没有输入时间", "请检查输入");
                    return -1;
                }
                try {
                    LocalTime lt = LocalTime.parse(postTime);
                } catch (DateTimeParseException dtpe) {

                    Utils.dialogCreator(3, "错误!", "请以HH:MM:SS格式输入时间", "错误的时间格式");
                    return -1;
                }
                BasicDao.executeUpdate("delete * from express_service where user_id='"+userId+"' and worker_id='"+workerId+"'and user_id='" + userId + "' and delivery_date='" + (postDay + " " + postTime) + "'");
                Utils.dialogCreator(1,"成功","已成功删除1条记录","删除成功");
                break;
            case"信息修改":
                String userId2 = uid.getText().trim();
                if (userId2.isEmpty() || userId2.equals("") || userId2.length() == 0) {
                    Utils.dialogCreator(3, "错误", "你没有输入用户id", "请检查输入");
                    return -1;
                }
                String workerId2 = did.getText().trim();
                if (workerId2.isEmpty() || workerId2.equals("") || workerId2.length() == 0) {
                    Utils.dialogCreator(3, "错误", "你没有输入快递员工号", "请检查输入");
                    return -1;
                }
                String postDay2 = birthday.getValue().toString();
                if (postDay2.isEmpty() || postDay2.equals("") || postDay2.length() == 0) {
                    Utils.dialogCreator(3, "错误", "你没有输入日期", "请检查输入");
                    return -1;
                }
                String postTime2 = timeOfpost.getText();
                if (postTime2.isEmpty() || postTime2.equals("") || postTime2.length() == 0) {
                    Utils.dialogCreator(3, "错误", "你没有输入时间", "请检查输入");
                    return -1;
                }
                String content2=content.getText().trim();
                if (content2.isEmpty() || content2.equals("") || content2.length() == 0) {
                    Utils.dialogCreator(3, "错误", "你没有输入寄送内容", "请检查输入");
                    return -1;
                }
                String start=outAddress.getText().trim();
                if (start.isEmpty() || start.equals("") || start.length() == 0) {
                    Utils.dialogCreator(3, "错误", "你没有输入始发地址", "请检查输入");
                    return -1;
                }
                String end=aimAddress.getText().trim();
                if (end.isEmpty() || end.equals("") || end.length() == 0) {
                    Utils.dialogCreator(3, "错误", "你没有输入到达地址", "请检查输入");
                    return -1;
                }
                try {
                    LocalTime lt = LocalTime.parse(postTime2);
                } catch (DateTimeParseException dtpe) {
                    Utils.dialogCreator(3, "错误!", "请以HH:MM:SS格式输入时间", "错误的时间格式");
                    return -1;
                }

                BasicDao.executeUpdate("update express_service set user_id='"+userId2+"' ,  worker_id='"+workerId2+"' , delivery_date='"+(postDay2+" "+postTime2)+"' , delivery='"+content2+"' , start_address='"+outAddress+"' , aim_address='"+aimAddress+"' where  id='"+id+"'");
                Utils.dialogCreator(1,"成功","已成功修改1条记录","修改成功");
                break;
        }
        return 0;
    }
}
