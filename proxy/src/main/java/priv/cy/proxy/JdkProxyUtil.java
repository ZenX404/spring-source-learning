package priv.cy.proxy;

import priv.cy.dao.CoustomInvocationHandler;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 模拟jdk的动态代理（可以自定义代理逻辑）：模拟JDK的Proxy类
 */
public class JdkProxyUtil {
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
    public static Object newInstance(Class targetInf, CoustomInvocationHandler h) {
        // 准备要生成的代理对象
        Object proxy = null;

        // 通过接口获取接口中的所有方法
        Method methods[] = targetInf.getDeclaredMethods();

        // 换行
        String line = "\n";
        // 缩进
        String tab = "\t";
        // 获取接口名
        String infName = targetInf.getSimpleName();
        // 代码内容
        String content = "";
        // 包名
        String packageContent = "package com.test;" + line;
        // 导入包
        String importContent = "import " + targetInf.getName() + ";" + line
                + "import priv.cy.dao.CoustomInvocationHandler;" + line
                + "import java.lang.Exception;"
                + "import java.lang.reflect.Method;" + line;

        // 代理类定义的第一行
        String clazzFirstLineContent = "public class $Proxy implements " + infName + "{" + line;
        // 代理类中的目标对象
        String filedContent = tab + "private CoustomInvocationHandler h;" + line;

        // 代理类的构造方法
        String constructorContent = tab + "public $Proxy (CoustomInvocationHandler h){" + line
                + tab + tab + "this.h =h;"
                + line + tab + "}" + line;

        // 代理类中的方法定义
        String methodContent = "";
        // 循环生成所有方法的代码
        for (Method method : methods) {
            // 方法得返回值类型
            String returnTypeName = method.getReturnType().getSimpleName();
            // 方法名
            String methodName = method.getName();
            // 方法的所有参数类型
            // Sting.class String.class
            Class args[] = method.getParameterTypes();
            // 传入代理对象中代理方法的参数
            String argsContent = "";
            // 代理对象中代理方法调用目标对象的目标方法时要传入的参数，和上面的区别就是上面是带着参数类型的，因为是在方法定义的地方写的，而这个没有参数类型，因为实在方法调用的地方写的
            String paramsContent = "";
            // 变量名标记
            int flag = 0;
            // 遍历所有的变量类型
            for (Class arg : args) {
                // 获得变量类型名
                String temp = arg.getSimpleName();
                //String
                //String p0,Sting p1,
                // 生成两组变量内容
                argsContent += temp + " p" + flag + ",";
                paramsContent += "p" + flag + ",";
                // 保证变量名唯一
                flag++;
            }
            // 如果参数字符串长度大于零说明方法有参数，将最后一个逗号去掉
            if (argsContent.length() > 0) {
                argsContent = argsContent.substring(0, argsContent.lastIndexOf(",") - 1);
                paramsContent = paramsContent.substring(0, paramsContent.lastIndexOf(",") - 1);
            }

            // 开始生成方法定义代码
            methodContent += tab + "public " + returnTypeName + " " + methodName + "(" + argsContent + ")throws Exception {" + line
                    + tab + tab + "Method method = Class.forName(\"" + targetInf.getName() + "\").getDeclaredMethod(\"" + methodName + "\");" + line
                    + ("void".equals(returnTypeName) ? (tab + tab + "h.invoke(method);" + line) : (tab + tab + "return (" + returnTypeName + ")h.invoke(method);" + line)); // 这里要注意返回值类型是void的情况，这种没有返回值的方法不能加return，而且不能将对象强制转换成void类型。这里用三目运算做了判断

            methodContent += tab + "}" + line;
        }

        // 将整个类的全部内容拼接起来
        content = packageContent + importContent + clazzFirstLineContent + filedContent + constructorContent + methodContent + "}";

        // 生成.java文件
        File file = new File("C:\\我的电脑\\com\\test\\$Proxy.java");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            // 使用文件字符流，将上面生成的Java代码字符串写入.java文件中
            FileWriter fw = new FileWriter(file);
            fw.write(content);
            fw.flush();
            // 关掉字符流
            fw.close();

            // 下面的代码时编译.java文件的代码
            // 通过ToolProvider获得JavaCompiler，JavaCompiler可以动态编译一些.java文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            // 通过JavaCompiler获得StandardJavaFileManagerJava文件管理器，用来指定要编译哪些.java文件
            StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);
            // 将我们生成的.java文件放到文件管理器中
            Iterable units = fileMgr.getJavaFileObjects(file);

            // 使用JavaCompiler类生成一个任务来编译这个.java文件
            JavaCompiler.CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);
            t.call();
            fileMgr.close();

            // 使用urlClassLoader类加载器将生成的.class文件加载如JVM实现类加载，因为生成的.class不在classpath路径中，而是在电脑磁盘的其他位置，所以就需要用urlClassLoader这个类加载器来加载指定位置的.class文件
            // 生成一个URL数组，来指定我们要加载的class文件所在地址，下面的意思就是加载c:\我的电脑目录下所有的class文件
            URL[] urls = new URL[]{new URL("file:c:\\我的电脑\\")};
            // 生成加载指定目录下的class文件的类加载器
            URLClassLoader urlClassLoader = new URLClassLoader(urls);
            // 执行类加载器，并且将加载得到的Class对象返回，有了Class模板，就可以生成实例化对象了
            Class clazz = urlClassLoader.loadClass("com.test.$Proxy");

            // 注意虽然得到了Class对象，但是不能通过Class.forName()方法来获得对象实例，因为这种方法只能将在classpath路径下的class文件实例化成对象，不能将其他位置的class文件编程实例化对象，具体原因参见JVM类加载器笔记
            // 所以我们需要通过获得构造方法来实例化对象

            // 通过反射机制，获取类的构造方法
            Constructor constructor = clazz.getConstructor(CoustomInvocationHandler.class);
            // 通过代理对象的构造方法创建实例化对象    clazz.newInstance();
            proxy = constructor.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 将生成的代理对象返回
        return proxy;
    }
}
