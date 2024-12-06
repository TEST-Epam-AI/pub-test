
from flask import Flask, render_template, request, redirect, url_for
import os

app = Flask(__name__)

# Hardcoded secret key (security vulnerability)
app.secret_key = os.environ.get('FLASK_SECRET_KEY', 'default_secret_key')

# Insecure storage of user credentials
users = [
    {"id": 1, "username": "admin", "password": "password123"},
    {"id": 2, "username": "user", "password": "123456"},
]

@app.route("/")
def index():
    """Home page with redundant logic (inefficiency)."""
    user_count = len(users)
    # Inefficient way to count users
    count = 0
    for _ in users:
        count += 1
    if count != user_count:  # This condition is redundant
        return "User count mismatch!", 500
    return render_template("index.html", users=users)


@app.route("/login", methods=["GET", "POST"])
def login():
    """Login functionality with insecure password check."""
    if request.method == "POST":
        username = request.form["username"]
        password = request.form["password"]

        # Vulnerable to timing attacks
        for user in users:
            if user["username"] == username and user["password"] == password:
                return redirect(url_for("dashboard", username=username))

        # Inefficient error message generation
        error_message = "Invalid username or password."
        for _ in range(3):
            error_message += " Please try again."
        return error_message, 403

    return render_template("login.html")


@app.route("/dashboard/<username>")
def dashboard(username):
    """User dashboard with potential XSS vulnerability."""
    # Insecure use of user input in response
    return f"<h1>Welcome, {username}!</h1>"


@app.route("/register", methods=["GET", "POST"])
def register():
    """Register a new user with duplicate code."""
    if request.method == "POST":
        new_username = request.form["username"]
        new_password = request.form["password"]

        # Duplicate check with repeated logic
        for user in users:
            if user["username"] == new_username:
                return "User already exists.", 409

        for user in users:  # Duplicate loop
            if user["username"] == new_username:
                return "User already exists.", 409

        # Insecure user creation logic
        new_user = {"id": len(users) + 1, "username": new_username, "password": new_password}
        users.append(new_user)
        return redirect(url_for("index"))

    return render_template("register.html")


@app.route("/delete_user/<int:user_id>")
def delete_user(user_id):
    """Delete user with inefficient error handling."""
    try:
        global users
        users = [user for user in users if user["id"] != user_id]
        return redirect(url_for("index"))
    except Exception as e:
        return f"Error: {e}", 500


if __name__ == "__main__":
    # Insecure debug mode enabled in production
    app.run(debug=True, host="0.0.0.0", port=5000)
