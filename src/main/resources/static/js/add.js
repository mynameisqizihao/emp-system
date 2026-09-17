$(function (){
    $.get("/department/selectAll",function (data) {
        var options = "";
        for (var i = 0; i < data.length; i++){
            options += "<option value=\"" + data[i].id + "\">" + data[i].departmentName + "</option>";
        }
        $("#department").html(options);
    });
})

function saveEmployee () {
    var lastName = $("#lastName").val();
    var email = $("#email").val();
    var gender = $("input[name='gender']:checked").val();
    var department = $("#department").val();
    var birth = $("#birth").val();

    if (!lastName) { alert("请输入姓名"); return;}
    if (!email) { alert("请输入邮箱"); return;}
    if (!gender) { alert("请选择性别"); return;}
    if (!department) { alert("请选择部门"); return;}
    if (!birth) { alert("请输入生日"); return;}

    $.ajax({
        url: "/employee/add",
        type: "POST",
        data:{
            "lastName":lastName,
            "email":email,
            "gender":gender,
            "department.id":department,
            "birth":birth
        },
        success:function () {
            alert("添加成功！即将返回管理页...");
            setTimeout(function (){
                window.location.href = "/dashboard.html";
            },1000);
        },
        error:function () {
            alert("添加失败！");
        }
    });
}