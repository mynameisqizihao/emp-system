var currentPage = 1;
var pageSize = 5;

$(function (){
    loadPage(1);
});

function loadPage(page){

     currentPage = page;

     $.ajax({
         url: "/employee/page",
         type: "GET",
         data: {
             "page": page,
             "size": pageSize
         },
         success:function (result) {
             var data = result.list;
             var total = result.total;

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
             renderPagination(total,page);
         }
     });
}

function renderPagination(total,page){
    var totalPages = Math.ceil(total / pageSize);
    var html = "共" + total + "条，第" + page + "/" + totalPages + "页";

    if (page>1){
        html += "<a href='#' class='edit_btn' onclick='loadPage(" + (page-1) + "); return false;'>上一页</a> ";
    }else{
        html += "<span class='edit_btn' style='background:#ccc;'>上一页</span> ";
    }

    if (page<totalPages){
        html += "<a href='#' class='edit_btn' onclick='loadPage(" + (page+1) + "); return false;'>下一页</a> ";
    }else{
        html += "<span class='edit_btn' style='background:#ccc;'>下一页</span> ";

    }
    $("#pagination").html(html);
}
//
// $(function () {
//     $.ajax({
//         url: "/employee/selectAll",
//         type: "GET",
//         success:function (data) {
//             var html = "";
//             for (var i = 0; i < data.length; i++){
//                 var emp = data[i];
//                 html += "<div class=\"table_data_row\">"
//                     + "<div class=\"column_data\">" + emp.id + "</div>"
//                     + "<div class=\"column_data\">" + emp.lastName + "</div>"
//                     + "<div class=\"column_data\">" + emp.email + "</div>"
//                     + "<div class=\"column_data\">" + (emp.gender===1?"男":"女") + "</div>"
//                     + "<div class=\"column_data\">" + emp.department.departmentName + "</div>"
//                     + "<div class=\"column_data\">" + emp.birth + "</div>"
//                     + "<div class=\"column_actions\">"
//                     + "<a href=\"/update.html?id=" + emp.id +"\" class=\"edit_btn\">编辑</a>"
//                     + "<a href=\"#\" onClick='deleteEmployee(" + emp.id + "); return false;' class=\"delete_btn\">删除</a>"
//                     + "</div>"
//                     + "</div>";
//             }
//             $("#data_area").html(html);
//         }
//     })
// })

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