$(function (){
    var id = new URLSearchParams(window.location.search).get("id");

    $.get("/employee/selectAllById/" + id,function (emp) {
        $("#empId").val(emp.id);
        $("#lastName").val(emp.lastName);
        $("#email").val(emp.email);

        $("input[name='gender'][value='" + emp.gender + "']").prop("checked",true);

        $.get("/department/selectAll",function (data) {
            var options = "";
            for (var i = 0; i < data.length; i++){
                var selected = (data[i].id===emp.department.id) ? " selected" : "";
                options += "<option value='" + data[i].id + "'" + selected + ">" + data[i].departmentName + "</option>";
            }
            $("#department").html(options);
        });
        if (emp.birth){
            $("#birth").val(emp.birth.substring(0,10));
        }
    });
})

function updateEmployee () {
    var id = $("#empId").val();
    var lastName = $("#lastName").val();
    var email = $("#email").val();
    var gender = $("input[name='gender']:checked").val();
    var department = $("#department").val();
    var birth = $("#birth").val();

    if (!lastName) {alert("请输入姓名"); return;}
    if (!email) {alert("请输入邮箱"); return;}
    if (!gender) {alert("请选择性别"); return;}
    if (!department) {alert("请选择部门"); return;}
    if (!birth) {alert("请选择日期"); return;}

    $.post({
        url: "/employee/update",
        data: {
         "id":id,
         "lastName":lastName,
         "email":email,
         "gender":gender,
         "department.id":department,
         "birth":birth
        },
        success:function () {
            alert("修改成功，即将跳转到管理页...");
            setTimeout(function (){
                window.location.href = "/dashboard.html";
            },1000);
        },
        error:function (){
            alert("修改失败！");
        }
    });
}