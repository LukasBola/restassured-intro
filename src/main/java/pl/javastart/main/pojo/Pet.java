
package pl.javastart.main.pojo;

import java.util.List;
import java.util.Objects;
import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class Pet {

    private Integer id;
    private Category category;
    private String name;
    private List<String> photoUrls = null;
    private List<Tag> tags = null;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id) && pet.category.equals(category) && Objects.equals(name, pet.name) && Objects.equals(photoUrls, pet.photoUrls) && pet.tags.equals(tags) && Objects.equals(status, pet.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, name, photoUrls, tags, status);
    }
}
