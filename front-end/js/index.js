
// Fetch user data from the API and display it
async function fetchUsers() {
    
    try {
        const response = await fetch('https://parking-system-api-production-1618.up.railway.app/api/users');

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const users = await response.json();
        displayUsers(users);
    } catch (error) {
        console.error("Error fetching users:", error);
        showSnackbar("Failed to load user data. Please try again later.");
    }
}

// Display users in table format
function displayUsers(users) {
    const userTableBody = document.getElementById('userTableBody');
    userTableBody.innerHTML = ''; // Clear existing content

    if (users.length === 0) {
        userTableBody.innerHTML = '<tr><td colspan="4">No users found.</td></tr>';
        return;
    }

    users.forEach(user => {
        const userRow = document.createElement('tr');

        userRow.innerHTML = `
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>
                <button onclick="editUser(${user.id})">Edit</button>
                <button onclick="deleteUser(${user.id})">Delete</button>
            </td>
        `;

        userTableBody.appendChild(userRow);
    });
}


// Delete user function
async function deleteUser(userId) {
    // Confirm deletion
    const isConfirmed = confirm("Are you sure you want to delete this user?");
    if (!isConfirmed) {
        return; // Exit if the user cancels the action
    }

    try {
        const response = await fetch(`https://parking-system-api-production-1618.up.railway.app/api/users/${userId}`, {
            method: 'DELETE',
        });

        if (response.ok) {
            showSnackbar("User deleted successfully!");
            fetchUsers(); // Refresh the user list after deletion
        } else {
            throw new Error(`Failed to delete user. Status: ${response.status}`);
        }
    } catch (error) {
        console.error("Error deleting user:", error);
        showSnackbar("Failed to delete user. Please try again later.");
    }
}


// Show Snackbar function
function showSnackbar(message) {
    const snackbar = document.getElementById("snackbar");
    snackbar.textContent = message;
    snackbar.style.display = "block";
    setTimeout(() => {
        snackbar.style.display = "none";
    }, 3000);
}

// Initialize the page
fetchUsers(); // Fetch and display users
