let txusr;
let txpass;
const URL_FINANCIERA="http://localhost:8080";

// Configurar jQuery para agregar automáticamente el token en el header Authorization en todas las peticiones AJAX
$.ajaxSetup({
    beforeSend: function(xhr, settings) {
        // No agregar el token en la petición de login
        if (!settings.url.endsWith('/api/v1/security')) {
            let token = sessionStorage.getItem("tokenFinaciera");
            if (token) {
                xhr.setRequestHeader("Authorization", "Bearer " + token);
            }
        }
    }
});

$(document).ready(function(){
    txusr=$("#usuario");
    txpass=$("#contrasena");

    $("#entrar").click(function(){
        //validar que esten ambos ingresados
        if(txusr.val()=="" || txpass.val()==""){
            let msg=(txusr.val()=="" )?"campo usuario":"";
            msg+=(txusr.val()=="" && txpass.val()=="")? " y ":"";
            msg=(txpass.val()=="" )?"campo contraseña":"";
            msg+=" no pueden estar vacios.";
            alert(msg);
            return;
        }
        $.ajax({
            url:URL_FINANCIERA+"/api/v1/security",
            method:'POST',
            contentType: 'application/json',
            data: JSON.stringify({ usuario: txusr.val(), contrasena: txpass.val() }),
            success: validarLogin
        });

    });

});

function validarLogin(response, status, xhr){
    if(xhr.status==200){
        let headerValue = xhr.getResponseHeader("Authorization");
        // Extraer solo el token sin el prefijo 'Bearer '
        let token = headerValue ? headerValue.replace("Bearer ", "") : null;
        //guardar solo el token limpio en session storage
        sessionStorage.setItem("tokenFinaciera", token);
        alert ("logueado " + token);
    }else{
        alert("usuario no existe o datos son incorrectos");
    }
}