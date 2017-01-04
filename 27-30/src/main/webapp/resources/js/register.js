function register(URL) {
    var firstName = $('#firstName').val();
    var lastName = $('#lastName').val();
    var password = $('#password').val();
    var email = $('#email').val();

    var user = JSON.stringify({
        firstName: firstName,
        lastName: lastName,
        password: password,
        email: email
    });

    console.log(user);

    $.ajax({
        url: URL,
        contentType : 'application/json',
        dataType : 'json',
        type: "POST",
        data: user,
        success: function (result) {
            console.log(result);
        },
        error: function () {
            console.log('error');
        }
    });
}