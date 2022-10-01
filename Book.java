public class Book {
    private String title;
    private String author;
    private String genre;
    private int pages;
    private int pagesRead;

    public Book(String title, String author, String genre, int pages) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pages = pages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setPagesRead(int pagesRead) {
        this.pagesRead = pagesRead;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getPages() {
        return pages;
    }

    public int getPagesRead() {
        return pagesRead;
    }

    public boolean checkPages(int pagesToRead) {
        if (pagesRead + pagesToRead > pages)
            return false;
        return true;
    }

    public boolean read(int pagesToRead) {
        if (!checkPages(pagesToRead))
            return false;
        pagesRead += pagesToRead;
        return true;
    }

    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append("\nTitle:  ");
        str.append(title);
        str.append("\nAuthor: ");
        str.append(author);
        str.append("\nGenre:  ");
        str.append(genre);
        str.append("\nPages:  ");
        str.append(pages);
        return str.toString();
    }
}