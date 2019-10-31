class BookInfo {
    title
    thumbnail
    content
    isbn
    author
    publisher
    publishingDate
    price
    salePrice

    constructor(title, thumbnail, content, isbn, author, publisher, publishingDate, price, salePrice) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.content = content;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        // this.publishingDate = moment(publishingDate, "YYYY-DD-MM");
        this.publishingDate = publishingDate;
        this.price = price;
        this.salePrice = salePrice;
    }
}

class Board {
    id;
    title;
    writer;
    creationDate;
    views;

    constructor(id, title, writer, creationDate, views) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.creationDate = creationDate;
        this.views = views;
    }
}

class Posts {
    id;
    title;
    writer;
    content;
    creationDate;
    modificationDate;
    views;

    constructor(id, title, writer, content, creationDate, modificationDate, views) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.views = views;
    }
}