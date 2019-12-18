package GUI;

import Dao.BasicDao;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeliverManager {

    @FXML
    private ComboBox<String> cb;//功能选择框
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
    private boolean isQueried = false;
    private final String rule_of_phone = "1\\d{10}";

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
    private int addWorker() {
        Pattern pattern = Pattern.compile(rule_of_phone);//电话正则检测
        int x=0;
        String worker_name = name.getText().trim();
        String worker_phone = phone.getText().trim();
        LocalDate worker_birthday = birthday.getValue();
        String worker_gender = gender.getValue();
        String worker_password = password.getText().trim();
        String worker_start_time = workStart.getText().trim();
        String worker_finish_time = workStop.getText().trim();
        String workerId="D0000";
        Matcher mc = pattern.matcher(worker_phone);
        //表单检验
        if (worker_name.isEmpty() || worker_name.equals("") || worker_name.length() == 0) {
            Utils.dialogCreator(3, "错误", "未输入名字", "请检查输入");
            return -1;
        }
        if (worker_phone.isEmpty() || worker_phone.equals("") || worker_phone.length() == 0) {
            Utils.dialogCreator(3, "错误", "未输入电话", "请检查输入");
            return -1;
        }
        if (worker_password.isEmpty() || worker_password.equals("") || worker_password.length() == 0) {
            Utils.dialogCreator(3, "错误", "未输入密码", "请检查输入");
            return -1;
        }
        if (worker_start_time.isEmpty() || worker_start_time.equals("") || worker_start_time.length() == 0) {
            Utils.dialogCreator(3, "错误", "未输入开始时间 ", "请检查输入");
            return -1;
        }
        if (worker_finish_time.isEmpty() || worker_finish_time.equals("") || worker_finish_time.length() == 0) {
            Utils.dialogCreator(3, "错误", "未输入结束时间", "请检查输入");
            return -1;
        }
        if (worker_phone.length() != 11 && !mc.matches()) {
            Utils.dialogCreator(3, "错误", "手机号码格式不正确", "请检查输入");
            return -1;
        }
        if (worker_name.length()>10 || worker_name.length()<2) {
            Utils.dialogCreator(3, "错误", "姓名长度太短或太长(2-10字符以内)", "请检查输入");
            return -1;
        }if (worker_password.length()>16 || worker_password.length()<4) {
            Utils.dialogCreator(3, "错误", "手机号码格式不正确", "请检查输入");
            return -1;
        }if (worker_birthday.isAfter(LocalDate.now())||worker_birthday.getYear()<1960) {
            Utils.dialogCreator(3, "错误", "日期异常!请正确输入生日!", "请检查输入");
            return -1;
        }

        try {
            LocalTime lt = LocalTime.parse(worker_start_time);
            LocalTime lc = LocalTime.parse(worker_finish_time);
        } catch (DateTimeParseException dtpe) {
            Utils.dialogCreator(3, "错误!", "请以HH:MM:SS格式输入时间", "错误的时间格式");
            return -1;
        }
        ResultSet rs1=BasicDao.executeQuery("select count(*) from express_workers where worker_role='D'");
        try {
            if (rs1.next())
            {
                workerId+=(rs1.getInt(1)+(int)(1+Math.random()*10));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //拼接字符串
         x=BasicDao.executeUpdate("insert into express_workers(worker_id,worker_name,worker_gender,worker_role,worker_phone,worker_birthday,worker_pwd,worker_work_time,worker_finish_time) values('"+workerId+"','"+worker_name+"','"+worker_gender+"','"+"D"+"','"+worker_phone+"','"+worker_birthday.toString()+"','"+worker_password+"','"+worker_start_time+"','"+worker_finish_time+"')");
        while (x==-1)//可能有id和随机数,重复再试一遍插入
        {
           addWorker();
        }
        Utils.dialogCreator(1,"成功","帐号:"+workerId+"\n"+"密码:"+worker_password,"请妥善保存,切勿随意泄露");
        name.setText("");
        phone.setText("");
        birthday.setValue(LocalDate.of(2009,01,01));
        gender.setValue("--请选择--");
        password.setText("");
        workStart.setText("00:00:00");
        workStop.setText("00:00:00");
        isQueried = false;

        return 0;
    }
    @FXML
    private int updateWorker()
    {
        if(isQueried) {
            Pattern pattern = Pattern.compile(rule_of_phone);//电话正则检测
            int x = 0;
            String worker_name = name.getText().trim();
            String worker_phone = phone.getText().trim();
            LocalDate worker_birthday = birthday.getValue();
            String worker_gender = gender.getValue();
            String worker_password = password.getText().trim();
            String worker_start_time = workStart.getText().trim();
            String worker_finish_time = workStop.getText().trim();
            Matcher mc = pattern.matcher(worker_phone);
            //表单检验
            if (worker_name.isEmpty() || worker_name.equals("") || worker_name.length() == 0) {
                Utils.dialogCreator(3, "错误", "未输入名字", "请检查输入");
                return -1;
            }
            if (worker_phone.isEmpty() || worker_phone.equals("") || worker_phone.length() == 0) {
                Utils.dialogCreator(3, "错误", "未输入电话", "请检查输入");
                return -1;
            }
            if (worker_password.isEmpty() || worker_password.equals("") || worker_password.length() == 0) {
                Utils.dialogCreator(3, "错误", "未输入密码", "请检查输入");
                return -1;
            }
            if (worker_start_time.isEmpty() || worker_start_time.equals("") || worker_start_time.length() == 0) {
                Utils.dialogCreator(3, "错误", "未输入开始时间 ", "请检查输入");
                return -1;
            }
            if (worker_finish_time.isEmpty() || worker_finish_time.equals("") || worker_finish_time.length() == 0) {
                Utils.dialogCreator(3, "错误", "未输入结束时间", "请检查输入");
                return -1;
            }
            if (worker_phone.length() != 11 && !mc.matches()) {
                Utils.dialogCreator(3, "错误", "手机号码格式不正确", "请检查输入");
                return -1;
            }
            if (worker_name.length() > 10 || worker_name.length() < 2) {
                Utils.dialogCreator(3, "错误", "姓名长度太短或太长(2-10字符以内)", "请检查输入");
                return -1;
            }
            if (worker_password.length() > 16 || worker_password.length() < 4) {
                Utils.dialogCreator(3, "错误", "手机号码格式不正确", "请检查输入");
                return -1;
            }
            if (worker_birthday.isAfter(LocalDate.now()) || worker_birthday.getYear() < 1960) {
                Utils.dialogCreator(3, "错误", "日期异常!请正确输入生日!", "请检查输入");
                return -1;
            }

            try {
                LocalTime lt = LocalTime.parse(worker_start_time);
                LocalTime lc = LocalTime.parse(worker_finish_time);
            } catch (DateTimeParseException dtpe) {
                Utils.dialogCreator(3, "错误!", "请以HH:MM:SS格式输入时间", "错误的时间格式");
                return -1;
            }



        //拼接字符串
        x=BasicDao.executeUpdate("update express_workers set worker_name='"+worker_name+"',worker_gender='"+worker_gender+"',worker_phone='"+worker_phone+"',worker_birthday='"+worker_birthday.toString()+"',worker_pwd='"+worker_password+"',worker_work_time='"+worker_start_time+"',worker_finish_time='"+worker_finish_time+"'  where worker_id='"+workerId.getText()+"' and worker_role='D'");
        Utils.dialogCreator(1,"成功","您的相关信息已经完成更改","更新信息成功");
        name.setText("");
        phone.setText("");
        birthday.setValue(LocalDate.of(2009,01,01));
        gender.setValue("--请选择--");
        password.setText("");
        workStart.setText("00:00:00");
        workStop.setText("00:00:00");
        isQueried = false;

        return 0;
        }
        else
        {
            Utils.dialogCreator(3, "错误", "在执行操作前,请先查询信息", "请先查询");
            return -1;
        }
    }

    @FXML
    private void deleteWorker() {
        if (isQueried) {
            int request=BasicDao.executeUpdate("delete from express_workers where worker_id='"+workerId.getText()+"' and worker_role='D'");
            if (request==-1)
            {
                Utils.dialogCreator(3,"错误", "该工号不存在", "请检查输入");
            }
            else
            {
                Utils.dialogCreator(1,"成功","已成功删除用户"+workerId.getText(),"删除成功");
                name.setText("");
                phone.setText("");
                birthday.setValue(LocalDate.of(2009,01,01));
                gender.setValue("--请选择--");
                password.setText("");
                workerId.setText("");
                workStart.setText("00:00:00");
                workStop.setText("00:00:00");
            }
        } else {
            Utils.dialogCreator(3, "错误", "在执行操作前,请先查询信息", "请先查询");
        }
    }
    @FXML
    /*
    * 查询函数
    * */
    private int query() {
        String clause = workerId.getText();
        if (clause.equals("") || clause.length() == 0 || clause.isEmpty()) {
            Utils.dialogCreator(3, "错误", "请输入工号以查询", "请检查您的输入");
            return -1;
        }
        ResultSet rs = BasicDao.executeQuery("select * from express_workers where worker_id='" + clause + "'");
        try {
            if (rs.next()) {
                workerId.setEditable(true);
                name.setText(rs.getString("worker_name"));
                phone.setText(rs.getString("worker_phone"));
                birthday.setValue(rs.getDate("worker_birthday").toLocalDate());
                gender.setValue(rs.getString("worker_gender"));
                password.setText(rs.getString("worker_pwd"));
                workStart.setText(rs.getTime("worker_work_time").toString());
                workStop.setText(rs.getTime("worker_finish_time").toString());

            } else {
                Utils.dialogCreator(3, "错误", "查无此人", "请检查输入");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        isQueried = true;
        return 0;
    }

    @FXML
    private void typeShift() {
        switch (cb.getValue()) {
            case "增加员工":
                name.setEditable(true);
                phone.setEditable(true);
                birthday.setEditable(true);
                gender.setDisable(false);
                password.setEditable(true);
                workerId.setEditable(false);
                workStart.setEditable(true);
                workStop.setEditable(true);
                queryButton.setDisable(true);
                excute.setOnAction(action -> addWorker());//将执行按钮onAction转为插入
                break;
            case "删除员工":
                name.setEditable(false);
                phone.setEditable(false);
                birthday.setDisable(true);
                gender.setDisable(true);
                password.setEditable(false);
                workStart.setEditable(false);
                workStop.setEditable(false);
                workerId.setEditable(true);
                queryButton.setDisable(false);
                excute.setOnAction(action->deleteWorker());
                break;
            case "信息修改":
                name.setEditable(true);
                phone.setEditable(true);
                birthday.setEditable(true);
                gender.setDisable(false);
                password.setEditable(true);
                workStart.setEditable(true);
                workStop.setEditable(true);
                workerId.setEditable(true);
                queryButton.setDisable(false);
                excute.setOnAction(action->updateWorker());
                break;

            default:

                break;

        }
    }
}
