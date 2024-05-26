const urlClientes = 'http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=CLIENTES.FIND_ALL';
let clientesLoaded = false;

const fetchClientes = async () => {
    try {
        const result = await fetch(urlClientes);
        const data = await result.json();
        console.log('Clientes obtenidos de la API: ', data);
        printClientes(data);
        clientesLoaded = true;
    } catch (error) {
        console.log('Error al obtener datos de la API: ', error);
    }
}

const printClientes = (clientes) => {
    const table = document.getElementById('tabla-clientes');
    const tbody = table.querySelector('tbody');
    table.style.display= 'table';

    clientes.forEach(cliente => {
        const {
            _idCliente,
            _nombre,
            _apellidos,
            _fecha_nacimiento,
            _correo,
            _contrasena
        } = cliente;

        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${_idCliente}</td>
            <td>${_nombre}</td>
            <td>${_apellidos}</td>
            <td>${_fecha_nacimiento}</td>
            <td>${_correo}</td>
            <td>${_contrasena}</td>
        `;
        tbody.appendChild(row);
    });
}

const toggleClientes = () => {
    const table = document.getElementById('tabla-clientes');
    const boton = document.getElementById('boton-find');
    if (table.style.display === 'none') {
        if (!clientesLoaded) {
            fetchClientes();
        } else {
            table.style.display = 'table';
        }
    } else {
        table.style.display = 'none';
    }
}

const deleteClient = async (idCliente) => {
    try {
        const urlDeleteCliente = `http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=CLIENTES.DELETE&ID_CLIENTE=${idCliente}`
        await fetch(urlDeleteCliente, {
            method: 'DELETE'
        })
            alert('Cliente eliminado.');
    } catch (error) {
        console.error('Error al eliminar cliente:', error);
    }
};
const toggleClientesDelete = () => {
    const formulario = document.getElementById('formulario-delete');
    if(formulario.style.display === 'none'){
        formulario.style.display = 'block'
    }
    else{
        formulario.style.display = 'none'
    }
}


document.getElementById('formulario-delete').addEventListener('submit', (event) => {
    event.preventDefault();
    const idCliente = document.getElementById('casilla-id').value;
    if (idCliente) {
        deleteClient(idCliente);
    } else {
        alert('Debe ingresar un ID válido.');
    }
});

