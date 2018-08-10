package demo.core;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class ServicesTracker implements ServiceTrackerCustomizer<Service, Service> {

	List<Service> services = new ArrayList<>();
	BundleContext context;

	public ServicesTracker(BundleContext context) {
		this.context = context;
	}

	@Override
	public Service addingService(ServiceReference<Service> reference) {
		Service service = context.getService(reference);
		services.add(service);
		return service;
	}

	@Override
	public void modifiedService(ServiceReference<Service> reference, Service service) {
	}

	@Override
	public void removedService(ServiceReference<Service> reference, Service service) {
	}

	public void presentServices() {
		services.forEach(Service::present);
	}

}
