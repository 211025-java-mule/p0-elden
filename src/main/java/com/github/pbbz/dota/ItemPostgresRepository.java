package com.github.pbbz.dota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ItemPostgresRepository{
    private Connection connection;

    public ItemPostgresRepository(Connection connection){
        this.connection = connection;
    }

    public void create(List<Item> itemList) throws SQLException {
        PreparedStatement statement = connection.prepareStatement
            ("insert into item(name, cost, secretShop, sideShop, recipe) values (?, ?, ?, ?, ?)");

        for (int i = 0; i < itemList.size(); i++){
            statement.setString(1, itemList.get(i).getName());
            statement.setInt(2, itemList.get(i).getCost());
            statement.setInt(3, itemList.get(i).getSecret());
            statement.setInt(4, itemList.get(i).getSide());
            statement.setInt(5, itemList.get(i).getRecipe());
            statement.executeUpdate();
        }
        statement.close();
    }
    
}