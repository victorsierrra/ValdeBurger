async function verificarEmpleado() {
    const correo = document.getElementById('login-correo').value;
    const contrasena = document.getElementById('login-contra').value;
    const url = `http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=CLIENTES.LOGIN&CORREO=${correo}&CONTRASENA=${contrasena}`;

    try {
        const result = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'text/plain'
            }
        });

        if (!result.ok) {
            throw new Error('Error en la solicitud');
        }

        const data = await result.text();
        alert(`ID Cliente recibido: ${data}`);

        // Guarda el ID_CLIENTE en localStorage
        const idCliente = data.trim();  // Asegúrate de quitar espacios en blanco
        localStorage.setItem('ID_CLIENTE', idCliente);

        // Puedes usar el idCliente en otras partes de tu código si es necesario
        console.log('ID_CLIENTE:', idCliente);

        document.getElementById('message').textContent = `Welcome back`;
    } catch (error) {
        console.error('Error:', error);
        localStorage.removeItem('ID_CLIENTE');
        document.getElementById('message').textContent = 'E-Mail or Password no valid';
    }
}