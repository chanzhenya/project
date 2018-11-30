$(function () {
    initTable();
    initHead();
    initButton();
});

function initHead() {
    $("#myTab li").eq(2).addClass("active").siblings().removeClass("active");
}

function initTable(){
    $('#branch_table').bootstrapTable({
        url: ctxPath+'branch/list',
        method: 'POST',
        toolbar: 'toolbar',
        striped: true,
        cache: false,
        clickToSelect: true,
        uniqueId: "ID",
        columns: [{
            checkbox: true
        }, {
            field: 'branchId',
            title: 'ID'
        }, {
            field: 'branchName',
            title: '门店名称'
        }, {
            field: 'branchNo',
            title: '门店编号'
        }, ]
    });
}

function initButton() {

    var postdata = {};

    $('#btn_add').click(function () {
        $("#myModalLabel").text("新增");
        $("#myModal").find(".form-control").val("");
        $('#myModal').modal();
        postdata.branchId=null;
    });

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
        $("#inputBranchName").val(arrselections[0].branchName);
        $("#inputBranchNo").val(arrselections[0].branchNo);

        postdata.branchId = arrselections[0].branchId;
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
            url: ctxPath+'branch/delete',
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
        postdata.branchName = $("#inputBranchName").val();
        postdata.branchNo = $("#inputBranchNo").val();
        $.ajax({
            type: "post",
            url: ctxPath+"branch/save",
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