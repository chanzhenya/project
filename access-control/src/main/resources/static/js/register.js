$(document).ready(function () {

    //图片预览
    $("#photo").change(function (e) {
        var file = e.target.files[0] || e.dataTransfer.files[0];
        var obj = document.getElementById("photo");
        if(file) {
            var reader = new FileReader();
            reader.onload = function () {
                $("img").attr("src",this.result);
            }
            reader.readAsDataURL(file);
        }
    });

    //提交
    $(".submit-btn").click(function () {
        var name = $("#inputName").val().trim();
        var gender = $("input[name='gender']:checked").val();
        var jobNumber = $("#inputJobNumber").val().trim();
        var title = $("#inputTitle").val().trim();
        var obj = document.getElementById("photo");
        var photo = obj.files[0];

        var data = JSON.stringify({
            "name":name,
            "jobNumber":jobNumber,
            "gender":gender,
            "title":title
        });

        var formData = new FormData();
        formData.append("data",data);
        formData.append("photo",photo);

        $.ajax({
            url:ctxPath+"submit",
            type:"POST",
            data: formData,
            contentType: false,
            processData: false,
            mimeType: "multipart/form-data",
            success: function (data) {
                var result = $.parseJSON(data);
                if(result.code == 200) {
                    $(".page-header").after('<div class="alert alert-success">\n' +
                        '            <a href="#" class="close" data-dismiss="alert">\n' +
                        '                &times;\n' +
                        '            </a>\n' +
                        '            <strong>Success！</strong>您的信息提交成功！\n' +
                        '        </div>');
                } else {
                    $(".page-header").after('<div class="alert alert-error">\n' +
                        '            <a href="#" class="close" data-dismiss="alert">\n' +
                        '                &times;\n' +
                        '            </a>\n' +
                        '            <strong>Error！</strong>' + result.msg + '\n' +
                        '        </div>');
                }
            }
        });
    });
});