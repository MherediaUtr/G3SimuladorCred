let txusr;
let txpass;

$(document).ready(function(){
    txusr=$("#usuario");
    txpass=$("#contrasena");

    $("#entrar").click(function(){
        //validar que esten ambos ingresados
        if(txusr.value=="" || txpass.value==""){
            let msg=(txusr.value=="" )?"campo usuario":"";
            msg+=(txusr.value=="" && txpass.value=="")? " y ":"";
            msg=(txpass.value=="" )?"campo contraseña":"";
            msg+=" no pueden estar vacios.";
            alert(msg);
            return;
        }
        $.ajax({
            url:"localhost:8080/api/usuario/login",
            method:'POST',
            data: { usuario: txusr.value, contrasena: txpass.value },
            success: validarLogin,
        });

    });

});

function validarLogin(data){
    if(data!=null){
        alert ("logueado "+data['nombreCompleto']);
    }else
        alert("usuario no existe o datos son incorrectos");
}