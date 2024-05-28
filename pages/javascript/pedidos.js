const urlPedidos = 'http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=PEDIDOS.FIND_ALL';
let pedidosLoaded = false;
const fetchPedidos = async ()=> {
    try{
        const result = await fetch(urlPedidos);
        const data = await result.json();
        console.log('Pedidos obtenidos de la API: ', data);
        pedidosLoaded = true;
        printPedidos(data);
    }catch(error){
        console.log('Error al obtener datos de la API: ', error);
    }
}

const printPedidos = (pedidos) => {
    const table = document.getElementById('tabla-pedidos');
    const tbody = table.querySelector('tbody');
    table.style.display= 'table';

    pedidos.forEach(pedido => {
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
        <td>${_idEmpleados}</td>
        <td>${_idClientes}</td>
        <td>${_fecha_pedido}</td>
        <td>${_direccion_entrega}</td>
        <td>${_precio_total}</td>
        `;
        tbody.appendChild(row);
    })
}
const togglePedidos = () => {
    const table = document.getElementById('tabla-pedidos');
    if (table.style.display === 'none') {
        if (!pedidosLoaded) {
            fetchPedidos();
        } else {
            table.style.display = 'table';
        }
    } else {
        table.style.display = 'none';
    }
}
//fetchPedidos();