/**
 * 
 */

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.io.FileUtils;

/**
 * @author AndTokm
 *
 */
public class FTPTestApp 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		FTPClient ftpClient = new FTPClient();
		
		try {
			ftpClient.connect("ftp.kraft-s.ru");
			if (true == ftpClient.login("anonymous", "anonymous"))
			{

				String[] names = ftpClient.listNames();
				for (String name : names) {
					System.out.println("Name = " + name);
				}
		    
				FTPFile[] ftpFiles = ftpClient.listFiles();
				for (FTPFile ftpFile : ftpFiles) 
				{
					// Check if FTPFile is a regular file
					if (ftpFile.getType() == FTPFile.FILE_TYPE) {
						System.out.println("FTPFile: " + ftpFile.getName() + "; "
								+ FileUtils.byteCountToDisplaySize(ftpFile.getSize()));
					}
				}
				ftpClient.logout();
			}
			else 
				System.out.println("Filed to connect to the FTP server");
		    ftpClient.disconnect();
		}
		catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}

}
