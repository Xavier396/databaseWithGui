package Dao;

import Table.TExpressService;
import Table.TExpressUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpressServiceDao {
    public static List<TExpressService> getAllService() {
        ResultSet resultSet = BasicDao.executeQuery("select * from express_service");
        List<TExpressService> expressServiceList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                TExpressService tes=convertToExpressService(resultSet);
                expressServiceList.add(tes);
            }
            BasicDao.dbClose();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expressServiceList;
    }

    private static TExpressService convertToExpressService(ResultSet rs) {
        TExpressService expressService = new TExpressService();
        try {
            expressService.setId(rs.getInt("id"));
            expressService.setUser_id(rs.getString("user_id"));
            expressService.setWorker_id(rs.getString("worker_id"));
            expressService.setDelivery(rs.getString("delivery"));
            expressService.setDelivery_status(rs.getString("delivery_status"));
            expressService.setStart_address(rs.getString("start_address"));
            expressService.setAim_address(rs.getString("aim_address"));
            expressService.setHint(rs.getString("hint"));
            expressService.setTime_stamp(rs.getString("time_stamp"));


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expressService;
    }
    public static List<TExpressService> getSelected(ResultSet rs)
    {
        List<TExpressService> selected=new ArrayList<>();
        try {
            while (rs.next())
            {
                TExpressService tew = convertToExpressService(rs);
                selected.add(tew);
            }
        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }
        return selected;
    }

}
