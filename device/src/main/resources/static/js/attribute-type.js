$(function () {
    initTable();

    initButton();

    initHead();
});

function initHead() {
    $("#myTab li").eq(4).addClass("active").siblings().removeClass("active");
}

function initTable(){
    $('#attribute_type_table').bootstrapTable({
        url: ctxPath+'attribute-type/list',
        method: 'POST',
        toolbar: 'toolbar',
        striped: true,
        cache: false,
        clickToSelect: true,
        uniqueId: "ID",
        columns: [{
            checkbox: true
        }, {
            field: 'attributeTypeId',
            title: 'ID'
        }, {
            field: 'attributeTypeName',
            title: '属性类型名称'
        }, ]
    });
}

function initButton() {

    var postdata = {};

    $('#btn_add').click(function () {
        $("#myModalLabel").text("新增");
        $("#myModal").find(".form-control").val("");
        $('#myModal').modal();
        postdata.attributeTypeId=null;
    });

    $("#btn_edit").click(function () {
        var arrselections = $("#attribute_type_table").bootstrapTable('getSelections');
        if (arrselections.length > 1) {
            toastr.warning('只能选择一行进行编辑');

            return;
        }
        if (arrselections.length <= 0) {
            toastr.warning('请选择有效数据');

            return;
        }
        $("#myModalLabel").text("编辑");
        $("#inputAttributeTypeName").val(arrselections[0].attributeTypeName);

        postdata.attributeTypeId = arrselections[0].attributeTypeId;
        $('#myModal').modal();
    });

    $("#btn_delete").click(function () {
        var arrselections = $("#attribute_type_table").bootstrapTable('getSelections');
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
            url: ctxPath+'attribute-type/delete',
            contentType: 'application/json',
            data: JSON.stringify({"data":JSON.stringify(arrselections)}),
            success: function (data) {
                if (data.code == 200) {
                    toastr.success('提交数据成功');
                    $("#attribute_type_table").bootstrapTable('refresh');
                }
            },
            error: function (data) {
                var msg = data.responseJSON.msg;
                toastr.error(msg);
            },
            complete: function () {

            }

        });
    });

    $("#btn_submit").click(function () {
        postdata.attributeTypeName = $("#inputAttributeTypeName").val();
        $.ajax({
            type: "post",
            url: ctxPath+"attribute-type/save",
            contentType: 'application/json',
            data: JSON.stringify(postdata),
            success: function (data) {
                if (data.code == 200) {
                    toastr.success('提交数据成功');
                    $("#attribute_type_table").bootstrapTable('refresh');
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