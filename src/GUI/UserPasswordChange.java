package GUI;

import Dao.BasicDao;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.Utils.dialogCreator;

public class UserPasswordChange {
    @FXML
    private TextField adminPwd;
    @FXML
    private TextField name;
    @FXML
    private TextField workOn;//其实这是电话
    @FXML
    private DatePicker workOff;//其实这是生日
    @FXML
    private int  excute()
    {
        String worker_name = name.getText().trim();
        String worker_password = adminPwd.getText().trim();
        String worker_start_time = workOn.getText().trim();
        String worker_finish_time = workOff.getValue().toString().trim();
        Pattern p=Pattern.compile("1\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d");
        Matcher m=p.matcher(worker_start_time);
        boolean r=m.matches();

        if (worker_name.isEmpty() || worker_name.equals("") || worker_name.length() == 0) {
            Utils.dialogCreator(3, "错误", "未输入名字", "请检查输入");
            return -1;
        }
        if (worker_password.isEmpty() || worker_password.equals("") || worker_password.length() == 0) {
            Utils.dialogCreator(3, "错误", "未输入密码", "请检查输入");
            return -1;
        }
        if (worker_start_time.length()==0||worker_start_time.equals("")||worker_start_time==null)
        {
            dialogCreator(3,"错误","你没有输入电话号码","请检查输入");
            return -1;
        }
        else if(!r)//判断输入的电话是不是符合正则
        {
            dialogCreator(3,"错误","你的电话号码格式不正确","请检查输入");
            workOn.setText("");
            return -1;
        }


        BasicDao.executeUpdate("update express_user set user_name='"+worker_name+"',user_pwd='"+worker_password+"',user_phone='"+worker_start_time+"',user_birthtday='"+worker_finish_time+"'  where user_account='"+DataStore.userId+"'");
        Utils.dialogCreator(1,"成功","您的相关信息已经完成更改","更新信息成功");
        return 0;
    }

    @FXML
    private void query()
    {
        System.out.println(DataStore.userId);
        ResultSet rs= BasicDao.executeQuery("select * from express_user where user_account='"+DataStore.userId+"' ");
        try {
            if (rs.next()) {
                String birthday=rs.getString("user_birthtday");
                String realBirthday=birthday.substring(0,10);
                name.setText(rs.getString("user_name"));
                adminPwd.setText(rs.getString("user_pwd"));
                workOn.setText(rs.getString("user_phone"));
                workOff.setValue(LocalDate.parse(realBirthday));
                System.out.println(realBirthday);
            }

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
