//OPGAVE 1 - MEMBERS TABLE

// TODO: Delete
var members = [
    {
        "name": "Cecilie Nielsen",
        "studentId": "cph-cn274",
        "favTvShow": "Away",
    },
    {
        "name": "Abed Hariri",
        "studentId": "cph-ah156",
        "favTvShow": "My little pony",
    },
];

function createMembersTable(data) {
    const headers = "<table class=\"table\"><tr><th>Name</th><th>Student ID</th><th>Favourite Tv-show</th></tr>";

    let mappedRows = data.map(member => "<tr><td>" + member.name + "</td><td>" + member.studentId + "</td><td>" + member.favTvShow + "</td></tr>");
    let joinedRows = mappedRows.join("");
    document.getElementById("memberTable").innerHTML = headers + joinedRows + "</table>";

}

function fetchMembers() {
    const url = ""; //TODO: Change
    event.preventDefault() 
    fetch(url)
        .then(res => res.json())
        .then(data => createMembersTable(data));
    
}

createMembersTable(members);

document.getElementById("reloadNames").addEventListener("click", fetchMembers);

//OPGAVE 3 - JOKES

function createJokesTable(data) {
    const headers = "<table class=\"table\"><tr><th>Jokes</th></tr>";

    let mappedRows = data.map(joke => "<tr><td>" + joke.!!!!! + "</td></tr>");
    let joinedRows = mappedRows.join("");
    document.getElementById("jokeTable").innerHTML = headers + joinedRows + "</table>";

}

function fetchJokes() {
    const url = ""; //TODO: Change
    event.preventDefault() 
    fetch(url)
        .then(res => res.json())
        .then(data => createJokesTable(data));
    
}

document.getElementById("fetchJoke").addEventListener("click", fetchMembers);