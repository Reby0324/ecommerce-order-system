package controller;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginError extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginError frame = new LoginError();
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
	public LoginError() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 541, 286);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Title = new JLabel("電商系統- 登入失敗");
		Title.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		Title.setBounds(190, 36, 122, 20);
		contentPane.add(Title);
		
		JLabel lblNewLabel_1 = new JLabel("登入失敗");
		lblNewLabel_1.setBounds(225, 90, 59, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("帳號或密碼錯誤");
		lblNewLabel.setForeground(new Color(168, 0, 0));
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		lblNewLabel.setBounds(190, 115, 133, 33);
		contentPane.add(lblNewLabel);
		
		
		//========================event==========================
		
		JButton btnNewButton = new JButton("返回登入");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Login login=new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(208, 172, 87, 23);
		contentPane.add(btnNewButton);
		
		

	}

}
