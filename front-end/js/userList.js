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
        // Optionally display an error message to the user
        const userContainer = document.getElementById('userContainer');
        userContainer.innerHTML = '<p>Failed to load user data. Please try again later.</p>';
    }
}

// Display users in card format
function displayUsers(users) {
    const userContainer = document.getElementById('userContainer');
    userContainer.innerHTML = ''; // Clear existing content

    if (users.length === 0) {
        userContainer.innerHTML = '<p>No users found.</p>';
        return;
    }

    users.forEach(user => {
        const userCard = document.createElement('div');
        userCard.classList.add('card');

        const userName = document.createElement('h2');
        userName.textContent = user.name;

        const userDetails = document.createElement('p');
        userDetails.textContent = `Phone: ${user.phone}, Email: ${user.email}`;

        userCard.appendChild(userName);
        userCard.appendChild(userDetails);

        // Check if user has cars
        if (user.cars && user.cars.length > 0) {
            user.cars.forEach(car => {
                const carInfo = document.createElement('div');
                carInfo.classList.add('car');
                carInfo.innerHTML = `
                    <strong>${car.manufacturer} ${car.model} (${car.year})</strong><br>
                    Color: ${car.color}, Plate: ${car.plate}, Type: ${car.type}
                `;
                userCard.appendChild(carInfo);

                // Check if the car has a history
                if (car.history && car.history.length > 0) {
                    const historyContainer = document.createElement('div');
                    historyContainer.classList.add('history');
                    historyContainer.innerHTML = '<strong>History:</strong>';

                    car.history.forEach(history => {
                        const historyEntry = document.createElement('div');
                        historyEntry.textContent = `Check-in: ${history.checkin}, Check-out: ${history.checkout}`;
                        historyContainer.appendChild(historyEntry);
                    });

                    userCard.appendChild(historyContainer);
                } else {
                    const noHistory = document.createElement('p');
                    noHistory.textContent = 'No history available for this car.';
                    userCard.appendChild(noHistory);
                }
            });
        } else {
            const noCars = document.createElement('p');
            noCars.textContent = 'No cars available for this user.';
            userCard.appendChild(noCars);
        }

        userContainer.appendChild(userCard);
    });
}

fetchUsers(); // Fetch and display users
