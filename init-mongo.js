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
        loanHistory: []
    },
    {
        title: "War and Peace",
        author: "Leo Tolstoy",
        available: true,
        loanHistory: []
    },
    {
        title: "The Odyssey",
        author: "Homer",
        available: true,
        loanHistory: []
    },
    {
        title: "1984",
        author: "George Orwell",
        available: true,
        loanHistory: []
    }
]);

db.customers.insertMany([
    {
        firstName: "John",
        lastName: "Doe",
        borrowedBooks: [],
        loanHistory: []
    },
    {
        firstName: "Alice",
        lastName: "Smith",
        borrowedBooks: [],
        loanHistory: []
    }
]);
