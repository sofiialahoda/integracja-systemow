package pl.pollub.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerDTO {

    @JsonProperty(value = "Team")
    private String team;
    @JsonProperty(value = "Number")
    private String number;
    @JsonProperty(value = "Position")
    private String position;
    @JsonProperty(value = "FullName")
    private String fullName;
    @JsonProperty(value = "Club")
    private String club;
    @JsonProperty(value = "ClubCountry")
    private String clubCountry;
    @JsonProperty(value = "DateOfBirth")
    private String dateOfBirth;
    @JsonProperty(value = "IsCaptain")
    private Boolean isCaptain;

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getClubCountry() {
        return clubCountry;
    }

    public void setClubCountry(String clubCountry) {
        this.clubCountry = clubCountry;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getCaptain() {
        return isCaptain;
    }

    public void setCaptain(Boolean captain) {
        isCaptain = captain;
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "team='" + team + '\'' +
                ", number='" + number + '\'' +
                ", position='" + position + '\'' +
                ", fullName='" + fullName + '\'' +
                ", club='" + club + '\'' +
                ", clubCountry='" + clubCountry + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", isCaptain=" + isCaptain +
                '}';
    }
}
