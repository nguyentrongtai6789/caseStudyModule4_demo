function getStatusToCreate() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/listStatus",
        success: function (listStatus) {
            let content = `<select id="status-create">`
            for (let i = 0; i < listStatus.length; i++) {
                content += `<option value="${listStatus[i].id}">${listStatus[i].name}</option>`
            }
            content += "</select>"
            document.getElementById('status-create-span').innerHTML = content;
        }
    })
    event.preventDefault();
}

function checkValidation() {
    let name = document.getElementById("name-create").value;
    let address = document.getElementById("address-create").value;
    if (name == null | name == "" | address == null | address == "") {
        document.getElementById("valid-address-create").innerHTML = "Trường này là bắt buộc!"
        document.getElementById("valid-name-create").innerHTML = "Trường này là bắt buộc!"
    } else {
        createNewStudent();
    }
    event.preventDefault();
}

function createNewStudent() {
    let student;
    let name = $("#name-create").val();
    let address = $("#address-create").val();
    let dob = $("#dob-create").val(); // nếu không chọn gì thì nó vẫn gửi đi được và giá trị là null tuy nhiên file
    // là bắt buộc phải có giá trị
    let id_status = $("#status-create").val();
    let file = $("#file-image-create")[0].files[0];
    // bắt buộc file phải có gì đó thì formData mới chuyển dữ liệu đi,
    // ở đây xử lí trường hợp không chọn ảnh khi create:
    if (file == undefined) {
        file = new File([], "", undefined);
    }
    student = {
        name: name,
        address: address,
        dateOfBirth: dob,
        status: {
            id: id_status
        }
    }
    // đối tượng student này phải chuyển thành Blob thì mới chuyển bằng form data được:
    // còn nếu không thì chỉ cần dùng json là chuyển được
    // ví dụ nếu không chuyển bằng form data và đối tượng student này không có ảnh thì chuyển bằng json như sau đây:
    // $.ajax({
    //     headers: {
    //         'Accept': 'application/json',
    //         'Content-Type': 'application/json'
    //     },
    //     type: "POST",
    //     data: JSON.stringify(newSmartphone),
    //     //tên API
    //     url: "http://localhost:8080/api/smartphones",
    //     //xử lý khi thành công
    //     success: successHandler
    //
    // });
    let formData = new FormData();
    formData.append("student",
        new Blob([JSON.stringify(student)], {type: "application/json"}));
    formData.append("file", file);
    $.ajax({
        url: "http://localhost:8080/api/saveStudent",
        type: "POST",
        processData: false,
        contentType: false,
        data: formData,
        success: function () {
            alert("Thêm thành công!"),
                window.location.href = "home.html"
        }
    })
    document.getElementById("form-create-student").reset()
    event.preventDefault()
}
