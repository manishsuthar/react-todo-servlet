

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/getList")
public class getList extends HttpServlet {
	CommanFunction cf = new CommanFunction();
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String listType = req.getParameter("listType");
		String sql = "select * from `task` where `status`='"+listType+"' ORDER BY `date` DESC";
		JSONObject jsonObject = new JSONObject();
		try {
			String result  = cf.getList(sql);
			if(!result.equals("")) {
				jsonObject.put("success", true);
				jsonObject.put("msg","Data Found");
				jsonObject.put("data",result);
			}else {
				jsonObject.put("success", true);
				jsonObject.put("msg","NO Data Found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		cf.response(res,jsonObject.toString());
	}

}
