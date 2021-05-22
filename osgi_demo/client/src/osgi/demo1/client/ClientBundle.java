package osgi.demo1.client;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import osgi.demo1.api.api;

/**
 * @author ${林锋鹏}
 * @Title: ClientBundle
 * @ProjectName osgi_demo
 * @Description: TODO
 * @date 2020/5/31 9:17
 */
public class ClientBundle implements BundleActivator {
    @Override
    public void start(BundleContext context) throws Exception {
        ServiceReference<api> ref = context.getServiceReference(api.class);
        api service = context.getService(ref);
        System.out.println(service.sayHello());
    }

    @Override
    public void stop(BundleContext context) throws Exception {

    }
}
