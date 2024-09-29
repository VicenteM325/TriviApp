$('document').ready(function () {
    $("#form-login").validate({

        rules: {
            usuario: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            usuario: {
                required: "No ha ingresado un usuario"
            },
            password: {
                required: "No ha ingresado su contraseña"
            }
        }
    });
});
