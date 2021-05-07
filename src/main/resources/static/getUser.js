const url = "http://localhost:8080/getUser"
function getUser() {
    fetch(url).then((res) => res.json())
        .then((user) => {
            let userRoles = "";
            for (let i = 0; i < user.roles.length; i++) {
                // console.log(user.roles[i].role.replace("ROLE_",""))
                userRoles += `${user.roles[i].role.replace("ROLE_","")} `
            }

            let output = "<tr>";
            output += `
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.lastName}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${userRoles}</td>
            `;
            output += "<tr>";

            document.getElementById("gUser").innerHTML = output;
        })
}

function getHeader() {
    fetch(url).then((res) => res.json())
        .then((user) => {
            let userRoles = "";
            for (let i = 0; i < user.roles.length; i++) {
                userRoles += `${user.roles[i].role.replace("ROLE_","")} `
            }
            let output = "";
            output += `${user.email} with roles: ${userRoles}`;
            document.getElementById("gHead").innerHTML = output;
        })
}
getHeader()
getUser()