package com.githup.dszentgy.beadando.dao.mysql;

import com.githup.dszentgy.beadando.dao.PostOfficeDAO;
import com.githup.dszentgy.beadando.exception.NotFoundNameException;
import com.githup.dszentgy.beadando.model.PostOffice;
import com.githup.dszentgy.beadando.model.PostOfficeCategory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class PostOfficeDAOsql implements PostOfficeDAO {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    private Connection connection;

    @Override
    public Collection<PostOffice> readPostOffice() {
        String selectSQL = "SELECT * FROM drygoods";
        PreparedStatement preparedStatement = null;
        Collection<PostOffice> postOffices = null;
        PostOffice goods = null;
        try {
            connection=dataSource.getConnection();
            preparedStatement=connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isExist = resultSet.next();
            if (isExist){
                goods = new PostOffice(
                        resultSet.getString("Tipus"),
                        resultSet.getString("Név"),
                        resultSet.getInt("Ár"),
                        resultSet.getInt("Mennyiseg"),
                        (PostOfficeCategory) resultSet.getObject("Categoria"));
                postOffices.add(goods);
            }
            resultSet.close();
            preparedStatement.close();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return postOffices;
    }

    @Override
    public PostOffice readPostOfficeByName(String name) {
        String selectSQL = "SELECT * FROM postoffice WHERE Név = ?";
        PreparedStatement preparedStatement = null;
        PostOffice postOffice = null;
        try {
            connection=dataSource.getConnection();
            preparedStatement=connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isExist = resultSet.next();
            if (isExist){
                postOffice = new PostOffice(
                        resultSet.getString("Tipus"),
                        resultSet.getString("Név"),
                        resultSet.getInt("Ár"),
                        resultSet.getInt("Mennyiseg"),
                        (PostOfficeCategory) resultSet.getObject("Categoria"));
            }
            resultSet.close();
            preparedStatement.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return postOffice;
    }

    @Override
    public void createPostOffice(PostOffice postOffice) {
        String updateSQL = "UPDATE postoffice SET Tipus = ?, Név = ?, Ár = ?, Mennyiseg = ?, Categoria = ?";
        try
        {
            connection=dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(updateSQL);
            ps.setString(1, postOffice.getType());
            ps.setString(2,postOffice.getName());
            ps.setInt(3, postOffice.getPrice());
            ps.setInt(4, postOffice.getQuantity());
            ps.setString(5, postOffice.getPostOfficeCategory().toString());
        }catch (SQLException e){
            throw new RuntimeException();
        }

    }

    @Override
    public void updatePostOffice(PostOffice postOffice) throws NotFoundNameException{
        String updateSQL = "UPDATE postoffice SET Tipus = ?, Név = ?, Ár = ?, Mennyiseg = ?, Categoria = ? WHERE  Név=?";
        PostOffice old = new PostOffice(postOffice.getType(),postOffice.getName(),postOffice.getPrice(),postOffice.getQuantity(), postOffice.getPostOfficeCategory());
        try
        {
            connection=dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(updateSQL);
            ps.setString(1, postOffice.getType());
            ps.setString(2,postOffice.getName());
            ps.setInt(3, old.getPrice() + postOffice.getPrice());
            ps.setInt(4, old.getQuantity() + postOffice.getQuantity());
            ps.setString(5, postOffice.getPostOfficeCategory().toString());

            if (ps.executeUpdate()== 0){
                throw new NotFoundNameException("Can not update because this name does not exist!");
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public void deletePostOffice(PostOffice postOffice) throws NotFoundNameException{
        String deleteSQL = "DELETE FROM postoffice WHERE Név = ?";
        try
        {
            connection=dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(deleteSQL);
            ps.setString(1, postOffice.getName());
            if(ps.executeUpdate() == 0)
            {
                throw new NotFoundNameException("Not in delete because this name does not exist!");
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
}
