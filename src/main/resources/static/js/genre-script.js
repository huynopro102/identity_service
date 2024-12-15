// Base URL for your API
const BASE_URL = 'http://localhost:8080/api/genres';

// Fetch and display all genres
async function fetchGenres() {
    try {
        const response = await fetch(BASE_URL);
        const genres = await response.json();
        const tableBody = document.getElementById('genreTableBody');
        tableBody.innerHTML = ''; // Clear existing rows

        genres.forEach(genre => {
            const row = `
                <tr>
                    <td>${genre.id}</td>
                    <td>${genre.name}</td>
                    <td>
                        <button onclick="editGenre(${genre.id}, '${genre.name}')" class="btn btn-sm btn-warning">Edit</button>
                        <button onclick="deleteGenre(${genre.id})" class="btn btn-sm btn-danger">Delete</button>
                    </td>
                </tr>
            `;
            tableBody.innerHTML += row;
        });
    } catch (error) {
        console.error('Error fetching genres:', error);
        alert('Failed to fetch genres');
    }
}

// Add new genre
async function addGenre(event) {
    event.preventDefault();
    const genreNameInput = document.getElementById('genreName');
    const genreId = document.getElementById('editGenreId').value;

    const genreData = {
        genrename: genreNameInput.value
    };

    try {
        let response;
        if (genreId) {
            // Update existing genre
            response = await fetch(`${BASE_URL}/${genreId}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ name: genreNameInput.value })
            });
        } else {
            // Add new genre
            response = await fetch(BASE_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(genreData)
            });
        }

        const result = await response.json();

        // Reset form
        genreNameInput.value = '';
        document.getElementById('editGenreId').value = '';

        // Refresh genre list
        fetchGenres();
    } catch (error) {
        console.error('Error:', error);
        alert('Failed to add/update genre');
    }
}

// Edit genre
function editGenre(id, name) {
    const genreNameInput = document.getElementById('genreName');
    const editGenreId = document.getElementById('editGenreId');

    genreNameInput.value = name;
    editGenreId.value = id;
}

// Delete genre
async function deleteGenre(id) {
    if (!confirm('Are you sure you want to delete this genre?')) return;

    try {
        const response = await fetch(`${BASE_URL}/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            fetchGenres(); // Refresh the list
        } else {
            alert('Failed to delete genre');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Failed to delete genre');
    }
}

// Initialize
document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('genreForm').addEventListener('submit', addGenre);
    fetchGenres(); // Load genres on page load
});