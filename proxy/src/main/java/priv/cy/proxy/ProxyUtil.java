package priv.cy.proxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ProxyUtil {
    /**
     * 创建对象的过程
     * 代码内容content（也就是一堆字符串） -> .java -> .class -> 创建对象
     *
     * 产生.java文件的方法有两种：
     * 1、自己新建一个.java文件
     * 2、通过IO去创建一个文件对象，File类
     *
     * @return
     */
    public static Object newInstance(Object target) {
        // 要生成的代理对象
        Object proxy = null;

        // 获取类的实现接口
        Class targetInf = target.getClass().getInterfaces()[0];

        // 获得接口的所有方法
        Method methods[] = targetInf.getDeclaredMethods();

        // 换行
        String line = "\n";
        // tab缩进
        String tab = "\t";

        // 获取到简单类名
        String infName = targetInf.getSimpleName();

        // 代码的全部内容对象
        String content = "";

        String packageContent = "package com.test;" + line;

        // 取得类的全限定名
        String importContent = "import " + targetInf.getName() + ";" + line;

        String clazzFirstLineContent = "public class $Proxy implements " + infName + "{" + line;
        String filedContent = tab + "private " + infName + " target;" + line;
        String constructorContent = tab + "public $Proxy (" + infName + " target){" + line
                + tab + tab + "this.target =target;"
                + line + tab + "}" + line;
        String methodContent = "";
        for (Method method : methods) {
            // 获得方法得返回类型
            String returnTypeName = method.getReturnType().getSimpleName();
            // 获得方法名
            String methodName = method.getName();

            // Sting.class String.class
            // 获得方法的所有参数类型
            Class args[] = method.getParameterTypes();
            String argsContent = "";
            String paramsContent = "";
            int flag = 0;
            for (Class arg : args) {
                String temp = arg.getSimpleName();
                //String
                //String p0,Sting p1,
                argsContent += temp + " p" + flag + ",";
                paramsContent += "p" + flag + ",";
                flag++;
            }
            if (argsContent.length() > 0) {
                argsContent = argsContent.substring(0, argsContent.lastIndexOf(",") - 1);
                paramsContent = paramsContent.substring(0, paramsContent.lastIndexOf(",") - 1);
            }

            methodContent += tab + "public " + returnTypeName + " " + methodName + "(" + argsContent + ") {" + line
                    + tab + tab + "System.out.println(\"log\");" + line
                    + tab + tab + "target." + methodName + "(" + paramsContent + ");" + line
                    + tab + "}" + line;

        }


        // 将前面所有的代码字符串全部拼接
        content = packageContent + importContent + clazzFirstLineContent + filedContent + constructorContent + methodContent + "}";

        // 生成.java文件
        File file = new File("C:\\我的电脑\\com\\test\\$Proxy.java");
        try {
            // 如果不存在，则创建
            if (!file.exists()) {
                file.createNewFile();
            }

            // 生成字符流
            FileWriter fw = new FileWriter(file);
            // 将字符串写入字符流
            fw.write(content);
            fw.flush();
            // jdk1.8不要需要手动关闭，但是还是要养成良好编码习惯
            fw.close();

            // 我们手动来编译我们自己生成的.java文件，生成.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

            StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
            Iterable units = fileMgr.getJavaFileObjects(file);

            JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
            t.call();
            fileMgr.close();

            URL[] urls = new URL[]{new URL("file:c:\\我的电脑\\")};
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            Class clazz = urlClassLoader.loadClass("com.test.$Proxy");

            Constructor constructor = clazz.getConstructor(targetInf);

            proxy = constructor.newInstance(target);
            //clazz.newInstance();
            //Class.forName()
        } catch (Exception e) {
            e.printStackTrace();
        }


        /**
         * public UserDaoLog(UserDao target){
         * 		this.target =target;
         *
         *        }
         */
        return proxy;
    }
}
