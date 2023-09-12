package resstAssureLearn;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)//в данном случаи будет записаны данные только id email
//остальные проигнарируются а если не указывать анотацию тогда выпадет ошибка или тогда прописвать все
public class UserPojo {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private int id;
    private String email;

}
