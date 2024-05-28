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
function updateClientes() {
    const cliente = {
        _idCliente: document.getElementById('casilla-id-upt').value,
        _nombre: document.getElementById('casilla-name-upt').value,
        _apellidos: document.getElementById('casilla-surname-upt').value,
        _fecha_nacimiento: document.getElementById('casilla-fecha-nac-upt').value,
        _correo: document.getElementById('casilla-correo-upt').value,
        _contrasena: document.getElementById('casilla-contra-upt').value
    };

    console.log("Datos enviados:", cliente); // Log para depuración

    fetch("http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=CLIENTES.UPDATE", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(cliente)
    })
    .then(response => {
        console.log("Respuesta recibida:", response); // Log para depuración
        if (!response.ok) {
            throw new Error('Error en la respuesta del servidor');
        }
        return response.json();
    })
    .then(data => console.log('Empleado actualizado:', data))
    .catch(error => console.error('Error:', error));
}
const toggleClientUpdate = () => {
    const formulario = document.getElementById('formulario-update');
    if(formulario.style.display === 'none'){
        formulario.style.display = 'block'
    }
    else{
        formulario.style.display = 'none'
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

