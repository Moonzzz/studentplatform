function allFound() {
    $("#mainFrame").hide();
    $("#innerView").show();
    /*嵌套网页到DIV中，让后台填充数据到页面上再进行嵌套*/
    $("#innerView").load("/toshowfound");
}