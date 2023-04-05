// Get the "Manage Students" link and submenu
const studentLink = document.querySelector(".student > a");
const studentSubmenu = document.querySelector(".student > .submenu");
const studentSubmenuItems = document.querySelectorAll(".student .submenu li");
const storedUsername = localStorage.getItem("username");
const storedPassword = localStorage.getItem("password");

// Add a click event listener to the "Manage Students" link
studentLink.addEventListener("click", function (event) {
	event.preventDefault();

	// Toggle the active class on the "Manage Students" link
	studentLink.classList.toggle("active");

	// Toggle the display of the submenu
	studentSubmenu.style.display =
		studentSubmenu.style.display === "block" ? "none" : "block";
});

// Add a hover event listener to the submenu items
studentSubmenuItems.forEach(function (item) {
	item.addEventListener("mouseover", function () {
		item.style.backgroundColor = "#ccc";
	});
	item.addEventListener("mouseout", function () {
		item.style.backgroundColor = "";
	});
});

// get the create-student element
const createStudent = document.querySelector("#create-student");

// get the create-student-form-container element
const createStudentForm = document.querySelector(
	".create-student-form-container"
);

// add a click event listener to the create-student element
createStudent.addEventListener("click", () => {
	// show the create-student-form-container element
	createStudentForm.style.display = "block";
	updateStudentForm.style.display = "none";
	deleteStudentForm.style.display = "none";
	GetStudentForm.style.display = "none";
});

// get the form and message elements
const form = document.querySelector("#create-student-form");
const message = document.querySelector("#message");

// add a submit event listener to the form
form.addEventListener("submit", function (event) {
	event.preventDefault();
	const name = form.elements.name.value;
	const email = form.elements.email.value;

	fetch("http://localhost:8080/api/Students", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
			Authorization:
				"Basic " + btoa(storedUsername + ":" + storedPassword),
		},
		body: JSON.stringify({
			name: name,
			email: email,
		}),
	})
		.then((response) => response.json())
		.then((data) => {
			message.textContent = `Student ${data.name} created successfully!`;
			form.reset();
			message.style.display = "block";
		})
		.catch((error) => {
			message.textContent = "Error creating student.";
			console.error(error);
			message.style.display = "block";
		});
});

// get the update student form container
const updateStudentForm = document.querySelector(
	".update-student-form-container"
);

// add event listener to the update student link
const updateStudentLink = document.querySelector("#update-student");
updateStudentLink.addEventListener("click", (event) => {
	// prevent default behavior of link
	event.preventDefault();

	// hide create student form and show update student form
	createStudentForm.style.display = "none";
	deleteStudentForm.style.display = "none";
	updateStudentForm.style.display = "block";
	GetStudentForm.style.display = "none";
});
// get the form and message elements
const Updateform = document.querySelector("#update-student-form");
const updateMessage = document.getElementById("update-message");
// add a submit event listener to the form
Updateform.addEventListener("submit", function (event) {
	event.preventDefault();
	const id = Updateform.elements.id.value;
	const name = Updateform.elements.name.value;
	const email = Updateform.elements.email.value;

	fetch(`http://localhost:8080/api/Students/${id}`, {
		method: "PUT",
		headers: {
			"Content-Type": "application/json",
			Authorization:
				"Basic " + btoa(storedUsername + ":" + storedPassword),
		},
		body: JSON.stringify({
			id: id,
			name: name,
			email: email,
		}),
	})
		.then((response) => response.json())
		.then((data) => {
			updateMessage.textContent = `Student ${data.name} Updated successfully!`;
			Updateform.reset();
			updateMessage.style.display = "block";
		})
		.catch((error) => {
			updateMessage.textContent = "Error Updating student.";
			console.error(error);
			updateMessage.style.display = "block";
		});
});
// get the delete student form container
const deleteStudentForm = document.querySelector(
	".delete-student-form-container"
);

// hide the delete student form by default
deleteStudentForm.style.display = "none";

// add event listener to the delete student link
const deleteStudentLink = document.querySelector("#delete-student");
deleteStudentLink.addEventListener("click", (event) => {
	// prevent default behavior of link
	event.preventDefault();

	// hide create student form and show delete student form
	createStudentForm.style.display = "none";
	updateStudentForm.style.display = "none";
	deleteStudentForm.style.display = "block";
	GetStudentForm.style.display = "none";
});

// get the form and message elements for the delete form
const deleteform = document.querySelector("#delete-student-form");
const deleteMessage = document.getElementById("delete-message");

// add a submit event listener to the delete form
deleteform.addEventListener("submit", function (event) {
	event.preventDefault();
	const id = deleteform.elements.id.value;

	fetch(`http://localhost:8080/api/Students/${id}`, {
		method: "DELETE",
		headers: {
			"Content-Type": "application/json",
			Authorization:
				"Basic " + btoa(storedUsername + ":" + storedPassword),
		},
		body: JSON.stringify({
			id: id,
		}),
	})
		.then((response) => response.json())
		.then((data) => {
			deleteMessage.textContent = `Student ${data.name} Deleted successfully!`;
			deleteform.reset(); // reset the delete form
			deleteMessage.style.display = "block";
		})
		.catch((error) => {
			deleteMessage.textContent = "Error Deleting student.";
			console.error(error);
			deleteMessage.style.display = "block";
		});
});

const GetStudentForm = document.querySelector(".get-student-form-container");

// add event listener to the delete student link
const getAllStudentsLink = document.querySelector("#get-all-students");
getAllStudentsLink.addEventListener("click", (event) => {
	// prevent default behavior of link
	event.preventDefault();

	// hide create student form and show delete student form
	createStudentForm.style.display = "none";
	updateStudentForm.style.display = "none";
	deleteStudentForm.style.display = "none";
	GetStudentForm.style.display = "block";
});

// Add a click event listener to the "Get All Students" link
getAllStudentsLink.addEventListener("click", function (event) {
	event.preventDefault();

	// Fetch the list of students from your API
	fetch("http://localhost:8080/api/Students", {
		headers: {
			"Content-Type": "application/json",
			Authorization:
				"Basic " + btoa(storedUsername + ":" + storedPassword),
		},
	})
		.then((response) => response.json())
		.then((data) => {
			// Get the element where you want to render the list of students
			const studentList = document.querySelector("#student-list");

			// Create a table element and its header row
			const table = document.createElement("table");
			const headerRow = table.insertRow();
			const idHeader = headerRow.insertCell();
			const nameHeader = headerRow.insertCell();
			const emailHeader = headerRow.insertCell();
			idHeader.textContent = "ID";
			nameHeader.textContent = "Name";
			emailHeader.textContent = "Email";

			// Loop through each student in the data and add their information to the table
			data.forEach((student) => {
				const row = table.insertRow();
				const idCell = row.insertCell();
				const nameCell = row.insertCell();
				const emailCell = row.insertCell();
				idCell.textContent = student.id;
				nameCell.textContent = student.name;
				emailCell.textContent = student.email;
			});

			// Set the HTML of the studentList element to the table
			studentList.innerHTML = "";
			studentList.appendChild(table);
		})
		.catch((error) => console.error(error));
});
document.querySelector("#logout").addEventListener("click", function (event) {
	event.preventDefault();
	alert(`Are you sure you went to logout?.`);
	if (alert.apply) {
		localStorage.removeItem("username");
		localStorage.removeItem("password");
		window.location.href = "login (2).html";
	}
});
