$(function () {

    $("#login-btn").click(function () {
        login();
    });

});

function login() {
    var publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCQC2a572Gu7TA22LjBw/Fxl4mfocZ6RTZVHB8eQiEakSZI3H+wgIGDKMRvdNzCG51hNKielTSHUoO2I7o96PsnYveDChYTEdEjUakb0GXhsw0urOkFJD7/IVZ1iaEAPuK9uDfkqv55U59garH4x72BebuEd0PaAYB5wa0Rr6s2QwIDAQAB";
    var username = $("#username").val();
    var password = $("#password").val();

    var encrypt = new JSEncrypt();
    encrypt.setPublicKey(publicKey);

    password = encrypt.encrypt(password);

    $.ajax({
        type:"POST",
        data:JSON.stringify({
            "username":username,
            "password":password
        }),
        contentType: 'application/json',
        url:ctxPath+"check",
        success: function (data) {
            if(data.code == 200) {
                swal({title:"提示信息",text:"登录成功，进入系统！",type:"success"},
                    function () {
                        window.location.href = ctxPath+"device";
                    });
            } else {
                swal("错误信息",data.msg,"error");
            }
        },
        error: function (data) {
            var responseJson = data.responseJSON;
            var msg = responseJson.msg;
            var code = responseJson.code;
            swal("错误信息",code+" "+msg,"error");
        },
        complete: function () {

        }
    });
}