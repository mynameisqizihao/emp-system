function ajax3 () {
    var name = $("#username").val().trim();
    if (name === ""){
        $("#username_commit_word").css("color","red").html("用户名不能为空");
    }else{
        $("#username_commit_word").css("color","green").html("OK");

    }
    // $.post({
    //     url:"/ajax/a3",
    //     data:{"name":$("#username").val()},
    //     success:function (data) {
    //         if (data.toString()==="OK"){
    //             $("#username_commit_word").css("color","green");
    //         }else{
    //             $("#username_commit_word").css("color","red");
    //         }
    //         $("#username_commit_word").html(data);
    //     }
    // });
}

function ajax4 () {
    var password = $("#password").val().trim();
    if (password === ""){
        $("#password_commit_word").css("color","red").html("密码不能为空");
    }else if (password.length < 6){
        $("#password_commit_word").css("color","red").html("密码至少6位");
    }else{
        $("#password_commit_word").css("color","green").html("OK");

    }

    // $.ajax({
    //     url:"/ajax/a4",
    //     type: "POST",
    //     data:{
    //         "name":$("#username").val(),
    //         "password":md5($("#password").val())
    //     },
    //     success:function (data) {
    //         if (data.toString()==="OK"){
    //             $("#password_commit_word").css("color","green");
    //         }else{
    //             $("#password_commit_word").css("color","red");
    //         }
    //         $("#password_commit_word").html(data);
    //     }
    // });
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