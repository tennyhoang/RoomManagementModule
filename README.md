
---

```markdown
 🏨 Room Management Module – ATZ Resort System

This is a Java-based room management module developed for the ATZ Resort Complex, a hospitality facility offering accommodation, dining, and wellness services. The module manages rooms and guest stays using Object-Oriented Programming (OOP) principles.

---

 🚀 Features

- 📥 Import room data from a text file
- 📃 Display available room list
- 🧑 Register new guest information
- ✏️ Update guest stay details
- 🔍 Search guest by national ID
- ❌ Cancel reservation before check-in
- 🛏️ List vacant rooms
- 📆 Generate monthly revenue report
- 🧾 Revenue report by room type
- 💾 Save guest information to a binary file (`guestInfo.dat`)

---

 🧠 Technologies Used

- Java SE 8+
- OOP Principles: Abstraction, Encapsulation, Inheritance, Polymorphism
- File I/O (Text & Binary)
- Console-based menu interface

---

 📁 Project Structure

```

/model
├── Room.java
├── Guest.java

/business
├── Rooms.java
├── Guests.java

/tool
├── Inputter.java
├── Acceptable.java

/data
├── Active\_Room\_List.txt
├── guestInfo.dat

RoomManagementModule.java   # Main entry point

````

---

 🛠️ How to Run

```bash
javac RoomManagementModule.java
java RoomManagementModule
````

Make sure the file `Active_Room_List.txt` is present inside the `/data` directory before running the application.

---

📌 Notes

* All guest and room data is saved using Java object serialization.
* Includes input validation for IDs, dates, names, and room formats.
* Built with a modular structure for maintainability.

---

 📄 License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

```

---


