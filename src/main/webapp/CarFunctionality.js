/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function createCarTable(data) {
    console.log(data);
    const headers = "<table class=\"table\"><tr><th>Year</th><th>Make</th><th>Model</th><th>Price</th></tr>";

    let mappedRows = data.map(car => "<tr><td>" + car.year + "</td><td>" + car.make + "</td><td>" + car.model + "</td><td>" + car.price + "</td></tr>");
    let joinedRows = mappedRows.join("");
    document.getElementById("carTable").innerHTML = headers + joinedRows + "</table>";
}

function getCars(){
     const url = "api/Car/all";
    fetch(url)
        .then(res => res.json())
        .then(data => createCarTable(data));
}

getCars();