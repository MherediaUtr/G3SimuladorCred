document.addEventListener("DOMContentLoaded", () => {
    fetch("http://localhost:8080/api/productos")
        .then(res => res.json())
        .then(data => {
            const tbody = document.querySelector("#productos-table tbody");
            tbody.innerHTML = "";
            data.forEach(producto => {
                const tr = document.createElement("tr");
                tr.innerHTML = `
                    <td>${producto.idProd}</td>
                    <td>${producto.descripcion}</td>
                    <td>${producto.tasa}</td>
                    <td>${producto.mesesMinimo}</td>
                    <td>${producto.mesesMaximo}</td>
                    <td>${producto.ingresoMinimo}</td>
                `;
                tbody.appendChild(tr);
            });
        })
        .catch(err => console.error("Error al cargar productos:", err));
});