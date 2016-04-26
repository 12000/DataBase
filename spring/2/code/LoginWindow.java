package app;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginWindow extends JFrame {
	
	private String name;
	private String surname;
	private String docNum;
	private String docType;
	private int id;
	
	private User user;
	private Cabinet cabinet;
	
	private KModel model;
	private KFDB baza;
	
	private boolean input;
	
	public LoginWindow( String s){
		super(s);
		
		input = false;
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
		//-----текстовые поля + кнопка ОК-----------------------------------------
		JTextField textName = new JTextField("Stas", 20);
		textName.setSize(120, 20);
		textName.setLocation(20, 30);
		JTextField textSurname = new JTextField("Stasov", 20);
		textSurname.setSize(120, 20);
		textSurname.setLocation(20, 60);
		JTextField textDocNum = new JTextField("123", 20);
		textDocNum.setSize(120, 20);
		textDocNum.setLocation(20, 90);
		
		JButton ok = new JButton("OK");
		ok.setSize(200, 60);
		ok.setLocation(50, 120);
		//--------------------------------------------------------------------------------
		//-------панель-------------------------------------------------------------------
		JPanel secondPanel = new JPanel();
        secondPanel.setLayout(null);
        secondPanel.add(textName);
        secondPanel.add(textSurname);
        secondPanel.add(textDocNum);
        secondPanel.add(ok);
        secondPanel.add(lName);
        secondPanel.add(lSurname);
        secondPanel.add(lDoc);
        //--------------------------------------------------------------------------------
        //--------слушатель для кнопки ОК-------------------------------------------------
        ok.addMouseListener(new MouseListener(){
        	public void mouseClicked(MouseEvent event) {
        			Vector<Vector<Object>> tmpVectorForMouseEvent;
        			String comand;
        			      			
        			name = textName.getText();
        			surname = textSurname.getText();
        			docNum = textDocNum.getText();
        			
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
        			try{ // вход выполнен
        				id = ((Integer)tmpVectorForMouseEvent.get(0).get(0)).intValue();
        				docType = ((String)tmpVectorForMouseEvent.get(0).get(3)).toString();
        				System.out.println("Ok button");
            			System.out.printf("Comand: %s\n", comand);
            			System.out.printf("Name: %s\n", name);
            			System.out.printf("surName: %s\n", surname);
            			System.out.printf("docNum: %s\n", docNum);
            			System.out.printf("id: %d\n", tmpVectorForMouseEvent.size());
            			System.out.printf("id: %d\n", id);
            			System.out.printf("Size: %d\n", tmpVectorForMouseEvent.size());
            			
            			createCabinet();// создание окна кабинета
            			JOptionPane.showMessageDialog(ok, "Вход выполнен!", "Информация", JOptionPane.WARNING_MESSAGE);
            			
            			input = true;
            			System.out.printf("Input: %s\n", input);
            			setVisible(false);
        			}
        			catch(IndexOutOfBoundsException e){ //вход не выполнен
        				//e.printStackTrace();
        				System.out.println("Ok button");
            			System.out.printf("Comand: %s\n", comand);
            			System.out.printf("Name: %s\n", name);
            			System.out.printf("surName: %s\n", surname);
            			System.out.printf("docNum: %s\n", docNum);
            			System.out.printf("Size: %d\n", tmpVectorForMouseEvent.size());
            			JOptionPane.showMessageDialog(ok, "Неверная комбинация!", "Информация", JOptionPane.WARNING_MESSAGE);
            			input = false;	
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
	
	public boolean GetInput() {
		return this.input;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getSurname(){
		return this.surname;
	}
	
	public String getDocNum(){
		return this.docNum;
	}
	
	public String getDocType(){
		return this.docType;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void createCabinet(){
		user = new User(getId(), getName(), getSurname(), getDocNum(), getDocType());
    	cabinet = new Cabinet(user);
    	/*
    	//--------новая панель - после входа--------------------------------------
        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(null);
        //------------------------------------------------------------------------
        //-----текстовые поля + кнопка ОК-----------------------------------------
		JTextField startStation = new JTextField("Станция отправления", 20);
		startStation.setSize(120, 20);
		startStation.setLocation(20, 30);
		JTextField stopStation = new JTextField("Станция назначения", 20);
		stopStation.setSize(120, 20);
		stopStation.setLocation(20, 60);
		
		JButton ok = new JButton("OK");
		ok.setSize(200, 60);
		ok.setLocation(50, 120);
		//-------------------------------------------------------------------------
		secondPanel.add(startStation);
		secondPanel.add(stopStation);
		secondPanel.add(ok);
		
		JFrame jp = new JFrame();
		jp.getContentPane().add(secondPanel);
		jp.setVisible(true);*/
	}
	
	public KFDB getBase(){
		return this.baza;
	}
}
