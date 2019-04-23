function allAttractions() {
    $("#mainFrame").hide();
    $("#innerView").show();
    /*嵌套网页到DIV中，让后台填充数据到页面上再进行嵌套*/
    $("#innerView").load("/allAttraction");
}

function allFoods() {
    $("#mainFrame").hide();
    $("#innerView").show();
    /*嵌套网页到DIV中，让后台填充数据到页面上再进行嵌套*/
    $("#innerView").load("/allFood");
}

function getMap(){
    $("#mainFrame").hide();
    $("#innerView").show();
    //嵌套网页到DIV中，让后台填充数据到页面上再进行嵌套
    $("#innerView").load("/Map");
}

function Test(){
    $("#mainFrame").hide();
    $("#innerView").show();
    //嵌套网页到DIV中，让后台填充数据到页面上再进行嵌套
    $("#innerView").load("/test");
}