package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class OrdersWindow extends JFrame {
	
	private KFDB baza;
	private User user;
	
	public OrdersWindow(User u){

		super();
		this.user = u;
		baza = new KFDB("org.firebirdsql.jdbc.FBDriver", "jdbc:firebirdsql:localhost/3050:D:TICKET_BASE.FDB", "SYSDBA", "masterkey");
		JPanel panel = new JPanel();
		JButton ok = new JButton("Удалить заказ");
		
        //------------------------------------------------
        KModel orders = new KModel("ORDER_TABLE");
        //место, старт, стоп, дата, время
        String comand = "select * from ticket, t_order, orders " +
				"where ticket.t_num=t_order.t_num " +
				"and t_order.order_id=orders.order_id " +
				"and orders.pas_id=" + Integer.toString(user.getId());
        System.out.println(comand);
        Vector<Vector<Object>> v = new Vector<Vector<Object>>();
        v =	baza.getNomen(comand);
        //поезд
        comand = "select * from place, ticket, t_order,  orders where " +
       		"orders.pas_id = " + Integer.toString(user.getId()) +
       		" and orders.order_id=t_order.order_id and t_order.t_num=ticket.t_num and ticket.place_id=place.place_id";
        System.out.println(comand);
        Vector<Vector<Object>> v2 = baza.getNomen(comand);
        //формируем модель orders
        Vector<Vector<Object>> v3 = new Vector<Vector<Object>>();
        //Vector<Object> v4 = new Vector<Object>();
        
        Object[] mas = new Object[8];
        for(int i=0; i < v.size(); i++){
        	Vector<Object> v4 = new Vector<Object>();
          	v4.clear();
        	v4.add(v2.get(i).get(6)); //номер билета
        	v4.add(v2.get(i).get(13)); //номер заказа
        	v4.add(v2.get(i).get(1)); //поезд 
        	v4.add(v.get(i).get(1)); //место
        	v4.add(v.get(i).get(3)); // старт
        	v4.add(v.get(i).get(4));// стоп
        	v4.add(v.get(i).get(5));// дата
        	v4.add(v.get(i).get(6));//время
        	System.out.println("V4: ");
        	System.out.println(v4);
        	
        	v3.add(v4);
        }
        System.out.println(v3);
       	orders.setTableData(v3);
        //------------------------------------------------
        
        JTable table = new JTable(orders);
        table.getColumnModel().getColumn(0).setMaxWidth(500);
          
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(ok);
        setSize(400, 300);
        
      //-----обработчик кнопки----------------------------------------------------
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				int order_id = (int) v3.get(table.getSelectedRow()).get(1);
				String comand;
				
				
				//удаление из orders
				comand = "delete from orders where order_id=" + Integer.toString(order_id);
				try {
    				PreparedStatement ps = baza.getPrepareStatement(comand);
    				ps.executeUpdate();
    			} catch (SQLException r) {
    				r.printStackTrace();
    			}
				
				//удаление из t_order
				System.out.println(order_id);
				comand = "delete from t_order where order_id=" + Integer.toString(order_id);
				try {
    				PreparedStatement ps = baza.getPrepareStatement(comand);
    				ps.executeUpdate();
    			} catch (SQLException r) {
    				r.printStackTrace();
    			}
				
				//смена статуса билета на wait
				int t_num = (int) v3.get(table.getSelectedRow()).get(0);
				comand = "update ticket set ticket.status = 'wait' where t_num = " + 
								Integer.toString(t_num);
				System.out.println(comand);
				//-------------выполняем команду-------------------------------
    			try {
    				PreparedStatement ps = baza.getPrepareStatement(comand);
    				ps.executeUpdate();
    			} catch (SQLException r) {
    				r.printStackTrace();
    			}
    			JOptionPane.showMessageDialog(ok, "Заказ отменен", "Информация", JOptionPane.WARNING_MESSAGE);
    			setVisible(false);
			}	
		});
		//------------------------------------------------------------------------------------
		
        add(panel);
        //setPreferredSize(new Dimension(430, 550));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);        
	}
}
