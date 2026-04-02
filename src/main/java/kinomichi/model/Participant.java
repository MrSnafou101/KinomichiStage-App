package kinomichi.model;

import java.util.Objects;

public class Participant {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String club;
    private ParticipantType type;

    public Participant(String firstName, String lastName, String phone, String email, String club, ParticipantType type){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.club = club;
        this.type = type;
    }

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getPhone() {return phone; }
    public void setPhone(String phone) {this.phone = phone;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getClub() {return club;}
    public void setClub(String club) {this.club = club;}

    public ParticipantType getType() {return type;}
    public void setType(ParticipantType type) {this.type = type;}


    public String toString(){
        return "Nom: %s %s |telephone: %s |email: %s |club: %s |type: %s"
                .formatted(this.firstName,this.lastName, this.phone, this.email, this.club,this.type.name());
    }

    public boolean equals(Object o){
        return o!= null && o.getClass() == this.getClass()
                && Objects.equals(this.firstName, ((Participant) o).firstName)
                && Objects.equals(this.lastName, ((Participant) o).lastName);
    }
    public int hashCode(){
        return this.firstName.hashCode() + this.lastName.hashCode();
    }

    public String toSaveString() {
        //**firstname;lastname;phoneNumber;email;clubName;participantType
        return "**%s;%s;%s;%s;%s;%s".formatted(this.firstName, this.lastName,this.phone,this.email,this.club,this.type.toString());
    }
}
