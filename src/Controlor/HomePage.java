package Controlor;


import Dao.ExpressUserDao;
import Dao.ExpressWorkerDao;
import com.mysql.jdbc.StringUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static utils.Utils.dialogCreator;

public class HomePage {
    @FXML
    private Button login;
    @FXML
    private Button regsis;
    @FXML
    private TextField userAccount;
    @FXML
    private PasswordField pwd;
    @FXML
    private CheckBox isAdmin;



    @FXML
    protected void reg()
    {
        try {
            Parent anotherRoot = FXMLLoader.load(getClass().getResource("../GUI/regsist.fxml"));
            Stage anotherStage = new Stage();
            anotherStage.setTitle("用户注册");
            anotherStage.setScene(new Scene(anotherRoot, 300, 600));
            anotherStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
    @FXML
    protected int  login()
    {

        boolean admin=isAdmin.isSelected();
        String userAccountText=userAccount.getText().trim();
        String password=pwd.getText().trim();
        if (userAccountText.equals("") ||userAccountText==null|| StringUtils.isEmptyOrWhitespaceOnly(userAccountText))//表单判断
        {
            dialogCreator(3,"错误","请输入用户名","请检查输入");
            return -1;
        }
        if (password.equals("") ||password==null|| StringUtils.isEmptyOrWhitespaceOnly(password))//表单判断
        {
            dialogCreator(3,"错误","请输入密码","请检查输入");
            return -1;
        }
        if (!admin)
        {
            if(ExpressUserDao.isThisAUser(userAccountText,password))
            {
                DataStore.userId=userAccountText;
                try {
                    Parent anotherRoot = FXMLLoader.load(getClass().getResource("../GUI/user_control_pane.fxml"));
                    Stage anotherStage = new Stage();
                    anotherStage.setTitle("用户界面");
                    anotherStage.setScene(new Scene(anotherRoot, 600, 400));
                    anotherStage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else if (ExpressWorkerDao.isThisDeliver(userAccountText,password))
            {
                DataStore.deliverId=userAccountText;
                try {
                    Parent anotherRoot = FXMLLoader.load(getClass().getResource("../GUI/deliver_control_pane.fxml"));
                    Stage anotherStage = new Stage();
                    anotherStage.setTitle("快递员界面");
                    anotherStage.setScene(new Scene(anotherRoot, 600, 400));
                    anotherStage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else{
                dialogCreator(3,"错误","用户名或密码输入错误!","请检查输入");
            }
        }
        if (admin)
           if (ExpressWorkerDao.isThisAdmin(userAccountText,password))
           {
               DataStore.workerId=userAccountText;
               try {
                   Parent anotherRoot = FXMLLoader.load(getClass().getResource("../GUI/admin_control_pane.fxml"));
                   Stage anotherStage = new Stage();
                   anotherStage.setTitle("管理员界面");
                   anotherStage.setScene(new Scene(anotherRoot, 600, 400));
                   anotherStage.show();

               } catch (IOException e) {
                   e.printStackTrace();
               }

           }
        else {
               dialogCreator(3,"错误","用户名或密码输入错误!","请检查输入");
           }
        return 0;
    }

}

