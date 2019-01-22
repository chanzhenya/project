$(function () {
    initTable();

    initButton();

    initHead();
});

function initHead() {
    $("#myTab li").eq(2).addClass("active").siblings().removeClass("active");
}

function initTable(){
    $('#dish_table').bootstrapTable({
        url: ctxPath+'dish/list',
        method: 'POST',
        toolbar: '#toolbar',
        striped: true,
        cache: false,
        clickToSelect: true,
        uniqueId: "id",
        columns: [{
            checkbox: true
        }, {
            field: 'productId',
            title: '菜品ID'
        }, {
            field: 'productName',
            title: '菜品名称'
        }, {
            field: 'dishId',
            title: '菜品编号'
        }, ]
    });
}

function initButton() {

    var postdata = {};

    // $('#btn_add').click(function () {
    //     $("#myModalLabel").text("新增");
    //     $("#myModal").find(".form-control").val("");
    //     $('#myModal').modal();
    //     $("#attributeTypeSelect").val("");
    //     postdata.attributeId=null;
    // });

    $("#btn_edit").click(function () {
        var arrselections = $("#dish_table").bootstrapTable('getSelections');
        if (arrselections.length > 1) {
            toastr.warning('只能选择一行进行编辑');

            return;
        }
        if (arrselections.length <= 0) {
            toastr.warning('请选择有效数据');

            return;
        }
        $("#myModalLabel").text("编辑");
        $("#inputDishName").val(arrselections[0].productName);
        $("#inputDishId").val(arrselections[0].dishId)

        postdata.id = arrselections[0].id;
        $('#myModal').modal();
    });

    $("#btn_delete").click(function () {
        var arrselections = $("#dish_table").bootstrapTable('getSelections');
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
            url: ctxPath+'dish/delete',
            contentType: 'application/json',
            data: JSON.stringify({"data":JSON.stringify(arrselections)}),
            success: function (data) {
                if (data.code == 200) {
                    toastr.success('提交数据成功');
                    $("#dish_table").bootstrapTable('refresh');
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
        postdata.dishId = $("#inputDishId").val();
        postdata.productName = $("#inputDishName").val();
        $.ajax({
            type: "post",
            url: ctxPath+"dish/save",
            contentType: 'application/json',
            data: JSON.stringify(postdata),
            success: function (data) {
                if (data.code == 200) {
                    toastr.success('提交数据成功');
                    $("#dish_table").bootstrapTable('refresh');
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