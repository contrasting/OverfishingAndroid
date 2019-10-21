package dating.overfishing.data;

import java.util.List;

public class UserProfile {

    private List<String> profileImages;

    private String name;
    private Integer age;
    private Integer distance;
    private String about;
    private String school;
    // etc

    public UserProfile(List<String> profileImages, String name, Integer age, Integer distance, String about, String school) {
        this.profileImages = profileImages;
        this.name = name;
        this.age = age;
        this.distance = distance;
        this.about = about;
        this.school = school;
    }

    public List<String> getProfileImages() {
        return profileImages;
    }

    public String getAbout() {
        return about;
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
}
