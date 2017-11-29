package com.githup.dszentgy.beadando.dao.mysql;

import com.githup.dszentgy.beadando.dao.DryGoodsDAO;
import com.githup.dszentgy.beadando.exception.NotFoundNameException;
import com.githup.dszentgy.beadando.model.DryGoods;
import com.githup.dszentgy.beadando.model.DryGoodsCategory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Collection;

public class DryGoodsDAOsql implements DryGoodsDAO{

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    private Connection connection;

    @Override
    public Collection<DryGoods> readDryGoods() {
        String selectSQL = "SELECT * FROM drygoods";
        PreparedStatement preparedStatement = null;
        Collection<DryGoods> dryGoods = null;
        DryGoods goods = null;
        try {
            connection=dataSource.getConnection();
            preparedStatement=connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isExist = resultSet.next();
            if (isExist){
                goods = new DryGoods(
                        (DryGoodsCategory) resultSet.getObject("Categoria"),
                        resultSet.getString("Tipus"),
                        resultSet.getString("Név"),
                        resultSet.getInt("Ár"),
                        resultSet.getInt("Mennyiseg"));
                dryGoods.add(goods);
            }
            resultSet.close();
            preparedStatement.close();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return dryGoods;
    }

    @Override
    public DryGoods readDryGoodsByName(String name) {
        String selectSQL = "SELECT * FROM drygoods WHERE Név = ?";
        PreparedStatement preparedStatement = null;
        DryGoods dryGoods = null;
        try {
            connection=dataSource.getConnection();
            preparedStatement=connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isExist = resultSet.next();
            if (isExist){
                dryGoods = new DryGoods(
                        (DryGoodsCategory) resultSet.getObject("Categoria"),
                        resultSet.getString("Tipus"),
                        resultSet.getString("Név"),
                        resultSet.getInt("Ár"),
                        resultSet.getInt("Mennyiseg"));
            }
            resultSet.close();
            preparedStatement.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return dryGoods;
    }


    @Override
    public void createDryGoods(DryGoods dryGoods) {
        String updateSQL = "UPDATE drygoods SET Categoria = ?, Tipus = ?, Név = ?, Ár = ?, Mennyiseg = ?";
        try
        {
            connection=dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(updateSQL);
            ps.setString(1, dryGoods.getCategory().toString());
            ps.setString(2, dryGoods.getType());
            ps.setString(3,dryGoods.getName());
            ps.setInt(4, dryGoods.getPrice());
            ps.setInt(5, dryGoods.getQuantity());
        }catch (SQLException e){
            throw new RuntimeException();
        }
        }

    @Override
    public void updateDryGoods(DryGoods dryGoods) throws NotFoundNameException{
        String updateSQL = "UPDATE drygoods SET Categoria = ?, Tipus = ?, Név = ?, Ár = ?, Mennyiseg = ? WHERE  Név=?";
        DryGoods old = new DryGoods(dryGoods.getCategory(),dryGoods.getType(),dryGoods.getName(),dryGoods.getPrice(),dryGoods.getQuantity());
        try
        {
            connection=dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(updateSQL);
            ps.setString(1, dryGoods.getCategory().toString());
            ps.setString(2, dryGoods.getType());
            ps.setString(3,dryGoods.getName());
            ps.setInt(4, old.getPrice() + dryGoods.getPrice());
            ps.setInt(5, old.getQuantity() + dryGoods.getQuantity());
            if (ps.executeUpdate()== 0){
                throw new NotFoundNameException("Can not update because this name does not exist!");
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }

    }

    @Override
    public void deleteDryGoods(DryGoods dryGoods) throws NotFoundNameException{
        String deleteSQL = "DELETE FROM drygoods WHERE Név = ?";
        try
        {
            connection=dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(deleteSQL);
            ps.setString(1, dryGoods.getName());
            if(ps.executeUpdate() == 0)
            {
                throw new NotFoundNameException("Can not delete because this name does not exist!");
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
}
