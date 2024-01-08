package com.example.exampledemo.db;

import com.example.exampledemo.dto.StudentDTO;
import lombok.var;

import java.sql.Connection;
import java.sql.SQLException;

public class DBProcess {
    private static final String SAVE_STUDENT_DATA = "INSERT INTO STUDENT (stid, stname, staddress) VALUES (?,?,?)";

    public void saveItemOne(StudentDTO stDetails, Connection connection){
        try {
            var ps = connection.prepareStatement(SAVE_STUDENT_DATA);
            ps.setString(1,stDetails.getId() );
            ps.setString(2, stDetails.getName());
            ps.setString(3, stDetails.getAddress());

            if (ps.executeUpdate() != 0) {
                System.out.println("Data saved");
            } else {
                System.out.println("Failed to save");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
