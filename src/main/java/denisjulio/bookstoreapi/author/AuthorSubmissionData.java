package denisjulio.bookstoreapi.author;

import com.fasterxml.jackson.annotation.JsonProperty;
import denisjulio.bookstoreapi.common.validation.DateString;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AuthorSubmissionData {

  @NotNull(message = "invalid.name.authorSubmissionData")
  @Size(max = 255)
  @JsonProperty("name")
  private String name;

  @DateString(message = "invalid.dateString.authorSubmissionData")
  @JsonProperty("birth_date")
  private String birthDate;

  @Size(max = 255)
  @JsonProperty("country_name")
  private String countryName;

  @JsonProperty("biography")
  private String biography;

  public AuthorSubmissionData() {
  }

  public AuthorSubmissionData(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(String birthDate) {
    this.birthDate = birthDate;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public String getBiography() {
    return biography;
  }

  public void setBiography(String biography) {
    this.biography = biography;
  }
}
