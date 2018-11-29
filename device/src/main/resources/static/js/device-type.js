$(function () {
    initTable();

    initButton();
});

function initTable(){
    $('#device_type_table').bootstrapTable({
        url: ctxPath+'device-type/list',
        method: 'POST',
        toolbar: 'toolbar',
        striped: true,
        cache: false,
        clickToSelect: true,
        uniqueId: "ID",
        columns: [{
            checkbox: true
        }, {
            field: 'typeId',
            title: 'ID'
        }, {
            field: 'typeName',
            title: '设备类型名称'
        }, {
            field: 'typeCode',
            title: '设备类型编号'
        }, ]
    });
}

function initButton() {

    var postdata = {};

    $('#btn_add').click(function () {
        $("#myModalLabel").text("新增");
        $("#myModal").find(".form-control").val("");
        $('#myModal').modal();
        postdata.typeId = null;
    });

    $("#btn_edit").click(function () {
        var arrselections = $("#device_type_table").bootstrapTable('getSelections');
        if (arrselections.length > 1) {
            toastr.warning('只能选择一行进行编辑');

            return;
        }
        if (arrselections.length <= 0) {
            toastr.warning('请选择有效数据');

            return;
        }
        $("#myModalLabel").text("编辑");
        $("#inputName").val(arrselections[0].typeName);
        $("#inputCode").val(arrselections[0].typeCode);

        postdata.typeId = arrselections[0].typeId;
        $('#myModal').modal();
    });

    $("#btn_delete").click(function () {
        var arrselections = $("#device_type_table").bootstrapTable('getSelections');
        if (arrselections.length <= 0) {
            toastr.warning('请选择有效数据');
            return;
        }
        var r = confirm("确认要删除选择的数据吗？");
        if (!r) {
            return;
        }
        $.ajax({
            type: "post",
            url: ctxPath+'device-type/delete',
            contentType: 'application/json',
            data: JSON.stringify({"data":JSON.stringify(arrselections)}),
            success: function (data) {
                if (data.code == 200) {
                    toastr.success('提交数据成功');
                    $("#device_type_table").bootstrapTable('refresh');
                }
            },
            error: function () {
                toastr.error('Error');
            },
            complete: function () {

            }

        });
    });

    $("#btn_submit").click(function () {
        postdata.typeName = $("#inputName").val();
        postdata.typeCode = $("#inputCode").val();
        $.ajax({
            type: "post",
            url: ctxPath+"device-type/save",
            contentType: 'application/json',
            data: JSON.stringify(postdata),
            success: function (data) {
                if (data.code == 200) {
                    toastr.success('提交数据成功');
                    $("#device_type_table").bootstrapTable('refresh');
                } else {
                    toastr.error(data.msg);
                }
            },
            error: function () {
                toastr.error('Error');
            },
            complete: function () {

            }

        });
    });
}