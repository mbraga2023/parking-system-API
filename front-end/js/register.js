function submitForm(event) {
  event.preventDefault(); // Prevent the form from submitting normally

  
  const formData = new FormData(document.getElementById("userForm"));
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
              history: [
                  {
                      checkin: formData.get("historyCheckin"),
                      checkout: formData.get("historyCheckout"),
                  }
              ]
          }
      ]
  };

  fetch("https://parking-system-api-production-1618.up.railway.app/api/users", {
      method: "POST",
      headers: {
          "Content-Type": "application/json",
      },
      body: JSON.stringify(user),
  })
  .then((response) => {
      if (!response.ok) {
          // Log the response status and throw an error
          console.error('Response status:', response.status);
          return response.json().then(errorData => {
              throw new Error(`Error: ${errorData.message || 'Unknown error'}`);
          });
      }
      return response.json();
  })
  .then((data) => {
      showSnackbar("User created successfully!");
      clearForm(); // Clear the form after successful submission
      console.log(data);
  })
  .catch((error) => {
      console.error("Error:", error);
      showSnackbar("An error occurred while creating the user.");
  });
}

// Function to show the snackbar
function showSnackbar(message) {
  const snackbar = document.getElementById("snackbar");
  snackbar.textContent = message;
  snackbar.style.display = "block";
  setTimeout(() => {
      snackbar.style.display = "none";
  }, 3000);
}

// Function to clear the form
function clearForm() {
  document.getElementById("userForm").reset();
}
