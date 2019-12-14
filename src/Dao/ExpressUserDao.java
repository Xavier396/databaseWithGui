package Dao;

import Table.TExpressUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpressUserDao {
    public static List<TExpressUser> getAllUser()
     {
        ResultSet resultSet=BasicDao.executeQuery("select * from express_user");
        List<TExpressUser> expressUserList=new ArrayList<>();
        try {
            while (resultSet.next())
            {
                TExpressUser teu=convertToTExpressUser(resultSet);
                expressUserList.add(teu);
            }
            BasicDao.dbClose();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
        return expressUserList;

    }
    public static TExpressUser convertToTExpressUser(ResultSet rs)
    {
        TExpressUser teu=new TExpressUser();
        try{
            teu.setId(rs.getInt("id"));
            teu.setUser_account(rs.getString("user_account"));
            teu.setUser_name(rs.getString("user_name"));
            teu.setUser_gender(rs.getString("user_gender").charAt(0));
            teu.setRegistertime(rs.getString("user_registertime"));
            teu.setBirthday(rs.getString("user_birthtday"));
            teu.setUser_phone(rs.getString("user_phone"));
            teu.setUser_pwd(rs.getString("user_pwd"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teu;
    }
    public static boolean isThisAUser(String username,String pwd)
    {
        boolean res=false;
        ResultSet rs=BasicDao.executeQuery("select * from express_user where user_account='"+username+"' and user_pwd='"+pwd+"'");
        try {
            if(rs.next())
            {
                System.out.println("ok user");
                res=true;
            }
            else
            {
                System.out.println("no such user");
                res=false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}
