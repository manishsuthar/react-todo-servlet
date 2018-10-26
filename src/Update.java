

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/Update")
public class Update extends HttpServlet {
	CommanFunction cf = new CommanFunction();
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String id = req.getParameter("id"); 
		String status = req.getParameter("status");
		String sql = "UPDATE `task` SET `status`='"+status+"' WHERE `id`="+id;
		JSONObject jsonObject = new JSONObject();
		try {
			int result  = cf.UpdateStatus(sql);
			System.out.println(result);
			if(result == 1) {
				jsonObject.put("success", true);
				jsonObject.put("msg","Update Status");
			}else {
				jsonObject.put("success",false);
				jsonObject.put("msg","Something is Wrong");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		cf.response(res,jsonObject.toString());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
