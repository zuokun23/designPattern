package chainOfResponsibility.v1;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Msg msg = new Msg();
        msg.setMsg("大家好:)，<script>，欢迎访问baidu.com，大家都是996");

        FilterChain fc = new FilterChain();
        fc.add(new HtmlFilter()).add(new SensitiveFilter());

        FilterChain fc2 = new FilterChain();
        fc2.add(new FaceFilter()).add(new UrlFilter());

        fc.add(fc2);
        fc.doFilter(msg);

        System.out.println(msg);
    }
}


class Msg{
    String msg;
    String name;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "msg='" + msg + '\'' +
                '}';
    }
}

interface Filter{
    void doFilter(Msg msg);
}

class HtmlFilter implements Filter{
    @Override
    public void doFilter(Msg msg) {
        String r = msg.getMsg();
        r = r.replace('<', '[');
        r = r.replace('>', ']');
        msg.setMsg(r);
    }
}

class SensitiveFilter implements Filter{
    @Override
    public void doFilter(Msg msg) {
        String r = msg.getMsg();
        r = r.replace("996", "955");
        msg.setMsg(r);
    }
}

class FaceFilter implements Filter{

    @Override
    public void doFilter(Msg msg) {
        String r = msg.getMsg();
        r = r.replace(":)", "^v^");
        msg.setMsg(r);
    }
}

class UrlFilter implements Filter{

    @Override
    public void doFilter(Msg msg) {
        String r = msg.getMsg();
        r = r.replace("baidu.com", "carlos.com");
        msg.setMsg(r);
    }
}

class FilterChain implements Filter{
    List<Filter> filters = new ArrayList<>();

    //小技巧：链式编程
    public FilterChain add(Filter f){
        filters.add(f);
        return this;
    }

    public void doFilter(Msg msg){
        for (Filter filter : filters) {
            filter.doFilter(msg);

        }
    }
}