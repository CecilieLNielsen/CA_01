//OPGAVE 3 - JOKES

function createJokesTable(data) {
    const headers = "<table class=\"table\"><tr><th>Joke ID</th><th>Jokes</th></tr>";

    let mappedRows = data.map(joke => "<tr><td>" + joke.id + "</td><td>" + joke.joke + "</td></tr>");
    let joinedRows = mappedRows.join("");
    document.getElementById("jokeTable").innerHTML = headers + joinedRows + "</table>";

}

function fetchJokes() {
    const url = "api/joke/all"; //TODO: Change
    event.preventDefault() 
    fetch(url)
        .then(res => res.json())
        .then(data => createJokesTable(data));
    
}

 function fetchJoke() {
    const jokeID = document.getElementById("inputId").value;
    const url = "api/joke/"; //TODO: Change
    event.preventDefault() 
    fetch(url + jokeID)
        .then(res => res.json())
        .then(data => createJokesTable(data));

    
}
function fetchRandomJoke() {
    const url = "api/joke/random"; //TODO: Change
    event.preventDefault() 
    fetch(url)
        .then(res => res.json())
        .then(data => createJokesTable(data));
    
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