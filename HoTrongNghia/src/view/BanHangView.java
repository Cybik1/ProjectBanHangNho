package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bean.HangBean;
import bo.HangBo;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.JobAttributes;

import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BanHangView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtgia;
	private JTextField txtsoluongton;
	private JTextField txtnhapsi;
	private JTextField txtthanhtien;
	private JTable table;
	private ArrayList<HangBean> ds;
	private HangBo hbo = new HangBo();
	private ArrayList<String> dsdonhang = new ArrayList<String>();
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	JComboBox cmbhang = new JComboBox();
	private JTextField txtmahang;
	/**
	 * Launch the application.
	 */
	public void NapBang(ArrayList<String> dsdonhang) {
		DefaultTableModel mh = new DefaultTableModel();
		mh.addColumn("Mã Hàng");
		mh.addColumn("Tên Hàng");
		mh.addColumn("Ngày Bán");
		mh.addColumn("Số Lượng");
		mh.addColumn("Thành Tiền");
		for(String s : dsdonhang) {
			String che[] = s.split("[;]");
			mh.addRow(che);
		}
		table.setModel(mh);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BanHangView frame = new BanHangView();
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
	public BanHangView() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					hbo.xoatatcasql();
					ds = hbo.getds();
					hbo.LuuVaoSql("hang.txt");
					for(HangBean h : ds) {
						cmbhang.addItem(h.getMahang() + "  " + h.getTenhang());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			@Override
			public void windowClosing(WindowEvent e) {
				dsdonhang.clear();
				ds.clear();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1356, 802);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		cmbhang.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String th = cmbhang.getSelectedItem().toString();
				for(HangBean h : ds) {
					if(th.toLowerCase().strip().contains(h.getMahang().strip().toLowerCase())) {
						String mh = h.getMahang();
						String sl = Integer.toString(h.getSoluong());
						String g = Double.toString(h.getGia());
						txtmahang.setText(mh);
						txtsoluongton.setText(sl);
						txtgia.setText(g);
						break;
					}
				}
				
			}
		});
		cmbhang.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		
		cmbhang.setBounds(210, 59, 322, 41);
		contentPane.add(cmbhang);
		
		JLabel lblNewLabel = new JLabel("Chọn Hàng");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(53, 61, 112, 41);
		contentPane.add(lblNewLabel);
		
		JLabel lblGi = new JLabel("Giá");
		lblGi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGi.setBounds(53, 183, 112, 41);
		contentPane.add(lblGi);
		
		JLabel lblSLngTn = new JLabel("Số Lượng Tồn");
		lblSLngTn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSLngTn.setBounds(53, 250, 125, 41);
		contentPane.add(lblSLngTn);
		
		JLabel lblNhpS = new JLabel("Nhập Sĩ");
		lblNhpS.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNhpS.setBounds(53, 316, 112, 41);
		contentPane.add(lblNhpS);
		
		JLabel lblThnhTin = new JLabel("Thành Tiền");
		lblThnhTin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblThnhTin.setBounds(53, 375, 112, 41);
		contentPane.add(lblThnhTin);
		
		txtgia = new JTextField();
		txtgia.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtgia.setBounds(210, 181, 322, 41);
		contentPane.add(txtgia);
		txtgia.setColumns(10);
		
		txtsoluongton = new JTextField();
		txtsoluongton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtsoluongton.setColumns(10);
		txtsoluongton.setBounds(210, 248, 322, 41);
		contentPane.add(txtsoluongton);
		
		txtnhapsi = new JTextField();
		txtnhapsi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					Double a = Double.parseDouble(txtgia.getText());
					Long b = Long.parseLong(txtnhapsi.getText());
					Double kq = a * b;
					txtthanhtien.setText(Double.toString(kq));
				}
				if(e.getKeyCode() == 27) {
					System.exit(1);
				}
			}
		});
		txtnhapsi.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtnhapsi.setColumns(10);
		txtnhapsi.setBounds(210, 314, 322, 41);
		contentPane.add(txtnhapsi);
		
		txtthanhtien = new JTextField();
		txtthanhtien.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtthanhtien.setColumns(10);
		txtthanhtien.setBounds(210, 373, 322, 41);
		contentPane.add(txtthanhtien);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(590, 100, 709, 566);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("New tab", null, scrollPane, null);
		
		table = new JTable();
		scrollPane.setViewportView(table);

		
		JLabel lblNewLabel_1 = new JLabel("ĐƠN HÀNG\r\n");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblNewLabel_1.setBounds(855, 43, 153, 47);
		contentPane.add(lblNewLabel_1);
		
		JButton btntdhang = new JButton("Thêm Vào Giỏ Hàng");
		btntdhang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer ns = Integer.parseInt(txtnhapsi.getText());
				Integer sl = Integer.parseInt(txtsoluongton.getText());
				if(ns > sl) {
					JOptionPane.showInternalMessageDialog(null, "Số Lượng Mặt Hàng Muốn Bán Nhiều Hơn Số Lượng Tồn Kho");
					return;
				}
				
				String m = txtmahang.getText();
				for(HangBean h : ds) {
					if(m.strip().equals(h.getMahang().strip())) {
						//Nạp thông tin mặt hàng muốn bán vào bảng đơn hàng
						String th = h.getTenhang();
						Date d = new Date();
						String nb = sdf.format(d);
						String nsi = txtnhapsi.getText();
						String tt = txtthanhtien.getText();
						String s = m + ";" + th + ";" + nb + ";" + nsi + ";" + tt; 
						dsdonhang.add(s);
						NapBang(dsdonhang);
						break;
					}
				}
				
				
			}
		});
		btntdhang.setForeground(new Color(0, 0, 255));
		btntdhang.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btntdhang.setBounds(254, 502, 293, 58);
		contentPane.add(btntdhang);
		
		JButton btntt = new JButton("Thanh Toán");
		btntt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int n = JOptionPane.showConfirmDialog(null, "Xác nhận thanh toán");
				String file = JOptionPane.showInputDialog("Tên File Lưu Hóa Đơn");
				String nm = JOptionPane.showInputDialog("Người Thanh Toán");
				try {
					hbo.luuHoaDon(file, "Nguoi Mua " +  nm + ":");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if(n != 0)
					return;
				for(String d : dsdonhang) {
					String che[] = d.split("[;]");
					String ma = che[0];
					for(HangBean h : ds) {
						if(ma.strip().equals(h.getMahang().strip())) {
							Integer kq = h.getSoluong() - Integer.parseInt(che[3]);
							//Chỉnh sửa số lượng tồn kho của các mặt hàng sau khi đã bán ở sql và jframe
							if(kq >= 0) {
								try {
									hbo.chinhsua(h.getMahang(), h.getTenhang(), h.getNgaynhap(), kq, h.getGia());
								} catch (Exception e1) {
									e1.printStackTrace();
								}
								txtsoluongton.setText(Integer.toString(kq));
								//Lưu các sản phẩm của hóa đơn
								try {
									hbo.luuHoaDon(file, d);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							else {
								JOptionPane.showInternalMessageDialog(null, "Hàng Tồn Kho Không Đủ Để Thanh Toán Hết Các Sản Phẩm Trong Đơn Hàng.\n Chỉ Thanh Toán Những Sản Phẩm Trong Đơn Hàng Có Đủ Hàng Tồn Kho");
							}
						}
					}
				}
				dsdonhang.clear();
				NapBang(dsdonhang);
				n = JOptionPane.showConfirmDialog(null, "Bạn có muốn cập nhật dữ liệu hàng tồn kho ở file hang.txt không?");
				if(n == 0) {
					try {
						hbo.luuVaoFile("hang.txt");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btntt.setForeground(new Color(0, 0, 255));
		btntt.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btntt.setBounds(254, 629, 293, 58);
		contentPane.add(btntt);
		
		txtmahang = new JTextField();
		txtmahang.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtmahang.setColumns(10);
		txtmahang.setBounds(210, 119, 322, 41);
		contentPane.add(txtmahang);
		
		JLabel lblMHng = new JLabel("Mã Hàng");
		lblMHng.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMHng.setBounds(53, 119, 112, 41);
		contentPane.add(lblMHng);
		
		JButton btnxoasp = new JButton("Xóa Sản Phẩm");
		btnxoasp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> a = new ArrayList<String>();
				String mahang = JOptionPane.showInputDialog("Nhập mã mặt hàng");
				if(mahang == null) return;
				for(String s : dsdonhang) {
					String che = s.split("[;]")[0];
					if(mahang.strip().equals(che.strip())) {
						a.add(s);
					}
				}
				for(String s : a) {
					dsdonhang.remove(s);
				}
				NapBang(dsdonhang);
			}
		});
		btnxoasp.setForeground(Color.BLUE);
		btnxoasp.setFont(new Font("Tahoma", Font.PLAIN, 28));
		btnxoasp.setBounds(812, 681, 293, 58);
		contentPane.add(btnxoasp);
	}
}
