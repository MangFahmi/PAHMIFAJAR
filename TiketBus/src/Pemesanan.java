import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

public class Pemesanan {

	private JFrame frame;
	private JTable table;
	private JTextField nama;
	private JTextField nohp;
	private JComboBox<Object>  tujuan;
	private JTextField tgl;
	private JTextField jemput;
	private JTextField ongkos;
	private DefaultTableModel model;
	private JButton pesan;
	private JButton edit;
	private JButton hapus;
	
	
	int row;
	
	// menggunakan data struktur arraylist untuk menyimpan data pesanan tiket
	ArrayList<Tiket> tiket = new ArrayList<Tiket>();
	
	//Fungsi menampilkan data dari arraylist ke tabel
	//menggunakan data struktur array untuk dimasukkan ke dalam tabel
	public void tampilkanData() {
		model.setRowCount(0);
		for (int i=0; i<tiket.size(); i++) {
			Object [] data = {tiket.get(i).nama, tiket.get(i).nohp, tiket.get(i).tujuan, tiket.get(i).ongkos, tiket.get(i).tgl, tiket.get(i).jemput};
			model.addRow(data);
		}
		
	}

	/**
	 * Main method untuk menjalankan aplikasi
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pemesanan window = new Pemesanan();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Aplikasinya
	 */
	public Pemesanan() {
		initialize();
	}

