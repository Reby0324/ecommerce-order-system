package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Users;
import util.Tool;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginSuccess extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginSuccess frame = new LoginSuccess();
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
	public LoginSuccess() {
		//讀取Login登入成功時存users.txt
		Users users=(Users) Tool.readFile("users.txt");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 286);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Title = new JLabel("電商系統- 登入成功");
		Title.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		Title.setBounds(188, 31, 147, 15);
		contentPane.add(Title);
		
		JLabel lblNewLabel_1 = new JLabel("登入成功");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		lblNewLabel_1.setBounds(222, 67, 58, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel showUser = new JLabel("");
		showUser.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		showUser.setBounds(178, 92, 147, 15);
		contentPane.add(showUser);
		
		JLabel showLevel = new JLabel("");
		showLevel.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		showLevel.setBounds(178, 127, 147, 15);
		contentPane.add(showLevel);
		
		JLabel showBalance = new JLabel("");
		showBalance.setFont(new Font("微軟正黑體", Font.BOLD, 12));
		showBalance.setBounds(178, 165, 147, 15);
		contentPane.add(showBalance);
		
		if(users !=null) {
			showUser.setText(users.getUsername()+"歡迎你");
			showLevel.setText("使用者等級 :"+users.getLevel());
			showBalance.setText("目前餘額 :"+users.getBalance());
		}
		
		
		///========================event===========================
		
		JButton btnNewButton = new JButton("進入購物");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShopUI shopui=new ShopUI();
				  shopui.setVisible(true);
				  dispose();
				
			}
		});
		btnNewButton.setBounds(208, 202, 87, 23);
		contentPane.add(btnNewButton);

	}

}
