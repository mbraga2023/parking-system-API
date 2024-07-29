// Edit user function
async function editUser(userId) {
    
    try {
        const response = await fetch(`https://parking-system-api-production-1618.up.railway.app/api/users/${userId}`);
        
        if (!response.ok) {
            throw new Error(`Error fetching user: ${response.statusText}`);
        }

        const user = await response.json();

        // Populate the form with user data
        document.querySelector('[name="name"]').value = user.name || '';
        document.querySelector('[name="phone"]').value = user.phone || '';
        document.querySelector('[name="email"]').value = user.email || '';

        // Populate car data (assuming only one car for simplicity)
        if (user.cars && user.cars.length > 0) {
            const car = user.cars[0]; // Get the first car from the user's car array
            document.querySelector('[name="carManufacturer"]').value = car.manufacturer || '';
            document.querySelector('[name="carModel"]').value = car.model || '';
            document.querySelector('[name="carColor"]').value = car.color || '';
            document.querySelector('[name="carYear"]').value = car.year || '';
            document.querySelector('[name="carPlate"]').value = car.plate || '';
            document.querySelector('[name="carType"]').value = car.type || '';
        } else {
            // Clear car fields if no cars
            document.querySelector('[name="carManufacturer"]').value = '';
            document.querySelector('[name="carModel"]').value = '';
            document.querySelector('[name="carColor"]').value = '';
            document.querySelector('[name="carYear"]').value = '';
            document.querySelector('[name="carPlate"]').value = '';
            document.querySelector('[name="carType"]').value = '';
        }

        // Store the user ID in a hidden field
        const userIdInput = document.createElement('input');
        userIdInput.type = 'hidden';
        userIdInput.name = 'userId';
        userIdInput.value = user.id; // Use user.id to store the correct ID
        document.getElementById('userForm').appendChild(userIdInput);

        // Show the modal
        document.getElementById("updateModal").style.display = "block";
    } catch (error) {
        console.error("Error fetching user:", error);
        showSnackbar("Failed to load user data. Please try again later.");
    }
}

// Close the modal
function closeModal() {
    document.getElementById("updateModal").style.display = "none";
}

// Submit the form to update the user
async function submitForm(event) {
    event.preventDefault();

    // Extract user ID from the hidden field
    const userId = event.target.querySelector('[name="userId"]').value;

    const formData = new FormData(event.target);
    const user = {
        name: formData.get("name"),
        phone: formData.get("phone"),
        email: formData.get("email"),
        cars: [
            {
                manufacturer: formData.get("carManufacturer"),
                model: formData.get("carModel"),
                color: formData.get("carColor"),
                year: parseInt(formData.get("carYear")),
                plate: formData.get("carPlate"),
                type: formData.get("carType"),
            },
        ],
    };

    try {
        const response = await fetch(`https://parking-system-api-production-1618.up.railway.app/api/users/${userId}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(user),
        });

        if (response.ok) {
            showSnackbar("User updated successfully!");
            closeModal(); // Close the modal
            fetchUsers(); // Refresh the user list
        } else {
            throw new Error(`Failed to update user. Status: ${response.status}`);
        }
    } catch (error) {
        console.error("Error updating user:", error);
        showSnackbar("Failed to update user. Please try again later.");
    }
}
