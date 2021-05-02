package chainOfResponsibility.v3;

import java.util.ArrayList;
import java.util.List;

public class ServletMain {

    public static void main(String[] args) {

        Request request = new Request();
        request.str = "大家好:)，<script>,欢迎访问baidu.com，大家都是996";
        Response response = new Response();
        response.str = "";

        FilterChain chain = new FilterChain();
        chain.add(new HtmlFilter()).add(new SensitiveFilter());
        chain.doFilter(request, response);

        System.out.println(request.str);
        System.out.println(response.str);

    }
}

class Request{
    String str;
}
class Response{
    String str;
}

interface Filter{
    //不需要中断循环，所以改boolean为void
    void doFilter(Request request, Response response, FilterChain chain);
}

class HtmlFilter implements Filter{
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {

        request.str = request.str.replace("<", "[").replace(">", "]");
        chain.doFilter(request, response);
        response.str += "--HTMLFilter()";
    }
}

class SensitiveFilter implements Filter{
    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.str = request.str.replaceAll("996", "955");
        chain.doFilter(request, response);
        response.str += "--SensitiveFilter()";
    }
}

//这里不需要多chain进行连接，所以不需要implement Filter
class FilterChain{
    List<Filter> filters = new ArrayList<>();
    int i = 0;

    //小技巧：链式编程
    public FilterChain add(Filter f){
        filters.add(f);
        return this;
    }

    public void doFilter(Request request, Response response){
        if(filters.size() == i)
            return;
        filters.get(i++).doFilter(request, response, this);
    }
}