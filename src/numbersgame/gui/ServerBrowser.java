
/**
 * ServerBrowser
 * 
 * Retrieves information about the servers on the network.
 */

package numbersgame.gui;

import numbersgame.Client;

public class ServerBrowser
{
	Client client;
	ServerInfo[] serverList;
	
	public ServerBrowser( Client client )
	{
		serverList = new ServerInfo[ 1 ];
		this.client = client;
		refreshServerList();
	}
	
	public void refreshServerList()
	{
//		client.findServers();
//		String[] ips = client.getServerIPs();
//		String[] names = client.getServerNames();
//		serverList = new ServerInfo[ ips.length ];
//		
//		for ( int i = 0; i < ips.length; i++ )
//		{
//			if ( ips[ i ] != null )
//			{
//				serverList[ i ] = new ServerInfo( ips[ i ], names[ i ] );
//				System.out.println( "!!!! Added new server !!!!" );
//			}
//		}
		serverList[ 0 ] = new ServerInfo( "10.19.108.12", "Tim" );		
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
