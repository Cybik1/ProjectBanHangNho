package dao;


import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Date;

import bean.HangBean;


public class HangDao {
	private ArrayList<HangBean> ds = new ArrayList<HangBean>();
	public ArrayList<HangBean> getds() throws Exception {
		//b1: Ketnoi
		KetNoiDao kn = new KetNoiDao();
		kn.ketnoi();
		//b2: Ket noi cau lenh sql
		String sql = "select * from Hang";
		//b3: Tao cau lenh PreparedStatement
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		//b4: Thuc hien cau lenh
		ResultSet rs = cmd.executeQuery();
		//b5: Duyet qua rs
		while(rs.next()) {
			String mahang = rs.getString("mahang");
			String tenhang = rs.getString("tenhang");
			Date ngaynhap = rs.getDate("ngaynhap");
			int soluong = rs.getInt("soluong");
			Double gia = rs.getDouble("gia");
			
			HangBean h = new HangBean(mahang, tenhang, ngaynhap, soluong, gia);
			ds.add(h);
		}
		rs.close();
		return ds;
	}
	
	public int them(String mahang, String tenhang, Date ngaynhap, int soluong, Double gia) throws Exception {
		//b1: Ketnoi
		KetNoiDao kn = new KetNoiDao();
		kn.ketnoi();
		//b2: Ket noi cau lenh sql
		String sql = "insert into Hang values(?, ? , ? , ? , ?)";
		//b3: Tao cau lenh PreparedStatement
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, mahang);
		cmd.setString(2, tenhang);
		cmd.setDate(3, new java.sql.Date(ngaynhap.getTime()));
		cmd.setInt(4, soluong);
		cmd.setDouble(5, gia);
		//b4: Thuc hien cau lenh
		int rs = cmd.executeUpdate();
		return rs;
		
	}
	
	public int xoa(String mahang)  throws Exception {
		//b1: Ketnoi
		KetNoiDao kn = new KetNoiDao();
		kn.ketnoi();
		//b2: Ket noi cau lenh sql
		String sql = "delete from Hang where mahang = ?;";
		//b3: Tao cau lenh PreparedStatement
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, mahang);
		//b4: Thuc hien cau lenh
		int rs = cmd.executeUpdate();
		return rs;
	}
	
	public int xoatatcasql()  throws Exception {
		//b1: Ketnoi
		KetNoiDao kn = new KetNoiDao();
		kn.ketnoi();
		//b2: Ket noi cau lenh sql
		String sql = "delete from Hang;";
		//b3: Tao cau lenh PreparedStatement
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		//b4: Thuc hien cau lenh
		int rs = cmd.executeUpdate();
		return rs;
	}
	
	public int chinhsua(String mahang, String tenhang, Date ngaynhap, int soluong, Double gia) throws Exception {
		//b1: Ketnoi
		KetNoiDao kn = new KetNoiDao();
		kn.ketnoi();
		//b2: Ket noi cau lenh sql
		String sql = "update Hang\r\n"
				+ "set tenhang = ? , ngaynhap = ?, soluong = ?, gia = ?\r\n"
				+ "where mahang = ?;";
		//b3: Tao cau lenh PreparedStatement
		PreparedStatement cmd = kn.cn.prepareStatement(sql);
		cmd.setString(1, tenhang);
		cmd.setDate(2, new java.sql.Date(ngaynhap.getTime()));
		cmd.setInt(3, soluong);
		cmd.setDouble(4, gia);
		cmd.setString(5, mahang);
		//b4: Thuc hien cau lenh
		int rs = cmd.executeUpdate();
		return rs;
	}
	
	public void luuVaoFile(String tenfile) throws Exception{
		FileWriter f = new FileWriter(tenfile);
		PrintWriter pw = new PrintWriter(f);
		for(HangBean h : ds) {
			pw.println(h.toString());
		}
		pw.close();
	}
	
	public void luuHoaDon(String tenfile, String s) throws Exception{
		FileWriter f = new FileWriter(tenfile, true);
		PrintWriter pw = new PrintWriter(f);
		pw.println(s);
		pw.close();
	}
	
}
