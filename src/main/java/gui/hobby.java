package gui;

public class hobby {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

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

    public hobby(String id, String hobby){
        this.id = id;
        this.hobby = hobby;
    }

    public hobby(){

    }
}
