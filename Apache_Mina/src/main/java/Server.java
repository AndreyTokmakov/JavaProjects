import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Date;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class Server {
    private static final int PORT = 9123;
    
	public Server() {
		// TODO Auto-generated constructor stub
	}
	
    public static void main( String[] args ) throws IOException
    {
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        acceptor.setHandler(new TimeServerHandler());
        acceptor.getSessionConfig().setReadBufferSize(2048 );
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        acceptor.bind(new InetSocketAddress(PORT));
    }
}


class TimeServerHandler extends IoHandlerAdapter {
    @Override
    public void exceptionCaught( IoSession session, Throwable cause ) throws Exception {
        cause.printStackTrace();
    }

    @Override
    public void messageReceived( IoSession session, Object message ) throws Exception {
        String str = message.toString();
        
        System.out.println("Message received : " + str);
        
        if (str.trim().equalsIgnoreCase("quit") ) {
            //session.close();
            session.closeNow();
            return;
        }

        Date date = new Date();
        session.write( date.toString() );
        System.out.println("Message written...");
    }

    @Override
    public void sessionIdle( IoSession session, IdleStatus status ) throws Exception {
        System.out.println( "IDLE " + session.getIdleCount( status ));
    }
}