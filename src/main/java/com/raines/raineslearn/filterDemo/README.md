#过滤器
###自定义过滤器被执行两次的原因分析及解决办法
打印req.getRequestURI()显示两次调用的url为

    /demo/create
    /favicon.ico
过滤器被执行两次的情况仅限于使用Servlet容器既提供静态访问支持、又提供动态访问支持的情况，当采用动静态分离的场合（例如apache+tomcat，apache将静态请求拦截自己处理，tomcat只处理动态内容），这种问题自然而然就消失了，因为该请求不会到达Servlet容器。


既然知道了原理，解决起来就好办了，写几个正则表达式，按照requestURI的规划进行合理的分配，不同的访问URL采用不同的权限过滤机制即可。但是这种方法并不能阻止doFilter方法被调用两次，只是代码在按照我们指定的请求逻辑上运行。也许，这就是过滤器的不足之处。