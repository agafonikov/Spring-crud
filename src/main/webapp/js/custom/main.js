

window.onload = async function (){
    await drawTable();
    let addButton = document.getElementById("add-button");

    addButton.addEventListener("click", function (e) {submitAdd(e)});
}

function changeToEdit(){
    let btn = document.getElementsByClassName("submit")[0];
    btn.classList.remove("btn-danger");
    btn.classList.add("btn-primary");
    btn.value = "Edit";

    let form = document.getElementsByClassName("edit-form")[0];
    form.removeEventListener("submit", submitDelete, false);
    form.addEventListener("submit", submitEdit, false);

    form.getElementsByTagName("select")[0].removeAttribute("disabled");
    let els = Array.from(form.children[0].getElementsByTagName("input"));
    els.forEach(el => el.removeAttribute("disabled"));
    document.getElementById("id").setAttribute("disabled", "");
}

function changeToDelete(){
    let btn = document.getElementsByClassName("submit")[0];
    btn.classList.remove("btn-primary");
    btn.classList.add("btn-danger");
    btn.value = "Delete";

    let form = document.getElementsByClassName("edit-form")[0];
    form.removeEventListener("submit", submitEdit, false);
    form.addEventListener("submit", submitDelete, false);

    form.getElementsByTagName("select")[0].setAttribute("disabled", "")
    let els = Array.from(form.children[0].getElementsByTagName("input"));
    els.forEach(el => el.setAttribute("disabled", ""));
    document.getElementById("id").setAttribute("disabled", "");
}

function fillForm(e){
    let target = e.target;
    e.preventDefault();
    let form = document.getElementsByClassName("edit-form")[0];
    let els = Array.from(form.children[0].getElementsByTagName("input"));
    let tt = Array.from(target.parentElement.parentElement.children);
    let roles = Array.from(tt[4].getElementsByClassName("role"));
    let info = {
        id:         tt[0].innerHTML,
        username:   tt[1].innerHTML,
        name:       tt[2].innerHTML,
        lastName:   tt[3].innerHTML,
        roles:      roles.map((value, index) => value.innerHtml)
    };
    els[0].value = info.id;
    els[1].value = info.username;
    els[2].value = info.name;
    els[3].value = info.lastName;

    let options = Array.from(form.getElementsByTagName("option"));
    options.forEach((value, index) => {
        if (info.roles.includes(value.innerHTML)) value.setAttribute("selected", "");
    });
}

function getFormData(form){
    let formData = new FormData(form);
    formData.append("id", form.elements["id"].value);
    return formData;
}

async function submitEdit(e){
    e.preventDefault();
    let form = document.getElementsByClassName("edit-form")[0];
    let data = Object.fromEntries(getFormData(form));
    data.roles = Array.from(getFormData(form).getAll("roles"));
    await fetch("api/users/", {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json; charset=utf-8',
        },
        body: JSON.stringify(data)
    });
    closeModal();
    await drawTable();
    return false;
}

async function submitDelete(e){
    e.preventDefault();
    let form = document.getElementsByClassName("edit-form")[0];
    await fetch("api/users/", {
        method: "DELETE",
        headers: {
            'Content-Type': 'application/json; charset=utf-8',
        },
        body: JSON.stringify(Object.fromEntries(getFormData(form)))
    });
    closeModal();
    await drawTable();
    return false;
}

async function submitAdd(e){
    e.preventDefault();
    let form = document.getElementsByClassName("add-form")[0];
    let data = Object.fromEntries(new FormData(form));
    data.roles = Array.from(new FormData(form).getAll("roles"));
    await fetch("api/users/", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json; charset=utf-8',
        },
        body: JSON.stringify(data)
    });
    await drawTable();
    return false;
}

function closeModal() {

    // get modal
    const modal = document.getElementById("editModal");

    // change state like in hidden modal
    modal.classList.remove('show');
    modal.setAttribute('aria-hidden', 'true');
    modal.setAttribute('style', 'display: none');

    // get modal backdrop
    const modalBackdrops = document.getElementsByClassName('modal-backdrop');

    // remove opened modal backdrop
    document.body.removeChild(modalBackdrops[0]);
}

async function drawTable() {
    let body = document.getElementById("user_list_body");

    let response = await fetch("api/users/", {
        method: "GET",
        headers: {
            'Content-Type': 'application/json; charset=utf-8',
        }
    });
    
    let result = await response.json();

    let tr;
    body.innerHTML = "";
    result.forEach((user, index)=> {
        tr = document.createElement("tr");
        tr.appendChild(createTd(user.id));
        tr.appendChild(createTd(user.username));
        tr.appendChild(createTd(user.name));
        tr.appendChild(createTd(user.lastName));
        let rolesList = createTd("");
        user.authorities.map((role, index)=>{
            let div = document.createElement("div");
            div.className = "role d-inline-block m-1";
            div.innerHTML = role.authority;
            return div;
        }).forEach((value, index) => {rolesList.appendChild(value)});
        tr.appendChild(rolesList);
        tr.appendChild(createTd('<button type="button" class="btn btn-info edit-btn" data-toggle="modal" data-target="#editModal">Edit</button></td>'));
        tr.appendChild(createTd('<button type="button" class="btn btn-danger delete-btn" data-toggle="modal" data-target="#editModal">Delete</button></td>'));
        body.appendChild(tr);
    });


    let delButton = document.getElementsByClassName("delete-btn");
    let editButton = document.getElementsByClassName("edit-btn");
    Array.from(delButton).forEach(el=> el.addEventListener("click", function (e){
        fillForm(e);
        changeToDelete();
    }, false));
    Array.from(editButton).forEach(el=> el.addEventListener("click", function (e){
        fillForm(e);
        changeToEdit();
    }, false));
}

function createTd(inp){
    let el = document.createElement("td");
    el.innerHTML = inp;
    return el;
}

