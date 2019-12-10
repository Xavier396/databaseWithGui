package GUI;

import Dao.BasicDao;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.Utils.dialogCreator;

public class Regsist {
    @FXML
    private TextField userName;
    @FXML
    private PasswordField passWord;
    @FXML
    private TextField userPhone;
    @FXML
    private RadioButton man;
    @FXML
    private RadioButton woman;
    @FXML
    private DatePicker userBirthday;
    @FXML
    private void reset()
    {

        Optional<ButtonType> res=dialogCreator(2,"注意!","您真的要清除表单的所用内容吗?","您所做的更改将不会被保留!");
        if (res.get()==ButtonType.OK)
        {
            userName.setText("");
            passWord.setText("");
            userPhone.setText("");
            man.setSelected(false);
            woman.setSelected(false);
            userBirthday.setValue(null);
        }


    }
    @FXML
    private int submit()
    {
        String uN=userName.getText().trim();//用户名
        String pwd=passWord.getText().trim();//密码
        String uP=userPhone.getText().trim();
        boolean isMan=man.isSelected();
        boolean isWoman=woman.isSelected();
        LocalDate uBirthday=userBirthday.getValue();
        Pattern p=Pattern.compile("1\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d");
        Matcher m=p.matcher(uP);
        boolean r=m.matches();
        char gender='男';
        int acc=0;
        String userCode="";
        if (!isMan&& isWoman)gender='女';
        if (uN.length()==0||uN.equals("")||uN==null)
        {
            dialogCreator(3,"错误","你没有输入用户名","请检查输入");
            return -1;

        }
        else if (uN.length()<4||uN.length()>16)
        {
            dialogCreator(1,"注意!","请确保控制在4-16字符中!","您输入的用户名长度不合规");
            userName.setText("");
            return -1;
        }
        if (pwd.length()==0||pwd.equals("")||pwd==null)
        {
            dialogCreator(3,"错误","你没有输入密码","请检查输入");
            return -1;
        }
        else if(pwd.length()<4||pwd.length()>16)
        {
            dialogCreator(1,"注意!","请确保控制在4-16字符中!","您输入的密码长度不合规");
            passWord.setText("");
            return -1;
        }
        if(!isMan&&!isWoman)
        {
            dialogCreator(3,"错误","你没有选择性别","请检查输入");
            return -1;
        }
        if (uP.length()==0||uP.equals("")||uP==null)
        {
            dialogCreator(3,"错误","你没有输入电话号码","请检查输入");
            return -1;
        }
        else if(!r)//判断输入的电话是不是符合正则
        {
            dialogCreator(3,"错误","你的电话号码格式不正确","请检查输入");
            userPhone.setText("");
            return -1;
        }

        if (uBirthday.isAfter(LocalDate.now()))
        {
            dialogCreator(3,"错误","时间输入有误","请检查输入");
            return -1;
        }

        ResultSet rs= BasicDao.executeQuery("select count(*) from express_user");
        try {
            if(rs.next()) {
                acc = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(acc);
        if(acc+1<10)//按照实际数量来安排id
        {
            userCode="U0000000"+(acc + 1);
        }
        else if (acc+1<100)
        {
            userCode="U000000"+(acc + 1);
        }
        else if (acc+1<1000)
        {
         userCode="U00000"+(acc + 1);
        }
        else if(acc+1<10000)
        {
            userCode="U0000"+(acc + 1);
        }
        else if(acc+1<100000)
        {
            userCode="U000"+(acc + 1);
        }
        else if(acc+1<1000000)
        {
            userCode="U00"+(acc + 1);
        }
        else if(acc+1<10000000)
        {
            userCode="U0"+(acc + 1);
        }
        else if(acc+1<100000000)
        {
            userCode="U"+(acc + 1);
        }
        int ok=BasicDao.executeUpdate("INSERT INTO express_user(user_account,user_name,user_gender,user_birthtday,user_phone,user_pwd)VALUES ('" + userCode+ "','" + uN + "','" + gender + "','" + uBirthday.toString() + "','" + uP + "','" + pwd + "')");
        if (ok==-1)
        {
            dialogCreator(3,"错误","您已经注册过了","重复注册");
            return -1;
        }

        dialogCreator(1,"成功","请牢记您的用户名:"+userCode+"\n密码:"+pwd+",并不要随意向他人泄露","注册成功");
        return 0;

    }
    @FXML
    private void against()
    {
        if(woman.isSelected())
        {
            man.setSelected(false);
        }
        if(man.isSelected())
        {
            woman.setSelected(false);
        }
    }

}
