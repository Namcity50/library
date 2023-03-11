package org.example.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@Component
public class InitDatabase {
    @Autowired
    private Database database;
    public void intAdmin() {
        try {
            Connection con = database.getConnection();

            PreparedStatement preparedStatement = con.prepareStatement(
                    "insert into student(id,name,surname,phone,status,role,created_date,visible) " +
                            "values('-1','ali','aliyev','2222','ACTIVE','ADMIN',now(),true)" +
                            " ON CONFLICT (id) DO NOTHING;");
            int effectRows = preparedStatement.executeUpdate();
            System.out.println(effectRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
