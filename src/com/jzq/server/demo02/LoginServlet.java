package com.jzq.server.demo02;

public class LoginServlet extends Servlet{

	@Override
	public void doGet(Request req,Response rep) throws Exception {
		String name=req.getParameter("uname");
		String pwd=req.getParameter("pwd");
		if(login(name,pwd)) {
			rep.println("µÇÂ¼³É¹¦");
		}else {
			rep.println("µÇÂ¼Ê§°Ü");
		}
//		rep.println("<html><head><title>»¶Ó­»ØÀ´</title>");
//		rep.println("</head><body>");
//		rep.println("»¶Ó­£º").println(req.getParameter("uname")).println("»ØÀ´");
//		rep.println("</body></html>");
	}
	public boolean login(String name,String pwd) {
		return name.equals("jzq")&&pwd.equals("123456");
	}
	@Override
	public void doPost(Request req,Response rep) throws Exception {
		String name=req.getParameter("uname");
		String pwd=req.getParameter("pwd");
		if(login(name,pwd)) {
			rep.println("µÇÂ¼³É¹¦");
		}else {
			rep.println("µÇÂ¼Ê§°Ü");
		}
	}

}
