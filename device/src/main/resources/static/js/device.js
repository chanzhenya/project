$(function () {
    initTable();

    initButton();

    initSelect();

    initHead();
});

function initHead() {
    $("#myTab li").eq(0).addClass("active").siblings().removeClass("active");
}

function initTable(){
    $('#device_table').bootstrapTable({
        url: ctxPath+'device/list',
        method: 'POST',
        toolbar: '#toolbar',
        striped: true,
        cache: false,
        clickToSelect: true,
        uniqueId: "deviceId",
        pagination: true,
        search: true,
        showColumns: true,
        columns: [{
            checkbox: true
        }, {
            field: 'deviceId',
            title: '设备ID',
            width: 150
        }, {
            field: 'deviceName',
            title: '设备名称'
        }, {
            field: 'ip',
            title: 'IP'
        }, {
            field: 'port',
            title: '端口号'
        }, {
            field: 'deviceTypeName',
            title: '设备类型'
        }, {
            field: 'pdeviceName',
            title: '所属设备',
            formatter: pdeviceFormatter
        }, {
            field: 'online',
            title: '在线/空闲',
            formatter: onlineFormatter
        }, {
            field: 'stationType',
            title: '工位类型',
            formatter: stationTypeFormatter
        }, {
            field: 'stationPno',
            title: '主工位号'
        }, {
            field: 'stationNo',
            title: '次工位号'
        }, {
            field: 'positionCode',
            title: '坐标'
        }, {
            field: 'operate',
            title: '操作',
            events: window.operateEvents,
            formatter: operateFormatter
        }, ]
    });
}

function pdeviceFormatter(value, row, index) {
    if(row.pid != null) {
        return row.pdeviceName;
    } else {
        return null;
    }
}

function onlineFormatter(value, row, index) {
    return row.online == 1?'是':'否'
}

function stationTypeFormatter(value, row, index) {
    if(row.stationType == 0) {
        return '非工位';
    } else if(row.stationType == 1) {
        return '主工位';
    } else {
        return '次工位';
    }
}

function operateFormatter(value, row, index) {
    return [
        '<a class="like" href="javascript:void(0)" title="Like">',
        '<i class="glyphicon glyphicon-plus icon-plus"></i>',
        "关联菜品",
        '</a>  '
    ].join('')
}
window.operateEvents = {
    'click .like': function (e, value, row, index) {
        console.log("Dish Table Init");
        $('#deviceIdInput').val(row.deviceId);
        $('#dishSelect').val('');

        $('#dish_table').bootstrapTable("destroy");
        $('#dish_table').bootstrapTable({
            url: ctxPath+'device/dish-list?deviceId='+row.deviceId,
            method: 'POST',
            striped: true,
            cache: false,
            uniqueId: "id",
            columns: [{
                field: 'dishId',
                title: '菜品编号'
            }, {
                field: 'dishName',
                title: '菜品名称'
            }, {
                field: 'operate',
                title: '操作',
                events: window.dishOperateEvents,
                formatter: dishOperateFormatter
            },]
        });
        $('#dishModal').modal();
    }
}


