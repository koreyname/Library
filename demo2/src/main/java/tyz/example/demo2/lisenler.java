package tyz.example.demo2;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionListener;

//ServletContextListener
//HttpSessionListener
@WebListener()
public class lisenler implements ServletContextListener {
//    @Overridea
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("创建后执行");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("销毁后执行");
    }
}
