const urlClientes = 'http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=CLIENTES.FIND_ALL';

const fetchClientes = async ()=> {
    try{
        const result = await fetch(urlClientes);
        const data = await result.json();
        console.log('Clientes obtenidos de la API: ', data);
        printClientes(data);
    }catch(error){
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
    })
}
//fetchClientes();