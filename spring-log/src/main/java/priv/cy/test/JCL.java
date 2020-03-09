package priv.cy.test;

// JCL的日志对象是Log
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JCL {
    public static void main(String[] args) {
        // JCL的日志对象是log，括号里面设置的是日志对象的唯一标识，既可以使用当前类的class也可以自定义名字
        Log log = LogFactory.getLog("JCL");
        log.info("jcl");
    }
}
