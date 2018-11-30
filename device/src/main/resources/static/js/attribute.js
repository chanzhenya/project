$(function () {
    initTable();

    initButton();

    initSelect();

    initHead();
});

function initHead() {
    $("#myTab li").eq(3).addClass("active").siblings().removeClass("active");
}

function initTable(){
    $('#attribute_table').bootstrapTable({
        url: ctxPath+'attribute/list',
        method: 'POST',
        toolbar: 'toolbar',
        striped: true,
        cache: false,
        clickToSelect: true,
        uniqueId: "ID",
        columns: [{
            checkbox: true
        }, {
            field: 'attributeName',
            title: '属性名称'
        }, {
            field: 'attributeValue',
            title: '属性值'
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
        $("#attributeTypeSelect").val("");
        postdata.attributeId=null;
    });

    $("#btn_edit").click(function () {
        var arrselections = $("#attribute_table").bootstrapTable('getSelections');
        if (arrselections.length > 1) {
            toastr.warning('只能选择一行进行编辑');

            return;
        }
        if (arrselections.length <= 0) {
            toastr.warning('请选择有效数据');

            return;
        }
        $("#myModalLabel").text("编辑");
        $("#inputAttributeName").val(arrselections[0].attributeName);
        $("#inputAttributeValue").val(arrselections[0].attributeValue)
        $("#attributeTypeSelect").val(arrselections[0].attributeTypeId);

        postdata.attributeId = arrselections[0].attributeId;
        $('#myModal').modal();
    });

    $("#btn_delete").click(function () {
        var arrselections = $("#attribute_table").bootstrapTable('getSelections');
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
            url: ctxPath+'attribute/delete',
            contentType: 'application/json',
            data: JSON.stringify({"data":JSON.stringify(arrselections)}),
            success: function (data) {
                if (data.code == 200) {
                    toastr.success('提交数据成功');
                    $("#attribute_table").bootstrapTable('refresh');
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
        postdata.attributeName = $("#inputAttributeName").val();
        postdata.attributeValue = $("#inputAttributeValue").val();
        postdata.attributeTypeId = $("#attributeTypeSelect").val();
        postdata.attributeTypeName = $("#attributeTypeSelect").text;
        $.ajax({
            type: "post",
            url: ctxPath+"attribute/save",
            contentType: 'application/json',
            data: JSON.stringify(postdata),
            success: function (data) {
                if (data.code == 200) {
                    toastr.success('提交数据成功');
                    $("#attribute_table").bootstrapTable('refresh');
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
        url:ctxPath+"attribute-type/list",
        type: 'POST',
        success: function (data) {
            var deviceType='<option value=""></option>';
            var result = data.data;
            $.each(result, function(index, item) {
                deviceType += '<option value="'+item.attributeTypeId+'">' + item.attributeTypeName + '</option>';
            });
            $('#attributeTypeSelect').html(deviceType);

        }
    });
}