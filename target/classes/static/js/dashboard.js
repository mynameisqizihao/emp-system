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
                    + "<a href=\"#\" onClick='deleteEmployee(" + emp.id + "); return false;' class=\"delete_btn\">删除</a>"
                    + "</div>"
                    + "</div>";
            }
            $("#data_area").html(html);
        }
    })
})

function deleteEmployee(id) {
    // 浏览器自带的确认框，confirm,点击确定返回true，否则返回false
    if (confirm("确定要删除这个员工吗？")){
        $.post({

            url: "/employee/delete/" + id,
            success:function () {
                alert("删除成功");
                setTimeout(function (){
                    window.location.reload();
                },1000);
            },
            error: function (){
                alert("删除失败");
            }
        });
    }
}