
/**
 * ServerBrowserPanel
 * 
 * Panel for displaying information from the server browser.
 * It organizes the data into a table.
 */

package numbersgame.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ServerBrowserPanel extends JPanel
{
	private ServerBrowser browser;
	private JTable serverTable;
	private JScrollPane scrollPane;
	private static String[] columnNames = { "IP", "HostName" };
	
	public ServerBrowserPanel( ServerBrowser browser )
	{
		this.setLayout( new BorderLayout() );
		this.browser = browser;
		serverTable = new JTable();
		scrollPane = new JScrollPane( serverTable );
		serverTable.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
		updateTable();
		
		this.add( scrollPane );
	}
	
	public String getSelectedIP()
	{
		String ip = "";
		int rowIndex = serverTable.getSelectedRow();
		
		if ( rowIndex > -1 )
			ip = (String) serverTable.getModel().getValueAt( rowIndex, 0 );
		
		return ip;
	}
	
	public void updateTable()
	{
		ServerInfo[] serverList = browser.getServerList();
		DefaultTableModel tableModel = new DefaultTableModel( columnNames, 0 );
		
		for ( int i = 0; i < browser.getNumberOfServers(); i++ )
		{
			if ( serverList[ i ] != null )
			{
				String[] rowData = { serverList[ i ].getIPAddress(), serverList[ i ].getHostName() };
				tableModel.addRow( rowData );
			}
		}
		
		serverTable.setModel( tableModel );
		
		this.validate();
		this.repaint();
		System.out.println( "server browser table updated" );
	}
	
	public ServerBrowser getServerBrowser()
	{
		return browser;
	}
}
