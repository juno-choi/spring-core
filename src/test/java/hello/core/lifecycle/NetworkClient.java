package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient{

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = "+ url);
        //connect();
        //call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect: "+ url);
    }

    public void call(String message){
        System.out.println("call : "+ url + " message = "+ message);
    }

    public void disconnect(){
        System.out.println("close + "+ url);
    }


    /**
     * InitializingBean, DisposableBean interface들을 implements하여 사용하는 방법
    //의존 관계 주입이 끝나면 호출해주는 method
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화");
    }

    //서비스 종료시 호출되는 method
    @Override
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
    */

    /**
     * @Bean(initMethod = "init", destroyMethod = "close")에 init과 destroy를 통해 의존 관계 주입이 끝난것을 호출한다.
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화");
    }

    //서비스 종료시 호출되는 method
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
     */

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화");
    }

    //서비스 종료시 호출되는 method
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }

}
