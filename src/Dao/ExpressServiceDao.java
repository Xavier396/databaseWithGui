package Dao;

import Table.TExpressService;

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
            expressService.setUser_phone(rs.getString("user_phone"));
            expressService.setWorker_phone(rs.getString("worker_phone"));
            expressService.setDelivery(rs.getString("delivery"));
            expressService.setDelivery_status(rs.getString("deliver_status"));
            expressService.setStart_address(rs.getString("start_address"));
            expressService.setAim_address(rs.getString("aim_address"));
            expressService.setStart_time(rs.getString("start_time"));
            expressService.setEnd_time(rs.getString("end_time"));
            expressService.setRate(rs.getInt("rate"));
            expressService.setTime_stamp(rs.getString("times_stamp"));


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expressService;
    }

}
