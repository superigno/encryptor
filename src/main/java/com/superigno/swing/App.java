package com.superigno.swing;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.superigno.util.Encryptor;

public class App extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField key;
	private JTextField text;
	private JTextField value;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App dialog = new App();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public App() {
		
		Image img = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/key.png"));
		
		setIconImage(img);
		setTitle("Encryptor");
		setBounds(100, 100, 383, 187);
		getContentPane().setLayout(null);
		
		JLabel lblKey = new JLabel("   Key:");
		lblKey.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblKey.setBounds(33, 25, 46, 14);
		getContentPane().add(lblKey);
		
		JLabel lblText = new JLabel("  Text:");
		lblText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblText.setBounds(33, 50, 46, 14);
		getContentPane().add(lblText);
		
		key = new JTextField();
		key.setBounds(82, 22, 249, 20);
		getContentPane().add(key);
		key.setColumns(10);
		
		text = new JTextField();
		text.setColumns(10);
		text.setBounds(82, 47, 249, 20);
		getContentPane().add(text);
		
		JLabel lblValue = new JLabel("Result:");
		lblValue.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblValue.setBounds(33, 75, 46, 14);
		getContentPane().add(lblValue);
		
		value = new JTextField();
		value.setEditable(false);
		value.setColumns(10);
		value.setBounds(82, 75, 249, 20);
		getContentPane().add(value);
		
		JButton btnEncrypt = new JButton("Encrypt");
		btnEncrypt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		final JButton btnCopy = new JButton("Copy");
		btnCopy.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCopy.setEnabled(false);
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(key.getText().trim().equals("")){
					JOptionPane.showMessageDialog(getContentPane(), "Key is required.", "", JOptionPane.ERROR_MESSAGE);
				}else{
					Encryptor en = new Encryptor();
					try {
						value.setText(en.encrypt(key.getText(), text.getText()));
						btnCopy.setEnabled(true);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(getContentPane(), "Unable to encrypt.", "", JOptionPane.INFORMATION_MESSAGE);
						e1.printStackTrace();
					}
				}
			}
		});
		btnEncrypt.setBounds(82, 106, 89, 23);
		getContentPane().add(btnEncrypt);
		
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection (value.getText());
				Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
				clpbrd.setContents (stringSelection, null);
				JOptionPane.showMessageDialog(getContentPane(), "Result copied to clipboard!", "", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnCopy.setBounds(181, 106, 70, 23);
		getContentPane().add(btnCopy);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				key.setText("");
				text.setText("");
				value.setText("");
				btnCopy.setEnabled(false);
			}
		});
		btnClear.setBounds(261, 106, 70, 23);
		getContentPane().add(btnClear);

	}
}
