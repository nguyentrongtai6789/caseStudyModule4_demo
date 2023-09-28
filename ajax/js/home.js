function displayPageStudent(page) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/students/getPageStudent?page=" + page + "&size=2",
        success: function (data) {
            displayListStudent(data.content);
            displayPagination(data.number, data.totalPages)
        }
    })
}

function displayListStudent(students) {
    let content = "";
    content += '    <table id=""><tr>\n' +
        '        <th width="100px">Số thứ tự</td>\n' +
        '        <th width="100px">Họ và tên</td>\n' +
        '        <th width="150px">Địa chỉ</td>\n' +
        '        <th width="100px">Ảnh</td>\n' +
        '        <th width="180px">Các môn học đã đăng kí</td>\n' +
        '        <th width="120px">Trạng thái</td>\n' +
        '        <th width="80px">Edit</td>\n' +
        '        <th width="80px">Delete</td>\n' +
        '        <th width="80px">View</td>\n' +
        '    </tr>';
    for (let i = 0; i < students.length; i++) {
        content += `<tr><td>${i + 1}</td>
<td >${students[i].name}</td>
            <td >${students[i].address}</td>
            <td >
             <img style="width: 100px; height: 100px" src="\\src\\main\\resources\\static\\img\\${students[i].url_img}" alt="Khong co anh"></td>` +
            `<td id="subject-checked-${students[i].id}">`
            + `</td><td>${students[i].status.name}</td>` +
            `<td><button onclick="deleteStudent(${students[i].id})">Delete</button></td>` +
            `<td><button onclick="showFormEditStudent(${students[i].id})">Edit</button></td>` +
            `<td><button onclick="">View</button></td></tr>`;
    }
    console.log(content)
    document.getElementById("page-student-home").innerHTML = content;
}
function displayPagination(currentPage, totalPages) {
    var pagination = $('#pagination');
    pagination.empty();
    if (totalPages > 1 && currentPage > 0) {
        var prevButton = '<button id="prevButton" onclick="displayPageStudent(' + (currentPage - 1) + ')">Previous</button>';
        pagination.append(prevButton);
    }

    for (var i = 0; i < totalPages; i++) {
        var pageNumber = i;
        if (i === currentPage) {
            pagination.append('<span class="currentPage">' + (pageNumber + 1) + '</span>');
        } else {
            var pageButton = '<button onclick="displayPageStudent(' + pageNumber + ')">' + (pageNumber + 1) + '</button>';
            pagination.append(pageButton);
        }
    }

    if (totalPages > 1 && currentPage < (totalPages-1)) {
        var nextButton = '<button id="nextButton" onclick="displayPageStudent(' + (currentPage + 1) + ')">Next</button>';
        pagination.append(nextButton);
    }
}

function deleteStudent(id) {
}

function showFormEditStudent(id) {
}