function getUser(URL) {
    var email = $("#email").val();
    var user = null;

    $.ajax({
        url: URL,
        contentType: "text/html",
        type: "GET",
        email : email,
        success: function (result) {
            user = result;
        },
        error: function () {
            console.log('error');
        }
    });

    return user;
}

function renderUser(URL) {
    var user = getUser(URL);
    $("#userData").append(user);
}