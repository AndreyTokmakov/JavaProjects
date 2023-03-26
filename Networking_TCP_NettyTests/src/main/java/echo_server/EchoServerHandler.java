package echo_server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
  
/**
* Handler implementation for the echo server.
*/
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
	
	long counter = 0;
  
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		//ctx.write(msg);
		// Discard the received data silently.
		//System.out.println(msg.toString());
		System.out.println(++counter);
        ((ByteBuf) msg).release(); 
	}
  
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}
  
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}