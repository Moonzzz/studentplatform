function allSociety() {
    $("#mainFrame").hide();
    $("#innerView").show();
    $("#innerView").empty();
    /*嵌套网页到DIV中，让后台填充数据到页面上再进行嵌套*/
    $("#innerView").load("/allSocietys");
}

function allStuOrganize() {
    $("#mainFrame").hide();
    //$("#innerView").empty();
    $("#innerView").show();
    $("#innerView").empty();
    /*嵌套网页到DIV中，让后台填充数据到页面上再进行嵌套*/
    $("#innerView").load("/allStuOrganize");
}

function allColleges() {
    $("#mainFrame").hide();
    $("#innerView").show();
    $("#innerView").empty();
    /*嵌套网页到DIV中，让后台填充数据到页面上再进行嵌套*/
    $("#innerView").load("/allColleges");
}
function mainFrame() {
    $("#mainFrame").show();
    $("#innerView").hide();
    $("#innerView").empty();
}

function feded() {
    $("#detail").toggle(50);
}