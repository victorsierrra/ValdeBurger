document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const adminEmail = 'admin@valdeburger.com';
    const adminPassword = 'admin123';
    const employeeEmail = 'employee@valdeburger.com';
    const employeePassword = 'employee123';

    if (email === adminEmail && password === adminPassword) {
        window.location.href = '../html/homeadmin.html';
    } else if (email === employeeEmail && password === employeePassword) {
        window.location.href = '../html/homeemployee.html';
    } else {
        Swal.fire("Access Denegade!");
    }
});