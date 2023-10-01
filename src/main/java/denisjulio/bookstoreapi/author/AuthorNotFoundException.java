package denisjulio.bookstoreapi.author;

public class AuthorNotFoundException extends RuntimeException {

  private final String title;

  public AuthorNotFoundException() {
    super("Could not find an Author with the given 'id'");
    this.title = "Author not found";
  }

  public String getTitle() {
    return title;
  }
}
