$(document).ready(function(){
    $("#formRegistro").submit(function(e){
        e.preventDefault();
        let data = {
            nombreCompleto: $("#nombre").val(),
            usuario: $("#usuario").val(),
            contrasena: $("#contrasena").val(),
            tipoDocumento: $("#tipoDocumento").val(),
            documento: $("#numeroDocumento").val(),
            email: $("#correo").val()
        };
        $.ajax({
            url: "http://localhost:8080/api/usuario/",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function(response){
                alert("Usuario registrado correctamente");
                //window.location.href = "/login.html";
            },
            error: function(xhr){
                alert("Error al registrar usuario: " + (xhr.responseText || xhr.status));
            }
        });
    });
});
