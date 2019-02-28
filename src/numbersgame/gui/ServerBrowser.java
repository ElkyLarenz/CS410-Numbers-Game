
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
		// Testing only
		serverList = new ServerInfo[ 3 ];
		serverList[ 0 ] = new ServerInfo( "111", "Bob" );
		serverList[ 1 ] = new ServerInfo( "222", "Bill" );
		serverList[ 2 ] = new ServerInfo( "333", "Bill2" );
	}
	
	public void refreshServerList()
	{
		
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
