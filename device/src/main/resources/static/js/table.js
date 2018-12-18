$(function () {
    initTable();
    initHead();
    initButton();
    initSelect();
});

function initHead() {
    $("#myTab li").eq(1).addClass("active").siblings().removeClass("active");
}

function initTable(){
    $('#branch_table').bootstrapTable({
        url: ctxPath+'table/list',
        method: 'POST',
        toolbar: 'toolbar',
        striped: true,
        cache: false,
        clickToSelect: true,
        uniqueId: "tableId",
        columns: [{
            checkbox: true
        }, {
            field: 'tableName',
            title: '餐桌名称'
        }, {
            field: 'positionCode',
            title: '餐桌坐标'
        }, {
            field: 'deviceName',
            title: '设备'
        }, ]
    });
}

function initButton() {

    var postdata = {};

    // $('#btn_add').click(function () {
    //     $("#myModalLabel").text("新增");
    //     $("#myModal").find(".form-control").val("");
    //     $('#myModal').modal();
    //     postdata.branchId=null;
    // });

    $("#btn_edit").click(function () {
        var arrselections = $("#branch_table").bootstrapTable('getSelections');
        if (arrselections.length > 1) {
            toastr.warning('只能选择一行进行编辑');

            return;
        }
        if (arrselections.length <= 0) {
            toastr.warning('请选择有效数据');

            return;
        }
        $("#myModalLabel").text("编辑");
        $("#inputTableName").val(arrselections[0].tableName);
        $("#inputPositionCode").val(arrselections[0].positionCode);

        $("#deviceSelect").selectpicker('val',arrselections[0].deviceId);
        $("#deviceSelect").selectpicker('refresh');

        postdata.tableId = arrselections[0].tableId;
        $('#myModal').modal();
    });

    $("#btn_delete").click(function () {
        var arrselections = $("#branch_table").bootstrapTable('getSelections');
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
            url: ctxPath+'table/delete',
            contentType: 'application/json',
            data: JSON.stringify({"data":JSON.stringify(arrselections)}),
            success: function (data) {
                if (data.code == 200) {
                    toastr.success('提交数据成功');
                    $("#branch_table").bootstrapTable('refresh');
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
        postdata.tableName = $("#inputTableName").val();
        postdata.positionCode = $("#inputPositionCode").val();
        postdata.deviceId =  $("#deviceSelect").val();
        $.ajax({
            type: "post",
            url: ctxPath+"table/save",
            contentType: 'application/json',
            data: JSON.stringify(postdata),
            success: function (data) {
                if (data.code == 200) {
                    toastr.success('提交数据成功');
                    $("#branch_table").bootstrapTable('refresh');
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

function initSelect() {
    $.ajax({
        url:ctxPath+"device/select-option",
        type: 'POST',
        success: function (data) {
            var result = data.data;

            var devices = result.deviceSelection
            initDevice(devices);
        }
    });
}

function initDevice(obj) {
    var html='<option value=""></option>';
    $.each(obj,function (index,item) {
        html += '<option value="'+item.deviceId+'">' + item.deviceId + ' - ' + item.deviceName + '</option>';
    });
    $("#deviceSelect").selectpicker({
        noneSelectedText : '请选择'
    });
    $("#deviceSelect").append(html);
    $("#deviceSelect").selectpicker('refresh');
}