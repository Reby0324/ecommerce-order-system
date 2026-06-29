package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ProductsDao;
import dao.impl.ProductsDaoImpl;
import entity.Products;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;

public class AdminProductsUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField id;
	private JTextField category;
	private JTextField stock;
	private JTextField product_name;
	private JTextField price;
	private JTextField description;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminProductsUI frame = new AdminProductsUI();
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
	public AdminProductsUI() {
		setTitle("電商購物系統 - 後台商品管理");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 866, 609);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("商品ID :");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		lblNewLabel.setBounds(66, 31, 73, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("分類 :");
		lblNewLabel_1.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(66, 89, 46, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("庫存 :");
		lblNewLabel_2.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(66, 141, 46, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("商品名稱 :");
		lblNewLabel_3.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(449, 34, 73, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("價格 :");
		lblNewLabel_4.setFont(new Font("微軟正黑體", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(449, 86, 46, 21);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("商品描述 :");
		lblNewLabel_5.setFont(new Font("微軟正黑體 Light", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(449, 138, 98, 21);
		contentPane.add(lblNewLabel_5);
		
		id = new JTextField();
		id.setBounds(136, 31, 200, 21);
		contentPane.add(id);
		id.setColumns(10);
		
		category = new JTextField();
		category.setBounds(136, 86, 200, 21);
		contentPane.add(category);
		category.setColumns(10);
		
		stock = new JTextField();
		stock.setBounds(136, 138, 200, 21);
		contentPane.add(stock);
		stock.setColumns(10);
		
		product_name = new JTextField();
		product_name.setBounds(574, 31, 200, 21);
		contentPane.add(product_name);
		product_name.setColumns(10);
		
		price = new JTextField();
		price.setBounds(574, 86, 200, 21);
		contentPane.add(price);
		price.setColumns(10);
		
		description = new JTextField();
		description.setBounds(574, 138, 200, 21);
		contentPane.add(description);
		description.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 182, 830, 310);
		contentPane.add(scrollPane);
		
		//==============================載入商品Table===============================
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		loadProductsTable();
		

		// =======================點選表格某列資料，帶入上方欄位 這是請AI幫忙寫的=======================
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();

				if (row != -1) {
					id.setText(table.getValueAt(row, 0).toString());
					product_name.setText(table.getValueAt(row, 1).toString());
					category.setText(table.getValueAt(row, 2).toString());
					price.setText(table.getValueAt(row, 3).toString());
					stock.setText(table.getValueAt(row, 4).toString());

					Object des = table.getValueAt(row, 5);
					if (des != null) {
						description.setText(des.toString());
					} else {
						description.setText("");
					}
				}
			}
		});

		
		//==============================event===============================
		
		//新增商品
		JButton addButton = new JButton("新增商品");
		addButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addProduct();
			}
		});
		addButton.setBounds(10, 523, 87, 23);
		contentPane.add(addButton);
		
		
		//修改商品
		JButton updateButton = new JButton("修改商品");
		updateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateProducts();
			}
		});
		updateButton.setBounds(136, 523, 87, 23);
		contentPane.add(updateButton);
		
		
		//整理清單
		JButton refreshButton = new JButton("整理清單");
		refreshButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loadProductsTable();
				clear();
			}
		});
		refreshButton.setBounds(272, 523, 87, 23);
		contentPane.add(refreshButton);
		
		
		
		JButton deleteButton = new JButton("刪除商品");
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				deleteProducts();
			}
		});
		deleteButton.setBounds(397, 523, 87, 23);
		contentPane.add(deleteButton);
		
		
		
		//返回購物
		JButton backButton = new JButton("返回購物");
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ShopUI shopui=new ShopUI();
				shopui.setVisible(true);
				dispose();
			}
		});
		backButton.setBounds(753, 523, 87, 23);
		contentPane.add(backButton);
		
		}
	
        private ProductsDao pdi=new ProductsDaoImpl();
	
	   //==============================以下private是詢問AI=============================
	    private void loadProductsTable() {
		    table.setModel(pdi.findAllProductsTable());
	};
	    //清空欄位
	    private void clear() {
		    id.setText("");
		    product_name.setText("");
		    category.setText("");
		    price.setText("");
		    stock.setText("");
		    description.setText("");
	}
	
	    //新增商品
	    private void addProduct() {
		  try {
			 Products products = new Products(
			 product_name.getText(),
			 category.getText(),
			 new BigDecimal(price.getText()),
			 Integer.parseInt(stock.getText()),
			 description.getText()
			 );

			boolean result = pdi.add(products);

			if (result) {
				JOptionPane.showMessageDialog(null, "新增成功");
				loadProductsTable();
				clear();
			} else {
				JOptionPane.showMessageDialog(null, "新增失敗");
			}

		    } catch (Exception e) {
			    JOptionPane.showMessageDialog(null, "請確認價格與庫存是否輸入正確");
		    }
	}
	    //修改商品
	    private void updateProducts() {
		  try {
		     Products products=new Products();
		
		     products.setId(Integer.parseInt(id.getText()));
		     products.setProduct_name(product_name.getText());
		     products.setCategory(category.getText());
		     products.setPrice(new BigDecimal(price.getText()));
		     products.setStock(Integer.parseInt(stock.getText()));
		     products.setDescription(description.getText());
		
		  boolean result=pdi.update(products);
		  if(result) {
			 JOptionPane.showMessageDialog(null, "修改成功");
			 loadProductsTable();
			 clear();
			 
		  }else {	
			 JOptionPane.showMessageDialog(null, "修改失敗");
		  }
		 
		  }catch(Exception e) {
		     JOptionPane.showMessageDialog(null, "修改失敗");
		  }
	    }
		  
	    //刪除商品
	     private void deleteProducts() {
				if (id.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "請先點選要刪除的商品");
					return;
				}

				int check = JOptionPane.showConfirmDialog(
						null,
						"確定要刪除此商品嗎？",
						"刪除確認",
						JOptionPane.YES_NO_OPTION
				);

				if (check == JOptionPane.YES_OPTION) {
					Integer productId = Integer.parseInt(id.getText());

					boolean result = pdi.deleteById(productId);

					if (result) {
						JOptionPane.showMessageDialog(null, "刪除成功");
						loadProductsTable();
						clear();
					} else {
						JOptionPane.showMessageDialog(null, "刪除失敗");
					}
			}
	   }
	}
	

