package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entity.Users;
import service.UsersService;
import service.impl.UsersServiceImpl;
import util.Tool;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 866, 525);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(239, 239, 239));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("電商系統登入");
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 40));
		lblNewLabel.setBounds(84, 192, 282, 92);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(246, 246, 246));
		panel.setBounds(401, 52, 404, 383);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("使用者登入");
		lblNewLabel_1.setFont(new Font("微軟正黑體", Font.BOLD, 20));
		lblNewLabel_1.setBounds(140, 56, 108, 28);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("帳號 :");
		lblNewLabel_2.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel_2.setBounds(59, 131, 69, 21);
		panel.add(lblNewLabel_2);
		
		username = new JTextField();
		username.setBounds(140, 131, 191, 21);
		panel.add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("密碼 :");
		lblNewLabel_3.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		lblNewLabel_3.setBounds(59, 209, 46, 15);
		panel.add(lblNewLabel_3);
		
		password = new JTextField();
		password.setBounds(140, 203, 191, 21);
		panel.add(password);
		password.setColumns(10);
		
		
		//=======================event================================
		
		UsersService usi=new UsersServiceImpl();
		
		
		//立即登入
		JButton btnNewButton = new JButton("立即登入");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			String Username = username.getText();
			String Password = password.getText();

			
			if(Username.isEmpty() || Password.isEmpty()) 
			{
				JOptionPane.showMessageDialog(null, "請輸入帳號與密碼");
				return;
			}
			Users users=usi.login(Username, Password);
			if(users !=null) { //登入成功-->存檔
				Tool.saveFile("users.txt", users);
				
				LoginSuccess loginSuccess=new LoginSuccess();
				loginSuccess.setVisible(true);
				dispose();
				
			}else  //登入失敗
			{
				LoginError loginError=new LoginError();
				loginError.setVisible(true);
				dispose();
			}
			}
		});
		btnNewButton.setFont(new Font("微軟正黑體", Font.BOLD, 13));
		btnNewButton.setBounds(59, 292, 108, 23);
		panel.add(btnNewButton);
		
		
		
		JButton btnNewButton_1 = new JButton("註冊帳號");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTextField Username=new JTextField();
				JTextField Password=new JTextField();
				JTextField Email=new JTextField();
				
				Object[] message= {"設定帳號:",Username,"設定密碼 :",Password,"Email :",Email
						
				};
				
				
				int option=JOptionPane.showConfirmDialog(null, message,"使用者註冊",JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String newUsername = Username.getText();
					String newPassword = Password.getText();
					String newEmail = Email.getText();
					
					if (newUsername.isEmpty() || newPassword.isEmpty()) {
						JOptionPane.showMessageDialog(null, "帳號與密碼不可空白");
						return;
					}
					
					// 檢查帳號是否重複
					if (usi.checkUsername(newUsername)) {
						JOptionPane.showMessageDialog(null, "註冊失敗，帳號已存在");
						return;
					}
					
					Users users = new Users(
							newUsername,
							newPassword,
							newEmail,
							"Nomal",
							new BigDecimal("10000.00")
					);
					boolean result=usi.register(users);
					if(result) {
						JOptionPane.showConfirmDialog(null, "註冊成功: \n預設等級:Normal\n預設餘額:1000.00元");
					}else {
						JOptionPane.showConfirmDialog(null, "註冊失敗");
					}
				}
			}
		});
		btnNewButton_1.setBounds(224, 292, 108, 23);
		panel.add(btnNewButton_1);

	}
}
