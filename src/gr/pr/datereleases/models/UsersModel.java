package gr.pr.datereleases.models;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "users", schema = "test_db", catalog = "test_db")
public class UsersModel {

    private int id;
    private String userName;
    private String password;
    private String imageUrl;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Collection<UsersFavoritesSeriesModel> usersFavoritesSeriesModels;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 45, unique = true)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "img_url", length = 45)
    public String getImageUrl(){
        return imageUrl;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    @Basic
    @Column(name = "first_name", length = 45)
    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "middle_name", length = 45)
    public String getMiddleName(){
        return middleName;
    }

    public void setMiddleName(String middleName){
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "last_name", length = 45)
    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email", length = 255, nullable = false, unique = true)
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }


    public Collection<UsersFavoritesSeriesModel> getUsersFavoritesSeriesModels(){
        return usersFavoritesSeriesModels;
    }

    public void setUsersFavoritesSeriesModels(Collection<UsersFavoritesSeriesModel> usersFavoritesSeriesModels){
        this.usersFavoritesSeriesModels = usersFavoritesSeriesModels;
    }

    @Override
    public String toString(){
        return "UsersModel{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof UsersModel)) return false;

        UsersModel that = (UsersModel) o;

        if (getId() != that.getId()) return false;
        if (getUserName() != null ? !getUserName().equals(that.getUserName()) : that.getUserName() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(that.getPassword()) : that.getPassword() != null)
            return false;
        if (getImageUrl() != null ? !getImageUrl().equals(that.getImageUrl()) : that.getImageUrl() != null)
            return false;
        if (getFirstName() != null ? !getFirstName().equals(that.getFirstName()) : that.getFirstName() != null)
            return false;
        if (getMiddleName() != null ? !getMiddleName().equals(that.getMiddleName()) : that.getMiddleName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(that.getLastName()) : that.getLastName() != null)
            return false;
        return getEmail() != null ? getEmail().equals(that.getEmail()) : that.getEmail() == null;
    }

    @Override
    public int hashCode(){
        int result = getId();
        result = 31 * result + (getUserName() != null ? getUserName().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getImageUrl() != null ? getImageUrl().hashCode() : 0);
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getMiddleName() != null ? getMiddleName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        return result;
    }
}
