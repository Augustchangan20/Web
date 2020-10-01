package SQL;

import java.util.Arrays;

public class MySQLClient {
    /**
     * 以下配置，可以通过parseArguments进行替换
     * @param args
     */
    private static String host = "127.0.0.1";
    private static int port = 3306;
    private static String user = null;
    private static String password = "";
    private static String defaultDatebaseName = "";

    public static void main(String[] args) {
        //args代表用户输入的参数
        if (args.length == 0){
            printUsageAndExit();
        }
        parseArguments(args);
        System.out.println(host);
        System.out.println(port);
        System.out.println(user);
        System.out.println(password);
        System.out.println(defaultDatebaseName);
    }

    private static void parseArguments(String[] args) {
        defaultDatebaseName = args[args.length -1];
        args = Arrays.copyOfRange(args,0,args.length -1);
        for (int i = 0; i < args.length ; i++) {
            String arg = args[i];
            switch (arg){
                case "--help":
                    printUsageAndExit();
                case "-h":
                case "--host":
                    host = args[++i];
                    break;
                case "-P":
                case "--port":
                    port = Integer.parseInt(args[++i]);
                    break;
                case "-u":
                case "--user":
                    user = args[++i];
                    break;
                case "-p":
                case"--password":
                    password = args[++i];
                    break;
                default:
                    printUsageAndExit("不认识的选项 "+arg);
            }

        }
    }

    private static void printUsageAndExit(String... messages) {
        System.out.println("使用帮助：mysql [选项] 默认数据库");
        System.out.println("--help         打印帮助信息");
        System.out.println("-h, --host      连接主机，默认是127.0.0.1");
        System.out.println("-P, --port      连接端口，默认是3306");
        System.out.println("-u, --user      mysql 用户名，必须设置");
        System.out.println("-p, --password mysql 密码");

        //1.stream的操作 ---- 等于下面的简单版本
        //2，方法引用
        Arrays.stream(messages).peek(System.out::println);
        /*for (String s : messages){
            System.out.println(s);
        }*/
        System.exit(1);
    }
}

