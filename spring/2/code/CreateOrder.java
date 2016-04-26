package app;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateOrder extends JFrame {
	private KFDB baza;
	private User user;
	private Vector<Vector<Object>> tickets;
	private Vector<Object> info;
	
	public CreateOrder(Vector<Object> in, User u){
		this.info = in;
		this.user = u;
		baza = new KFDB("org.firebirdsql.jdbc.FBDriver", "jdbc:firebirdsql:localhost/3050:D:TICKET_BASE.FDB", "SYSDBA", "masterkey");
		
		JPanel firstPanel = new JPanel();
        firstPanel.setLayout(null);
        
        JLabel lName = new JLabel("Имя");
		lName.setSize(90, 20);
		lName.setLocation(250, 30);
		JLabel lSurname = new JLabel("Фамилия");
		lSurname.setSize(90, 20);
		lSurname.setLocation(250, 60);
		JLabel lDocType = new JLabel("Тип документа");
		lDocType.setSize(160, 20);
		lDocType.setLocation(250, 90);
		JLabel lDoc = new JLabel("Номер документа");
		lDoc.setSize(160, 20);
		lDoc.setLocation(250, 120);
		
		//-----текстовые поля + кнопка ОК-----------------------------------------
				JTextField name = new JTextField(user.getName(), 20);
				name.setSize(190, 20);
				name.setLocation(20, 30);
				JTextField surname = new JTextField(user.getSurname(), 20);
				surname.setSize(190, 20);
				surname.setLocation(20, 60);
				JTextField docType = new JTextField("passport", 20);
				docType.setSize(190, 20);
				docType.setLocation(20, 90);
				JTextField docNum = new JTextField(user.getDocNum(), 20);
				docNum.setSize(190, 20);
				docNum.setLocation(20, 120);
				
				JButton ok = new JButton("OK");
				ok.setSize(200, 60);
				ok.setLocation(50, 180);
							
				//----------поиск количества доступных мест-------------------------------------------
				String comand = "select * from ticket,place where " +
						"ticket.place_id=place.place_id and " +
						"place.train_id=" + Integer.toString((int) info.get(1)) +
						" and ticket.status='wait' and ticket.r_date = '" + info.get(3) +"'";
				//System.out.println(comand);
				Vector<Vector<Object>> tmpVectorForMouseEvent = baza.getNomen(comand);	
				tickets = tmpVectorForMouseEvent;
				int numPlace = tmpVectorForMouseEvent.size();				
				String tmp = "Доступных мест: " + Integer.toString(numPlace);
				//если место 0 - кнопка Заказа не работает
				if(numPlace == 0)
					ok.setEnabled(false);
				//--------------------------------------------------------------------------------------
				
				JLabel label = new JLabel(tmp);				
				label.setSize(250, 120);
				label.setLocation(500, 10);
				//-------------------------------------------------------------------------
				
						
				//-----обработчик кнопки----------------------------------------------------
				ok.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						//берем самый первый билет:
						int t_num = ((Integer)tickets.get(0).get(0)).intValue(); //T_NUM в этой ячейке
						System.out.print("t_num:");
						System.out.println(Integer.toString(t_num));
						
						//смена статуса билета на buy
						String comand = "update ticket set ticket.status = 'buy' where t_num = " + 
										Integer.toString(t_num);
						System.out.println(comand);
						//-------------выполняем команду-------------------------------
            			try {
            				PreparedStatement ps = baza.getPrepareStatement(comand);
            				ps.executeUpdate();
            			} catch (SQLException r) {
            				r.printStackTrace();
            			}
						
						//заполнение t_order
						// ищем свободный id in t_orders
						//-----------формирование id--------------------------------------------
            			int new_id = 1;
            			while(true){
            				try{
            					comand = "SELECT * FROM t_order where order_id=";
                    			comand += String.valueOf(new_id);
                    			Vector<Vector<Object>> tmp = baza.getNomen(comand);
                    			System.out.printf("Comand: %s\n", comand);
                    			if(tmp.size() != 0){
                    				new_id++;
                    			}
                    			else{
                    				//System.out.printf("new_id for t_orders: %d\n", new_id);
                    				break;
                    			}	
            				}
            				catch(IndexOutOfBoundsException e2){
            					//System.out.printf("new_id for t_orders: %d\n", new_id);
            					break;
            				}
            			}
            			System.out.printf("new_id for t_order: %d\n", new_id);
						//закончили формирование new_id
            			
            			comand = "insert into t_order(order_id, t_num, tickets_num) values (" +
            						Integer.toString(new_id) +
            						", " + Integer.toString(t_num) +
            						", 1)";
            			System.out.println(comand);
            			//-------------выполняем команду-------------------------------
            			try {
            				PreparedStatement ps = baza.getPrepareStatement(comand);
            				ps.executeUpdate();
            			} catch (SQLException r) {
            				r.printStackTrace();
            			}
            			//закончили заполнение таблицы t_orders
            			
            			//заполнение таблицы orders
            			comand = "insert into orders(pas_id, order_id) values (" +
            						Integer.toString(user.getId()) +
            						", " + Integer.toString(new_id) +")";
            			System.out.println(comand);
            			//-------------выполняем команду-------------------------------
            			try {
            				PreparedStatement ps = baza.getPrepareStatement(comand);
            				ps.executeUpdate();
            			} catch (SQLException r) {
            				r.printStackTrace();
            			}
            			//закончили заполнение orders
            			JOptionPane.showMessageDialog(ok, "Заказ добавлен", "Информация", JOptionPane.WARNING_MESSAGE);
            			setVisible(false);
            			//закончили формирование нового заказа
					
					}
				});
				//------------------------------------------------------------------------------------
				firstPanel.add(name);
				firstPanel.add(surname);
				firstPanel.add(docType);
				firstPanel.add(docNum);
				firstPanel.add(label);
				firstPanel.add(ok);
				firstPanel.add(lName);
				firstPanel.add(lSurname);
				firstPanel.add(lDocType);
				firstPanel.add(lDoc);
				//-------------------------------------------------------------------------
				getContentPane().add(firstPanel);
				setSize(800, 300);
				setLocationRelativeTo(null);
				setVisible(true);	
	}
	
	

}
