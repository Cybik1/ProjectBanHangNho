package bo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import bean.HangBean;
import dao.HangDao;

public class HangBo {
	private HangDao hdao = new HangDao();
	private ArrayList<HangBean> ds;
	private ArrayList<HangBean> tam = new ArrayList<HangBean>();
	public ArrayList<HangBean> getds() throws Exception {
		ds = hdao.getds();
		return ds;
	}
	public ArrayList<HangBean> getTam(String tenHang) throws Exception {
		timkiem(tenHang);
		return tam;
	}
	public int them(String mahang, String tenhang, Date ngaynhap, int soluong, Double gia) throws Exception {
		for(HangBean h : ds) {
			if(h.getMahang().equals(mahang)) 		
				return 0;
		}
		HangBean h1 = new HangBean(mahang, tenhang, ngaynhap, soluong, soluong);
		ds.add(h1);
		hdao.them(mahang, tenhang, ngaynhap, soluong, gia);
		return 1;
	}
	public int xoa(String mahang)  throws Exception {
		for(HangBean h : ds) {
			if(h.getMahang().equals(mahang)) {
				ds.remove(h);
				hdao.xoa(mahang);
				return 1;
			}
		}
		return 0;
	}
	public int chinhsua(String mahang, String tenhang, Date ngaynhap, int soluong, Double gia) throws Exception {
		for(HangBean h : ds) {
			if(h.getMahang().equals(mahang)) {
				h.setTenhang(tenhang);
				h.setNgaynhap(ngaynhap);
				h.setSoluong(soluong);
				h.setGia(gia);
				hdao.chinhsua(mahang, tenhang, ngaynhap, soluong, gia);
				return 1;
			}
		}
		return 0;
	}
	public void LuuVaoSql(String v) throws Exception {
		FileReader f = new FileReader(v);
		BufferedReader br = new BufferedReader(f);
		while(true) {
			String st = br.readLine();
			if(st == null || st == "") 
				break;
			String[] t = st.split("[;]");
			String mahang = t[0];
			String tenhang = t[1];
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date ngaynhap = sdf.parse(t[2]);
			int soluong = Integer.parseInt(t[3]);
			Double gia = Double.parseDouble(t[4]);
			them(mahang, tenhang, ngaynhap, soluong, gia);
		}
		br.close();
	}
	public ArrayList<HangBean> timkiem(String tenhang) throws Exception {
		for(HangBean h : ds) {
			if(h.getTenhang().toLowerCase().strip().contains(tenhang.strip().toLowerCase())) {
				tam.add(h);
			}
		}
		return tam;
	}
	
	public void luuVaoFile(String tenfile) throws Exception{
		hdao.luuVaoFile(tenfile);
	}
	
	public int xoatatcasql()  throws Exception {
		return hdao.xoatatcasql();
	}
	public void luuHoaDon(String tenfile, String s) throws Exception{
		hdao.luuHoaDon(tenfile, s);
	}
}
