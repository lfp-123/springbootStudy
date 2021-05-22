package osgi.demo1.server;

import org.eclipse.jdt.internal.compiler.util.HashtableOfType;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import osgi.demo1.api.api;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * @author ${林锋鹏}
 * @Title: ServerBundle
 * @ProjectName osgi_demo
 * @Description: TODO
 * @date 2020/5/31 9:14
 */
public class ServerBundle implements BundleActivator {
    @Override
    public void start(BundleContext context) throws Exception {
        Impl impl = new Impl();
        Dictionary<String, Object> props = new Hashtable<>();
         context.registerService(api.class, impl, props);
        System.out.println("register server");
    }

    @Override
    public void stop(BundleContext context) throws Exception {

    }
}
