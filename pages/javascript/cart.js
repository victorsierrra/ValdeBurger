function loadCart() {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    let cartTableBody = document.getElementById('cartTable').querySelector('tbody');
    cartTableBody.innerHTML = '';
    let total = 0;

    cart.forEach(item => {
        let row = document.createElement('tr');
        row.innerHTML = `
            <td>${item.product}</td>
            <td>$${item.price.toFixed(2)}</td>
            <td>${item.quantity}</td>
            <td>$${(item.price * item.quantity).toFixed(2)}</td>
            <td>
                <button onclick="removeFromCart('${item.product}')">Quitar</button>
                <button onclick="addToCart('${item.product}', ${item.price})">Añadir</button>
            </td>
        `;
        cartTableBody.appendChild(row);
        total += item.price * item.quantity;
    });

    document.getElementById('cartTotal').innerText = total.toFixed(2);
}

function removeFromCart(product) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    let item = cart.find(item => item.product === product);

    if (item) {
        if (item.quantity > 1) {
            item.quantity--;
        } else {
            cart = cart.filter(item => item.product !== product);
        }
    }

    localStorage.setItem('cart', JSON.stringify(cart));
    loadCart();
}

function addToCart(product, price) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    let item = cart.find(item => item.product === product);

    if (item) {
        item.quantity++;
    } else {
        cart.push({ product, price, quantity: 1 });
    }

    localStorage.setItem('cart', JSON.stringify(cart));
    loadCart();
}

window.onload = loadCart;