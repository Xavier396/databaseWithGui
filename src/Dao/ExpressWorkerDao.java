package Dao;

import Table.TExpressWorker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpressWorkerDao {
    public List<TExpressWorker> getAllWorkers() {
        ResultSet resultSet = BasicDao.executeQuery("select * from express_workers");
        List<TExpressWorker> allWorkers = new ArrayList<>();

        try {
            while (resultSet.next()) {
                TExpressWorker tew = convertToTExpressWorker(resultSet);
                allWorkers.add(tew);
            }
            BasicDao.dbClose();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allWorkers;
    }

    public static TExpressWorker convertToTExpressWorker(ResultSet rs) {
        TExpressWorker tew = new TExpressWorker();
        try {
            tew.setId(rs.getInt("id"));
            tew.setWorker_birthday(rs.getString("worker_birthday"));
            tew.setWorker_id(rs.getString("worker_id"));
            tew.setWorker_join_date(rs.getString("woker_join_date"));
            tew.setWorker_name(rs.getString("worker_name"));
            tew.setWorker_phone(rs.getString("worker_phone"));
            tew.setWorker_pwd(rs.getString("worker_pwd"));
            tew.setWorker_role(rs.getString("worker_role").charAt(0));
            tew.setWorker_gender(rs.getString("worker_gender").charAt(0));
            tew.setWorker_work_time(rs.getString("worker_work_time"));
            tew.setWorker_finish_time(rs.getString("worker_finish_time"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tew;
    }
    public static boolean isThisAdmin(String account,String pwd)
    {
        boolean res=false;
        ResultSet rs=BasicDao.executeQuery("select * from express_workers where worker_id='"+account+"' and worker_pwd='"+pwd+"' and worker_role='A'");
        try {
            if(rs.next())
            {
                System.out.println("ok Admin");
                res=true;
            }
            else
            {
                System.out.println("wrong Admin");
                res=false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;

    }
}
