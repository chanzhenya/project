$(function () {
    $('#myTab li:eq(0) a').tab('show');
    //iframeTable();
});

function initTab() {
    $('#device-type').click()
    $().onload()
}
function iframeTable() {
    var iframe="";
    iframe='<iframe src="device" frameborder="0" style="width: 100%; height: 700px; padding-top: 10px;"></iframe>';
    $('#home').append(iframe);
}