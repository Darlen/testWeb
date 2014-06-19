package com.darlen.servlet;

import com.darlen.util.Util;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 这是一个测试login登录的case,里面包括ajax对<b>JSON</b>处理
 * User: darlenliu
 * Date: 14-6-18
 * Time: 下午4:22
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 4348577223281597346L;
    private static Logger logger =Logger.getLogger(LoginServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String action = request.getParameter("action");
		HttpSession session =  request.getSession();
		String name = Util.nullToString(request.getParameter("name"));
		String pwd = Util.nullToString(request.getParameter("password"));
		session.setAttribute("user", name);

        logger.info("开始begain");
		ObjectMapper mapper = new ObjectMapper();
		Map result = new HashMap();


		try {
			if("darlen".equalsIgnoreCase(name.trim()) && "1".equalsIgnoreCase(pwd.trim()) ) {
				result.put("success",true);
				result.put("user",name);
//				request.getRequestDispatcher("index.jsp").forward(request,response);
//				return;
			}else {
				result.put("success",false);
//				request.getRequestDispatcher("Login.jsp").forward(request,response);
//				return;
			}
		} finally {
            logger.info("结束end");
			String out =mapper.writeValueAsString(result);
			PrintWriter pw =  response.getWriter();
			pw.print(out);
		}




	}
}
