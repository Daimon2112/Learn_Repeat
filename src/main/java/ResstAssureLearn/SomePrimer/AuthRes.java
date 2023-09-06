package ResstAssureLearn.SomePrimer;

import java.security.PublicKey;

public class AuthRes {
    public String token;

    public AuthRes(){//нужно добавлять пустой конструктор по умолчанию
    }

    public String getToken(){
        return token;
    }

    public AuthRes(String token){
        this.token = token;
    }


}
