document.getElementById("editModal").addEventListener("submit", editPost)
document.getElementById("deleteModal").addEventListener("submit", deletePost)

const  urlEdit = "http://localhost:8080/update";
const  urlDelete = "http://localhost:8080/delete";

function editPost(e){
    e.preventDefault();

    let id = document.getElementById("idEdit").value;
    let name = document.getElementById("nameEdit").value;
    let lastName = document.getElementById("lastNameEdit").value;
    let age = document.getElementById("ageEdit").value;
    let email = document.getElementById("emailEdit").value;
    let password = document.getElementById("passwordEdit").value;
    let roles = setRoles(Array.from(document.getElementById("roleEdit").selectedOptions)
        .map(option => option.value));

    fetch(urlEdit, {
        method:"PUT",
        headers: {
            "Accept": "application/json, text/plain, */*",
            "Content-type":"application/json"
        },
        body:JSON.stringify({
            id:id,
            name:name,
            lastName:lastName,
            age:age,
            email:email,
            password:password,
            roles:roles
        })
    }).finally(() => {
        $('#edit').modal("hide")
        getAllUsers();
    })
}

function deletePost(e){
    e.preventDefault();

    let id = document.getElementById("idDelete").value;
    let name = document.getElementById("nameDelete").value;
    let lastName = document.getElementById("lastNameDelete").value;
    let age = document.getElementById("ageDelete").value;
    let email = document.getElementById("emailDelete").value;
    let password = document.getElementById("passwordDelete").value;
    let roles = setRoles(Array.from(document.getElementById("roleDelete").selectedOptions)
        .map(option => option.value));

    fetch(urlDelete, {
        method:"DELETE",
        headers: {
            "Accept": "application/json, text/plain, */*",
            "Content-type":"application/json"
        },
        body:JSON.stringify({
            id:id,
            name:name,
            lastName:lastName,
            age:age,
            mail:email,
            password:password,
            roles:roles
        })
    }).finally(() => {
        $('#delete').modal("hide")
        getAllUsers();
    })
}