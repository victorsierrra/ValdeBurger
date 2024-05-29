document.getElementById('viewCartButton').addEventListener('click', function() {
    window.location.href = 'cart.html';
});

function addToCart(product, price) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    let item = cart.find(item => item.product === product);

    if (item) {
        item.quantity++;
    } else {
        cart.push({ product, price, quantity: 1 });
    }

    localStorage.setItem('cart', JSON.stringify(cart));
    Swal.fire({
        title: 'Product add',
        html: `<strong>${product}</strong> is added the cart`,
        icon: 'success',
        confirmButtonText: 'Accept',
        confirmButtonColor: '#e74c3c'
    });
}