package com.wmember.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class WCartDAO {
	//디비셋팅
	private static WCartDAO instance=new WCartDAO();
	public static WCartDAO getInstance() {
		return instance;
	}
	private Connection getConnection() throws Exception{
		Context initCtx=new InitialContext();
		Context envCtx=(Context) initCtx.lookup("java:comp/env");
		DataSource ds=(DataSource) envCtx.lookup("jdbc/wiser"); //context.xml의 name을 jdbc/member로 바꾸기
		return ds.getConnection();
	}
	
	//장바구니 추가
	public int cartInsert(WCartDTO vo) {
		Connection con=null;
		PreparedStatement ps=null;
		int flag=0;
		
		try {
			con=getConnection();
			String sql="INSERT INTO wcart(cartnum, classnum, userid, classname) VALUES(wcart_seq.nextval,?,?,?)";
			ps=con.prepareStatement(sql);
			ps.setInt(1, vo.getClassnum());
			ps.setString(2, vo.getUserid());
			ps.setString(3, vo.getClassname());
			flag=ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, ps);
		}
		return flag;
	}
	
	//장바구니 체크
	public int cartCheck(int classnum, String userid) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int flag=-1;
		try {
			con=getConnection();
			String sql="select classnum, classname from wcart where userid='"+userid+"'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			if(rs.next()) { //id 맞음
				if(rs.getInt("classnum")==classnum) { //비번 일치
					flag=1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return flag;
	}
	
	//장바구니 전체보기
	public ArrayList<WCartDTO> cartList(String userid) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		ArrayList<WCartDTO>arr=new ArrayList<WCartDTO>();
		
		try {
			con=getConnection();
			String sql="select * from wcart where userid='"+userid+"'";
			st=con.createStatement();
			rs=st.executeQuery(sql);
			while(rs.next()) {
				WCartDTO dto=new WCartDTO();
				dto.setCartnum(rs.getInt("cartnum"));
				dto.setClassnum(rs.getInt("classnum"));
				dto.setUserid(rs.getString("userid"));
				dto.setClassname(rs.getString("classname"));
				arr.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, rs);
		}
		return arr;
	}
	
	//장바구니에서 삭제
	public void cartDel(int cartnum) {
		Connection con=null;
		Statement st=null;
		try {
			con=getConnection();
			String sql="delete from wcart where cartnum='"+cartnum+"'";
			st=con.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeConnection(con, st, null);
		}
	}
	
	//닫기 closeConnection
	private void closeConnection(Connection con, PreparedStatement ps) {
		try {
			if(ps!=null) ps.close();
			if(con!=null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	private void closeConnection(Connection con, Statement st, ResultSet rs) {
		try {
			if(st!=null) st.close();
			if(con!=null) con.close();
			if(rs!=null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
