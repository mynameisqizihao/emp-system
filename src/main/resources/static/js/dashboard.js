$(function () {
    $.ajax({
        url: "/employee/selectAll",
        type: "GET",
        success:function (data) {
            var html = "";
            for (var i = 0; i < data.length; i++){
                var emp = data[i];
                html += "<div class=\"table_data_row\">"
                    + "<div class=\"column_data\">" + emp.id + "</div>"
                    + "<div class=\"column_data\">" + emp.lastName + "</div>"
                    + "<div class=\"column_data\">" + emp.email + "</div>"
                    + "<div class=\"column_data\">" + (emp.gender===1?"男":"女") + "</div>"
                    + "<div class=\"column_data\">" + emp.department.departmentName + "</div>"
                    + "<div class=\"column_data\">" + emp.birth + "</div>"
                    + "<div class=\"column_actions\">"
                    + "<a href=\"/update.html?id=" + emp.id +"\" class=\"edit_btn\">编辑</a>"
                    + "<a href=\"/employee/delete/" + emp.id +"\" class=\"delete_btn\">删除</a>"
                    + "</div>"
                    + "</div>";
            }
            $("#data_area").html(html);
        }
    })
})