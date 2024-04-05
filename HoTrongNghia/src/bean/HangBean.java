package bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HangBean {
	private String mahang;
	private String tenhang;
	private Date ngaynhap;
	private int soluong;
	private double gia;
	public HangBean(String mahang, String tenhang, Date ngaynhap, int soluong, double gia) {
		super();
		this.mahang = mahang;
		this.tenhang = tenhang;
		this.ngaynhap = ngaynhap;
		this.soluong = soluong;
		this.gia = gia;
	}
	public String getMahang() {
		return mahang;
	}
	public void setMahang(String mahang) {
		this.mahang = mahang;
	}
	public String getTenhang() {
		return tenhang;
	}
	public void setTenhang(String tenhang) {
		this.tenhang = tenhang;
	}
	public Date getNgaynhap() {
		return ngaynhap;
	}
	public void setNgaynhap(Date ngaynhap) {
		this.ngaynhap = ngaynhap;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return this.mahang + ";" + this.tenhang + ";" + sdf.format(ngaynhap) + ";" + this.soluong + ";" + (int)this.gia * 100 / 100.0;
	}
	
}
