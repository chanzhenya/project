$(function () {

    $("#myTab li").eq(4).addClass("active").siblings().removeClass("active");

    initButton();
});

function initButton() {
    var cmd = null;
    $('#btn_start').click(function () {
        cmd = 0;
        execute(cmd)
    });

    $('#btn_stop').click(function () {
        cmd = 1;
        execute(cmd);
    });

    $('#btn_restart').click(function () {
        cmd = 2;
        execute(cmd);
    });

    $('#btn_start_2').click(function () {
        cmd = 3;
        execute(cmd);
    });

    $('#btn_stop_2').click(function () {
        cmd = 4;
        execute(cmd);
    });

    $('#btn_restart_2').click(function () {
        cmd = 5;
        execute(cmd);
    });

    $('#btn_init').click(function () {
        $.ajax({
            url: ctxPath + 'device-start/init',
            type:"POST",
            success: function (data) {
                if (data.code == 200) {
                    toastr.success(data.msg);
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
}

function execute(cmdIndex) {
    $.ajax({
        url: ctxPath + 'device-start/execute?cmdIndex='+cmdIndex,
        type:"POST",
        success: function (data) {
            if (data.code == 200) {
                toastr.success(data.msg);
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
}