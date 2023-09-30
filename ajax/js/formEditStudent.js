let idEdit;
function fillStudentToForm() {
    getStatusToEdit();
    let student = JSON.parse(localStorage.getItem("student"));
    idEdit = student.id;
    $("#name-edit").val(student.name);
    $("#address-edit").val(student.address);
    $("#dob-edit").val(student.dateOfBirth);
    let img = document.getElementById("img-edit");
    img.src = "\\src\\main\\resources\\static\\img\\" + student.url_img;
    // console.log(student)
    event.preventDefault();
}

function getStatusToEdit() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/listStatus",
        success: function (listStatus) {
            let content = `<select id="status-edit">`
            for (let i = 0; i < listStatus.length; i++) {
                content += `<option value="${listStatus[i].id}">${listStatus[i].name}</option>`
            }
            content += "</select>"
            document.getElementById('status-edit-span').innerHTML = content;
        }
    })
    event.preventDefault();
}
function checkValidation() {
    let name = document.getElementById("name-edit").value;
    let address = document.getElementById("address-edit").value;
    if (name == null | name == "" | address == null | address == "") {
        document.getElementById("valid-address-edit").innerHTML = "Trường này là bắt buộc!"
        document.getElementById("valid-name-edit").innerHTML = "Trường này là bắt buộc!"
    } else {
       saveEditStudent();
    }
    event.preventDefault();
}
function saveEditStudent() {
    let id = idEdit;
    let name = $("#name-edit").val();
    let address = $("#address-edit").val();
    let dob = $("#dob-edit").val();
    let id_status = $("#status-edit").val();
    let file = $("#file-image-edit")[0].files[0];
    if (file == undefined) {
        file = new File([], "", undefined);
    }
    let student = {
        id: id,
        name: name,
        address: address,
        dateOfBirth: dob,
        status: {
            id: id_status
        }
    }
    let formData = new FormData();
    formData.append("student", new Blob([JSON.stringify(student)], {type: "application/json"}));
    formData.append("file", file);
    $.ajax({
        url: "http://localhost:8080/api/saveStudent",
        type:"POST",
        processData: false,
        contentType: false,
        data: formData,
        success: function () {
            alert("Sửa thành công!")
            window.location.href = "home.html"
        }
    })
    event.preventDefault();
}
