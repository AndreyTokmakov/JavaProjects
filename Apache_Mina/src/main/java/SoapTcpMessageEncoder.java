import java.io.OutputStream;
//import javax.xml.soap.SOAPMessage;

//import org.apache.cxf.binding.soap.tcp.frames.SoapTcpMessage;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
//import org.apache.cxf.binding.soap.*;

public class SoapTcpMessageEncoder extends ProtocolEncoderAdapter {

	public SoapTcpMessageEncoder() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void encode(IoSession session, Object obj, ProtocolEncoderOutput out) throws Exception {
	     /*  if (obj instanceof SoapMessage) {
	    	                SOAPMessage msg = (SOAPMessage)obj;
	    	                IoBuffer buffer = IoBuffer.allocate(1024);
	    	                buffer.setAutoExpand(true);
	    	                OutputStream outStream = buffer.asOutputStream();
	    	                SoapTcpUtils.writeSoapTcpMessage(outStream, msg);
	    	                outStream.close();
	    	                out.write(buffer);
	       }*/
	}

}
