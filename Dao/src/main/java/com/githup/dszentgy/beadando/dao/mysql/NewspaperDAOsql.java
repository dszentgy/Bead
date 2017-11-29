package com.githup.dszentgy.beadando.dao.mysql;

import com.githup.dszentgy.beadando.dao.NewspaperDAO;
import com.githup.dszentgy.beadando.exception.NotFoundNameException;
import com.githup.dszentgy.beadando.model.Newspaper;
import com.githup.dszentgy.beadando.model.NewspaperCategory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class NewspaperDAOsql implements NewspaperDAO {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    private Connection connection;
    @Override
    public Collection<Newspaper> readNewspaper() {
        String selectSQL = "SELECT * FROM newspaper";
        PreparedStatement preparedStatement = null;
        Collection<Newspaper> newspapers = null;
        Newspaper goods = null;
        try {
            connection=dataSource.getConnection();
            preparedStatement=connection.prepareStatement(selectSQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isExist = resultSet.next();
            if (isExist){
                goods = new Newspaper(
                        resultSet.getString("Tipus"),
                        resultSet.getString("Név"),
                        resultSet.getInt("Ár"),
                        resultSet.getInt("Mennyiseg"),
                        (NewspaperCategory) resultSet.getObject("Categoria"));
                newspapers.add(goods);
            }
            resultSet.close();
            preparedStatement.close();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return newspapers;
    }

    @Override
    public Newspaper readNewspaperByName(String name) {
        String selectSQL = "SELECT * FROM newspaper WHERE Név = ?";
        PreparedStatement preparedStatement = null;
        Newspaper newspaper = null;
        try {
            connection=dataSource.getConnection();
            preparedStatement=connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isExist = resultSet.next();
            if (isExist){
                newspaper = new Newspaper(
                        resultSet.getString("Tipus"),
                        resultSet.getString("Név"),
                        resultSet.getInt("Ár"),
                        resultSet.getInt("Mennyiseg"),
                        (NewspaperCategory) resultSet.getObject("Categoria"));
            }
            resultSet.close();
            preparedStatement.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return newspaper;
    }

    @Override
    public void createNewspaper(Newspaper newspaper) {
        String updateSQL = "UPDATE newspaper SET Tipus = ?, Név = ?, Ár = ?, Mennyiseg = ?, Categoria = ?";
        try
        {
            connection=dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(updateSQL);
            ps.setString(1, newspaper.getType());
            ps.setString(2,newspaper.getName());
            ps.setInt(3, newspaper.getPrice());
            ps.setInt(4, newspaper.getQuantity());
            ps.setString(5, newspaper.getNewspaperCategory().toString());
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public void updateNewspaper(Newspaper newspaper) throws NotFoundNameException{
        String updateSQL = "UPDATE newspaper SET Tipus = ?, Név = ?, Ár = ?, Mennyiseg = ?, Categoria = ? WHERE  Név=?";
        Newspaper old = new Newspaper(newspaper.getType(),newspaper.getName(),newspaper.getPrice(),newspaper.getQuantity(), newspaper.getNewspaperCategory());
        try
        {
            connection=dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(updateSQL);
            ps.setString(1, newspaper.getType());
            ps.setString(2,newspaper.getName());
            ps.setInt(3, old.getPrice() + newspaper.getPrice());
            ps.setInt(4, old.getQuantity() + newspaper.getQuantity());
            ps.setString(5, newspaper.getNewspaperCategory().toString());

            if (ps.executeUpdate()== 0){
                throw new NotFoundNameException("Can not update because this name does not exist!");
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteNewspaper(Newspaper newspaper) throws NotFoundNameException{
        String deleteSQL = "DELETE FROM newspaper WHERE Név = ?";
        try
        {
            connection=dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(deleteSQL);
            ps.setString(1, newspaper.getName());
            if(ps.executeUpdate() == 0)
            {
                throw new NotFoundNameException("Can't delete because this name does not exist!");
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }
}
