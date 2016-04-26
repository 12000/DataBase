            package app;

            import java.util.Vector;
            import javax.swing.table.AbstractTableModel;


            public class KModel extends AbstractTableModel
            {
                    private static final long serialVersionUID = 1L;
                    //Имена колонок;
                    protected Vector<String> columnNames;
                    //Данные;
                    private Vector<Vector<Object>> tableData;
                    //Классы колонок;
                    protected Vector<Object> vColClass;

                    public KModel(String s)
                    {	
                    	super();
                        if(s.equals("PASSENGER")==true){
                        	vColClass = new Vector<Object>();
                        	vColClass.add(0, Integer.class);
                            vColClass.add(1, String.class);
                            vColClass.add(2, String.class);
                            vColClass.add(3, String.class);
                            vColClass.add(4, String.class);
                            columnNames = new Vector<String>();
                            columnNames.add("PAS_ID");
                            columnNames.add("NAME");
                            columnNames.add("SURNAME");
                            columnNames.add("DOCUMENT");
                            columnNames.add("DOC_NUM");
                        }
                        else if(s.equals("TICKET")){
                            vColClass = new Vector<Object>(); 
                            vColClass.add(0, Integer.class);
                            vColClass.add(1, Integer.class);
                            vColClass.add(2, String.class);
                            vColClass.add(3, String.class);
                            vColClass.add(4, String.class);
                            vColClass.add(5, String.class);
                            vColClass.add(6, String.class);
                            columnNames = new Vector<String>();
                            columnNames.add("T_NUM");
                            columnNames.add("PLACE_ID");
                            columnNames.add("STATUS");
                            columnNames.add("START_ST");
                            columnNames.add("STOP_ST");
                            columnNames.add("R_DATE");
                            columnNames.add("R_TIME");
                        }
                        else if(s.equals("SHEDULE")){
                            vColClass = new Vector<Object>(); 
                            vColClass.add(0, Integer.class);
                            vColClass.add(1, Integer.class);
                            vColClass.add(2, String.class);
                            vColClass.add(3, String.class);
                            vColClass.add(4, String.class);
                            columnNames = new Vector<String>();
                            columnNames.add("ROUTE_ID");
                            columnNames.add("TRAIN_ID");
                            columnNames.add("NAME");
                            columnNames.add("R_DATE");
                            columnNames.add("R_TIME");
                        }
                        else if(s.equals("ST_FIND")){
                            vColClass = new Vector<Object>(); 
                            vColClass.add(0, Integer.class);
                            columnNames = new Vector<String>();
                            columnNames.add("ST_ID");
                        }
                        else if(s.equals("ORDER_TABLE")){
                            vColClass = new Vector<Object>(); 
                            vColClass.add(0, Integer.class);
                            vColClass.add(1, Integer.class);
                            vColClass.add(2, Integer.class);
                            vColClass.add(3, Integer.class);
                            vColClass.add(4, String.class);
                            vColClass.add(5, String.class);
                            vColClass.add(6, String.class);
                            vColClass.add(7, String.class);
                            columnNames = new Vector<String>();
                            columnNames.add("TICKET NUM");
                            columnNames.add("ORDER NUM");
                            columnNames.add("TRAIN");
                            columnNames.add("PLACE");
                            columnNames.add("START_ST");
                            columnNames.add("STOP_ST");
                            columnNames.add("DATE");
                            columnNames.add("TIME");
                        }
                    }

                    @Override
                    public int getColumnCount() 
                    {
                            return columnNames.size();
                    }

                    @Override
                    public int getRowCount()
                    {
                            return getTableData().size();
                    }

                    @Override
                    public Object getValueAt(int row, int column) 
                    {
                            return getTableData().get(row).get(column);
                    }

                    //Показывать заголовки колонок;
                    public String getColumnName(int column)
                    {
                            return columnNames.get(column);
                    }

                    //Запрещаем редактировать первую колонку (счёт колонок идёт с нуля);
                    public boolean isCellEditable(int row, int column)
                    {
                            if(column == 0)
                            {
                                    return false;
                            }
                            return true;

                    }

                    public void setValueAt(Object obj, int row, int column)
                    {
                            if(column == 0)
                            {
                                    (getTableData().get(row)).set(column, (Integer)obj);
                            }		
                            else if(column == 1)
                            {
                                    (getTableData().get(row)).set(column, (String)obj);
                            }
                            fireTableCellUpdated(row, column);
                    }

                    public Class<?> getColumnClass(int col)
                    {
                            Class<?> c = Object.class;
                            try 
                            {
                                    c = (Class<?>)vColClass.get(col);
                            } catch (RuntimeException e)
                            {
                                    System.out.println(e);
                            }
                            return c;
                    }

                    public void setTableData(Vector<Vector<Object>> tableData)
                    {
                            this.tableData = tableData;
                    }

                    public Vector<Vector<Object>> getTableData()
                    {
                            return tableData;
                    }

            }

      