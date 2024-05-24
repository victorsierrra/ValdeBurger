const urlProducto = 'http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=PRODUCTO.FIND_ALL';

const fetchProducto = async ()=> {
    try{
        const result = await fetch(urlProducto);
        const data = await result.json();
        console.log('Productos obtenidos de la API: ', data);
        printEmployees(data);
    }catch(error){
        console.log('Error al obtener datos de la API: ', error);
    }
}

const printProducto = (producto) => {
    const table = document.getElementById('tabla-producto');
    const tbody = table.querySelector('tbody');
    table.style.display= 'table';

    producto.forEach(productos => {
        const {
            _idProducto,
            _categoria,
            _nombre,
            _descripcion,
            _ingredientes,
            _precio,
            _ruta_imagen,
            _ruta_imagen_alergias1,
            _ruta_imagen_alergias2,
            _ruta_imagen_alergias3,
        } = productos;

        const row = document.createElement('tr');
        row.innerHTML = `
        <td>${_idProducto}</td>
        <td>${_categoria}</td>
        <td>${_nombre}</td>
        <td>${_descripcion}</td>
        <td>${_ingredientes}</td>
        <td>${_precio}</td>
        <td>${_ruta_imagen}</td>
        <td>${_ruta_imagen_alergias1}</td>
        <td>${_ruta_imagen_alergias2}</td>
        <td>${_ruta_imagen_alergias3}</td>
        `;
        tbody.appendChild(row);
    })
}
//fetchProducto();