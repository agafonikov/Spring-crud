
window.onload = function (){
    let delButton = document.getElementsByClassName("delete-btn");
    let editButton = document.getElementsByClassName("edit-btn");
    let form = document.getElementsByClassName("edit-form")[0];
    form.addEventListener("submit", async (e) => {
        let response = await fetch("api/users/", {
            method: "POST",
            body: JSON.stringify(getFormData(form))
        });
        let result = await response.json();
        alert(result.id);
    });
}

function getFormData(form){
    let formData = new FormData(form);
    let roles = form.elements["role"];
    Array.from(roles.options).forEach(x => {if (x.selected) formData.append("authorities", x.value)});
    formData.append("id", form.elements["id"].value);
    return formData;
}