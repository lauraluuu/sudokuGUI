package sudoku;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class creates a database to store the date relating to the player
 * including the player's id, login password and score
 * @author Peifen Lu 18008550
 */
public class Database {

    Connection connection = null;
    String url = "jdbc:derby:PlayerDataDB;create=true";

    String dbUserName = "sudoku";
    String dbPassword = "sudoku";

    /**
     * This method sets up a connection between database and program and
     * creates a table to store player's data if the table being created is not already existing.
     */
    public void dbsetup() {
        try {
            connection = DriverManager.getConnection(url, dbUserName, dbPassword);
            Statement statement = connection.createStatement();
            String tableName = "PlayerData";

            if (!checkTableExisting(tableName)) {
                statement.executeUpdate("CREATE TABLE " + tableName + " (playername VARCHAR(30), password VARCHAR(30), score INT)");
            }

            statement.close();

        } catch (Throwable e) {
            System.out.println("error");

        }
    }

    /**
     * This method checks whether the player exists in the database
     * @param usernameInput
     * @param passwordInput
     * @return data
     */
    public Data checkName(String usernameInput, String passwordInput) {
        Data data = new Data();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT playername, password, score FROM PlayerData "
                    + "WHERE playername = '" + usernameInput + "'");
            if (rs.next()) {
                String playerPassword = rs.getString("password");

                System.out.println("This player is found in the database!");
                if (passwordInput.compareTo(playerPassword) == 0) {
                    data.playerScore = rs.getInt("score");
                    data.loginNotify = true;
                } else {
                    data.loginNotify = false;
                }
            } else {
                System.out.println("The user does not exist in the database.");
                statement.executeUpdate("INSERT INTO PlayerData "
                        + "VALUES('" + usernameInput + "', '" + passwordInput + "', 0)");
                data.playerScore = 0;
                data.loginNotify = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }

    /**
     * This method checks whether the table being created is already existing in the database
     * @param newTableName
     * @return boolean to indicate whether the table exists or not
     */
    private boolean checkTableExisting(String newTableName) {
        boolean isExisting = false;
        try {

            System.out.println("The program is now checking the existing tables.");
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = connection.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);

            while (rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if (tableName.compareToIgnoreCase(newTableName) == 0) {
                    System.out.println(tableName + ": This table exists in the database!");
                    isExisting = true;
                }
            }
            if (rsDBMeta != null) {
                rsDBMeta.close();
            }
        } catch (SQLException ex) {
        }
        return isExisting;
    }

    /**
     * This method update the data including the player's name and score into the database
     * when the player quit the game
     * @param score
     * @param username 
     */
    public void quitGame(int score, String username) {

        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate("UPDATE PlayerData SET score=" + score + " WHERE playername='" + username + "'");
            System.out.println("Player Login id: "+username +" Current Score: "+ score);

        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
