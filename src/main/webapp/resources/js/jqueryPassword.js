$(document).ready(function() {
    $("#viewPassword").hide();

    $("#randomPassword").click(function(){
        $.get("../randompassword", function(responseText){
            $("#newPassword").val(responseText);
            $("#confirmNewPassword").val(responseText);
            $("#viewPassword").show();
        });
    });

    $("#viewPassword").click(function(){
        $("#newPassword").prop("type", "text");
    })
});