package main.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Илья on 04.12.2015.
 */
public class RecordListNameDAO {
    private final Connection connection;


    public RecordListNameDAO(Connection connection) {
        this.connection = connection;
    }

    //получение RecordListName из строки ResultSet
    private RecordListName getRecordListNameFromRs(ResultSet resultSet) {
        RecordListName recordListName = new RecordListName();
        try {
            recordListName.setId(resultSet.getInt("id"));
            recordListName.setListName(resultSet.getString("listname"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recordListName;
    }

    //получение RecordListName  по id
    public RecordListName getRecordListNameId(int id) {
        RecordListName recordListName = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM listname WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                recordListName = this.getRecordListNameFromRs(resultSet);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recordListName;
    }

    //получение всего списка RecordListName
    public List<RecordListName> getRecordListNameList() {
        List<RecordListName> recordListNamesList = new ArrayList<RecordListName>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM listname");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                recordListNamesList.add(this.getRecordListNameFromRs(resultSet));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recordListNamesList;
    }

    //создание/обновление RecordListName
    public void updateRecordListName(RecordListName recordListName) {
        try {
            PreparedStatement preparedStatement = null;
            if(recordListName.getId() > 0) {
                preparedStatement = connection.prepareStatement("UPDATE listname SET listname=? WHERE id=?");
                preparedStatement.setInt(2, recordListName.getId());
            }
            else {
                preparedStatement = connection.prepareStatement("INSERT INTO listname (listname) VALUES(?)");
            }
            preparedStatement.setString(1, recordListName.getListName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //удаление
    public void deleteRecordListName(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM listname WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