function dishOperateFormatter(value, row, index) {
    return [
        '<a class="remove" href="javascript:void(0)" title="Remove">',
        '<i class="glyphicon glyphicon-remove"></i>',
        '</a>'
    ].join('')
}
window.dishOperateEvents = {
    'click .remove': function (e, value, row, index) {
        $.ajax({
            type: "post",
            url: ctxPath+'device/dish-delete',
            contentType: 'application/json',
            data: JSON.stringify({"data":JSON.stringify(row)}),
            success: function (data) {
                if (data.code == 200) {
                    toastr.success('提交数据成功');
                    $("#dish_table").bootstrapTable('remove', {
                        field: 'id',
                        values: [row.id]
                    });
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
}


function initButton() {

    var postdata = {};

    $('#btn_add').click(function () {
        $.ajax({
            type: "post",
            url: ctxPath+'device/sync',
            success: function (data) {
                if (data.code == 200) {
                    toastr.success('数据同步成功');
                    $("#device_table").bootstrapTable('refresh');
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

    $("#btn_edit").click(function () {
        var arrselections = $("#device_table").bootstrapTable('getSelections');
        if (arrselections.length > 1) {
            toastr.warning('只能选择一行进行编辑');

            return;
        }
        if (arrselections.length <= 0) {
            toastr.warning('请选择有效数据');

            return;
        }
        $("#myModalLabel").text("编辑");
        $("#inputDeviceName").val(arrselections[0].deviceName);
        $("#inputIp").val(arrselections[0].ip);
        $("#inputPort").val(arrselections[0].port);
        $("#inputStationPno").val(arrselections[0].stationPno);
        $("#inputStationNo").val(arrselections[0].stationNo);
        $("#inputPositionCode").val(arrselections[0].positionCode);

        $("#deviceTypeSelect").selectpicker('val',arrselections[0].deviceType);
        $("#deviceTypeSelect").selectpicker("refresh");

        $("#deviceSelect").selectpicker('val',arrselections[0].pid);
        $("#deviceSelect").selectpicker('refresh');

        $("#stationSelect").selectpicker('val',arrselections[0].stationType);
        $("#stationSelect").selectpicker("refresh");

        if(arrselections[0].online == 1) {
            $('input:radio[name=optionsRadios]').filter('[value=1]').prop('checked',true);
        } else {
            $('input:radio[name=optionsRadios]').filter('[value=0]').prop('checked',true);
        }

        postdata.deviceId = arrselections[0].deviceId;
        $('#myModal').modal();
});

    $("#btn_delete").click(function () {
        var arrselections = $("#device_table").bootstrapTable('getSelections');
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
            url: ctxPath+'device/delete',
            contentType: 'application/json',
            data: JSON.stringify({"data":JSON.stringify(arrselections)}),
            success: function (data) {
                if (data.code == 200) {
                    toastr.success('提交数据成功');
                    $("#device_table").bootstrapTable('refresh');
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

    $("#btn_submit").click(function () {
        postdata.deviceName = $("#inputDeviceName").val();
        postdata.ip = $("#inputIp").val();
        postdata.port = $("#inputPort").val();
        postdata.online = $('input[name="optionsRadios"]:checked').val();
        postdata.deviceType = $("#deviceTypeSelect").val();
        postdata.pid = $("#deviceSelect").val();
        postdata.stationType=$("#stationSelect").val();
        postdata.stationPno=$("#inputStationPno").val();
        postdata.stationNo=$("#inputStationNo").val();
        postdata.positionCode=$("#inputPositionCode").val();
        if(postdata.deviceName=="" || postdata.ip=="" || postdata.port == "" || postdata.online == "" || postdata.branchId == "" || postdata.deviceTypeId == "" || postdata.stationType == "") {
            toastr.error("请完成'*'必填数据的输入");
            return;
        }
        $.ajax({
            type: "post",
            url: ctxPath+"device/submit",
            contentType: 'application/json',
            data: JSON.stringify(postdata),
            success: function (data) {
                if (data.code == 200) {
                    toastr.success('提交数据成功');
                    $("#device_table").bootstrapTable('refresh');
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

    $("#dish_btn_submit").click(function () {
        var deviceId = $('#deviceIdInput').val();
        var id = $('#dishSelect').val();
        var data = {
            'id':id,
            'deviceId':deviceId
        };

        $.ajax({
            type: "post",
            url: ctxPath+"device/dish-submit",
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (data) {
                if (data.code == 200) {
                    toastr.success('提交数据成功');
                    $("#dish_table").bootstrapTable('refresh');
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

function initSelect() {
    $.ajax({
        url:ctxPath+"device/select-option",
        type: 'POST',
        success: function (data) {
            var result = data.data;

            var devices = result.deviceSelection
            initDevice(devices);

            var deviceTypes = result.deviceTypeSelection;
            initDeviceType(deviceTypes);

            var dishes = result.dishSelection;
            initDish(dishes);
        }
    });
}

function initDevice(obj) {
    var html='<option value=""></option>';
    $.each(obj,function (index,item) {
        html += '<option value="'+item.deviceId+'">' + item.deviceName + '</option>';
    });
    $("#deviceSelect").selectpicker({
        noneSelectedText : '请选择'
    });
    $("#deviceSelect").append(html);
    $("#deviceSelect").selectpicker('refresh');
}

function initDeviceType(obj) {
    var html='<option value=""></option>';
    $.each(obj,function (index,item) {
        html += '<option value="'+item.typeCode+'">' + item.typeName + '</option>';
    });
    $("#deviceTypeSelect").selectpicker({
        noneSelectedText : '请选择'
    });
    $("#deviceTypeSelect").append(html);
    $("#deviceTypeSelect").selectpicker('refresh');
}

function initDish(obj) {
    var html='<option value=""></option>';
    $.each(obj,function (index,item) {
        html += '<option value="'+item.id+'">' + item.productName + '</option>';
    });

    $("#dishSelect").selectpicker({
        noneSelectedText : '请选择'
    });
    $("#dishSelect").append(html);
    $("#dishSelect").selectpicker('refresh');
}