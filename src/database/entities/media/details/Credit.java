package database.entities.media.details;

public class Credit extends MediaDetails {
    private String name;
    private String role;
    private double popularity;

    public Credit() {

    }

    public Credit(int entityId, String name, String role, double popularity) {
        super(entityId);

        this.name = name;
        this.role = role;
        this.popularity = popularity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "entityId=" + mediaId +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", popularity=" + popularity +
                '}';
    }
}