	/**
	 * Kodingan membentuk frame GUI dan fungsi-fungsinya
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(70, 130, 180));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Pemesanan.class.getResource("/foto/bus.png")));
		frame.setResizable(false);
		frame.setTitle("TIKET BUS");
		frame.setBounds(100, 100, 649, 511);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		hapus = new JButton("Hapus");
		
		//Fungsi tombol hapus untuk menghapus data tiket pesanan
		hapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Hapus Data","Delete this row?",JOptionPane.YES_NO_OPTION);
				int row=table.getSelectedRow();
			 	if (row>=0 && confirm == 0) {
			 		model.removeRow(row);
			 		JOptionPane.showMessageDialog(null, "Data Sudah Terhapus");
			 		nama.setText("");
					nohp.setText("");
					jemput.setText("");
					tgl.setText("");
					tujuan.setSelectedIndex(0);
					ongkos.setText("Rp -");
					nama.setFocusable(true);
					edit.setEnabled(false);
					hapus.setEnabled(false);
					pesan.setEnabled(true);
			 	} else {
			 		JOptionPane.showMessageDialog(null, "Pilih baris yang akan dihapus");
			 	}
			}
		});
		hapus.setEnabled(false);
		hapus.setFocusable(false);
		hapus.setForeground(Color.WHITE);
		hapus.setBackground(Color.DARK_GRAY);
		hapus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hapus.setForeground(new Color(0,0,0));
				hapus.setBackground(new Color(224,255,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hapus.setForeground(Color.WHITE);
				hapus.setBackground(Color.DARK_GRAY);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				hapus.setForeground(new Color(0,0,0));
				hapus.setBackground(new Color(224,255,255));
			}
		});
		
		JButton clear = new JButton("Clear");
		
		//Fungsi tombol clear untuk menghapus form
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nama.setText("");
				tgl.setText("");
				nohp.setText("");
				jemput.setText("");
				ongkos.setText("Rp -");
				tujuan.setSelectedIndex(0);
			}
		});
		clear.setFocusable(false);
		clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				clear.setForeground(new Color(0,0,0));
				clear.setBackground(new Color(224,255,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				clear.setForeground(Color.WHITE);
				clear.setBackground(Color.DARK_GRAY);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				clear.setForeground(new Color(0,0,0));
				clear.setBackground(new Color(224,255,255));
			}
		});
		clear.setForeground(Color.WHITE);
		clear.setFont(new Font("Tahoma", Font.BOLD, 14));
		clear.setFocusable(false);
		clear.setBackground(Color.DARK_GRAY);
		clear.setBounds(489, 11, 115, 30);
		frame.getContentPane().add(clear);
		hapus.setFont(new Font("Tahoma", Font.BOLD, 14));
		hapus.setBounds(445, 254, 159, 30);
		frame.getContentPane().add(hapus);
		
		edit = new JButton("Edit");
		edit.setEnabled(false);
		
		//Fungsi tombol edit untuk mengedit data
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(edit, "Lanjut Menyimpan?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
				if (option == 0) {
					if (nama.getText().equals("")) {
						JOptionPane.showMessageDialog(edit, "Nama tidak boleh kosong!");
					} else if (nohp.getText().equals("")) {
						JOptionPane.showMessageDialog(edit, "No HP tidak boleh kosong!");
					} else if (tujuan.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(edit, "Tujuan tidak boleh kosong!");
					} else if (tgl.getText().equals("")) {
						JOptionPane.showMessageDialog(edit, "Tanggal berangkat tidak boleh kosong!");
					} else if (jemput.getText().equals("")) {
						JOptionPane.showMessageDialog(edit, "Titik jemput tidak boleh kosong!");
					}  else {
						tiket.get(row).nama=nama.getText();
						tiket.get(row).nohp=nohp.getText();
						tiket.get(row).tujuan=String.valueOf(tujuan.getSelectedItem());
						tiket.get(row).tgl=tgl.getText();
						tiket.get(row).jemput=jemput.getText();
						tiket.get(row).ongkos=ongkos.getText();
						
						JOptionPane.showMessageDialog(edit, "Data di update");
						tampilkanData();
						
						nama.setText("");
						tgl.setText("");
						nohp.setText("");
						jemput.setText("");
						ongkos.setText("Rp -");
						tujuan.setSelectedIndex(0);
						nama.setFocusable(true);
						pesan.setEnabled(true);
						edit.setEnabled(false);
						hapus.setEnabled(false);
					}
				}
			}
		});
		
		edit.setFocusable(false);
		edit.setForeground(Color.WHITE);
		edit.setBackground(Color.DARK_GRAY);
		edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				edit.setForeground(new Color(0,0,0));
				edit.setBackground(new Color(224,255,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				edit.setForeground(Color.WHITE);
				edit.setBackground(Color.DARK_GRAY);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				edit.setForeground(new Color(0,0,0));
				edit.setBackground(new Color(224,255,255));
			}
		});
		edit.setFont(new Font("Tahoma", Font.BOLD, 14));
		edit.setBounds(37, 254, 159, 30);
		frame.getContentPane().add(edit);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Ongkos");
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1_1_1.setBounds(37, 204, 104, 22);
		frame.getContentPane().add(lblNewLabel_2_1_1_1);
		
		ongkos = new JTextField();
		ongkos.setEditable(false);
		ongkos.setText("Rp -");
		ongkos.setBounds(151, 204, 86, 20);
		frame.getContentPane().add(ongkos);
		ongkos.setColumns(10);
		
		pesan = new JButton("Pesan");
		
		//Fungsi tombol pesan untuk memesan dan datanya akan disimpan pada tabel
		pesan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tiket.add(new Tiket(nama.getText(),nohp.getText(),String.valueOf(tujuan.getSelectedItem()),String.valueOf(ongkos.getText()),tgl.getText(),jemput.getText()));
				model = (DefaultTableModel)table.getModel();
				int option = JOptionPane.showConfirmDialog(pesan, "Lanjut Menyimpan?", "Konfirmasi", JOptionPane.YES_NO_OPTION);

			    if (option == 0) {
			    	if (nama.getText().equals("")) {
						JOptionPane.showMessageDialog(pesan, "Nama tidak boleh kosong!");
					} else if (nohp.getText().equals("")) {
						JOptionPane.showMessageDialog(pesan, "No HP tidak boleh kosong!");
					} else if (tujuan.getSelectedIndex() == 0) {
						JOptionPane.showMessageDialog(pesan, "Pilih tujuan terlebih dahulu!");
					}else if (tgl.getText().equals("")) {
						JOptionPane.showMessageDialog(pesan, "Tanggal Berangkat tidak boleh kosong!");
					} else if (jemput.getText().equals("")) {
						JOptionPane.showMessageDialog(pesan, "Titik Jemput tidak boleh kosong!");
					} else {
						tampilkanData(); 
						JOptionPane.showMessageDialog(pesan, "Data Pemesanan Tersimpan");
						nama.setText("");
						tgl.setText("");
						nohp.setText("");
						jemput.setText("");
						ongkos.setText("");
						tujuan.setSelectedIndex(0);
					}
			    }
			}
		});
		pesan.setFocusable(false);
		pesan.setForeground(Color.WHITE);
		pesan.setBackground(Color.DARK_GRAY);
		pesan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pesan.setForeground(new Color(0,0,0));
				pesan.setBackground(new Color(224,255,255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pesan.setForeground(Color.WHITE);
				pesan.setBackground(Color.DARK_GRAY);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				pesan.setForeground(new Color(0,0,0));
				pesan.setBackground(new Color(224,255,255));
			}
		});
		pesan.setFont(new Font("Tahoma", Font.BOLD, 14));
		pesan.setBounds(445, 162, 159, 30);
		frame.getContentPane().add(pesan);
		
		tgl = new JTextField();
		tgl.setColumns(10);
		tgl.setBounds(445, 56, 159, 30);
		frame.getContentPane().add(tgl);
		
		JLabel lblNewLabel_2_2 = new JLabel("Tanggal Berangkat");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_2.setBounds(331, 58, 114, 27);
		frame.getContentPane().add(lblNewLabel_2_2);
		
		jemput = new JTextField();
		jemput.setColumns(10);
		jemput.setBounds(445, 108, 159, 30);
		frame.getContentPane().add(jemput);
		
		JLabel lblNewLabel_2_3 = new JLabel("Lokasi Jemput");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_3.setBounds(331, 110, 104, 27);
		frame.getContentPane().add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Tujuan");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1_1.setBounds(37, 166, 104, 27);
		frame.getContentPane().add(lblNewLabel_2_1_1);
		
		tujuan = new JComboBox<Object>();
		tujuan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tujuan.getSelectedIndex() == 1) {
					ongkos.setText("Rp175000");
				} else if (tujuan.getSelectedIndex() == 2) {
					ongkos.setText("Rp125000");
				} else if (tujuan.getSelectedIndex() == 3){
					ongkos.setText("Rp150000");
				} else {
					ongkos.setText("Rp -");
				}
			}
		});
		tujuan.setModel(new DefaultComboBoxModel<Object>(new String[] {"-pilih tujuan-", "Pontianak - Sintang", "Pontianak - Sambas", "Pontianak - Sandae"}));
		tujuan.setBounds(151, 166, 159, 27);
		frame.getContentPane().add(tujuan);
		
		JLabel lblNewLabel_2_1 = new JLabel("Nomor HP");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1.setBounds(37, 111, 104, 27);
		frame.getContentPane().add(lblNewLabel_2_1);
		
		nohp = new JTextField();
		nohp.setColumns(10);
		nohp.setBounds(151, 109, 159, 30);
		frame.getContentPane().add(nohp);
		
		nama = new JTextField();
		nama.setBounds(151, 57, 159, 30);
		frame.getContentPane().add(nama);
		nama.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Nama Pemesan");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(37, 59, 104, 27);
		frame.getContentPane().add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 294, 610, 162);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nama Pemesan", "Nomor HP", "Tujuan", "Ongkos", "Tanggal Keberangkatan", "Lokasi Jemput" 
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, Long.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		//Fungsi ketika klik tabel maka akan mengirim data ke form lagi, dan akan mengaktifkan tombol edit dan hapus
		//dan menonaktifkan tombol pesan
		//ketika sudah melakukan edit / hapus, tombol pesan akan aktif dan tombol edit/hapus akan non aktif
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pesan.setEnabled(false);
				edit.setEnabled(true);
				hapus.setEnabled(true);
				int row = table.rowAtPoint(e.getPoint());
				
				nama.setText(table.getValueAt(row, 0).toString());
				nohp.setText(table.getValueAt(row, 1).toString());
				tujuan.setSelectedItem(table.getValueAt(row, 2).toString());
				ongkos.setText(table.getValueAt(row, 3).toString());
				tgl.setText(table.getValueAt(row, 4).toString());
				jemput.setText(table.getValueAt(row, 5).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBounds(201, 11, 210, 30);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("PEMESANAN TIKET BUS");
		lblNewLabel_1.setBounds(0, 0, 210, 30);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Pemesanan.class.getResource("/foto/backgr.jpg")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 649, 500);
		frame.getContentPane().add(lblNewLabel);
	}
}
