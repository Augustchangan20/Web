package web.http;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLEncodeDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("C++","utf-8");//编码
        System.out.println(encode);

        String 我 = URLEncoder.encode("我","utf-8");
        System.out.println(我);

        String decode = URLDecoder.decode("C%2B%2B","utf-8");//解码
        System.out.println(decode);

    }
}
