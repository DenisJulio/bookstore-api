/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package denisjulio.bookstoreapi.book;

class BookNotFoundException extends RuntimeException {

  public BookNotFoundException(Integer bookId) {
    super("Book with id=" + bookId + " not found");
  }
}
