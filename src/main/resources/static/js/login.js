function ajax1 () {
    var name = $("#username").val().trim();
    if (name === ""){
        $("#username_commit_word").css("color","red").html("用户名不能为空");
    }else{
        $("#username_commit_word").css("color","green").html("OK");
    }
}

function ajax2 () {
    var password = $("#password").val().trim();
    if (password === ""){
        $("#password_commit_word").css("color","red").html("密码不能为空");
    }else{
        $("#password_commit_word").css("color","green").html("OK");
    }
}
//
// function doLogin () {
//
//     if ($("#username").val()===""){
//         alert("用户名不能为空");
//         return;
//     }
//
//     if ($("#password").val()===""){
//         alert("密码不能为空");
//         return;
//     }
//
//     $.ajax({
//         url:"/user/login",
//         type:"POST",
//         data:{
//             "username":$("#username").val(),
//             "password":$("#password").val()
//         },
//         success:function (data) {
//             if (data==="OK"){
//                 alert("登陆成功！即将跳转到首页...");
//                 setTimeout(function (){
//                     window.location.href = "/index.html";
//                 },1000)
//             }else{
//                 alert("登录失败！"+data);
//             }
//         },
//         error:function () {
//             alert("网络错误，请稍后再试!");
//         }
//     });
// }