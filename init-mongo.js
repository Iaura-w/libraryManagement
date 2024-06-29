conn = new Mongo();
db = conn.getDB("library");

db.createUser(
    {
        user: "admin",
        pwd: "admin",
        roles: [
            {
                role: "readWrite",
                db: "library"
            }
        ]
    }
)

db.books.drop();
db.customers.drop();
db.loans.drop();

db.books.insertMany([
    {
        title: "Hamlet",
        author: "William Shakespeare",
        available: true,
        isbn: "12345"
    },
    {
        title: "War and Peace",
        author: "Leo Tolstoy",
        available: true,
        isbn: "24251"
    },
    {
        title: "The Odyssey",
        author: "Homer",
        available: true,
        isbn: "33441"
    },
    {
        title: "1984",
        author: "George Orwell",
        available: true,
        isbn: "67564"
    }
]);

db.customers.insertMany([
    {
        firstName: "John",
        lastName: "Doe",
        loanHistory: []
    },
    {
        firstName: "Alice",
        lastName: "Smith",
        loanHistory: []
    }
]);
