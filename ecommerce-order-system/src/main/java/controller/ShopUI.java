package controller;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import dao.ProductsDao;
import dao.impl.ProductsDaoImpl;
import entity.Order_Items;
import entity.Orders;
import entity.Users;
import service.OrdersService;
import service.impl.OrdersServiceImpl;
import util.Tool;

public class ShopUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	private ProductsDao pdi = new ProductsDaoImpl();
	private OrdersService osi = new OrdersServiceImpl();

	private int cartCount = 0;
	private BigDecimal cartTotal = BigDecimal.ZERO;
	private JButton checkoutButton;

	private List<Order_Items> cartList = new ArrayList<>();
	private int lastOrderId = 0;

	Users users = (Users) Tool.readFile("users.txt");

	private void loadProductsTable() {
		table.setModel(pdi.findAllProductsTable());
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopUI frame = new ShopUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ShopUI() {
		setTitle("電商購物系統");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 866, 609);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ======================== 使用者資訊 ========================

		JLabel showUser = new JLabel("");
		showUser.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		showUser.setBounds(40, 21, 200, 20);
		contentPane.add(showUser);

		JLabel showLevel = new JLabel("");
		showLevel.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		showLevel.setBounds(40, 45, 200, 20);
		contentPane.add(showLevel);

		JLabel showBalance = new JLabel("");
		showBalance.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		showBalance.setBounds(40, 65, 190, 20);
		contentPane.add(showBalance);

		if (users != null) {
			showUser.setText(users.getUsername() + "歡迎你");
			showLevel.setText("使用者等級 :" + users.getLevel());
			showBalance.setText("目前餘額 :" + users.getBalance());
		}

		// ======================== 商品表格 ========================

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 101, 767, 382);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		loadProductsTable();

		// ========================= 系統時間 =========================

		JLabel timeLabel = new JLabel("");
		timeLabel.setFont(new Font("微軟正黑體", Font.BOLD, 13));
		timeLabel.setBounds(174, 60, 304, 30);
		contentPane.add(timeLabel);

		DateTimeFormatter now = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		Timer timer = new Timer(1000, e -> timeLabel.setText("系統時間 :" + LocalDateTime.now().format(now)));
		timer.start();

		// ============================= event ===========================

		JButton adminButton = new JButton("進入後台系統");
		adminButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminProductsUI admin = new AdminProductsUI();
				admin.setVisible(true);
				dispose();
			}
		});
		adminButton.setBounds(551, 45, 128, 23);
		contentPane.add(adminButton);

		JButton logoutButton = new JButton("登出系統");
		logoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		logoutButton.setBounds(704, 45, 103, 23);
		contentPane.add(logoutButton);

		// 結帳
		checkoutButton = new JButton("結帳(0)");
		checkoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (cartCount == 0) {
					JOptionPane.showMessageDialog(null, "購物車是空的");
					return;
				}

				Users users = (Users) Tool.readFile("users.txt");

				if (users == null) {
					JOptionPane.showMessageDialog(null, "請先登入");
					return;
				}

				// 1. 新增 orders 訂單主檔
				Orders orders = new Orders();
				orders.setUserId(users.getId());
				orders.setTotalPrice(cartTotal);
				orders.setStatus("已成立");

				int orderId = osi.createOrder(orders);

				if (orderId > 0) {

					// 2. 新增 order_items 訂單明細
					for (Order_Items item : cartList) {
						item.setOrderId(orderId);
						osi.createOrderItem(item);
					}

					lastOrderId = orderId;

					JOptionPane.showMessageDialog(null, "結帳成功，訂單編號：" + orderId);

					// 3. 清空購物車
					cartCount = 0;
					cartTotal = BigDecimal.ZERO;
					cartList.clear();

					checkoutButton.setText("結帳(0)");

				} else {
					JOptionPane.showMessageDialog(null, "結帳失敗");
				}
			}
		});
		checkoutButton.setBounds(693, 512, 114, 23);
		contentPane.add(checkoutButton);

		// 加入購物車
		JButton addCartButton = new JButton("加入購物車");
		addCartButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();

				if (row == -1) {
					JOptionPane.showMessageDialog(null, "請先選擇商品");
					return;
				}

				Integer productId = Integer.parseInt(table.getValueAt(row, 0).toString());
				String productName = table.getValueAt(row, 1).toString();
				BigDecimal productPrice = new BigDecimal(table.getValueAt(row, 3).toString());

				Order_Items item = new Order_Items();
				item.setProductId(productId);
				item.setQuantity(1);
				item.setPrice(productPrice);
				item.setSubtotal(productPrice);

				cartList.add(item);

				cartCount++;
				cartTotal = cartTotal.add(productPrice);

				BigDecimal newBalance = users.getBalance().subtract(cartTotal);
				showBalance.setText("目前餘額 :" + newBalance);

				JOptionPane.showMessageDialog(null, productName + " 已加入購物車");
				checkoutButton.setText("結帳(" + cartCount + ")");
			}
		});
		addCartButton.setBounds(40, 512, 114, 23);
		contentPane.add(addCartButton);

		// 清空購物車
		JButton clearCartButton = new JButton("清空購物車");
		clearCartButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cartCount = 0;
				cartTotal = BigDecimal.ZERO;
				cartList.clear();

				if (users != null) {
					showBalance.setText("目前餘額 :" + users.getBalance());
				}

				checkoutButton.setText("結帳(0)");
				JOptionPane.showMessageDialog(null, "購物車已清空");
			}
		});
		clearCartButton.setBounds(192, 512, 114, 23);
		contentPane.add(clearCartButton);

		// 查看訂單明細
		JButton detailButton = new JButton("查看訂單明細");
		detailButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (lastOrderId == 0) {
					JOptionPane.showMessageDialog(null, "請先完成結帳");
					return;
				}

				OrderDetailUI orderDetailUI = new OrderDetailUI(lastOrderId);
				orderDetailUI.setVisible(true);
			}
		});
		detailButton.setBounds(346, 512, 114, 23);
		contentPane.add(detailButton);
	}
}