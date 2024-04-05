package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bean.HangBean;
import bo.HangBo;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.JobAttributes;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HangView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textmahang;
	private JTextField texttenhang;
	private JTextField textngaynhap;
	private JTextField textsoluong;
	private JTextField textgia;
	private ArrayList<HangBean> ds;
	private ArrayList<HangBean> tam;
	private HangBo hbo = new HangBo();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	/**
	 * Launch the application.
	 */
	public void NapBang(ArrayList<HangBean> ds) {
		DefaultTableModel mh = new DefaultTableModel();
		mh.addColumn("Mã Hàng");
		mh.addColumn("Tên Hàng");
		mh.addColumn("Ngày Nhập Hàng");
		mh.addColumn("Số Lượng");
		mh.addColumn("Đơn Giá");
		for(HangBean h : ds) {
			String m = h.getMahang();
			String th = h.getTenhang();
			String nn = sdf.format(h.getNgaynhap());
			String sl = Integer.toString(h.getSoluong());
			String dg = Double.toString(h.getGia());
			mh.addRow(new Object[] { m, th, nn, sl, dg});
		}
		table.setModel(mh);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HangView frame = new HangView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HangView() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					hbo.xoatatcasql();
					ds = hbo.getds();
					hbo.LuuVaoSql("hang.txt");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				NapBang(ds);
			}
			@Override
			public void windowClosing(WindowEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "Bạn có muốn lưu dữ liệu vào file hang.txt không?");
				if(n == 0) {
					try {
						hbo.luuVaoFile("hang.txt");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				ds.clear();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1187, 740);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(48, 329, 1079, 318);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		tabbedPane.addTab("New tab", null, scrollPane, null);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int d = table.getSelectedRow();
				String m = table.getValueAt(d, 0).toString();
				String th = table.getValueAt(d, 1).toString();
				String nn = table.getValueAt(d, 2).toString();
				String sl = table.getValueAt(d, 3).toString();
				String dg = table.getValueAt(d, 4).toString();
				textmahang.setText(m);
				texttenhang.setText(th);
				textngaynhap.setText(nn);
				textsoluong.setText(sl);
				textgia.setText(dg);
			}
		});
		scrollPane.setViewportView(table);

		
		JLabel lblmahang = new JLabel("Mã Hàng");
		lblmahang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblmahang.setBounds(48, 22, 93, 34);
		contentPane.add(lblmahang);
		
		JLabel lbltenhang = new JLabel("Tên Hàng");
		lbltenhang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbltenhang.setBounds(48, 66, 93, 34);
		contentPane.add(lbltenhang);
		
		JLabel lblngaynhap = new JLabel("Ngày Nhập");
		lblngaynhap.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblngaynhap.setBounds(48, 110, 93, 34);
		contentPane.add(lblngaynhap);
		
		JLabel lblsoluong = new JLabel("Số Lượng");
		lblsoluong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblsoluong.setBounds(48, 154, 93, 34);
		contentPane.add(lblsoluong);
		
		JLabel lblgia = new JLabel("Giá");
		lblgia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblgia.setBounds(48, 198, 93, 34);
		contentPane.add(lblgia);
		
		textmahang = new JTextField();
		textmahang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textmahang.setBounds(151, 22, 435, 34);
		contentPane.add(textmahang);
		textmahang.setColumns(10);
		
		texttenhang = new JTextField();
		texttenhang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		texttenhang.setColumns(10);
		texttenhang.setBounds(151, 66, 435, 34);
		contentPane.add(texttenhang);
		
		textngaynhap = new JTextField();
		textngaynhap.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textngaynhap.setColumns(10);
		textngaynhap.setBounds(151, 110, 435, 34);
		contentPane.add(textngaynhap);
		
		textsoluong = new JTextField();
		textsoluong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textsoluong.setColumns(10);
		textsoluong.setBounds(151, 154, 435, 34);
		contentPane.add(textsoluong);
		
		textgia = new JTextField();
		textgia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textgia.setColumns(10);
		textgia.setBounds(151, 198, 435, 34);
		contentPane.add(textgia);
		
		JButton btnthem = new JButton("Thêm");
		btnthem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String mahang = textmahang.getText();
					String tenhang = texttenhang.getText();
					Date ngaynhap = sdf.parse(textngaynhap.getText());
					int soluong = Integer.parseInt(textsoluong.getText());
					Double gia = Double.parseDouble(textgia.getText());
					hbo.them(mahang, tenhang, ngaynhap, soluong, gia);
					NapBang(ds);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				
			}
		});
		btnthem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnthem.setBounds(48, 268, 107, 34);
		contentPane.add(btnthem);
		
		JButton btnsua = new JButton("Sửa");
		btnsua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String mahang = textmahang.getText();
					String tenhang = texttenhang.getText();
					Date ngaynhap = sdf.parse(textngaynhap.getText());
					int soluong = Integer.parseInt(textsoluong.getText());
					Double gia = Double.parseDouble(textgia.getText());
					hbo.chinhsua(mahang, tenhang, ngaynhap, soluong, gia);
					NapBang(ds);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnsua.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnsua.setBounds(190, 268, 107, 34);
		contentPane.add(btnsua);
		
		JButton btnxoa = new JButton("Xóa");
		btnxoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mahang = JOptionPane.showInputDialog("Nhập mã mặt hàng");
				int n = JOptionPane.showConfirmDialog(null, "Chắc chắn xóa?");
				if(n == 0)
				{
					try {
						hbo.xoa(mahang);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				NapBang(ds);
			}
		});
		btnxoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnxoa.setBounds(340, 268, 107, 34);
		contentPane.add(btnxoa);
		
		JButton btnluu = new JButton("Lưu");
		btnluu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tenfile = JOptionPane.showInputDialog("Nhập file để lưu dữ liệu");
				int n = JOptionPane.showConfirmDialog(null, "Chắc chăn lưu dữ liệu vào file " + tenfile + "?");
				if(n == 0) {
					try {
						hbo.luuVaoFile(tenfile);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnluu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnluu.setBounds(648, 268, 107, 34);
		contentPane.add(btnluu);
		
		JButton btnload = new JButton("Load");
		btnload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NapBang(ds);
			}
		});
		btnload.setForeground(new Color(255, 0, 0));
		btnload.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnload.setBounds(744, 23, 107, 34);
		contentPane.add(btnload);
		
		JButton btntimkiem = new JButton("Tìm Kiếm");
		btntimkiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tenHang = JOptionPane.showInputDialog("Nhập tên hàng cần tìm kiếm");
				try {
					tam = hbo.timkiem(tenHang);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				NapBang(tam);
				
			}
		});
		btntimkiem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btntimkiem.setBounds(492, 268, 129, 34);
		contentPane.add(btntimkiem);
	}
}
