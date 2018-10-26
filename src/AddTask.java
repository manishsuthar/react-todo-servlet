

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/AddTask")
public class AddTask extends HttpServlet {
	CommanFunction cf = new CommanFunction();
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String task = req.getParameter("task");
		String priority = req.getParameter("priority");
		String nowDate = cf.getCurrentDate();
		System.out.println(task);
		String sql = "insert into `task` (`userid`, `task`, `priority`, `date`, `status`) VALUES (1,'"+task+"','"+priority+"','"+nowDate+"','TODO')";
		JSONObject jsonObject  = new JSONObject();
		if(cf.ExecuteInsert(sql)) {
			try {   jsonObject.put("success", true);
					jsonObject.put("msg", "Task Successfully Inserted");
				} catch (JSONException e) {
					e.printStackTrace();
				}
		}else {
			try {   jsonObject.put("success", false);
					jsonObject.put("msg", "Something Want Worng");
				} catch (JSONException e) {
					e.printStackTrace();
				}
		}
		cf.response(res,jsonObject.toString());
	}

}
