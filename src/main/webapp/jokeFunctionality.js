//OPGAVE 3 - JOKES

function createJokesTable(data) {
    console.log(data);
    const headers = "<table class=\"table\"><tr><th>Joke</th><th>Type</th><th>Reference</th></tr>";

    let mappedRows = data.map(joke => "<tr><td>" + joke.the_joke + "</td><td>" + joke.type + "</td><td>" + joke.reference + "</td></tr>");
    let joinedRows = mappedRows.join("");
    document.getElementById("jokeTable").innerHTML = headers + joinedRows + "</table>";

}


function createJokeParagraph(data) {
    console.log(data);
    document.getElementById("fetchedJoke").innerHTML = data.the_joke;

}

function fetchJokes() {
    const url = "api/joke/all";
    fetch(url)
        .then(res => res.json())
        .then(data => createJokesTable(data));
}

 function fetchJoke() {
    const jokeID = document.getElementById("inputId").value;
    const url = "api/joke/" + jokeID;
    fetch(url)
        .then(res => res.json())
        .then(data => createJokeParagraph(data));    
}

function fetchRandomJoke() {
    const url = "api/joke/random";
    fetch(url)
        .then(res => res.json())
        .then(data => createJokeParagraph(data));
}

fetchJokes();

document.getElementById("fetchJoke").addEventListener("click", function (event) {
    event.preventDefault();
    fetchJoke();
});
document.getElementById("randomJoke").addEventListener("click", function (event) {
    event.preventDefault();
    fetchRandomJoke();
});