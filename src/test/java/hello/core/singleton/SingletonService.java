package hello.core.singleton;

public class SingletonService {

    //이렇게 하면 class level에 올라가기 때문에 java static 영역에 대해 공부해야함
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱클톤 객체 로직 호출");
    }

}
