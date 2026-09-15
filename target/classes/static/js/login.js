function ajax1 () {
    $.post({
        url:"/ajax/a1",
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

function ajax2 () {
    $.ajax({
        url:"/ajax/a2",
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

function doLogin () {

    if ($("#username").val()===""){
        alert("用户名不能为空");
        return;
    }

    if ($("#password").val()===""){
        alert("密码不能为空");
        return;
    }

    $.ajax({
        url:"/user/login",
        type:"POST",
        data:{
            "name":$("#username").val(),
            "password":$("#password").val()
        },
        success:function (data) {
            if (data==="OK"){
                alert("登陆成功！即将跳转到首页...");
                setTimeout(function (){
                    window.location.href = "/index.html";
                },1000)
            }else{
                alert("登录失败！请检查用户名和密码是否正确或是否已注册"+data);
            }
        },
        error:function () {
            alert("网络错误，请稍后再试!");
        }
    });
}