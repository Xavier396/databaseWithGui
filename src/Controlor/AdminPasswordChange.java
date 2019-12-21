package Controlor;

import Dao.BasicDao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import utils.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class AdminPasswordChange {
    @FXML
    private TextField adminPwd;
    @FXML
    private TextField name;
    @FXML
    private TextField workOn;
    @FXML
    private TextField workOff;
    @FXML
    private void query()
    {
        System.out.println(DataStore.workerId);
        ResultSet rs=BasicDao.executeQuery("select * from express_workers where worker_id='"+DataStore.workerId+"' and worker_role='A'");
        try {
            if (rs.next()) {

                name.setText(rs.getString("worker_name"));
                adminPwd.setText(rs.getString("worker_pwd"));
                workOn.setText(rs.getTime("worker_work_time").toString());
                workOff.setText(rs.getTime("worker_finish_time").toString());

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private int update()
    {
        String worker_name = name.getText().trim();
        String worker_password = adminPwd.getText().trim();
        String worker_start_time = workOn.getText().trim();
        String worker_finish_time = workOff.getText().trim();
        if (worker_name.isEmpty() || worker_name.equals("") || worker_name.length() == 0) {
            Utils.dialogCreator(3, "错误", "未输入名字", "请检查输入");
            return -1;
        }
        if (worker_password.isEmpty() || worker_password.equals("") || worker_password.length() == 0) {
            Utils.dialogCreator(3, "错误", "未输入密码", "请检查输入");
            return -1;
        }
        try {
            LocalTime lt = LocalTime.parse(worker_start_time);
            LocalTime lc = LocalTime.parse(worker_finish_time);
        } catch (DateTimeParseException dtpe) {
            Utils.dialogCreator(3, "错误!", "请以HH:MM:SS格式输入时间", "错误的时间格式");
            return -1;
        }
        BasicDao.executeUpdate("update express_workers set worker_name='"+worker_name+"',worker_pwd='"+worker_password+"',worker_work_time'"+worker_start_time+"',worker_finish_time'"+worker_finish_time+"'  where worker_id='"+DataStore.workerId+"' and worker_role='A'");
        Utils.dialogCreator(1,"成功","您的相关信息已经完成更改","更新信息成功");
        return 0;
    }
}
