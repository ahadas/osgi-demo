package demo.plugin1;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import demo.core.Service;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		System.out.println("plugin1 start");
		context.registerService(Service.class, new Service() {
			@Override
			public void present() {
				System.out.println("I'm plugin1");
			}}, null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		System.out.println("plugin1 stop");
	}

}
