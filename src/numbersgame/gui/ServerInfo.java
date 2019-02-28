
/**
 * ServerInfo
 * 
 * Utility class to get the information about a single server.
 */

package numbersgame.gui;

public class ServerInfo
{
	private String ipAddress;
	private String hostName;
	
	public ServerInfo( String ipAddress, String hostName )
	{
		this.ipAddress = ipAddress;
		this.hostName = hostName;
	}
	
	public String getIPAddress()
	{
		return ipAddress;
	}
	
	public String getHostName()
	{
		return hostName;
	}
}
