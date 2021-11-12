package com.github.pbbz.dota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class HeroPostgresRepository{
    private Connection connection;

    public HeroPostgresRepository(Connection connection){
        this.connection = connection;
    }

    public void create(List<Hero> heroList) throws SQLException {
        PreparedStatement statement = connection.prepareStatement
            ("insert into hero(name, attribute, attackType, damage, movespeed) values (?, ?, ?, ?, ?)");

        for (int i = 0; i < heroList.size(); i++){
            statement.setString(1, heroList.get(i).getName());
            statement.setString(2, heroList.get(i).getAttribute());
            statement.setString(3, heroList.get(i).getAttackType());
            statement.setInt(4, heroList.get(i).getDamage());
            statement.setInt(5, heroList.get(i).getSpeed());
            statement.executeUpdate();
        }
        statement.close();
    }
    
}
