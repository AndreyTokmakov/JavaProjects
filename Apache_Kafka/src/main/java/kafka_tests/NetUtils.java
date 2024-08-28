package kafka_tests;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class NetUtils {
	public static InetAddress intToInetAddress(Integer value) throws UnknownHostException {
		ByteBuffer buffer = ByteBuffer.allocate(32);
		buffer.putInt(value);
		buffer.position(0);
		byte[] bytes = new byte[4];
		buffer.get(bytes);
		return InetAddress.getByAddress(bytes);
	}
	
	public static String intToIp(long i) {
        return ((i >> 24 ) & 0xFF) + "." +
               ((i >> 16 ) & 0xFF) + "." +
               ((i >>  8 ) & 0xFF) + "." +
               ( i        & 0xFF);
    }
	
	public static Long ipToInt(String addr) {
        String[] addrArray = addr.split("\\.");

        long num = 0;
        for (int i=0;i<addrArray.length;i++) {
            int power = 3-i;

            num += ((Integer.parseInt(addrArray[i])%256 * Math.pow(256,power)));
        }
        return num;
    }
}
