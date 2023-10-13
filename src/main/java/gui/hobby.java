package gui;

public class hobby {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int id;

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return hobby;
    }

    String hobby;

    public hobby(int id, String hobby){
        this.id = id;
        this.hobby = hobby;
    }

    public hobby(){

    }
}
