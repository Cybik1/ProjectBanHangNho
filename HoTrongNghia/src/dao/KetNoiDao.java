package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class KetNoiDao {
	public Connection cn;
	public void ketnoi() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerConnection");
		System.out.println("Da xac dinh HQTCSDL");
		cn = DriverManager.getConnection("jdbc:sqlserver://NGHIA\\SQLEXPRESS:1433;databaseName=BaiTap;user=sa;password=123");
		System.out.println("Da ket noi den csdl BaiTap");
	}
}
