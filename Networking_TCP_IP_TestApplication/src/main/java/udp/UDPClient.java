package udp;

import java.io.*;
import java.net.*;

public class UDPClient {
	public static void main(String args[]) throws Exception {
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("100.99.4.84");
		byte[] sendData = new byte[10240];
		byte[] receiveData = new byte[10240];
		
		String message = "{\"worker_name\": \"WorkerTesterWin7x64\", ";
	    message += "\"start_time\": \"2019-07-04 15:10:10\",";
	    message += "\"end_time\": \"2019-07-04 15:22:48\",";
	    message += "\"tests_type\": \"UpdaterTests\",";    
	    message += "\"is_nightly\": false, \"tests\": {";
	    message += "\"chromedriver_unittests\": {\"passed\": 357, \"failed\": 0, \"skipped\": 0},";
	    message += "\"elevation_service_unittests\": {\"passed\": 5, \"failed\": 0, \"skipped\": 0}}}";
		
		sendData = message.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 50000);
		clientSocket.send(sendPacket);
		clientSocket.close();
	}
}