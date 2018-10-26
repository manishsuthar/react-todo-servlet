

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/getOneTask")
public class getOneTask extends HttpServlet {
	CommanFunction cf = new CommanFunction();
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id = req.getParameter("id");
		String sql = "select * from `task` where `id`="+id;
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


