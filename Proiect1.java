import java.util.Scanner;
import java.lang.String;
import java.util.Random;

class Proiect1 {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int option = -1, i;
        System.out.print("How many books do you have in your library? ");
        int numOfBooks = scanner.nextInt();
        scanner.nextLine();
        Book[] books = new Book[numOfBooks];
        Random random = new Random();
        String title, author, genre;
        int pages;
        char sortOpt = '\0', searchOpt = '\0';
        int chosenBook = -1;
        int pagesToRead = -1;
        String searchFor = "";
        boolean found = false;
        Book temp = new Book("", "", "", 0);

        for (i = 0; i < books.length; i++) {
            System.out.println("Give the title, author, genre and number of pages of your book");
            books[i] = new Book(scanner.nextLine(), scanner.nextLine(), scanner.nextLine(), scanner.nextInt());
            scanner.nextLine();
        }
        System.out.println();

        for (i = 0; i < books.length; i++) {
            System.out.println(books[i].toString());
        }
        System.out.println();

        while (option != 0) {
            System.out.println(
                    "Choose an option:\n\t1. Add a new book\n\t2. Read something\n\t3. Sort your books\n\t4. Search for a book\n\t0. Exit");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    // se instantiaza un nou obiect de tip Book
                    // cu metodele set se seteaza proprietatile noului obiect
                    Book newBook = new Book("", "", "", 0);
                    System.out.print("What's your new book's title? ");
                    title = scanner.nextLine();
                    newBook.setTitle(title);
                    System.out.print("Who's your new book's author? ");
                    author = scanner.nextLine();
                    newBook.setAuthor(author);
                    System.out.print("What's your new book's genre? ");
                    genre = scanner.nextLine();
                    newBook.setGenre(genre);
                    System.out.print("How many pages does your new book have? ");
                    pages = scanner.nextInt();
                    newBook.setPages(pages);
                    // se creaza un nou array cu o marime mai mare ca cel precedent
                    Book[] newBooks = new Book[numOfBooks + 1];
                    // se copiaza elementele in noul array
                    for (i = 0; i < books.length; i++)
                        newBooks[i] = books[i];
                    // se adauga noul element la ultimul index
                    newBooks[numOfBooks] = newBook;
                    // se copiaza in arrayul initial
                    books = newBooks;
                    for (i = 0; i < books.length; i++)
                        System.out.println(books[i].toString());
                    break;
                case 2:
                    System.out.println("Which book would you like to read?");
                    for (i = 0; i < books.length; i++) {
                        System.out.println("\t" + (i + 1) + ". " + books[i].getTitle() + " by " + books[i].getAuthor());
                    }
                    chosenBook = scanner.nextInt();
                    pagesToRead = random.nextInt(100);
                    // se verifica daca este posibila citirea atator pagini cate au fost generate
                    // random
                    if (!books[chosenBook - 1].read(pagesToRead)) {
                        System.out.println("You want to read " + pagesToRead + " pages, but you only have "
                                + (books[chosenBook - 1].getPages() - books[chosenBook - 1].getPagesRead())
                                + " pages left!");
                    } else if (books[chosenBook - 1].getPages() == books[chosenBook - 1].getPagesRead()) {
                        System.out.println("You finished " + books[chosenBook - 1].getTitle());
                    } else {
                        System.out.println(
                                "You have " + (books[chosenBook - 1].getPages() - books[chosenBook - 1].getPagesRead())
                                        + " pages left of " + books[chosenBook - 1].getTitle());
                    }
                    break;
                case 3:
                    while (sortOpt != 'x') {
                        System.out.println(
                                "Choose an option:\n\ta. Sort alphabetically by title\n\tb. Sort alphabetically by author\n\tc. Sort by number of pages left to read\n\tx. Exit");
                        sortOpt = scanner.next().charAt(0);
                        // sortare prin selectie directa in functie de optiunea selectata
                        switch (sortOpt) {
                            case 'a':
                                for (i = 0; i < books.length; i++) {
                                    for (int j = i + 1; j < books.length; j++) {
                                        if (books[i].getTitle().compareTo(books[j].getTitle()) > 0) {
                                            temp = books[i];
                                            books[i] = books[j];
                                            books[j] = temp;
                                        }
                                    }
                                }
                                for (i = 0; i < books.length; i++) {
                                    System.out.println(books[i].getTitle() + " by " + books[i].getAuthor());
                                }
                                break;
                            case 'b':
                                for (i = 0; i < books.length; i++) {
                                    for (int j = i + 1; j < books.length; j++) {
                                        if (books[i].getAuthor().compareTo(books[j].getAuthor()) > 0) {
                                            temp = books[i];
                                            books[i] = books[j];
                                            books[j] = temp;
                                        }
                                    }
                                }
                                for (i = 0; i < books.length; i++) {
                                    System.out.println(books[i].getAuthor() + ", " + books[i].getTitle());
                                }
                                break;
                            case 'c':
                                for (i = 0; i < books.length; i++) {
                                    for (int j = i + 1; j < books.length; j++) {
                                        if ((books[i].getPages() - books[i].getPagesRead()) > (books[j].getPages()
                                                - books[j].getPagesRead())) {
                                            temp = books[i];
                                            books[i] = books[j];
                                            books[j] = temp;
                                        }
                                    }
                                }
                                for (i = 0; i < books.length; i++) {
                                    System.out.println("You have " + (books[i].getPages() - books[i].getPagesRead()
                                            + " pages left from " + books[i].getTitle()));
                                }
                                break;
                            case 'x':
                                break;
                            default:
                                System.out.println("Invalid option");
                        }
                    }
                    break;
                case 4:
                    while (searchOpt != 'x') {
                        found = false;
                        System.out.println("Choose an option:\n\ta. Search by title\n\tb. Search by author\n\tx. Exit");
                        searchOpt = scanner.next().charAt(0);
                        scanner.nextLine();
                        // cautare secventiala in functie de optiunea aleasa
                        switch (searchOpt) {
                            case 'a':
                                System.out.println("Which book are you searching for?");
                                searchFor = scanner.nextLine();
                                for (i = 0; i < books.length; i++) {
                                    if (books[i].getTitle().equals(searchFor)) {
                                        System.out.println(searchFor + " was found!");
                                        found = true;
                                        break;
                                    }
                                }
                                if (found == false) {
                                    System.out.println("You don't have " + searchFor + " in your library!");
                                }
                                break;
                            case 'b':
                                System.out.println("Which author's books are you searching for?");
                                searchFor = scanner.nextLine();
                                for (i = 0; i < books.length; i++) {
                                    if (books[i].getAuthor().equals(searchFor)) {
                                        System.out.println(books[i].getTitle() + " by " + searchFor + " was found!");
                                        found = true;
                                    }
                                }
                                if (found == false) {
                                    System.out
                                            .println("You don't have anything from " + searchFor + " in your library!");
                                }
                                break;
                            case 'x':
                                break;
                            default:
                                System.out.println("Invalid option");
                        }
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid option");
            }
            System.out.println();
        }
        scanner.close();
    }
}
