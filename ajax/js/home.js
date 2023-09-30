function displayPageStudent(page) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/pageStudent?page=" + page + "&size=2",
        success: function (data) {
            displayListStudent(data.content);
            displayPagination(data.number, data.totalPages)
        }
    })
}

function displayListStudent(students) {
    console.log(students);
    let content = ' <table id="" class="table table-striped table-hover"><tr>' +
        '        <td width="30px">Stt</td>' +
        '        <td width="130px">Họ và tên</td>' +
        '        <td width="100px">Địa chỉ</td>' +
        '        <td width="100px">Ngày sinh</td>' +
        '        <td width="120px">Ảnh</td>' +
        '        <td width="180px">Các môn học đã đăng kí</td>' +
        '        <td width="120px">Trạng thái</td>' +
        '        <td width="80px">Delete</td>' +
        '        <td width="80px">Edit</td>' +
        '        <td width="80px">View</td>' +
        '    </tr>';
    for (let i = 0; i < students.length; i++) {
        content += `<tr><td>${i + 1}</td>`
            +`<td>${students[i].name}</td>`
            + `<td>${students[i].address}</td>`
            + `<td>${students[i].dateOfBirth}</td>`
            + `<td><img style="width: 100px; height: 100px" src="\\src\\main\\resources\\static\\img\\${students[i].url_img}" alt="No image"></td>`
            + `<td>${students[i].count_subject}</td>`
            + `<td>${students[i].status.name}</td>`
            + `<td><button onclick="deleteStudent(${students[i].id})"  class="btn btn-danger">Delete</button></td>`
            + `<td><button onclick="showFormEditStudent(${students[i].id})" class="btn btn-warning">Edit</button></td>`
            + `<td><button onclick="" class="btn btn-info">View</button></td></tr>`
        console.log(`${students[i].name}`)
    }
    content += `</table>`
    document.getElementById("page-student-home").innerHTML = content;
}

function displayPagination(currentPage, totalPages) {
    var pagination = $('#pagination');
    pagination.empty();
    if (totalPages > 1 && currentPage > 0) {
        var prevButton = '<button id="prevButton" class="btn btn-light" onclick="displayPageStudent(' + (currentPage - 1) + ')">Previous</button>';
        pagination.append(prevButton);
    }

    for (var i = 0; i < totalPages; i++) {
        var pageNumber = i;
        if (i === currentPage) {
            pagination.append('<span class="currentPage">' + (pageNumber + 1) + '</span>');
        } else {
            var pageButton = '<button class="btn btn-light" onclick="displayPageStudent(' + pageNumber + ')">' + (pageNumber + 1) + '</button>';
            pagination.append(pageButton);
        }
    }

    if (totalPages > 1 && currentPage < (totalPages - 1)) {
        var nextButton = '<button class="btn btn-light" id="nextButton" onclick="displayPageStudent(' + (currentPage + 1) + ')">Next</button>';
        pagination.append(nextButton);
    }
}

function deleteStudent(id) {
    $.ajax({
        url: "http://localhost:8080/api/deleteStudent/" + id,
        type: "DELETE",
        success: function () {
            alert("Xoá thành công!")
            displayPageStudent(0)
        }
    })
    event.preventDefault()
}

function showFormEditStudent(id) {
    $.ajax({
        url: "http://localhost:8080/api/findStudentById/" + id,
        type: "GET",
        success: function (student) {
            localStorage.setItem("student", JSON.stringify(student)),
                window.location.href = "formEditStudent.html"
        }
    })
}
function showFormCreateStudent() {
    window.location.href = "formCreate.html"
}