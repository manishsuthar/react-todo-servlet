import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CommanFunction { 
	Connection con = null;
	Statement stmt = null;
//	CommanFunction cf = new CommanFunction();
	public JSONArray convertResultSetIntoJSON(java.sql.ResultSet rs) throws Exception {
        JSONArray jsonArray = new JSONArray();
        while (rs.next()) {
            int total_rows = rs.getMetaData().getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i = 0; i < total_rows; i++) {
                String columnName = rs.getMetaData().getColumnLabel(i + 1).toLowerCase();
                Object columnValue = rs.getObject(i + 1);
                if (columnValue == null){
                    columnValue = "null";
                }
                if (obj.has(columnName)){
                    columnName += "1";
                }
                obj.put(columnName, columnValue);
            }
            jsonArray.put(obj);
        }
        return jsonArray;
    }
	
	public void response(HttpServletResponse resp, String msg) throws IOException {
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		out.println(msg);
	}
	
	public String getCurrentDate() {
		java.util.Date dt = new java.util.Date();
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		return currentTime;
	}
	public boolean ExecuteInsert(String sql) {
		
		try 
		{
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
			return true;
		}
		catch (SQLException ex) 
		{
			return false;
		}
		
		
	}
	
	public String getList(String sql) throws Exception {
		try 
		{
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(sql);
			 JSONArray jsonArray = convertResultSetIntoJSON(rs);
			 return jsonArray.toString();
		}
		catch (SQLException ex) 
		{
			return "";
		}
		
		
	}
	
	public int UpdateStatus(String sql) {
		int result = 0;
		try 
		{
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			 result = stmt.executeUpdate(sql);
			 return result;
		}
		catch (SQLException ex) 
		{
			return result; 
		}
		
	}

}
