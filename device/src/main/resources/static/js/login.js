$(function () {

    $("#login-btn").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        if(username == "" || password == "") {
            toastr.error("用户名或密码不能为空！");
        }

        $.ajax({
            type: "post",
            url: ctxPath+"login/submit",
            contentType: 'application/json',
            data: JSON.stringify({
                "username":username,
                "password":password
            }),
            success: function (data) {
                if (data.code == 200) {
                    window.location.href=ctxPath+"device";
                } else {
                    toastr.error(data.msg);
                }
            },
            error: function (data) {
                var responseJson = data.responseJSON;
                var msg = responseJson.msg;
                var code = responseJson.code;
                toastr.error(code+" "+msg);
            },
            complete: function () {

            }
        });
    });
});