function signOut() {
    alertify.confirm("Are you sure ?",
            function() {
                $.post("logoutCompany", {}, function(data, textStatus, jqXHR) {
                    if (textStatus === 'success' & data === 'OK') {
                        document.location.href = "index.jsp";
                    }
                }, 'html');
            });
}
function loginUser() {
    $.post('checkSession', {}, function(data, textStatus, jqXHR) {
        if (textStatus === 'success') {
            if (data === 'FORWARD') {
                document.location.href = "admin_panel.jsp";
            }
        }
    }, 'html');
}
function getAgents() {
//    alertify.log("Please wait...");
    $.post('registered_agents.jsp', {}, function(data) {
        $('#main-content').html(data);
    });
}
//function getOverview() {
////    alertify.log("Please wait...");
//    $.post('res/register_owner.jsp', {}, function(data) {
//        $('#main-content').html(data);
//    });
//}
function registerAgent() {
//    alertify.log("Please wait...");
    $.post('register_agent.jsp', {}, function(data) {
        $('#main-content').html(data);
    });
}
function getClaims() {
    loadContent('res/admin_claim.jsp');
}
function loadContent(content) {
//    alertify.log("Please wait...");
    $.post(content, {}, function(data) {
        $('#main-content').html(data);
    });
}
function getLoader() {
    return '<div style="margin: auto 0;text-align:center;"><img src="img/loader/roller.gif" style="max-width:38px;text-align:center;verticle-alig:middle;"<br><p style="margin-top:10px;display:block"><label class="label label-warning">Please wait...</label></p></div>';
}
$(document).ready(function() {
    var body_id = $("body").attr('id');

    if (body_id === 'login') {
//        loginUser();
    } else if (body_id === 'admin-panel') {
        alertify.logPosition("bottom right");
        alertify.log("Welcome !");
        loadContent("res/admin_claim.jsp");
    }
});