package entities;

public class Actor
{
    private String mid;
    private String name;
    private String character;

    public Actor() {

    }

    public String getMid() {
        return mid;
    }
    public void setMid(int mid) {
        this.mid = "" + mid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCharacter() {
        return character;
    }
    public void setCharacter(String character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + mid +
                ", name='" + name + '\'' +
                ", character='" + character + '\'' +
                '}';
    }
}
