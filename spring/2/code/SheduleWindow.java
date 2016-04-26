package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SheduleWindow extends JFrame{
	private Vector<Vector<Object>> info; //структура shedule
	private User user;
	
	public SheduleWindow(Vector<Vector<Object>> v, User u){
		super();
		this.info = v;
		this.user = u;
		
		for(int i=0; i < info.size(); i++)
			System.out.println(info.get(i).toString());
		
		Vector<String> str_info = new Vector<String>();
		for(int i=0; i<info.size(); i++)
			str_info.add(info.get(0).toString());
		
		JPanel panel = new JPanel();
		JButton ok = new JButton("«аказать билет");
		
        KModel shedule = new KModel("SHEDULE");
        shedule.setTableData(info);
        
        JTable table = new JTable(shedule);
        table.getColumnModel().getColumn(0).setMaxWidth(500);
          
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(ok);
        setSize(400, 800);
        
        ok.addActionListener(new ActionListener() {
			 
			public void actionPerformed(ActionEvent event) {
				//получаем номер выделенной строки
				Vector<Object> trans_info = info.get(table.getSelectedRow());
				//создаем окно создани€ заказа
				CreateOrder orderWindow = new CreateOrder(trans_info, user);
			
			}
		});
              
        add(panel);
        //setPreferredSize(new Dimension(430, 550));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);        
	}
}
