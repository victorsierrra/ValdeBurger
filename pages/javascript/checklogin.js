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


        const data = await result.text();
        alert(data)
        document.getElementById('message').textContent = data;
    } catch (error) {
        console.error('Error:', error);
        document.getElementById('message').textContent = 'E-Mail or Password no valid';
    }
}
