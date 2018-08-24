package demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.felix.framework.FrameworkFactory;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.launch.Framework;

public class Main {

	public static void main(String[] args) {
		// https://stackoverflow.com/questions/1887809/how-to-start-and-use-apache-felix-from-code
		// https://github.com/apache/felix/blob/trunk/main/src/main/java/org/apache/felix/main/Main.java
		// http://felix.apache.org/documentation/subprojects/apache-felix-framework/apache-felix-framework-launching-and-embedding.html
		System.out.println("starting");
		Map<String, Object> map = new HashMap<>();
		Framework f = new FrameworkFactory().newFramework(map);
		System.out.println("starting OSGI...");
	    try {
	    	f.init();
			f.start();
		} catch (BundleException e) {
			e.printStackTrace();
		}

	    Bundle c;
	    Bundle p1;
	    Bundle p2;
		try {
			System.out.println("installing core");
			c = f.getBundleContext().installBundle("file:core/build/libs/core.jar");
			System.out.println("installing plugin1");
			p1 = f.getBundleContext().installBundle("file:plugin1/build/libs/plugin1.jar");
			System.out.println("installing plugin2");
			p2 = f.getBundleContext().installBundle("file:plugin2/build/libs/plugin2.jar");
		} catch (BundleException x) {
			// TODO Auto-generated catch block
			x.printStackTrace();
			return;
		} // dirty path ;)
	    
//	    f.getBundleContext().addServiceListener(new ServiceListener() {
//			
//			@Override
//			public void serviceChanged(ServiceEvent arg0) {
//				System.out.println("service changed " + arg0.getServiceReference());
//			}
//		});

	    try {
	    	c.start();
	    	System.out.println("started core");
			p1.start();
			System.out.println("started plugin1");
			p2.start();
	    	System.out.println("started plugin2");
		} catch (BundleException x) {
			// TODO Auto-generated catch block
			x.printStackTrace();
		}

	    /*
	    Set<String> inUse = new HashSet<String>();
	    for (ServiceReference sr : f.getServicesInUse()) {
	    	inUse.add(sr.toString());
	    }

	    for (ServiceReference sr : f.getRegisteredServices()) {
	    	String flag = (inUse.contains(sr.toString()) ? "[in use]" : "[      ]");
	    	System.out.println("+ registered service: " + flag + " " + sr.toString());
	    	System.out.println("+ registered service: " + sr.toString());
	    }
            */

            System.out.println("end");
	}

}
