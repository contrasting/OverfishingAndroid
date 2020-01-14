package dating.overfishing.data;

import java.io.Serializable;
import java.util.List;

public class UserProfile implements Serializable {

    private List<String> profileImages;

    private String id;
    private String name;
    private Integer age;
    private Integer distance;
    private Integer height; // in cm
    private String about;
    private String school;
    private String occupation;
    // etc

    public UserProfile(List<String> profileImages, String id, String name, Integer age, Integer distance, Integer height, String about, String school, String occupation) {
        this.profileImages = profileImages;
        this.id = id;
        this.name = name;
        this.age = age;
        this.distance = distance;
        this.height = height;
        this.about = about;
        this.school = school;
        this.occupation = occupation;
    }

    public List<String> getProfileImages() {
        return profileImages;
    }

    public String getAbout() {
        return about;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getDistance() {
        return distance;
    }

    public String getSchool() {
        return school;
    }

    public Integer getHeight() {
        return height;
    }

    public String getOccupation() {
        return occupation;
    }
}
