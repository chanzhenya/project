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
        toolbar: 'toolbar',
        striped: true,
        cache: false,
        clickToSelect: true,
        uniqueId: "ID",
        columns: [{
            checkbox: true
        }, {
            field: 'deviceId',
            title: '设备ID'
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
            field: 'branchName',
            title: '门店'
        }, {
            field: 'pid',
            title: '所属设备ID'
        }, {
            field: 'online',
            title: '是否在线'
        }, {
            field: 'attributeName',
            title: '附加属性名称'
        }, {
            field: 'attributeValue',
            title: '附加属性值'
        }, ]
    });
}

function initButton() {

    var postdata = {};

    $('#btn_add').click(function () {
        $("#myModalLabel").text("新增");
        $("#myModal").find(".form-control").val("");
        $('#myModal').modal();
        $('input:radio[name=optionsRadios]').filter('[value=1]').prop('checked',true);
        postdata.deviceId = "";
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
        $("#inputRemark").val(arrselections[0].remark);
        $("#deviceTypeSelect").val(arrselections[0].deviceTypeId);
        $("#branchSelect").val(arrselections[0].branchId);
        $("#attributeSelect").val(arrselections[0].attributeId);
        if(arrselections[0].pid != null) {
            $("#deviceSelect").val(arrselections[0].pid);
        } else {
            $("#deviceSelect").val("");
        }

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
            error: function () {
                toastr.error('Error');
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
        postdata.branchId = $("#branchSelect").val();
        postdata.deviceTypeId = $("#deviceTypeSelect").val();
        postdata.pid = $("#deviceSelect").val();
        postdata.attributeId=$("#attributeSelect").val();
        if(postdata.deviceName=="" || postdata.ip=="" || postdata.port == "" || postdata.online == "" || postdata.branchId == "" || postdata.deviceTypeId == "") {
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
            var branches = result.branches;
            initBranch(branches);

            var devices = result.devices;
            initDevice(devices);

            var deviceTypes = result.deviceTypes;
            initDeviceType(deviceTypes);

            var attributes = result.attributes;
            initAttribute(attributes);
        }
    });
}

function initBranch(obj) {
    var html='';
    $.each(obj,function (index,item) {
        html += '<option value="'+item.branchId+'">' + item.branchName + '</option>';
    });
    $("#branchSelect").html(html);
}

function initDevice(obj) {
    var html='<option value=""></option>';
    $.each(obj,function (index,item) {
        html += '<option value="'+item.deviceId+'">' + item.deviceId + ' - ' + item.deviceName + '</option>';
    });
    $("#deviceSelect").html(html);
}

function initDeviceType(obj) {
    var html='';
    $.each(obj,function (index,item) {
        html += '<option value="'+item.typeId+'">' + item.typeCode + '-' + item.typeName + '</option>';
    });
    $("#deviceTypeSelect").html(html);
}

function initAttribute(obj) {
    var html='<option value=""></option>';
    $.each(obj,function (index,item) {
        html += '<option value="'+item.attributeId+'">' + item.attributeName + ' - ' + item.attributeValue + '</option>';
    });
    $("#attributeSelect").html(html);
}