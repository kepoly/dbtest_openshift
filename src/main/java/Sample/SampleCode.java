package Sample;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.*;

public class SampleCode {

   private static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found exception! "
                    + e.getMessage());
        }

        String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
        String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
        String name = "sample";
        String url = "jdbc:mysql://" + host + ":" + port + "/" + name;
        String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
        String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
        Connection con = DriverManager.getConnection(url, username, password);
        return con;
    }

    public static String getJSON() {
        String output = "";
        try {
            JsonArray arr = new JsonArray();
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM people");
            while (rs.next()) {
                JsonObject obj = new JsonObject();
                obj.addProperty("id", rs.getInt("id"));
                obj.addProperty("name", rs.getString("name"));
                obj.addProperty("bio", rs.getString("bio"));
                arr.add(obj);
            }
            output = arr.toString();
            conn.close();
        } catch (SQLException ex) {
            output = "SQL Exception Error: " + ex.getMessage();
        }
        return output;
    }
    
    public static String getJSON(int id) {
        String output = "";
        try {
            JsonArray arr = new JsonArray();
            Connection conn = getConnection();
            String query = "SELECT * FROM people WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                JsonObject obj = new JsonObject();
                obj.addProperty("id", rs.getInt("id"));
                obj.addProperty("name", rs.getString("name"));
                obj.addProperty("bio", rs.getString("bio"));
                arr.add(obj);
            }
            output = arr.toString();
            conn.close();
        } catch (SQLException ex) {
            output = "SQL Exception Error: " + ex.getMessage();
        }
        return output;
    }
}
