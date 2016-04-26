            package app;

            import java.awt.BorderLayout;
            import java.awt.Container;
            import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
            import javax.swing.*;
			//import javax.swing.JFrame;
            //import javax.swing.JScrollPane;
            //import javax.swing.JTable;

            public class KApplication extends JFrame 
            {

                    private static final long serialVersionUID = 1L;
                    private JTable table;
                    private KModel model;
                    private KFDB baza;
                    
                    private boolean input;
                    private User user;
                    public KApplication()
                    {
                            super("Продажа билетов");
                            input = false;
                            Container c = getContentPane();
                            String s;
                            Vector<Vector<Object>> tmpVector; 
                            baza = new KFDB("org.firebirdsql.jdbc.FBDriver", "jdbc:firebirdsql:localhost/3050:D:TICKET_BASE.FDB", "SYSDBA", "masterkey");
                                                      
                            c.add(new JScrollPane(table), BorderLayout.CENTER);
                            setSize(400, 300);
                            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                            
                            //панель для кнопок
                            JPanel firstPanel = new JPanel();
                            firstPanel.setLayout(null);
                            
                            
                            //кнопки первого окна
                            JButton inpButton = new JButton("Login");
                            inpButton.setSize(200, 60);
                            inpButton.setLocation(90, 40);
                            
                            //слушатель кнопки входа
                            inpButton.addMouseListener(new MouseListener(){
                            	public void mouseClicked(MouseEvent event) {
                            			secondPart();
                            		}
                            		 
                            		public void mouseEntered(MouseEvent event) {}
                            		 
                            		public void mouseExited(MouseEvent event) {}
                            		 
                            		public void mousePressed(MouseEvent event) {}
                            		 
                            		public void mouseReleased(MouseEvent event) {}                      		
                            			
                            });
                            
                            JButton regButton = new JButton("Registration");
                            regButton.setSize(120, 30);
                            regButton.setLocation(90, 180);
                            
                            //слушатель кнопки регистрации
                            regButton.addMouseListener(new MouseListener(){
                            	public void mouseClicked(MouseEvent event) {
                            			RegistrationWindow RW = new RegistrationWindow("Registration");
                            			System.out.println("Reg button");
                            		}
                            		 
                            		public void mouseEntered(MouseEvent event) {}
                            		 
                            		public void mouseExited(MouseEvent event) {}
                            		 
                            		public void mousePressed(MouseEvent event) {}
                            		 
                            		public void mouseReleased(MouseEvent event) {}                      		
                            			
                            });
                            
                            firstPanel.add(inpButton);
                            firstPanel.add(regButton);
                            
                            
                            getContentPane().add(firstPanel);
                            setLocationRelativeTo(null);
                            setVisible(true);
                            
                    }	
                    
                    public void secondPart(){
                    	LoginWindow LW = new LoginWindow("Login");
            			System.out.println("Input button");
            			System.out.printf("Input: %s", input);
            			
                    }
                    
                    
                    public static void main(String[] args)
                    {
                            KApplication app = new KApplication();
                    }
            }

      