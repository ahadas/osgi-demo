package demo.core;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {

	private ServiceTracker<Service, Service> st;
	Thread thread;
	boolean stop;
	
	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("core start");
		ServicesTracker tracker = new ServicesTracker(context);
		st = new ServiceTracker<>(context, Service.class, tracker);
	    st.open();

		thread = new Thread() {
			public void run() {
				while (!stop) {
					tracker.presentServices();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		};
		thread.start();
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("core stop");
		st.close();
		stop = true;
	}

}
