const urlEmployees = 'http://localhost:8080/ValdeBurgerBuck02/Controller?ACTION=EMPLEADOS.FIND_ALL';

const fetchEmployees = async ()=> {
    try{
        const result = await fetch(urlEmployees);
        const data = await result.json();
        console.log('Empleados obtenidos de la API: ', data);
        printEmployees(data);
    }catch(error){
        console.log('Error al obtener datos de la API: ', error);
    }
}

const printEmployees = (employees) => {
    const table = document.getElementById('tabla-empleados');
    const tbody = table.querySelector('tbody');
    table.style.display= 'table';

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
        <td>${_apellidos}</td>
        <td>${_DNI}</td>
        <td>${_correo}</td>
        <td>${_contrasena}</td>
        <td>${_idDepartamento}</td>
        <td>${_idTrabajo}</td>
        <td>${_nombre}</td>
        <td>${_telefono}</td>
        <td>${_salario}</td>
        <td>${_fechaNac}</td>
        <td>${_fechaCont}</td>
        `;
        tbody.appendChild(row);
    })
}
//fetchEmployees();