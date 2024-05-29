const url = "http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=CLIENTES.ADD";

    function registrarCliente() {
        console.log('Funciona')
        const cliente = {
            _idCliente: 0,
            _nombre: document.getElementById('cliente-nombre').value,
            _apellidos: document.getElementById('cliente-apellido').value,
            _contrasena: document.getElementById('cliente-contrasena').value,
            _fecha_nacimiento: document.getElementById('cliente-fecha-nacimiento').value,
            _correo: document.getElementById('cliente-correo').value
        };

        fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(cliente)
        })
        .then(response => response.json(), alert('Client registered'))
        .catch(error => console.error('Error:', error));
    }