package osgi.blueprint.client;

import osgi.demo1.api.api;

/**
 * @author ${林锋鹏}
 * @Title: BlueClientBundle
 * @ProjectName osgi_demo
 * @Description: TODO
 * @date 2020/5/31 21:37
 */
public class BlueClientBundle {

        private api api;

    public osgi.demo1.api.api getApi() {
        return api;
    }

    public void setApi(osgi.demo1.api.api api) {
        this.api = api;
        System.out.println(api.sayHello());
    }
}

