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
