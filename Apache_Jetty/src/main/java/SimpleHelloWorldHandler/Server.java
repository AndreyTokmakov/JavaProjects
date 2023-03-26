
package SimpleHelloWorldHandler;

import handlers.HelloHandler;

public class Server {
	/*
    public void execute() throws Exception{
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
            	Server server = new Server(8080);
                try {
                    server.getConnectors()[0].getConnectionFactory(HttpConnectionFactory.class);
                    server.setHandler(new HelloHttpRequestHandler());
                    server.start();
                    server.join();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
    }*/
	
	public static void main(String[] args) throws Exception {
		org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server(8080);
		server.setHandler(new HelloHandler());
		server.start();
		server.join();
	}	
}