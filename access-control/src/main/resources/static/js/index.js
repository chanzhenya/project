$(function () {
    $(".register").click(function () {
        window.location.href = ctxPath+"/register";
    });

    $(".submit").click(function () {
        var obj = document.getElementById("image");
        var photo = obj.files[0];

        var formData = new FormData();
        formData.append("imageFile",photo);

        $.ajax({
            url: ctxPath+"upload",
            type:"POST",
            data: formData,
            contentType: false,
            processData: false,
            mimeType: "multipart/form-data",
            success: function (data) {

            },
            error: function (data) {

            }
        });
    });
});