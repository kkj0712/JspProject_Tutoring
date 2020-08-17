package com.wclass.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wmember.model.WCartDAO;
import com.wmember.model.WCartDTO;

/**
 * Servlet implementation class WCartInsert
 */
@WebServlet("/class/cartInsert")
public class WCartInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WCartInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd=request.getRequestDispatcher("courseDetail.jsp");
        rd.forward(request, response);
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        WCartDTO cart=new WCartDTO();
        cart.setClassname(request.getParameter("classname"));
        cart.setClassnum(Integer.parseInt(request.getParameter("classnum")));
        cart.setUserid(request.getParameter("userid"));
        WCartDAO dao=WCartDAO.getInstance();
        int flag=dao.cartInsert(cart);
        PrintWriter out = response.getWriter();
        out.println(flag);
    }
}
