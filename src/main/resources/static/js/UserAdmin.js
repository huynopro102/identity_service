document.addEventListener("DOMContentLoaded", () => {
    const addUserForm = document.getElementById("addUserForm");
    const updateUserForm = document.getElementById("updateUserForm");
    const cancelUpdateButton = document.getElementById("cancelUpdate");
    const userTable = document.getElementById("userTable");

    const apiUrl = "/api/users";

    // Load danh sách người dùng
    function loadUsers() {
        fetch(apiUrl)
            .then((response) => response.json())
            .then((data) => {
                userTable.innerHTML = "";
                data.result.forEach((user) => {
                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${user.dob}</td>
                        <td>
                            <button class="btn btn-warning btn-sm updateBtn" data-username="${user.username}">Update</button>
                            <button class="btn btn-danger btn-sm deleteBtn" data-username="${user.username}">Delete</button>
                        </td>
                    `;
                    userTable.appendChild(row);
                });

                // Gắn sự kiện cho nút Update
                document.querySelectorAll(".updateBtn").forEach((button) => {
                    button.addEventListener("click", () => {
                        const username = button.getAttribute("data-username");
                        loadUserForUpdate(username);
                    });
                });

                // Gắn sự kiện cho nút Delete
                document.querySelectorAll(".deleteBtn").forEach((button) => {
                    button.addEventListener("click", () => {
                        const username = button.getAttribute("data-username");
                        deleteUser(username);
                    });
                });
            });
    }

    // Thêm người dùng
    addUserForm.addEventListener("submit", (event) => {
        event.preventDefault();
        const formData = new FormData(addUserForm);
        const user = Object.fromEntries(formData.entries());

        fetch(apiUrl, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(user),
        }).then(() => {
            addUserForm.reset();
            loadUsers();
        });
    });

    // Hiển thị form cập nhật với dữ liệu người dùng hiện tại
    function loadUserForUpdate(username) {
        fetch(`${apiUrl}/${username}`)
            .then((response) => response.json())
            .then((user) => {
                document.getElementById("updateUsername").value = user.username;
                document.getElementById("updateEmail").value = user.email;
                document.getElementById("updateDob").value = user.dob;
                document.getElementById("updatePassword").value = ""; // Clear password
                addUserForm.classList.add("d-none");
                updateUserForm.classList.remove("d-none");
            });
    }

    // Cập nhật người dùng
    updateUserForm.addEventListener("submit", (event) => {
        event.preventDefault();
        const formData = new FormData(updateUserForm);
        const user = Object.fromEntries(formData.entries());

        fetch(`${apiUrl}/${user.username}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(user),
        }).then(() => {
            updateUserForm.reset();
            addUserForm.classList.remove("d-none");
            updateUserForm.classList.add("d-none");
            loadUsers();
        });
    });

    // Hủy cập nhật
    cancelUpdateButton.addEventListener("click", () => {
        updateUserForm.reset();
        addUserForm.classList.remove("d-none");
        updateUserForm.classList.add("d-none");
    });

    // Xóa người dùng
    function deleteUser(username) {
        fetch(`${apiUrl}/${username}`, { method: "DELETE" }).then(() => {
            loadUsers();
        });
    }

    // Load danh sách người dùng ban đầu
    loadUsers();
});
