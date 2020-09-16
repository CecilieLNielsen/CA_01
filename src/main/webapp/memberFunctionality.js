//OPGAVE 1 - MEMBERS TABLE

function createMembersTable(data) {
    console.log(data);
    const headers = "<table class=\"table\"><tr><th>Name</th><th>Student ID</th><th>Favourite Tv-show</th></tr>";

    let mappedRows = data.map(member => "<tr><td>" + member.name + "</td><td>" + member.studentId + "</td><td>" + member.favTvShow + "</td></tr>");
    let joinedRows = mappedRows.join("");
    document.getElementById("memberTable").innerHTML = headers + joinedRows + "</table>";

}

function fetchMembers() {
    const url = "api/groupmembers/all";
    fetch(url)
            .then(res => res.json())
            .then(data => createMembersTable(data));

}

fetchMembers();

document.getElementById("reloadNames").addEventListener("click", function (event) {
    event.preventDefault();
    fetchMembers();
});

