$(document).ready(function(){
    $.ajax({
        url: "http://localhost:8080/api/usuario/",
        method: "GET",
        success: function(data){
            let tbody = "";
            data.forEach(function(cliente){
                tbody += `<tr>` +
                    `<td style='display:none;'>${cliente.idUsuario}</td>` +
                    `<td>${cliente.nombre || cliente.nombreCompleto || ''}</td>` +
                    `<td>${cliente.email || cliente.correo || ''}</td>` +
                    `<td>${cliente.tipoDocumento || ''}</td>` +
                    `<td>${cliente.numeroDocumento || cliente.documento || ''}</td>` +
                `</tr>`;
            });
            $("#tablaClientes tbody").html(tbody);
        },
        error: function(){
            alert("No se pudo obtener el listado de clientes.");
        }
    });
});
