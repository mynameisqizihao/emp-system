function ajax3 () {
    $.post({
        url:"/ajax/a3",
        data:{"name":$("#username").val()},
        success:function (data) {
            if (data.toString()==="OK"){
                $("#username_commit_word").css("color","green");
            }else{
                $("#username_commit_word").css("color","red");
            }
            $("#username_commit_word").html(data);
        }
    });
}

function ajax4 () {
    $.ajax({
        url:"/ajax/a4",
        data:{"password":$("#password").val()},
        success:function (data) {
            if (data.toString()==="OK"){
                $("#password_commit_word").css("color","green");
            }else{
                $("#password_commit_word").css("color","red");
            }
            $("#password_commit_word").html(data);
        }
    });
}

function doRegister () {

    var name = $("#username").val();
    var password = $("#password").val();

    if (name==="" || password===""){
        alert("用户名和密码不能为空");
        return;
    }

    $.ajax({
        url: "/user/register",
        type: "POST",
        data: {
            "name": name,
            "password":password
        },
        success:function (data) {
            if (data === "OK") {
                alert("注册成功！即将跳转到登录页...");
                setTimeout(function (){
                    window.location.href = "login.html";
                },1000);
            }else {
                alert("注册失败！"+data);
            }
        },
        error:function () {
            alert("网络错误，请稍后再试!");
        }
    });
}