document.getElementById("addNewUser").addEventListener("submit", addNewUser);

const urlNewUser = "http://localhost:8080/addUser";

function addNewUser(e){
    e.preventDefault();

    let name = document.getElementById("nameNew").value;
    let lastName = document.getElementById("lastNameNew").value;
    let age = document.getElementById("ageNew").value;
    let email = document.getElementById("emailNew").value;
    let password = document.getElementById("passwordNew").value;
    let roles = setRoles(Array.from(document.getElementById("roleNew").selectedOptions)
        .map(option => option.value));

    fetch(urlNewUser, {
        method: "POST",
        headers: {
            "Accept": "application/json, text/plain, */*",
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            name: name,
            lastName: lastName,
            age: age,
            email: email,
            password: password,
            roles: roles
        })
    })
        .finally(() => {
            document.getElementById("idUsersTable").click();
            getAllUsers();
            document.getElementById("addNewUser").reset();
        })
}

function setRoles(someRoles) {
    let roles = [];
    if (someRoles.indexOf("ROLE_ADMIN") >= 0) {
        roles.push({"id": 1, "role": "ROLE_ADMIN"});
    }
    if (someRoles.indexOf("ROLE_USER") >= 0) {
        roles.push({"id": 2, "role": "ROLE_USER"});
    }
    return roles;
}