# Event Counter App

## 📌 About the App
The **Event Counter App** is a simple yet powerful Android application that allows users to manually count and track occurrences of three distinct events. Whether you're tracking workout reps, daily habits, or any other activity, this app provides a seamless way to store and review event data in a user-friendly interface.

With **customizable event names**, a detailed **history log**, and **persistent data storage**, the Event Counter App is the perfect tool for users who need an efficient and lightweight event tracking solution.

---

## ✨ Features

✅ **Intuitive Event Counting:** Three customizable event buttons to record occurrences quickly.

✅ **Persistent Data Storage:** Uses `SharedPreferences` to save event counts and settings, even after closing the app.

✅ **Customizable Event Names:** Personalize event names through the **Settings** menu.

✅ **Detailed Event History:** View a **chronological list** of recorded events in the **History section**.

✅ **Toggle Display Mode:** Switch between event names and button numbers for better readability.

✅ **Smooth User Navigation:** Designed with a clean and user-friendly interface.

✅ **Error Handling:** Prevents invalid inputs (e.g., non-alphabetic names, exceeding max counts, etc.).

---

## 🛠 Installation & Setup

### **Try It Online!** 🚀
No need to install anything! You can test the app directly in your browser:
👉 **[Click here to run the app](https://appetize.io/app/b_z5aimjfz2ijemdgncsrwe2pwvm)**
The app will take to my setting page where you will need to name your event/counter/task first by selecting the menu bar and then get started!

### **Install on Your Device (APK Download)**
1. **Download the APK** from the latest [GitHub Releases](https://github.com/FabioKoshy/EventCounter-App/releases).
2. Open the file on your Android device.
3. If prompted, enable **“Install from Unknown Sources”** in settings.
4. Launch the app and start tracking your events!

---

## 🚀 Usage Guide

### 1️⃣ **Main Screen (MainActivity)**
- Tap any of the **three event buttons** to increase their respective count.
- View the **total event count** updated in real-time.
- Click **“Show My Counts”** to view event history.
- Click **Settings** to customize event names.

### 2️⃣ **Settings (SettingsActivity)**
- Default mode: **View event button names**.
- Edit mode: **Modify event names and set a max event count**.
- Press **Save** to apply changes.

### 3️⃣ **History (DataActivity)**
- View total counts of each event.
- Scroll through a **detailed list of past events**.
- Toggle between **Event Name Mode** and **Event Button # Mode** in the action bar.

---

## ⚠️ Error Handling & Restrictions
To ensure a smooth user experience, the app enforces the following constraints:

- **Event Button Names**: Only alphabetical characters and spaces (max 20 characters).
- **Max Event Count**: Can only be set between **5 and 200**.
- **Invalid Inputs**: If invalid data is entered, a **Toast message** is displayed, and the data is not saved.

---

## 🤝 Contributing
Want to improve the app? Feel free to fork the repo and submit a pull request!

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -m 'Added new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a pull request.

---

## 📝 License
This project is open-source and available under the **MIT License**.

---

## 📬 Contact
For any questions or feedback, reach out via GitHub Issues or email me at `your.email@example.com`.

🚀 **Happy Counting!**

