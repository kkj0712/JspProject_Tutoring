package com.wmember.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wmember.model.WMemberDAO;
import com.wmember.model.WMemberDTO;

/**
 * Servlet implementation class WMemberAdminViewAction
 */
@WebServlet("/member/Adminview")
public class WMemberAdminViewAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WMemberAdminViewAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String userid=(String)session.getAttribute("userid");
		WMemberDAO dao=WMemberDAO.getInstance();
		WMemberDTO dto=dao.memberView(userid);
		request.setAttribute("member", dto);
		
		RequestDispatcher rd=request.getRequestDispatcher("Adminview.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
