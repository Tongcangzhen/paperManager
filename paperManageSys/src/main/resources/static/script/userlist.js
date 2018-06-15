//删除
function del(id) {
    var o = $(id).parents('tr');
    o.remove();
    var id = o.attr("id");

    alert("移除数据id="+id);
};
window.onload = function() {
    getUserList();
};

function getUserList() {
    $.ajax({
        url: 'userlist',
        type: 'get',
        dataType: "json",
        success: function(json) {
            var jsonObj = jQuery.parseJSON(json);
            if(jsonObj.success) {
                showlist(jsonObj);
            } else {
                alert(jsonObj.msg);
            }
        },
        error: function(xhr, status, error) {
            var json = '{\"data\":[{\"age\":18,\"id\":1,\"password\":\"sfasgfaf\",\"userName\":\"测试\"},{\"age\":20,\"id\":2,\"password\":\"123456\",\"userName\":\"user\"}],\"msg\":\"成功\",\"success\":true}';
            var jsonObj = jQuery.parseJSON(json);
            showlist(jsonObj.data);
        }
    });
}

function showlist(userlist) {
    var str = '';
    for(var i = 0; i < userlist.length; i++) {
        var user = userlist[i];
        str += '<tr id=' + user.id + '>';
        str += '<td><input name="" type="checkbox" value= ""/></td>';
        str += '<td>' + user.id + '</td>';
        str += '<td>' + user.userName + '</td>';
        str += '<td>' + user.password + '</td>';
        str += '<td>2013-09-09 15:05</td>';
        str += '<td>已审核</td>';
        str += '<td><a href="#" class="tablelink">  查看  </a><a onclick="del(this)" href="#" class="tablelink">  删除   </a></td>';
        str += '</tr>'; //拼接str
    }
    $("#stable").empty(); //清空子元素
    $("#stable").append(str); //添加元素
    $('.tablelist tbody tr:odd').addClass('odd');

}