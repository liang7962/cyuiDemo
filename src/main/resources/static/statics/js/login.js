layui.config({
    base: "js/"
}).use(['form', 'layer'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        $ = layui.jquery;
    //登录按钮事件
    form.on("submit(login)", function (data) {
        //parent.location.href = 'login.html';
        //alert(data.field.username);
        var datas = "userName=" + data.field.username + "&userPassword=" + data.field.password + "&captcha=" + data.field.captcha;
        $.ajax({
            type: "POST",
            url: "/cyui/login.html",
            data: datas,
            dataType: "json",
            success: function (result) {
                if (result.code == 0) {//登录成功
                    //window.location.href = '/cyui/index.html';
                    parent.location.href = '/cyui/index.html';
                } else {
                    layer.msg(result.msg, {icon: 5});
                    refreshCode();
                }
            }
        });
        return false;
    })
});
function refreshCode(){
    var captcha = document.getElementById("captcha");
    captcha.src = "/cyui/toVerification?t=" + new Date().getTime();
}
