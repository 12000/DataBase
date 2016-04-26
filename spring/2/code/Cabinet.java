package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Cabinet extends JFrame{
	private KFDB baza;
	private User user;
	private KModel shedule;
	private KModel start_st;
	private KModel finish_st;
	
	public Cabinet(User u){
		baza = new KFDB("org.firebirdsql.jdbc.FBDriver", "jdbc:firebirdsql:localhost/3050:D:TICKET_BASE.FDB", "SYSDBA", "masterkey");
		this.user = u;
		
		setSize(800, 400);
		//--------новая панель - после входа--------------------------------------
        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(null);
        //------------------------------------------------------------------------
        //-----текстовые поля + кнопка ОК-----------------------------------------
        JLabel lStart = new JLabel("Станция отправления");
		lStart.setSize(150, 20);
		lStart.setLocation(20, 10);
		JTextField startStation = new JTextField("Moscow", 20);
		startStation.setSize(190, 20);
		startStation.setLocation(20, 30);
		
		JLabel lStop = new JLabel("Станция назначения");
		lStop.setSize(150, 20);
		lStop.setLocation(250, 10);
		JTextField stopStation = new JTextField("SPB", 20);
		stopStation.setSize(190, 20);
		stopStation.setLocation(250, 30);
		
		JLabel lDate = new JLabel("Дата");
		lDate.setSize(150, 20);
		lDate.setLocation(20, 50);
		JTextField dateField = new JTextField("24.12.2015", 20);
		dateField.setSize(190, 20);
		dateField.setLocation(20, 70);
		
		JButton ok = new JButton("ПОИСК");
		ok.setSize(200, 60);
		ok.setLocation(50, 220);
		
		JButton orders = new JButton("Мои заказы");
		orders.setSize(200, 60);
		orders.setLocation(300, 220);
		
		JButton edit = new JButton("Изменить данные");
		edit.setSize(200, 60);
		edit.setLocation(550, 220);
		
		String tmp = "<html>Пользователь: " + this.user.getName() + " " + this.user.getSurname() +
				"<br>Документ: " + this.user.getDocType() + "</br>" + 
				"<br>Номер документа: " + this.user.getDocNum() + "</br>" +
				"<br>Идентификатор пользователя: " + this.user.getId() + "</br>";
					
		JLabel label = new JLabel(tmp);
		
		label.setSize(250, 120);
		label.setLocation(500, 10);
		//-------------------------------------------------------------------------
		firstPanel.add(startStation);
		firstPanel.add(stopStation);
		firstPanel.add(dateField);
		firstPanel.add(ok);
		firstPanel.add(orders);
		firstPanel.add(edit);
		firstPanel.add(label);
		firstPanel.add(lStart);
		firstPanel.add(lStop);
		firstPanel.add(lDate);
		//-------------------------------------------------------------------------
		
		//--------слушатель для кнопки ОК-------------------------------------------------
		ok.addMouseListener(new MouseListener(){
        	public void mouseClicked(MouseEvent event) {
        		String start = startStation.getText();
        		String finish = stopStation.getText();
        		String date = dateField.getText();
        		
        		// объекты для поиска
        		shedule = new KModel("SHEDULE");
        		start_st = new KModel("ST_FIND");
        		finish_st = new KModel("ST_FIND");
        		int start_id;
        		int finish_id;
        		
        		// вектор для хранения информации
        		Vector<Vector<Object>> tmpVectorForMouseEvent;
        		
        		//ищем ид станции отправления
        		String comand = "SELECT * FROM STATION WHERE STATION.NAME='" + start + "'";
        		tmpVectorForMouseEvent = baza.getNomen(comand);
        		start_id = ((Integer)tmpVectorForMouseEvent.get(0).get(0)).intValue();
        		//System.out.println(start_id);
        		
        		//ищем ид станции назначения
        		comand = "SELECT * FROM STATION WHERE STATION.NAME='" + finish + "'";
        		tmpVectorForMouseEvent.clear();
        		tmpVectorForMouseEvent = baza.getNomen(comand);
        		finish_id = ((Integer)tmpVectorForMouseEvent.get(0).get(0)).intValue();
        		//System.out.println(finish_id);
        		
        		//ищем, где встечаются такие id
        		comand ="select * from" +
        				"(select route_station.route_id as r_id1 from route_station where route_station.st_id="
        				+ Integer.toString(start_id) +")T1," +
        				"(select route_station.route_id as r_id2 from route_station where route_station.st_id="
        				+ Integer.toString(finish_id) +")T2 " + "where T1.r_id1 = T2.r_id2";
        		tmpVectorForMouseEvent.clear();
        		tmpVectorForMouseEvent = baza.getNomen(comand);
        		System.out.println(comand);
        		System.out.printf("Size tmpVectorForMouseEvent: %d\n", tmpVectorForMouseEvent.size());
        		
        		//удаляем повторы
        		Vector<Object> route = new Vector<Object>();
        		Vector<Integer> clear_route = new Vector<Integer>();
        		
        		//заполнение массива route
        		for(int i=0; i < tmpVectorForMouseEvent.size(); i++){
        			route.addElement(tmpVectorForMouseEvent.get(i).get(0));
        		}
        		System.out.printf("Size route: %d\n", route.size());
        		
        		//удаление повторов
        		route = new Vector<Object>(new TreeSet<Object>(route));
        		System.out.printf("Size route: %d\n", route.size());
        		System.out.println(route);
        		
        		// теперь в route cодержатся id маршрутов, где есть нужные станции, но неизввестно в каком порядке идут
        		// проверка на номер в пути: num_in_route_start < num_in_route_finish
        		int num_in_route_start, num_in_route_finish;
        		for(int i=0; i < route.size(); i++){
        			comand = "select * from route_station where st_id=" + Integer.toString(start_id) + 
        					" and route_id=" + route.get(i).toString();
        			tmpVectorForMouseEvent.clear();
            		tmpVectorForMouseEvent = baza.getNomen(comand);
            		num_in_route_start = (int) tmpVectorForMouseEvent.get(0).get(4);
            		
            		comand = "select * from route_station where st_id=" + Integer.toString(finish_id) + 
        					" and route_id=" + route.get(i).toString();
            		tmpVectorForMouseEvent.clear();
            		tmpVectorForMouseEvent = baza.getNomen(comand);
            		num_in_route_finish = (int) tmpVectorForMouseEvent.get(0).get(4);
            		
            		if( num_in_route_start < num_in_route_finish )
            			clear_route.add((int) route.get(i));
        		}
        		System.out.printf("Size clear_route: %d\n", clear_route.size());
        		System.out.println(clear_route);
        		
        		// в clear_route все подходящие маршруты
        		//получаем информацию о маршруте из shedule
        		for(int i=0; i < clear_route.size(); i++){
        			comand = "select * from shedule where route_id=" + clear_route.get(i).toString() + 
        					" and r_date = '" + date +"'";
        			
        			tmpVectorForMouseEvent.clear();
        			tmpVectorForMouseEvent = baza.getNomen(comand);
        		}
        		
        		for(int i=0; i < tmpVectorForMouseEvent.size(); i++)
        			System.out.println(tmpVectorForMouseEvent.get(i).toString());
        		
        		SheduleWindow curShedule = new SheduleWindow(tmpVectorForMouseEvent, user);
        		
        	}
        		 
        		public void mouseEntered(MouseEvent event) {}
        		 
        		public void mouseExited(MouseEvent event) {}
        		 
        		public void mousePressed(MouseEvent event) {}
        		 
        		public void mouseReleased(MouseEvent event) {}                      		
        			
        });
		
		//-------слушатель кнопки Мои заказы----------------------------------------------
		orders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				//создаем объект окна заказов
				OrdersWindow ordersWindow = new OrdersWindow(user);
							
			}
		});
		//--------------------------------------------------------------------------------
		//-------слушатель кнопки Изменить данные----------------------------------------------
		edit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					//создаем объект окна
					EditData ed = new EditData(user);					
				}
			});
		//--------------------------------------------------------------------------------
		getContentPane().add(firstPanel);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
