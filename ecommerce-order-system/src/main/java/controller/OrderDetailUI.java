package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.ProductsDao;
import dao.impl.ProductsDaoImpl;
import entity.Order_Items;
import entity.Products;
import service.OrdersService;
import service.impl.OrdersServiceImpl;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;

public class OrderDetailUI extends JFrame {
public OrderDetailUI(Integer orderId) {
	this();
	idField.setText(String.valueOf(orderId));
	loadOrderItemsTable(orderId);
}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private OrdersService osi = new OrdersServiceImpl();
	private ProductsDao pdi = new ProductsDaoImpl();
	private JTextField idField;
	private void loadOrderItemsTable(Integer orderId) {
		List<Order_Items> list = osi.findItemsByOrderId(orderId);

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("明細ID");
		model.addColumn("訂單ID");
		model.addColumn("商品ID");
		model.addColumn("商品名稱");
		model.addColumn("數量");
		model.addColumn("單價");
		model.addColumn("小計");
		for (Order_Items item : list) {
			String productName = "";

			List<Products> productList = pdi.findById(item.getProductId());

			if (!productList.isEmpty()) {
				Products products = productList.get(0);
				productName = products.getProduct_name();
			}

			Object[] row = new Object[] {
					item.getId(),
					item.getOrderId(),
					item.getProductId(),
					productName,
					item.getQuantity(),
					item.getPrice(),
					item.getSubtotal()
			};

			model.addRow(row);
		}

		table.setModel(model);

		if (list.isEmpty()) {
			JOptionPane.showMessageDialog(null, "查無此訂單明細");
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderDetailUI frame = new OrderDetailUI();
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
	public OrderDetailUI() {
		setTitle("訂單明細查詢");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("訂單ID :");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel_1.setBounds(20, 30, 75, 15);
		contentPane.add(lblNewLabel_1);
		
		idField = new JTextField();
		idField.setBounds(95, 27, 115, 21);
		contentPane.add(idField);
		idField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 73, 564, 257);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		
		
		
		//==============================event==============================
		JButton btnNewButton = new JButton("關閉");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShopUI shopui=new ShopUI();
				shopui.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(252, 356, 87, 23);
		contentPane.add(btnNewButton);
		
		
		JButton btnNewButton_1 = new JButton("查詢");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (idField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "請輸入訂單ID");
					return;
				}
				
				try {
					Integer orderId = Integer.parseInt(idField.getText());
					loadOrderItemsTable(orderId);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "訂單ID請輸入數字");
				}
			}
		});
		btnNewButton_1.setBounds(232, 26, 87, 23);
		contentPane.add(btnNewButton_1);

	}
}
