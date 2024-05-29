const urlEmployees = 'http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=EMPLEADOS.FIND_ALL';
const urlAdd = "http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=EMPLEADOS.ADD";
const urlUpdate = "http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=EMPLEADOS.UPDATE";
let employeesLoaded = false;

const fetchEmployees = async () => {
    try {
        const result = await fetch(urlEmployees);
        const data = await result.json();
        console.log('Empleados obtenidos de la API: ', data);
        printEmployees(data);
        employeesLoaded = true;
    } catch (error) {
        console.log('Error al obtener datos de la API: ', error);
    }
}

const printEmployees = (employees) => {
    const table = document.getElementById('tabla-empleados');
    const tbody = table.querySelector('tbody');
    tbody.innerHTML = ''; // Limpiar el contenido anterior
    table.style.display = 'table';

    employees.forEach(employee => {
        const {
            _nombre,
            _apellidos,
            _DNI,
            _correo,
            _contrasena,
            _idDepartamento,
            _idTrabajo,
            _idEmpleado,
            _telefono,
            _salario,
            _fechaNac,
            _fechaCont,
        } = employee;

        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${_idEmpleado}</td>
            <td>${_idDepartamento}</td>
            <td>${_idTrabajo}</td>
            <td>${_nombre}</td>
            <td>${_apellidos}</td>
            <td>${_DNI}</td>
            <td>${_correo}</td>
            <td>${_contrasena}</td>
            <td>${_telefono}</td>
            <td>${_salario}</td>
            <td>${_fechaNac}</td>
            <td>${_fechaCont}</td>
        `;
        tbody.appendChild(row);
    });
}


const toggleEmployees = () => {
    const table = document.getElementById('tabla-empleados');
    if (table.style.display === 'none') {
        if (!employeesLoaded) {
            fetchEmployees();
        } else {
            table.style.display = 'table';
        }
    } else {
        table.style.display = 'none';
    }
}

function registrarEmpleado() {
    const empleado = {
        _idEmpleado: document.getElementById('casilla-id-add').value,
        _idDepartamento: document.getElementById('casilla-dep').value,
        _idTrabajo: document.getElementById('casilla-job').value,
        _nombre: document.getElementById('casilla-name').value,
        _apellidos: document.getElementById('casilla-surname').value,
        _DNI: document.getElementById('casilla-dni').value,
        _correo: document.getElementById('casilla-correo').value,
        _contrasena: document.getElementById('casilla-contra').value,
        _telefono: document.getElementById('casilla-telefono').value,
        _salario: document.getElementById('casilla-salario').value,
        _fechaNac: document.getElementById('casilla-fecha-nac').value,
        _fechaCont: document.getElementById('casilla-fecha-cont').value
    };

    console.log("Datos enviados:", empleado); // Log para depuración

    fetch("http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=EMPLEADOS.ADD", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(empleado)
    })
    .then(response => {
        console.log("Respuesta recibida:", response); // Log para depuración
        if (!response.ok) {
            throw new Error('Error en la respuesta del servidor');
        }
        return response.json();
    })
    .then(data => alert('Empleado añadido:', data))
    .catch(error => console.error('Error:', error));
}
const toggleEmpleadoAdd = () => {
    const formulario = document.getElementById('formulario-add');
    if(formulario.style.display === 'none'){
        formulario.style.display = 'block'
    }
    else{
        formulario.style.display = 'none'
    }
}

function updateEmpleados() {
    const empleado = {
        _idEmpleado: document.getElementById('casilla-id-upt').value,
        _idDepartamento: document.getElementById('casilla-dep-upt').value,
        _idTrabajo: document.getElementById('casilla-job-upt').value,
        _nombre: document.getElementById('casilla-name-upt').value,
        _apellidos: document.getElementById('casilla-surname-upt').value,
        _DNI: document.getElementById('casilla-dni-upt').value,
        _correo: document.getElementById('casilla-correo-upt').value,
        _contrasena: document.getElementById('casilla-contra-upt').value,
        _telefono: document.getElementById('casilla-telefono-upt').value,
        _salario: document.getElementById('casilla-salario-upt').value,
        _fechaNac: document.getElementById('casilla-fecha-nac-upt').value,
        _fechaCont: document.getElementById('casilla-fecha-cont-upt').value
    };

    console.log("Datos enviados:", empleado); // Log para depuración

    fetch("http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=EMPLEADOS.UPDATE", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(empleado)
    })
    .then(response => {
        console.log("Respuesta recibida:", response); // Log para depuración
        if (!response.ok) {
            throw new Error('Error en la respuesta del servidor');
        }
        return response.json();
    })
    .then(data => alert('Empleado actualizado:', data))
    .catch(error => console.error('Error:', error));
}
const toggleProductUpdate = () => {
    const formulario = document.getElementById('formulario-update');
    if(formulario.style.display === 'none'){
        formulario.style.display = 'block'
    }
    else{
        formulario.style.display = 'none'
    }
}

const deleteEmployee = async (idEmpleado) => {
    try {
        const urlDeleteEmpleado = `http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=EMPLEADOS.DELETE&ID_EMPLEADO=${idEmpleado}`;
        await fetch(urlDeleteEmpleado, {
            method: 'DELETE'
        })
            alert('Empleado eliminado.');
    } catch (error) {
        console.error('Error al eliminar al empleado:', error);
    }
};
const toggleEmployeeDelete = () => {
    const formulario = document.getElementById('formulario-delete');
    if(formulario.style.display === 'none'){
        formulario.style.display = 'block'
    }
    else{
        formulario.style.display = 'none'
    }
}


document.getElementById('formulario-delete').addEventListener('submit', (event) => {
    event.preventDefault();
    const idEmpleado = document.getElementById('casilla-id').value;
    if (idEmpleado) {
        deleteEmployee(idEmpleado);
    } else {
        alert('Debe ingresar un ID válido.');
    }
});