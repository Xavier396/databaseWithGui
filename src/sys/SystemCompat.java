package sys;

import java.util.Scanner;

public class SystemCompat {

    public static void main(String[] args)
    {
        char control_word=' ';
        String account_name="";
        String password="";
        while (control_word!='q')
        {
            System.out.print("\t欢迎访问物流登陆界面，请选择\nu-用户登录\na-管理员登陆\nr-用户注册\nq-退出");
            Scanner sc=new Scanner(System.in);
            control_word=sc.nextLine().charAt(0);
            switch (control_word)
            {
                case 'u':
                    System.out.println("请输入您的用户名");
                    account_name=sc.nextLine();
                    System.out.println("请输入您的密码");
                    password=sc.nextLine();
                    show_user_feature(account_name,password);
                    break;
                case 'a':
                    System.out.println("请输入您的用户名");
                    account_name=sc.nextLine();
                    System.out.println("请输入您的密码");
                    password=sc.nextLine();
                    show_admin_feature(account_name,password);
                    break;
                case 'r':


            }
        }

    }
    public static void show_user_feature(String account,String password)
    {

    }
    public static void show_admin_feature(String account,String password)
    {

    }
}
