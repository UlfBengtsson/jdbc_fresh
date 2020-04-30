package org.example.dao;

import org.example.model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoJDBC implements CountryDao {

    private final String connectionString;
    private final String userName;
    private final String userPassword;

    public CountryDaoJDBC(String connectionString, String userName, String userPassword) {
        this.connectionString = connectionString;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    @Override
    public List<Country> getContinent(String continent) {
        List<Country> countries = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection(connectionString, userName, userPassword);
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM country WHERE continent = ?", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);//OCP book page 518
        )
        {
            preparedStatement.setString(1, continent);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                countries.add(
                        new Country(
                                resultSet.getString(1),// Col index starts at 1
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4)
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;
    }

    //outside of interface
    public List<Country> getContinentFirstLast(String continent) {
        List<Country> countries = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection(connectionString, userName, userPassword);
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM country WHERE continent = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);//OCP book page 518
        )
        {
            preparedStatement.setString(1, continent);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.first()) {
                countries.add(
                        new Country(
                                resultSet.getString(1),// Col index starts at 1
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4)
                        )
                );
            }
            if (resultSet.last()) {
                String countryNameBig = resultSet.getString(2).toUpperCase();
                resultSet.updateString(2, countryNameBig);                       // like git commit
                resultSet.updateRow();                                                      // like git push
                countries.add(
                        new Country(
                                resultSet.getString(1),// Col index starts at 1
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4)
                        )
                );
            }
            if (resultSet.previous()) {
                countries.add(
                        new Country(
                                resultSet.getString(1),// Col index starts at 1
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4)
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countries;
    }

    @Override
    public List<Country> getRegion(String region) {
        return null;
    }

    @Override
    public Country getByName(String name) {
        return null;
    }

    @Override
    public Country getByCode(String code) {
        return null;
    }

    @Override
    public Country create(Country country) {
        try (
                Connection connection = DriverManager.getConnection(connectionString, userName, userPassword);
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO country (Code,Name,Region,Continent) VALUES(?,?,?,?)");
        )
        {
            preparedStatement.setString(1, country.getCode());
            preparedStatement.setString(2, country.getName());
            preparedStatement.setString(3, country.getRegion());
            preparedStatement.setString(4, country.getContinent());

            int rowsChanged = preparedStatement.executeUpdate();
            System.out.println("rows changed: " + rowsChanged);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return country;
    }
}
