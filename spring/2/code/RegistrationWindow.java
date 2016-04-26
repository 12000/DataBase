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

public class RegistrationWindow extends JFrame {
	
	private String name;
	private String surname;
	private String docNum;
	private String docType;
	
	private KModel model;
	private KFDB baza;
	
	public RegistrationWindow( String s){
		super(s);
		
		baza = new KFDB("org.firebirdsql.jdbc.FBDriver", "jdbc:firebirdsql:localhost/3050:D:TICKET_BASE.FDB", "SYSDBA", "masterkey");
		model = new KModel("PASSENGER");
		//Vector<Vector<Object>> tmpVector;
		
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
		
		JButton ok = new JButton("Зарегистрироваться");
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
        			comand = "SELECT * FROM PASSENGER where name='";
        			comand += name;
        			comand += "'";
        			comand += " and surname='";
        			comand += surname;
        			comand += "'";
        			comand += " and doc_num='";
        			comand += docNum;
        			comand += "'";
        			
        			tmpVectorForMouseEvent = baza.getNomen(comand);
        			try{
        				id = ((Integer)tmpVectorForMouseEvent.get(0).get(0)).intValue();
        				System.out.println("Ok button");
            			System.out.printf("Comand: %s\n", comand);
            			System.out.printf("Name: %s\n", name);
            			System.out.printf("surName: %s\n", surname);
            			System.out.printf("docNum: %s\n", docNum);
            			System.out.printf("docType: %s\n", docType);
            			System.out.printf("id: %d\n", id);
            			System.out.printf("Size: %d\n", tmpVectorForMouseEvent.size()); //если не 0 - то такой уже есть
            			
            			JOptionPane.showMessageDialog(ok, "Неверные данные или такой пользователь уже существует!", "Информация", JOptionPane.WARNING_MESSAGE);
        			}
        			catch(IndexOutOfBoundsException e){
        				//e.printStackTrace();
        				System.out.println("Ok button");
            			System.out.printf("Comand: %s\n", comand);
            			System.out.printf("Name: %s\n", name);
            			System.out.printf("surName: %s\n", surname);
            			System.out.printf("docNum: %s\n", docNum);
            			System.out.printf("docType: %s\n", docType);
            			System.out.printf("Size: %d\n", tmpVectorForMouseEvent.size());
            			
            			//-----формирование id--------------------------------------------
            			int new_id = 1;
            			while(true){
            				try{
            					comand = "SELECT * FROM PASSENGER where pas_id=";
                    			comand += String.valueOf(new_id);
                    			tmpVectorForMouseEvent = baza.getNomen(comand);
                    			System.out.printf("Comand: %s\n", comand);
                    			if(tmpVectorForMouseEvent.size() != 0){
                    				new_id++;
                    			}
                    			else{
                    				System.out.printf("new_id: %d\n", new_id);
                    				break;
                    			}	
            				}
            				catch(IndexOutOfBoundsException e2){
            					System.out.printf("new_id: %d\n", new_id);
            					break;
            				}
            			}
            			
            			//----добавление пользователя---------------------------------------
            			comand = "INSERT INTO PASSENGER (PAS_ID, NAME, SURNAME, DOCUMENT, DOC_NUM) VALUES (";
            			comand += String.valueOf(new_id);
            			comand += ", ";
            			comand += "'";
            			comand += name;
            			comand += "'";
            			comand += ", ";
            			comand += "'";
            			comand += surname;
            			comand += "'";
            			comand += ", ";
            			comand += "'";
            			comand += docType;
            			comand += "'";
            			comand += ", ";
            			comand += "'";
            			comand += docNum;
            			comand += "'";
            			comand += ")";
            			System.out.printf("Comand: %s\n", comand);
            			System.out.printf("ВОТ ЩАС НАЧАЛО!!!\n");
            			
            			//baza.getNomen(comand);
            			//-------------выполняем команду-------------------------------
            			try {
            				PreparedStatement ps = baza.getPrepareStatement(comand);
            				ps.executeUpdate();
            			} catch (SQLException r) {
            				r.printStackTrace();
            			}
            			
            			//------выдача сообщения----------------------------------------
            			JFrame err = new JFrame("Correct!");
            			JLabel l = new JLabel("    Пользователь добавлен");
            			err.getContentPane().add(l);
            			err.setSize(200, 100);
            			err.setVisible(true);
        			}
        			
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
