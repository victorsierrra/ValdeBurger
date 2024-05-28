const urlProducto = 'http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=PRODUCTO.FIND_ALL';
let productosLoaded = false;

const fetchProducto = async () => {
    try {
        const result = await fetch(urlProducto);
        const data = await result.json();
        console.log('Productos obtenidos de la API: ', data);
        printProducto(data);
    } catch (error) {
        console.log('Error al obtener datos de la API: ', error);
    }
}

const printProducto = (productos) => {
    const startersSection = document.getElementById('ENT');
    const hamburgersSection = document.getElementById('BUR');
    const dessertsSection = document.getElementById('POS');
    const drinksSection = document.getElementById('DRI');

    startersSection.innerHTML = '';
    hamburgersSection.innerHTML = '';
    dessertsSection.innerHTML = '';
    drinksSection.innerHTML = '';

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

      // Verificar si la descripción está definida y no está vacía
      let descripcion = _descripcion || '';
      descripcion = descripcion.trim(); // Eliminar espacios en blanco al inicio y al final

      // Verificar si los ingredientes están definidos y no están vacíos
      let ingredientes = _ingredientes || '';
      ingredientes = ingredientes.trim();

        const productCard = createProductCard({
            id: _idProducto,
            name: _nombre,
            description: _descripcion,
            ingredients: _ingredientes,
            price: _precio,
            imageUrl: _ruta_imagen,
            allergies: [_ruta_imagen_alergias1, _ruta_imagen_alergias2, _ruta_imagen_alergias3],
            category: _categoria
        });

        

        console.log(`Añadiendo tarjeta para producto ${_nombre} a la categoría ${_categoria}`);
        
        switch (_categoria) {
            case 'ENT':
                startersSection.appendChild(productCard);
                break;
            case 'BUR':
                hamburgersSection.appendChild(productCard);
                break;
            case 'POS':
                dessertsSection.appendChild(productCard);
                break;
            case 'DRI':
                drinksSection.appendChild(productCard);
                break;
            default:
                console.log(`Categoría desconocida: ${_categoria}`);
                break;
        }
    });

    console.log("Productos impresos en el DOM");
}

const createProductCard = (product) => {
    const card = document.createElement('div');
    card.className = 'card';
    card.innerHTML = `
        <div class="card-inner">
            <div class="card-front">
                <img src="${product.imageUrl}" alt="${product.name}">
                <div class="card-title-container">
                    <div class="title-allergy-wrapper">
                        <div class="card-title">${product.name}</div>
                        ${getAllergyIcons(product.allergies)}
                    </div>
                </div>
                ${product.description ? `<div class="card-description">${product.description}</div>` : ''}
                <div class="card-price">${product.price.toFixed(2)}€</div>
            </div>
            <div class="card-back">
                ${product.ingredients ? `<div class="card-title">INGREDIENTS:</div>` : ''}
                ${product.ingredients ? `<div class="card-description">${product.ingredients}</div>` : ''}
                <button class="add-to-cart-button" onclick="addToCart('${product.name}', ${product.price})">Añadir al Carrito</button>
            </div>
        </div>
    `;
    return card;
}

const getAllergyIcons = (allergies) => {
    return allergies.map(allergy => allergy ? `<img src="${allergy}" class="img-alergias">` : '').join('');
}

document.addEventListener('DOMContentLoaded', () => {
    fetchProducto();
});
