const urlProducto = 'http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=PRODUCTO.FIND_ALL';
const urlAdd = "http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=PRODUCTO.ADD";
const urlUpdate = "http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=PRODUCTO.UPDATE";
let productosLoaded = false;
const fetchProducto = async ()=> {
    try{
        const result = await fetch(urlProducto);
        const data = await result.json();
        console.log('Productos obtenidos de la API: ', data);
        printProducto(data);
    }catch(error){
        console.log('Error al obtener datos de la API: ', error);
    }
}

const printProducto = (productos) => {
    const table = document.getElementById('tabla-producto');
    const tbody = table.querySelector('tbody');
    table.style.display= 'table';

    productos.forEach(producto => {
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
        } = producto;

        const row = document.createElement('tr');
        row.innerHTML = `
        <td>${_idProducto}</td>
        <td>${_nombre}</td>
        <td>${_descripcion}</td>
        <td>${_ingredientes}</td>
        <td>${_precio}</td>
        <td>${_ruta_imagen}</td>
        <td>${_ruta_imagen_alergias1}</td>
        <td>${_ruta_imagen_alergias2}</td>
        <td>${_ruta_imagen_alergias3}</td>
        <td>${_categoria}</td>
        `;
        tbody.appendChild(row);
    })
}
const toggleProductos = () => {
    const table = document.getElementById('tabla-producto');
    if (table.style.display === 'none') {
        if (!productosLoaded) {
            fetchProducto();
            productosLoaded = true;
        } else {
            table.style.display = 'table';
        }
    } else {
        table.style.display = 'none';
    }
}

function registrarProducto() {
    const producto = {
        _idProducto: document.getElementById('casilla-id-add').value,
        _categoria: document.getElementById('casilla-cate').value,
        _nombre: document.getElementById('casilla-name').value,
        _descripcion: document.getElementById('casilla-description').value,
        _ingredientes: document.getElementById('casilla-ingredientes').value,
        _precio: document.getElementById('casilla-precio').value,
        _ruta_imagen: document.getElementById('casilla-imagen').value,
        _ruta_imagen_alergias1: document.getElementById('casilla-imagen1').value,
        _ruta_imagen_alergias2: document.getElementById('casilla-imagen2').value,
        _ruta_imagen_alergias3: document.getElementById('casilla-imagen3').value
    };

    fetch(urlAdd, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(producto)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error en la respuesta del servidor');
        }
        alert('Producto added')
        return response.json();
    })
    .then(data => console.log('Producto editado:', data))
    .catch(error => console.error('Error:', error));
}
const toggleProductAdd = () => {
    const formulario = document.getElementById('formulario-add');
    if(formulario.style.display === 'none'){
        formulario.style.display = 'block'
    }
    else{
        formulario.style.display = 'none'
    }
}
function updateProducto() {
    const producto = {
        _idProducto: document.getElementById('casilla-id-upt').value,
        _categoria: document.getElementById('casilla-cate-upt').value,
        _nombre: document.getElementById('casilla-name-upt').value,
        _descripcion: document.getElementById('casilla-description-upt').value,
        _ingredientes: document.getElementById('casilla-ingredientes-upt').value,
        _precio: document.getElementById('casilla-precio-upt').value,
        _ruta_imagen: document.getElementById('casilla-imagen-upt').value,
        _ruta_imagen_alergias1: document.getElementById('casilla-imagen1-upt').value,
        _ruta_imagen_alergias2: document.getElementById('casilla-imagen2-upt').value,
        _ruta_imagen_alergias3: document.getElementById('casilla-imagen3-upt').value
    };

    fetch(urlUpdate, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(producto)
    })
    .then(response => response.json())
    .then(data => {
        if (data.status === 'success') {
            alert('Product updated :', data);
        } else {
            console.error('Error en la actualización:', data.message);
        }
    })
    .catch(error => console.error('Error al actualizar producto:', error));
}
const deleteProduct = async (idProducto) => {
    try {
        const urlDeleteProducto = `http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=PRODUCTO.DELETE&ID_PRODUCTO=${idProducto}`;
        await fetch(urlDeleteProducto, {
            method: 'DELETE'
        })
            alert('Producto eliminado.');
    } catch (error) {
        console.error('Error al eliminar al producto:', error);
    }
};
const toggleProductDelete = () => {
    const formulario = document.getElementById('formulario-delete');
    if(formulario.style.display === 'none'){
        formulario.style.display = 'block'
    }
    else{
        formulario.style.display = 'none'
    }
}
const toggleProductUpdate = () => {
    const formulario = document.getElementById('formulario-update');
    if(formulario.style.display === 'none'){
        formulario.style.display = 'block'
    }
    else{
        formulario.style.display = 'none'
    }
}


document.getElementById('formulario-delete').addEventListener('submit', (event) => {
    event.preventDefault();
    const idProducto = document.getElementById('casilla-id').value;
    if (idProducto) {
        deleteProduct(idProducto);
    } else {
        alert('Debe ingresar un ID válido.');
    }
});