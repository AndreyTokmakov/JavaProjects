/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Visitor pattern demo class
*
* @name    : Router.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 15, 2020
* 
****************************************************************************/ 

package Visitor;


interface Router {
    public void sendData(char[] data);
    public void acceptData(char[] data);
    public void accept(RouterVisitor v);
}

interface RouterVisitor {
    public void visit(DLinkRouter router);
    public void visit(TPLinkRouter router);
    public void visit(LinkSysRouter router);
}

class DLinkRouter implements Router {
	
    @Override
    public void sendData(char[] data) {
    }
 
    @Override
    public void acceptData(char[] data) {
    }
 
    @Override
    public void accept(RouterVisitor visitor) {
    	visitor.visit(this);
    }
}

class LinkSysRouter implements Router {
	
    @Override
    public void sendData(char[] data) {
    }
 
    @Override
    public void acceptData(char[] data) {
    }
     
    @Override
    public void accept(RouterVisitor visitor) {
    	visitor.visit(this);
    }
}

class TPLinkRouter implements Router {	
	
    @Override
    public void sendData(char[] data) {
    }
 
    @Override
    public void acceptData(char[] data) {
    }
     
    @Override
    public void accept(RouterVisitor visitor) {
    	visitor.visit(this);
    }
}

class LinuxConfigurator implements RouterVisitor {
	 
    @Override
    public void visit(DLinkRouter router) {
        System.out.println("DLinkRouter Configuration for Linux complete !!");
    }
 
    @Override
    public void visit(TPLinkRouter router) {
        System.out.println("TPLinkRouter Configuration for Linux complete !!");
    }
 
    @Override
    public void visit(LinkSysRouter router) {
        System.out.println("LinkSysRouter Configuration for Linux complete !!");
    }
}

class MacConfigurator implements RouterVisitor {
	 
    @Override
    public void visit(DLinkRouter router) {
        System.out.println("DLinkRouter Configuration for Mac complete !!");
    }
 
    @Override
    public void visit(TPLinkRouter router) {
        System.out.println("TPLinkRouter Configuration for Mac complete !!");
    }
 
    @Override
    public void visit(LinkSysRouter router) {
        System.out.println("LinkSysRouter Configuration for Mac complete !!");
    }
}


public class Router_Visitor 
{
	private MacConfigurator macConfigurator = new MacConfigurator();
    private LinuxConfigurator linuxConfigurator = new LinuxConfigurator();
    
    private DLinkRouter dlink = new DLinkRouter();
    private TPLinkRouter tplink = new TPLinkRouter();
    private LinkSysRouter linksys = new LinkSysRouter();
     
    protected void ConfigureRouter(Router router) {
    	router.accept(macConfigurator);
        router.accept(linuxConfigurator);
        System.out.println();
    }

    protected void testDlink()  {
    	this.ConfigureRouter(dlink);
    }
     
    protected void testTPLink() {
        this.ConfigureRouter(tplink);
    }
     
    protected void testLinkSys() {
        this.ConfigureRouter(linksys);
    }
    
    protected void Test() {
    	this.testDlink();
    	this.testTPLink();
    	this.testLinkSys();
    }
    
    public static void main(String[] args) {
    	Router_Visitor test = new Router_Visitor();
    	test.Test();
    	
    }
}
