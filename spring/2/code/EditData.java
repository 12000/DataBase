package app;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditData extends JFrame {
	
	private String name;
	private String surname;
	private String docNum;
	private String docType;
	
	private User user;
	
	private KModel model;
	private KFDB baza;
	
	public EditData(User u){
		super();
		this.user = u;
		int user_id = user.getId();
		
		baza = new KFDB("org.firebirdsql.jdbc.FBDriver", "jdbc:firebirdsql:localhost/3050:D:TICKET_BASE.FDB", "SYSDBA", "masterkey");
		model = new KModel("PASSENGER");
		
		JLabel lName = new JLabel("Имя");
		lName.setSize(90, 20);
		lName.setLocation(150, 30);
		JLabel lSurname = new JLabel("Фамилия");
		lSurname.setSize(90, 20);
		lSurname.setLocation(150, 60);
		JLabel lDoc = new JLabel("Номер документа");
		lDoc.setSize(160, 20);
		lDoc.setLocation(150, 90);
		JLabel lDocType = new JLabel("Тип документа");
		lDocType.setSize(160, 20);
		lDocType.setLocation(150, 120);
		//-----текстовые поля + кнопка ОК-----------------------------------------
		JTextField textName = new JTextField("", 20);
		textName.setSize(120, 20);
		textName.setLocation(20, 30);
		JTextField textSurname = new JTextField("", 20);
		textSurname.setSize(120, 20);
		textSurname.setLocation(20, 60);
		JTextField textDocNum = new JTextField("", 20);
		textDocNum.setSize(120, 20);
		textDocNum.setLocation(20, 90);
		JTextField textDocType = new JTextField("", 20);
		textDocType.setSize(120, 20);
		textDocType.setLocation(20, 120);
		
		JButton ok = new JButton("Сохранить");
		ok.setSize(200, 60);
		ok.setLocation(50, 150);
		//--------------------------------------------------------------------------------
		//-------панель-------------------------------------------------------------------
		JPanel secondPanel = new JPanel();
        secondPanel.setLayout(null);
        secondPanel.add(textName);
        secondPanel.add(textSurname);
        secondPanel.add(textDocNum);
        secondPanel.add(textDocType);
        secondPanel.add(ok);
        secondPanel.add(lName);
        secondPanel.add(lSurname);
        secondPanel.add(lDoc);
        secondPanel.add(lDocType);
        //--------------------------------------------------------------------------------
        //--------слушатель для кнопки ОК-------------------------------------------------
        ok.addMouseListener(new MouseListener(){
        	public void mouseClicked(MouseEvent event) {
        			Vector<Vector<Object>> tmpVectorForMouseEvent;
        			String comand;
        			int id;
        			
        			name = textName.getText();
        			surname = textSurname.getText();
        			docNum = textDocNum.getText();
        			docType = textDocType.getText();
        			
        			//формирование команды
        			//меняем имя
        			comand = "update passenger set name='" + name +"'" +
        					" where pas_id=" + Integer.toString(user.getId());
        			try {
        				PreparedStatement ps = baza.getPrepareStatement(comand);
        				ps.executeUpdate();
        			} catch (SQLException r) {
        				r.printStackTrace();
        			}
        			//меняем фамилию
        			comand = "update passenger set surname='" + surname +"'" +
        					" where pas_id=" + Integer.toString(user.getId());
        			try {
        				PreparedStatement ps = baza.getPrepareStatement(comand);
        				ps.executeUpdate();
        			} catch (SQLException r) {
        				r.printStackTrace();
        			}
        			//меняем номер документа
        			comand = "update passenger set doc_num='" + docNum +"'" +
        					" where pas_id=" + Integer.toString(user.getId());
        			try {
        				PreparedStatement ps = baza.getPrepareStatement(comand);
        				ps.executeUpdate();
        			} catch (SQLException r) {
        				r.printStackTrace();
        			}
        			//меняем тип документа
        			comand = "update passenger set document='" + docType +"'" +
        					" where pas_id=" + Integer.toString(user.getId());
        			try {
        				PreparedStatement ps = baza.getPrepareStatement(comand);
        				ps.executeUpdate();
        			} catch (SQLException r) {
        				r.printStackTrace();
        			}
        			
        			JOptionPane.showMessageDialog(ok, "Данные изменены!", "Информация", JOptionPane.WARNING_MESSAGE);
        			setVisible(false);
        		}
        		 
        		public void mouseEntered(MouseEvent event) {}
        		 
        		public void mouseExited(MouseEvent event) {}
        		 
        		public void mousePressed(MouseEvent event) {}
        		 
        		public void mouseReleased(MouseEvent event) {}                      		
        			
        });
        //--------------------------------------------------------------------------------       
        getContentPane().add(secondPanel);
        setSize(300, 400);
        setLocationRelativeTo(null);
        setVisible(true);
	}
}
