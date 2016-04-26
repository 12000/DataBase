            package app;

            import java.sql.Connection;
            import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
            import java.sql.SQLException;
            import java.sql.Statement;
            import java.sql.ResultSet;
            import java.util.Vector;

            public class KFDB 
            {
                private Connection con = null;

                public KFDB(String driver, String url, String login, String pass)
                {
                    try
                    {
                        Class.forName(driver);
                        con = DriverManager.getConnection(url, login, pass);
                    } catch (ClassNotFoundException ex)
                    {
                        System.err.println("KFDB.Cannot find this db driver classes.");
                        ex.printStackTrace();
                    } catch (SQLException e)
                    {
                        System.err.println("KFDB.Cannot connect to this db.");
                        e.printStackTrace();
                    }         
                }

                public Vector<Vector<Object>> getNomen(String query)
                {
                    Vector<Vector<Object>> retVector = new Vector<Vector<Object>>();
                    try
                    {
                            Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(query);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int cols = rsmd.getColumnCount();
                                    while (rs.next())
                                    {
                                            Vector<Object> newRow = new Vector<Object>();
                                            for (int i = 1; i <= cols; i++)
                                            {
                                                    newRow.add(rs.getObject(i));
                                            }
                                            retVector.add(newRow);
                                    }
                                    rs.close();
                        st.close();
                     } catch (SQLException e)
                     {
                        System.err.println("KFDB.There are problems with the query " + query);
                        e.printStackTrace();
                     }    	
                    return retVector;
                }
                
                public PreparedStatement getPrepareStatement(String sql)
            			throws SQLException {
            		return con.prepareStatement(sql);
            	}
            }

      