package priv.cy.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import priv.cy.app.AppConfig;
import priv.cy.dao.Dao;
import priv.cy.dao.IndexDao;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext
                = new AnnotationConfigApplicationContext(AppConfig.class);

        Dao dao1 = (Dao) annotationConfigApplicationContext.getBean("indexDao");
        Dao dao2 = (Dao) annotationConfigApplicationContext.getBean("indexDao");

        dao1.query();
        dao2.query();
//        Dao dao = (Dao) annotationConfigApplicationContext.getBean("indexDao");
//        System.out.println(dao instanceof IndexDao);
//        dao.query("test");
//        System.out.println("=========================");
//        dao.query();



//        Class<?>[] interfaces = new Class[]{Dao.class};
//        byte bytes[] = ProxyGenerator.generateProxyClass("AA", interfaces);
//        File file = new File("C:\\Users\\97307\\Downloads\\Test.class");
//        try {
//            FileOutputStream fw = new FileOutputStream(file);
//
//            fw.write(bytes);
//            fw.flush();
//            fw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
