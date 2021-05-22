    package osgi.blueprint.demo;

            import osgi.demo1.api.api;

    /**
     * @author ${林锋鹏}
     * @Title: BluePrintServerImpl
     * @ProjectName osgi_demo
     * @Description: TODO
     * @date 2020/5/31 10:34
     */
    public class BluePrintServerImpl implements api {

        private api api;

        public osgi.demo1.api.api getApi() {
            return api;
        }

        public void setApi(osgi.demo1.api.api api) {
            this.api = api;
        }

        @Override
        public String sayHello() {
            return "这是使用blueprint 实现的服务：1111111";
        }
    }
