$(function () {
    deviceList();
});

//获取列表数据
function deviceList() {
    $.ajax({
        url: ctxPath+'list',
        type:"POST",
        async: false,
        success: function (data) {
            var result = data.data;
            var html='';
            var delHtml='<input type="button" name="" value="删除" class="btn btn-danger" onclick="del(this)" />';
            var updateHtml='<input type="button" name="" value="修改" class="btn btn-info" onclick="modify(this)" />';
            for(var i=0;i<result.length;i++) {
                html += '<tr>'+'<td>'+result[i].id+'</td>'
                    +'<td>'+result[i].ip+'</td>'
                    +'<td>'+result[i].port+'</td>'
                    +'<td>'+result[i].deviceType+'</td>'
                    +'<td>'+result[i].pid+'</td>'
                    +'<td>'+result[i].online+'</td>'
                    +'<td>'+result[i].branchNo+'</td>'
                    +'<td>'+result[i].remark+'</td>'
                    +'<td>'+delHtml+updateHtml+'</td></tr>';
            }
            $('#robot_table tbody').html(html);
        }
    });
}

function add() {
    window.location.href=ctxPath+'add';
}

function del(obj) {
    var oParentnode = obj.parentNode.parentNode;
    var olistTable = document.getElementById('robot_table');
    var firstChild = oParentnode.firstChild;
    var id = firstChild.innerText;
    $.ajax({
        url: ctxPath+'delete',
        type:"POST",
        async: false,
        data: JSON.stringify({'id':id}),
        contentType: 'application/json',
        success: function () {
            olistTable.removeChild(oParentnode);
        }
    });
}

function modify(obj) {

}