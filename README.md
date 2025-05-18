# Library-Management-System

Welcome to the **Library Management System**, a fully menu-driven Java application designed to mimic core functionalities of a real-world library. This project is built purely using **core Java**, without any external libraries or databases — ideal for learning object-oriented programming, Java collections, and command-line interfaces.

---

## 🧾 Table of Contents

- [📌 Features]
- [🛠 Technologies Used]
- [📂 Project Structure]
- [▶️ How to Run]
- [🔍 Functionality Explained]
- [🧠 Key Concepts Used]
- [📸 Sample Output]
- [🎯 Real-World Use Cases]
- [🚀 Future Enhancements]
- [👩‍💻 Author]
- [📄 License]

---

## 📌 Features

This Library Management System supports:

- ✅ **Add Books**: Add new books with title, author, genre, publisher, ID, and quantity.
- 📘 **View All Books**: Lists all books along with their details and available quantities.
- 🔍 **Search Books**: Search by any property — title, author, ID, genre, or publisher.
- 🛠 **Update Book Info**: Dynamically update any field of any book, including quantity.
- ❌ **Delete Book**: Remove a book from the system by its ID.
- 📤 **Issue Book**: Issue a book to a student, with tracking of issue date and quantity.
- 📥 **Return Book**: Accept a returned book and compute **late fee** if returned after 7 days.
- 📦 **Check Book Quantity**: View remaining copies of a specific book.
- ⚠️ **Auto-remove**: Automatically deletes books from the system if quantity becomes zero.

---

## 🛠 Technologies Used

- **Java (JDK 8+)**
- **Java Collections Framework** – for managing book records
- **Scanner** – for reading console input
- **Date API (`java.time`)** – to calculate late fee
- **OOP principles** – encapsulation, modularity, class/object design

---

## 📂 Project Structure

📁 Library-Management-System
│
├── 📄 LibrarySystem.java     # Main Java source file
└── 📄 README.md              # Project documentation (you’re reading it!)

---

## ▶️ How to Run

1. **Clone this repo**:

   ```bash
   git clone https://github.com/yourusername/Library-Management-System-Java.git
   cd Library-Management-System-Java
   ```

2. **Compile the Java code**:

   ```bash
   javac LibrarySystem.java
   ```

3. **Run the application**:

   ```bash
   java LibrarySystem
   ```

---

## 🔍 Functionality Explained

### 📘 Book Management

Each book has the following properties:

* Book ID
* Title
* Author
* Genre
* Publisher
* Quantity
* Issue Date (when issued)

Books are stored using a `HashMap<String, Book>` where the key is the **Book ID** for fast retrieval.

### 🔍 Searching

Search is implemented across all fields:

* The system checks user input against **title, author, ID, genre, and publisher** using `equalsIgnoreCase()`.

### 🛠 Updating Books

The `updateBook()` method:

* Prompts the user to select which field to update
* Allows updating multiple fields one after another
* Quantity can also be modified directly

### 📤 Issuing Books

* Only allows issuing if quantity > 0
* Stores the current date as `issueDate`
* Reduces book quantity by 1

### 📥 Returning Books

* Calculates the number of days since issue
* Computes late fee of ₹2/day after 7 days
* Increments book quantity by 1
* Automatically deletes the book if quantity reaches zero

---

## 🧠 Key Concepts Used

* ✅ **OOP in Java** – Class design, methods, and encapsulation
* ✅ **Menu-driven program** – Using `switch` and loops
* ✅ **Java Collections** – `HashMap` for data storage
* ✅ **Date Handling** – Using `LocalDate` and `ChronoUnit.DAYS.between`
* ✅ **Input Handling** – Using `Scanner` and robust user interaction

---

## 📸 Sample Output

===== Library Menu =====
1. Add Book
2. Display All Books
3. Search Book
4. Update Book
5. Delete Book
6. Issue Book
7. Return Book
8. Check Book Quantity
0. Exit
Enter your choice:

---

## 🎯 Real-World Use Cases

This project can be used in:

* College course assignments
* Internship coding assessments
* Learning Java collections and OOP
* Interview prep for Java roles

---

## 🚀 Future Enhancements

If you'd like to extend this project:

* 🔐 Add user roles (admin/student login)
* 🗃 Save data using file I/O or a database
* 📊 Generate PDF or CSV reports
* 💻 GUI version using JavaFX or Swing
* 🌐 Web version using Spring Boot

---

## 👩‍💻 Author

**Sravani Mamidi**
👩‍🎓 Master’s Student in Computer Science – Governors State University (USA)
💡 Passionate about full stack development, Java, and problem solving
📫 [LinkedIn](https://www.linkedin.com/in/sravani-mamidi) 
🌐 [GitHub](https://github.com/SRAVANI-HUB-07)

---

## 📄 License

This project is open-source and free to use under the MIT License.
