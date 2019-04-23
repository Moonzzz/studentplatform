function letInnerViewOut(url) {
    $("#mainFrame").hide();
    $("#innerView").show();
    $("#innerView").empty();
    $("#innerView").load(url);

}
function allContests() {
    letInnerViewOut("/officialPage?page=contests");
}
function allInfoDynamic() {
    letInnerViewOut("/officialPage?page=infodynamics");
}
function collegeIntro() {
    letInnerViewOut("http://localhost:8080/pages/official/collegeintro.html");
}

function allSociety() {
    letInnerViewOut("/allSocietys");
}

function allStuOrganize() {
    letInnerViewOut("/allStuOrganize");
}


function allColleges() {
    letInnerViewOut("/allColleges");
}
function mainFrame() {
    $("#mainFrame").show();
    $("#innerView").hide();
    $("#innerView").empty();
}

function feded() {
    $("#detail").toggle(50);
}