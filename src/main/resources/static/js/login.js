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
    })
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
    })
}