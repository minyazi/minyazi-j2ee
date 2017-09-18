// JavaScript Document
$(function() {
    $("a, button, :button, :submit, :reset").click(function getAuthority() {
        $.getJSON("authority/authority.action", function(json) {
            var username = json.username;
            if (username == "") {
                top.location.href = "";
            }
        });
    });
});
