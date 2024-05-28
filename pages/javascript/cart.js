const urlAdd = "http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=PEDIDOS.ADD";

function loadCart() {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    let cartTableBody = document.getElementById('cartTable').querySelector('tbody');
    cartTableBody.innerHTML = '';
    let total = 0;
    cart.forEach(({ product, price, quantity }) => {
        let row = document.createElement('tr');
        row.innerHTML = `
            <td>${product}</td>
            <td>$${price.toFixed(2)}</td>
            <td>${quantity}</td>
            <td>$${(price * quantity).toFixed(2)}</td>
            <td>
                <button onclick="updateCart('${product}', -1)">Remove</button>
                <button onclick="updateCart('${product}', 1)">Add</button>
            </td>
        `;
        cartTableBody.appendChild(row);
        total += price * quantity;
    });

    document.getElementById('cartTotal').innerText = total.toFixed(2);
}

    var currentDate = new Date();
    const formattedDate = currentDate.toLocaleDateString();


function updateCart(product, change) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    let item = cart.find(item => item.product === product);

    if (item) {
        item.quantity += change;
        if (item.quantity <= 0) cart = cart.filter(item => item.product !== product);
    }

    localStorage.setItem('cart', JSON.stringify(cart));
    loadCart();
}

window.onload = loadCart;

function registrarPedido() {
    const fecha_entrega = formattedDate;
    const totalCarrito = document.getElementById('cartTotal').innerText;
    const direccion_entrega = document.getElementById('usuario-direccion').value;
    const idCliente = localStorage.getItem('ID_CLIENTE');

    /*if (!idCliente || !fecha_entrega || !direccion_entrega || !totalCarrito) {
        console.error('Missing required fields');
        console.log('idCliente:', idCliente);
        console.log('fecha_entrega:', fecha_entrega);
        console.log('direccion_entrega:', direccion_entrega);
        console.log('totalCarrito:', totalCarrito);
        return;
    }*/

    const pedido = {
        _idPedido: 0,
        _idEmpleados: 4,
        _idClientes: idCliente,
        _fecha_pedido: fecha_entrega,
        _direccion_entrega: direccion_entrega,
        _precio_total: parseFloat(totalCarrito)
    };

    console.log('Pedido:', pedido);

    fetch(urlAdd, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(pedido)
    })
    .then(response => {

        return response.json();
    })
    .then(data => console.log('Pedido registrado:', data))
    .catch(error => console.error('Error:', error));
}