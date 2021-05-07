const urlUser = "http://localhost:8080/allUser";
const urlRoles = "http://localhost:8080/allRoles";

function getAllUsers() {
    fetch(urlUser)
        .then((res) => res.json())
        .then((data) => {
            let output = "";
            data.forEach(function (user) {
                let userRoles = "";
                for (let i = 0; i < user.roles.length; i++) {
                    userRoles += `${user.roles[i].role.replace("ROLE_","")} `
                }
                output += `
                <tr>
                <td id="id${user.id}">${user.id}</td>
                <td id="name${user.id}">${user.name}</td> 
                <td id="lastName${user.id}">${user.lastName}</td>
                <td id="age${user.id}">${user.age}</td>
                <td id="email${user.id}">${user.email}</td>
                <td id="roles${user.id}">${userRoles}</td>
                <td>
                <a class="btn btn-info" role="button"
                data-toggle="modal" data-target="#edit" id="callModalEdit"
                onclick="openModalWindow(${user.id})">Edit</a>
                </td>
                <td>
                <a class="btn btn-danger" role="button"
                data-toggle="modal" data-target="#delete" id="delete-post"
                onclick="openModalWindowDel(${user.id})">Delete</a>
                </td>
              </tr>
          `;
            });
            document.getElementById("allUsers").innerHTML = output;
        })
}

function getAllRoles() {
    fetch(urlRoles).then((res) => res.json())
        .then((data) => {
            let output = "";
            data.forEach(function (role) {
                output += `<option>${role.role}</option>`;
            });
            document.getElementById("roleNew").innerHTML;
            document.getElementById("roleEdit").innerHTML = output;
            document.getElementById("roleDelete").innerHTML = output;
        })
}

getAllUsers()
getAllRoles()