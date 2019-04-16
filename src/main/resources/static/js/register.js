var rand;//60秒内输入正确的验证密码
var email;
$("#btnSendCode").click(function () {
    layer.msg('正在调用该函数', {icon: 1});

    var x = 1000;
    var y = 9999;
    rand = parseInt(Math.random() * (x - y + 1) + y);
    email = $('#email').val();
    $("#registe").removeAttr("disabled");//启用按钮
    sendMessage();
});
$("#registe").click(function () {
    var inputcode = $("#checkcode").val();
    if (rand == inputcode) {
        layer.msg('输入正确', {icon: 1});
    }else{
        layer.msg('输入错误', {icon: 2});
    }
});
var InterValObj; //timer变量，控制时间
var count = 30; //间隔函数，1秒执行
var curCount;//当前剩余秒数
function sendMessage() {
    curCount = count;
    //设置button效果，开始计时
    $("#btnSendCode").attr("disabled", "true");
    $("#checkcode").val("请在" + curCount + "秒内输入验证码");
    InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
    //向后台发送处理数据
    $.ajax({
        type: "GET", //用POST方式传输
        contentType: 'appliction/json',
        dataType: "json", //数据格式:JSON
        url: 'LoginCheck.action', //目标地址
        data: "action=" + "codeCheck" + "&email=" + email + "&checkcode=" + rand,
        success: function (msg) {
            if (msg == 200) {
                layer.msg('发送验证码成功', {icon: 1});
            } else {
                layer.msg('发送验证码失败', {icon: 5, time: 1000});
            }
        }
    });
}

//timer处理函数
function SetRemainTime() {
    if (curCount == 0) {
        window.clearInterval(InterValObj);//停止计时器
        $("#btnSendCode").removeAttr("disabled");//启用按钮
        $("#btnSendCode").val("重新发送验证码");
        $("#registe").attr("disabled", "true");

    }
    else {
        curCount--;
        $("#checkcode").val("请在" + curCount + "秒内输入验证码");
    }
}