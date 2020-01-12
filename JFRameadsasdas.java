import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class JFRameadsasdas extends JFrame {
	
	public JFRameadsasdas() {
		setSize(400, 400);
		
		
		JPanel jPanel = new JPanel();
		jPanel.setBounds(0, 0, 400, 400);
		jPanel.setLayout(null);
		
		JTextField txtFieldSubjecTextField = new JTextField("SUBJECT");
		txtFieldSubjecTextField.setBounds(20, 20, 360, 30);
		jPanel.add(txtFieldSubjecTextField);
		
		JTextField txtFieldHomeworkField = new JTextField("HMWK");
		txtFieldHomeworkField.setBounds(20,60,360,30);
		jPanel.add(txtFieldHomeworkField);
		
		JLabel lblQRCodeIconJLabel = new JLabel();
		lblQRCodeIconJLabel.setBounds(100,130,200,200);
		jPanel.add(lblQRCodeIconJLabel);
		
		JButton btnGenerateQRCodeButton = new JButton("GENERATE QR CODE");
		btnGenerateQRCodeButton.setBounds(20,100,360,30);
		jPanel.add(btnGenerateQRCodeButton);
		
		btnGenerateQRCodeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				QRCodeWriter qrCodeWriter = new QRCodeWriter();
				
				BitMatrix bitMatrix = null;
				try {
					bitMatrix = qrCodeWriter.encode(txtFieldSubjecTextField.getText() + "-" + txtFieldHomeworkField.getText(), BarcodeFormat.QR_CODE, 200,200);
				} catch (WriterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				
				try {
					MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
					
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				byte[] byteArray = byteArrayOutputStream.toByteArray();
				
				ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
				
				
				
				try {
					
					lblQRCodeIconJLabel.setIcon(new ImageIcon(byteArray));
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		add(jPanel);
		setVisible(true);
		
	}

}
