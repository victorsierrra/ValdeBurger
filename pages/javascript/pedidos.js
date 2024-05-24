const urlPedidos = 'http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=PEDIDOS.FIND_ALL';

const fetchPedidos = async ()=> {
    try{
        const result = await fetch(urlPedidos);
        const data = await result.json();
        console.log('Pedidos obtenidos de la API: ', data);
        printPedidos(data);
    }catch(error){
        console.log('Error al obtener datos de la API: ', error);
    }
}

const printPedidos = (pedidos) => {
    const table = document.getElementById('tabla-pedidos');
    const tbody = table.querySelector('tbody');
    table.style.display= 'table';

    Pedidos.forEach(pedido => {
        const {
            _idPedidos,
            _fecha_pedido,
            _direccion_entrega,
            _precio_total,
            _idEmpleados,
            _idClientes,
        } = pedido;

        const row = document.createElement('tr');
        row.innerHTML = `
        <td>${_idPedidos}</td>
        <td>${_fecha_pedido}</td>
        <td>${_direccion_entrega}</td>
        <td>${_precio_total}</td>
        <td>${_idEmpleados}</td>
        <td>${_idClientes}</td>
        `;
        tbody.appendChild(row);
    })
}
//fetchPedidos();