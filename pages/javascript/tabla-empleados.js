const urlEmployees = 'http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=EMPLEADOS.FIND_ALL';
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
    const boton = document.getElementById('boton-find')
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