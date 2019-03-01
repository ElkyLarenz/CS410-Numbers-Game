
/**
 * ServerBrowser
 * 
 * Retrieves information about the servers on the network.
 */

package numbersgame.gui;

public class ServerBrowser
{
	ServerInfo[] serverList;
	
	public ServerBrowser()
	{
		refreshServerList();
	}
	
	public void refreshServerList()
	{
		// Testing only
		serverList = new ServerInfo[ 3 ];
		serverList[ 0 ] = new ServerInfo( "111.111.1.1", "Bob" );
		serverList[ 1 ] = new ServerInfo( "222.222.2.2", "Bill" );
		serverList[ 2 ] = new ServerInfo( "333.333.3.3", "John" );
	}
	
	public int getNumberOfServers()
	{
		return serverList.length;
	}
	
	public ServerInfo[] getServerList()
	{
		return serverList;
	}
}
